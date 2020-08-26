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

    public ArrayList<Task> showTasks() throws DukeException {
        try {

            if (!Files.exists(path)) {
                Files.createFile(path);
                return new ArrayList<>();
            }

            Stream<Task> taskStream = Files.lines(path).map(line -> {

                String[] lineData = line.split("\\|");
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
