import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Duke {

    public static void printTaskList(List<Task> taskList) {
        if (taskList.size() == 0) {
            System.out.println("You have no tasks!");
        } else {
            int taskNumber = 1;
            while (taskNumber < taskList.size() + 1) {
                Task task = taskList.get(taskNumber - 1);
                System.out.println(taskNumber + "." + task.toString());
                taskNumber++;
            }
        }
    }

    public static void markTaskAsDone(List<Task> taskList, String command) throws DukeException {
        // If task number is not given
        if (command.equals("done") || command.equals("done ")) {
            throw new DukeException("OOPS!!! Please indicate the number of the task you have completed.");
        } else {
            try {
                int taskNumber = Integer.parseInt(command.split(" ", 2)[1]);
                Task task = taskList.get(taskNumber - 1);
                task.markAsDone();
                taskList.set(taskNumber - 1, task);
                System.out.println("Nice! I've marked this task as done:\n" + "  " + task.toString());
            } catch (IndexOutOfBoundsException indexException) {
                throw new DukeException("OOPS!!! Please enter a valid task number.");
            }
        }
    }

    public static void addTaskToList(List<Task> taskList, String command) throws DukeException {
        Task task;
        String taskType = command.split(" ", 2)[0];
        String taskBody;

        // If there is no taskBody available
        if (command.split(" ", 2).length == 1
                || command.split(" ", 2)[1].equals("")) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        } else {
            taskBody = command.split(" ", 2)[1];
        }

        // Generate task with appropriate type and add to taskList
        if (taskType.equals("todo")) {
            task = new Todo(taskBody);
        } else {
            if (taskType.equals("deadline")) {
                try {
                    String description = taskBody.split(" /by ")[0];
                    String by = taskBody.split(" /by ")[1];
                    task = new Deadline(description, by);
                } catch (ArrayIndexOutOfBoundsException outOfBoundsException) {
                    throw new DukeException("OOPS!!! Please add a deadline for your task.");
                }
            } else { // Events
                try {
                    String description = taskBody.split(" /at ")[0];
                    String at = taskBody.split(" /at ")[1];
                    task = new Event(description, at);
                } catch (ArrayIndexOutOfBoundsException outOfBoundsException) {
                    throw new DukeException("OOPS!!! Please add a date for your event.");
                }
            }
        }
        taskList.add(task);
        System.out.println("Got it. I've added this task:\n"
                + "  " + task.toString() + "\n"
                + "Now you have " + taskList.size() + " tasks in the list");
    }

    public static void main(String[] args) {
        // Print introduction
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");

        // Initialize empty task list
        List<Task> taskList = new ArrayList<>();

        // Receive commands
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            command = scanner.nextLine();
            String firstWordOfCommand = command.split(" ", 2)[0];

            try {
                if (firstWordOfCommand.equals("list")) {
                    printTaskList(taskList);

                } else if (firstWordOfCommand.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;

                // Mark a task as done
                } else if (firstWordOfCommand.equals("done")) {
                    markTaskAsDone(taskList, command);

                // Add task to task list
                } else if (firstWordOfCommand.equals("todo")
                        || firstWordOfCommand.equals("deadline")
                        || firstWordOfCommand.equals("event")) {
                    addTaskToList(taskList, command);

                // Unrecognizable commands
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

            } catch (DukeException dukeException) {
                System.out.println(dukeException.getMessage());
            }
        }
    }

}
