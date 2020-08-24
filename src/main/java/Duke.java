import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Duke {

    static String line = "____________________________________________________________";
    static String userInput = "";
    static ArrayList<Task> lst = new ArrayList<>();
    static String directoryPath = "src/main/data/";
    static String dataFilePath = "src/main/data/data.txt";
    static File dataFile;

    public static void main(String[] args) throws IOException {
        try {
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            dataFile = new File(dataFilePath);
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }

            readFile();
            dukeBot();
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    private static void readFile() {
        try {
            Scanner sc = new Scanner(dataFile);
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                if (task.startsWith("[T]")) {
                    lst.add(new ToDo(isDone(task.substring(4, 5)), task.substring(7)));
                } else if (task.startsWith("[D]")) {
                    int pos = task.indexOf("(by: ");
                    lst.add(new Deadline(isDone(task.substring(4, 5)),
                            task.substring(7, pos - 1), task.substring(pos + 5, task.length() - 1)));
                } else if (task.startsWith("[E]")) {
                    int pos = task.indexOf("(at: ");
                    lst.add(new Event(isDone(task.substring(4, 5)),
                            task.substring(7, pos - 1), task.substring(pos + 5, task.length() - 1)));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    private static boolean isDone(String symbol) {
        String tick = "" + '\u2713';
        return symbol.equals(tick);
    }

    private static void saveTaskList() {
        StringBuilder sb = new StringBuilder();
        for (Task task : lst) {
            sb.append(task);
            sb.append("\n");
        }
        try {
            writeToFile(sb.toString());
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fileWriter = new FileWriter(dataFilePath);
        fileWriter.write(textToAdd);
        fileWriter.close();
    }

    public static void dukeBot() {
        try {
            Scanner input = new Scanner(System.in);

            format("Hello! I'm Duke\nWhat can I do for you?");

            userInput = input.nextLine();
            while (!userInput.equals("bye")) {
                if (userInput.equals("list")) {
                    formatLst();
                } else if (userInput.startsWith("done")) {
                    if (userInput.substring(4).isEmpty()) {
                        throw new MissingTaskIndexException();
                    }
                    int num = Integer.parseInt(userInput.substring(5));
                    if (num <= 0 || num > lst.size()) {
                        throw new InvalidTaskIndexException();
                    }
                    formatMarkAsDone(num - 1);
                } else if (userInput.startsWith("delete")) {
                    if (userInput.substring(6).isEmpty()) {
                        throw new MissingTaskIndexException();
                    }
                    int num = Integer.parseInt(userInput.substring(7));
                    if (num <= 0 || num > lst.size()) {
                        throw new InvalidTaskIndexException();
                    }
                    formatDeleteTask(num - 1);
                } else if (userInput.startsWith("todo")) {
                    if (userInput.substring(4).isEmpty()) {
                        throw new MissingTaskDescriptionException("todo");
                    }
                    formatAddTask(new ToDo(userInput.substring(5)));
                } else if (userInput.startsWith("deadline")) {
                    if (userInput.substring(8).isEmpty()) {
                        throw new MissingTaskDescriptionException("deadline");
                    }
                    if (!userInput.contains("/by")) {
                        throw new MissingDateTimeException("deadline");
                    }
                    int pos = userInput.indexOf("/by");
                    formatAddTask(new Deadline(userInput.substring(9, pos - 1), userInput.substring(pos + 4)));
                } else if (userInput.startsWith("event")) {
                    if (userInput.substring(5).isEmpty()) {
                        throw new MissingTaskDescriptionException("event");
                    }
                    if (!userInput.contains("/at")) {
                        throw new MissingDateTimeException("event");
                    }
                    int pos = userInput.indexOf("/at");
                    formatAddTask(new Event(userInput.substring(6, pos - 1), userInput.substring(pos + 4)));
                } else {
                    throw new InvalidDukeCommandException();
                }
                userInput = input.nextLine();
            }

            format("Bye. Hope to see you again soon!");
        } catch (MissingTaskDescriptionException | InvalidDukeCommandException | MissingTaskIndexException | InvalidTaskIndexException | MissingDateTimeException e) {
            System.out.println(line + "\n" + e + "\n" + line);
        }
    }

    public static void format(String message) {
        System.out.println(line);
        System.out.println(message);
        System.out.println(line);
    }

    public static void formatLst() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= lst.size(); i++) {
            System.out.println(i + "." + lst.get(i-1));
        }
        System.out.println(line);
    }

    public static void formatMarkAsDone(int num) {
        lst.get(num).markAsDone();
        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(lst.get(num));
        System.out.println(line);
        saveTaskList();
    }

    public static void formatAddTask(Task task) {
        lst.add(task);
        int size = lst.size();
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d %s in the list.", size, size == 1 ? "task" : "tasks"));
        System.out.println(line);
        saveTaskList();
    }

    public static void formatDeleteTask(int num) {
        System.out.println(line);
        System.out.println("Noted. I've removed this task:");
        System.out.println(lst.get(num));
        lst.remove(num);
        int size = lst.size();
        System.out.println(String.format("Now you have %d %s in the list.", size, size == 1 ? "task" : "tasks"));
        System.out.println(line);
        saveTaskList();
    }
}
