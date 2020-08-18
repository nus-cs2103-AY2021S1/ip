package main.java;

import java.util.Scanner;

public class Duke {

    private static char star = '*';
    private static int lineLength = 50;

    private static void drawLine(char ch, int length) {
        System.out.println();
        for (int i = 0; i < length; i++) {
            System.out.print(ch);
        }
        System.out.println();
    }

    private static void greet() {
        drawLine(star, lineLength);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        drawLine(star, lineLength);
    }

    private static void echo(String str) {
        drawLine(star, lineLength);
        System.out.println(str);
        drawLine(star, lineLength);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();

        while (true) {
            String inputLine = UserInput.getOneLine();
            echo(inputLine);
        }
    }
}
