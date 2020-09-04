package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * The Storage class deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private static final String TODO = "T";
    private static final String EVENT = "E";
    private static final String DEADLINE = "D";

    private Path path;

    /**
     * Creates and initialises a storage object.
     *
     * @param path The path where data are saved.
     */
    public Storage(Path path) {
        this.path = path;
    }

    /**
     * Returns the list of task saved in file.
     *
     * @return list of task saved in file.
     */
    public List<Task> load() {
        boolean directoryExists = java.nio.file.Files.exists(path);
        List<Task> list = new ArrayList<>();

        try {
            if (directoryExists) {
                FileReader fileReader = new FileReader(String.valueOf(path));
                BufferedReader buffReader = new BufferedReader(fileReader);
                while (buffReader.ready()) {
                    String savedTask = buffReader.readLine();
                    String type = savedTask.substring(0, 1);
                    switch (type) {
                    case TODO:
                        loadTodo(list, savedTask);
                        break;
                    case DEADLINE:
                        loadDeadline(list, savedTask);
                        break;
                    case EVENT:
                        loadEvent(list, savedTask);
                        break;
                    default:
                    }
                }
            } else {
                Files.createFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Adds data to the file.
     *
     * @param data Data to be added.
     */
    public void addData(String data) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(String.valueOf(path), true), "UTF-8"));
            bufferedWriter.write(data + "\n");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the data in file.
     *
     * @param data Data to be updated.
     * @param taskNumber Task number of the data.
     */
    public void updateData(String data, int taskNumber) {
        assert taskNumber > 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(String.valueOf(path)));
            String newData = "";
            String oldData;
            int lineNumber = 1;
            while (br.ready()) {
                oldData = br.readLine();
                if (lineNumber == taskNumber) {
                    newData += data.equals("") ? data : data + "\n";
                } else {
                    newData += oldData + "\n";
                }
                lineNumber++;
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(String.valueOf(path)));
            bw.write(newData);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTodo(List<Task> list, String savedTask) {
        list.add(new ToDo(savedTask.substring(4).trim(), savedTask.substring(2, 3).equals("T")));
    }

    private void loadDeadline(List<Task> list, String savedTask) {
        String deadlineDetails = savedTask.substring(4);
        String[] deadlineArr = deadlineDetails.split("/by");
        Deadline deadline = new Deadline(
                deadlineArr[0].trim(),
                savedTask.substring(2, 3).equals("T"),
                LocalDateTime.parse(deadlineArr[1].trim()));
        list.add(deadline);
    }

    private void loadEvent(List<Task> list, String savedTask) {
        String eventDetails = savedTask.substring(4);
        String[] eventArr = eventDetails.split("/at");
        Event event = new Event(
                eventArr[0].trim(),
                savedTask.substring(2, 3).equals("T"),
                LocalDateTime.parse(eventArr[1].trim()));
        list.add(event);
    }
}
