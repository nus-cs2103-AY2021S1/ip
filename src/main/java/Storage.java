import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * A class responsible for saving and loading tasks to and from the hard disk respectively.
 */
public class Storage {

    private Path path;

    public Storage(Path path) {
        this.path = path;
    }

    /**
     * Loads the tasks from the hard disk. Creates a new file if the file doesnt exist.
     * @return the arrayList containing the tasks.
     * @throws DukeException with the failure to load message.
     */
    public ArrayList<Task> showTasks() throws DukeException {
        try {

            assert path != null : "Path cannot be null";

            if (!Files.exists(path)) {
                Files.createFile(path);
                return new ArrayList<>();
            }

            Stream<Task> taskStream = Files.lines(path).map(item -> {

                String[] dataArray = item.split("\\|");
                String type = dataArray[0].trim();
                boolean isDone = dataArray[1].trim().equals("1");
                String description = dataArray[2].trim();
                String time = dataArray[3].trim();

                Task task = null;

                switch (type) {
                case "T":
                    task = new Todo(description, isDone);
                    break;
                case "D":
                    task = new Deadline(description, isDone, LocalDate.parse(time));
                    break;
                case "E":
                    task = new Event(description, isDone, LocalDate.parse(time));
                    break;
                default:
                    break;
                }
                return task;

            });

            ArrayList<Task> taskList = new ArrayList<>();
            taskStream.forEach(taskList::add);

            return taskList;


        } catch (IOException e) {
            throw new DukeException("Unable to load tasks");
        }
    }

    /**
     * Saves the tasks to the hard disk.
     * @param taskList arrayList containing the tasks.
     * @throws DukeException Exception with the "failure to save" message
     */
    public void saveTasks(ArrayList<Task> taskList) throws DukeException {

        String message = "";

        for (Task tsk : taskList) {
            String time = tsk.getTime() == null ? "-" : tsk.getTime().toString();
            String entry = tsk.getType() + " | " +
                    tsk.getStatus() + " | " +
                    tsk.getDescription() + " | " +
                    time  +
                    System.lineSeparator();
            message = message.concat(entry);
        }

        try {
            Files.write(path, message.getBytes());
        } catch (IOException e) {
            throw new DukeException("Unable to save the tasks.");
        }
    }


}
