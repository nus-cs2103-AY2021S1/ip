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
            String remaining = arr.length == 1 ? null : arr[1];
            isActive = !input.equalsIgnoreCase("bye");
            switch(command) {
                case "list": {
                    new ListCommand(list).execute();
                    break;
                }
                case "done": {
                    int index = Integer.parseInt(remaining) - 1; // TODO: exception handling
                    new DoneCommand(index, list).execute();
                    break;
                }
                case "bye": {
                    new ByeCommand().execute();
                    break;
                }
                default: {
                    Task task = new Task(input);
                    new AddCommand(task, list).execute();
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
