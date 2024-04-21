package cloud.jordaan.juan.books;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@SpringBootApplication
public class BooksApplication {
    Logger log = LoggerFactory.getLogger(BooksApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(BooksApplication.class, args);
    }

    // This is for information purposes
    // should not be here because it counts against code coverage
    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        event.getApplicationContext()
            .getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping.class)
            .getHandlerMethods()
            .forEach((key, value) -> log.info("{} {}", key, value));
    }
}