package com.genai.schemamapping.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class SourceTable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String tableName;


    private String description;


    @OneToMany(
            mappedBy = "sourceTable",
            cascade = CascadeType.ALL
    )
    private List<SourceColumn> columns;



    public SourceTable() {
    }



    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getTableName() {
        return tableName;
    }


    public void setTableName(String tableName) {
        this.tableName = tableName;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public List<SourceColumn> getColumns() {
        return columns;
    }


    public void setColumns(List<SourceColumn> columns) {
        this.columns = columns;
    }

}
