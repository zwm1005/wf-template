package fun.werfamily.template.facade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description: 启动类
 * <p>
 *
 *  @Author Mr.WenMing
 * Created on 2021/12/7.
 */
@SpringBootApplication(scanBasePackages = {"fun.werfamily.template.*"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
