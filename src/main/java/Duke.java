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
                int listSize = taskList.size() + 1;
                Task newTask = new Task(input);
                taskList.add(newTask);
                System.out.println("Task added: " + input);
            }
        }
        System.out.println("SAYONARA HUMAN ಥ﹏ಥ");
    }
}
