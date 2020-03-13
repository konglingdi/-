package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

public class BaseDaoJpa<T> implements BaseDao<T> {

    @PersistenceContext
    protected EntityManager entityManager;
    // 根据ID加载实体
    public T get(Class<T> entityClazz, Serializable id) {
        return (T)entityManager.find(entityClazz,id);
    }
    // 保存实体
    public void save(T entity) {


            entityManager.persist(entity);

    }
    // 更新实体
    public void update(T entity) {
        entityManager.merge(entity);
    }
    // 删除实体
    public void delete(T entity) {
        entityManager.remove(entity);
    }
    // 根据id删除实体
    public void delete(Class<T> entityClazz, Serializable id) {

    }

    public List<T> findAll(Class<T> entityClazz) {
        return null;
    }

    public long findCount(Class<T> entityClazz) {
        return 0;
    }
}
