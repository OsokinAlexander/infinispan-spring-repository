package ru.osalan.infinispan.repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.infinispan.client.hotrod.RemoteCache;
import org.springframework.cache.CacheManager;
import org.springframework.data.keyvalue.core.AbstractKeyValueAdapter;
import org.springframework.data.keyvalue.core.ForwardingCloseableIterator;
import org.springframework.data.util.CloseableIterator;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class InfinispanKeyValueAdapter extends AbstractKeyValueAdapter {

    /** Spring cache manager. */
    private CacheManager cacheManager;

    @Override
    public <T> T get(Object id, String keyspace, Class<T> type) {
        return (T) ((RemoteCache) cacheManager.getCache(keyspace).getNativeCache()).get(id);
    }

    @Override
    public Object put(Object id, Object item, String keyspace) {
        cacheManager.getCache(keyspace).put(id, item);
        return get(id, keyspace);
    }

    @Override
    public boolean contains(Object id, String keyspace) {
        return ((RemoteCache) cacheManager.getCache(keyspace).getNativeCache()).containsKey(id);
    }

    @Override
    public Object get(Object id, String keyspace) {
        return cacheManager.getCache(keyspace).get(id);
    }

    @Override
    public Object delete(Object id, String keyspace) {
        cacheManager.getCache(keyspace).evict(id);
        return null;
    }

    @Override
    public Iterable<?> getAllOf(String keyspace) {
        return ((RemoteCache) cacheManager.getCache(keyspace).getNativeCache()).entrySet();
    }

    @Override
    public CloseableIterator<Map.Entry<Object, Object>> entries(String keyspace) {
        return new ForwardingCloseableIterator<>(
                ((RemoteCache) cacheManager.getCache(keyspace).getNativeCache()).entrySet().iterator());
    }

    @Override
    public void deleteAllOf(String keyspace) {
        cacheManager.getCache(keyspace).clear();
    }

    @Override
    public void clear() {
        this.cacheManager.getCacheNames().clear();
    }

    @Override
    public long count(String keyspace) {
        return ((RemoteCache) cacheManager.getCache(keyspace).getNativeCache()).size();
    }

    @Override
    public void destroy() throws Exception {
        clear();
    }

//    public List executeQuery(String queryString, String keyspace) {
//        Query query = Search.getQueryFactory(
//                (RemoteCache) cacheManager.getCache(keyspace).getNativeCache()
//        ).create(queryString);
//        return query.list();
//    }
}
