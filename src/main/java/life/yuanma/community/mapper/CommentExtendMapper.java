package life.yuanma.community.mapper;

import life.yuanma.community.model.Comment;
import life.yuanma.community.model.Question;

public interface CommentExtendMapper {
    int incCommentCount(Comment comment);
}
