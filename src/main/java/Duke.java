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
        TodoList newList = new TodoList();

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
            } else {
                System.out.println(underscore);
                newList.addTask(input);
                System.out.println(underscore);

            }
        }
        sc.close();

    }
}

