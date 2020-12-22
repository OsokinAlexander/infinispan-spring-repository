package ru.osalan.infinispan.repository;

import lombok.NoArgsConstructor;
import org.springframework.data.repository.config.RepositoryBeanDefinitionRegistrarSupport;
import org.springframework.data.repository.config.RepositoryConfigurationExtension;

import java.lang.annotation.Annotation;

@NoArgsConstructor
public class InfinispanRepositoriesRegistrar extends RepositoryBeanDefinitionRegistrarSupport {

    @Override
    protected Class<? extends Annotation> getAnnotation() {
        return EnableInfinispanRepositories.class;
    }

    @Override
    protected RepositoryConfigurationExtension getExtension() {
        return new InfinispanRepositoryConfigurationExtension();
    }
}
