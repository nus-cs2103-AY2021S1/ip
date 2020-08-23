package main.java;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();
        String MSG_DIVIDER = "____________________________________________________________";
        System.out.println("Hello I'm batman\n");

        while (myObj.hasNext()) {
            String input = myObj.nextLine();
            try {
                String[] taskInput = input.split(" ", 2);
                String taskCategory = taskInput[0];

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
                    String digitsOnlyInput = input.replaceAll("[^0-9]", "");
                    if (digitsOnlyInput.isEmpty()) {
                        throw new DukeException("Specify the task number to delete e.g. delete 12");
                    } else {
                        int taskNumberToMark = Integer.parseInt(digitsOnlyInput);
                        if (taskNumberToMark > taskList.size() | taskNumberToMark < 1) {
                            throw new DukeException("There is no such task number.");
                        }
                        Task taskToMark = taskList.get(taskNumberToMark - 1);
                        taskToMark.markAsDone();
                        System.out.println("Nicely done. I've marked this task as done:" +
                                "\n" + taskToMark);
                    }
                } else if (taskCategory.equals("delete")) {
                    String digitsOnlyInput = input.replaceAll("[^0-9]", "");
                    if (digitsOnlyInput.isEmpty()) {
                        throw new DukeException("Specify the task number to delete e.g. delete 12");
                    }
                    int taskNumberToMark = Integer.parseInt(digitsOnlyInput);
                    if (taskNumberToMark > taskList.size() | taskNumberToMark < 1 ) {
                        throw new DukeException("There is no such task number.");
                    }
                    Task taskToMark = taskList.remove(taskNumberToMark - 1);
                    String remainingTasksMsg = "\nNow you have " + taskList.size() + " tasks in the list.";
                    System.out.println("Noted. I've removed this task:\n" + taskToMark + remainingTasksMsg);
                } else {
                    boolean invalidTask = !(taskCategory.equals("todo") || taskCategory.equals("event")
                            || taskCategory.equals("deadline"));
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
                            throw new DukeException("Please specify the time of task " +
                                    "e.g. event finish book /by 2019-15-10");
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
            } catch (DateTimeParseException e) {
                System.out.println("Datetime could not be recognised. Use yyyy-mm-dd format e.g. 2019-10-15");
            } finally {
                System.out.println(MSG_DIVIDER);
            }
        }
        System.out.println("Bye bye");
    }
}
