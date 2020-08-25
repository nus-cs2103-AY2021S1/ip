import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() {
        List<Task> taskList = new ArrayList<>();
        try {
/*            String home = System.getProperty("user.home");
            Path path = Paths.get(home, "Duke", "data", "tasks.text");*/
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
                        ToDos todo = Integer.parseInt(doneStatus) == Task.doneNo ?
                                new ToDos(taskInfo).doneTask() : new ToDos(taskInfo);
                        taskList.add(todo);
                        break;
                    case 'D' :
                        Deadlines deadline = Integer.parseInt(doneStatus) == Task.doneNo ?
                                new Deadlines(taskInfo, when).doneTask() : new Deadlines(taskInfo, when);
                        taskList.add(deadline);
                        break;
                    case 'E' :
                        Events event = Integer.parseInt(doneStatus) == Task.doneNo ?
                                new Events(taskInfo, when).doneTask() : new Events(taskInfo, when);
                        taskList.add(event);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            /*A new file will be created when updatelist*/
        }
        return taskList;
    }

    public void saveTaskList() throws DukeIOException{
        try {
            File file = new File(filePath);
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < TaskList.taskList.size(); i++) {
                Task task = TaskList.taskList.get(i);
                TaskType taskType = task.returnTaskType();
                char taskTypeChar = taskType.toString().charAt(1);
                int status = task.returnDoneStatus();
                String taskInfo = task.returnTaskInfo();
                String when = "";
                switch(taskTypeChar) {
                case 'D' :
                    Deadlines deadline = (Deadlines)TaskList.taskList.get(i);
                    when = " : " + deadline.returnTime().trim();
                    break;
                case 'E' :
                    Events event = (Events)TaskList.taskList.get(i);
                    when = " : " + event.returnTime().trim();
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
