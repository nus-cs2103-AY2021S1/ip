public class DukeBuffer {
    private TaskList taskList;

    public DukeBuffer() {
        this.taskList = new TaskList();
    }

    public DukeBuffer(TaskList taskList) {
        this.taskList = new TaskList();
    }

    public DukeBuffer addTask(Task task) {
        return new DukeBuffer(taskList.addToList(task));
    }

    public String showTasks() {
        return this.taskList.getAllTasks();
    }
}
