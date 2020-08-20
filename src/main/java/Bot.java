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
        String firstWord = firstWord(input);
        try {
            Command cmd = Command.valueOf(firstWord.toUpperCase());
            switch (cmd) {
                case LIST:
                    return cmdList();
                default:
                    return cmdAdd(input);
            }
        } catch (IllegalArgumentException e) {
            return cmdAdd(input);
        }
    }

    private String firstWord(String word) {
        String firstWord = "";
        for (char c : word.toCharArray()) {
            if (Character.isWhitespace(c)) {
                return firstWord;
            }
            firstWord += c;
        }
        return firstWord;
    }

    private String cmdAdd(String item) {
        this.taskList.add(item);
        return responseWrapper("added: " + item);
    }

    private String cmdList() {
        int index = 0;
        StringBuilder string = new StringBuilder();
        for (String item : taskList) {
            index++;
            string.append(index).append(". ").append(item).append("\n    ");
        }
        if (index == 0) {
            return responseWrapper("Nothing in the list");
        }
        string.delete(string.length() - 5, string.length());
        return responseWrapper(string.toString());
    }
}
