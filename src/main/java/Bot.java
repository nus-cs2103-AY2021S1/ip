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
        if (input.length() == 0) {
            return responseWrapper("Please input something");
        }
        String[] words = input.split(" ");
        try {
            Command cmd = Command.valueOf(words[0].toUpperCase());
            switch (cmd) {
                case LIST:
                    return cmdList();
                case TODO:
                    return cmdTodo(words);
                case DEADLINE:
                    return cmdDeadline(words);
                case EVENT:
                    return cmdEvent(words);
                case DONE:
                    return cmdDone(words);
                default:
                    return cmdAdd(input);
            }
        } catch (IllegalArgumentException e) {
            return cmdAdd(input);
        }
    }

    private String cmdTodo(String[] words) {
        StringBuilder name = new StringBuilder();
        for (int i = 1; i < words.length; i++) {
            name.append(words[i]);
            if (i != words.length - 1) {
                name.append(" ");
            }
        }
        Todo newTodo = new Todo(name.toString());
        this.taskList.add(newTodo);
        return responseWrapper("Got it. I've added this task:\n    " +
                newTodo + "\n    " +
                "Now you have " + taskList.size() + " tasks in the list.");
    }

    private String cmdDeadline(String[] words) {
        StringBuilder name = new StringBuilder();
        StringBuilder deadline = new StringBuilder();
        Boolean deadlineWords = false;
        for (int i = 1; i < words.length; i++) {
            if (words[i].equals("/by")) {
                name.deleteCharAt(name.length() - 1);
                deadlineWords = true;
                i++;
            }
            if (deadlineWords) {
                deadline.append(words[i]);
                deadline.append(" ");
            } else {
                name.append(words[i]);
                name.append(" ");
            }
        }
        deadline.deleteCharAt(deadline.length() - 1);
        Deadline newTask = new Deadline(name.toString(), deadline.toString());
        this.taskList.add(newTask);
        return responseWrapper("Got it. I've added this task:\n    " +
                newTask + "\n    " +
                "Now you have " + taskList.size() + " tasks in the list.");
    }

    private String cmdEvent(String[] words) {
        StringBuilder name = new StringBuilder();
        StringBuilder deadline = new StringBuilder();
        Boolean deadlineWords = false;
        for (int i = 1; i < words.length; i++) {
            if (words[i].equals("/at")) {
                name.deleteCharAt(name.length() - 1);
                deadlineWords = true;
                i++;
            }
            if (deadlineWords) {
                deadline.append(words[i]);
                deadline.append(" ");
            } else {
                name.append(words[i]);
                name.append(" ");
            }
        }
        deadline.deleteCharAt(deadline.length() - 1);
        Event newTask = new Event(name.toString(), deadline.toString());
        this.taskList.add(newTask);
        return responseWrapper("Got it. I've added this task:\n    " +
                newTask + "\n    " +
                "Now you have " + taskList.size() + " tasks in the list.");
    }

    private String cmdAdd(String item) {
        Task newTask = new Task(item);
        this.taskList.add(newTask);
        return responseWrapper("added:" + newTask);
    }

    private String cmdList() {
        int index = 0;
        StringBuilder string = new StringBuilder("Here are the tasks in your list:\n    ");
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
