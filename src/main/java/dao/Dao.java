package dao;

import entity.Entity;

import java.util.List;

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
        if(entityList.contains(t)){
            return entityList.remove(t);
        } return false;
    }
    default boolean delete(int id){
        List<T> entityList = get();
        Entity item = entityList.stream()
                .filter(e -> e.getId() == id)
                .findFirst().orElse(null);
        if(item != null){
            return entityList.remove(item);
        } return false;
    }
    default T get(int id){
        List<T> entityList = get();
        return entityList.stream()
                .filter(e -> e.getId() == id)
                .findFirst().orElse(null);
    }
}
