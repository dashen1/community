package life.yuanma.community.dto;

import life.yuanma.community.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
//    private Integer id;
    private Long id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer commentCount;
    private Integer likeCount;
    private Integer viewCount;
    private String tag;
    private User user;

}
