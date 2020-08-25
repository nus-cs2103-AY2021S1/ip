import task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static File file;

    public Storage(String filePath) {
        file = new File("data/duke.txt");
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } else {
                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNext()) {
                    String task = fileScanner.nextLine();
                    String[] taskSplit = task.split(">", 4);
                    String taskType = taskSplit[0];
                    boolean taskIsDone = taskSplit[1].equals("1") ? true : false;
                    String taskDescription = taskSplit[2];
                    if (taskType.equals("T")) {
                        taskList.add(new ToDo(taskDescription, taskIsDone));
                    } else if (taskType.equals("D")) {
                        String taskBy = taskSplit[3];
                        taskList.add(new Deadline(taskDescription, taskBy, taskIsDone));
                    } else {
                        String taskAt = taskSplit[3];
                        taskList.add(new Event(taskDescription, taskAt, taskIsDone));
                    }
                }
                fileScanner.close();
            }
        } catch (IOException e) {
            throw new DukeException("Error loading file!");
        }
        return taskList;
    }

    public void save() throws DukeException {
        try {
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < Duke.taskList.size(); i++) {
                Task task = Duke.taskList.get(i);
                writer.write(task.toEncoding() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Error saving file!");
        }
    }
}
