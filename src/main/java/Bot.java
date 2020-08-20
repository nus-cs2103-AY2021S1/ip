import java.util.Scanner;

public class Bot {
    private String name;

    public Bot(String name) {
        this.name = name;
    }

    /**
     * Start the bot's interaction with user.
     */
    public void init() {
        Scanner scanner = new Scanner(System.in);
        greet();
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String response = parseUserInput(input);
            System.out.println(response);
        }
    }

    private void greet() {
        String greeting = String.format("Hello, I'm %s. How can I help you?", name);
        System.out.println(responseWrapper(greeting));
    }

    private String responseWrapper(String str) {
        final String TEXT_LINE = "__________________________________________________";
        return TEXT_LINE + "\n    " + str + "\n" + TEXT_LINE;
    }

    private String parseUserInput(String input) {
        switch (input) {
            case "bye":
                return responseWrapper("Bye. Hope to see you again soon!");
            default:
                return responseWrapper(input);
        }
    }
}
