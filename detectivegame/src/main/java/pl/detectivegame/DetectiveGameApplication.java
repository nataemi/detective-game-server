package pl.detectivegame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.TimeZone;


@SpringBootApplication
@EntityScan(basePackageClasses = {
        DetectiveGameApplication.class
})
public class DetectiveGameApplication {

    private static final Logger log = LoggerFactory.getLogger(DetectiveGameApplication.class);

    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(DetectiveGameApplication.class, args);
        log.info("Detective game application started");
    }
}

