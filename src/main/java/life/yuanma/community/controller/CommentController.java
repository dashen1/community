package life.yuanma.community.controller;

import life.yuanma.community.dto.CommentCreateDTO;
import life.yuanma.community.dto.CommentDTO;
import life.yuanma.community.dto.ResultDTO;
import life.yuanma.community.eunm.CommentTypeEunm;
import life.yuanma.community.exception.CustomizedErrorCode;
import life.yuanma.community.mapper.CommentMapper;
import life.yuanma.community.model.Comment;
import life.yuanma.community.model.User;
import life.yuanma.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentService commentService;

//    通過responseBody將hashmap序列化成json對象
    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if (user == null){
            return ResultDTO.errorResouce(CustomizedErrorCode.NOT_LOGIN);
        }
        if (commentCreateDTO == null || commentCreateDTO.getContent() == null ||commentCreateDTO.getContent() == "") {
            return ResultDTO.errorResouce(CustomizedErrorCode.CONTENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentID());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        System.out.println(commentCreateDTO.getParentID()+":"+ commentCreateDTO.getContent()+":"+ commentCreateDTO.getType());
        comment.setCommentator(1L);
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setLikeCount(1L);
        comment.setCommentator(user.getId());
        commentService.insert(comment);
        return ResultDTO.loginSuccess();

//        commentMapper.insert(comment);
//        Map<Object,Object> hashMap = new HashMap<>();
//        hashMap.put("message","成功");
//        return hashMap;
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDTO<List> comments(@PathVariable(name = "id") Long id){
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEunm.COMMENT);
        return ResultDTO.errorResouce(commentDTOS);
    }
}
