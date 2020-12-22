package ru.osalan.infinispan.repository;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.data.keyvalue.core.KeyValueTemplate;
import org.springframework.data.keyvalue.repository.config.KeyValueRepositoryConfigurationExtension;
import org.springframework.data.repository.config.RepositoryConfigurationSource;

import java.util.Collection;
import java.util.Collections;

@NoArgsConstructor
public class InfinispanRepositoryConfigurationExtension extends KeyValueRepositoryConfigurationExtension {

    @Override
    public String getModuleName() {
        return "Infinispan";
    }

    @Override
    protected String getModulePrefix() {
        return "infinispan";
    }

    @Override
    protected String getDefaultKeyValueTemplateRef() {
        return "infinispanKeyValueTemplate";
    }

    @Override
    protected Collection<Class<?>> getIdentifyingTypes() {
        return Collections.singleton(InfinispanRepository.class);
    }

    @Override
    protected AbstractBeanDefinition getDefaultKeyValueTemplateBeanDefinition(
            RepositoryConfigurationSource configurationSource) {
        RootBeanDefinition infinispanKeyValueAdapterDefinition = new RootBeanDefinition(InfinispanKeyValueAdapter.class);
        RootBeanDefinition keyValueTemplateDefinition = new RootBeanDefinition(KeyValueTemplate.class);
        ConstructorArgumentValues constructorArgumentValuesForKeyValueTemplate = new ConstructorArgumentValues();
        constructorArgumentValuesForKeyValueTemplate.addGenericArgumentValue(infinispanKeyValueAdapterDefinition);
        keyValueTemplateDefinition.setConstructorArgumentValues(constructorArgumentValuesForKeyValueTemplate);
        return keyValueTemplateDefinition;
    }
}
