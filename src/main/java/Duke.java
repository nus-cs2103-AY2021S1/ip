import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private List<Task> tasks = new ArrayList<>();

    private static String filePathName = "./data/duke.txt";
    private static Path filePath = Paths.get(filePathName);

    void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    void exit() {
        System.out.println("Bye. Hope to see you soon!");
    }

    void addTask(Task task) {
        tasks.add(task);
        try {
            writeToFile(FileWriterCommand.APPEND, task);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    void completeTask(int taskNo) {
        Task task = tasks.get(taskNo - 1);
        task.done();
        try {
            writeToFile(FileWriterCommand.EDIT, task);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    void deleteTask(int taskNo) {
        Task task = tasks.get(taskNo - 1);
        tasks.remove(task);
        try {
            writeToFile(FileWriterCommand.DELETE, task);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i - 1);
            String message = String.valueOf(i) + ".";
            message += task;
            System.out.println(message);
        }
    }

    private void getLocalData() throws FileNotFoundException {
        File f = new File(filePathName);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] task = s.nextLine().split(" \\| ");
            switch (task[0]) {
                case "T": {
                    tasks.add(new Todo(task[2], task[1].equals("1")));
                    break;
                }
                case "E": {
                    tasks.add(new Event(task[2], task[3], task[1].equals("1")));
                    break;
                }
                case "D": {
                    tasks.add(new Deadline(task[2], task[3], task[1].equals("1")));
                    break;
                }
                default:
                    break;
            }
        }
        s.close();
    }

    void loadFile() {
        // Initializes or loads file depending on whether it exists
        Path directory = Paths.get("./data/");

        try {
            if (Files.exists(filePath)) {
                // If duke.txt exists, read the file and fill task list
                getLocalData();
            } else if (Files.exists(directory)) {
                File txtFile = new File(filePathName);
                txtFile.createNewFile();
            } else {
                Files.createDirectory(directory);
                File txtFile = new File(filePathName);
                txtFile.createNewFile();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    String parseTaskAsText(Task task) {
        if(task instanceof Todo) {
            return "T | " + (task.done ? "1" : "0") + " | " + task.description;
        } else if(task instanceof Event) {
            return "E | " + (task.done ? "1" : "0") + " | " + task.description + " | " + ((Event) task).date;
        } else {
            return "D | " + (task.done ? "1" : "0") + " | " + task.description + " | " + ((Deadline) task).date;
        }
    }

    void writeToFile(FileWriterCommand command, Task task) throws IOException {
        List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
        switch(command) {
            case APPEND: {
                lines.add(parseTaskAsText(task));
                break;
            }
            case EDIT: {
                int lineNumber = tasks.indexOf(task);
                lines.set(lineNumber, parseTaskAsText(task));
                break;
            }
            case DELETE: {
                int lineNumber = lines.indexOf(parseTaskAsText(task));
                lines.remove(lineNumber);
                break;
            }
        }

        Files.write(filePath, lines, StandardCharsets.UTF_8);
    }

    void start() {
        greet();
        loadFile();

        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            try {
                Command command = Command.getValue(sc.next());
                if (command == null) {
                    throw new InvalidCommandException("Unknown command.");
                }
                switch (command) {
                    case BYE: {
                        exit();
                        isRunning = false;
                        break;
                    }
                    case LIST:
                        list();
                        break;
                    case DONE: {
                        int taskNo = sc.nextInt();
                        completeTask(taskNo);
                        break;
                    }
                    case DELETE: {
                        int taskNo = sc.nextInt();
                        deleteTask(taskNo);
                        break;
                    }
                    case TODO: {
                        String description = sc.nextLine().trim();
                        if (description.length() == 0) {
                            throw new EmptyInputException("The description of a todo cannot be empty.");
                        }
                        addTask(new Todo(description));
                        break;
                    }
                    case DEADLINE: {
                        String input = sc.nextLine().trim();
                        String[] details = input.split(" /by ");
                        if (details[0].length() == 0) {
                            throw new EmptyInputException("The description of a deadline cannot be empty.");
                        }
                        if (details.length <= 1 || details[1].length() == 0) {
                            throw new EmptyInputException("The due date of a deadline cannot be empty.");
                        }
                        addTask(new Deadline(details[0], details[1]));
                        break;
                    }
                    case EVENT: {
                        String input = sc.nextLine().trim();
                        String[] details = input.split(" /at ");
                        if (details[0].length() == 0) {
                            throw new EmptyInputException("The description of an event cannot be empty.");
                        }
                        if (details.length <= 1 || details[1].length() == 0) {
                            throw new EmptyInputException("The date of an event cannot be empty.");
                        }
                        addTask(new Event(details[0], details[1]));
                        break;
                    }
                    default:
                        throw new InvalidCommandException("Unknown command.");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
