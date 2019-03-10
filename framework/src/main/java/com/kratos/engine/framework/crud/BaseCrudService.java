package com.kratos.engine.framework.crud;

import com.kratos.engine.framework.cache.AbstractCacheContainer;
import com.kratos.engine.framework.cache.CacheOptions;
import com.kratos.engine.framework.cache.DefaultCacheContainer;
import com.kratos.engine.framework.cache.Persistable;
import com.kratos.engine.framework.db.BaseEntity;
import com.kratos.engine.framework.db.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.concurrent.Callable;

/**
 * 抽象缓存服务
 * 
 * @author herton
 */
public abstract class BaseCrudService<K extends Serializable, V extends BaseEntity> implements Persistable<K, V> {

	private AbstractCacheContainer<K, V> container;
    private Class <V> entityClass;
    @Autowired
    private EntityManager em;
    @Autowired
    private DbService dbService;

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void init() {
        container = new DefaultCacheContainer<>(this, CacheOptions.defaultCacheOptions());
        entityClass = (Class <V>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

	/**
	 * 通过key获取对象
	 * 
	 * @param key
	 * @return
	 */
	public V get(K key) {
		return container.get(key);
	}

	public final V getOrCreate(K k, Callable<V> callable) {
		return container.getOrCreate(k, callable);
	}

	/**
	 * 手动移除缓存
	 * 
	 * @param key
	 * @return
	 */
	public void remove(K key) {
		container.remove(key);
	}

	/**
	 * 手动加入缓存
	 * 
	 * @param key
	 * @return
	 */
	public void put(K key, V v) {
        v.setId((Long) key);
		this.container.put(key, v);
	}

    @Override
    @Transactional(readOnly = true)
    public V loadFromDb(K k) {
        return em.find(entityClass, k);
    }

    @Override
    public void saveAndPersist(K key, V v) {
        put(key, v);
        dbService.add2Queue(v);
    }
}
