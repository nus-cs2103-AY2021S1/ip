import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;
    private boolean isUpdating = true;

    public TaskList(){
        this.taskList = new ArrayList<>();
    }

    public void DoneTask(int index) throws DukeException {
        if(index < 0 || index > taskList.size() - 1){
            throw new DukeException("please give a correct task index");
        }
        Task doneTask = taskList.get(index);
        doneTask.complete();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(String.format("  %s", doneTask.toString()));
    }

    public void ListTask(){
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, taskList.get(i).toString()));
        }
    }

    public void AddTask(Task newTask, Boolean print){
        taskList.add(newTask);
        if(print) {
            newTask.printAddTask();
            printNumberOfTask(taskList.size());
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
    public void DeleteTask(int index) throws DukeException {
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
