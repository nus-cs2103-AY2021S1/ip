package chatbot.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

import chatbot.common.Message;
import chatbot.data.Deadline;
import chatbot.data.Event;
import chatbot.data.Task;
import chatbot.data.Todo;
import chatbot.exception.ChatbotException;


/**
 * A storage class that contains methods for loading and saving tasks.
 */

public class Storage {

    private final Path location;

    public Storage(Path location) {
        this.location = location;
    }

    /**
     * Loads a list of tasks from a file on the disk. If no such file exists, create a new file.
     * @return list of tasks saved to the disk
     * @throws ChatbotException if could not load file
     */
    public ArrayList<Task> loadTasks() throws ChatbotException {
        try {

            assert location != null : "File path is not supposed to be null.";

            // File does not exist
            if (!Files.exists(location)) {
                Files.createFile(location);
                return new ArrayList<>();
            }

            // File exists, load data
            Stream<Task> taskStream = Files.lines(location).map(line -> {

                // split by separator
                String[] lineData = line.split("\\|");

                // trim and extract data
                boolean isDone = lineData[1].trim().equals("1");
                String type = lineData[0].trim();
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
            throw new ChatbotException(Message.MESSAGE_FAIL_LOAD);
        }
    }

    /**
     * Save the current tasks to disk.
     * @param taskList list of tasks
     * @return true if save is successful
     * @throws ChatbotException if save is unsuccessful
     */
    public boolean saveTasks(ArrayList<Task> taskList) throws ChatbotException {

        Iterator<Task> iter = taskList.iterator();
        String dataStr = "";

        while (iter.hasNext()) {

            String separator = " | ";

            Task tsk = iter.next();
            String timestamp = tsk.getDate() == null ? "-" : tsk.getDate().toString();
            String entry = tsk.getType() + separator
                    + tsk.getStatus() + separator
                    + tsk.getDescription()
                    + separator
                    + timestamp
                    + System.lineSeparator();

            dataStr = dataStr.concat(entry);
        }

        try {
            Files.write(location, dataStr.getBytes());
            System.out.print("Wrote to file...");
        } catch (IOException e) {
            throw new ChatbotException(Message.MESSAGE_FAIL_SAVE);
        }

        return true;
    }
}
