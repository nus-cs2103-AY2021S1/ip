import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    static ArrayList<Task> taskList = new ArrayList<>();
    static private final String HOME = System.getProperty("user.home");
    static private final java.nio.file.Path PATH = java.nio.file.Paths.get(HOME, "IdeaProjects", "ip");
    static private final java.nio.file.Path FILE = java.nio.file.Paths.get(HOME, "IdeaProjects", "ip", "iPStorage");


    public static void writeTask(Task task, PrintWriter printWriter) throws IOException {
        Class taskType = task.getClass();

        if (taskType.equals(Event.class)) {
            printWriter.print("E ~ ");
        } else if (taskType.equals(Deadline.class)) {
            printWriter.print("D ~ ");
        } else {
            printWriter.print("T ~ ");
        }

        printWriter.print(task.isDone ? "1 ~ " : "0 ~ ");
        printWriter.print(task.getDescription() + "\n");

    }


    public static void saveToDisk() throws IOException {
        boolean directoryExists = java.nio.file.Files.exists(PATH);

        if (!directoryExists) {
            Files.createDirectory(PATH);
        }

        FileWriter fileWriter = new FileWriter(FILE.toFile());
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (Task task : taskList) {
            Storage.writeTask(task, printWriter);
        }
        printWriter.close();
    }

    public static void loadFromDisk() throws IOException {
        if (java.nio.file.Files.exists(FILE)) {
            Scanner scanner = new Scanner(FILE);
            while(scanner.hasNextLine()) {
                String taskData = scanner.nextLine();
                String[] taskDataDivided = taskData.split(" ~ ");
                boolean isDone = taskDataDivided[2].equals("1");
                switch (taskDataDivided[0]) {
                    case "E":
                        taskList.add(new Event(taskDataDivided[2], taskDataDivided[3], isDone));
                        break;
                    case "D":
                        taskList.add(new Deadline(taskDataDivided[2], taskDataDivided[3], isDone));
                        break;
                    case "T":
                        taskList.add(new Task(taskDataDivided[2], isDone));
                        break;
                }
            }
        }
    }
}
