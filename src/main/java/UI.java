package main.java;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class consists of methods which prints out information that may be helpful to the user.
 * It also consists of a single method which accepts user input, which may be passed to Bob's
 * parser.
 */
public class UI {
    /** A scanner which scans user's input */
    Scanner sc = new Scanner(System.in);

    /**
     * Prints out a greeting from Bob to the user.
     */
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

    /**
     * Iterates through a TaskList to print out all tasks on the TaskList.
     *
     * @param tasks the TaskList to be printed out.
     * @throws BobIndexOutOfBoundsException if the method tries to retrieve a task with an index not on the list.
     */
    public void printOutList(TaskList tasks) throws BobIndexOutOfBoundsException {
        for(int i = 1; i < tasks.size()+1; i++) {
            Task task = tasks.get(i);
            System.out.println(i +"." + task.toString());
        }
        if(tasks.isEmpty()) {
            System.out.println("There are no tasks in the list at the moment. Feel free to add any.");
        }
    }

    /**
     * Prints out a message to indicate that a task of a particular index has been deleted from the TaskList.
     *
     * @param tasks the TaskList from which the task has been deleted.
     * @param index the index of the task on the TaskList that has been deleted.
     * @throws BobIndexOutOfBoundsException if the index of the task to be deleted does not exist on the TaskList.
     */
    public void deleteTask(TaskList tasks, int index) throws BobIndexOutOfBoundsException {
        Task task = tasks.get(index);
        System.out.println("Noted. I have removed the following task: ");
        System.out.println("\t" + task.toString());
        System.out.println("There are now " + tasks.size() + " remaining tasks on the list.");
    }

    /**
     * Prints out a message to indicate that a task of a particular index has been marked as done on the TaskList.
     *
     * @param tasks the TaskList from which the task has been marked as done.
     * @param index the index of the task on the TaskList that has been marked as done.
     * @throws BobIndexOutOfBoundsException if the index of the task to be marked as done does not exist on the TaskList.
     */
    public void markAsDone(TaskList tasks, int index) throws BobIndexOutOfBoundsException {
        Task task = tasks.get(index);
        System.out.println("Good job! I have marked this task as done:");
        System.out.println("\t" + index + "." + task.toString());
    }

    /**
     * Prints out a message to indicate that a task has been added to the TaskList.
     *
     * @param task the task that has been added to TaskList.
     */
    public void addTask(Task task) {
        System.out.println("Got it! I have added a new task to the list.");
        System.out.println("added: " + task.toString());
    }

    /**
     * Returns user input.
     *
     * @return user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints out an error message.
     * @param error the error message to be printed out.
     */
    public void printError(String error) {
        System.out.println(error);
    }
}
