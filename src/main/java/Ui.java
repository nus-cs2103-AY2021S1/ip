import java.util.Scanner;

public class Ui {
    private String line = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private String outputFormat = "  %s\n";
    private boolean ongoing;
    private Parser parser;

    Ui(Parser parser) {
        ongoing = false;
        this.parser = parser;
    }

    public void run() {
        ongoing = true;
        greeting();
        Scanner scanner = new Scanner(System.in);
        while (ongoing) {
            String input = scanner.nextLine();
            systemOutput(input);
        }
    }

    public void systemOutput(String input) {
        System.out.println(line + "\n");
        try {
            String output = parser.scenarios(input);
            if (output.equals("bye")) {
                ongoing = false;
                goodBye();
            } else {
                System.out.println(output);
            }
        } catch (DukeException e) {
            System.out.printf(outputFormat, e.getMessage());
        }
        System.out.println("\n" + line);
    }

    public void greeting() {
        System.out.println(line + "\n         (^v^)");
        System.out.printf(outputFormat, "Hey there! I'm JavaDuke");
        System.out.printf(outputFormat, "What can I do for you?\n" + line);
    }

    public void goodBye() {
        System.out.printf(outputFormat, "          *(^v^)");
        System.out.printf(outputFormat, "Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {
        System.out.println("An error occurred while loading the task list. Please rerun the program again.");
    }
}
