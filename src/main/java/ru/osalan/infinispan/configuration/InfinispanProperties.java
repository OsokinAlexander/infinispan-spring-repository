package ru.osalan.infinispan.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "infinispan")
public class InfinispanProperties {

    private String host;
    private Integer port;
    private String user;
    private String password;
    private Integer batchSize;
    private String defaultCacheTemplate;

}