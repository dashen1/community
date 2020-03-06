package life.yuanma.community.cache;

import life.yuanma.community.dto.HotTagsDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Data
public class HotTagCache {
    private Map<String,Integer> tags = new HashMap<>();
    private List<String> hot = new ArrayList<>();
    public void updateTags(Map<String,Integer> tags){
        int max = 3;
        PriorityQueue<HotTagsDTO> priorityQueue = new PriorityQueue<>(max);
        tags.forEach((name,priority) -> {
            HotTagsDTO hotTagsDTO = new HotTagsDTO();
            hotTagsDTO.setName(name);
            hotTagsDTO.setPriority(priority);
            if (priorityQueue.size() < max) {
                priorityQueue.add(hotTagsDTO);
            }else{
                HotTagsDTO minDTO = priorityQueue.peek();
                if(hotTagsDTO.compareTo(minDTO) > 0){
                    priorityQueue.poll();
                    priorityQueue.add(hotTagsDTO);
                }
            }
        });
        List<String> sortTags = new ArrayList<>();
        HotTagsDTO poll = priorityQueue.poll();
        while(poll != null){
            sortTags.add(0,poll.getName());
            poll = priorityQueue.poll();
        }
        hot = sortTags;
        System.out.println(hot);
    }
}
