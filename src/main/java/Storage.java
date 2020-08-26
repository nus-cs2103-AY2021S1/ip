import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * <h1>Storage class</h1>
 * This class helps to save the data that have been inputted in
 * a different session to ensure continuity the next time users
 * run the program again.
 */
public class Storage {
    private final Path path;
    private String DESTINATION = "./duke.txt";

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
                task = new Todo(taskString, isCompleted);
            } else if (type.equals("D")) {
                task = new Deadline(taskString, details[3], isCompleted);
            } else {
                task = new Event(taskString, details[3], isCompleted);
            }

            list.add(task);
        }

        return list;
    }

    /**
     * Saves the new list of tasks to a specific file.
     * @param list List containing all the current tasks that have been inputted previously.
     * @throws IOException
     */
    public void addData(List<Task> list) throws IOException {
        FileWriter fw = new FileWriter(DESTINATION);

        for (Task task : list) {
            String toAdd = "";
            String completed = String.valueOf(task.isCompleted);

            if (task instanceof Todo) {
                toAdd = "T:" + completed + ":" + task.task;

            } else if (task instanceof Deadline) {
                toAdd = "D:" + completed + ":" + task.task + ":" + ((Deadline) task).date;

            } else if (task instanceof Event) {
                toAdd = "E:" + completed + ":" + task.task + ":" + ((Event) task).date;
            } else {
                // nothing
            }

            // System.out.println(toAdd);
            fw.write(toAdd + "\n");
        }

        fw.close();
    }

    /**
     * Resets the file.
     * @throws IOException
     */
    public void clear() throws IOException {
        FileWriter fw = new FileWriter(DESTINATION);
        fw.close();
    }

}
