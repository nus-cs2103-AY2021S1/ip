package main.java;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A bot that records tasks for people and keeps track of it for them.
 */
public class Willy {

    private static TaskStore storage;
    private String message;
    private static String lastGreeting = "bye";
    static String style = "\t________________________________________________________________\n";
    static String logo = "__       ____       __\n"
            + "\\  \\    /    \\    /  /\n"
            + " \\  \\  /  /\\  \\  /  /\n"
            + "  \\  \\/  /  \\  \\/  /\n"
            + "   \\____/    \\____/ ILLY ~(^-^)~\n";


    public static void main(String[] args) throws WillyException, FileNotFoundException {
        System.out.println(logo + "    Your personal life secretary");

        Scanner input = new Scanner(System.in);
        Greet startDuke = new Greet();
        // prints out intro
        System.out.println(startDuke);
        storage = new TaskStore();
        storage.createFile();
        TaskList list = new TaskList(storage);


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
                list.readList();
            }
            // take note of keyword "done" to update task
            else if (message.contains("done")) {
                int taskNum = Integer.parseInt(message.substring(5));
                list.setTaskDone(taskNum);
            }
            // take note of keyword "delete" to remove task from list
            else if (message.contains("delete")) {
                int taskNum = Integer.parseInt(message.substring(7));
                list.removeTask(taskNum);
            }
            // take note of keyword "to-do" to add normal task
            else if (message.contains("todo")) {
                try {
                    String activity = message.substring(5);
                    ToDoTask newTask = new ToDoTask(activity, TaskSymbol.TODO);
                    list.addToList(newTask);
                } catch (Exception e) {
                    WillyException error = new WillyException("Hmmm what would you like to do?");
                    System.out.println(error);
                }

            }

            // deadline input format: dd/MM/yyyy HHmm, output format: dd MMM yyyy HH:mm a
            // take note of keyword "deadline" to add task with deadline
            else if (message.contains("deadline")) {
                try {
                    int separatorIndex = message.indexOf("/");
                    String activity = message.substring(9, separatorIndex - 1);
                    String deadline = message.substring(separatorIndex + 4);
                    DeadlineTask newTask = new DeadlineTask(deadline, activity, TaskSymbol.DEADLINE);
                    list.addToList(newTask);

                } catch (Exception e){
                    WillyException error = new WillyException("Hmmm are you missing description/deadline of the task? \n\tCheck and try again?");
                    System.out.println(error);
                }
            }

            // deadline input format: dd/MM/yyyy HH:mm, output format: dd MMM yyyy HH:mm a
            // take note of keyword "event" to add task with duration
            else if (message.contains("event")) {
                try {
                    int separatorIndex = message.indexOf("/");
                    String activity = message.substring(6, separatorIndex - 1);
                    String duration = message.substring(separatorIndex + 4);
                    EventsTask newTask = new EventsTask(duration, activity, TaskSymbol.EVENT);
                    list.addToList(newTask);
                } catch (Exception e) {
                    WillyException error = new WillyException("Hmmm are you missing the description/timing of event? \n\tCheck and try again?");
                    System.out.println(error);
                }

            }
            else if (message.contains("find")) {
                    String keyword = message.substring(5);
                    list.findTask(keyword);
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
