package duke.storage;

import java.util.Arrays;
import java.util.List;

import duke.exception.DukeException;
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
    public TaskList<Task> loadFile() throws DukeException {
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
     * @param strings User input.
     */
    public TaskList<Task> matchContent(String[] strings) throws DukeException {
        TaskList<Task> taskList = new TaskList<>();

        if (doesFileExist()) {
            List<String> taskStrings = readFile();
            boolean[] check = new boolean[taskStrings.size()];
            Arrays.fill(check, false);
            for (String content : strings) {
                for (int i = 0; i < taskStrings.size(); i++) {
                    if (taskStrings.get(i).contains(content) && !check[i]) {
                        check[i] = true;
                        taskList.addMemory(Format.decodeTask(taskStrings.get(i)));
                    }
                }
            }
        }

        return taskList;
    }
}
