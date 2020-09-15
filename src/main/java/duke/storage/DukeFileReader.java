package duke.storage;

import java.util.List;

import duke.task.Task;
import duke.task.TaskList;
import duke.tools.Format;



/**
 * Reads the file in the path directory.
 */
public class DukeFileReader extends DukeFile {

    /**
     * Constructs a DukeFileReader object.
     *
     * @param path A string which contains
     *             the directory of the file
     *             that is to be read.
     */
    public DukeFileReader(String path) {
        super(path);
    }


    /**
     * Loads all the strings back to the Parser list as tasks.
     */
    public TaskList<Task> loadFile() {
        if (!doesFileExist()) {
            createFile();
        }

        TaskList<Task> taskList = new TaskList<>();
        List<String> taskStrings = readFile();

        for (String taskString : taskStrings) {
            taskList.addMemory(Format.decodeTask(taskString));
        }

        return taskList;
    }

    /**
     * Finds the Tasks whose detail contains
     * the content.
     *
     * @param content User input.
     */
    public TaskList<Task> matchContent(String content) {
        TaskList<Task> taskList = new TaskList<>();

        if (doesFileExist()) {
            List<String> taskStrings = readFile();
            for (String taskString : taskStrings) {
                if (taskString.contains(content)) {
                    taskList.addMemory(Format.decodeTask(taskString));
                }
            }
        }

        return taskList;
    }
}
