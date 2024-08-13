package com.sahil.onlineExamination.controller;

import com.sahil.onlineExamination.model.Answer;
import com.sahil.onlineExamination.model.AnswersWrapper;
import com.sahil.onlineExamination.model.Question;
import com.sahil.onlineExamination.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class ExamController {

    private static final Logger logger = Logger.getLogger(ExamController.class.getName());

    @Autowired
    private ExamService examService;

    @GetMapping("/exam")
    public String showExamPage(Model model) {
        List<Question> questions = examService.getQuestions();
        AnswersWrapper answersWrapper = new AnswersWrapper();
        List<Answer> answers = new ArrayList<>(questions.size());
        for (Question question : questions) {
            Answer answer = new Answer();
            answer.setQuestionId(question.getId());
            answers.add(answer);
        }
        answersWrapper.setAnswers(answers);
        model.addAttribute("questions", questions);
        model.addAttribute("answersWrapper", answersWrapper);
        return "exam";
    }

    @PostMapping("/submitExam")
    public String submitExam(AnswersWrapper answersWrapper, Model model, HttpSession session) {
        logger.info("Submitted Answers: " + answersWrapper.getAnswers());
        int score = examService.calculateScore(answersWrapper.getAnswers());
        session.setAttribute("score", score);
        return "redirect:/result";
    }

    @GetMapping("/result")
    public String showResult(HttpSession session, Model model) {
        Integer score = (Integer) session.getAttribute("score");
        model.addAttribute("score", score);
        return "result";
    }
}
