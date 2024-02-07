package repositories;

import java.util.List;

public interface CrudRepository<T> {

    T findByID(int id);

    List<T> findAll();

    void save(T event);

    void deleteByID(int id);
}
