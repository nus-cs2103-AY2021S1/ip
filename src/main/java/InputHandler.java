import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class InputHandler {
    private static final ArrayList<Task> TASK_LIST = new ArrayList<>();
    private static final String DIVIDER = "____________________________________________________________";
    private static final String CMD_EXIT = "bye";
    private static final String CMD_LIST = "list";
    private static final String CMD_DONE = "done";
    private static final String CMD_DELETE = "delete";
    private static final Path SAVE_FOLDER_PATH = Paths.get("data");
    private static final Path SAVE_FILE_PATH = Paths.get("data", "duke.txt");
    private final Scanner SC;

    public InputHandler(Scanner sc) {
        this.SC = sc;
        handleStart();
    }

    private static void handleStart() {
        String startMsg =
            DIVIDER + "\n" +
            "Hello! I'm Duke\n" +
            "What can I do for you?\n" +
            DIVIDER;
        System.out.println(startMsg);
    }

    public void run() {
        createSaveFile();
        loadSaveFile();
        String input;
        while (!(input = SC.nextLine()).equals(CMD_EXIT)) {
            System.out.println(handleInput(input, true));
        }
        System.out.println(handleExit());
    }

    private void createSaveFile() {
        if (!java.nio.file.Files.exists(SAVE_FOLDER_PATH)) {
             new File(SAVE_FOLDER_PATH.toString()).mkdir();
        }

        if (!java.nio.file.Files.exists(SAVE_FILE_PATH)) {
            try {
                new File(SAVE_FILE_PATH.toString()).createNewFile();
            } catch(IOException e) {
                System.out.println("An error has occurred when creating the save file.");
                e.printStackTrace();
            }
        }
    }

    private void loadSaveFile() {
        try {
            BufferedReader in = new BufferedReader(new FileReader(SAVE_FILE_PATH.toString()));
            in.lines().forEach((String s) -> {
                handleInput(s.substring(1), false);
                if (s.charAt(0) == '1') {
                    TASK_LIST.get(TASK_LIST.size() - 1).markAsDone();
                }
            });
        } catch(FileNotFoundException e) {
            System.out.println("An error has occurred when reading the save file.");
            e.printStackTrace();
        }
    }

    private String handleInput(String in, boolean shouldSave) {
        String[] input = in.split(" ");
        String cmdWord = input[0];
        switch (cmdWord) {
        case (CMD_LIST):
            return handleList();
        case (CMD_DONE):
            return handleDone(Integer.parseInt(input[1]));
        case (CMD_DELETE):
            return handleDelete(in);
        default: // for invalid commands and adding of tasks
            try {
                return handleTask(in, cmdWord, shouldSave);
            } catch (DukeException e) {
                return generateMessage(e.getMessage());
            }
        }
    }

    private String handleDelete(String in) {
        try {
            int index =
                Integer.parseInt(
                    in.replaceFirst(CMD_DELETE, "").trim()
                );
            Task task = TASK_LIST.remove(index - 1);
            updateSaveFile();
            int len = TASK_LIST.size();
            return generateMessage(
                "Noted. I've removed this task:\n" +
                "  " + task.toString() + "\n" +
                "Now you have " + len + " task" + (len == 1 ? "" : "s") + " in the list."
            );
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return  generateMessage("Please input a valid index.");
        }
    }

    private String handleList() {
        int len = TASK_LIST.size();
        String firstLine = len == 0
            ? "There are no tasks in your list!"
            : "Here are the tasks in your list:\n";
        StringBuilder msgBody = new StringBuilder(firstLine);
        for (int i = 1; i <= len; i++) {
            Task task = TASK_LIST.get(i - 1);
            String line = i + "." + task.toString() + (i == len ? "" : "\n");
            msgBody.append(line);
        }
        return generateMessage(msgBody.toString());
    }

    private String handleDone(int index) {
        Task task = TASK_LIST.get(index - 1);
        task.markAsDone();
        return generateMessage("Nice! I've marked this task as done\n  " + task.toString());
    }

    private String handleTask(String in, String cmdWord, boolean shouldSave) throws DukeException {
        switch (cmdWord) {
        case "deadline":
            return addNewTask(TaskType.Deadline, in, shouldSave);
        case "event":
            return addNewTask(TaskType.Event, in, shouldSave);
        case "todo":
            return addNewTask(TaskType.Todo, in, shouldSave);
        default:
            String errMsg = "I'm sorry, but I don't know what that means :-(";
            throw new InvalidCommandException(errMsg);
        }
    }

    private String addNewTask(TaskType type, String in, boolean shouldSave) throws InvalidTaskException {
        String taskDetails =
            in.replaceFirst(type.toString().toLowerCase(), "").trim();
        Task task = Task.createTask(type, taskDetails);
        TASK_LIST.add(task);
        if (shouldSave) {
            updateSaveFile();
        }
        return handleTaskCreated(task);
    }

    private String handleTaskCreated(Task task) {
        int len = TASK_LIST.size();
        return generateMessage(
            "Got it. I've added this task: \n" +
            "  " + task.toString() + "\n" +
            "Now you have " + len + " task" + (len == 1 ? "" : "s") + " in the list."
        );
    }

    private void updateSaveFile() {
        try {
            FileWriter myWriter = new FileWriter(SAVE_FILE_PATH.toString());
            for (int i = 0; i < TASK_LIST.size(); i++) {
                Task task = TASK_LIST.get(i);
                myWriter.write(task.toSaveString() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error when updating the save file.");
            e.printStackTrace();
        }
    }

    private String handleExit() {
        return generateMessage("Bye. Hope to see you again soon!");
    }

    private String generateMessage(String reply) {
        return DIVIDER + "\n" + reply + "\n" + DIVIDER;
    }
}
