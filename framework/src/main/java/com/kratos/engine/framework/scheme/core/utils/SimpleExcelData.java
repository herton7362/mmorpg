package com.kratos.engine.framework.scheme.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Log4j
public class SimpleExcelData extends ExcelData {
    private final static String SCHEMA_BASE_PATH = "/schema/schema_";

    private SimpleExcelData(File file) {
        super(file);
    }

    public static SimpleExcelData readExcel(File file) {
        return new SimpleExcelData(file);
    }

    public String getJsonCode() {
        // 前一行为说明信息，跳过
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObj;
        for (int i = 1; i < dataList.size(); i++) {
            jsonObj = new JSONObject();
            for (ExcelColumn column : fields) {
                jsonObj.put(column.getFieldName(), column.convert(dataList.get(i).get(column.getColumnIndex())));
            }
            jsonArray.add(jsonObj);
        }
        return JSON.toJSONString(jsonArray, SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
    }

    public String getFileName() {
        return this.fileName;
    }

    public List<List<String>> getDataList() {
        return dataList;
    }

    public List<ExcelColumn> getFields() {
        return fields;
    }

    List<ExcelColumn> readSchema(File file) {
        String schemaPath = file.getParentFile().getAbsolutePath() + SCHEMA_BASE_PATH + file.getName();
        FileInputStream in = null;
        try {
            in = new FileInputStream(schemaPath);
            HSSFSheet sheet = new HSSFWorkbook(in).getSheetAt(0);
            List<ExcelColumn> columns = Lists.newArrayList();
            for (int rowNum = 1; true; rowNum++) {
                HSSFRow row = sheet.getRow(rowNum);
                if (ExcelUtils.isEnd(row)) break;
                columns.add(new ExcelColumn(row));
            }
            return columns;
        } catch (Exception e) {
            log.error("读schema文件出错" + e.getMessage(), e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error("", e);
                }
            }
        }
        return null;
    }
}
