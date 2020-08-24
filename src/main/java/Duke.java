package main.java;

public class Duke {

    public static void run() {
        Ui.start();
        TaskList.readFile();
        String input;
        do {
            input = Ui.feed();
            Commands.create(Parser.parse(input)).run();
        }
        while (!input.equals("bye"));
        Ui.close();
    }

    public static void main(String[] args) {
        run();
    }
}











