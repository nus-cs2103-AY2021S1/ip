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
                inputs = scanner.nextLine().toLowerCase();
                break;
            case "blah":
                this.blah();
                inputs = scanner.nextLine().toLowerCase();
                break;
            case "bye":
                System.out.println("~ \n I’ll Be Back \n~ ");
                shouldBreak = !shouldBreak;
                break;
            }
        }
    }

    public static void greet() {
        System.out.println("~ \n Hello I'm the Terminator \n What can I do for you? \n~");
    }

    public void lst() {
        System.out.println("~ \n list \n~");
    }

    public void blah() {
        System.out.println("~ \n blah \n~");
    }
}
