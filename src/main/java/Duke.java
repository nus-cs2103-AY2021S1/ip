import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String BYE = "bye";
    private static final String SAVE_FILE_PATH =
            System.getProperty("user.home") + File.separator + ".duke" + File.separator + "tasks.txt";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Ui.displayGreeting();

            if (Duke.hasSavedTasks()) {
                TaskList.addAllTasks(Duke.loadSavedTasks());
                Ui.displayGreetingReminder(TaskList.tasksCount());
            }

            System.out.print("> ");
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase(BYE)) {
                    Duke.saveTasks(TaskList.getTasks());
                    break;
                } else {
                    Duke.handleCommand(input);
                }
                System.out.print("> ");
            }

            Ui.displayGoodbye();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO: 26/8/20 Add more relevant error for parsing
    private static Collection<Task> loadSavedTasks() throws IOException {
        List<Task> tasks = new ArrayList<>();
        try (var br = new BufferedReader(new FileReader(SAVE_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task task = TaskParser.parse(line);
                tasks.add(task);
            }
        }
        return tasks;
    }

    private static void saveTasks(List<Task> tasks) throws IOException {
        File saveFile = new File(SAVE_FILE_PATH);
        if (!saveFile.exists()) {
            boolean directoryCreated = saveFile.getParentFile().mkdirs();
            if (!directoryCreated) {
                throw new IOException("Unable to create parent directories to save file");
            }
            boolean saveFileCreated = saveFile.createNewFile();
            if (!saveFileCreated) {
                throw new IOException("Unable to create save file");
            }
        }

        // Use PrintWriter wrapping BufferedWriter in FileWriter
        try (var out = new PrintWriter(new BufferedWriter(new FileWriter(SAVE_FILE_PATH)))) {
            for (Task task : tasks) {
                out.println(task.toSaveString());
            }
        }
    }

    // TODO: 26/8/20 consider a more robust check
    private static boolean hasSavedTasks() {
        return new File(SAVE_FILE_PATH).exists();
    }

    private static void handleCommand(String input) {
        try {
            String[] tokens = input.split(" ");
            String command = tokens[0].toLowerCase();
            switch (command) {
            case "list": // show tasks available
                Ui.displayTasks(TaskList.getTasks());
                break;
            case "done": {
                if (tokens.length < 2) {
                    throw new InvalidInputException("Um, you need to tell me what it is you've done.");
                }
                int index = Integer.parseInt(tokens[1]) - 1;
                Task task = TaskList.getTask(index);
                task.markDone();
                Ui.displayMessages(
                        "Okay. So you've done:",
                        task.toString());
            }
            break;
            case "delete":
                int index = Integer.parseInt(tokens[1]) - 1;
                Task task = TaskList.getTask(index);
                TaskList.deleteTask(index);
                Ui.displayMessages(
                        "Right, you no longer want me to track:",
                        task.toString(),
                        Ui.getTasksLeftMessage(TaskList.tasksCount()));
                break;
            case "todo":
            case "deadline":
            case "event": // it's a new task
                Duke.addTask(command, input);
                break;
            default:
                Ui.displayMessages("Um, I don't get what you're saying.");
                break;
            }
        } catch (InvalidInputException e) {
            Ui.displayMessages(e.getMessage());
        }
    }

    // TODO: Consider some cleaner way of validating, perhaps a factory method for each Task
    private static void addTask(String command, String input) throws InvalidTaskException {
        Task task;
        switch (command) {
        case "todo":
            String[] todoDetails = input.split("todo ");
            if (todoDetails.length < 2) {
                throw new InvalidTaskException("😡 I have no idea what you want to do.");
            }
            String taskName = todoDetails[1];
            task = new Todo(taskName);
            break;
        case "deadline":
            String[] deadlineDetails = input.split("deadline | /by ");
            if (deadlineDetails.length < 2) {
                throw new InvalidTaskException("What is it you want to do?");
            }
            if (deadlineDetails.length < 3) {
                throw new InvalidTaskException("What's your deadline? You have to tell me, you know.");
            }
            // TODO: 26/8/20 Use parser for all Tasks
            // Ugh this interleaving of logic is disgusting
            task = TaskParser.parseDeadline(false, deadlineDetails[1], deadlineDetails[2]);
            break;
        case "event":
            String[] eventDetails = input.split("event | /at ");
            if (eventDetails.length < 2) {
                throw new InvalidTaskException("What is it you want to do?");
            }
            if (eventDetails.length < 3) {
                throw new InvalidTaskException("When do you need to do this? You have to tell me, you know.");
            }
            task = new Event(eventDetails[1], eventDetails[2]);
            break;
        default:
            throw new InvalidTaskException("Um, I don't get what you're saying.");
        }
        TaskList.addTask(task);
        Ui.displayMessages(
                "Okay, you want to:",
                task.toString(),
                Ui.getTasksLeftMessage(TaskList.tasksCount()));
    }
}
