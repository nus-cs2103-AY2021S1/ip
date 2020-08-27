import java.nio.file.Files;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {

    private Ui ui;

    public Duke() {
        this.ui = new Ui();
    }

    // Loop through every task and transform it into a string
    public static String listToString(ArrayList<Task> taskList) {
        String taskListStr = "";
        for (Task t : taskList) {
            taskListStr += t.toStringFileFormat() + "\n";
        }
        return taskListStr;
    }

    public static void saveToFile(String output) {
        // Check if data folder exists, if not create
        Path folderPath = Paths.get("..", "..", "..", "data");
        if (!Files.exists(folderPath)) {
            File folderDir = new File(folderPath.toString());
            folderDir.mkdir();
        }

        // Get OS-independent file path to text file
        String filePath = Paths.get("..", "..", "..", "data", "Tasklist.txt")
                .toString();

        // Uncomment for testing
//        String filePath = Paths.get("..","data", "Tasklist.txt")
//                .toString();

        try {
            FileWriter myFile = new FileWriter(filePath);
            myFile.write(output); // Output is already all tasks in a string
            myFile.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<Task> readFromFile() {
        // Check if data folder exists, if not create
        Path folderPath = Paths.get("..", "..", "..", "data");
        if (!Files.exists(folderPath)) {
            File folderDir = new File(folderPath.toString());
            folderDir.mkdir();
        }

        // Initialise ArrayList to return
        ArrayList<Task> savedTasks = new ArrayList<Task>();

        // Check if file exists, if return empty list
        Path filePath = Paths.get("..", "..", "..", "data", "Tasklist.txt");
        if (!Files.exists(filePath)) {
            return savedTasks;
        }

        // Uncomment for testing
//        Path filePath = Paths.get("..", "data", "Tasklist.txt");
//        if (!Files.exists(filePath)) {
//            return savedTasks;
//        }

        try {
            File myFile = new File(filePath.toString());
            Scanner taskReader = new Scanner(myFile);

            // Keep reading new line until file end
            while (taskReader.hasNextLine()) {
                String taskString = taskReader.nextLine();

                // Only work with non empty lines
                if (taskString != "") {
                    switch (taskString.charAt(1)) {
                    case 'T':
                        boolean isDone = taskString.split("  ")[0]
                                .substring(3)
                                .equals("[Done]");
                        String description = " " + taskString.split("  ")[1];
                        Task t = new ToDo(description);
                        if (isDone) {
                            t.setDone();
                        }
                        savedTasks.add(t);
                        break;
                    case 'D':
                        isDone = taskString.split("  ")[0]
                                .substring(3)
                                .equals("[Done]");
                        description = " " + taskString.split("  ")[1]
                                .split("\\s[(]by:\\s")[0];
                        String by = taskString.split("  ")[1]
                                .split("\\s[(]by:\\s")[1];
                        by = by.substring(0, by.length() - 1); // remove parentheses at the end
                        Deadline d = new Deadline(description, by);
                        if (isDone) {
                            d.setDone();
                        }
                        savedTasks.add(d);
                        break;
                    case 'E':
                        isDone = taskString.split("  ")[0]
                                .substring(3)
                                .equals("[Done]");
                        String[] stringSplit = taskString.split("  ")[1]
                                .split("\\s[(]at:\\s");
                        description = " " + stringSplit[0];
                        String at = stringSplit[1].split(" ")[0];
                        String timeRange = stringSplit[1].split(" ")[1];
                        timeRange = timeRange.substring(0, timeRange.length() - 1); // remove parentheses at the end
                        Event e = new Event(description, at, timeRange);
                        if (isDone) {
                            e.setDone();
                        }
                        savedTasks.add(e);
                        break;
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return savedTasks;
    }

    public static boolean isEmptyInput(String input) {
        return input.isEmpty();
    }

    public static boolean isValidCommand(String input) {
        return input.toLowerCase().startsWith("todo")
                || input.toLowerCase().startsWith("deadline")
                || input.toLowerCase().startsWith("event");
    }

    public static boolean isEmptyDescription(String input) {
        return input.split(" ").length == 1;
    }

    public static boolean hasDeadlineBy(String input) {
        return input.contains("/by")
                && input.split(" /by ").length == 2;
    }

    public static boolean hasEventStartEndTime(String input) {
        return input.contains("/at")
                && input.split(" /at ").length == 2
                && input.split(" /at ")[1].split(" ").length == 2
                && input.split(" /at ")[1].split(" ")[1]
                .split("-").length == 2;
    }

    public void run() {
        ui.welcomeMessage();
        // Initialise strings to separate messages from Duke
        // and commands from CLI
//        String servantSpeak = "Duke:\n";
//        String masterSpeak = "Your Command Sire:";

//        // Introduction at the beginning of the chat
//        System.out.println(servantSpeak
//                + "    Greetings my Liege.\n"
//                + "    Why have you summoned me?\n");

        // Initialise the Scanner object to get input from user
        Scanner myObj = new Scanner(System.in);
        String input;

        // Initialise ArrayList to store tasks from user
        ArrayList<Task> userTasks;

        // Read tasks stored in hard drive if any
        userTasks = readFromFile();

        // Start chat
        while (true) {
            // Get input from user
            System.out.println(ui.getUserPrompt());
            input = myObj.nextLine().trim();
            System.out.println();

            // If user inputs "help", print list of available commands
            if (input.equals("help")) {
                ui.availableCommands();
                continue;
            }

            // If user inputs "bye" in any case, end the chat
            if (input.equals("bye")) {
                System.out.println(ui.getServantSpeak()
                        + "    It was a pleasure to serve you my Liege.\n"
                        + "    Till next time.");
                break;
            }

            // If user marks "done" mark task as isDone = true
            if (input.contains("done ")) {
                // Get the index stated after "done" by parsing the string
                int index = Integer.parseInt(input.substring(5)) - 1;

                // Mark item as done
                try {
                    if (index >= userTasks.size()) {
                        throw new DukeException("", ExceptionType.INDEX_OUT_OF_BOUNDS);
                    } else {
                        userTasks.get(index).setDone();
                        System.out.println(ui.getServantSpeak()
                                + "    As you wish Sire. I have marked this task as done:\n"
                                + "       " + userTasks.get(index).toString());
                    }

                    // Update Tasklist.txt after marking task as done
                    saveToFile(listToString(userTasks));

                } catch (DukeException ex) {
                    System.out.print(ui.getServantSpeak());
                    ui.printError(ex);
                }
                System.out.println();
                continue;
            }

            // If user requests delete, delete selected task
            if (input.contains("delete ")) {
                // Get the index stated after "delete" by parsing the string
                int index = Integer.parseInt(input.substring(7)) - 1;

                // Delete item
                try {
                    if (index >= userTasks.size()) {
                        throw new DukeException("", ExceptionType.INDEX_OUT_OF_BOUNDS);
                    } else {
                        System.out.println(ui.getServantSpeak()
                                + "    As you wish Sire. I removed this task:\n"
                                + "       " + userTasks.get(index).toString());
                        userTasks.remove(index);
                    }

                    // Update Tasklist.txt after removing task
                    saveToFile(listToString(userTasks));

                } catch (DukeException ex) {
                    System.out.print(ui.getServantSpeak());
                    ui.printError(ex);
                }
                System.out.println();
                continue;
            }

            // If user requests for list, display list of tasks
            if (input.equals("list")) {
                int count = 1;
                System.out.println(ui.getServantSpeak()
                        + "    Here are your tasks your Majesty:");
                for (Task i : userTasks) {
                    System.out.println("    "
                            + count + ". "
                            + i.toString());
                    count++;
                }
                System.out.println();
                continue;
            }

            // Check validity of input command
            try {
                if (isEmptyInput(input)) {
                    throw new DukeException("", ExceptionType.EMPTY_INPUT);
                }
                if (!isValidCommand(input)) {
                    throw new DukeException("", ExceptionType.INVALID_COMMAND);
                }
                if (isEmptyDescription(input)) {
                    throw new DukeException("", ExceptionType.EMPTY_DESCRIPTION);
                }
            } catch (DukeException ex) {
                System.out.print(ui.getServantSpeak());
                ui.printError(ex);
                continue;
            }

            // Determine what kind of task it is
            Task t;
            String[] inputSplit;
            String description;
            switch (input.toLowerCase().split(" ")[0]) {
            case "todo":
                description = input.substring(4);
                t = new ToDo(description);
                userTasks.add(t);
                break;
            case "deadline":
                try {
                    if (!hasDeadlineBy(input)) {
                        throw new DukeException("", ExceptionType.DEADLINE_NO_BY);
                    }
                    inputSplit = input.split(" /by ");
                    String by = inputSplit[1];
                    description = inputSplit[0].substring(8);
                    t = new Deadline(description, by);
                    userTasks.add(t);
                    break;
                } catch (DukeException ex) {
                    System.out.print(ui.getServantSpeak());
                    ui.printError(ex);
                    continue;
                }
            case "event":
                try {
                    if (!hasEventStartEndTime(input)) {
                        throw new DukeException("", ExceptionType.EVENT_NO_START_END);
                    }
                    inputSplit = input.split(" /at ");
                    String at = inputSplit[1].split(" ")[0];
                    String timeRange = inputSplit[1].split(" ")[1];
                    description = inputSplit[0].substring(5);
                    t = new Event(description, at, timeRange);
                    userTasks.add(t);
                    break;
                } catch (DukeException ex) {
                    System.out.print(ui.getServantSpeak());
                    ui.printError(ex);
                    continue;
                }
            }

            // Standard reply from Duke for adding a task
            System.out.println(ui.getServantSpeak()
                    + "    As you wish Sire. I have added the task:\n       "
                    + userTasks.get(userTasks.size() - 1).toString() + "\n"
                    + "    Now you have " + userTasks.size()
                    + " tasks in the list.\n");

            // Update Tasklist.txt after adding task
            saveToFile(listToString(userTasks));
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke().run();
    }
}