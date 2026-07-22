package com.genai.schemamapping.repository;

import com.genai.schemamapping.entity.SourceTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceTableRepository extends JpaRepository<SourceTable, Long> {

}