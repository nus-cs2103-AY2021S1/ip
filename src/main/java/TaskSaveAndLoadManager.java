import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>The TaskSaveAndLoadManager class manages the logic to convert Java objects into data objects
 * and vice versa.</p>
 */
public class TaskSaveAndLoadManager {

    /**
     * Saves the task list with tasks, along with their descriptions as well as
     * their respective statuses and types.
     * @param taskManager The TaskManager that stores the task list
     * @throws IOException An exception thrown when IO operation fails
     */
    public void saveTaskManager(TaskManager taskManager) throws IOException, DukeException {
        System.out.println(taskManager.getTaskList().size());
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
     * @return The TaskManager that stores the task list.
     * @throws IOException An exception thrown when IO operation fails.
     */
//    public TaskManager loadTaskManager() throws IOException {
//        List<String> loadedData = FileReadWrite.loadFromSavedFile();
//        ArrayList<Task> taskList = new ArrayList<>();
//        // loop through the loaded data strings, split each string by | and add to task list
//        if (loadedData != null) {
//            for (String loadedDatum : loadedData) {
//                assert (loadedDatum.contains(" %% ")) : "Each data object must have %% as separator";
//                String[] tempArr = loadedDatum.split(" %% ");
//                TaskData taskData;
//                // a todo item
//                if (tempArr[0].equals("todo")) {
//                    taskData = new TaskData(tempArr[0], tempArr[1], Integer.parseInt(tempArr[2]), tempArr[3]);
//                // a deadline item
//                } else if (tempArr[0].equals("deadline")) {
//                    taskData = new TaskData(tempArr[0], tempArr[1], Integer.parseInt(tempArr[2]), tempArr[3], tempArr[4]);
//                } else {
//                    taskData = new TaskData(tempArr[0], tempArr[1],
//                            Integer.parseInt(tempArr[2]), tempArr[3], tempArr[4], tempArr[5]);
//                }
//                Task task = loadTask(taskData);
//                taskList.add(task);
//            }
//            return new TaskManager(taskList);
//        }
//        return null;
//    }

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

    private TagList loadTagList(TaskData taskData) {
        TagList tagList = new TagList();
        if (!taskData.getTags().equals("")) {
            String[] tags = taskData.getTags().trim().split("#");
            for (String tag : tags) {
                Tag currTag = new Tag(tag);
                tagList.addTag(currTag);
            }
            return tagList;
        } else {
            return null;
        }
    }

    private Task loadTask(TaskData taskData) {
        boolean isDone;
        isDone = taskData.getIsDone() == 1;

        if (taskData.getTaskType().equals("todo")) {
            return new ToDoTask(taskData.getTaskDescription(), isDone, loadTagList(taskData));
        } else if (taskData.getTaskType().equals("deadline")) {
            DateAndTime dt = new DateAndTime(LocalDate.parse(taskData.getTime()));
            return new DeadlineTask(taskData.getTaskDescription(), isDone, dt, loadTagList(taskData));
        } else {
            DateAndTime dt = new DateAndTime(LocalDate.parse(taskData.getDate()), LocalTime.parse(taskData.getTime()));
            return new EventTask(taskData.getTaskDescription(), isDone, dt, loadTagList(taskData));
        }
    }

    private TagList loadTagList(String fullData) {
        TagList tagList = new TagList();
        int i = fullData.indexOf("#");
        String tagListString = fullData.substring(i);
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
                return new DeadlineTask(taskDescription, isDone, dt,null);
            }
        } else if (taskType.equals("event")) {
            DateAndTime dt = new DateAndTime(LocalDate.parse(splitDataString[3]),
                                LocalTime.parse(splitDataString[4]));
            if (string.contains("#")) {
                TagList tagList = loadTagList(string);
                return new DeadlineTask(taskDescription, isDone, dt, tagList);
            } else {
                return new DeadlineTask(taskDescription, isDone, dt,null);
            }
        } else {
            return null;
        }
    }
}
