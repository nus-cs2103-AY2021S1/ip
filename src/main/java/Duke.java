package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner inputScanner = new Scanner(System.in);
        String userInput = "";
        List<String> orderList = new ArrayList<>();

        System.out.println("//////////\n" + "->Hello! I'm Duke\n" +
                "->What can I do for you?" + "\n\n");

        while (!userInput.equals("bye")) {
            userInput = inputScanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("\nBye. Hope to see you again soon!");
            } else if (userInput.equals("list")) {
                if (orderList.size() > 0) {
                    System.out.println("\n-> Current List:\n");
                    for (int i = 1; i <= orderList.size(); i++) {
                        System.out.println(i + ". " + orderList.get(i - 1) + "  <-");
                    }
                    System.out.println("\n");
                } else {
                    System.out.println("\n//////There is NO order in my list now//////\n");
                }
            } else {
                orderList.add(userInput);
                System.out.println("\n-> Added: " + userInput + "\n");
            }
        }
    }
}
