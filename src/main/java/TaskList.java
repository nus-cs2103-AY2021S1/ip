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

    /**
     * getter for the size of the ArrayList
     * @return
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Getter to return a copy of the ArrayList
     * @return copy of ArrayList
     */
    public ArrayList<Task> getList() {
        ArrayList<Task> returnTaskList = new ArrayList<>();
        for (Task task : taskList) returnTaskList.add(task);
        return returnTaskList;
    }

    /**
     * Deletes task specific to the index
     * @param index
     * @throws deleteException
     * @throws IOException
     */
    public void deleteTask(int index) throws deleteException, IOException {
        if (index <= taskList.size()) {
            taskList.remove(index - 1);
        } else {
            throw new deleteException();
        }
    }

    /**
     * Add Task given to the list
     * @param task
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Marks task with given index as completed
     * @param index
     */
    public void taskCompleted(int index) {
        taskList.get(index - 1).isDone = true;
    }

    /**
     * Called by Storage when Duke is initialised. Looks for the on hard disc storage of the list. If found, retrieves it to be used by Duke
     * Else the text file is created to be used for future storing on hard disc
     * @param File
     * @return
     * @throws IOException
     */
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
        }
        return returnTaskList;
    }
}
