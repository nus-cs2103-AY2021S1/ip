import java.util.ArrayList;
import java.util.Scanner;

public class Bot {
    private String name;
    private ArrayList<String> taskList;

    public Bot(String name) {
        this.name = name;
        this.taskList = new ArrayList<String>();
    }

    /**
     * Start the bot's interaction with user.
     */
    public void init() {
        Scanner scanner = new Scanner(System.in);
        greet();
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                String farewell = responseWrapper("Bye. Hope to see you again soon!");
                System.out.println(farewell);
                return;
            }
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
            case "list":
                return cmdList();
            default:
                return cmdAdd(input);

        }
    }

    private String cmdAdd(String item) {
        this.taskList.add(item);
        return responseWrapper("added: " + item);
    }

    private String cmdList() {
        int index = 1;
        StringBuilder string = new StringBuilder();
        for (String item : taskList) {
            string.append(index).append(". ").append(item).append("\n    ");
            index++;
        }
        string.delete(string.length() - 6, string.length());
        return responseWrapper(string.toString());
    }
}
