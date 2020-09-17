package willy.ui;

import java.util.ArrayList;
import java.util.Scanner;

import willy.store.TaskStore;
import willy.task.TaskList;
import willy.exceptions.WillyException;
import willy.task.Task;
import willy.command.Parser;

/**
 * A bot that records tasks for people and keeps track of it for them.
 */
public class Willy {

    public static TaskStore storage;
    private static String lastGreeting = "bye";
    private static String logo = "__       ____       __\n"
            + "\\  \\    /    \\    /  /\n"
            + " \\  \\  /  /\\  \\  /  /\n"
            + "  \\  \\/  /  \\  \\/  /\n"
            + "   \\____/     \\___/ ILLY ~(^-^)~\n"
            + "\tYour personal life secretary\n";
    private static boolean isOnJavaFX;

    public Willy() {
        this.isOnJavaFX = false;
        System.out.println(logo);
        Greet startDuke = new Greet();
        System.out.println(startDuke); // prints out intro
        storage = new TaskStore();
        storage.createFile();
    }

    public Willy(boolean boo) {
        this.isOnJavaFX = boo;
        storage = new TaskStore();
        storage.createFile();
    }

    public static String getLastGreeting() {
        return lastGreeting;
    }

    public static String response(String message) {
        return "\n\t" + message + "\n" ;
    }

    public static void main(String[] args) throws WillyException {

        new Willy();
        Scanner input = new Scanner(System.in);
        ArrayList<Task> listOfTask  = storage.retrieveStorage();
        TaskList list = new TaskList(listOfTask, storage);
        Parser parser = new Parser(list);

        while (input.hasNext()) {

            String message = input.nextLine();

            // check when to end the bot
            if (message.equals(lastGreeting)) {
                Greet endGreeting = new Greet(message);
                System.out.println(endGreeting);
                break;
            }

            parser.parse(message, false);
        }
        input.close();
    }
}
