import java.io.IOException;
import java.util.List;

/**
 * <p>The FileReadWriteIO connects the logic between reading and writing a general file to
 * reading and writing the specific files of the project.</p>
 */
public class FileReadWriteIO {

    // Saves task in 2 lines, first line is task description and second is status (1 is done, 0 is not done)
    private static void saveTaskData(TaskData taskData) throws IOException {
        FileReadWrite.writeToFile(taskData.taskType + " %% " +
                                    taskData.taskDescription + " %% " +
                                    taskData.isDone + " %% " +
                                    taskData.date + " %% " + taskData.time);
    }

    private static void appendTaskData(TaskData taskData) throws IOException {
        FileReadWrite.appendToFile(taskData.taskType + " %% " +
                taskData.taskDescription + " %% " +
                taskData.isDone + " %% " +
                taskData.date + " %% " + taskData.time);
    }

    /** Saves all tasks and their status in the list of task.
     * @param taskManagerData The data object that stores information about the task list
     * @throws IOException when an IO operation fails
     */
    public static void saveTaskListData(TaskManagerData taskManagerData) throws IOException {
        for (int i = 0; i < taskManagerData.getTaskList().size(); i++) {
            if (i == 0) {
                saveTaskData(taskManagerData.getTaskList().get(0));
            } else {
                appendTaskData(taskManagerData.getTaskList().get(i));
            }
        }
    }

    public static List<String> loadUngroupedSavedTaskList() throws IOException {
        return FileReadWrite.loadFromSavedFile();
    }
}
