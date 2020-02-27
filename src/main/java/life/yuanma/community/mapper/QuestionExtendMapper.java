package life.yuanma.community.mapper;

import life.yuanma.community.model.Question;
import life.yuanma.community.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtendMapper {

    int incView(Question question);
    int incCommentCount(Question question);
    List<Question> selectRelated(Question question);
}