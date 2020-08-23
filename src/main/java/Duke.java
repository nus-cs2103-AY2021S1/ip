package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner inputScanner = new Scanner(System.in);
        String userInput = "";
        List<Task> taskList = new ArrayList<>();

        System.out.println("//////////\n" + "->Hello! I'm Duke\n" +
                "->What can I do for you?" + "\n");

        while (!userInput.equals("bye")) {
            userInput = inputScanner.nextLine();

            if (userInput.equals("bye")) {

                System.out.println("\nBye. Hope to see you again soon!");

            } else if (userInput.equals("list")) {

                if (taskList.size() > 0) {
                    System.out.println("\n-> Current List:\n");
                    for (int i = 1; i <= taskList.size(); i++) {
                        System.out.println(i + ". " + taskList.get(i - 1));
                    }
                    System.out.println("\n");
                } else {
                    System.out.println("\n//////There is NO task in your list now//////\n");
                }

            } else if (userInput.startsWith("done")) {

                try {
                    int doneNo = Integer.parseInt(userInput.substring(5));
                    if (doneNo <= 0 || doneNo - 1 >= taskList.size()) {
                        System.out.println("\n-> Sorry, this task does not exist...\n");
                    } else {
                        taskList.get(doneNo - 1).done();
                        System.out.println("\n-> Good job! I have marked this task as done:\n" + taskList.get(doneNo - 1).toString() + "\n");
                    }
                } catch (Exception e) {
                    System.out.println("\n-> Oops, there is an error...\n" +
                            "-> please add correct description to \"done\" order\n" +
                            "-> done {order of task in task list}\n");
                }

            } else if (userInput.startsWith("todo")) {

                try {
                    if (userInput.substring(5).length() == 0) {
                        throw new Exception();
                    }
                    Todo newTodo = new Todo(userInput.substring(5));
                    taskList.add(newTodo);
                    System.out.println(
                            "\n-> I have added a Todo:\n" +
                                    newTodo.toString() +
                                    "\nYou have " + taskList.size() + " tasks in your list currently.\n"
                    );
                } catch (Exception e) {
                    System.out.println("\n-> Oops, there is an error...\n" +
                            "-> please add correct description to \"todo\" order\n" +
                            "-> todo {task content}\n");
                }

            } else if (userInput.startsWith("deadline")) {

                try {
                    String[] splited = userInput.substring(9).split("/");
                    Deadline newDdl = new Deadline(splited[0], splited[1].replace(" ", ""));
                    taskList.add(newDdl);
                    System.out.println(
                            "\n-> I have added a Deadline:\n" +
                                    newDdl.toString() +
                                    "\nYou have " + taskList.size() + " tasks in your list currently.\n"
                    );
                } catch (Exception e) {
                    System.out.println("\n-> Oops, there is an error...\n" +
                            "-> please add correct description to \"deadline\" order\n" +
                            "-> deadline {task content} /{yyyy-mm-dd}\n");
                }

            } else if (userInput.startsWith("event")) {

                try {
                    String[] splited = userInput.substring(6).split("/");
                    Event newEvent = new Event(splited[0], splited[1].replace(" ", ""));
                    taskList.add(newEvent);
                    System.out.println(
                            "\n-> I have added an Event:\n" +
                                    newEvent.toString() +
                                    "\nYou have " + taskList.size() + " tasks in your list currently.\n"
                    );
                } catch (Exception e) {
                    System.out.println("\n-> Oops, there is an error...\n" +
                            "-> please add correct description to \"event\" order\n" +
                            "-> event {task content} /{yyyy-mm-dd}\n");
                }

            } else if (userInput.startsWith("delete")) {

                try {
                    int rmNo = Integer.parseInt(userInput.substring(7));
                    if (rmNo <= 0 || rmNo - 1 >= taskList.size()) {
                        System.out.println("\n-> Sorry, this task does not exist...\n");
                    } else {
                        System.out.println("\n-> I have removed this task:\n" + taskList.get(rmNo - 1).toString() + "\n");
                        taskList.remove(rmNo - 1);
                        System.out.println("Now you have " + taskList.size() + " tasks in your list." + "\n");
                    }
                } catch (Exception e) {
                    System.out.println("\n-> Oops, there is an error...\n" +
                            "-> please add correct description to \"done\" order\n" +
                            "-> done {order of task in task list}\n");
                }

            } else {

                System.out.println(
                        "\n-> Sorry I cannot understand, please tap in your order correctly." +
                        "\n-> todo {task content}   || add a todo task" +
                        "\n-> deadline {task content} /{time}   || add a deadline task" +
                        "\n-> event {task content} /{time} || add an event task" +
                        "\n-> list   || list all tasks" +
                        "\n-> done {order of task in task list}   || mark a task as done\n");

            }
        }
        inputScanner.close();
    }
}
