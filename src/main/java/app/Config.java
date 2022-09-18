package app;

import java.io.*;

public class Config implements Serializable{
    private static Config instance;
    private static String path = "config.bin";

    // for saving last index of entities
    private int lastFlightID;
    private int lastBookingID;
    private int lastUserID;

    private Config(){}
    public static Config getInstance(){
        if(instance == null){
            instance = load();
        }
        return instance;
    }

    public int getLastFlightID() {
        return lastFlightID;
    }

    public void setLastFlightID(int lastFlightID) {
        this.lastFlightID = lastFlightID;
    }

    public int getLastBookingID() {
        return lastBookingID;
    }

    public void setLastBookingID(int lastBookingID) {
        this.lastBookingID = lastBookingID;
    }

    public int getLastUserID() {
        return lastUserID;
    }

    public void setLastUserID(int lastUserID) {
        this.lastUserID = lastUserID;
    }
    public static void save(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(getInstance());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Config load(){

        File file = new File(path);
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = reader.readObject();
            if (obj instanceof Config) {
                 return (Config) obj;
            }
        }catch (FileNotFoundException e){
            // in first run files may not exist
            // they will be created after proper exit
            // therefore exception is ignored
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Config();
    }
}
