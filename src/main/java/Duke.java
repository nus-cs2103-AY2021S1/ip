package main.java;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner inputScanner = new Scanner(System.in);
        String userInput = "";

        System.out.println("//////////\n" + "->Hello! I'm Duke\n" +
                "->What can I do for you?" + "\n\n");

        while (!userInput.equals("bye")) {
            userInput = inputScanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("\nBye. Hope to see you again soon!");
            } else {
                System.out.println("\n->" + userInput + "\n");
            }
        }
    }
}
