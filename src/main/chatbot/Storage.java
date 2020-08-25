import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

/*
    Storage class takes in a path and contains methods for loading and saving tasks.
*/

public class Storage {

    private final Path location;

    public Storage(Path location) {
        this.location = location;
    }

    public ArrayList<Task> loadTasks() throws ChatbotException {
        try {

            // File does not exist
            if (!Files.exists(location)) {
                Files.createFile(location);
                return  new ArrayList<>();
            }

            // File exists, load data
            Stream<Task> taskStream = Files.lines(location).map(line -> {

                String[] lineData = line.split("\\|");
                String type = lineData[0].trim();
                boolean isDone = lineData[1].trim().equals("1");
                String description = lineData[2].trim();
                String timestamp = lineData[3].trim();

                Task task = null;

                switch (type) {
                    case "T":
                        task = new Todo(description, isDone);
                        break;
                    case "D":
                        task = new Deadline(description, isDone, LocalDate.parse(timestamp));
                        break;
                    case "E":
                        task = new Event(description, isDone, LocalDate.parse(timestamp));
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
            throw new ChatbotException("Ooops, I couldn't load the tasks.");
        }
    }

    public void saveTasks(ArrayList<Task> taskList) throws ChatbotException {
        Iterator iter = taskList.iterator();
        String dataStr = "";
        while (iter.hasNext()) {
            Task tsk = (Task)iter.next();
            String timestamp = tsk.timestamp == null ? "-" : tsk.timestamp.toString();
            String entry = tsk.type + " | " +
                    tsk.getStatus() + " | " +
                    tsk.description + " | " +
                    timestamp  +
                    System.lineSeparator();
            dataStr = dataStr + entry;
        }
        try {
            Files.write(location, dataStr.getBytes());
        } catch (IOException e) {
            throw new ChatbotException("Oooops, I couldn't save the tasks.");
        }

    }
}
