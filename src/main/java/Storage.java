import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {

    private TaskList taskList;
    private Path storageFilePath;

    public Storage(TaskList taskList) throws DukeException {

        this.taskList = taskList;
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

    public void saveFile() throws DukeException{
        try {
            FileWriter fw = new FileWriter(storageFilePath.toString());
            for (int i = 0; i < taskList.getTaskListSize(); i++) {
                fw.write(taskList.getTask(i).safeFileFormat());
            }
            fw.close();
        } catch(IOException e) {
            throw new DukeException("unable to save file");
        }
    }

}
