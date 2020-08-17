package main.java;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    ______________________________________");
        System.out.println("        Hello! I'm Duke");
        System.out.println("        What can I do for you?");
        System.out.println("    ______________________________________");

        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>();

        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("    ______________________________________");
                System.out.println("        Bye. Hope to see you again soon!");
                System.out.println("    ______________________________________");
                break;

            } else if (userInput.equals("list")) {
                System.out.println("    ______________________________________");
                System.out.println("        Here are the tasks in your list:");
                for(int i=0; i<list.size(); i++) {
                    System.out.println("        " + (i+1) + "." + list.get(i));
                }
                System.out.println("    ______________________________________");

            }  else if (userInput.startsWith("done")) {
                int spacePosition = userInput.indexOf(" ");
                int taskPosition = Integer.parseInt(userInput.substring(spacePosition+1));
                list.get(taskPosition-1).markAsDone();
                System.out.println("    ______________________________________");
                System.out.println("        Nice! I've marked this task as done:");
                System.out.println("        " + list.get(taskPosition-1));
                System.out.println("    ______________________________________");

            } else if (userInput.startsWith("todo")) {
                try {
                    String description = userInput.substring(5);
                    Task toDo = new Todo(description);
                    list.add(toDo);
                    System.out.println("    ______________________________________");
                    System.out.println("        Got it. I've added this task:");
                    System.out.println("            " + toDo);
                    System.out.println("        Now you have " + list.size() + " tasks in the list.");
                    System.out.println("    ______________________________________");
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("    ______________________________________");
                    System.out.println("        ☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println("    ______________________________________");
                }

            } else if (userInput.startsWith("deadline")) {
                try {
                    int spacePosition = userInput.indexOf(" ");
                    int keywordPosition = userInput.indexOf("/by");
                    String description = userInput.substring(spacePosition + 1, keywordPosition - 1);
                    String by = userInput.substring(keywordPosition + 4);
                    Task deadline = new Deadline(description, by);
                    list.add(deadline);
                    System.out.println("    ______________________________________");
                    System.out.println("        Got it. I've added this task:");
                    System.out.println("            " + deadline);
                    System.out.println("        Now you have " + list.size() + " tasks in the list.");
                    System.out.println("    ______________________________________");
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("    ______________________________________");
                    System.out.println("        ☹ OOPS!!! The description of a deadline cannot be empty or in wrong format.");
                    System.out.println("    ______________________________________");
                }
            } else if (userInput.startsWith("event")) {
                try {
                    int spacePosition = userInput.indexOf(" ");
                    int keywordPosition = userInput.indexOf("/at");
                    String description = userInput.substring(spacePosition + 1, keywordPosition - 1);
                    String at = userInput.substring(keywordPosition + 4);
                    Task event = new Event(description, at);
                    list.add(event);
                    System.out.println("    ______________________________________");
                    System.out.println("        Got it. I've added this task:");
                    System.out.println("            " + event);
                    System.out.println("        Now you have " + list.size() + " tasks in the list.");
                    System.out.println("    ______________________________________");
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("    ______________________________________");
                    System.out.println("        ☹ OOPS!!! The description of an event cannot be empty or in wrong format.");
                    System.out.println("    ______________________________________");
                }

            } else {
                System.out.println("    ______________________________________");
                System.out.println("        ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("    ______________________________________");
            }
        }
    }
}
