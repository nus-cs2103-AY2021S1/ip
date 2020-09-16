import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents the storage of the tasks that a user wishes to save.
 */
public class Storage {

    private TaskList taskList;
    private String filepath;

    /**
     * Constructor of the Storage object.
     * @param filepath The filepath in which we are going to save our tasks.
     */
    public Storage(String filepath) {
        this.taskList = new TaskList();
        File file = new File(filepath);

        try {
            file.getParentFile().mkdirs();

            //Create the file if it does not exist.
            file.createNewFile();
        } catch (Exception e) {
            //Create new file throws error
            System.out.println("Error");
        }

        this.filepath = filepath;

        try {
            this.taskList.generateList(file);
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
    }

    /**
     * Gets the size of the saved tasks.
     * @return int The number of saved tasks currently in the storage.
     */
    public int getSizeofTasks() {
        return this.taskList.size();
    }

    public String getTaskType(int index) {
        return this.taskList.getTask(index).getClass().getName();
    }

    /**
     * Adds new tasks into the storage.
     * @param task The task that is to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes task in the storage.
     * @param index The index of the task that is to be deleted.
     */
    public void deleteTask(int index) {
        this.taskList.delete(index);
    }

    /**
     * Marks a task as done in the storage.
     * @param index the index of the task that is to be marked as done.
     */
    public void markDone(int index) {
        this.taskList.markDone(index);
    }

    /**
     * Returns the task at the specified index of the task list.
     * @param index The index of the task that is to be retrieved.
     * @return Task The task that is to be retrieved.
     */
    public Task getTaskFromList(int index) {
        return this.taskList.getTask(index);
    }

    public void replaceTask(int index, Task task) {
        this.taskList.changeTask(index, task);
    }

    public void changeDesc(int index, String newDesc) {
        this.taskList.changeDescription(index, newDesc);
    }

    public void changeTime(int index, LocalTime newTime) {
        this.taskList.modifyTime(index, newTime);
    }

    public void changeDate(int index, LocalDate newDate) {
        this.taskList.modifyDate(index, newDate);
    }


    private void resetFile() throws IOException {
        FileWriter fw = new FileWriter(this.filepath, false);
        fw.close();
    }

    private void rewriteFile() throws IOException {
        FileWriter fw = new FileWriter(this.filepath);
        BufferedWriter bw = new BufferedWriter(fw);

        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = this.taskList.getTask(i);
            bw.write((i + 1) + "." + task.toString());

            //Writes each task on a new line
            bw.newLine();
        }

        bw.close();
    }

    /**
     * Saves all the tasks that the user currently has.
     * @throws IOException If the file path does not exist.
     */
    public void save() throws IOException {
        resetFile();
        rewriteFile();
    }
}
