package main.java;
import java.util.Scanner;

public class Duke {
    public static void echoInput() {
        Scanner readInput = new Scanner(System.in);
        String currentWord = readInput.next();
        while (!currentWord.equals("bye")) {
            System.out.println(currentWord);
            currentWord = readInput.next();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        echoInput();
    }
}
