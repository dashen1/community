package life.yuanma.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VideoController {

    @GetMapping("/video")
    public String video(){
        return "video";
    }
    @GetMapping("/videoInfo")
    public String play(@RequestParam(name = "aid") String aid,
                       @RequestParam(name = "page",defaultValue = "1") String page,
                       Model model){
        model.addAttribute("aid",aid);
        model.addAttribute("page",page);
        return "videoInfo";
    }
}
