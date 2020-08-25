package main.java.misc;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private static Scanner sc;

    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void wrap(Runnable printMessage) {
        System.out.println();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
        printMessage.run();
        System.out.println("------------------------------------------");
        System.out.println();
    }


    public static void start() {
        wrap(() -> System.out.println("Hello from\n" + logo));
        sc = new Scanner(System.in);
    }

    public static String feed() {
        return sc.nextLine();
    }

    public static void close() {
        sc.close();
    }

    public static void bye() {
        wrap(() -> System.out.println("See you again!"));
    }

    public static void list(List<String> tasks) {
        wrap(() -> {
            System.out.println("Here are the tasks in your list: ");
            List<String> output = tasks;
            for (String s : output) {
                System.out.println(s);
            }
        });
    }

    public static void done(String str) {
        wrap(() -> {
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("    " + str);
        });
    }

    public static void delete(String task, int count) {
        wrap(() -> {
            System.out.println("Noted. I've removed this task: ");
            System.out.println("    " + task);
            System.out.printf("Now you have %d tasks in the list. \n", count);
        });
    }

    public static void clear() {
        wrap(() -> {
            System.out.println("All tasks cleared!");
        });
    }

    public static void task(String task, int count) {
        wrap(() -> {
            System.out.println("Got it. I've added this task: ");
            System.out.println("    " + task);
            System.out.printf("Now you have %d tasks in the list. \n", count);
        });
    }
}
