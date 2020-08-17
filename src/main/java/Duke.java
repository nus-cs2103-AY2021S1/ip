package main.java;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Create horizontal line.
        StringBuilder line = new StringBuilder();
        line.append("_".repeat(50));

        // Print greeting message.
        System.out.println(line + "\n" + "Hey there! I am Popi" + "\n"
            + "How can I help you?" + "\n" + line + "\n");

        // Process user input(s).
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(line + "\n" + "Bye! Hope to see you again in the future!"
                    + "\n" + line + "\n");
                break;
            } else {
                System.out.println(line + "\n" + input + "\n" + line + "\n");
            }
        }
    }
}
