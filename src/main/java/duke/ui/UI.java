package duke.ui;

import duke.storage.Storage;
import duke.task.deadline.Deadline;
import duke.dukeexception.DukeTaskNonExistException;
import duke.task.Task;
import duke.task.eventtask.EventTask;
import duke.task.todo.ToDo;
import duke.tasklist.TaskList;

import java.io.IOException;
import java.time.LocalDateTime;

public class UI {
    public final static String LINE = "*********************************************************";
    private TaskList shelf;
    private Storage storage;

    public UI(TaskList shelf, Storage storage) {
        this.shelf = shelf;
        this.storage = storage;
    }

    public void replyBye(){
        System.out.println("CYA PAL. Hope to see you again!");
        this.showLine();
    }

    public void replyList(){
        shelf.iterate();
        this.showLine();
    }

    public void replyDelete(int index) throws IOException, DukeTaskNonExistException {
        System.out.println("Noted. I've removed this task: ");
        shelf.delete(index);
        storage.updateFile(shelf);
        System.out.println("Now you have " + shelf.getSize() + " in the list.");
        this.showLine();
    }

    public void replyDone(int index) throws DukeTaskNonExistException, IOException {
        if (index >= shelf.getSize() || index < 0) {
            throw new DukeTaskNonExistException("error");
        }
        System.out.println("Nice! I've marked this task as done: ");
        Task book = shelf.completeTask(index);
        storage.updateFile(shelf);
        System.out.println(book);
        this.showLine();
    }

    public void addTodo(String response) throws IOException {
        Task book = new ToDo(response, LocalDateTime.now());
        shelf.addTask(book);
        storage.updateFile(shelf);
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + book);
        System.out.println("Now you have " + shelf.getSize() + " tasks in the list.");
        this.showLine();
    }

    public void addDeadline(String response, String duedate) throws IOException {
        Task book = new Deadline(response, LocalDateTime.now(), duedate);
        shelf.addTask(book);
        storage.updateFile(shelf);
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + book);
        System.out.println("Now you have " + shelf.getSize() + " tasks in the list.");
        this.showLine();
    }

    public void addEvent(String response, String duedate) throws IOException {
        Task book = new EventTask(response, LocalDateTime.now(), duedate);
        shelf.addTask(book);
        storage.updateFile(shelf);
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + book);
        System.out.println("Now you have " + shelf.getSize() + " tasks in the list.");
        this.showLine();
    }

    public void showError(Exception e){
        System.out.println(e);
        this.showLine();
    }

    public void showLine(){
        System.out.println(LINE);
    }
}
