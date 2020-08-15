package main.java;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        Boolean botRunning = true;
        ArrayList<Task> taskList = new ArrayList<Task>();
        String logo =
                "___.           __                         \n"
                        + "\\_ |__ _____ _/  |_  _____ _____    ____  \n"
                        + " | __ \\__  \\     __\\/     \\__  \\  /     \\\n"
                        + " | \\_\\ \\/ __ \\|  | |  Y Y  \\/ __ \\|   |  \\ \n"
                        + " |___  (____  /__| |__|_|  (____  /___|  /\n"
                        + "     \\/     \\/           \\/     \\/     \\/\n";
        System.out.println("Hello from\n" + logo);

        while (botRunning) {
            String input = myObj.nextLine();
            if (input.equals("bye")) {
                botRunning = false;
            } else if (input.equals("list")) {
                int n = 0;
                System.out.println("Here are the tasks in your list: ");
                for (Task task : taskList) {
                    n++;
                    System.out.println(n + "." + task);
                }
            } else if (input.contains("done")) {
                int taskNumberToMark = Integer.parseInt(input.substring(input.length()-1));
                Task taskToMark = taskList.get(taskNumberToMark - 1);
                taskToMark.markAsDone();
                System.out.println("Nicely done. I've marked this task as done: \n" +
                        taskToMark);
            } else {
                String[] taskInput = input.split(" ", 2);
                String taskCategory = taskInput[0];
                String taskMessage = taskInput[1];
                String[] taskMessageArr = taskMessage.split("/");
                Task newTask;

                if (taskCategory.equals("todo")) {
                    newTask = new ToDo(taskMessage);
                } else {
                    String taskTime = taskMessageArr[1].split(" ", 2)[1];
                    if (taskCategory.equals("deadline")) {
                        newTask = new Deadline(taskMessageArr[0].trim(), taskTime);
                    } else if (taskCategory.equals("event")) {
                        newTask = new Event(taskMessageArr[0].trim(), taskTime);
                    } else {
                        newTask = new Task(input);
                    }
                }

                taskList.add(newTask);
                String remainingTasks = "\n Now you have " + taskList.size() + " tasks in the list.";
                System.out.println("Got it. I've added this task: \n" + newTask + remainingTasks);
            }
        }
        System.out.println("SAYONARA HUMAN ಥ﹏ಥ");
    }
}
