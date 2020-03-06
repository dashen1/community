package life.yuanma.community.dto;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class HotTagsDTO implements Comparable{//为什么实现comparable
    private String name;
    private Integer priority;

    @Override
    public int compareTo(Object o) {
        return this.getPriority() - ((HotTagsDTO) o).getPriority();
    }
}
