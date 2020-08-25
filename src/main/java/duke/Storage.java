package duke;

import duke.task.*;

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

/**
 * Deals with all Storage operations.
 */
public class Storage {
    private Path path;
    static private File memoryFile;


    public Storage(String filePath) {
        path = Paths.get(filePath);
    }

    private List<Task> parseData(File f) throws FileNotFoundException {
        List<Task> tasklist = new ArrayList<>();

        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String str = s.nextLine();
            String[] args = str.split(" \\| ");
            boolean done = args[1].equals("1");
            String description = args[2];
            Task task;
            switch (args[0]) {
                case "T":
                    task = new Todo(description);
                    if (done) {
                        task.markAsDone();
                    }
                    tasklist.add(task);
                    break;
                case "D":
                    task = new Deadline(description, LocalDateTime.parse(args[3]));
                    if (done) {
                        task.markAsDone();
                    }
                    tasklist.add(task);
                    break;
                case "E":
                    task = new Event(description, LocalDateTime.parse(args[3]));
                    if (done) {
                        task.markAsDone();
                    }
                    tasklist.add(task);
                    break;
                default:
                    System.out.println(Ui.INDENT + "Corrupted Data Entry found : " + str);
            }
        }
        return tasklist;
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
        for(Task t : tasks) {
            fw.write(t.convertToData() + "\n");
        }
        fw.close();
    }

    /**
     * Initialise a TaskList from memory file
     *
     * @param tasklist  TaskList to be initialised.
     */
    public void initialiseTasks(List<Task> tasklist){
        System.out.print(Ui.divider);
        memoryFile = new File(path.toString());
        if (Files.exists(path)) {
            System.out.println(Ui.INDENT + "Loading Tasks from Memory...");
            try {
                List<Task> memoryList = parseData(memoryFile);
                tasklist.addAll(memoryList);
            } catch (FileNotFoundException e) {
                System.out.println(Ui.INDENT + "Error loading data.");
            }
            System.out.println(Ui.INDENT + "Load Successful!");
        } else {
            System.out.println(Ui.INDENT + "No Memory Found.");
            try {
                FileWriter fw = new FileWriter(path.toString());
                System.out.println(Ui.INDENT + "Creating new memory file..");
                fw.write("");
                fw.close();
                System.out.println(Ui.INDENT + "Done.");
            } catch (IOException e) {
                System.out.print(Ui.INDENT + e.getMessage());
            }
        }
        System.out.print(Ui.divider);
    }
}
