package com.genai.schemamapping.repository;

import com.genai.schemamapping.entity.TargetColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetColumnRepository extends JpaRepository<TargetColumn, Long> {

}