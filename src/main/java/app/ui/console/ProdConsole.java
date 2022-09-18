package app.ui.console;

import java.util.Scanner;

public class ProdConsole implements Console{
    Scanner sc = new Scanner(System.in);
    @Override
    public void print(String message) {
        System.out.print(message);
    }

    @Override
    public void println(String message) {
        System.out.println(message);
    }

    @Override
    public String nextLine() {
        return sc.nextLine();
    }
    @Override
    public String next(){return sc.next();}
    @Override
    public void waitForEnter() {
        bigLineSeparator();
        System.out.println("Press enter to continue...");
        sc.nextLine();
    }

    @Override
    public String bigLineSeparator() {
        return "=============================================================\n";
    }

    @Override
    public String lineSeparator() {
        return "------------------------------------------------------\n";
    }
}
