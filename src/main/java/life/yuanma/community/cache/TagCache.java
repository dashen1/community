package life.yuanma.community.cache;

import life.yuanma.community.dto.TagDTO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {
    public static List<TagDTO> getTags(){
        ArrayList<TagDTO> tagDTOS = new ArrayList<>();
        TagDTO program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("js","java","php","python"));
        tagDTOS.add(program);

        TagDTO framework = new TagDTO();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("Spring","SSH","MyBatis"));
        tagDTOS.add(framework);

        TagDTO server = new TagDTO();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("tomcat"));
        tagDTOS.add(server);

        TagDTO dataBase = new TagDTO();
        dataBase.setCategoryName("数据库");
        dataBase.setTags(Arrays.asList("MySQL","sql"));
        tagDTOS.add(dataBase);

        TagDTO tools = new TagDTO();
        tools.setCategoryName("开发工具");
        tools.setTags(Arrays.asList("idea","eclipse"));
        tagDTOS.add(tools);
        return tagDTOS;
    }

    public static String filterInvalid(String tags){
        String[] split = new String[0];
        String invalid = null;
        List<TagDTO> tagDTOList = getTags();
        List<String> tagList = tagDTOList.stream().flatMap(tagDTO -> tagDTO.getTags().stream()).collect(Collectors.toList());
        if (tags.contains(",")) {
            split = StringUtils.split(tags, ",");
        }else {
            split = new String[]{tags};
        }
        invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}
