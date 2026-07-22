package com.genai.schemamapping.repository;

import com.genai.schemamapping.entity.SourceColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceColumnRepository extends JpaRepository<SourceColumn, Long> {

}