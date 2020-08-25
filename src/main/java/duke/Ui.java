package duke;

import duke.command.Command;
import duke.task.Task;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Ui {
    protected TaskListHandler handler;
    protected Storage storage;
    protected static boolean isRunning = true;


    public Ui(TaskListHandler handler, Storage storage) {
        this.handler = handler;
        this.storage = storage;
    }

    public static void stopRunning() {
        isRunning = false;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            // Listens for input
            try {
                String input = scanner.nextLine();
                Command c = Parser.parse(input, handler);
                c.execute(handler, storage);
                System.out.println();
            }  catch (NoSuchElementException e1) {
                // Encounter end of file, terminate
                isRunning = false;
            } catch (DukeException e) {
                e.printStackTrace(System.out);
                DukeException.tryAgain();
            }
        }
    }

    public static void indent(int times) {
        for (int i=0; i<times; i++) {
            System.out.print("    ");
        }
    }

    public static void printSuccess(String operation, Task currentTask, int listSize) {
        // Prints success message and list size after task added/deleted
        indent(1);
        if (operation.equals("add")) {
            System.out.print("Successfully added:\n");
        } else if (operation.equals("delete")) {
            System.out.print("Noted. I've removed this task:\n");
        } else {
            System.out.println("Good job! You completed:");
            indent(2);
            System.out.println(currentTask);
            return;
        }
        indent(2);
        System.out.println(currentTask);
        indent(1);
        System.out.println("You have " + listSize + " task(s) in the list.");
    }

    public static void greet() {
        String logo = "\n"
                + "██████  ██    ██ ██   ██ ███████\n"
                + "██   ██ ██    ██ ██  ██  ██\n"
                + "██   ██ ██    ██ █████   █████\n"
                + "██   ██ ██    ██ ██  ██  ██\n"
                + "██████   ██████  ██   ██ ███████\n";
//        String logoSol = "\n" +
//                "   ▄████████  ▄██████▄   ▄█       \n" +
//                "  ███    ███ ███    ███ ███       \n" +
//                "  ███    █▀  ███    ███ ███       \n" +
//                "  ███        ███    ███ ███       \n" +
//                "  ██████████ ███    ███ ███       \n" +
//                "         ███ ███    ███ ███       \n" +
//                "   ▄█    ███ ███    ███ ███▌      ▄ \n" +
//                " ▄████████▀   ▀██████▀  █████▄▄▄███ \n";


        System.out.println(logo);
        String greeting = "Hello! I'm Duke the chatbot! \n" +
                "What can I do for you?\n";
        System.out.println(greeting);
    }

}
