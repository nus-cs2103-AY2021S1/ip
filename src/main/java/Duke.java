import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String LOGO =
            "██████╗ ███████╗███╗   ██╗███████╗██████╗ ██╗ ██████╗████████╗\n"
                    + "██╔══██╗██╔════╝████╗  ██║██╔════╝██╔══██╗██║██╔════╝╚══██╔══╝\n"
                    + "██████╔╝█████╗  ██╔██╗ ██║█████╗  ██║  ██║██║██║        ██║\n"
                    + "██╔══██╗██╔══╝  ██║╚██╗██║██╔══╝  ██║  ██║██║██║        ██║\n"
                    + "██████╔╝███████╗██║ ╚████║███████╗██████╔╝██║╚██████╗   ██║\n"
                    + "╚═════╝ ╚══════╝╚═╝  ╚═══╝╚══════╝╚═════╝ ╚═╝ ╚═════╝   ╚═╝\n";
    private static final String BYE = "bye";
    private static final String GOODBYE_MESSAGE = "Ok lor like that lor.";
    private static final String SAVE_FILE_PATH =
            System.getProperty("user.home") + File.separator + ".duke" + File.separator + "tasks.txt";

    private static final List<Task> TASKS = new ArrayList<>();

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Hi, I'm\n" + LOGO);
            if (Duke.hasSavedTasks()) {
                TASKS.addAll(Duke.loadSavedTasks());
                Duke.displayMessages(
                        "Don't forget you already have " + TASKS.size() + " things to do.",
                        "But okay.");
            }
            Duke.displayMessages("What do you need this time 😫");

            System.out.print("> ");
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase(BYE)) {
                    Duke.saveTasks(TASKS);
                    break;
                } else {
                    Duke.handleCommand(input);
                }
                System.out.print("> ");
            }

            Duke.displayMessages(GOODBYE_MESSAGE);
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
                Duke.displayTasks();
                break;
            case "done": {
                if (tokens.length < 2) {
                    throw new InvalidInputException("Um, you need to tell me what it is you've done.");
                }
                int index = Integer.parseInt(tokens[1]) - 1;
                Task task = TASKS.get(index);
                task.markDone();
                Duke.displayMessages(
                        "Okay. So you've done:",
                        task.toString());
            }
            break;
            case "delete":
                int index = Integer.parseInt(tokens[1]) - 1;
                Task task = TASKS.get(index);
                TASKS.remove(index);
                Duke.displayMessages(
                        "Right, you no longer want me to track:",
                        task.toString(),
                        Duke.getTasksLeftMessage());
                break;
            case "todo":
            case "deadline":
            case "event": // it's a new task
                Duke.addTask(command, input);
                break;
            default:
                Duke.displayMessages("Um, I don't get what you're saying.");
                break;
            }
        } catch (InvalidInputException e) {
            Duke.displayMessages(e.getMessage());
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
            task = new TodoTask(taskName);
            break;
        case "deadline":
            String[] deadlineDetails = input.split("deadline | /by ");
            if (deadlineDetails.length < 2) {
                throw new InvalidTaskException("What is it you want to do?");
            }
            if (deadlineDetails.length < 3) {
                throw new InvalidTaskException("What's your deadline? You have to tell me, you know.");
            }
            task = new DeadlineTask(deadlineDetails[1], deadlineDetails[2]);
            break;
        case "event":
            String[] eventDetails = input.split("event | /at ");
            if (eventDetails.length < 2) {
                throw new InvalidTaskException("What is it you want to do?");
            }
            if (eventDetails.length < 3) {
                throw new InvalidTaskException("When do you need to do this? You have to tell me, you know.");
            }
            task = new EventTask(eventDetails[1], eventDetails[2]);
            break;
        default:
            throw new InvalidTaskException("Um, I don't get what you're saying.");
        }
        TASKS.add(task);
        Duke.displayMessages(
                "Okay, you want to:",
                task.toString(),
                Duke.getTasksLeftMessage());
    }

    private static String getTasksLeftMessage() {
        return String.format(
                "Now you have %d thing%s you'need me to remind you about.",
                TASKS.size(),
                TASKS.size() == 1 ? "" : "s");
    }

    private static void displayTasks() {
        if (TASKS.size() == 0) {
            Duke.displayMessages("You didn't tell me to remind you anything.");
        } else {
            String[] messages = new String[TASKS.size() + 1];
            messages[0] = "Right, you said you wanted to:";

            for (int i = 0; i < TASKS.size(); i++) {
                messages[i + 1] = String.format("%3d: %s", i + 1, TASKS.get(i));
            }

            Duke.displayMessages(messages);
        }
    }

    private static void displayMessages(String... messages) {
        System.out.println("     ――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
        for (String message : messages) {
            System.out.printf("     %s\n", message);
        }
        System.out.println("     ――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
        System.out.println();
    }
}
