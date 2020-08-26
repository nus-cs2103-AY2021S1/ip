import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Storage class handles read and write operations to a specified text file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor that creates a Storage object.
     * @param filePath the filepath in which Tasks will be saved to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Updates the current Tasks in the specified text file.
     * @param taskList the TaskList which contains the Tasks to save.
     * @param ui the Ui which is currently in use.
     */
    public void save(TaskList taskList, Ui ui) {
        File savedTasks = new File(filePath);
        boolean exists = savedTasks.exists();
        try {
            if(exists) {
                BufferedWriter taskWriter = new BufferedWriter(new FileWriter(filePath));
                StringBuilder currentTasks = new StringBuilder();
                for(int i = 1; i <= taskList.size(); i++) {
                    currentTasks.append(taskList.getTask(i).encode());
                    currentTasks.append("\n");
                }
                taskWriter.write(currentTasks.toString());
                taskWriter.close();
            } else { //file does not exist, create new file
                boolean isCreated = savedTasks.createNewFile();
                if(isCreated) {
                    ui.printSuccess("New save file created");
                } else {
                    ui.printError("Failed to create save file");
                }
                save(taskList, ui);
            }
        } catch (IOException ex) {
            ui.printError(ex);
        }
    }

    /**
     * Reads the save file and updates the TaskList with the previously saved Tasks.
     * @param taskList the TaskList that is currently in use.
     * @param ui the Ui that is currently in use.
     */
    public void load(TaskList taskList, Ui ui) {
        ui.loadTasks();
        try {
            BufferedReader taskReader = new BufferedReader(new FileReader(filePath));
            String line = taskReader.readLine();
            while(line != null) {
                processTask(line, taskList, ui);
                line = taskReader.readLine();
            }
            ui.showTasks(taskList);
        } catch (FileNotFoundException e) {
            //Folder not yet created, do nothing
        } catch (IOException e) {
            ui.printError(e);
        }
    }

    /**
     * Processes the text in the save file to update the current TaskList with Tasks
     * previously saved in the text file.
     * @param line the line of text in the text file
     * @param taskList the TaskList that is currently in use.
     * @param ui the Ui that is currently in use.
     */
    private static void processTask(String line, TaskList taskList, Ui ui) {
        String[] task = line.split(" \\| ");
        Boolean isDone = task[1].equals("1");
        String taskName = task[2];
        String taskDateTime = null;
        if(task.length > 3) {
            taskDateTime = task[3];
        }
        if(line.startsWith("T")) {
            Todo newTodo = new Todo(taskName, isDone);
            taskList.addTask(newTodo);
        } else if(line.startsWith("E")) {
            Event newEvent = new Event(taskName, isDone, taskDateTime);
            taskList.addTask(newEvent);
        } else if(line.startsWith("D")) {
            Deadline newDeadline = new Deadline(taskName, isDone, taskDateTime);
            taskList.addTask(newDeadline);
        } else {
            ui.printError("Save file on device corrupted");
        }
    }
}
