package life.yuanma.community.schedule;

import life.yuanma.community.cache.HotTagCache;
import life.yuanma.community.mapper.QuestionMapper;
import life.yuanma.community.model.Question;
import life.yuanma.community.model.QuestionExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class HotScheduledTasks {

//    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private QuestionMapper questionMapper;

    @Scheduled(fixedRate = 10000)
//    @Scheduled(cron = "0 0 1 * * *")//每天1点计算
    public void reportCurrentTime() {
        int offset = 0;
        int limit = 2;
        log.info("The time is start{}", new Date());
        List<Question> list = new ArrayList<>();
        while( offset == 0 || list.size() ==limit){
            list = questionMapper.selectByExampleWithRowbounds(new QuestionExample(),new RowBounds(offset,limit));
            for (Question question :list) {
                Map<String, Integer> tagsMap = HotTagCache.getTags();
                String questionTag = question.getTag();
                String[] tags = StringUtils.split(questionTag, ",");
                    for (String tag : tags) {
                    Integer priority = tagsMap.get(tag);
                    if(priority != null){
                        tagsMap.put(tag,priority+5+question.getCommentCount());
                    }else{
                        tagsMap.put(tag,5+question.getCommentCount());
                    }
                }
                log.info("question",question.getId());
            }
            offset += limit;
        }
        HotTagCache.getTags().forEach(
                (k,v) -> {
                    System.out.print(k);
                    System.out.print(":");
                    System.out.print(v);
                    System.out.println();
                }
    );
        log.info("The time is stop {}", new Date());
    }
}
