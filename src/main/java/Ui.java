package main.java;

import java.util.Scanner;

public class Ui {

    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private String line = "________________________________";

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void printWelcome() {
        System.out.println("Hello from");
        System.out.println(logo);
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public void printLine() {
        System.out.println(line);
    }

    public String readLine() {
        return sc.nextLine();
    }
}
