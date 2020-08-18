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
                    System.out.println("\n//////There is NO task in your list now//////\n");
                }

            } else if (userInput.startsWith("done")) {

                int doneNo = Integer.parseInt(userInput.substring(5));
                if (doneNo <= 0 || doneNo - 1 >= taskList.size()) {
                    System.out.println("\n-> Sorry, this task does not exist...\n");
                } else {
                    taskList.get(doneNo - 1).done();
                    System.out.println("\n-> Good job! I have marked this task as done:\n" + taskList.get(doneNo - 1).toString() + "\n");
                }

            } else if (userInput.startsWith("todo")) {

                Todo newTodo = new Todo(userInput.substring(5));
                taskList.add(newTodo);
                System.out.println(
                        "\n-> I have added a Todo:\n" +
                        newTodo.toString() +
                        "\nYou have " + taskList.size() + " tasks in your list currently.\n"
                );

            } else if (userInput.startsWith("deadline")) {

                String[] splited = userInput.substring(9).split("/");
                Deadline newDdl = new Deadline(splited[0], splited[1]);
                taskList.add(newDdl);
                System.out.println(
                        "\n-> I have added a Deadline:\n" +
                        newDdl.toString() +
                        "\nYou have " + taskList.size() + " tasks in your list currently.\n"
                );

            } else if (userInput.startsWith("event")) {

                String[] splited = userInput.substring(9).split("/");
                Deadline newEvent = new Deadline(splited[0], splited[1]);
                taskList.add(newEvent);
                System.out.println(
                        "\n-> I have added an Event:\n" +
                        newEvent.toString() +
                        "\nYou have " + taskList.size() + " tasks in your list currently.\n"
                );

            } else {

                System.out.println(
                        "\n-> please tap in your order correctly." +
                        "\n-> todo {task content}" +
                        "\n-> deadline {task content} {time}" +
                        "\n-> event {task content} {time} \n");

            }
        }
    }
}
