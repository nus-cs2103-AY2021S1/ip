import java.util.ArrayList;
import java.util.Scanner;

public class Bot {
    private String name;
    private ArrayList<Task> taskList;

    public Bot(String name) {
        this.name = name;
        this.taskList = new ArrayList<Task>();
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
        String[] words = input.split(" ");
        if (words.length == 0) {
            return responseWrapper("Please input something");
        }
        try {
            Command cmd = Command.valueOf(words[0].toUpperCase());
            switch (cmd) {
                case LIST:
                    return cmdList();
                case DONE:
                    return cmdDone(words);
                default:
                    return cmdAdd(input);
            }
        } catch (IllegalArgumentException e) {
            return cmdAdd(input);
        }
    }

    private String[] split(String word) {
        return word.split(" ");
    }

    private String cmdAdd(String item) {
        Task newTask = new Task(item);
        this.taskList.add(newTask);
        return responseWrapper("added:" + newTask);
    }

    private String cmdList() {
        int index = 0;
        StringBuilder string = new StringBuilder();
        for (Task item : taskList) {
            index++;
            string.append(index).append(".").append(item).append("\n    ");
        }
        if (index == 0) {
            return responseWrapper("Nothing in the list");
        }
        string.delete(string.length() - 5, string.length());
        return responseWrapper(string.toString());
    }

    private String cmdDone(String[] words) {
        if (words.length != 2) {
            return responseWrapper("Please input 1 argument");
        }
        Task task = this.taskList.get(Integer.parseInt(words[1]) - 1);
        task.markAsDone();
        return responseWrapper("Nice! I've marked this task as done: \n    " +
                task + "\n");
    }
}
