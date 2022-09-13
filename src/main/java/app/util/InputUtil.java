package app.util;

import app.Context;
import app.ui.console.Console;
import app.ui.console.ProdConsole;

import java.util.Scanner;
import java.util.function.Predicate;

public class InputUtil {
    static Console console = new ProdConsole();
    public static boolean checkInt(String tmp){
        if(tmp == null) return false;
        try{
            Integer.parseInt(tmp);
        }catch (NumberFormatException nfe){
            return false;
        }
        return true;
    }
    public static int getCorrectInt(){
        String tmp = console.next();
        if(checkInt(tmp)){
            console.nextLine();
            return Integer.parseInt(tmp);
        }
        System.out.print("Please enter a valid number: ");
        return getCorrectInt();
    }
    public static int getCorrectInt(Predicate<Integer> predicate, String errorMessage){
        String tmp = console.next();
        if(checkInt(tmp)){
            int parsed = Integer.parseInt(tmp);
            if(predicate.test(parsed)) {
                console.nextLine();
                return Integer.parseInt(tmp);
            }
            else{
                System.out.println(errorMessage);
            }
        }
        System.out.print("Please enter a valid number: ");
        return getCorrectInt(predicate, errorMessage);
    }
}
