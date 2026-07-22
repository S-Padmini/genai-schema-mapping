package com.genai.schemamapping.entity;

import jakarta.persistence.*;

@Entity
public class SourceColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String columnName;

    private String dataType;

    private String sampleValue;

    @Column(length = 1000)
    private String businessDescription;

    @ManyToOne
    @JoinColumn(name = "source_table_id")
    private SourceTable sourceTable;

    public SourceColumn() {
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

    public String getSampleValue() {
        return sampleValue;
    }

    public void setSampleValue(String sampleValue) {
        this.sampleValue = sampleValue;
    }

    public String getBusinessDescription() {
        return businessDescription;
    }

    public void setBusinessDescription(String businessDescription) {
        this.businessDescription = businessDescription;
    }

    public SourceTable getSourceTable() {
        return sourceTable;
    }

    public void setSourceTable(SourceTable sourceTable) {
        this.sourceTable = sourceTable;
    }
}