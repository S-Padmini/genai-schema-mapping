package com.genai.schemamapping.entity;

import jakarta.persistence.*;

@Entity
public class TargetColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String columnName;

    private String dataType;

    @Column(length = 1000)
    private String businessDescription;

    private String erpModule;

    public TargetColumn() {
    }

    public Long getId() {
        return id;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getBusinessDescription() {
        return businessDescription;
    }

    public void setBusinessDescription(String businessDescription) {
        this.businessDescription = businessDescription;
    }

    public String getErpModule() {
        return erpModule;
    }

    public void setErpModule(String erpModule) {
        this.erpModule = erpModule;
    }
}