package main.java;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static void printBorder() {
        System.out.println("____________________________________________________________\n");
    }

    public static void main(String[] args) throws DukeException{
        Scanner userInput = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        printBorder();
        System.out.println("Hello I'm Duke\n");
        System.out.println("What can I do for you?\n");
        printBorder();
        String input = userInput.nextLine();

        while (!input.equals("bye")) {
                if (input.equals("list")) {
                    printBorder();
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    printBorder();
                } else if (input.contains("done")) {
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    tasks.get(index).markAsDone();
                    printBorder();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(index));
                    printBorder();
                } else {
                    printBorder();
                    if (input.contains("todo")) {
                        try {
                            Todo t = new Todo(input.substring(5));
                            tasks.add(t);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(t);
                        } catch (Exception e) {
                            throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                    } else if (input.contains("deadline") || input.contains("event")){
                        int due = input.indexOf("/");

                        if (input.contains("deadline")) {
                            try {
                                Deadline dl = new Deadline(input.substring(9, due), input.substring(due + 4));
                                System.out.println("Got it. I've added this task:");
                                tasks.add(dl);
                            } catch (Exception e) {
                                throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
                            }
                        } else if (input.contains("event")) {
                            try {
                                Event e = new Event(input.substring(6, due), input.substring(due + 4));
                                System.out.println("Got it. I've added this task:");
                                tasks.add(e);
                            } catch (Exception e) {
                                throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
                            }
                        }
                        try {
                            System.out.println(tasks.get(tasks.size() - 1));
                            System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
                        } catch (Exception e) {
                            throw new DukeException("failed to provide task info sufficiently");
                        }
                    }
                    else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            printBorder();
            input = userInput.nextLine();
        }
        printBorder();
        System.out.println("Bye. Hope to see you again soon!");
        printBorder();
    }
}