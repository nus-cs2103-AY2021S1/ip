package duke;

import duke.exception.DukeException;
import duke.task.TaskType;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    public static final String LOAD_FAILED = "Oops, load was unsuccessful!";
    public static final String SAVE_WAS_UNSUCCESSFUL = "Oops, save was unsuccessful!";
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads existing Duke save file before running.
     *
     * @return an ArrayList of Tasks for TaskList.
     */
    public ArrayList<Task> load() {
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
            System.out.println(LOAD_FAILED);
        }
        return result;
    }

    /**
     * Saves and/or overwrites the current Tasks into a Duke save file.
     *
     * @param taskList The final state of TaskList as Duke closes.
     */
    public void save(TaskList taskList) {
        List<Task> tasks = taskList.getTasks();
        try {
            File file = new File(filePath);
            // System.getProperty("ip"), "duke_save_data.txt"
            FileWriter output = new FileWriter(file);
            for (Task task : tasks) {
                String taskType = task.getClass().getSimpleName().toLowerCase();
                TaskType type = TaskType.getTaskType(taskType);
                String saveLine = type.generateSaveLine(task.isDone, task.taskName, task.getDate());
                output.write(saveLine);
            }
            output.close();
        } catch (Exception e) {
            System.out.println(SAVE_WAS_UNSUCCESSFUL);
        }
    }
}
