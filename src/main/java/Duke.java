package main.java;
import actions.Greet;
import tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<Task> list = new ArrayList<>();
    static String message;
    static String lastGreeting = "bye";
    static String style = "\t___________________________________\n";

    public static void addToList(Task task) {
        list.add(task);
        System.out.println(style + "\tadded: " + task + "\n" + style);
    }

    public static void readList() {
        System.out.println(style);
        System.out.println("\tHere are the tasks in your list:");
        for(int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            System.out.println("\t" + (i+1) + ". " +
                    "[" + task.getStatusIcon() + "] " +
                    task);
        }
        System.out.println(style);
    }

    public static void setTaskDone(int taskNum) {
        int i = taskNum - 1;
        Task task = list.get(i);
        task.setTaskDone(true);
        System.out.println(style);
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t   " + "[" + task.getStatusIcon() + "] " + task);
        System.out.println(style);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner input = new Scanner(System.in);
        Greet startDuke = new Greet();
        // prints out intro
        System.out.println(startDuke);
        while (input.hasNext()) {
            String message = input.nextLine();
            if (message.equals(lastGreeting)) {
                // prints out exit
                System.out.println(new Greet(message));
                break;
            }
            if (message.equals("list")) {
                // reads list
                readList();
            } else if (message.contains("done")) {
                int taskNum = Integer.parseInt(message.substring(5));
                setTaskDone(taskNum);
            } else {
                Task newTask = new Task(message);
                addToList(newTask);
            }
        }
        input.close();
    }
}
