package duke;
import duke.task.TaskList;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Todo;
import duke.task.Event;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    private static String dukeFilePath;

    public static void loadFromFile(TaskList taskList) {
        try {
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();

            java.nio.file.Path path = java.nio.file.Paths.get(s, "src", "data");
            boolean directoryExists = java.nio.file.Files.exists(path);

            if(!directoryExists) {
                System.out.println("data folder does not exist. Let's create one.");
                File dir = new File(String.valueOf(path));
                dir.mkdir();
            }
            java.nio.file.Path filePath = java.nio.file.Paths.get(s, "src", "data", "duke.txt");
            dukeFilePath = String.valueOf(filePath);
            File f = new File(String.valueOf(filePath));
            if (!f.createNewFile()) {
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    String row = sc.nextLine();
                    String[] data = row.split(" {2}");
//                  System.out.println(Arrays.toString(data));
                    memoryProcessor(data, taskList);
                }
                sc.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Create new file please");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void memoryProcessor(String[] data, TaskList taskList) {
        switch (data[0]) {
            case "T":
                Todo todo = new Todo(data[2]);
                if (data[1].equals("0")) {
                    todo.markAsDone();
                }
                taskList.addTask(todo);
                break;
            case "D":
                Deadline deadline = new Deadline(data[2], data[3]);
                if (data[1].equals("0")) {
                    deadline.markAsDone();
                }
                taskList.addTask(deadline);
                break;
            case "E":
                Event event = new Event(data[2], data[3]);
                if (data[1].equals("0")) {
                    event.markAsDone();
                }
                taskList.addTask(event);
                break;
        }

    }

    public static void writeToFile(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(dukeFilePath);
            for (Task task : taskList.getTaskList()) {
                String[] data = task.taskToArray();
                if(data.length == 3) {
                    writer.write(data[0] + "  " + data[1] + "  " + data[2] + System.lineSeparator());
                } else if (data.length == 4) {
                    writer.write(data[0] + "  " + data[1] + "  " + data[2] + "  " + data[3] + System.lineSeparator());
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
