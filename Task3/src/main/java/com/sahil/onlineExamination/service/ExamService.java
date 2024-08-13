package com.sahil.onlineExamination.service;

import com.sahil.onlineExamination.model.Answer;
import com.sahil.onlineExamination.model.Question;
import com.sahil.onlineExamination.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ExamService {

    private static final Logger logger = Logger.getLogger(ExamService.class.getName());

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }

    public int calculateScore(List<Answer> answers) {
        int score = 0;
        for (Answer answer : answers) {
            Optional<Question> questionOpt = questionRepository.findById(answer.getQuestionId());
            if (questionOpt.isPresent()) {
                Question question = questionOpt.get();
                logger.info("Question ID: " + question.getId());
                logger.info("Selected Answer: " + answer.getSelectedAnswer());
                logger.info("Correct Answer: " + question.getCorrectAnswer());

                if (question.getCorrectAnswer().equals(answer.getSelectedAnswer())) {
                    score++;
                }
            } else {
                logger.warning("Question not found for ID: " + answer.getQuestionId());
            }
        }
        logger.info("Final Score: " + score);
        return score;
    }
}
