package duke.util;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Responsible for loading and saving tasks to memory.
 */
public class Storage {
    private String dirName = "data/";
    private String filePath;

    public Storage(String filePath) {
        this.filePath = dirName + filePath;
    }

    /**
     * Loads the tasks from the storage directory.
     * 
     * @return {@code ArrayList<Task>} of the tasks stored in the storage directory.
     * @throws DukeException If the tasks were not saved in appropriate format.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> result = new ArrayList<>();
        try {
            // Create the directory if it does not exist
            File dir = new File(dirName);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File f = new File(filePath);
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String nextLine = sc.nextLine();
                String[] taskDetails = nextLine.split(" \\| ");
                Task task;
                if (taskDetails[0].equals("T")) {
                    task = new ToDo(taskDetails[2]);
                } else if (taskDetails[0].equals("D")) {
                    task = new Deadline(taskDetails[2], 
                            DateFormatter.extractTimestampInput(taskDetails[3]));
                } else {
                    task = new Event(taskDetails[2], 
                            DateFormatter.extractTimestampInput(taskDetails[3]));
                }
                
                if (taskDetails[1].equals("1")) {
                    task.completeTask();
                }
                result.add(task);
            }
        } catch (FileNotFoundException e) {
            // Create the .txt file if it does not exist
            try {
                File f = new File(filePath);
                f.createNewFile();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } 
        
        return result;
    }

    /**
     * Saves tasks to the storage directory.
     * 
     * @param tasks The tasks to save.
     */
    public void save(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(tasks.getSaveFormat());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
