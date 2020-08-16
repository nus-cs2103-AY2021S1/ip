package main.java;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();
        String msgDivider = "____________________________________________________________";
        System.out.println("Hello I'm batman\n");

        while (myObj.hasNext()) {
            String input = myObj.nextLine();
            try {
                String[] taskInput = input.split(" ", 2);
                String taskCategory = taskInput[0];

                if (taskCategory.equals("bye")) {
                    break;
                } else if (taskCategory.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int n = 1; n <= taskList.size(); n++) {
                        System.out.println(n + "." + taskList.get(n - 1));
                    }
                } else if (taskCategory.equals("done")) {
                    int taskNumberToMark = Integer.parseInt(input.substring(input.length() - 1));
                    if (taskNumberToMark > taskList.size() | taskNumberToMark < 1 ) {
                        throw new DukeException("There is no such task number.");
                    }
                    Task taskToMark = taskList.get(taskNumberToMark - 1);
                    taskToMark.markAsDone();
                    System.out.println("Nicely done. I've marked this task as done:" +
                            "\n" + taskToMark);
                } else {
                    boolean invalidTask = !taskCategory.equals("todo") &&
                            !taskCategory.equals("event") &&
                            !taskCategory.equals("deadline");
                    if (invalidTask) {
                        throw new CommandNotFoundException();
                    }

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
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println(msgDivider);
            }
        }
        System.out.println("Bye bye");
    }
}
