import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {

    static final String GREETING = "Hello! I'm Duke";
    static final String PROMPT = "What can I do for you?";
    static final String FILE_PATH = "./data/duke.txt";


    public static void main(String[] args) {
        new Duke().repl();
    }

    public void repl() {
        Scanner sc = new Scanner(System.in);
        String input;
        List<Task> list = readFile();
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
                        String by = taskItems[1].trim();
                        Task task = new DeadlineTask(description, by);
                        new AddCommand(task, list).execute();
                        break;
                    }
                    case "event": {
                        if (remainingText == null) throw new DukeException("The description for a task cannot be empty.");
                        String taskItems[] = remainingText.split(" /at ");
                        String description = taskItems[0].trim();
                        if(taskItems.length == 1) throw new DukeException("The date for an event cannot be empty.");
                        String at = taskItems[1].trim();
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

    public static List<Task> readFile()  {
        List<Task> list = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            file.createNewFile();

            Scanner sc = new Scanner(file);

            String record;
            while (sc.hasNextLine()) {
                record = sc.nextLine().trim();
                String tokens[] = record.split("~", 3);
                String taskType = tokens[0];
                boolean isDone = Integer.parseInt(tokens[1]) == 1;
                String remainingText = tokens[2];
                String description;

                switch(taskType) {
                case "T":
                    description = remainingText;
                    list.add(new TodoTask(description, isDone));
                    break;
                case "E":
                    String eventTokens[] = remainingText.split("~");
                    description = eventTokens[0];
                    String at = eventTokens[1];
                    list.add(new EventTask(description, at, isDone));
                    break;
                case "D":
                    String deadlineTokens[] = remainingText.split("~");
                    description = deadlineTokens[0];
                    String by = deadlineTokens[1];
                    list.add(new DeadlineTask(description, by, isDone));
                    break;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            return list;
        }
    }

    public static void writeFile(List<Task> list) {
        try {
            File file = new File(FILE_PATH);
            FileWriter writer = new FileWriter(file);
            file.createNewFile();
            for(Task task: list) {
                writer.write(task.toDBString() + System.lineSeparator());
            }
            writer.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

