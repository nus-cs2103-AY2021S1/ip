package duke;

import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * Handles storage, reading, and writing of tasks to .txt file.
 */
public class Storage {
    private File file;

    /**
     * Instantiates a new storage object located at the input filePath string.
     *
     * @param filePath location of storage file.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Reads the tasks from the storage file, and returns a TaskList object
     * containing the tasks.
     *
     * @return TaskList containing tasks in storage file.
     * @throws DukeException if storage file not found.
     */
    public TaskList loadList() throws DukeException {
        try {
            TaskList list = new TaskList();
            //this.file = new File("../data/duke.txt"); // for testcase running
            this.file = new File("./data/duke.txt");
            Scanner sc = new Scanner(this.file);
            while (sc.hasNext()) { // e.g. deadline, 1, description/by date
                String[] arr = sc.nextLine().split(", ");
                if (arr.length == 3) {
                    list.addTask(arr[0], arr[2], arr[1].equals("1"));
                } else {
                    throw new DukeException("Invalid text format in file.");
                }
            }
            return list;
        } catch (FileNotFoundException e) {
            throw new DukeException("Sorry, " + e.getMessage());
        }
    }

    /**
     * Overwrites the .txt file to contain the tasks in the input TaskList object.
     *
     * @throws DukeException if file cannot be found / created / opened.
     */
    public void updateTextFile(TaskList list) throws DukeException {
        try {
            StringBuilder text = new StringBuilder();
            FileWriter fw = new FileWriter(this.file);
            for (Task task : list) {
                text.append(task.getTxtFormat());
                text.append("\n");
            }
            fw.write(text.toString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Sorry, " + e.getMessage());
        }
    }
}
