package life.yuanma.community.cache;

import java.util.HashMap;
import java.util.Map;

public class HotTagCache {
    private static Map<String,Integer> tags = new HashMap<>();
    public static synchronized Map<String,Integer> getTags(){
        return tags;
    }
}
