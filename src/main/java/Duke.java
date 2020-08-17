import java.util.*;

public class Duke {
    private String line = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private String outputFormat = "  %s\n";
    private boolean ongoing;

    Duke() {
        ongoing = false;
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
        scenarios(input);
        System.out.println("\n" + line);
    }

    public void scenarios(String input) {
        if (input.equals("bye")) {
            ongoing = false;
            goodBye();
        } else {
            echo(input);
        }
    }

    public void echo(String input) {
        System.out.printf(outputFormat, input);
    }

    public void greeting() {
        System.out.printf(outputFormat, line + "\n         (^v^)");
        System.out.printf(outputFormat, "Hey there! I'm JavaDuke");
        System.out.printf(outputFormat, "What can I do for you?\n" + line);
    }

    public void goodBye() {
        System.out.printf(outputFormat, "          *(^v^)");
        System.out.printf(outputFormat, "Bye. Hope to see you again soon!");
    }



    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.run();
    }
}
