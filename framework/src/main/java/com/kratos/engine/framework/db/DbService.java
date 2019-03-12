package com.kratos.engine.framework.db;

import com.kratos.engine.framework.common.thread.NamedThreadFactory;
import com.kratos.engine.framework.common.utils.BlockingUniqueQueue;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 用户数据异步持久化的服务
 * 
 * @author herton
 */
@Log4j
@Component
public class DbService {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

	public DbService() {
        new NamedThreadFactory("db-save-service").newThread(new Worker()).start();
	}

	private BlockingQueue<BaseEntity> queue = new BlockingUniqueQueue<>();

	private final AtomicBoolean run = new AtomicBoolean(true);

	public void add2Queue(BaseEntity entity) {
		this.queue.add(entity);
	}

	private class Worker implements Runnable {
		@Override
		public void run() {
			while (run.get()) {
				BaseEntity entity = null;
				try {
					entity = queue.take();
					saveToDb(entity);
				} catch (Exception e) {
					log.error("", e);
					// 有可能是并发抛错，重新放入队列
					add2Queue(entity);
				}
			}
		}
	}

	/**
	 * 数据真正持久化
	 * 
	 * @param entity
	 */
	private void saveToDb(BaseEntity entity) {
		entity.doBeforeSave();
        entity.autoSetStatus();
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            if(entity.isInsert() || entity.isUpdate()) {
				em.persist(entity);
			} else if(entity.isDelete()) {
            	em.remove(entity);
			}
            transaction.commit();
            entity.resetDbStatus();
        } catch (Exception e) {
            log.error("", e);
        } finally {
            em.close();
        }
	}

}
