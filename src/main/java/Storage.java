import java.nio.file.Files;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.io.BufferedWriter;

import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Represents the storage of the tasks that a user wishes to save.
 */
public class Storage {

    private TaskList taskList;
    private boolean isInitialised;

    public Storage() {
        this.taskList = new TaskList();
        isInitialised = false;
    }

    /**
     * Checks if storage is initialised.
     * @return boolean Returns true if storage is initialised and false otherwise.
     */
    public boolean isInitialised() {
        return this.isInitialised;
    }

    /**
     * Gets the size of the saved tasks.
     * @return int The number of saved tasks currently in the storage.
     */
    public int getSizeofTasks() {
        return this.taskList.size();
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
    public Task getListTask(int index) {
        return this.taskList.getTask(index);
    }

    /**
     * Retrieves the file containing the saved tasks in the user's computer.
     * @throws FileNotFoundException If the file is not found.
     * @throws IOException If the parent directory does not exists.
     */
    public void initialise() throws FileNotFoundException, IOException{
        this.isInitialised = true;
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "ip", "src", "main", "java", "Data");

        boolean directoryExists = Files.exists(path);

        //checks for the directory
        if (directoryExists) {
            File file1 = new File(home + "/ip/src/main/java/Data/Duke.txt");
            File file2 = new File(home + "/ip/src/main/java/Data/Duke2.txt");

            //Checks which file is to be read
            if (file1.exists()) {
                this.taskList.generateList(file1);
            } else if (file2.exists()) {
                this.taskList.generateList(file2);
            } else {
                //if no file create a new empty file
                Path newPath = Files.createFile(Path.of(home + "/ip/src/main/java/Data/Duke.txt"));
                File newFile = new File(String.valueOf(newPath));

                this.taskList.generateList(newFile);
            }
        } else {
            throw new FileNotFoundException();
        }
    }

    /**
     * Saves the user's tasks in a file at the end of the program.
     * @throws IOException If the parent directory is not found.
     * @throws FileNotFoundException If the file is lost or deleted.
     */
    public void save() throws IOException, FileNotFoundException{

        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "ip", "src", "main", "java", "Data");
        boolean directoryExists = Files.exists(path);

        //Checks if directory is unchanged
        if (directoryExists) {
            File file1 = new File(home + "/ip/src/main/java/Data/Duke.txt");
                if (file1.exists()) {
                    //creates a new empty file
                    Files.createFile(Path.of(home + "/ip/src/main/java/Data/Duke2.txt"));
                    FileWriter fw = new FileWriter(home + "/ip/src/main/java/Data/Duke2.txt");
                    BufferedWriter bw = new BufferedWriter(fw);

                    //deletes the old file
                    Files.delete(Paths.get(home + "/ip/src/main/java/Data/Duke.txt"));

                    for (int i = 0; i < this.taskList.size(); i++) {
                        Task task = this.taskList.getTask(i);
                        bw.write((i + 1) + "." + task.toString());
                        //Writes task on a new line
                        bw.newLine();
                    }

                    bw.close();
                } else {
                    //creates a new empty file
                    Files.createFile(Path.of(home + "/ip/src/main/java/Data/Duke.txt"));
                    FileWriter fw = new FileWriter(home + "/ip/src/main/java/Data/Duke.txt");
                    BufferedWriter bw = new BufferedWriter(fw);

                    //deletes the old file
                    Files.delete(Paths.get(home + "/ip/src/main/java/Data/Duke2.txt"));

                    for (int i = 0; i < this.taskList.size(); i++) {
                        Task task = this.taskList.getTask(i);
                        bw.write((i + 1) + "." + task.toString());
                        //Writes the task on a new line
                        bw.newLine();
                    }

                    bw.close();
                }
        } else {
           throw new FileNotFoundException();
        }
    }
}
