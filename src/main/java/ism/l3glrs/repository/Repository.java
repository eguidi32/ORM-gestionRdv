package ism.l3glrs.repository;

import java.util.List;

public interface Repository<T, ID> {
    ID insert(T entity);
    T findById(ID id);
    List<T> selectAll();
    T update(T entity);
    void delete(ID id);
}
