import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {

    /** TaskList class that stores and deals with the tasks **/
    private TaskList taskList;
    /** Parser class that parse and deal with the commands given **/
    private Parser parser;
    /** Path to where the file is stored **/
    private Path storageFilePath;

    /**
     *Class constructor
     *
     * @throws DukeException If the file is unable to be opened
     */
    public Storage(TaskList taskList, Parser parser) throws DukeException {
        this.taskList = taskList;
        this.parser = parser;
        storageFilePath = Paths.get(".", "data", "test.txt");
        try {
            // Create directory if needed
            Path parentPath = storageFilePath.getParent();
            Files.createDirectories(parentPath);

            if (!Files.exists(storageFilePath)) {
                Files.createFile(storageFilePath);
            }

        } catch (IOException e) {
            System.out.println("unable to read file " + e.getMessage());
        }
        LoadFile();
    }

    /**
     * Open and read the stored tasks from the hard drive
     * add the stored tasks to taskList
     *
     * @throws DukeException If the file is unable to be opened
     */
    public void LoadFile() throws DukeException {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(storageFilePath.toString()));
            String task = bf.readLine();
            String[] inputs;
            while (task != null) {
                inputs = task.split(" \\| ", 4);
                String taskType = inputs[0];
                Task newTask;
                try {
                    switch (taskType) {
                        case "T": {
                            newTask = new Todo(inputs[2]);
                            break;
                        }

                        case "D": {
                            newTask = Deadline.create(inputs[2], inputs[3]);
                            break;
                        }

                        case "E": {
                            newTask = Event.create(inputs[2], inputs[3]);
                            break;
                        }

                        default: {
                            throw new DukeException("smlj??????");
                        }
                    }

                    if (inputs[1].equals("1")) {
                        newTask.complete();
                    }
                    taskList.AddTask(newTask, false);
                    task = bf.readLine();
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }

            }
        }catch(IOException e){
            throw new DukeException("unable to load file");
        }

    }

    /**
     * Write the stored tasks in taskList into a file stored in the hard drive
     *
     * @throws DukeException If the tasks are unable to be saved into the file
     */
    public void saveFile() throws DukeException{
        try {
            FileWriter fw = new FileWriter(storageFilePath.toString());
            for (int i = 0; i < taskList.getTaskListSize(); i++) {
                fw.write(taskList.getTask(i).safeFileFormat());
            }
            fw.close();
        }catch(IOException e){
            throw new DukeException("unable to save file");
        }
    }

}
