package app.data;

import app.data.entity.Entity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Db {
    private static Db instance;
    private Db(){}
    public static Db getInstance(){
        if(instance == null){
            instance = new Db();
        }
        return instance;
    }

    public void save(List<? extends Entity> entities, String path){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(entities);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Optional<List<Entity>> load(String path){
        File file = new File(path);
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = reader.readObject();
            if (obj instanceof List
                    && ((List) obj).stream().allMatch(o -> o instanceof Entity)) {

                return Optional.of(new ArrayList<>((List<Entity>) obj));
            }
        }catch (FileNotFoundException e){
            // in first run files may not exist
            // they will be created after proper exit
            // therefore exception is ignored
        }catch (Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
