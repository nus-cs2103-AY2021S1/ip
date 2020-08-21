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
            throw new DukeException("☹ OOPS!!! Please indicate the number of the task you have completed.");
        } else {
            try {
                int taskNumber = Integer.parseInt(command.split(" ", 2)[1]);
                Task completedTask = taskList.get(taskNumber - 1);
                completedTask.markAsDone();
                taskList.set(taskNumber - 1, completedTask);
                System.out.println("Nice! I've marked this task as done:\n" + "  " + completedTask.toString());
            } catch (IndexOutOfBoundsException | NumberFormatException indexException) {
                throw new DukeException("☹ OOPS!!! Please enter a valid task number.");
            }
        }
    }

    public static void addTaskToList(List<Task> taskList, String command) throws DukeException {
        Task task;
        String taskType = command.split(" ", 2)[0];
        String taskBody;

        // If there is no taskBody available
        if (command.split(" ", 2).length == 1
                || command.split(" ", 2)[1].isBlank()) {
            throw new DukeException("☹ OOPS!!! You didn't provide any task details.");
        } else {
            taskBody = command.split(" ", 2)[1];
        }

        // Generate task with appropriate type and add to taskList
        if (taskType.equals("todo")) {
            task = new Todo(taskBody);

        } else if (taskType.equals("deadline")) {
            if (taskBody.split("/by").length == 0) {
                throw new DukeException("☹ OOPS!!! Please add a description and deadline for your task!");
            } else if (taskBody.split("/by").length == 1
                    || taskBody.split("/by")[1].isBlank()) {
                throw new DukeException("☹ OOPS!!! Please add a deadline for your task!");
            } else if (taskBody.split("/by")[0].isBlank()) {
                throw new DukeException("☹ OOPS!!! Please add a task description!");
            } else {
                String description = taskBody.split("/by")[0];
                String by = taskBody.split("/by")[1];
                task = new Deadline(description.trim(), by.trim());
            }

        } else {
            if (taskBody.split("/at").length == 0) {
                throw new DukeException("☹ OOPS!!! Please add a description and date for your event!");
            } else if (taskBody.split("/at").length == 1
                    || taskBody.split("/at")[1].isBlank()) {
                throw new DukeException("☹ OOPS!!! Please add a date for your event!");
            } else if (taskBody.split("/at")[0].isBlank()) {
                throw new DukeException("☹ OOPS!!! Please add an event description!");
            } else {
                String description = taskBody.split("/at")[0];
                String by = taskBody.split("/at")[1];
                task = new Deadline(description.trim(), by.trim());
            }
        }

        taskList.add(task);
        System.out.println("Got it. I've added this task:\n"
                + "  " + task.toString() + "\n"
                + "Now you have " + taskList.size() + " tasks in the list");
    }

    public static void deleteTaskFromList(List<Task> taskList, String command) throws DukeException {
        // If task number is not given
        if (command == "delete" || command == "delete ") {
            throw new DukeException("☹ OOPS!!! Please indicate the number of the task you want to delete.");
        } else {
            try {
                int taskNumber = Integer.parseInt(command.split(" ", 2)[1]);
                Task removedTask = taskList.remove(taskNumber - 1);
                System.out.println("Noted. I've removed this task:\n" + "  " + removedTask.toString() + "\n"
                        + "Now you have " + taskList.size() + " tasks in the list.");
            } catch (IndexOutOfBoundsException | NumberFormatException exception) {
                throw new DukeException("☹ OOPS!!! Please enter a valid task number.");
            }
        }
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

                // Delete task from task list
                } else if (firstWordOfCommand.equals("delete")) {
                    deleteTaskFromList(taskList, command);

                // Unrecognizable commands
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

            } catch (DukeException dukeException) {
                System.out.println(dukeException.getMessage());
            }
        }
    }

}
