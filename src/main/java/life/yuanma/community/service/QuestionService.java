package life.yuanma.community.service;

import life.yuanma.community.dto.PaginationDTO;
import life.yuanma.community.dto.QuestionDTO;
import life.yuanma.community.exception.CustomizedErrorCode;
import life.yuanma.community.exception.CustomizedException;
import life.yuanma.community.mapper.QuestionExtendMapper;
import life.yuanma.community.mapper.QuestionMapper;
import life.yuanma.community.mapper.UserMapper;
import life.yuanma.community.model.Question;
import life.yuanma.community.model.QuestionExample;
import life.yuanma.community.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionExtendMapper questionExtendMapper;

    public PaginationDTO getList(Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
//        Integer count = questionMapper.count();mybatis迁移
        Integer count = (int)questionMapper.countByExample(new QuestionExample());
        if(count % size == 0){
            totalPage = count / size;
        }else{
            totalPage = count /size +1;
        }
        if(page < 1){
            page = 1;
        }
        if(page > totalPage){
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage,page);
        QuestionExample questionExample = new QuestionExample();
        questionExample.getOrderByClause("gmt_create desc");
        Integer offset = size*(page - 1);
//        List<Question> questions = questionMapper.getList(offset,size);分页插件
        List<Question> questions = questionMapper.selectByExampleWithBLOBsWithRowbounds(questionExample, new RowBounds(offset, size));

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
//            User user = userMapper.findById(question.getCreator());
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOList);
        return  paginationDTO;
    }

    public PaginationDTO list(Long userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
//        Integer count = questionMapper.countById(userId);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(userId);
        Integer count = (int)questionMapper.countByExample(questionExample);
        if(count % size == 0){
            totalPage = count / size;
        }else{
            totalPage = count /size +1;
        }
        if(page < 1){
            page = 1;
        }
        if(page > totalPage){
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage,page);
        Integer offset = size*(page - 1);
//        List<Question> questions = questionMapper.list(userId,offset,size);
        QuestionExample questionExample1 = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithBLOBsWithRowbounds(questionExample1, new RowBounds(offset, size));

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
//            User user = userMapper.findById(question.getCreator());
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOList);
        return  paginationDTO;
    }

    public QuestionDTO getById(Long id) {
//        Question question = questionMapper.getById(id);
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomizedException(CustomizedErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
//        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtModified());
            question.setViewCount(0);
            question.setCommentCount(0);
            question.setLikeCount(0);
            questionMapper.insert(question);
        }else {
            //更新
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(question.getGmtModified());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria()
                    .andIdEqualTo(question.getId());
            int update = questionMapper.updateByExampleSelective(updateQuestion, questionExample);
            if(update != 1){
                throw new CustomizedException(CustomizedErrorCode.QUESTION_NOT_FOUND);
            }
//            questionMapper.update(question);
        }
    }

    public void addViewCount(Long id) {
//        Question question = questionMapper.selectByPrimaryKey(id);
//        Question updateQuestion = new Question();
//            updateQuestion.setViewCount(question.getViewCount()+1);
//        QuestionExample questionExample = new QuestionExample();
//        questionExample.createCriteria()
//                .andIdEqualTo(id);
//        questionMapper.updateByExampleSelective(updateQuestion, questionExample);
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtendMapper.incView(question);

    }

    public List<QuestionDTO> selectRelated(QuestionDTO questionDTO) {
        if(StringUtils.isEmpty(questionDTO.getTag())){
            return new ArrayList<>();
        }
        String[] tags = questionDTO.getTag().split(",");
//        String[] tags = StringUtils.split(questionDTO.getTag(),",");
        if (tags == null || tags.length == 0) {
            return new ArrayList<>();
        }
        String regexpTags = Arrays.stream(tags).collect(Collectors.joining("|"));
        Question question = new Question();
        question.setId(questionDTO.getId());
        question.setTag(regexpTags);
        List<Question> questions = questionExtendMapper.selectRelated(question);
        List<QuestionDTO> questionDTOList = questions.stream().map(q -> {
            QuestionDTO dto = new QuestionDTO();
            BeanUtils.copyProperties(q, dto);
            return dto;
        }).collect(Collectors.toList());
        return questionDTOList;
    }
}
