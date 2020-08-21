package main.java;

import java.util.Scanner;

public class Ui {

    public void hello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    public void goodbyeMessage() {
        System.out.println("GoodBye, Hope to see you back soon.");
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println("*****************************************************************");
    }
    public void showSpace() {
        System.out.println();
    }

    public void showError(Exception e) {
        System.out.print(e);
    }
}
