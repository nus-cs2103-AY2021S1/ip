import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Duke {

    public static String getTaskListString(List<Task> taskList) {
        if (taskList.size() == 0) {
            return "You have no tasks!";
        } else {
            String taskListString = "";
            int taskNumber = 1;
            while (taskNumber < taskList.size() + 1) {
                Task task = taskList.get(taskNumber - 1);
                taskListString += taskNumber + "." + task.toString() + "\n";
                taskNumber++;
            }
            return taskListString;
        }
    }

    public static void markTaskAsDone(List<Task> taskList, String command) throws DukeException {
        // If task number is not given
        if (command.equals("done") || command.equals("done ")) {
            throw new DukeException("OOPS!!! Please indicate the number of the task you have completed.");
        }

        try {
            int taskNumber = Integer.parseInt(command.split(" ", 2)[1]);
            Task completedTask = taskList.get(taskNumber - 1);
            completedTask.markAsDone();
            taskList.set(taskNumber - 1, completedTask);
            System.out.println("Nice! I've marked this task as done:\n" + "  " + completedTask.toString());
        } catch (IndexOutOfBoundsException | NumberFormatException indexException) {
            throw new DukeException("OOPS!!! Please enter a valid task number.");
        }
    }

    public static void addTaskToList(List<Task> taskList, String command) throws DukeException {
        Task task = null;
        String taskType = command.split(" ", 2)[0];
        String taskBody;

        // If there is no taskBody available
        if (command.split(" ", 2).length == 1
                || command.split(" ", 2)[1].isBlank()) {
            throw new DukeException("OOPS!!! You didn't provide any task details.");
        } else {
            taskBody = command.split(" ", 2)[1];
        }

        // Generate task with appropriate type and add to taskList
        switch (taskType) {
        case "todo":
            task = new Todo(taskBody);
            break;
        case "deadline":
            try {
                String description = taskBody.split("/by")[0];
                String by = taskBody.split("/by")[1];
                if (description.isBlank() || by.isBlank()) {
                    throw new DukeException("OOPS!!! Please add both a description and deadline for your task!");
                } else {
                    task = new Deadline(description.trim(), by.trim());
                }
            } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
                throw new DukeException("OOPS!!! Please add both a description and deadline for your task!");
            }
            break;
        case "event":
            try {
                String description = taskBody.split("/at")[0];
                String at = taskBody.split("/at")[1];
                if (description.isBlank() || at.isBlank()) {
                    throw new DukeException("OOPS!!! Please add both a description and date for your event!");
                } else {
                    task = new Event(description.trim(), at.trim());
                }
            } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
                throw new DukeException("OOPS!!! Please add both a description and date for your event!");
            }
            break;
        default:
            break;
        }

        taskList.add(task);
        System.out.println("Got it. I've added this task:\n"
                + "  " + task.toString() + "\n"
                + "Now you have " + taskList.size() + " tasks in the list");
    }

    public static void deleteTaskFromList(List<Task> taskList, String command) throws DukeException {
        // If task number is not given
        if (command.equals("delete") || command.equals("delete ")) {
            throw new DukeException("OOPS!!! Please indicate the number of the task you want to delete.");
        } else {
            try {
                int taskNumber = Integer.parseInt(command.split(" ", 2)[1]);
                Task removedTask = taskList.remove(taskNumber - 1);
                System.out.println("Noted. I've removed this task:\n" + "  " + removedTask.toString() + "\n"
                        + "Now you have " + taskList.size() + " tasks in the list.");
            } catch (IndexOutOfBoundsException | NumberFormatException exception) {
                throw new DukeException("OOPS!!! Please enter a valid task number.");
            }
        }
    }

    public static void saveToFile(List<Task> taskList) throws DukeException {
        try {
            java.nio.file.Path pathToDirectory = java.nio.file.Paths.get("data");
            java.nio.file.Path pathToFile = java.nio.file.Paths.get("data", "duke.txt");

            // Make data directory if it doesn't exist
            if (!pathToDirectory.toFile().exists()) {
                File data = new File(pathToDirectory.toString());
                data.mkdir();
            }

            String latestTaskListString = getTaskListString(taskList);
            BufferedWriter writer = new BufferedWriter(new FileWriter(pathToFile.toString()));
            writer.write(latestTaskListString);
            writer.close();

        } catch (IOException ioException) {
            throw new DukeException("OOPS!! Unable to save task list.");
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
                switch (firstWordOfCommand) {
                case "":
                    break;
                case "list":
                    System.out.println(getTaskListString(taskList));
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                case "todo":
                    // Fallthrough
                case "deadline":
                    // Fallthrough
                case "event":
                    addTaskToList(taskList, command);
                    saveToFile(taskList);
                    break;
                case "done":
                    markTaskAsDone(taskList, command);
                    saveToFile(taskList);
                    break;
                case "delete":
                    deleteTaskFromList(taskList, command);
                    saveToFile(taskList);
                    break;
                default:
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

            } catch (DukeException dukeException) {
                System.out.println(dukeException.getMessage());
            }
        }
    }

}
