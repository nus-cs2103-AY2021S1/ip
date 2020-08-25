import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.Scanner;

public class InputHandler {
    private static final ArrayList<Task> TASK_LIST = new ArrayList<>();
    private static final String CMD_EXIT = "bye";
    private static final String CMD_LIST = "list";
    private static final String CMD_DONE = "done";
    private static final String CMD_DELETE = "delete";
    private static final String CMD_DUE = "due";
    private static final Path SAVE_FOLDER_PATH = Paths.get("data");
    private static final Path SAVE_FILE_PATH = Paths.get("data", "duke.txt");
    private final Scanner SC;


    public InputHandler(Scanner sc) {
        this.SC = sc;
        handleStart();
    }

    private static void handleStart() {
        String startMsg =
            generateMessage(
                "Hello! I'm Duke\n" +
                "What can I do for you?"
            );
        System.out.println(startMsg);
    }

    public void run() {
        createSaveFile();
        loadSaveFile();
        String input;
        while (!(input = SC.nextLine()).equals(CMD_EXIT)) {
            try {
                String reply = handleInput(input, true);
                System.out.println(reply);
            } catch (DukeException e) {
                System.out.println(generateMessage(e.getMessage()));
            }
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
                try {
                    handleInput(s.substring(1), false);
                    if (s.charAt(0) == '1') {
                        TASK_LIST.get(TASK_LIST.size() - 1).markAsDone();
                    }
                } catch (DukeException e) {
                    String msg = "A line in your save file seems to be formatted incorrectly!";
                    System.out.println(
                        generateMessage(msg)
                    );
                }
            });
        } catch(FileNotFoundException e) {
            System.out.println("An error has occurred when reading the save file.");
        }
    }

    private String handleInput(String in, boolean shouldSave) throws DukeException {
        String[] input = in.split(" ");
        String cmdWord = input[0];
        switch (cmdWord) {
        case (CMD_LIST):
            return handleList();
        case (CMD_DONE):
            return handleDone(Integer.parseInt(input[1]));
        case (CMD_DELETE):
            return handleDelete(in);
        case (CMD_DUE):
            return handleDue(in);
        default: // for invalid commands and adding of tasks
            return handleTask(in, cmdWord, shouldSave);
        }
    }

    private String handleDue(String in) throws InvalidCommandException {
        String dateStr = in.replaceFirst("due ", "");
        try {
            LocalDate date = DateTimeParsing.parseDate(dateStr);
            String formattedDate = DateTimeParsing.localDateToFormattedString(date);
            ArrayList<String> filteredTasks = new ArrayList<>();

            int len = TASK_LIST.size();
            for (int i = 1; i <= len; i++) {
                Task task = TASK_LIST.get(i - 1);
                if (task.isDueOn(date)) {
                    String output = i + "." + task.toString();
                    filteredTasks.add(output);
                }
            }

            String firstLine = filteredTasks.size() == 0
                    ? "There are no tasks due on " + formattedDate + "!"
                    : "These are the tasks due on " + formattedDate + ":";

            String msg = firstLine + "\n" + String.join("\n", filteredTasks);

            return generateMessage(msg);
        } catch (DateTimeParseException | NumberFormatException e) {
            String errMsg =
                "Please key in a valid date format.\n" +
                "due *yyyy-mm-dd*";
            throw new InvalidCommandException(errMsg);
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
        ArrayList<String> msgBody = new ArrayList<>();
        msgBody.add(
            len == 0
                ? "There are no tasks in your list!"
                : "Here are the tasks in your list:"
        );

        for (int i = 1; i <= len; i++) {
            Task task = TASK_LIST.get(i - 1);
            String line = i + "." + task.toString();
            msgBody.add(line);
        }
        return generateMessage(String.join("\n", msgBody));
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
        case "todo": // todo task
            return addNewTask(TaskType.Todo, in, shouldSave);
        default:
            throw new InvalidCommandException("I'm sorry, but I don't know what that means :-(");
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
            for (Task task : TASK_LIST) {
                myWriter.write(task.toSaveString() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error has occurred when updating the save file.");
        }
    }

    private String handleExit() {
        return generateMessage("Bye. Hope to see you again soon!");
    }

    private static String generateMessage(String reply) {
        String divider = "____________________________________________________________";
        return divider + "\n" + reply + "\n" + divider;
    }
}
