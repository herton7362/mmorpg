package com.kratos.engine.framework.scheme.core.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * excel工具类
 *
 * @author herton
 */
public class ExcelUtils {
    private static Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

    public final static String jsTemplate = "excel2js.ftl";
    public final static String javaTemplate = "excel2java.ftl";

    public static String getStringValue(HSSFCell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                return cell.toString();
            case HSSFCell.CELL_TYPE_NUMERIC:
                String str = cell.toString();
                if (str.endsWith(".0")) {
                    return str.substring(0, str.length() - 2);
                } else {
                    return str;
                }

            default:
                return cell.toString();
        }
    }

    public static boolean isEnd(HSSFRow row) {
        if (row == null) {
            return true;
        }
        String str = getStringValue(row.getCell((short) 0, Row.RETURN_NULL_AND_BLANK));
        return str.isEmpty();
    }

    public static List<List<String>> readExcel(File file) {
        FileInputStream in = null;
        List<List<String>> result = new ArrayList<>();
        List<String> rowList;
        try {
            in = new FileInputStream(file);
            HSSFSheet sheet = new HSSFWorkbook(in).getSheetAt(0);
            for (int rowNum = 0; true; rowNum++) {
                HSSFRow row = sheet.getRow(rowNum);
                if (row == null) break;
                rowList = new ArrayList<>();
                result.add(rowList);
                String str = getStringValue(row.getCell((short) 0, Row.RETURN_NULL_AND_BLANK));
                if (StringUtils.isEmpty(str.isEmpty())) break;
                for (int cellNum = 0; true; cellNum++) {
                    String cellString = getStringValue(row.getCell(cellNum, Row.RETURN_NULL_AND_BLANK));
                    if (StringUtils.isEmpty(cellString)) break;
                    rowList.add(cellString);
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return result;
    }
}