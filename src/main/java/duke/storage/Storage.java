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
import duke.task.ToDos;

/**
 * The class Storage denotes a storage object.
 *
 * @author Alvin Chee
 */
public class Storage {
    private String filePath;

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
    public void addDirIfRequired() {
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "Duke");
        boolean dukeDirectoryExists = Files.exists(path);
        if (!dukeDirectoryExists) {
            File dir = new File(path.toString());
            dir.mkdir();
        }
        path = Paths.get(home, "Duke", "data");
        boolean dataDirectoryExists = Files.exists(path);
        if (!dataDirectoryExists) {
            File dir = new File(path.toString());
            dir.mkdir();
        }
    }

    /**
     * Loads the list of tasks from storage.
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
                    String taskType = strArr[0];
                    char taskTypeChar = taskType.charAt(1);
                    String doneStatus = strArr[1].trim();
                    String taskInfo = strArr[2].trim();
                    String when = strArr.length > 3 ? strArr[3].trim() : "";
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
                        break;
                    }
                }
            }
        } catch (IOException e) {
            /*A new file will be created when updatelist*/
        }
        return taskList;
    }

    /**
     * Saves lists of tasks into storage.
     */
    public void saveTaskList(TaskList tasks) throws DukeIOException {
        try {
            File file = new File(filePath);
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                TaskType taskType = task.returnTaskType();
                char taskTypeChar = taskType.toString().charAt(1);
                int status = task.returnDoneStatus();
                String taskInfo = task.returnTaskInfo();
                String when = "";
                switch(taskTypeChar) {
                case 'D' :
                    Deadlines deadline = (Deadlines) tasks.get(i);
                    when = " : " + deadline.returnTime().trim();
                    break;
                case 'E' :
                    Events event = (Events) tasks.get(i);
                    when = " : " + event.returnTime().trim();
                    break;
                default :
                    break;
                }
                String toWrite = taskType.toString().trim() + " : " + status + " : " + taskInfo.trim() + when;
                bw.write(toWrite);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            throw new DukeIOException("Sorry handsome but file is not found.");
        }
    }
}
