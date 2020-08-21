package main.java;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Ui {
    public static final String INDENTATION = "    ";
    public static final String DIVIDER = "____________________________________________________________";
    public static final String GREETING = "Hello! I am Smith\n" + "What can I do for you?";
    public static final String EXITMESSAGE = "Bye. Hope to see you again soon!";
    public static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String makeBlock(String string) {
        String[] strings = string.split("\n");
        String result = INDENTATION + DIVIDER + "\n";
        for(int i = 0; i < strings.length; i = i + 1) {
            result = result + INDENTATION + strings[i] + "\n";
        }
        result = result + INDENTATION + DIVIDER + "\n";
        return  result;
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showGreeting() {
        //System.out.println("Hello from\n" + logo);
        System.out.println(makeBlock(logo + "\n" + GREETING));
    }

    public void handle(Exception exception) {
        System.out.println(makeBlock(exception.getMessage()));
    }

    public void showAdd(Task task, int size) {
        String result = "Got it. I have added this task:\n  " + task.toString() + "\nNow you have " + size + " tasks in the list.";
        System.out.println(makeBlock(result));
    }

    public void showList(TaskList taskList) {
        /*
        String result = "";
        for(int i = 0; i < tasks.size(); i = i + 1) {
            result = result.concat(String.valueOf(i + 1) + "." + tasks.get(i).toString() + "\n");
        }
        */
        System.out.println(makeBlock(taskList.toString()));
    }

    public void showDone(Task task, int count) {
        System.out.println(makeBlock("Nice! I have marked this task as done:\n" + String.valueOf(count) + "." + task.toString()));
    }

    public void showDelete(Task task, int count, int size) {
        System.out.println(makeBlock("Noted. I have removed this task:\n" +
                String.valueOf(count) +
                "." + task.toString() +
                "\nNow you have " + size + " tasks in the list."));
    }

    public void showExit() {
        System.out.println(makeBlock(EXITMESSAGE));
    }
}
