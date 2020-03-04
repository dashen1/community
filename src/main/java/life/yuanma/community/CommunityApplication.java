package life.yuanma.community;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("life.yuanma.community.mapper")
public class CommunityApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(CommunityApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CommunityApplication.class);
    }
}
