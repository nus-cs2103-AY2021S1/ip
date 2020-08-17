package main.java;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Create horizontal line.
        StringBuilder line = new StringBuilder();
        line.append("_".repeat(50));

        // Print greeting message.
        System.out.println(line + "\n" + " Hey there! I am Popi" + "\n"
            + " How can I help you?" + "\n" + line + "\n");

        // Array to store text(s)
        Task[] storage = new Task[100];
        int counter = 0;

        // Process user input(s).
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            // Split string for command purposes
            String[] s = input.split(" ");
            if (s[0].equals("bye")) {
                System.out.println(line + "\n" + " Bye! Hope to see you again in the future!"
                    + "\n" + line + "\n");
                break;
            } else if (s[0].equals("list")) {
                System.out.println(line);
                System.out.println(" These are the tasks in your list:");
                for (int i = 0; i < storage.length; i++) {
                    if (storage[i] == null) {
                        break;
                    }
                    Task t = storage[i];
                    System.out.println(" " + (i + 1) + "." + t.toString());
                }
                System.out.println(line);
            } else if (s[0].equals("done")) {
                Task t = storage[Integer.parseInt(s[1]) - 1];
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
                storage[counter] = t;
                counter++;
                System.out.println(line + "\n" + " Okay! I have added this task:" + "\n" + "   " + t.toString()
                    + "\n" + " Now you have " + counter + (counter > 1 ? " tasks" : " task") + "\n" + line);
            }
        }
    }
}
