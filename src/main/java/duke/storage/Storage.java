package duke.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.task.TimedTask;
import duke.task.ToDos;

/**
 * The class Storage denotes a storage object.
 *
 * @author Alvin Chee
 */
public class Storage {

    private enum dataFileColumn {

        TASKTYPE(0),
        DONESTATUS(1),
        TASKSTRING(2),
        DATE(3);

        private static final int TOTALCOLUMNS = 4;
        private int index;

        dataFileColumn(int index) {
            this.index = index;
        }

        private int returnIndex() {
            return index;
        }

    }

    private static final int TASKTYPEINDEX = 0;
    private String filePath;
    private boolean isFolderCreated;

    /**
     * Constructs a storage
     *
     * @param filePath  file path where data file is stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Adds the respective directories if required.
     */
    public void addDirectoryIfRequired() {
        String home = System.getProperty("user.home");
        Path dukePath = Paths.get(home, "Duke");
        boolean dukeDirectoryExists = Files.exists(dukePath);
        Path dataPath = Paths.get(home, "Duke", "data");
        boolean dataDirectoryExists = Files.exists(dataPath);
        if (!dukeDirectoryExists) {
            File dir = new File(dukePath.toString());
            dir.mkdir();
        }
        if (!dataDirectoryExists) {
            File dir = new File(dataPath.toString());
            dir.mkdir();
            isFolderCreated = true;
        }
    }

    /**
     * Returns True if a new data file is created.
     * @return True if a new data file is created, false if it is not.
     */
    public boolean folderIsCreated() {
        return isFolderCreated;
    }

    /**
     * Generates the task list for the user.
     * @param taskTypeChar  Task type.
     * @param doneStatus    Done status of the task.
     * @param taskInfo      Information of the task.
     * @param when          Date and time of the task.
     * @return Task list of user.
     * @throws DukeLoadFileException
     */
    private List<Task> addTaskToList(List<Task> taskList, char taskTypeChar, String doneStatus,
                                        String taskInfo, String when) throws DukeLoadFileException{
        assert taskTypeChar != '\0' : "taskTypeChar should not be null";
        switch (taskTypeChar) {
        case 'T' :
            ToDos todo = Integer.parseInt(doneStatus) == Task.DONENO
                ? new ToDos(taskInfo).doneTask()
                : new ToDos(taskInfo);
            taskList.add(todo);
            break;
        case 'D' :
            Deadlines deadline = Integer.parseInt(doneStatus) == Task.DONENO
                ? new Deadlines(taskInfo, when).doneTask()
                : new Deadlines(taskInfo, when);
            taskList.add(deadline);
            break;
        case 'E' :
            Events event = Integer.parseInt(doneStatus) == Task.DONENO
                ? new Events(taskInfo, when).doneTask()
                : new Events(taskInfo, when);
            taskList.add(event);
            break;
        default :
            throw new DukeLoadFileException("Error generating task list from file.");
        }
        return taskList;
    }

    /**
     * Loads the list of tasks from storage.
     * @Return List of tasks from storage.
     */
    public List<Task> load() {
        List<Task> taskList = new ArrayList<>();
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine();
                if (nextLine == "") {
                    return taskList;
                } else {
                    String[] strArr = nextLine.split(":");
                    assert strArr.length == 3 || strArr.length == 4: "Error in saving task information";
                    String taskType = strArr[dataFileColumn.TASKTYPE.returnIndex()];
                    String doneStatus = strArr[dataFileColumn.DONESTATUS.returnIndex()].trim();
                    String taskInfo = strArr[dataFileColumn.TASKSTRING.returnIndex()].trim();
                    String when = strArr.length == dataFileColumn.TOTALCOLUMNS
                        ? strArr[dataFileColumn.DATE.returnIndex()].trim()
                        : "";
                    char taskTypeChar = taskType.charAt(TASKTYPEINDEX);
                    addTaskToList(taskList, taskTypeChar, doneStatus, taskInfo, when);
                }
            }
        } catch (DukeLoadFileException e) {
            //Error in task list of data file
        } catch (IOException e) {
            //A new file will be created when updatelist
        }
        return taskList;
    }

    /**
     * Saves individual task to list.
     * @param bw    Buffered writer
     * @param tasks List of tasks to be saved
     * @throws IOException
     */
    private void saveTasksToList(BufferedWriter bw, TaskList tasks) throws IOException {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            TaskType taskType = task.returnTaskType();
            String taskInfo = task.returnTaskInfo();
            String when = "";
            switch(taskType.returnTaskSymbol()) {
            case 'D' :
            case 'E' :
                TimedTask timedTask = (TimedTask) tasks.get(i);
                when = " : " + timedTask.returnTime().trim();
                break;
            default :
                break;
            }
            String toWrite = taskType.returnTaskSymbol() + " : " + task.returnDoneStatus() + " : " + taskInfo.trim() + when;
            bw.write(toWrite);
            bw.newLine();
        }
    }

    /**
     * Saves lists of tasks into storage.
     * @param tasks List of tasks to be saved.
     * @throws DukeIOException
     */
    public void saveTaskList(TaskList tasks) throws DukeIOException {
        try {
            File file = new File(filePath);
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            saveTasksToList(bw, tasks);
            bw.close();
        } catch (IOException e) {
            throw new DukeIOException("Sorry Poppins but file is not found.");
        }
    }
}
