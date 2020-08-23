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
    }

    public void delete(int index) throws DukeTaskNonExistException {
        if(index <0 | index>=shelf.size()) {
            throw new DukeTaskNonExistException("error");
        }
        this.shelf.remove(index);
    }

    public void find(String response) {
        Iterator<Task> iter = shelf.iterator();
        ArrayList<Task> temp = new ArrayList<>();
        while (iter.hasNext()) {
            Task book = iter.next();
            if(book.getName().contains(response)){
                temp.add(book);
            }
        }
        iter = temp.iterator();
        int counter = 1;
        System.out.println("Here are the matching tasks in your list: ");
        while (iter.hasNext()) {
            System.out.println(counter + ". " + iter.next());
            counter++;
        }
    }

}
