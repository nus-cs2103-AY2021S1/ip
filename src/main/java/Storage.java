import java.io.*;

/**
 * Storage class is responsible to save the existing list
 * to a hardware storage. The list will auto-load when
 * user restart duke chatbot.
 */
public class Storage {

    public String path;

    /**
     * Constructor for storage.
     *
     * @param path path to the storage file.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Saves every tasks into the storage file.
     *
     * @throws IOException if storage file cannot be found.
     */
    public void saveTasks() throws IOException {
        BufferedWriter taskWriter = new BufferedWriter(new FileWriter(path));
        String tasks = "";
        for (Task task: TaskList.taskList) {
            tasks += task.toSaveString() + "\n";
        }
        taskWriter.write(tasks);
        taskWriter.close();
    }

    /**
     * Loads every tasks from the storage file to the user interface.
     *
     * @throws IOException if storage file cannot be found.
     */
    public void handleLoad() throws IOException {
        BufferedReader taskLoader = new BufferedReader(new FileReader(path));
        String longCommand = taskLoader.readLine();
        while (longCommand != null) {
            String[] keywords = longCommand.split(" \\|\\| ");
            Task cur = null;
            switch (keywords[1]) {
                case "todo":
                    cur = new Todo(keywords[2]);
                    break;
                case "deadline":
                    cur = new Deadline(keywords[2], keywords[3]);
                    break;
                case "event":
                    cur = new Event(keywords[2], keywords[3]);
                    break;
                default:
                    System.out.println("error");
                    break;
            }
            if (keywords[0].equals("1")) {
                cur.markAsDone();
            }
            TaskList.taskList.add(cur);
            longCommand = taskLoader.readLine();
        }
        taskLoader.close();
    }
}
