package main.java;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A bot that records tasks for people and keeps track of it for them.
 */
public class Willy {

    private static TaskStore storage;
    private String message;
    static String style = "\t________________________________________________________________\n";
    static String logo = "__       ____       __\n"
            + "\\  \\    /    \\    /  /\n"
            + " \\  \\  /  /\\  \\  /  /\n"
            + "  \\  \\/  /  \\  \\/  /\n"
            + "   \\____/    \\____/ ILLY ~(^-^)~\n";


    public static void main(String[] args) throws WillyException, FileNotFoundException {
        System.out.println(logo + "    Your personal life secretary");

        Scanner input = new Scanner(System.in);
        Greet startDuke = new Greet();
        // prints out intro
        System.out.println(startDuke);
        storage = new TaskStore();
        storage.createFile();
        ArrayList<Task> listOfTask  = storage.retrieveStorage();
        TaskList list = new TaskList(listOfTask, storage);
        Parser parser = new Parser(list);

        while (input.hasNext()) {
            String message = input.nextLine();
            parser.parse(message);
        }
        input.close();
    }
}
