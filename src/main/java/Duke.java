import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>(); // List of all input items from user

    public static void main(String[] args) throws DukeException {
        String logo = "Duke";
        System.out.println("Hello from " + logo + "\n" + "How may I be of service " +
                "to you this fine day sire?");

        try {
            awaitInstructions();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            awaitInstructions();
        }
    }

    // Gets the command from the user
    private static String parseCommand(String userInput) {
        String[] splitString = userInput.split(" ");
        return splitString[0];
    }

    private static void awaitInstructions() throws DukeException {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            try {
                String userInput = sc.nextLine();
                String userCommand = parseCommand(userInput);
                Commands command;
                try {
                    command = Commands.valueOf(userCommand.toUpperCase());
                } catch (IllegalArgumentException e) {
                   command = Commands.UNKNOWN;
                }

                switch (command) { // Determine output from user input
                    case BYE:
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    case LIST:
                        listAllItems();
                        break;
                    case TODO:
                        addToDoTask(userInput);
                        break;
                    case EVENT:
                        addEventTask(userInput);
                        break;
                    case DEADLINE:
                        addDeadlineTask(userInput);
                        break;
                    case DONE:
                        markTaskAsDone(userInput);
                        break;
                    case DELETE:
                        deleteTask(userInput);
                        break;
                    default:
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                awaitInstructions();
            }
        }
    }

    private static boolean emptyToDoDescription(String userInput) {
        return userInput.length() <= 4;
    }

    private static void deleteTask(String userInput) throws DukeException {
        try {
            String taskIndex = userInput.substring(7);
            int index = Integer.valueOf(taskIndex) - 1; // taskIndex started from 1
            Task completedTask = Duke.taskList.remove(index);

            System.out.println("Noted. I've removed this task:\n" +
                    completedTask.toString() + "\nNow you have " + (Duke.taskList.size())
                    + (Duke.taskList.size() > 1 ? " tasks" : " task")
                    + " in the list.");

        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Please enter a valid task number for me delete.");
        }

    }

    private static void addToDoTask(String userInput) throws DukeException {
        if (emptyToDoDescription(userInput)) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        String taskDescription = userInput.substring(5);
        ToDo newToDoItem = new ToDo(taskDescription);
        addItem(newToDoItem); // Add to taskList
    }

    private static void addEventTask(String userInput) throws DukeException {
        String taskDescription = userInput.substring(6, userInput.indexOf("/at") - 1);
        String eventDateTime = userInput.substring(userInput.indexOf("/at") + 4);
        Event newEventItem  = new Event(taskDescription, eventDateTime);
        addItem(newEventItem);
    }

    private static void addDeadlineTask(String userInput) throws DukeException {
        String taskDescription = userInput.substring(9, userInput.indexOf("/by") - 1);
        String deadlineBy = userInput.substring(userInput.indexOf("/by") + 4);
        Deadline newDeadlineItem = new Deadline(taskDescription, deadlineBy);
        addItem(newDeadlineItem);
    }

    // Updates taskList attribute to indicate task as done
    public static void markTaskAsDone(String userInput) throws DukeException {
        try {
            String taskIndex = userInput.substring(5);
            int index = Integer.valueOf(taskIndex) - 1; // taskIndex started from 1
            Task completedTask = Duke.taskList.get(index);
            completedTask.markAsDone();

            System.out.println("Nice! I've marked this task as done:\n" +
                    completedTask.toString());

        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Please enter a valid task number for me to mark as done.");
        }
    }

    // Adds a task item to Duke's taskList, which may be an Event, ToDoItem, Deadline
    public static void addItem(Task newTask) {
        Duke.taskList.add(newTask);
        System.out.println("Got it. I've added this task:\n   " +
                newTask.toString() + "\nNow you have " + (Duke.taskList.size())
                + (Duke.taskList.size() > 1 ? " tasks" : " task")
                + " in the list.");
    }

    private static void listAllItems() {
        System.out.println("Here are the tasks in your list:\n");
        ArrayList<Task> currList = Duke.taskList;
        currList.forEach(item ->
                System.out.println((currList.indexOf(item) + 1) + "." + item));
    }

    private static void echo(String userInput) {
        System.out.println(userInput);
    }
}
