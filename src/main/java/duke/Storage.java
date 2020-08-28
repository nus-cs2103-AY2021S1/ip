package duke;

import duke.exception.DukeException;
import duke.task.TaskType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> result = new ArrayList<>();

        try {
            File file = new File(filePath);
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {
                String nextLine = input.nextLine();
                String[] taskDetails = nextLine.split(":");
                String taskType = taskDetails[0];
                boolean taskIsDone = taskDetails[1].startsWith("1");
                String taskName = taskDetails[2];
                String taskDate = taskDetails.length < 4 ? "" : taskDetails[3];

                if (!TaskType.isMember(taskType)) {
                    throw new DukeException(taskType);
                } else {
                    TaskType task = TaskType.getTaskType(taskType);
                    task.addToTasks(result, taskName, taskIsDone, taskDate);
                }
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void save (TaskList taskList) {
        List<Task> tasks = taskList.getTasks();
        try {
            File file = new File(filePath);
            // System.getProperty("ip"), "duke_save_data.txt"
            FileWriter output = new FileWriter(file);
            for (Task task : tasks) {
                String taskType = task.getClass().getName().toLowerCase();
                TaskType type = TaskType.getTaskType(taskType);
                String saveLine = type.generateSaveLine(task.isDone, task.taskName, task.getDate());
                output.write(saveLine);
            }
            output.close();
        } catch (IOException | DukeException e) {
            e.printStackTrace();
        }
    }
}
