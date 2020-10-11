package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.model.task.Deadline;
import duke.model.task.Event;
import duke.model.task.Task;
import duke.model.task.ToDo;

/**
 * Handles the saving and loading of tasks.
 */
public class Storage {
    private String saveFilePath;

    /**
     * Constructor for Storage class.
     *
     * @param saveFilePath Location of save file.
     */
    public Storage(String saveFilePath) {
        this.saveFilePath = saveFilePath;
    }

    /**
     * Saves the current ArrayList of tasks to disk.
     *
     * @param taskList ArrayList of Tasks to save.
     * @throws IOException If unable to write save file.
     */
    public void save(ArrayList<Task> taskList) throws IOException {
        File saveFile = new File(saveFilePath);
        if (!saveFile.exists()) {
            saveFile.getParentFile().mkdirs();
            saveFile.createNewFile();
        }
        FileWriter fw = new FileWriter(saveFilePath);
        for (Task task : taskList) {
            fw.write(task.toDataString() + "\n");
        }
        fw.close();
    }

    /**
     * Loads saved tasks from disk.
     *
     * @return ArrayList of Tasks.
     * @throws IOException If unable to access or find the save file.
     */
    public ArrayList<Task> load() throws IOException, DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(saveFilePath));
        String line;
        while ((line = br.readLine()) != null) {
            String[] taskLine = line.split("\\|");
            switch (taskLine[0]) {
            case("T"):
                ToDo todo = new ToDo(taskLine[2], Boolean.parseBoolean(taskLine[1]));
                taskList.add(todo);
                break;
            case("D"):
                LocalDate deadlineDate = LocalDate.parse(taskLine[3]);
                Deadline deadline = new Deadline(
                        taskLine[2], Boolean.parseBoolean(taskLine[1]), deadlineDate);
                taskList.add(deadline);
                break;
            case("E"):
                LocalDate eventDate = LocalDate.parse(taskLine[3]);
                Event event = new Event(taskLine[2], Boolean.parseBoolean(taskLine[1]), eventDate);
                taskList.add(event);
                break;
            default:
                throw new DukeException("Error parsing save file.");
            }
        }
        return taskList;
    }
}
