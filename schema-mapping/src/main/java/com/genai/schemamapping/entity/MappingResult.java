package com.genai.schemamapping.entity;

import jakarta.persistence.*;

@Entity
public class MappingResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_column_id")
    private SourceColumn sourceColumn;

    @ManyToOne
    @JoinColumn(name = "target_column_id")
    private TargetColumn targetColumn;

    private Double confidenceScore;

    private String status;

    public MappingResult() {
    }

    public Long getId() {
        return id;
    }

    public SourceColumn getSourceColumn() {
        return sourceColumn;
    }

    public void setSourceColumn(SourceColumn sourceColumn) {
        this.sourceColumn = sourceColumn;
    }

    public TargetColumn getTargetColumn() {
        return targetColumn;
    }

    public void setTargetColumn(TargetColumn targetColumn) {
        this.targetColumn = targetColumn;
    }

    public Double getConfidenceScore() {
        return confidenceScore;
    }

    public void setConfidenceScore(Double confidenceScore) {
        this.confidenceScore = confidenceScore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}