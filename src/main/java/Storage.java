import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

/**
 * Storage class abstracts the I/O method of
 * reading tasks from a file and writing tasks onto a file
 */
public class Storage {
    private static ArrayList<Task> task_list;

    /**
     * Constructs a Storage instance.
     */
    public Storage() {
    }

    /**
     * Loads the tasks onto a task_list from the file specified.
     *
     * @return The List of all the tasks loaded from the file.
     * @throws IOException If an I/O error occurred.
     */
    public ArrayList<Task> loadFile() throws IOException {
        task_list = new ArrayList<Task>();
        File file = getFile();
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String[] taskContent = sc.nextLine().split("\\|");
            String task_type = taskContent[0];
            Boolean task_completion = Boolean.parseBoolean(taskContent[1]);
            String description = taskContent[2];
            switch (task_type) {
                case "T":
                    Task todo = new ToDo(description);
                    if (task_completion) {
                        todo.complete();
                    }
                    task_list.add(todo);
                    break;
                case "D":
                    Task deadline = new Deadline(description, getDate(taskContent[3]), getTime(taskContent[3]));
                    if (task_completion) {
                        deadline.complete();
                    }
                    task_list.add(deadline);
                    break;
                case "E":
                    Task event = new Event(description, getDate(taskContent[3]), getTime(taskContent[3]));
                    if (task_completion) {
                        event.complete();
                    }
                    task_list.add(event);
                    break;
                default:
                    break;
            }
        }
        return task_list;
    }


    private LocalDate getDate(String s) {
        return LocalDate.parse(s.substring(3, 13));
    }

    private String getTime(String s) {
        return s.substring(14);
    }

    /**
     * Saves the List of tasks to a file
     *
     * @param task_list The list of tasks.
     * @throws IOException If an I/O error occurred.
     */
    public void createFile(ArrayList<Task> task_list) throws IOException {
        File file = getFile();
        FileWriter writer = new FileWriter(file);
        for (Task task : task_list) {
            if (task instanceof ToDo) {
                writer.write("T|" + task.task_completion + "|" + task.task_info + "\n");
            } else if (task instanceof Deadline) {
                writer.write("D|" + task.task_completion + "|" + task.task_info + "|by " + ((Deadline) task).date + " " + ((Deadline) task).time + "\n");
            } else if (task instanceof Event) {
                writer.write("E|" + task.task_completion + "|" + task.task_info + "|at " + ((Event) task).date + " " +((Event) task).time + "\n");
            }
        }
        writer.close();
    }

    /**
     * Creates a file to store the list of tasks if the file does not exist,
     * otherwise returns the existing file containing Duke's data.
     *
     * @return File, the file containing Duke's data.
     * @throws IOException If an I/O error occurred.
     */
    private File getFile() throws IOException {
        Path path = Paths.get(System.getProperty("user.dir"), "data", "duke.txt");
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
        return new File(path.toString());
    }

}

