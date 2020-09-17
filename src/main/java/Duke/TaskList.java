package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> todoList = new ArrayList<>();

    public TaskList (ArrayList<Task> taskList) {
        todoList = taskList;
    }

    public int getSize(){
        return todoList.size();
    }

    /**
     * Returns the number of unfinished tasks in the todoList
     * @return number of unfinished tasks
     */
    public int getUndoneCount() {
        int undoneCount = 0;
        for (Task t:todoList){
            if (!t.checkDone())
                undoneCount++;
        }

        return undoneCount;
    }


    /**
     * Take in a task, add it to the totoList and returns it.
     * @param task a task object
     * @return the task itself
     */
    public Task addTask(Task task) {
        this.todoList.add(task);
        return task;
    }

    /**
     * Create a new Duke.TodoTask, add it to the totoList and returns it.
     * @param taskContent content of Duke.TodoTask
     * @return a newly created Duke.Task
     */
    public Task addTodoTask(String taskContent) {
        Task task = new TodoTask(taskContent);
        this.todoList.add(task);
        return task;
    }

    /**
     * Create a new Duke.DeadlineTask, add it to the totoList and returns it.
     * @param taskContent content of Duke.DeadlineTask
     * @param time deadline of Duke.DeadlineTask
     * @return a newly created Duke.Task
     * @throws ParseErrorException
     */
    public Task addDeadlineTask(String taskContent, String time) throws ParseErrorException{
        Task task = new DeadlineTask(taskContent, time);
        this.todoList.add(task);
        return task;
    }

    /**
     * Create a new Duke.EventTask, add it to the totoList and returns it.
     * @param taskContent content of Duke.EventTask
     * @param time deadline of Duke.EventTask
     * @return a newly created Duke.Task
     * @throws ParseErrorException
     */
    public Task addEventTask(String taskContent, String time) throws ParseErrorException{
        Task task = new EventTask(taskContent, time);
        this.todoList.add(task);
        return task;
    }

    /**
     * Change a task from undone to done
     * @param index index of the requested task
     * @return the task that was changed
     * @throws IndexOutOfBoundsException
     */
    public Task doneTask(int index) throws IndexOutOfBoundsException{
        Task task = todoList.get(index);
        task.closeTask();
        return task;
    }

    /**
     * Delete a task of index from todoList
     * @param index ndex of the requested task
     * @return the task that need to be deleted
     * @throws IndexOutOfBoundsException
     */
    public Task deleteTask(int index) throws IndexOutOfBoundsException{
        Task task = todoList.get(index);
        todoList.remove(index);
        return task;
    }

    public ArrayList<Task> findTask(String content) {
        ArrayList<Task> allMatches = new ArrayList<>();
        for (Task task:todoList) {
            if (task.getContent().contains(content))
                allMatches.add(task);
        }

        return allMatches;
    }

    public ArrayList<Task> getTodoList() {
        return todoList;
    }

    public Task get(int index){
        return todoList.get(index);
    }
}
