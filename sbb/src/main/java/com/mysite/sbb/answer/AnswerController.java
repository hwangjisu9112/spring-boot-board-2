package com.mysite.sbb.answer;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/answer")
@RequiredArgsConstructor

public class AnswerController {
	
	private final QuestionService questionService;
	
	private final AnswerService answerService ;
	
	
	@PostMapping("/create/{id}")
	public String createAnswer(Model model,  @PathVariable("id") Integer id, @RequestParam String content
			
			,@Valid AnswerForm answerForm, BindingResult bindingResult) {
        Question question = this.questionService.getQuestion(id);
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "question_detail";
        }
        // 답변을 저장하는 기능
        this.answerService.create(question, answerForm.getContent());

        return String.format("redirect:/question/detail/%s", id);
    }
	
	
	
	

}
