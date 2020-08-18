package main.java;

import java.util.Scanner;

public class Duke {
    private static String[] exitWords = new String[] { "bye" };
    private static boolean exitLoop = false;

    private static void drawLine() {
        char star = '*';
        char lineLength = 50;

        System.out.println();
        for (int i = 0; i < lineLength; i++) {
            System.out.print(star);
        }
        System.out.println();
    }

    private static void greet() {
        drawLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        drawLine();
    }

    private static void echo(String str) {
        drawLine();
        System.out.println(str);
        drawLine();
    }

    private static boolean checkExit(String str) {
        for (String exitWord : exitWords) {
            if (str.equals(exitWord)) {
                return true;
            }
        }

        return false;
    }

    private static void exit() {
        String exitWords = "Bye, hope to see you again soon!";

        drawLine();
        System.out.println(exitWords);
        drawLine();

        exitLoop = true;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();

        while (!exitLoop) {
            String inputLine = UserInput.getOneLine();

            if (checkExit(inputLine)) {
                exit();
            } else {
                echo(inputLine);
            }
        }
    }
}
