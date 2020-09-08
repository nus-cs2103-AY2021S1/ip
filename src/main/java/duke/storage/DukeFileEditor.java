package duke.storage;

import java.util.ArrayList;
import java.util.List;

import duke.tools.FormatString;

/**
 * This class is to edit the file with a provided path.
 */
public class DukeFileEditor extends DukeFile {

    /**
     * Constructs a DukeFileEditor object.
     *
     * @param path A string which contains
     *             the directory of the file
     *             that is to be read.
     */
    public DukeFileEditor(String path) {
        super(path);
    }

    /**
     * Deletes the task recorded in the file
     * provided by the Directory class
     * with the corresponding line number.
     *
     * @param lineNum The index of task that the user want to
     *                delete.
     */
    public void deleteLine(int lineNum) {
        if (doesFileExist()) {
            List<String> taskStrings = super.readFile();
            taskStrings.remove(lineNum - 1);
            super.write(taskStrings);
        }
    }

    /**
     * Clears all tasks recorded in the file
     * with directory in the FileDirectory in Directory class.
     */
    public void clearFile() {
        List<String> newList = new ArrayList<>();
        super.write(newList);
    }

    /**
     * Sets the corresponding
     * task to be marked as done in Duke.txt.
     *
     * @param lineNum The index of task that the user want to
     *                delete.
     */
    public void setTaskDone(int lineNum) {
        if (doesFileExist()) {
            List<String> taskStrings = readFile();
            String requiredTask = taskStrings.remove(lineNum - 1);

            String editedTask = requiredTask.substring(0, 4)
                    + FormatString.TICK.toString()
                    + requiredTask.substring(5);

            taskStrings.add(lineNum - 1, editedTask);
            write(taskStrings);
        }
    }
}
