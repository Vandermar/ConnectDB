package user.dao;

import java.util.List;
import java.util.Map;

public interface Repository<T,K>
{

    List<T> findAllBy(Map<String,Object> args) ;

    T saveOrUpdate(T entity);

    T getByKey(K key) ;

    T findBy(Map<String,Object> args);

    boolean delete(K key);

}
