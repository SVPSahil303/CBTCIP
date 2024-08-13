package com.sahil.onlineExamination.repository;

import com.sahil.onlineExamination.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
