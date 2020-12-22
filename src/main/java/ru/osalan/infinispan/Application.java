package ru.osalan.infinispan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.osalan.infinispan.repository.EnableInfinispanRepositories;

@EnableInfinispanRepositories(basePackages = {"ru.osalan.infinispan.client"})
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
