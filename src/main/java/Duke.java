package main.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<Task> taskList = new ArrayList<Task>();
    static String directoryName = "./data/";
    static String fileName = directoryName + "/" + "duke.txt";

    private static int parseTaskNumber(String input) throws DukeException {
        String digitsOnlyInput = input.replaceAll("[^0-9]", "");
        if (digitsOnlyInput.isEmpty()) {
            throw new DukeException("Specify the task number to change e.g. done 12 / delete 4");
        } else {
            int taskNumberToMark = Integer.parseInt(digitsOnlyInput);
            if (taskNumberToMark > taskList.size() | taskNumberToMark < 1) {
                throw new DukeException("There is no such task number.");
            } else {
                return taskNumberToMark;
            }
        }
    }

    private static void deleteTask(int taskNumberToMark) {
        Task taskToMark = taskList.remove(taskNumberToMark - 1);
        String remainingTasksMsg = "\nNow you have " + taskList.size() + " tasks in the list.";
        System.out.println("Noted. I've removed this task:\n" + taskToMark + remainingTasksMsg);
    }

    private static void markTask(int taskNumberToMark) {
        Task taskToMark = taskList.get(taskNumberToMark - 1);
        taskToMark.markAsDone();
        System.out.println("Nicely done. I've marked this task as done:" +
                "\n" + taskToMark);
    }

    private static void updateTaskFile() {
        try {
            // Convert ArrayList<Task> to String[]
            String[] taskArr = new String[taskList.size()];
            for (int k = 0; k < taskList.size(); k++) {
                taskArr[k] = taskList.get(k).getPlainText();
            }

            String fileData = String.join("\n",
                    taskArr);
            FileWriter fw = new FileWriter(fileName);
            fw.write(fileData);
            fw.close();
        } catch (IOException e) {
            System.out.println("An IO exception has occurred writing to ." + fileName);
        }
    }

    private static void loadTaskFile() {
        try {
            File directory = new File(directoryName);
            File file = new File(fileName);

            // creates data directory if it does not exist
            if (!directory.exists()) {
                directory.mkdir();
            }

            // creates duke.txt if it does not exist
            if (!file.exists()) {
                file.createNewFile();
            }

            Scanner s = new Scanner(file);

            while (s.hasNext()) {
                String plainText = s.nextLine();

                String[] plainTextSplit = plainText.split(" \\| ");
                String newTaskCategory = plainTextSplit[0];
                String newTaskStatus = plainTextSplit[1];
                String newTaskDescription = plainTextSplit[2];
                String newTaskTime = plainTextSplit.length > 3 ? plainTextSplit[3] : "";

                switch (newTaskCategory) {
                case "T":
                    taskList.add(new ToDo(newTaskDescription, Boolean.parseBoolean(newTaskStatus)));
                    break;
                case "E":
                    if (newTaskTime.isEmpty()) {
                        throw new DukeException("Error reading from " + fileName + ": Event is missing time");
                    } else {
                        taskList.add(new Event(newTaskDescription, Boolean.parseBoolean(newTaskStatus), newTaskTime));
                    }
                    break;
                case "D":
                    if (newTaskTime.isEmpty()) {
                        throw new DukeException("Error reading from " + fileName + ": Deadline is missing time");
                    } else {
                        taskList.add(new Deadline(newTaskDescription, Boolean.parseBoolean(newTaskStatus), newTaskTime));
                    }
                    break;
                }
            }
        } catch (DukeException | IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        String MSG_DIVIDER = "____________________________________________________________";
        System.out.println("Hello I'm batman\n");

        loadTaskFile();

        while (myObj.hasNext()) {
            String input = myObj.nextLine();
            try {
                String[] taskInput = input.split(" ", 2);
                String taskCategory = taskInput[0];
                boolean validTask = taskCategory.equals("todo") || taskCategory.equals("event")
                        || taskCategory.equals("deadline");

                if (taskCategory.equals("bye")) {
                    System.out.println("Bye bye");
                    break;
                } else if (taskCategory.equals("list")) {
                    if (taskList.size() == 0) {
                        System.out.println("You have no tasks currently :)");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int n = 1; n <= taskList.size(); n++) {
                            System.out.println(n + "." + taskList.get(n - 1));
                        }
                    }
                } else if (taskCategory.equals("done")) {
                    int taskNumberToMark = parseTaskNumber(input);
                    markTask(taskNumberToMark);
                    updateTaskFile();
                } else if (taskCategory.equals("delete")) {
                    int taskNumberToMark = parseTaskNumber(input);
                    deleteTask(taskNumberToMark);
                    updateTaskFile();
                } else if (validTask) {
                    boolean incompleteTask = taskInput.length == 1;
                    if (incompleteTask) {
                        throw new IncompleteCommandException(taskCategory);
                    }

                    String taskMessage = taskInput[1];
                    Task newTask;
                    if (taskCategory.equals("todo")) {
                        newTask = new ToDo(taskMessage);
                    } else {
                        String[] taskMessageArr = taskMessage.split("/");
                        boolean hasTime = taskMessageArr.length > 1 &&
                                taskMessageArr[1].split(" ", 2).length > 1;
                        if (!hasTime) {
                            throw new IncompleteCommandException(taskCategory);
                        }

                        String taskTime = taskMessageArr[1].split(" ", 2)[1];
                        if (taskCategory.equals("deadline")) {
                            newTask = new Deadline(taskMessageArr[0].trim(), taskTime);
                        } else {
                            newTask = new Event(taskMessageArr[0].trim(), taskTime);
                        }
                    }
                    taskList.add(newTask);
                    String remainingTasksMsg = "\nNow you have " + taskList.size() + " tasks in the list.";
                    System.out.println("Got it. I've added this task:\n" + newTask + remainingTasksMsg);
                    updateTaskFile();
                } else {
                    throw new CommandNotFoundException();
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println(MSG_DIVIDER);
            }
        }
    }
}
