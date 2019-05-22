package Creation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class Application  extends SpringBootServletInitializer {
    @Bean
    public MultipartConfigElement multipartFileUploadControllerConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("128000KB");
        factory.setMaxRequestSize("128000KB");
        return factory.createMultipartConfig();
    }
  //  static { System.setProperty("logback.configurationFile", "C:/Users/PC-User/Desktop/ServerPDF/src/main/resources/"+"config.xml");}
    private final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }



}