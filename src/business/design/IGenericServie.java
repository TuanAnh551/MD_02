package business.design;

import java.util.List;

public interface IGenericServie <T,E>{
    List<T> findAll();
    T findById(E id);
    void save(T t);
    void deleteById(E id);
}
