package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    // ArrayList to store task
    public static ArrayList<Task> STORAGE = new ArrayList<>();

    // Method to convert task from hard disk
    public static Task convertFromHardDisk(String s) {
        String[] data = s.split(" / ");
        String taskType = data[0];
        boolean isDone = data[1].equals("1");
        String description = data[2];
        Task task;
        if (taskType.equals("T")) {
            task = new Todo(description);
        } else if (taskType.equals("D")) {
            String date = data[3];
            task = new Deadline(description, date);
        } else {
            String date = data[3];
            task = new Event(description, date);
        }
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    // Method to convert task to hard disk
    public static String convertToHarDisk(Task t) {
        String[] info = t.getInfo();
        String taskType = info[0];
        String description = info[1];
        if (taskType.equals("T")) {
            return taskType + " / " + (t.isDone() ? "1" : "0") + " / " + description;
        } else if (taskType.equals("D")) {
            return taskType + " / " + (t.isDone() ? "1" : "0") + " / " + description + " / " + info[2];
        } else {
            return taskType + " / " + (t.isDone() ? "1" : "0") + " / " + description + " / " + info[2];
        }
    }

    // Method to write all task to hard disk
    public static void writeToHardDisk() {
        String projectRoot = System.getProperty("user.dir");
        Path p2 = Paths.get(projectRoot, "data", "task.txt");
        try {
            FileWriter fw = new FileWriter(p2.toString());
            StringBuilder text = new StringBuilder();
            for (Task t : STORAGE) {
                text.append(convertToHarDisk(t)).append("\n");
            }
            fw.write(text.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        // Create horizontal line.
        StringBuilder line = new StringBuilder();
        line.append("_".repeat(55));

        // Print greeting message.
        System.out.println(line + "\n" + " Hey there! I am Popi" + "\n"
            + " How can I help you?" + "\n" + line + "\n");

        // Create paths for the file
        String projectRoot = System.getProperty("user.dir");
        // p1 is used for creating directory
        Path p1 = Paths.get(projectRoot, "data");
        Path p2 = Paths.get(projectRoot, "data", "task.txt");
        String path = p2.toString();
        File data = new File(path);

        // Make directory if it does not exist
        if (!Files.exists(p1)) {
            File temp = new File(p1.toString());
            boolean created = temp.mkdir();
            if (!created) {
                System.out.println("Cannot create directory for storage file! List will not" + "\n" +
                    "be saved until the directory and the file are created.");
            }
        }

        // Input data to storage
        try {
            Scanner sc = new Scanner(data);
            while (sc.hasNextLine()) {
                // Convert line to task, then add to storage
                STORAGE.add(convertFromHardDisk(sc.nextLine()));
            }
        } catch (FileNotFoundException e) {
            try {
                // Create file if it does not exist
                /* The case for file does not exist is handled separately so that the
                 * user does not need to delete the entire folder.
                 */
                boolean created = data.createNewFile();
                if (!created) {
                    System.out.println("Cannot create storage file! List will not be saved until the " +
                        "file is created");
                }
            } catch (IOException x) {
                x.printStackTrace();
            }
        }

        // Process user input
        Scanner sc = new Scanner(System.in);
        label:
        while (true) {
            String input = sc.nextLine();
            // Check input
            try {
                checkInput(input);
            } catch (UnknownInputException | TodoIncompleteException | EventIncompleteException
                | DeadlineIncompleteException | DoneIncompleteException | NoInputException
                    | DoneOutOfListException | DeleteIncompleteException | DeleteOutOfListException e) {
                System.out.println(line + "\n" + e.getMessage() + "\n" + line + "\n" + " ");
                continue;
            }
            // Split string for command purposes
            String[] s = input.split(" ");
            switch (s[0]) {
            case "bye":
                System.out.println(line + "\n" + " Bye! Hope to see you again in the future!"
                    + "\n" + line + "\n");
                break label;
            case "list":
                System.out.println(line);
                System.out.println(" These are the tasks in your list:");
                for (int i = 0; i < STORAGE.size(); i++) {
                    Task t = STORAGE.get(i);
                    System.out.println(" " + (i + 1) + "." + t.toString());
                }
                System.out.println(line);
                break;
            case "done": {
                Task t = STORAGE.get(Integer.parseInt(s[1]) - 1);
                t.markAsDone();
                System.out.println(line + "\n" + " Yay! I have marked this task as done: " + "\n"
                    + "   " + t.toString() + "\n" + line);
                break;
            }
            case "delete": {
                Task t = STORAGE.get(Integer.parseInt(s[1]) - 1);
                STORAGE.remove(Integer.parseInt(s[1]) - 1);
                System.out.println(line + "\n" + " Okie! I have deleted this task: " + "\n"
                    + "   " + t.toString() + "\n" + " Now you have " + STORAGE.size() + (STORAGE.size() > 1
                    ? " tasks." : " task.") + "\n" + line);
                break;
            }
            default: {
                Task t;
                if (s[0].equals("event")) {
                    // Split string to get date
                    String[] str = input.split(" /at ");
                    // Ignore task type
                    String description = str[0].substring(6);
                    String date = str[1];
                    t = new Event(description, date);
                } else if (s[0].equals("deadline")) {
                    // Split string to get date
                    String[] str = input.split(" /by ");
                    // Ignore task type
                    String description = str[0].substring(9);
                    String date = str[1];
                    t = new Deadline(description, date);
                } else {
                    t = new Todo(input.substring(5));
                }
                STORAGE.add(t);
                System.out.println(line + "\n" + " Okay! I have added this task:" + "\n" + "   "
                    + t.toString() + "\n" + " Now you have " + STORAGE.size() + (STORAGE.size() > 1 ? " tasks."
                    : " task.") + "\n" + line);
                break;
                }
            }
            writeToHardDisk();
        }
    }

    // Method to check user input
    public static void checkInput(String line) throws UnknownInputException, TodoIncompleteException
        ,EventIncompleteException, DeadlineIncompleteException, DoneIncompleteException, NoInputException
            ,DoneOutOfListException, DeleteIncompleteException, DeleteOutOfListException {

        // Store all valid commands
        ArrayList<String> validCommand = new ArrayList<>();
        validCommand.add("list");
        validCommand.add("done");
        validCommand.add("deadline");
        validCommand.add("event");
        validCommand.add("todo");
        validCommand.add("delete");
        validCommand.add("bye");
        String[] input = line.split(" ");

        String command = input[0];
        if (command.equals("")) {
            throw new NoInputException();
        } else if (!validCommand.contains(input[0])) {
            throw new UnknownInputException();
        } else if (input.length == 1) {
            switch (command) {
            case "done":
                throw new DoneIncompleteException();
            case "deadline":
                throw new DeadlineIncompleteException();
            case "event":
                throw new EventIncompleteException();
            case "todo":
                throw new TodoIncompleteException();
            case "delete":
                throw new DeleteIncompleteException();
            }
        } else if (command.equals("done")) {
            if (Integer.parseInt(input[1]) < 1) {
                throw new DoneOutOfListException();
            }
        } else if (command.equals("delete")) {
            if (Integer.parseInt(input[1]) < 1) {
                throw new DeleteOutOfListException();
            }
        }
    }
}
