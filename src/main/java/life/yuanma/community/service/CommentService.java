package life.yuanma.community.service;

import life.yuanma.community.dto.CommentDTO;
import life.yuanma.community.eunm.CommentTypeEunm;
import life.yuanma.community.exception.CustomizedErrorCode;
import life.yuanma.community.exception.CustomizedException;
import life.yuanma.community.mapper.*;
import life.yuanma.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionExtendMapper questionExtendMapper;

    @Autowired
    private CommentExtendMapper commentExtendMapper;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void insert(Comment comment) {
        if(comment.getParentId() == null || comment.getParentId() == 0){
            throw new CustomizedException(CustomizedErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        // || !CommentTypeEunm.isExit(comment.getType())
        if(comment.getType() == null){
             throw  new CustomizedException(CustomizedErrorCode.TYPE_PARAM_WRONG);
        }
        if(comment.getType() == CommentTypeEunm.COMMENT.getType()){
            //回复评论
            CommentExample example = new CommentExample();
            example.createCriteria().andParentIdEqualTo(comment.getParentId());
            List<Comment> dbComment = commentMapper.selectByExample(example);
//            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(dbComment == null){
                throw new CustomizedException(CustomizedErrorCode.COMMENT_NOT_FIND);
            }
            commentMapper.insert(comment);
            //增加评论
            Comment parentComment = new Comment();
            parentComment.setId(comment.getParentId());
            parentComment.setCommentCount(1);
            commentExtendMapper.incCommentCount(parentComment);
        }else {
            //回复问题
             Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if(question == null){
                throw new CustomizedException(CustomizedErrorCode.QUESTION_NOT_FOUND);
            }
            comment.setCommentCount(0);
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtendMapper.incCommentCount(question);
        }
     }

    public List<CommentDTO> listByTargetId(Long id, CommentTypeEunm type) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(type.getType());
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> commentList = commentMapper.selectByExample(commentExample);
        if(commentList.size() == 0){
            return new ArrayList<>();
        }
        //??????
        Set<Long> collect = commentList.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userId = new ArrayList<>();
        userId.addAll(collect);
        //获取评论人并转为map
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(userId);
        List<User> users = userMapper.selectByExample(userExample);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
       //转换comment为commentDTO
        List<CommentDTO> commentDTOS = commentList.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOS;
    }
}
