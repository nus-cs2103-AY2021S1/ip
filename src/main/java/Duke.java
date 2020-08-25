import java.time.LocalDate;
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

            try {
                switch (command) {
                    case "list": {
                        if (remainingText != null) throw new DukeException("Did you mean to say \'list\'?");
                        new ListCommand(list).execute();
                        break;
                    }
                    case "todo": {
                        if (remainingText == null) throw new DukeException("The description for a task cannot be empty.");
                        Task task = new TodoTask(remainingText);
                        new AddCommand(task, list).execute();
                        break;
                    }
                    case "deadline": {
                        if (remainingText == null) throw new DukeException("The description for a task cannot be empty.");
                        String taskItems[] = remainingText.split(" /by ");
                        String description = taskItems[0].trim();
                        if(taskItems.length == 1) throw new DukeException("The date for a deadline cannot be empty.");
                        LocalDate by = LocalDate.parse(taskItems[1].trim());
                        Task task = new DeadlineTask(description, by);
                        new AddCommand(task, list).execute();
                        break;
                    }
                    case "event": {
                        if (remainingText == null) throw new DukeException("The description for a task cannot be empty.");
                        String taskItems[] = remainingText.split(" /at ");
                        String description = taskItems[0].trim();
                        if(taskItems.length == 1) throw new DukeException("The date for an event cannot be empty.");
                        LocalDate at = LocalDate.parse(taskItems[1].trim());
                        Task task = new EventTask(description, at);
                        new AddCommand(task, list).execute();
                        break;
                    }
                    case "done": {
                        if(remainingText == null) throw new DukeException("Please specify an item number.");
                        int index = Integer.parseInt(remainingText) - 1;
                        new DoneCommand(index, list).execute();
                        break;
                    }
                    case "delete": {
                        if(remainingText == null) throw new DukeException("Please specify an item number.");
                        int index = Integer.parseInt(remainingText) - 1;
                        new DeleteCommand(index, list).execute();
                        break;
                    }
                    case "bye": {
                        if (remainingText != null) throw new DukeException("Did you mean to say \'bye\'?");
                        new ByeCommand().execute();
                        break;
                    }
                    default: {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch(DukeException e) {
                print(e.getMessage());
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
