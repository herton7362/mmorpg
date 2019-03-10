package com.kratos.engine.framework.cache;

/**
 * 可持久化的
 * @author herton
 */
public interface Persistable<K, V> {
	
	/**
	 * 能从数据库获取bean
	 * @param k 查询主键
	 * @return  持久化对象
	 * @throws Exception
	 */
    V loadFromDb(K k) throws Exception;

	/**
	 * 手动加入缓存并存入数据库
	 *
	 * @param key
	 * @return
	 */
	void saveAndPersist(K key, V v);
    
}
