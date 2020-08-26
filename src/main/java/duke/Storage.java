package duke;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Return a list of tasks saved in local storage.
     * @return List of tasks saved in local storage.
     * @throws DukeException If there is error in the local storage file.
     */
    public List<String> load() throws DukeException {
        try {

            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            String task;
            List<String> tasks = new ArrayList<>();
            while ((task = br.readLine()) != null) {
                tasks.add(task.strip());
            }
            br.close();
            fr.close();

            return tasks;

        } catch (IOException e) {
            throw new DukeException("OOPS! I'm sorry, there is an error during initialisation :-(");
        }
    }

    /**
     * Creates new storage file for saving the tasks.
     * @throws DukeException If there is error while creating the local file.
     */
    public void newStorage() throws DukeException {
        new File("data/").mkdirs();
        try {
            new File(filepath).createNewFile();
        } catch (IOException ex) {
            throw new DukeException("OOPS! I'm sorry, there is an error during initialisation :-(");
        }
    }

    /**
     * Saves the updated task list to local storage.
     * @param taskList Task list.
     * @throws DukeException If there is error when writing to the local file.
     */
    public void saveTaskListToFile(TaskList taskList) throws DukeException {
        PrintWriter writer = initialiseWriter();
        String allTasks = taskList.getTaskListForSave();
        writer.print(allTasks);
        writer.close();
    }

    /**
     * Initialises the file writer.
     * @return File Writer to the local storage file.
     * @throws DukeException If there is error when initialising the file.
     */
    public static PrintWriter initialiseWriter() throws DukeException {
        try {
            PrintWriter writer = new PrintWriter("data/duke.TaskList.txt");
            return writer;
        } catch (IOException e) {
            throw new DukeException("OOPS! I'm sorry, there is an error during initialisation :-(");
        }
    }
}
