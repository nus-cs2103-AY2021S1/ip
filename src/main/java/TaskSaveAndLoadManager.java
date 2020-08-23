import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskSaveAndLoadManager {

    public void saveTaskManager(TaskManager taskManager) throws IOException {
        TaskManagerData taskManagerData = new TaskManagerData();
        TaskData taskData = null;
        for (Task task: taskManager.getTaskList()) {
            boolean bool = task.getTaskStatus();
            int boolInt;
            if (bool) {
                boolInt = 1;
            } else {
                boolInt = 0;
            }
            if (task instanceof ToDoTask) {
                taskData = new TaskData("todo", task.getTaskDescription(), boolInt, "");
            } else if (task instanceof DeadlineTask) {
                taskData = new TaskData("deadline", task.getTaskDescription(), boolInt, ((DeadlineTask) task).getTimeToBeDoneBy());
            } else if (task instanceof EventTask) {
                taskData = new TaskData("event", task.getTaskDescription(), boolInt, ((EventTask) task).getEventTime());
            }
            taskManagerData.taskList.add(taskData);
        }
        IOReadWrite.saveTaskListData(taskManagerData);
    }

    public Task loadTask(TaskData taskData) {
        boolean isDone;
        isDone = taskData.isDone == 1;

        if (taskData.taskType.equals("todo")) {
            return new ToDoTask(taskData.taskDescription, isDone);
        } else if (taskData.taskType.equals("deadline")) {
            return new DeadlineTask(taskData.taskDescription, isDone, taskData.time);
        } else {
            return new EventTask(taskData.taskDescription, isDone, taskData.time);
        }
    }

    public TaskManager loadTaskManager() throws IOException {
        List<String> loadedData = IOReadWrite.loadUngroupedSavedTaskList();
        ArrayList<Task> taskList = new ArrayList<>();

        // loop through the loaded data strings, split each string by | and add to task list
        if (loadedData != null) {
            for (String loadedDatum : loadedData) {
                String[] tempArr = loadedDatum.split(" %% ");
                TaskData taskData;
                // a todo item
                if (tempArr.length == 3) {
                    taskData = new TaskData(tempArr[0], tempArr[1], Integer.parseInt(tempArr[2]));
                } else {
                    taskData = new TaskData(tempArr[0], tempArr[1], Integer.parseInt(tempArr[2]), tempArr[3]);
                }
                Task task = loadTask(taskData);
                taskList.add(task);
            }
            return new TaskManager(taskList);
        }
        return null;
    }
}
