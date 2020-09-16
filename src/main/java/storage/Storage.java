package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * <h1>Storage class</h1>
 * This class helps to save the data that have been inputted in
 * a different session to ensure continuity the next time users
 * run the program again.
 */
public class Storage {
    private static final String DESTINATION = "./duke.txt";
    private final Path path;

    /**
     * Creates a Storage object.
     */
    public Storage() {
        path = Paths.get(DESTINATION);
        File file = new File(DESTINATION);

        if (Files.notExists(this.path)) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Reads the data saved in a specific file containing all the previous tasks.
     * Then the method parses those data and add the old tasks to a new list.
     *
     * @return List containing all the Tasks that have been inputted previously.
     * @throws IOException
     */
    public List<Task> readData() throws IOException {
        Scanner sc = new Scanner(path.toFile());
        List<Task> list = new ArrayList<>();

        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] details = line.split(":");
            String type = details[0];
            boolean isCompleted = details[1].equals("true");
            String taskString = details[2];
            Task task;

            if (type.equals("T")) {
                task = new Todo(taskString, isCompleted, Integer.parseInt(details[3]));
            } else if (type.equals("D")) {
                task = new Deadline(taskString, details[3], isCompleted, Integer.parseInt(details[4]));
            } else {
                task = new Event(taskString, details[3], isCompleted, Integer.parseInt(details[4]));
            }

            list.add(task);
        }

        return list;
    }

    /**
     * Saves the new list of tasks to a specific file.
     *
     * @param list List containing all the current tasks that have been inputted previously.
     * @throws IOException
     */
    public void addData(List<Task> list) throws IOException {
        FileWriter fw = new FileWriter(DESTINATION);

        for (Task task : list) {

            String toAdd = "";
            String completed = String.valueOf(task.isCompleted());

            if (task instanceof Todo) {
                toAdd = "T:" + completed + ":" + task.getTask() + ":" + task.getPriority();
            } else if (task instanceof Deadline) {
                toAdd = "D:" + completed + ":" + task.getTask() + ":"
                        + ((Deadline) task).getDate() + ":" + task.getPriority();
            } else if (task instanceof Event) {
                toAdd = "E:" + completed + ":" + task.getTask() + ":"
                        + ((Event) task).getDate() + ":" + task.getPriority();
            } else {
                assert false;
            }
            fw.write(toAdd + "\n");
        }

        fw.close();
    }

    /**
     * Deletes all previous tasks inputted.
     *
     * @throws IOException
     */
    public void clear() throws IOException {
        FileWriter fw = new FileWriter(DESTINATION);
        fw.close();
    }

}
