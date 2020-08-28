package duke;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

import exception.*;

public class Duke {
    Scanner readSc, inputSc;
    ArrayList<Task> tasks;
    String input;
    Ui ui;
    Storage storage;
    TaskList tl;
    Parser parser;

    public Duke() {
        inputSc = new Scanner(System.in);
        tasks = new ArrayList<>();
        ui = new Ui();
        tl = new TaskList();
        parser = new Parser();
        storage = new Storage();
    }

    public void start() {
        ui.introduceDuke();
        interact();
        ui.sayFarewell();
    }

    public void interact() {
        try {
            storage.readFile(readSc, tl, ui, tasks, "data/duke.txt");
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
                storage.writeFile(tasks);
                break;
            } else if (command.equals(Commands.LIST)) {
                ui.printList(tasks);
            } else if (command.equals(Commands.DONE)){
                try {
                    this.makeDone(Integer.parseInt(splitted[1]) - 1);
                } catch (ArrayIndexOutOfBoundsException | DukeErrorException ex) {
                    System.out.println(ex);
                }
            } else if (command.equals(Commands.DELETE)){
                try {
                    this.deleteTask(Integer.parseInt(splitted[1]) - 1);
                } catch (ArrayIndexOutOfBoundsException | DukeErrorException ex) {
                    System.out.println(ex);
                }
            } else if (command.equals(Commands.DEADLINE)) {
                try {
                    tl.addDeadline(ui, tasks, splitted[1], true, false);
                } catch (ArrayIndexOutOfBoundsException | InvalidDeadlineException ex) {
                    System.out.println(ex + ". ☹ Task deadline must be specified.");
                }
            } else if (command.equals(Commands.TODO)) {
                try {
                    tl.addTodo(ui, tasks, splitted[1], true, false);
                } catch (ArrayIndexOutOfBoundsException | InvalidTodoException ex) {
                    System.out.println(ex + ". ☹ The description of a todo cannot be empty.");
                }
            } else if (command.equals(Commands.EVENT)) {
                try {
                    tl.addEvent(ui, tasks, splitted[1], true, false);
                } catch (ArrayIndexOutOfBoundsException | InvalidEventException ex) {
                    System.out.println(ex + ". ☹ The description of an event cannot be empty.");
                }
            } else {
                ui.buildChatSeparator();
                System.out.println("added: " + input);
                ui.buildChatSeparator();
                tasks.add(new Task(input, false));
            }
        }
    }

    private void deleteTask(int index) throws DukeErrorException {
        if (index >= tasks.size() || index < 0) {
            throw new DukeErrorException("Operation: delete " + (index + 1) + " fails ☹.");
        }
        Task deleted = tasks.remove(index);
        ui.printDeleted(deleted, tasks);
    }

    public void makeDone(int index) throws DukeErrorException {
        if (index >= tasks.size() || index < 0) {
            throw new DukeErrorException("Operation: done " + (index + 1) + " fails ☹.");
        }
        tasks.set(index, tasks.get(index).completeTask());
        ui.printDone(tasks, index);
    }

}