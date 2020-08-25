import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

public class Storage {

    private final TaskList taskList;

    public Storage(TaskList taskList) {
        this.taskList = taskList;
    }

    public void loadTasks(Path file) throws ChatbotException {
        try {
            Stream<Task> taskStream = Files.lines(file).map(line -> {
                // parse line
                String[] lineData = line.split("\\|");
                String type = lineData[0].trim();
                boolean isDone = lineData[1].trim().equals("1");
                String description = lineData[2].trim();
                String timestamp = lineData[3].trim();

                Task task = null;

                // re-create task
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

            // add task to task list
            ArrayList<Task> tasklist = this.taskList.getTasks();
            taskStream.forEach(tasklist::add);

        } catch (IOException e) {
            throw new ChatbotException("Ooops, I couldn't load the tasks.");
        }
    }

    public void saveTasks(Path location) throws ChatbotException {
        ArrayList<Task> tasks = taskList.getTasks();
        Iterator iter = tasks.iterator();
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
