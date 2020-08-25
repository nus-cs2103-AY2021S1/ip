package main.java;

import java.util.ArrayList;
import java.util.Scanner;


public class UI {
    Scanner sc = new Scanner(System.in);

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greetings = "Hello! I'm Bob\nWhat can I do for you?";
        String exit = "Bye! Hope to see you again.";
        System.out.println("Hello from\n" + logo);
        System.out.println(greetings);
    }

    public void printOutList(TaskList tasks) throws BobIndexOutOfBoundsException {
        for(int i = 1; i < tasks.size()+1; i++) {
            Task task = tasks.get(i);
            System.out.println(i +"." + task.toString());
        }
        if(tasks.isEmpty()) {
            System.out.println("There are no tasks in the list at the moment. Feel free to add any.");
        }
    }


    public void deleteTask(TaskList tasks, int index) throws BobIndexOutOfBoundsException {
        Task task = tasks.get(index);
        System.out.println("Noted. I have removed the following task: ");
        System.out.println("\t" + task.toString());
        System.out.println("There are now " + tasks.size() + " remaining tasks on the list.");
    }

    public void markAsDone(TaskList tasks, int index) throws BobIndexOutOfBoundsException {
        Task task = tasks.get(index);
        System.out.println("Good job! I have marked this task as done:");
        System.out.println("\t" + index + "." + task.toString());
    }

    public void addTask(Task task) {
        System.out.println("Got it! I have added a new task to the list.");
        System.out.println("added: " + task.toString());
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void printError(String error) {
        System.out.println(error);
    }
}
