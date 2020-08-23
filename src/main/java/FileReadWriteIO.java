/* Class to connect between the FileReadWrite and the internal logic of TaskManager */

import java.io.IOException;
import java.util.List;

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

    public static void saveTaskListData(TaskManagerData taskManagerData) throws IOException {
        for (int i = 0; i < taskManagerData.taskList.size(); i++) {
            if (i == 0) {
                saveTaskData(taskManagerData.taskList.get(0));
            } else {
                appendTaskData(taskManagerData.taskList.get(i));
            }
        }
    }

    public static List<String> loadUngroupedSavedTaskList() throws IOException {
        return FileReadWrite.loadFromSavedFile();
    }
}
