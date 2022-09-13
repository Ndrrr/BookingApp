package app.data.dao;

import app.data.entity.Entity;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Entity> {
    List<T> get();
    void save();
    void load();

    default void add(T t){
        List<T> entityList = get();
        entityList.add(t);
    }
    default boolean update(int id, T t){
        List<T> entityList = get();
        Entity item = entityList.stream()
                .filter(e -> e.getId() == id)
                .map(e -> t).findFirst().orElse(null);
        return item != null;
    }
    default boolean delete(T t){
        List<T> entityList = get();
        return entityList.remove(t);
    }
    default boolean delete(int id){
        List<T> entityList = get();
        if(id < 0 || id >= entityList.size()){
            return false;
        }
        entityList.remove(id);
        return true;

    }
    default Optional<T> get(int id){
        List<T> entityList = get();
        return entityList.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
    }
}
