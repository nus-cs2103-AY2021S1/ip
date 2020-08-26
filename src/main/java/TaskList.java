import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> todoList = new ArrayList<>();

    public TaskList (ArrayList<Task> taskList) {
        todoList = taskList;
    }

    public int getSize(){
        return todoList.size();
    }

    public int getUndoneCount() {
        int undoneCount = 0;
        for (Task t:todoList){
            if (!t.checkDone())
                undoneCount++;
        }

        return undoneCount;
    }

    public Task addTodoTask(String taskContent) {
        Task task = new TodoTask(taskContent);
        this.todoList.add(task);
        return task;
    }

    public Task addDeadlineTask(String taskContent, String time) throws ParseErrorException{
        Task task = new DeadlineTask(taskContent, time);
        this.todoList.add(task);
        return task;
    }

    public Task addEventTask(String taskContent, String time) throws ParseErrorException{
        Task task = new EventTask(taskContent, time);
        this.todoList.add(task);
        return task;
    }

    public Task doneTask(int index) throws IndexOutOfBoundsException{
        Task task = todoList.get(index);
        task.closeTask();
        return task;
    }

    public Task deleteTask(int index) throws IndexOutOfBoundsException{
        Task task = todoList.get(index);
        todoList.remove(index);
        return task;
    }

    public ArrayList<Task> getTodoList() {
        return todoList;
    }

    public Task get(int index){
        return todoList.get(index);
    }
}
