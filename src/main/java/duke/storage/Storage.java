package duke.storage;

import duke.task.deadline.Deadline;
import duke.task.Task;
import duke.task.eventtask.EventTask;
import duke.task.todo.ToDo;
import duke.tasklist.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Storage class that deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private final File file;
    private Scanner lineReader;

    /**
     * Constructor for creating a storage variable. It will create a new tasklist.txt if it does not exist, or
     * use existing one if it is pressent.
     *
     * @param file Contains the filepath to wear the tasklist.txt will be stored
     */
    public Storage(File file) {
        this.file = file;
        try {
            file.createNewFile();
            this.lineReader = new Scanner(file);

        } catch (FileNotFoundException e) {
            System.out.println("File is not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * If a tasklist.txt file exists, this method would load it into the TaskList.
     *
     * @return A TaskList that is loaded with the tasks recorded on the txt file
     */
    public ArrayList<Task> loadFile() {
        ArrayList<Task> shelf = new ArrayList<>();
        while (lineReader.hasNextLine()) {
            String data = lineReader.nextLine();
            shelf.add(taskCreator(data));
        }
        return shelf;
    }

    /**
     * Updates the Tasklist.txt file with the existing tasks and states in the tasklist.
     *
     * @param shelf the TaskList that we are updating
     * @throws IOException if the file cannot be found
     */
    public void updateFile(TaskList shelf) throws IOException {
        FileWriter fw = new FileWriter(file.getAbsolutePath());
        for (int i = 0; i < shelf.getSize(); i++) {
            fw.write(shelf.getTask(i).toString());
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Creates a different type of task based on the response given
     *
     * @param task input by the user
     * @return a new task child object that is the type that is indicated in the response.
     */
    private Task taskCreator(String task) {
        switch (task.charAt(1)) {
            case 'T':
                return new ToDo(task.toString());
            case 'D':
                return new Deadline(task.toString());
            default:
                return new EventTask(task.toString());
        }
    }


}
