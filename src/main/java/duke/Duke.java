package duke;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

import exception.*;

/**
 * Main class for the Duke application
 */
public class Duke {
    // Scanner to read input
    Scanner readSc, inputSc;
    // List of tasks
    ArrayList<Task> list;
    // Ui object to print out displays
    Ui ui;
    // Storage object to handle saving to file
    Storage storage;
    // TaskList object to handle insertion, deletion, etc of tasks
    TaskList tl;
    // Parser object to process inputs and commands
    Parser parser;

    /**
     * A constructor to initialize Duke Chatbot
     */
    public Duke() {
        inputSc = new Scanner(System.in);
        list = new ArrayList<>();
        ui = new Ui();
        tl = new TaskList();
        parser = new Parser();
        storage = new Storage();
    }

    /**
     * Main method to start the application
     */
    public void start() {
        ui.introduce();
        interact();
        ui.bye();
    }

    /**
     * A method to handle the logic of Duke Chatbot and interact with users
     */
    public void interact() {
        String input;
        try {
            storage.readFile(readSc, tl, ui, list, "data/duke.txt");
        } catch (FileNotFoundException | NullPointerException e) {
            System.out.println("Folder data not found! " + e);
        }

        while (inputSc.hasNextLine()) {
            input = inputSc.nextLine();
            String[] splitted = input.split(" ", 2);
            Commands command;
            try {
                command = parser.processCommand(splitted);
            } catch (DukeErrorException ex) {
                System.out.println(ex);
                continue;
            }

            if (command.equals(Commands.BYE)) {
                storage.writeFile(list);
                break;
            } else if (command.equals(Commands.LIST)) {
                ui.printList(list);
            } else if (command.equals(Commands.DONE)){
                try {
                    tl.makeDone(list,Integer.parseInt(splitted[1]) - 1);
                } catch (ArrayIndexOutOfBoundsException | DukeErrorException ex) {
                    System.out.println(ex);
                }
            } else if (command.equals(Commands.DELETE)){
                try {
                    tl.deleteTask(list,Integer.parseInt(splitted[1]) - 1);
                } catch (ArrayIndexOutOfBoundsException | DukeErrorException ex) {
                    System.out.println(ex);
                }
            } else if (command.equals(Commands.DEADLINE)) {
                try {
                    tl.addDeadline(ui, list, splitted[1], true, false);
                } catch (ArrayIndexOutOfBoundsException | InvalidDeadlineException ex) {
                    System.out.println(ex + ". ☹ Task deadline must be specified.");
                }
            } else if (command.equals(Commands.TODO)) {
                try {
                    tl.addTodo(ui, list, splitted[1], true, false);
                } catch (ArrayIndexOutOfBoundsException | InvalidTodoException ex) {
                    System.out.println(ex + ". ☹ The description of a todo cannot be empty.");
                }
            } else if (command.equals(Commands.EVENT)) {
                try {
                    tl.addEvent(ui, list, splitted[1], true, false);
                } catch (ArrayIndexOutOfBoundsException | InvalidEventException ex) {
                    System.out.println(ex + ". ☹ The description of an event cannot be empty.");
                }
            }
        }
    }

}