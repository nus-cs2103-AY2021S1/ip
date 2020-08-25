package duke.main;

import java.util.Scanner;

public class Ui {
    public static final String LINE = "____________________";
    private static final String GREETING = "Hello, I'm your chatbot!\n"
            + "What can I do for you?";
    private static final String FAREWELL = "Sad to see you go!";

    private final Parser parser;
    private final Scanner scanner;

    Ui(TaskList taskList) {
        this.parser = new Parser(taskList);
        this.scanner = new Scanner(System.in);
    }

    void run() {
        System.out.println(LINE + "\n" + GREETING + "\n" + LINE);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                scanner.close();
                break;
            } else {
                String output = parser.parse(input);
                System.out.println(LINE);
                System.out.println(output);
                System.out.println(LINE);
            }
        }
        System.out.println(LINE + "\n" + FAREWELL + "\n" + LINE);
    }
}
