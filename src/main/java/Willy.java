package main.java;

import java.util.ArrayList;
import java.util.Scanner;

public class Willy {
    static ArrayList<Task> list = new ArrayList<>();
    static String message;
    static String lastGreeting = "bye";
    static String style = "\t________________________________________________________________\n";

    // Add Tasks to list
    public static void addToList(Task task) {
        list.add(task);
        System.out.println(style +
                "\tAy here is the task you just added:\n" +
                "\t  " + task + "\n" +
                "\tNow you have " + list.size() + " task(s) ah dun forget\n" +
                style);
    }

    public static void removeTask(int taskNum) {
        int i = taskNum - 1;
        Task task = list.get(i);
        list.remove(i);
        System.out.println(style +
                "\tOkai here is the task you just deleted:\n" +
                "\t  " + task + "\n" +
                "\tNow you have " + list.size() + " task(s) left ~\n" +
                style);
    }

    // Reads through all the tasks in the list
    public static void readList() {
        System.out.println(style);
        System.out.print("\tHere are the tasks in your list to jolt ur memory:>\n");
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
        System.out.println("\tNiceee I've marked this task as done!");
        System.out.println("\t   " + task);
        System.out.println(style);
    }

    public static void main(String[] args) throws WillyException {
        String logo = "__       ____       __\n"
                    + "\\  \\    /    \\    /  /\n"
                    + " \\  \\  /  /\\  \\  /  /\n"
                    + "  \\  \\/  /  \\  \\/  /\n"
                    + "   \\____/    \\____/ ILLY ~(^-^)~\n";
        System.out.println(logo + "    Your personal life secretary");

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
            // take note of keyword "delete" to remove task from list
            else if (message.contains("delete")) {
                int taskNum = Integer.parseInt(message.substring(7));
                removeTask(taskNum);
            }
            // take note of keyword "to-do" to add normal task
            else if (message.contains("todo")) {
                try {
                String activity = message.substring(5);
                ToDo newTask = new ToDo(activity, TaskSymbol.TODO);
                addToList(newTask);
                } catch (Exception e){
                    WillyException error = new WillyException("Hmmm what would you like to do?");
                    System.out.println(error);
                }

            }
            // take note of keyword "deadline" to add task with deadline
            else if (message.contains("deadline")) {
                try {
                    int separatorIndex = message.indexOf("/");
                    String activity = message.substring(9, separatorIndex - 1);
                    String deadline = message.substring(separatorIndex + 4);
                    DeadlineTask newTask = new DeadlineTask(deadline, activity, TaskSymbol.DEADLINE);
                    addToList(newTask);
                } catch (Exception e){
                    WillyException error = new WillyException("Hmmm the description/deadline of the task is missing... \n\tTry again with more details?");
                    System.out.println(error);
                }
            }
            // take note of keyword "event" to add task with duration
            else if (message.contains("event")) {
                try {
                    int separatorIndex = message.indexOf("/");
                    String activity = message.substring(6, separatorIndex - 1);
                    String duration = message.substring(separatorIndex + 4);
                    EventsTask newTask = new EventsTask(duration, activity, TaskSymbol.EVENT);
                    addToList(newTask);
                } catch (Exception e) {
                    WillyException error = new WillyException("Hmmm the description/timing of event is missing... \n\tTry again with more details?");
                    System.out.println(error);
                }
            }
            // else is nonsense which will produce error
            else {
                WillyException error = new WillyException("Hmmm sorry I'm not sure what you are saying, try something else?:(");
                System.out.println(error);
            }
        }
        input.close();
    }
}
