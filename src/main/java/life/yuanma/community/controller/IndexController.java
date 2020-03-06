package life.yuanma.community.controller;

import life.yuanma.community.cache.HotTagCache;
import life.yuanma.community.dto.PaginationDTO;
import life.yuanma.community.model.User;
import life.yuanma.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private HotTagCache hotTagCache;

    @GetMapping("/")
    public String index(Model model,
                              @RequestParam(name = "page",defaultValue = "1") Integer page,
                              @RequestParam(name = "size",defaultValue = "2") Integer size,
                              @RequestParam(name = "search",required = false) String search,
                              @RequestParam(name = "tag",required = false) String tag) {
        PaginationDTO pagination = questionService.getList(page,size,search,tag);
        List<String> tags = hotTagCache.getHot();
        model.addAttribute("pagination",pagination);
        model.addAttribute("search",search);
        model.addAttribute("tags",tags);
        model.addAttribute("tag",tag);
        return "index";
    }
}
