package ru.osalan.infinispan.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface InfinispanRepository <T, ID> extends PagingAndSortingRepository<T, ID> {

}
