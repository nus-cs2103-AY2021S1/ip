import javax.lang.model.type.ArrayType;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    private void add(Task task) {
        this.taskList.add(task);
    }

    public static TaskList retrieveTaskList(Path File) throws IOException {
        TaskList returnTaskList = new TaskList();
        if (java.nio.file.Files.exists(File)) {
            Scanner scanner = new Scanner(File);
            while (scanner.hasNextLine()) {
                String taskData = scanner.nextLine();
                String[] taskDataDivided = taskData.split(" ~ ");
                boolean isDone = taskDataDivided[2].equals("1");
                switch (taskDataDivided[0]) {
                    case "E":
                        returnTaskList.add(new Event(taskDataDivided[2], taskDataDivided[3], isDone));
                        break;
                    case "D":
                        returnTaskList.add(new Deadline(taskDataDivided[2], taskDataDivided[3], isDone));
                        break;
                    case "T":
                        returnTaskList.add(new Task(taskDataDivided[2], isDone));
                        break;
                }
            }
            return returnTaskList;
        } else {
            return returnTaskList;
        }
    }

    public ArrayList<Task> getList() {
        ArrayList<Task> returnTaskList = new ArrayList<>();
        for (Task task : taskList) returnTaskList.add(task);
        return returnTaskList;
    }

    public void deleteTask(int index) throws deleteException, IOException {
        if (index <= taskList.size()) {
            taskList.remove(index - 1);
        } else {
            throw new deleteException();
        }
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void taskCompleted(int index) {
        taskList.get(index - 1).isDone = true;
    }

    public int getSize() {
        return taskList.size();
    }



}
