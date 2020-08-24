package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() { }

    public TaskList(String[] taskList) throws DukeException {
        for (String plainText : taskList) {
            String[] plainTextSplit = plainText.split(" \\| ");
            String newTaskCategory = plainTextSplit[0];
            String newTaskStatus = plainTextSplit[1];
            String newTaskDescription = plainTextSplit[2];
            String newTaskTime = plainTextSplit.length > 3 ? plainTextSplit[3] : "";

            switch (newTaskCategory) {
            case "T":
                addTask(new ToDo(newTaskDescription, Boolean.parseBoolean(newTaskStatus)));
                break;
            case "E":
                addTask(new Event(newTaskDescription, Boolean.parseBoolean(newTaskStatus), newTaskTime));
                break;
            case "D":
                addTask(new Deadline(newTaskDescription, Boolean.parseBoolean(newTaskStatus), newTaskTime));
                break;
            }
        }
    }

    public Task deleteTask(int taskNumberToMark) {
        Task taskToMark = this.taskList.remove(taskNumberToMark - 1);
        return taskToMark;
    }

    public Task markTask(int taskNumberToMark) {
        Task taskToMark = this.taskList.get(taskNumberToMark - 1);
        taskToMark.markAsDone();
        return taskToMark;
    }

    public Task addTask(Task newTask) {
        this.taskList.add(newTask);
        return newTask;
    }

    public int getSize() {
        return this.taskList.size();
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public String getAllTasksPlainText() {
        String[] taskArr = new String[this.getSize()];
        for (int k = 0; k < this.getSize(); k++) {
            taskArr[k] = this.getTask(k).getPlainText();
        }
        String fileData = String.join("\n",
                taskArr);
        return fileData;
    }
}