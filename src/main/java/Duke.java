import java.util.Scanner;
import java.util.ArrayList;

import java.time.LocalDate;

public class Duke {
    public static void main(String[] args) {
        String horizontalLine = "\t-------------------------------------------------------";
        String errorPrefix = "\t\u2639" + " OOPS!!! ";
        System.out.println(horizontalLine);
        System.out.println("\tHello! I'm Duke\n" + "\t" + "What can I do for you?");
        System.out.println(horizontalLine);
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        while (scanner.hasNext()) {
            System.out.println(horizontalLine);
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println(horizontalLine);
                scanner.close();
                break;
            }
            if (userInput.equalsIgnoreCase("list")) {
                System.out.println("\tHere are the tasks in your list:");
                int index = 1;
                for (Task task : list) {
                    System.out.println("\t" + index++ + ". " + task.toString());
                }
                if (index == 1) {
                    System.out.println("\tYou have no tasks in your list :)");
                }
            } else {
                Scanner scanner2 = new Scanner(userInput);
                String cmd = scanner2.next();
                if (cmd.equalsIgnoreCase("done")) {
                    int index = scanner2.nextInt();
                    try {
                        Task task = list.get(index - 1);
                        task.markAsDone();
                        System.out.println("\tNice! I've marked this as done:");
                        System.out.println("\t  " + task.toString());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(errorPrefix + "Sorry, that is not a valid task.");
                    }

                }
                else if (cmd.equalsIgnoreCase("delete")) {
                    int index = scanner2.nextInt();
                    try {
                        System.out.println("\tNoted. I've removed this task:");
                        System.out.println("\t" + list.remove(index - 1).toString());
                        System.out.println("\tNow you have " + list.size() + " task(s) in the list.");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(errorPrefix + "Sorry, that is not a valid task.");
                    }
                }
                else if (cmd.equalsIgnoreCase("todo")) {
                    if (!scanner2.hasNext()) {
                        System.out.println(errorPrefix
                                + "The description of a todo cannot be empty.");
                    } else {
                        System.out.println("\tGot it. I've added this task:");
                        ToDo todo = new ToDo(scanner2.nextLine());
                        list.add(todo);
                        System.out.println("\t  " + todo.toString());
                        System.out.println("\tNow you have " + list.size() + " task(s) in the list.");
                    }
                }
                else if (cmd.equalsIgnoreCase("deadline")) {
                    if (!scanner2.hasNext()) {
                        System.out.println(errorPrefix
                                + "The description of a deadline cannot be empty.");
                    } else {
                        System.out.println("\tGot it. I've added this task:");
                        scanner2.useDelimiter("( /by )");
                        String description = scanner2.next();
                        scanner2.reset();
                        scanner2.next();
                        String by = scanner2.nextLine().trim();
                        Deadline deadline = new Deadline(description, by);
                        list.add(deadline);
                        System.out.println("\t  " + deadline.toString());
                        System.out.println("\tNow you have " + list.size() + " task(s) in the list.");
                    }
                }
                else if (cmd.equalsIgnoreCase("event")) {
                    if (!scanner2.hasNext()) {
                        System.out.println(errorPrefix
                                + "The description of an event cannot be empty.");
                    } else {
                        System.out.println("\tGot it. I've added this task:");
                        scanner2.useDelimiter("( /at )");
                        String description = scanner2.next();
                        scanner2.reset();
                        scanner2.next();
                        String at = scanner2.nextLine().trim();
                        Event event = new Event(description, at);
                        list.add(event);
                        System.out.println("\t  " + event.toString());
                        System.out.println("\tNow you have " + list.size() + " task(s) in the list.");
                    }
                }
                else {
                    System.out.println(errorPrefix
                            + "I'm sorry, but I don't know what that means :-(");
                }
            }
            System.out.println(horizontalLine);
        }
    }
}

