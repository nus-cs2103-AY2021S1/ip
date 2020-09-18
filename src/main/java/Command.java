import java.util.Scanner;
import java.util.ArrayList;

public class Command {

    public CommandType type;
    public String description;

    public Command(CommandType type, String description) {
        this.type = type;
        this.description = description;
    }

    // Executes Command by manipulating input ArrayList
    public ArrayList<Task> executeCommand(ArrayList<Task> tasks) throws InvalidInputException,
            IndexOutOfBoundsException {

        switch (this.type) {
            case BYE:
                System.out.println("Bye. Hope to see you again soon!");
                return tasks;
            case CLEAR:
                if (Ui.promptConfirm(new Scanner(System.in))) {
                    // Reference to empty ArrayList
                    System.out.println("Task list cleared!");
                    return new ArrayList<Task>();
                } else {
                    // do nothing
                    System.out.println("Did NOT clear your task list! Is there anything else?");
                    return tasks;
                }
            case LIST:
                if (tasks.size() == 0) {
                    // do nothing
                    return new ArrayList<Task>();
                } else {
                    printList(tasks);
                    return tasks;
                }
            case DONE:
                return markDone(tasks, this.description);
            case DELETE:
                return deleteTask(tasks, this.description);
            case TODO:
            case DEADLINE:
            case EVENT:
                return addTask(tasks, this.description);
            case UNKNOWN:
                System.out.println(this.description);
                return tasks;
        }
        return tasks;
    }

    // Marks select Task in ArrayList as done. Throws IndexOutOfBoundsException
    public ArrayList<Task> markDone(ArrayList<Task> tasks, String input) throws
            IndexOutOfBoundsException {

        // parse int for index of task to be marked as done
        int index = Integer.valueOf(input.split(" ")[1]);

        Task current = tasks.get(index - 1);
        current.completeTask();
        System.out.println("Nice! I have marked this task as done:\n\t" + current.printTask());
        return tasks;
    }

    public ArrayList<Task> deleteTask(ArrayList<Task> tasks, String input) throws
            IndexOutOfBoundsException {

        // parse int for index of task to be deleted
        int index = Integer.valueOf(input.split(" ")[1]);

        Task current = tasks.remove(index - 1);
        System.out.println("Okay! I have removed this task:\n\t" + current.printTask());
        System.out.println("Now you have " + tasks.size() + " tasks in your list");
        return tasks;
    }

    // Adds Task to list. Checks inputs and throws exceptions for invalid inputs
    public ArrayList<Task> addTask(ArrayList<Task> tasks, String input) throws
            InvalidInputException {

        StringBuilder sb = new StringBuilder();
        String[] splitSpace = input.split(" ");
        Task task;

        if (this.type.equals(CommandType.TODO)) {
            // case: todo
            for (String str : splitSpace) {
                if (str.toLowerCase().equals("todo")) {
                    continue;
                } else {
                    sb.append(str + " ");
                }
            } // end for loop
            task = new ToDo(sb.toString().trim());
        } else if (this.type.equals(CommandType.DEADLINE)) {
            // case: deadline

            String name = input.split("/by")[0].trim().substring(9);
            String deadline = input.split("/by")[1].trim();
            task = new Deadline(name, deadline);
        } else {
            // case: event
            String name = input.split("/at")[0].trim().substring(6);
            String time = input.split("/at")[1].trim();
            task = new Event(name, time);
        }

        tasks.add(task);
        System.out.println("Got it! Task added to list.");
        System.out.println("\t" + task.printTask());
        System.out.println("Now you have " + tasks.size() + " tasks in your list.");
        return tasks;
    }

    // Prints all Tasks in an ordered list
    public void printList(ArrayList<Task> tasks) {
        System.out.println("Here are your tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i).printTask());
        }
    }

    public CommandType getType() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
    }

}