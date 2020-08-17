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
                System.out.println("These are the tasks in your list:");
                for (int i = 0; i < storage.length; i++) {
                    if (storage[i] == null) {
                        break;
                    }
                    Task t = storage[i];
                    System.out.println(" " + (i + 1) + "." + "[" + t.getIcon() + "] " + t.getDescription());
                }
                System.out.println(line);
            } else if (s[0].equals("done")) {
                Task t = storage[Integer.parseInt(s[1]) - 1];
                t.markAsDone();
                System.out.println(line + "\n" + " Yay! I have marked this task as done: " + "\n"
                    + "   [" + t.getIcon() + "] " + t.getDescription() + "\n" + line);
            } else {
                Task t = new Task(input);
                storage[counter] = t;
                counter++;
                System.out.println(line + "\n" + " added: " + t.getDescription() + "\n" + line);
            }
        }
    }
}
