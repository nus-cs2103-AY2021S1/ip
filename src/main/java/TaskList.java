import javax.lang.model.type.ArrayType;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    private TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    private void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Called by Storage when Duke is initialised. Looks for the on hard disc storage of the list. If found, retrieves it to be used by Duke
     * Else the text file is created to be used for future storing on hard disc
     * @param File
     * @return
     * @throws IOException
     */
    public static TaskList retrieveTaskList(Path File) throws IOException, ToDoException, eventException, deadlineException {
        TaskList returnTaskList = new TaskList();
        if (java.nio.file.Files.exists(File)) {
            Scanner scanner = new Scanner(File);
            while (scanner.hasNextLine()) {
                Task taskAdded = null;
                String taskData = scanner.nextLine();
                String[] taskDataDivided = taskData.split(" ~ ");
                boolean isDone = taskDataDivided[1].equals("1");
                switch (taskDataDivided[0]) {
                    case "E":
                        taskAdded = new Event(taskDataDivided[2], taskDataDivided[3], isDone);
                        if (taskDataDivided.length > 4) taskAdded.setDuration(taskDataDivided[4]);
                        returnTaskList.add(taskAdded);
                        break;
                    case "D":
                        taskAdded = new Deadline(taskDataDivided[2], taskDataDivided[3], isDone);
                        if (taskDataDivided.length > 4) taskAdded.setDuration(taskDataDivided[4]);
                        returnTaskList.add(taskAdded);
                        break;
                    case "T":
                        taskAdded = new Task(taskDataDivided[2], isDone);
                        if (taskDataDivided.length > 3) taskAdded.setDuration(taskDataDivided[3]);
                        returnTaskList.add(taskAdded);
                        break;
                }
            }
        }
        return returnTaskList;
    }

    public static TaskList copy(TaskList copiedTaskList) {
        return new TaskList(copiedTaskList.getList());
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
    public void deleteTask(int index, Storage storage) throws deleteException, IOException {
        if (index <= taskList.size()) {
            taskList.remove(index - 1);
            storage.saveToDisk();
        } else {
            throw new deleteException();
        }
    }

    public void setDuration(String userInput, Storage storage) throws IOException {
        String[] splitBySpaces = userInput.split(" ");
        int indexSettingDuration = Integer.parseInt(splitBySpaces[1]);
        String durationSet = splitBySpaces[2];
        Task taskSettingDurationFor = taskList.get(indexSettingDuration-1);
        taskSettingDurationFor.setDuration(durationSet);
        storage.saveToDisk();
    }

    /**
     * Add Task given to the list
     * @param task
     */
    public void addTask(Task task, Storage storage) throws IOException {
        taskList.add(task);
        storage.saveToDisk();
    }

    /**
     * Marks task with given index as completed
     * @param index
     */
    public void taskCompleted(int index, Storage storage) throws IOException {
        taskList.get(index - 1).isDone = true;
        storage.saveToDisk();
    }

    /**
     * getter for the size of the ArrayList
     * @return
     */
    public int getSize() {
        return taskList.size();
    }

    public ArrayList<Task> searchFor(String searchString) {
        ArrayList<Task> returnArrayList = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDescription().contains(searchString)) returnArrayList.add(task);
        }
        return returnArrayList;
    }
    

    public String printTaskList() {
        if (this.taskList.size() == 0) {
            return "You currently have nothing on your list";
        } else {
            String returnString = "Here's what you have on your list";
            int counter = 0;
            Iterator<Task> taskIterator = this.taskList.iterator();
            while (taskIterator.hasNext()) {
                Task thisTask = taskIterator.next();
                returnString += "\n" + (counter + 1) + ". " + thisTask.toString();
                counter++;
            }
            return returnString;
        }
    }
}
