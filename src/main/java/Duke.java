package main.java;

import java.util.ArrayList;

import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        // Create horizontal line.
        StringBuilder line = new StringBuilder();
        line.append("_".repeat(50));

        // Print greeting message.
        System.out.println(line + "\n" + " Hey there! I am Popi" + "\n"
            + " How can I help you?" + "\n" + line + "\n");

        // ArrayList to store text(s)
        ArrayList<Task> storage = new ArrayList<>();

        // Process user input(s).
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            // Check input
            try {
                checkInput(input);
            } catch (UnknownInputException | TodoIncompleteException | EventIncompleteException
                | DeadlineIncompleteException | DoneIncompleteException | NoInputException
                    | DoneOutOfListException e) {
                System.out.println(line + "\n" + e.getMessage() + "\n" + line + "\n" + " ");
                continue;
            }
            // Split string for command purposes
            String[] s = input.split(" ");
            if (s[0].equals("bye")) {
                System.out.println(line + "\n" + " Bye! Hope to see you again in the future!"
                    + "\n" + line + "\n");
                break;
            } else if (s[0].equals("list")) {
                System.out.println(line);
                System.out.println(" These are the tasks in your list:");
                for (int i = 0; i < storage.size(); i++) {
                    Task t = storage.get(i);
                    System.out.println(" " + (i + 1) + "." + t.toString());
                }
                System.out.println(line);
            } else if (s[0].equals("done")) {
                Task t = storage.get(Integer.parseInt(s[1]) - 1);
                t.markAsDone();
                System.out.println(line + "\n" + " Yay! I have marked this task as done: " + "\n"
                    + "   " + t.toString() + "\n" + line);
            } else {
                Task t;
                if (s[0].equals("event")) {
                    // Split string to get date
                    String[] str = input.split(" /at ");
                    // Ignore task type
                    String description = str[0].substring(6);
                    String date = str[1];
                    t = new Event(description, date);
                } else if (s[0].equals("deadline")) {
                    // Split string to get date
                    String[] str = input.split(" /by ");
                    // Ignore task type
                    String description = str[0].substring(9);
                    String date = str[1];
                    t = new Deadline(description, date);
                } else {
                    t = new Todo(input.substring(5));
                }
                storage.add(t);
                System.out.println(line + "\n" + " Okay! I have added this task:" + "\n" + "   "
                    + t.toString() + "\n" + " Now you have " + storage.size() + (storage.size() > 1 ? " tasks"
                        : " task") + "\n" + line);
            }
        }
    }

    // Method to check user input
    public static void checkInput(String line) throws UnknownInputException, TodoIncompleteException
        ,EventIncompleteException, DeadlineIncompleteException, DoneIncompleteException, NoInputException
            ,DoneOutOfListException {

        // Store all valid commands
        ArrayList<String> validCommand = new ArrayList<>();
        validCommand.add("list");
        validCommand.add("done");
        validCommand.add("deadline");
        validCommand.add("event");
        validCommand.add("todo");
        validCommand.add("bye");
        String[] input = line.split(" ");

        if (input[0].equals("")) {
            throw new NoInputException();
        } else if (!validCommand.contains(input[0])) {
            throw new UnknownInputException();
        } else if (input.length == 1) {
            if (input[0].equals("done")) {
                throw new DoneIncompleteException();
            } else if (input[0].equals("deadline")) {
                throw new DeadlineIncompleteException();
            } else if (input[0].equals("event")) {
                throw new EventIncompleteException();
            } else if (input[0].equals("todo")) {
                throw new TodoIncompleteException();
            }
        } else if (input[0].equals("done")) {
            if (Integer.parseInt(input[1]) < 1) {
                throw new DoneOutOfListException();
            }
        }
    }
}
