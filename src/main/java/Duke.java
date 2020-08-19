package main.java;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";

        String underscore = "    ____________________________________________________________";

        System.out.println(underscore);
        System.out.println(logo + "\n" + "    Hello! I'm Duke" + "\n" + "    What can I do for you?");
        System.out.println(underscore);

        Scanner sc = new Scanner(System.in);
        TaskList newList = new TaskList();

        while(sc.hasNext()) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println(underscore);
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println(underscore);
                return;
            } else if (input.equals("list")) {
                System.out.println(underscore);
                newList.listTasks();
                System.out.println(underscore);
            } else if (input.contains("done")) {
                String taskNumberString = input.substring(5);
                int taskNumberInt = Integer.parseInt(taskNumberString) - 1;
                System.out.println(underscore);
                newList.get(taskNumberInt).markDone();
                System.out.println(underscore);
            } else {
                if (input.contains("todo")) {
                    String info = input.substring(5);
                    Todos newTodo = new Todos(info);
                    System.out.println(underscore);
                    newList.addTask(newTodo);
                    System.out.println(underscore);
                } else if (input.contains("deadline")) {
                    String info = input.substring(9);
                    String[] information = info.split("/by ");
                    String description = information[0];
                    String by = information[1];
                    Deadlines newDeadline = new Deadlines(description, by);
                    System.out.println(underscore);
                    newList.addTask(newDeadline);
                    System.out.println(underscore);
                } else if (input.contains("event")) {
                    String info = input.substring(6);
                    String[] information = info.split("/at ");
                    String description = information[0];
                    String at = information[1];
                    Events newEvent = new Events(description,at);
                    System.out.println(underscore);
                    newList.addTask(newEvent);
                    System.out.println(underscore);
                } else {
                    System.out.println("invalid");
                }

            }
        }
        sc.close();

    }
}

