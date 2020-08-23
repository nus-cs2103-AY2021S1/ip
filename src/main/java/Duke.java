import java.util.Scanner;
import java.util.ArrayList;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    private static final Path storageFilePath = Paths.get(".", "data", "test.txt");
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    Duke() {
        this.ui = new Ui();
        this.storage = new Storage(Duke.storageFilePath);
        try {
            this.taskList = new TaskList(this.storage.getAllTasks());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            taskList = new TaskList();
        }
    }

    public static void main(String[] args){
        new Duke().run();
    }

    private void run() {
        this.ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = this.ui.readCommand();
                ui.showLine();

                Commands command = Parser.parse(userInput);

                switch (command) { // Determine output from user input
                    case BYE:
                        ui.print("Bye. Hope to see you again soon!");
                        isExit = true;
                        break;
                    case LIST:
                        this.listAllItems();
                        break;
                    case TODO:
                        this.addToDoTask(userInput);
                        break;
                    case EVENT:
                        this.addEventTask(userInput);
                        break;
                    case DEADLINE:
                        this.addDeadlineTask(userInput);
                        break;
                    case DONE:
                        this.markTaskAsDone(userInput);
                        break;
                    case DELETE:
                        this.deleteTask(userInput);
                        break;
                    default:
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }

            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    private static boolean emptyToDoDescription(String userInput) {
        return userInput.length() <= 4;
    }

    private void deleteTask(String userInput) throws DukeException {
        try {
            String taskIndex = userInput.substring(7);
            int index = Integer.valueOf(taskIndex) - 1; // taskIndex started from 1
            Task deletedTask = this.taskList.delete(index);
            int listSize = this.taskList.size();

            this.storage.deleteTask(index);

            ui.print("Noted. I've removed this task:\n" +
                    deletedTask.toString() + "\nNow you have " + (listSize)
                    + (listSize > 1 ? " tasks" : " task")
                    + " in the list.");

        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Please enter a valid task number for me delete.");
        }

    }

    private void addToDoTask(String userInput) throws DukeException {
        if (emptyToDoDescription(userInput)) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        String taskDescription = userInput.substring(5);
        ToDo newToDoItem = new ToDo(taskDescription);
        addItem(newToDoItem); // Add to taskList
    }

    private void addEventTask(String userInput) throws DukeException {
        String taskDescription = userInput.substring(6, userInput.indexOf("/at") - 1);
        String eventDateTime = userInput.substring(userInput.indexOf("/at") + 4);
        Event newEventItem  = new Event(taskDescription, eventDateTime);
        addItem(newEventItem);
    }

    private void addDeadlineTask(String userInput) throws DukeException {
        String taskDescription = userInput.substring(9, userInput.indexOf("/by") - 1);
        String deadlineBy = userInput.substring(userInput.indexOf("/by") + 4);
        Deadline newDeadlineItem = new Deadline(taskDescription, deadlineBy);
        addItem(newDeadlineItem);
    }

    // Updates taskList attribute to indicate task as done
    public void markTaskAsDone(String userInput) throws DukeException {
        try {
            String taskIndex = userInput.substring(5);
            int index = Integer.valueOf(taskIndex) - 1; // taskIndex started from 1
            Task completedTask = this.taskList.get(index);
            completedTask.markAsDone();
            this.storage.updateTask(completedTask,index);
            ui.print("Nice! I've marked this task as done:\n" +
                    completedTask.toString());


        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Please enter a valid task number for me to mark as done.");
        }
    }

    // Adds a task item to Duke's taskList, which may be an Event, ToDoItem, Deadline
    public void addItem(Task newTask) {
        this.taskList.add(newTask);
        this.storage.createTask(newTask); // Add to storage database
        int listSize = this.taskList.size();
        ui.print("Got it. I've added this task:\n   " +
                newTask.toString() + "\nNow you have " + (listSize)
                + (listSize > 1 ? " tasks" : " task")
                + " in the list.");

    }

    private void listAllItems() {
        ui.print("Here are the tasks in your list:\n");
        this.taskList.showAllItems();
    }
}
