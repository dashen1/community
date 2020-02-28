package life.yuanma.community.mapper;

import life.yuanma.community.dto.QuestionQueryDTO;
import life.yuanma.community.model.Question;
import life.yuanma.community.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtendMapper {

    int incView(Question question);
    int incCommentCount(Question question);
    List<Question> selectRelated(Question question);

    Integer countBySearch(QuestionQueryDTO questionQueryDTO);

    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}