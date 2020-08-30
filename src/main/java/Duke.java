package main.java;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        System.out.println("    ____________________________________________________________\n");

        ArrayList<Task> taskList = new ArrayList<Task>();

        while (true) {
            String command = sc.next();
            switch(command) {
                case "bye":
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Bye. Hope to see you again soon!");
                    System.out.println("    ____________________________________________________________\n");
                    return;
                case "list":
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.print("     " + (i + 1) + ".");
                        taskList.get(i).printDescription();
                    }
                    System.out.println("    ____________________________________________________________\n");
                    break;
                case "done":
                    int index = sc.nextInt();
                    Task currentTask = taskList.get(index - 1);
                    currentTask.markAsDone();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Nice! I've marked this task as done:");
                    System.out.print("     ");
                    currentTask.printDescription();
                    System.out.println("    ____________________________________________________________\n");
                    break;
                case "todo":
                    String todoDescription = sc.nextLine();
                    Todo todo = new Todo(todoDescription);
                    taskList.add(todo);
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Got it. I've added this task:");
                    System.out.print("       ");
                    todo.printDescription();
                    System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
                    System.out.println("    ____________________________________________________________\n");
                    break;
                case "deadline":
                    String deadlineDescription = "";
                    while (!sc.hasNext("/by")) {
                        deadlineDescription += sc.next();
                        deadlineDescription += " ";
                    }
                    sc.next("/by");
                    String time = sc.nextLine();
                    Deadline deadline = new Deadline(deadlineDescription, time);
                    taskList.add(deadline);
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Got it. I've added this task:");
                    System.out.print("        ");
                    deadline.printDescription();
                    System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
                    System.out.println("    ____________________________________________________________\n");
                    break;
                case "event":
                    String eventDescription = "";
                    while (!sc.hasNext("/at")) {
                        eventDescription += sc.next();
                        eventDescription += " ";
                    }
                    sc.next("/at");
                    String date = sc.nextLine();
                    Event event = new Event(eventDescription, date);
                    taskList.add(event);
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Got it. I've added this task:");
                    System.out.print("        ");
                    event.printDescription();
                    System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
                    System.out.println("    ____________________________________________________________\n");
                    break;
                default:
                    String task = sc.nextLine();
                    taskList.add(new Task(command + task));
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     added: " + command + task);
                    System.out.println("    ____________________________________________________________\n");
                    break;
            }
        }
    }
}
