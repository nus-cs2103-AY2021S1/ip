package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

/**
 * The TaskList class contains the task list.
 */
public class TaskList {
    public ArrayList<Task> todoList = new ArrayList<Task>();

    public TaskList(ArrayList<Task> ls) {
        this.todoList = ls;
    }

    public void addTodo(Todo todo){
        this.todoList.add(todo);
    }

    public void addEvent(Event event){
        this.todoList.add(event);
    }

    public void addDead(Deadline deadline){
        this.todoList.add(deadline);
    }

    public void deleteTask(int id){
        this.todoList.remove(id);
    }

    public int size(){
        return todoList.size();
    }

    public Task get(int id){
        return todoList.get(id);
    }

    public int getID(Task task){
        return todoList.indexOf(task);
    }

    public ArrayList<Task> getTodoList(){
        return todoList;
    }
}
