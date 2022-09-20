package app.data.dao;

import app.data.entity.Entity;
import app.data.entity.Flight;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface Dao<T extends Entity> {
    List<T> get();
    void save();
    void load();

    default void add(T t){
        List<T> entityList = get();
        entityList.add(t);
    }
    default void addAll(List<T> list){
        List<T> entityList = get();
        entityList.addAll(list);
    }
    default boolean update(int id, T t){
        List<T> entityList = get();
        for (int i = 0; i < entityList.size(); i++) {
            T tmp = entityList.get(i);
            if(tmp.getId() == id){
                entityList.set(i, t);
                return true;
            }
        }
        return false;
    }
    default boolean delete(T t){
        List<T> entityList = get();
        return entityList.remove(t);
    }
    default boolean delete(int id){
        Optional<T> optionalEntity = get(id);
        return optionalEntity.filter(this::delete).isPresent();

    }
    default Optional<T> get(int id){
        List<T> entityList = get();
        return entityList.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
    }
}
