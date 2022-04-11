import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication
@EnableEurekaServer//开启eureka服务
@EnableAutoConfiguration
@ComponentScan(basePackageClasses =EurekaApplication.class)
public class EurekaApplication {

    /**
     * 加载启动类，以启动类为当前springBoot的配置标准
     */
    public static void main(String[] args){
        SpringApplication.run(EurekaApplication.class,args);
    }
}
