package main.java;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
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
        List<String> list = new ArrayList<>();

        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("    ______________________________________");
                System.out.println("        Bye. Hope to see you again soon!");
                System.out.println("    ______________________________________");
                break;
            } else if (userInput.equals("list")) {
                System.out.println("    ______________________________________");
                for(int i=0; i<list.size(); i++) {
                    System.out.println("        " + (i+1) + ". " + list.get(i));
                }
                System.out.println("    ______________________________________");
            } else {
                System.out.println("    ______________________________________");
                System.out.println("        added: " + userInput);
                System.out.println("    ______________________________________");
                list.add(userInput);
            }
        }
    }
}
