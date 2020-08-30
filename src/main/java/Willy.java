package main.java;

import java.util.ArrayList;
import java.util.Scanner;
import willy.store.TaskStore;
import willy.ui.Greet;
import willy.exceptions.WillyException;
import willy.task.Task;
import willy.command.Parser;

/**
 * A bot that records tasks for people and keeps track of it for them.
 */
public class Willy {

    private static TaskStore storage;
    static String style = "\t________________________________________________________________\n";
    static String logo = "__       ____       __\n"
            + "\\  \\    /    \\    /  /\n"
            + " \\  \\  /  /\\  \\  /  /\n"
            + "  \\  \\/  /  \\  \\/  /\n"
            + "   \\____/    \\____/ ILLY ~(^-^)~\n";

    public Willy() {
        System.out.println(logo + "    Your personal life secretary");
        Greet startDuke = new Greet();
        // prints out intro
        System.out.println(startDuke);
        storage = new TaskStore();
        storage.createFile();
    }


    public static void main(String[] args) throws WillyException {

        new Willy();
        Scanner input = new Scanner(System.in);
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
