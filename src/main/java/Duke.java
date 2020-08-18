import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {

    static final String GREETING = "Hello! I'm Duke";
    static final String PROMPT = "What can I do for you?";


    public static void main(String[] args) {
        new Duke().repl();
    }

    public void repl() {
        Scanner sc = new Scanner(System.in);
        String input;
        List<Task> list = new ArrayList<>();
        boolean isActive = true;

        print(GREETING, PROMPT);
        while(isActive) {
            input = sc.nextLine().trim();
            String arr[] = input.split(" ", 2);
            String command = arr[0];
            String remainingText = arr.length == 1 ? null : arr[1].trim();
            isActive = !input.equalsIgnoreCase("bye");
            switch(command) {
                case "list": {
                    new ListCommand(list).execute();
                    break;
                }
                case "todo": {
                    Task task = new TodoTask(remainingText); // TODO: exception handling
                    new AddCommand(task, list).execute();
                    break;
                }
                case "deadline": {
                    String taskItems[] = remainingText.split(" /by ");
                    String description = taskItems[0];
                    String by = taskItems[1];
                    Task task = new DeadlineTask(description, by);
                    new AddCommand(task, list).execute();
                    break;
                }
                case "event": {
                    String taskItems[] = remainingText.split(" /at ");
                    String description = taskItems[0];
                    String at = taskItems[1];
                    Task task = new EventTask(description, at);
                    new AddCommand(task, list).execute();
                    break;
                }
                case "done": {
                    int index = Integer.parseInt(remainingText) - 1; // TODO: exception handling
                    new DoneCommand(index, list).execute();
                    break;
                }
                case "bye": {
                    new ByeCommand().execute();
                    break;
                }
                default: {
                    print("☹ OOPS!!! I'm sorry, but I don't know what that means :-("); // TODO: exception handling
                }
            }
        }
    }

    public static void print(List<String> strings) {
        final String INDENT = "\t";
        final String SEPARATOR = "_".repeat(69);

        System.out.println(INDENT + SEPARATOR);
        for(String s: strings) {
            System.out.println(INDENT + INDENT + s);
        }
        System.out.println(INDENT + SEPARATOR + "\n");
    }

    public static void print(String ...strings) {
        print(Arrays.asList(strings));
    }
}
