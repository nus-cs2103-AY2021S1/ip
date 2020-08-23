package duke.tasklist;

import duke.dukeexception.DukeTaskNonExistException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Iterator;

public class TaskList {
    private final ArrayList<Task> shelf;

    public TaskList(ArrayList<Task> shelf) {
        this.shelf = shelf;
    }

    public Task getTask(int index){
        return this.shelf.get(index);
    }

    public void addTask(Task task){
        this.shelf.add(task);
        //also remember to include thee updateFile from the storage side
    }

    public Task completeTask(int index) throws DukeTaskNonExistException {
        if(index <0 | index>=shelf.size()) {
            throw new DukeTaskNonExistException("error");
        }
        Task book = this.shelf.get(index);
        book.complete();
        return book;
    }

    public int getSize(){
        return this.shelf.size();
    }

    public void iterate() {
        Iterator<Task> iter = shelf.iterator();
        int counter = 1;
        System.out.println("Here are the tasks in your list: ");
        while (iter.hasNext()) {
            System.out.println(counter + ". " + iter.next());
            counter++;
        }
        // reminder to print line from the duke.ui.UI side
    }

    public void delete(int index) throws DukeTaskNonExistException {
        if(index <0 | index>=shelf.size()) {
            throw new DukeTaskNonExistException("error");
        }
        this.shelf.remove(index);
    }

}
