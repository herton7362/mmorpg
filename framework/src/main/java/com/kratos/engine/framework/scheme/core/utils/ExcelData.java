package com.kratos.engine.framework.scheme.core.utils;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.List;

@Setter
@Getter
public abstract class ExcelData {
    protected List<List<String>> dataList;
    protected List<ExcelColumn> fields;
    protected String fileName;

    ExcelData(File file) {
        this.dataList = ExcelUtils.readExcel(file);
        this.fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
        this.fields = readSchema(file);
    }

    abstract List<ExcelColumn> readSchema(File file);

    public abstract String getJsonCode();
}
