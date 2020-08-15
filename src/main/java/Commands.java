package main.java;


import java.util.ArrayList;
import java.util.Scanner;

public class Commands {
    private boolean shouldBreak = true;

    private final String INPUT_LIST = "list";
    private final String INPUT_BLAH = "blah";
    private final String INPUT_BYE = "bye";

    public void start() {
        this.greet();

        Scanner scanner = new Scanner(System.in);

        String inputs = scanner.nextLine().toLowerCase();

        while (shouldBreak) {
            switch (inputs) {
            case INPUT_LIST:
                this.lst();
                inputs = scanner.nextLine().toLowerCase();
                break;
            case INPUT_BLAH:
                this.blah();
                inputs = scanner.nextLine().toLowerCase();
                break;
            case INPUT_BYE:
                System.out.println("~ \n I’ll Be Back \n~ ");
                shouldBreak = !shouldBreak;
                break;
            }
        }
    }

    public void greet() {
        System.out.println("~ \n Hello I'm the Terminator \n What can I do for you? \n~");
    }

    public void lst() {
        System.out.println("~ \n list \n~");
    }

    public void blah() {
        System.out.println("~ \n blah \n~");
    }
}
