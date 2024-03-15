import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Storage class that handles the reading and writing process from a designated text file,
 * referred to as a save file.
 */
public class Storage {
    public final String file;

    /**
     * Constructor that creates a Storage object.
     * @param file the file task sessions will be saved in.
     */
    public Storage(String file) {
        this.file = file;
    }

    /**
     * Writes the current TaskList to a save file.
     * @param taskList TaskList containing Tasks to write.
     * @param ui the Ui associated to the current Duke object.
     */
    public void saveTasks(TaskList taskList, Ui ui) {
        File savedFile = new File(file);
        boolean fileExists = savedFile.exists();
        try {
            if (fileExists) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                StringBuilder tasks = new StringBuilder();
                for (int i = 1; i <= taskList.getSize(); i++) {
                    Task task = taskList.getTask(i);
                    tasks.append(task.toSaveString());
                    tasks.append("\n");
                }
                writer.write(tasks.toString());
                writer.close();
            } else {
                boolean created = savedFile.createNewFile();
                ui.printHasCreated(created);
                saveTasks(taskList, ui);
                System.out.println("File does not exist!");
            }
        } catch (IOException e) {
            ui.printIOError(e);
        }

    }

    /**
     * Reads the save file and returns the taskList in it. Throws a DukeException
     * if the file is not found and proceeds to create one, or if a file cannot be created.
     * @param taskList the taskList that is associated with the current Duke object.
     * @param ui the ui that is associated to the current Duke object.
     */
    public void load(TaskList taskList, Ui ui) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(this.file));
            String line = br.readLine();
            while (line != null) {
                Task task = loadTasks(line);
                taskList.addTask(task);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            ui.printIOError(e);
        } catch (DukeDuplicateException e) {
            ui.printDuplicateError(e);
        }
    }

    /**
     * Creates a new Task depending on the type of Task read from the string.
     * @param line String that is read.
     * @return a new Task.
     */
    public Task loadTasks(String line) {
        String[] tasks = line.split(" \\| ");
        String task = tasks[0];
        Boolean isDone = tasks[1].equals("1");
        String desc = tasks[2];
        String date = null;
        if (tasks.length > 3) {
            date = tasks[3];
        }
        Task newTask = null;
        if (task.equals("T")) {
            newTask = new ToDos(desc, isDone);
        } else if (task.equals("E")) {
            newTask = new Events(desc, date, isDone);
        } else if (task.equals("D")) {
            newTask = new Deadline(desc, date, isDone);
        }
        return newTask;
    }
}
