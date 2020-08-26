import java.util.ArrayList;

public class TaskList {
    private final ArrayList<DukeTask> taskList;

    public TaskList(ArrayList<DukeTask> taskList) {
        this.taskList = taskList;
    }

    public void addToList(DukeTask dukeTask) {
        taskList.add(dukeTask);
    }

    public DukeTask deleteFromList(int index) throws InvalidInstructionException {
        if (index < 0 || index >= taskList.size()) { // check if loc is an existing DukeTask inside the array inputList
            throw new InvalidInstructionException(CommonString.DELETE + ": Invalid Task Number");
        }
        return taskList.remove(index);
    }

    public void markDone(int index) {
        taskList.get(index).markAsDone();
    }

    public int getSize() {
        return taskList.size();
    }

    public ArrayList<DukeTask> getTaskList() {
        return taskList;
    }
}
