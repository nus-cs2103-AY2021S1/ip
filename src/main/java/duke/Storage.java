package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import duke.command.AddCommand;
import duke.parser.FileStringChecker;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Deals with loading tasks from the local file and saving tasks in the local file.
 */
public class Storage {

    /** Filepath to access the local file to read/write from/to. */
    private final String filePath;

    /**
     * Initialises the storage object with the local filepath.
     *
     * @param filePath Filepath that indicates where to access the local file.
     */
    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads command strings from the local file and decipher them to tasks that can be added to the task list.
     *
     * @return Task list with tasks loaded from the local file.
     * @throws DukeException If there are any issues with reading the commands from the file.
     */
    public TaskList load() throws DukeException {
        try {
            File file = new File(filePath);
            // Add directory if it does not exist
            file.getParentFile().mkdirs();
            if (!file.exists()) {
                file.createNewFile();
            }
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            TaskList newTaskList = new TaskList();

            while (line != null) {
                String[] splitLine = line.split("\\s*\\|\\s*");
                String checkedFileInput = new FileStringChecker(splitLine).checkFile();
                boolean isDone = splitLine[1].equals("1");

                AddCommand c = new Parser(checkedFileInput).parseFromFile(isDone);
                c.executeFromFile(newTaskList);

                line = br.readLine();
            }

            br.close();
            fr.close();
            return newTaskList;
        } catch (IOException e) {
            throw new DukeException("Sorry, there were some issues reading the file located at: " + filePath
                    + ": " + e.getMessage());
        }
    }

    /**
     * Writes the specified task list to the local file.
     *
     * @param taskList The task list to be written to file.
     * @throws DukeException If there are issues writing to the local file.
     */
    public void write(TaskList taskList) throws DukeException {
        try {
            File file = new File(filePath);
            // Add directory if it does not exist
            file.getParentFile().mkdirs();
            FileWriter fw;

            if (file.exists()) {
                fw = new FileWriter(file, false);
            } else {
                fw = new FileWriter(file, true);
            }

            for (Task i : taskList.getTaskList()) {
                fw.write(i.appendFile() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Sorry, there were some issues writing to the file located at: " + filePath
                    + ": " + e.getMessage());
        }
    }
}
