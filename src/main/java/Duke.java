import java.util.Scanner;
import java.util.ArrayList;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    private static final Path storageFilePath = Paths.get(".", "data", "test.txt");
    private ArrayList<Task> taskList;
    private Storage storage;

    Duke() {
        System.out.println("Hello from Duke\nHow may I be of service " +
                "to you this fine day sire?");
        this.storage = new Storage(Duke.storageFilePath);
        this.taskList = this.storage.getAllTasks();
    }

    public static void main(String[] args) throws DukeException {
        Duke duke = new Duke();
        duke.awaitInstructions();
    }

    // Gets the command from the user
    private static String parseCommand(String userInput) {
        String[] splitString = userInput.split(" ");
        return splitString[0];
    }

    private void writeToFile() throws DukeException {
    }

    private void awaitInstructions() throws DukeException {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            try {
                String userInput = sc.nextLine();

                if (userInput.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                }

                String userCommand = parseCommand(userInput);
                Commands command;
                try {
                    command = Commands.valueOf(userCommand.toUpperCase());
                } catch (IllegalArgumentException e) {
                    command = Commands.UNKNOWN;
                }

                switch (command) { // Determine output from user input
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
                System.out.println(e.getMessage());
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
            Task deletedTask = this.taskList.remove(index);
            this.storage.deleteTask(index);

            System.out.println("Noted. I've removed this task:\n" +
                    deletedTask.toString() + "\nNow you have " + (this.taskList.size())
                    + (this.taskList.size() > 1 ? " tasks" : " task")
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

            System.out.println("Nice! I've marked this task as done:\n" +
                    completedTask.toString());

        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Please enter a valid task number for me to mark as done.");
        }
    }

    // Adds a task item to Duke's taskList, which may be an Event, ToDoItem, Deadline
    public void addItem(Task newTask) {
        this.taskList.add(newTask);
        this.storage.createTask(newTask); // Add to storage database

        System.out.println("Got it. I've added this task:\n   " +
                newTask.toString() + "\nNow you have " + (this.taskList.size())
                + (this.taskList.size() > 1 ? " tasks" : " task")
                + " in the list.");
    }

    private void listAllItems() {
        System.out.println("Here are the tasks in your list:\n");
        ArrayList<Task> currList = this.taskList;
        currList.forEach(item ->
                System.out.println((currList.indexOf(item) + 1) + "." + item));
    }
}
