package luke;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import luke.exception.LukeException;
import luke.task.Task;

public class Storage {
    private final String filePath;

    public Storage (String filePath) {
        this.filePath = filePath;
        // what if filePath is corrupted?
    }

    /**
     * Reads task list from the file.
     *
     * @return Latest task list.
     */
    public List<Task> load() throws LukeException {
        // if data folder does not exist
        // create a new folder

        // if file does not exist
        // create a new file
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String readLine = null;

            while((readLine = br.readLine()) != null){
                Task parsedTask = Parser.parseTask(readLine);
                tasks.add(parsedTask);
            }
        } catch (IOException e) {
           throw new LukeException(e.getMessage());
        }
        return tasks;
    }

    /**
     * Writes task list to the file.
     *
     * @param tasks newly updated task list.
     */
    public void save(TaskList tasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false))) {
            // write task to hard disk (./data/tasks.txt)
            // for each task inside the TaskList, toDataString, then write to file
            String dataString = "";
            for (int i = 0; i < tasks.getSize(); i++) {
                dataString += tasks.getTask(i).toDataString() + "\n";
            }
            bw.write(dataString);
            bw.flush();
//            printAddSuccess(tasks, task);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
