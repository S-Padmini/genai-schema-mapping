package com.genai.schemamapping.repository;

import com.genai.schemamapping.entity.MappingResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MappingResultRepository extends JpaRepository<MappingResult, Long> {

}