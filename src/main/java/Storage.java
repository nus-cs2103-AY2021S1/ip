/**
 * A class responsible for saving and loading tasks to and from the hard disk respectively.
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Storage {

    public Path path;

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

            if (!Files.exists(path)) {
                Files.createFile(path);
                return new ArrayList<>();
            }

            Stream<Task> taskStream = Files.lines(path).map(item -> {

                String[] lineData = item.split("\\|");
                String type = lineData[0].trim();
                boolean isDone = lineData[1].trim().equals("1");
                String description = lineData[2].trim();
                String time = lineData[3].trim();

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

            ArrayList<Task> tasks = new ArrayList<>();
            taskStream.forEach(tasks::add);

            return tasks;


        } catch (IOException e) {
            throw new DukeException("Unable to load tasks");
        }
    }

    /**
     * Saves the tasks to the hard disk.
     * @param taskList containing the tasks.
     * @throws DukeException with the failure to save message
     */
    public void saveTasks(ArrayList<Task> taskList) throws DukeException {

        String dataStr = "";

        for (Task tsk : taskList) {
            String timestamp = tsk.getTime() == null ? "-" : tsk.getTime().toString();
            String entry = tsk.getType() + " | " +
                    tsk.getStatus() + " | " +
                    tsk.getDescription() + " | " +
                    timestamp  +
                    System.lineSeparator();
            dataStr = dataStr.concat(entry);
        }

        try {
            Files.write(path, dataStr.getBytes());
        } catch (IOException e) {
            throw new DukeException("Unable to save the tasks.");
        }
    }


}
