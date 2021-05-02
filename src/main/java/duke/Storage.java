package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.FixedDurationTask;
import duke.task.Task;
import duke.task.Todo;

/**
 * Deals with all Storage operations.
 */
public class Storage {
    private static File memoryFile;
    private Path path;
    private String directory;


    /**
     * To initialise a Storage object.
     *
     * @param directory  Directory where storage file will be stored.
     */
    public Storage(String directory) {
        this.directory = directory;
        path = Paths.get(directory + "/data.txt");
    }

    private List<Task> parseData(File f) throws FileNotFoundException {
        assert f != null : "File should not be null";
        List<Task> tasks = new ArrayList<>();

        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String str = s.nextLine();
            String[] args = str.split(" \\| ");

            // Deconstruct Task
            String taskType = args[0];
            boolean isDone = args[1].equals("1");
            String description = args[2];

            Task task;
            switch (taskType) {
            case "T":
                task = new Todo(description);
                if (isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
                break;
            case "D":
                task = new Deadline(description, LocalDateTime.parse(args[3]));
                if (isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
                break;
            case "E":
                task = new Event(description, LocalDateTime.parse(args[3]));
                if (isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
                break;
            case "FT":
                task = new FixedDurationTask(description, args[3]);
                if (isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
                break;
            default:
                assert false : "Data File has unknown types";
                System.out.println("Corrupted Data Entry found : " + str);
            }
        }
        return tasks;
    }

    /**
     * Writes given task to memory.
     *
     * @param task  Task to be saved.
     * @throws IOException  If FileWriter faces writing issues.
     */
    public void writeData(Task task) throws IOException {
        FileWriter fw = new FileWriter(path.toString(), true);
        fw.write(task.convertToData() + "\n");
        fw.close();
    }

    /**
     * Rewrites entire memory file with TaskList.
     *
     * @param tasks  TaskList to be saved.
     * @throws IOException  If FileWriter faces exceptions
     */
    public void rewriteData(List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(path.toString());
        for (Task t : tasks) {
            fw.write(t.convertToData() + "\n");
        }
        fw.close();
    }

    /**
     * Initialise a TaskList from memory file
     *
     * @param tasks  TaskList to be initialised.
     */
    public void initialiseTasks(List<Task> tasks) {
        memoryFile = new File(path.toString());
        if (Files.exists(path)) {
            System.out.println("Loading Tasks from Memory...");
            try {
                List<Task> memoryList = parseData(memoryFile);
                tasks.addAll(memoryList);
            } catch (FileNotFoundException e) {
                System.out.println("Error loading data.");
            }
            System.out.println("Load Successful!");
        } else {
            System.out.println("No Memory Found.");
            File directory = new File(this.directory);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            try {

                FileWriter fw = new FileWriter(path.toString());
                System.out.println("Creating new memory file..");
                fw.write("");
                fw.close();
                System.out.println("Done.");
            } catch (IOException e) {
                System.out.print(e.getMessage());
            }
        }
    }
}
