import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;
    private boolean isUpdating = true;

    public TaskList(){
        this.taskList = new ArrayList<>();
    }

    public void doneTask(int index) throws DukeException {
        if(index < 0 || index > taskList.size() - 1){
            throw new DukeException("please give a correct task index");
        }
        Task doneTask = taskList.get(index);
        doneTask.complete();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(String.format("  %s", doneTask.toString()));
    }

    public void listTask(){
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, taskList.get(i).toString()));
        }
    }

    public void addTask(Task newTask, Boolean print){
        taskList.add(newTask);
        if(print) {
            newTask.printAddTask();
            printNumberOfTask(taskList.size());
        }
    }

    public void findTask(String description) {
        ArrayList<Task> taskMatchingDescription = new ArrayList<>();

        for (Task task : taskList){
            if (task.fitsTask(description)) {
                taskMatchingDescription.add(task);
            }
        }

        for (int i = 0; i < taskMatchingDescription.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, taskMatchingDescription.get(i).toString()));
        }
    }

    public int getTaskListSize(){
        return taskList.size();
    }

    public void setTaskListNotUpdating(){
        this.isUpdating = false;
    }

    public Task getTask(int index){
        return taskList.get(index);
    }

    public boolean isUpdating(){
        return isUpdating;
    }
    public void deleteTask(int index) throws DukeException {
        if(index < 0 || index > taskList.size() - 1){
            throw new DukeException("please give a correct task index");
        }
        taskList.get(index).printDeleteTask();
        taskList.remove(index);
        printNumberOfTask(taskList.size());
    }

    static void printNumberOfTask(int i){
        System.out.println(String.format("Now you have %d tasks in the list.",i));
    }
}
