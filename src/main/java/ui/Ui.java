package ui;

import java.util.ArrayList;
import java.util.List;

import data.task.Task;

public class Ui {
    // border line
    public static void line() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Pads a given String with 2 lines, top and bottom
     *
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
     * String welcome message
     *
     * @return Bruh What's up?
     */
    public static String getWelcomeString() {
        String logo = "888888b.                                        888      \n"
                + "888  \"88b                                       888      \n"
                + "888  .88P                                        888      \n"
                + "8888888K.   888d888  888  888     88888b.  \n"
                + "888  \"Y88b  888P\"      888   888     888 \"88b \n"
                + "888    888    888          888   888    888  888 \n"
                + "888   d88P   888         Y88b  888    888    888 \n"
                + "8888888P\"   888         \"Y88888       888    888 \n"
                + "                                     \n";

        return logo + "What's up?";
    }

    /**
     * Prints a message after adding a task
     *
     * @param task the task added
     */
    public static void taskAdded(Task task, List<Task> storage) {
        String toEcho = "Task added: \n"
                + task + "\n"
                + "You now have " + storage.size() + " task(s).";
        echo(toEcho);
    }

    /**
     * String version of taskAdded
     *
     * @param task    the task to be added
     * @param storage where the tasks are stored
     * @return String of task added
     */
    public static String getTaskAddedString(Task task, List<Task> storage) {
        return "Task added: \n"
                + task + "\n"
                + "You now have " + storage.size() + " task(s).";
    }


    /**
     * lists out every task
     *
     * @param storage where the tasks are stored
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

    /**
     * string version of listOut
     *
     * @param storage where tasks are stored
     * @return String of list of tasks
     */
    public static String getListOutString(ArrayList<Task> storage) {
        StringBuilder response = new StringBuilder("Here's your tasks:\n");
        for (int i = 0; i < storage.size(); i++) {
            // list starts from 1
            Task currentTask = storage.get(i);
            response.append(i + 1).append(". ").append(currentTask).append("\n");
        }
        return response.toString();
    }

    /**
     * only lists out tasks where it contains words
     *
     * @param words   the words to be searched
     * @param storage where the tasks are stored
     */
    public static void filterList(String words, ArrayList<Task> storage) {
        line();
        System.out.println("Here's your matching tasks");
        for (int i = 0; i < storage.size(); i++) {
            // list starts from 1
            Task currentTask = storage.get(i);
            if (currentTask.toString().contains(words)) {
                System.out.println((i + 1) + ". " + currentTask);
            }
        }
        line();
    }

    /**
     * String version of filterList
     *
     * @param words   words to be searched
     * @param storage where tasks are stored
     * @return list of filtered tasks
     */
    public static String getFilterListString(String words, ArrayList<Task> storage) {
        StringBuilder response = new StringBuilder("Here's your matching tasks:\n");
        for (int i = 0; i < storage.size(); i++) {
            // list starts from 1
            Task currentTask = storage.get(i);
            if (currentTask.toString().contains(words)) {
                response.append(i + 1).append(". ").append(currentTask).append("\n");
            }
        }
        return response.toString();
    }

    public static class DukeException extends Exception {

        public DukeException(String message) {
            super(message);
        }

        /**
         * error message for having a blank task name
         *
         * @param type deadline, event, etc
         * @return error message
         */
        public static DukeException empty(String type) {
            String message = "Error: The description of " + type + " cannot be empty.";
            return new DukeException(message);
        }

        /**
         * error message for unsupported user input
         *
         * @param order user input
         * @return error message
         */
        public static DukeException invalid(String order) {
            String message = "Error: Sorry, '" + order + "' is not a recognised order.";
            return new DukeException(message);
        }

        /**
         * error message for invalid task number
         *
         * @param index task number
         * @return error message
         */
        public static DukeException outOfBounds(int index) {
            String message = "Error: There is no task number " + index + ".";
            return new DukeException(message);
        }

        /**
         * exception for having invalid line format
         *
         * @return file error message
         */
        public static DukeException fileError() {
            String message = "Error: Line in save file has invalid format";
            return new DukeException(message);
        }
    }

}
