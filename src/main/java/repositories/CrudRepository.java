package repositories;

import java.util.List;

public interface CrudRepository<T> {
    List<T> findAll();
    void save(T model);
    T find(int id);
}
