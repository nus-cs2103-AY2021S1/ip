package main.java;
import actions.Greet;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    ArrayList<String> list = new ArrayList<>();
    String message;
    static String lastGreeting = "bye";

    void addToList(String task) {
        list.add(task);
        System.out.println("added: " + task);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner input = new Scanner(System.in);
        Greet startDuke = new Greet();
        // prints out intro
        System.out.println(startDuke);
        while (input.hasNext()) {
            String message = input.nextLine();
            Greet newMessage = new Greet(message);
            if (newMessage.getGreeting().equals(lastGreeting)) {
                // prints out exit
                System.out.println(newMessage);
                break;
            }
            // Echo
            System.out.println(newMessage);
        }
        input.close();
    }
}
