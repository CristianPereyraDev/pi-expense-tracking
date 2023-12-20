package dao;

import java.util.List;

public interface Dao<T, K> {
    K insert(T t);
    T read(K id);
    boolean update(T t);
    boolean delete(K id);
    List<T> getAll();
}
