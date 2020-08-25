import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

public class Duke {
    static List<Task> taskList = new ArrayList<>();

    static String wrapMessage(String message) {
        String line = "----------------------------------------------";
        return line + "\n " + message + "\n" + line;
    }

    static void createFile() {
        File file = new File("data/duke.txt");
        File directory = file.getParentFile();
        if (!(directory.exists())) {
            directory.mkdir();
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            wrapMessage(e.getMessage());
        }
    }

    static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        String textToAdd = "";
        for (Task task: taskList) {
            textToAdd += task.formatTaskForFile() + "\n";
        }
        fw.write(textToAdd);
        fw.close();
    }


    static void initialise() {
        File file = new File("data/duke.txt");
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                readData(s.nextLine());
            }
            s.close();
        } catch (FileNotFoundException e) {
            createFile();
        } catch (MissingDeadlineException e) {
            System.out.println(wrapMessage("The data file is formatted wrongly."));
        }
    }

    static void readData(String s) throws MissingDeadlineException {
        String[] arr = s.split(" \\| ");
        Task task;
        if (arr[0].equals("T")) {
            task = new Todo(arr[2]);
        } else if (arr[0].equals("D")) {
            task = new Deadline(arr[2] + " /by " + arr[3]);
        } else {
            task = new Event(arr[2] + " /at " + arr[3]);
        }

        if (arr[1].equals("1")) {
            task.completeTask();
        }

        taskList.add(task);
    }

    static void greet() {
        String greeting = "Hello! I'm Duke\n What can I do for you? (◠  ◠✿)";
        System.out.println(wrapMessage(greeting));
    }

    static void exit() throws IOException {
        writeToFile();
        String byeMessage = "Bye! ( ´ ▽ ` )/";
        System.out.println(wrapMessage(byeMessage));
        System.exit(0);
    }

    static Task createTask(String taskType, String desc) throws MissingDeadlineException{
        if (taskType.equals("todo")) {
            return new Todo(desc);
        } else if (taskType.equals("deadline")) {
            return new Deadline(desc);
        } else {
            return new Event(desc);
        }
    }

    static void addTask(String taskType, String desc) throws MissingDeadlineException {
        Task task = createTask(taskType, desc);
        taskList.add(task);
        String message = "Got it. I've added this task: \n  " + task +
                    "\n Now you have " + taskList.size() + " tasks in the list.";
        System.out.println(wrapMessage(message));
    }

    static String formatTask(int num) {
        String lineBreak = num != taskList.size() - 1 ? "\n  " : "";
        return (num + 1) + "." + taskList.get(num) + lineBreak;
    }

    static void list() {
        String list = "Here are the tasks in your list: \n  ";
        for (int i = 0; i < taskList.size(); i++) {
            list += formatTask(i);
        }
        System.out.println(wrapMessage(list));
    }

    static void completeTask(int num) throws MissingTaskException {
        if (num > 0 && num <= taskList.size()) {
            Task task = taskList.get(num - 1);
            task.completeTask();
            String message = "Nice! I've marked this task as done: \n  " + task;
            System.out.println(wrapMessage(message));
        } else {
            throw new MissingTaskException(num);
        }
    }

    static void deleteTask(int num) throws MissingTaskException {
        if (num > 0 && num <= taskList.size()) {
            Task task = taskList.remove(num - 1);
            String message = "Noted. I've removed this task: \n " + task +
                    "\n Now you have " + taskList.size() + " tasks in the list.";
            System.out.println(wrapMessage(message));
        } else {
            throw new MissingTaskException(num);
        }
    }

    static void readInput() {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String [] input = sc.nextLine().split(" ", 2);
            handleInput(input);
        }
    }

    static void handleInput(String[] input) {
        try {
            switch (input[0]) {
                case "list":
                    list();
                    break;
                case "bye":
                    exit();
                    break;
                case "done":
                    completeTask(Integer.valueOf(input[1]));
                    break;
                case "delete":
                    deleteTask(Integer.valueOf(input[1]));
                    break;
                case "todo":
                case "event":
                case "deadline":
                    if (input.length == 1) {
                        throw new MissingDescriptionException(input[0]);
                    } else {
                        addTask(input[0], input[1]);
                    }
                    break;
                default:
                    throw new UnknownCommandException();
            }
        } catch (DukeException | IOException e) {
            System.out.println(wrapMessage(e.toString()));
        }
    }
    public static void main(String[] args) {
        initialise();
        greet();
        readInput();
    }
}

