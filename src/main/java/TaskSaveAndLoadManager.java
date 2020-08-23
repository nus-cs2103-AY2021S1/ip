import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
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
                LocalDate date = ((DeadlineTask) task).getTimeToBeDoneBy().getDate();
                taskData = new TaskData("deadline", task.getTaskDescription(), boolInt, date.toString(), "");
            } else if (task instanceof EventTask) {
                LocalDate date = ((EventTask) task).getEventTime().getDate();
                LocalTime time = ((EventTask) task).getEventTime().getTime();
                taskData = new TaskData("event", task.getTaskDescription(), boolInt,
                        date.toString(), time.toString());
            }
            taskManagerData.taskList.add(taskData);
        }
        FileReadWriteIO.saveTaskListData(taskManagerData);
    }

    public Task loadTask(TaskData taskData) {
        boolean isDone;
        isDone = taskData.isDone == 1;

        if (taskData.taskType.equals("todo")) {
            return new ToDoTask(taskData.taskDescription, isDone);
        } else if (taskData.taskType.equals("deadline")) {
            DateAndTime dt = new DateAndTime(LocalDate.parse(taskData.time));
            return new DeadlineTask(taskData.taskDescription, isDone, dt);
        } else {
            DateAndTime dt = new DateAndTime(LocalDate.parse(taskData.date), LocalTime.parse(taskData.time));
            return new EventTask(taskData.taskDescription, isDone, dt);
        }
    }

    public TaskManager loadTaskManager() throws IOException {
        List<String> loadedData = FileReadWriteIO.loadUngroupedSavedTaskList();
        ArrayList<Task> taskList = new ArrayList<>();
        // loop through the loaded data strings, split each string by | and add to task list
        if (loadedData != null) {
            for (String loadedDatum : loadedData) {
                String[] tempArr = loadedDatum.split(" %% ");
                TaskData taskData;
                // a todo item
                if (tempArr.length == 3) {
                    taskData = new TaskData(tempArr[0], tempArr[1], Integer.parseInt(tempArr[2]));
                // a deadline item
                } else if (tempArr.length == 4) {
                    taskData = new TaskData(tempArr[0], tempArr[1], Integer.parseInt(tempArr[2]), tempArr[3]);
                } else {
                    taskData = new TaskData(tempArr[0], tempArr[1], Integer.parseInt(tempArr[2]), tempArr[3], tempArr[4]);
                }
                Task task = loadTask(taskData);
                taskList.add(task);
            }
            return new TaskManager(taskList);
        }
        return null;
    }
}
