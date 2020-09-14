package duke.storage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;
import duke.tag.Tag;
import duke.tag.TagList;
import duke.task.DateAndTime;
import duke.task.DeadlineTask;
import duke.task.Task;
import duke.task.TaskManager;
import duke.task.ToDoTask;

/**
 * <p>The duke.storage.TaskSaveAndLoadManager class manages the logic to convert Java objects into data objects
 * and vice versa.</p>
 */
public class TaskSaveAndLoadManager {

    /**
     * Saves the task list with tasks, along with their descriptions as well as
     * their respective statuses and types.
     * @param taskManager The duke.task.TaskManager that stores the task list
     * @throws IOException An exception thrown when IO operation fails
     */
    public void saveTaskManager(TaskManager taskManager) throws IOException, DukeException {
        for (int i = 0; i < taskManager.getTaskList().size(); i++) {
            if (i == 0) {
                FileReadWrite.writeToFile(taskManager.getTaskList().get(0).serialiseTask());
            } else {
                FileReadWrite.appendToFile(taskManager.getTaskList().get(i).serialiseTask());
            }
        }
    }

    /**
     * Loads the task list with tasks, along with their descriptions as well as
     * their respective statuses and types since last save.
     *
     * @return The duke.task.TaskManager that stores the task list.
     * @throws IOException An exception thrown when IO operation fails.
     */
    public TaskManager loadTaskManager() throws IOException {
        List<String> loadedData = FileReadWrite.loadFromSavedFile();
        // System.out.println(loadedData.get(0));
        ArrayList<Task> taskList = new ArrayList<>();
        if (loadedData != null) {
            for (String string : loadedData) {
                Task taskToAdd = loadTask2(string);
                taskList.add(taskToAdd);
            }
            return new TaskManager(taskList);
        } else {
            return null;
        }
    }

    private TagList loadTagList(String fullData) {
        TagList tagList = new TagList();
        int i = fullData.indexOf("#");
        String tagListString = fullData.substring(i + 1);
        String[] tagArr = tagListString.trim().split("#");
        for (String tag : tagArr) {
            Tag currTag = new Tag(tag);
            tagList.addTag(currTag);
        }
        return tagList;
    }

    private Task loadTask2(String string) {
        String[] splitDataString = string.trim().split(" %% ");
        String taskType = splitDataString[0];
        String taskDescription = splitDataString[1];
        String isDoneString = splitDataString[2];
        boolean isDone;
        isDone = Integer.parseInt(isDoneString) == 1;
        if (taskType.equals("todo")) {
            if (string.contains("#")) {
                TagList tagList = loadTagList(string);
                return new ToDoTask(taskDescription, isDone, tagList);
            } else {
                return new ToDoTask(taskDescription, isDone, null);
            }
        } else if (taskType.equals("deadline")) {
            DateAndTime dt = new DateAndTime(LocalDate.parse(splitDataString[3]));
            if (string.contains("#")) {
                TagList tagList = loadTagList(string);
                return new DeadlineTask(taskDescription, isDone, dt, tagList);
            } else {
                return new DeadlineTask(taskDescription, isDone, dt, null);
            }
        } else if (taskType.equals("event")) {
            DateAndTime dt = new DateAndTime(LocalDate.parse(splitDataString[3]),
                                LocalTime.parse(splitDataString[4]));
            if (string.contains("#")) {
                TagList tagList = loadTagList(string);
                return new DeadlineTask(taskDescription, isDone, dt, tagList);
            } else {
                return new DeadlineTask(taskDescription, isDone, dt, null);
            }
        } else {
            return null;
        }
    }
}
