package duke.storage;

import java.util.ArrayList;
import java.util.List;

import duke.command.CommandString;
import duke.exception.DukeException;
import duke.task.Task;
import duke.tools.Format;
import duke.tools.FormatString;
import duke.tools.Time;

/**
 * Edits the file with a provided path.
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
    public void deleteLine(int lineNum) throws DukeException {
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
    public void clearFile() throws DukeException {
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
    public void setTaskDone(int lineNum) throws DukeException {
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

    /**
     * Updates the corresponding task and returns the string of the task.
     *
     * @param lineNum The lineNum of the Task shown by list command.
     * @param input The detail that the user wants to change to.
     * @return The string of the Task being updated.
     */
    public Task update(int lineNum, String command, String input) throws DukeException {
        List<String> taskStrings = readFile();
        String[] requiredTask = taskStrings.remove(lineNum - 1).split(" ");
        int len = requiredTask.length;
        StringBuilder editedTask = new StringBuilder(requiredTask[0] + " ");

        if (command.equals(CommandString.UPDATE_DETAIL)) {
            editedTask.append(input).append(" ").append(requiredTask[len - 2]).append(requiredTask[len - 1]);
        } else if (command.equals(CommandString.UPDATE_TIME)) {
            for (int i = 1; i < len - 1; i++) {
                editedTask.append(requiredTask[i]).append(" ");
            }
            Time time = new Time(input);
            editedTask.append(time.toString()).append(")");
        } else {
            editedTask = null;
        }

        assert editedTask != null : "command is neither time or detail";

        taskStrings.add(lineNum - 1, editedTask.toString());
        write(taskStrings);
        return Format.decodeTask(editedTask.toString());
    }
}
