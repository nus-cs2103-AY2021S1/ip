package ui;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import data.task.Task;
import storage.Storage;
import tasklist.TaskList;

public class Ui {
    // border line
    public static void line() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Pads a given String with 2 lines, top and bottom
     * @param output the String to pad
     */
    public static void echo(String output) {
        line();
        System.out.println(output);
        line();
    }

    /**
     * Welcome message
     */
    public static void welcome() {
        String logo = " ____             _     \n"
                        + "|  _ \\           | |    \n"
                        + "| |_) |_ __ _   _| |__\n"
                        + "|  _ <| '__| | | | '_ \\ \n"
                        + "| |_) | |  | |_| | | | |\n"
                        + "|____/|_|   \\__,_|_| |_|\n";

        echo(logo + "What's up?");
    }


    /**
     * Prints a message after adding a task
     * @param task the task added
     */
    public static void taskAdded(Task task, List<Task> storage) {
        String toEcho = "Task added: \n"
                + task + "\n"
                + "You now have " + storage.size() + " task(s).";
        echo(toEcho);
    }


    /**
     * Prints the list of tasks in storage
     */
    public static void listOut(ArrayList<Task> storage) {
        line();
        System.out.println("Here's your tasks");
        for (int i = 0; i < storage.size(); i++) {
            // list starts from 1
            Task currentTask = storage.get(i);
            System.out.println((i + 1) + ". " + currentTask);
        }
        line();
    }

    public static class DukeException extends Exception {

        public DukeException(String message) {
            super(message);
        }

        public static DukeException empty(String type) {
            String message = "The description of " + type + " cannot be empty.";
            return new DukeException(message);
        }

        public static DukeException invalid(String order) {
            String message = "Sorry, '" + order + "' is not a recognised order.";
            return new DukeException(message);
        }

        public static DukeException outOfBounds(int index) {
            String message = "There is no task number " + index + ".";
            return new DukeException(message);
        }

        /**
         * exception for having invalid line format
         * @return file error message
         */
        public static DukeException fileError() {
            String message = "Line in save file has invalid format";
            return new DukeException(message);
        }

        static DukeException loadingError() {
            String message = "There has been a loading error";
            return new DukeException(message);
        }
    }

}
