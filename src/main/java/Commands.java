package main.java;


import java.util.Scanner;

public class Commands {
    private boolean shouldBreak = true;


    public void start() {
        Scanner scanner = new Scanner(System.in);

        String inputs = scanner.nextLine().toLowerCase();

        while (shouldBreak) {
            switch (inputs) {
            case "list":
                this.lst();
            case "bye":
                shouldBreak = !shouldBreak;
            }
            inputs = scanner.nextLine().toLowerCase();
        }

    }

    public static void greet() {
        System.out.println("~ \n Hello I'm the Terminator \n What can I do for you? \n~");
    }

    public void lst() {
        System.out.println("~ \n list \n~");
    }
}
