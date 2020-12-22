package ru.osalan.infinispan.configuration;

import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.commons.marshall.JavaSerializationMarshaller;
import org.infinispan.spring.starter.remote.InfinispanRemoteConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.keyvalue.core.KeyValueTemplate;
import ru.osalan.infinispan.repository.InfinispanKeyValueAdapter;

@Configuration
public class InfinispanConfiguration extends CachingConfigurerSupport {

    public static final String REGEX_PACKAGES_AND_CLASSES = "^.*$";
    public static final String SASL_MECHANISM = "DIGEST-SHA-512";
    @Autowired
    private InfinispanProperties infinispanProperties;

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public InfinispanKeyValueAdapter getInfinispanAdapter() {
        return new InfinispanKeyValueAdapter(
                applicationContext.getBean(CacheManager.class)
        );
    }

    @Bean("infinispanKeyValueTemplate")
    public KeyValueTemplate getInfinispanKeyValueTemplate() {
        return new KeyValueTemplate(getInfinispanAdapter());
    }

    @Bean
    public InfinispanRemoteConfigurer infinispanRemoteConfigurer() {
        return () -> new ConfigurationBuilder()
                .marshaller(new JavaSerializationMarshaller())
                .addJavaSerialWhiteList(REGEX_PACKAGES_AND_CLASSES)
                .batchSize(infinispanProperties.getBatchSize())
                .addServer()
                    .host(infinispanProperties.getHost())
                    .port(infinispanProperties.getPort())
                    .security().authentication()
                        .enable()
                        .saslMechanism(SASL_MECHANISM)
                        .username(infinispanProperties.getUser())
                        .password(infinispanProperties.getPassword())
                .build();
    }

}
