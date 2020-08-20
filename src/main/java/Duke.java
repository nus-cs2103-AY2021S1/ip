package main.java;
import actions.Greet;
import tasks.DeadlineTask;
import tasks.EventsTask;
import tasks.Task;
import tasks.ToDo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<Task> list = new ArrayList<>();
    static String message;
    static String lastGreeting = "bye";
    static String style = "\t___________________________________\n";

    // Add Tasks to list
    public static void addToList(Task task) {
        list.add(task);
        System.out.println(style +
                "\tGot it. I've added this task:\n" +
                "\t  " + task + "\n" +
                "\tNow you have " + list.size() + " tasks in the list.\n" +
                style);
    }

    // Reads through all the tasks in the list
    public static void readList() {
        System.out.println(style);
        System.out.print("\tHere are the tasks in your list:");
        for(int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            System.out.println("\t" + (i+1) + ". " + task);
        }
        System.out.println(style);
    }

    // Update Tasks to be done
    public static void setTaskDone(int taskNum) {
        int i = taskNum - 1;
        Task task = list.get(i);
        task.setTaskDone(true);
        System.out.println(style);
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t   " + task);
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

            // check when to end the bot
            if (message.equals(lastGreeting)) {
                // prints out exit
                System.out.println(new Greet(message));
                break;
            }
            // take note of keyword "list" to display the lists
            if (message.equals("list")) {
                // reads list
                readList();
            }
            // take note of keyword "done" to update task
            else if (message.contains("done")) {
                int taskNum = Integer.parseInt(message.substring(5));
                setTaskDone(taskNum);
            }
            // take note of keyword "to-do" to add normal task
            else if (message.contains("todo")) {
                String activity = message.substring(5);
                ToDo newTask = new ToDo(activity);
                addToList(newTask);
            }
            // take note of keyword "deadline" to add task with deadline
            else if (message.contains("deadline")) {
                int separatorIndex = message.indexOf("/");
                String activity = message.substring(9, separatorIndex -1);
                String deadline = message.substring(separatorIndex +4);
                DeadlineTask newTask = new DeadlineTask(deadline, activity);
                addToList(newTask);
            }
            // take note of keyword "event" to add task with duration
            else if (message.contains("event")) {
                int separatorIndex = message.indexOf("/");
                String activity = message.substring(6, separatorIndex -1);
                String duration = message.substring(separatorIndex +4);
                EventsTask newTask = new EventsTask(duration, activity);
                addToList(newTask);
            }
            // add task to the list
            else {
                Task newTask = new Task(message);
                addToList(newTask);
            }
        }
        input.close();
    }
}
