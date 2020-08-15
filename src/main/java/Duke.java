import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>(); // List of all input items from user

    public static void main(String[] args) {
        String logo = "Hans ㋡";
                /*"_   _        _        \n"
                   + "| |_| | ___  _| | _____ \n"
                   + "|  ㋡ | | | | | |/ / _ \\\n"
                   + "| |_| | |_| |   <  __/\n"
                   + "|_| |_|/ \\_|,_|_|\\_\\___|\n"; */
        System.out.println("Hello from " + logo + "\n" + "How may I be of service " +
                "to you this fine day sire?");

        awaitInstructions();
    }

    private static void awaitInstructions() {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String userInput =  sc.nextLine();

            switch (userInput) { // Determine output from user input
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case "list":
                    listAllItems();
                    break;
                default:
                    if (userMarkAsDone(userInput)) {
                        String taskIndex = userInput.substring(5);
                        markTaskAsDone(taskIndex);

                    } else if (userAddingToDo(userInput)) {
                        addToDoTask(userInput);

                    } else if (userAddingEvent(userInput)) {
                        addEventTask(userInput);

                    } else if (userAddingDeadline(userInput)) {
                        addDeadlineTask(userInput);

                    } else {
                        System.out.println("I don't know what you want me to do");
                    }
                    break;
            }
        }
    }
    private static void addToDoTask(String userInput) {
        String taskDescription = userInput.substring(5);
        ToDo newToDoItem = new ToDo(taskDescription);
        addItem(newToDoItem); // Add to taskList
    }

    private static void addEventTask(String userInput) {

    }

    private static void addDeadlineTask(String userInput) {

    }

    private static boolean userAddingToDo(String userInput) {
        return userInput.substring(0, 4).equals("todo");
    }

    private static boolean userAddingEvent(String userInput) {
        return userInput.substring(0, 5).equals("event");
    }


    private static boolean userAddingDeadline(String userInput) {
        return userInput.substring(0, 8).equals("deadline");
    }

    private static boolean userMarkAsDone(String userInput) {
        return userInput.substring(0, 4).equals("done");
    }

    // Updates taskList attribute to indicate task as done
    public static void markTaskAsDone(String userInput) {
        String taskIndex = userInput.substring(5);
        int index = Integer.valueOf(taskIndex) - 1; // taskIndex started from 1
        Task completedTask = Duke.taskList.get(index);
        completedTask.markAsDone();

        System.out.println("Nice! I've marked this task as done: \n" +
                completedTask.toString());

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
                System.out.println((currList.indexOf(item) + 1) + ". " + item));
    }

    private static void echo(String userInput) {
        System.out.println(userInput);
    }
}
