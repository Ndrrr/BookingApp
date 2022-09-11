package dao;

import entity.Entity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Db {
    public static void save(List<? extends Entity> entities, String path){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(entities);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Optional<List<Entity>> load(String path){
        File file = new File(path);
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = reader.readObject();
            if (obj instanceof List
                    && ((List) obj).stream().allMatch(o -> o instanceof Entity)) {

                return Optional.of(new ArrayList<>((List<Entity>) obj));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
