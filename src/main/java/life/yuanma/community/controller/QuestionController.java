package life.yuanma.community.controller;

import life.yuanma.community.dto.CommentDTO;
import life.yuanma.community.dto.QuestionDTO;
import life.yuanma.community.eunm.CommentTypeEunm;
import life.yuanma.community.service.CommentService;
import life.yuanma.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {


    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        List<QuestionDTO> relatedQuestions = questionService.selectRelated(questionDTO);
        List<CommentDTO> commentDTO=commentService.listByTargetId(id, CommentTypeEunm.QUESTION);
        questionService.addViewCount(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",commentDTO);
        model.addAttribute("relatedQuestions",relatedQuestions);
        return "question";
    }

}
