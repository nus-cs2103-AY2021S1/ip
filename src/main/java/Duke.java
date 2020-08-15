package main.java;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        TodoList todoList = new TodoList();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                todoList.list();
            } else {
                todoList.add(input);
            }
            input = scanner.nextLine();
        }
        System.out.println("Have a nice day.");
    }
}
