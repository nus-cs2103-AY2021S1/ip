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
        String[] storage = new String[100];
        int counter = 0;

        // Process user input(s).
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(line + "\n" + " Bye! Hope to see you again in the future!"
                    + "\n" + line + "\n");
                break;
            } else if (input.equals("list")) {
                System.out.println(line);
                for (int i = 0; i < storage.length; i++) {
                    if (storage[i] == null) {
                        break;
                    }
                    System.out.println(" " + (i + 1) + ". " + storage[i]);
                }
                System.out.println(line);
            } else {
                storage[counter] = input;
                counter++;
                System.out.println(line + "\n" + " added: " + input + "\n" + line);
            }
        }
    }
}
