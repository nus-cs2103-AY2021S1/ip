package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner inputScanner = new Scanner(System.in);
        String userInput = "";
        List<Task> taskList = new ArrayList<>();

        System.out.println("//////////\n" + "->Hello! I'm Duke\n" +
                "->What can I do for you?" + "\n\n");

        while (!userInput.equals("bye")) {

            userInput = inputScanner.nextLine();

            if (userInput.equals("bye")) {

                System.out.println("\nBye. Hope to see you again soon!");

            } else if (userInput.equals("list")) {

                if (taskList.size() > 0) {
                    System.out.println("\n-> Current List:\n");
                    for (int i = 1; i <= taskList.size(); i++) {
                        System.out.println(i + ". " + taskList.get(i - 1) + "  <-");
                    }
                    System.out.println("\n");
                } else {
                    System.out.println("\n//////There is NO order in my list now//////\n");
                }

            } else if (userInput.startsWith("done")) {
                int doneNo = Integer.parseInt(userInput.substring(5));
                if (doneNo <= 0 || doneNo - 1 >= taskList.size()) {
                    System.out.println("\n-> Sorry, this task does not exist...\n");
                } else {
                    taskList.get(doneNo - 1).done();
                    System.out.println("\n-> Good job! I have marked this task as done:\n" + taskList.get(doneNo - 1).toString() + "\n");
                }
            } else {

                taskList.add(new Task(userInput));
                System.out.println("\n-> Added: " + userInput + "\n");

            }
        }
    }
}
