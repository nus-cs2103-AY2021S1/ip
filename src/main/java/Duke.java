import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;

public class Duke {
    private static final String helloMsg = "Hello, I'm Eggy!\n" + "How may I help you?";
    private static final String byeMsg = "Bye, see you soon!";
    private static final String addTaskMsg = "Added this task to your list:\n";
    private static final String typeTask = "Please include the type of your task";

    private static final ArrayList<Task> taskList = new ArrayList<>();
    
    public static void load() throws IOException {
        File directory = new File("");
        String targetPath = directory.getAbsolutePath() + "/data/taskmanager.txt";
        java.nio.file.Path path = Path.of(targetPath);
        File file = new File(targetPath);
        boolean dir = java.nio.file.Files.exists(path);
        // if no file exists
        if (!dir) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } else {
            // if file exists, read and add to list using scanner
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String str = sc.nextLine();
                String[] command = str.split(" \\| ");
                if (command[0].equals("T")) {
                    taskList.add(new ToDo(command[2]));
                } else if (command[0].equals("E")) {
                    taskList.add(new Event(command[2], LocalDate.parse(command[3])));
                } else {
                    taskList.add(new Deadline(command[2], LocalDate.parse(command[3])));
                }
                if (command[1].equals("✓")) {
                    taskList.get(taskList.size() - 1).completeTask();
                }
            }
            sc.close();
        }
    }

    public static void write() throws IOException {
        File directory = new File("");
        FileWriter writer = new FileWriter(directory.getAbsolutePath() + "/data/taskmanager.txt");
        PrintWriter printer = new PrintWriter(writer);
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String[] command = new String[4];
            command[2] = task.description;
            if (task instanceof ToDo) {
                command[0] = "T";
            } else if (task instanceof Deadline) {
                command[0] = "D";
                command[3] = ((Deadline) task).by.toString();
            } else {
                command[0] = "E";
                command[3] = ((Event) task).at.toString();
            }
            if (!task.isDone) {
                command[1] = "✘";
            } else {
                command[1] = "✓";
            }
            String textLine = command[0] + " | " + command[1] + " | " + command[2]
                    + " | " + command[3];
            printer.printf("%s" + "%n", textLine);
        }
        printer.close();
    }
    
    public static void start() {
        try {
            load();
            System.out.println(helloMsg);
        } catch (java.io.IOException error) {
            System.out.println("Unable to load file");
        }
    }
    
    public static void operate(String command) {
        if (command.trim().equals("list")) {
            if (taskList.size() == 0) {
                System.out.println("No tasks in your list yet");
            } else {
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ": " + taskList.get(i));
                }
            }
        } else if (command.equals("done") || command.startsWith("done ")) {
            try {
                finishTask(command);
                write();
            } catch (IOException | DukeException e) {
                System.out.println(e.getMessage());
            }
        } else if (command.equals("delete") || command.startsWith("delete ")) {
            try {
                deleteTask(command);
                write();
            } catch (IOException | DukeException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                addTask(command);
                write();
            } catch (IOException | DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void addTask(String command) throws DukeException {
        Task task = null;
        if (command.equals("todo") || command.startsWith("todo ")) {
            if (command.length() <= 5 || command.substring(5).trim().isEmpty()) {
                throw new DukeException("Please enter the description of your todo");
            }
            task = new ToDo(command.substring(5));
        } else if (command.equals("deadline") || command.startsWith("deadline ")) {
            if (command.length() <= 9 || command.substring(9).trim().isEmpty()) {
                throw new DukeException("Please enter the description and deadline of your task");
            }
            String[] split = command.split("/by ");
            String description = split[0];
            LocalDate by = LocalDate.parse(split[1]);
            task = new Deadline(description, by);
        } else if (command.equals("event") || command.startsWith("event ")) {
            if (command.length() <= 6 || command.substring(6).trim().isEmpty()) {
                throw new DukeException("Please enter the description and timing of your event");
            }
            String[] split = command.split("/at ");
            String description = split[0];
            LocalDate at = LocalDate.parse(split[1]);
            task = new Event(description, at);
        } else {
            System.out.println("I don't get what you're saying :( \n" + typeTask);
        }
        taskList.add(task);
        System.out.println(addTaskMsg + task);
        if (taskList.size() > 1) {
            System.out.println("You now have " + taskList.size() + " tasks in the list!");
        } else {
            System.out.println("You now have " + taskList.size() + " task in the list!");
        }
    }
    
    public static void finishTask(String command) throws DukeException {
        String str = command.substring(5);
        int number = Integer.parseInt(str) - 1;
        if ((number < 0) || number + 1 > taskList.size()) {
            throw new DukeException("Please provide valid index of the task to be completed");
        }
        Task task = taskList.get(number);
        task.completeTask();
        System.out.println("Nice! I've marked this task as done: \n" + task);
    }
    
    public static void deleteTask(String command) throws DukeException {
        String str = command.substring(7);
        int number = Integer.parseInt(str) - 1;
        if ((number < 0) || number + 1 > taskList.size()) {
            throw new DukeException("Please provide valid index of the task to be deleted");
        }
        Task task = taskList.get(number);
        taskList.remove(task);
        System.out.println("Noted. Removed task: \n" + task);
        System.out.println("You currently have " + taskList.size() + " tasks in your list");
    }

    public static void main(String[] args) {
        start();
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            operate(command);
            command = sc.nextLine();
        }
        System.out.println(byeMsg);
    }
}