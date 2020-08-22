package main.java;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<Task> taskList = new ArrayList<Task>();

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

    private static void updateTaskFile(String filePath, String fileData) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(fileData);
            fw.close();
        } catch (IOException e) {
            System.out.println("An IO exception has occured writing to ." + filePath);
        }
    }

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        String MSG_DIVIDER = "____________________________________________________________";
        System.out.println("Hello I'm batman\n");

        while (myObj.hasNext()) {
            String input = myObj.nextLine();
            try {
                String[] taskInput = input.split(" ", 2);
                String taskCategory = taskInput[0];
                boolean validTask = taskCategory.equals("todo") || taskCategory.equals("event")
                        || taskCategory.equals("deadline");

                if (taskCategory.equals("bye")) {
                    break;
                } else if (taskCategory.equals("list")) {
                    if (taskList.size() == 0) {
                        System.out.println("You have no tasks currently :)");
                        continue;
                    }
                    System.out.println("Here are the tasks in your list:");
                    for (int n = 1; n <= taskList.size(); n++) {
                        System.out.println(n + "." + taskList.get(n - 1));
                    }
                } else if (taskCategory.equals("done")) {
                    int taskNumberToMark = parseTaskNumber(input);
                    markTask(taskNumberToMark);
                } else if (taskCategory.equals("delete")) {
                    int taskNumberToMark = parseTaskNumber(input);
                    deleteTask(taskNumberToMark);
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
                } else {
                    throw new CommandNotFoundException();
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println(MSG_DIVIDER);
            }
        }
        System.out.println("Bye bye");
    }
}
