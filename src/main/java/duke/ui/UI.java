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

/**
 * Class that deals with interactions with the user.
 */
public class UI {
    private final TaskList shelf;
    private final Storage storage;

    /**
     * Constructor for the UI object
     *
     * @param shelf   Tasklist
     * @param storage Storage
     */
    public UI(TaskList shelf, Storage storage) {
        this.shelf = shelf;
        this.storage = storage;
    }

    /**
     * Method that prints the goodbye statement
     */
    public void replyBye() {
        System.out.println("CYA PAL. Hope to see you again!");
    }

    /**
     * Method that would invoke the iterate statement from the Tasklist.
     * This iterates through the entire list and prints out each task with a 1-based index position
     * of where the task is on the Tasklist.
     */
    public void replyList() {
        shelf.iterate();
    }

    /**
     * method that deletes a task from the tasklist given the one-based index provided by the user.
     * It also updates the txt file based on the updated tasklist.
     *
     * @param index one-based integer index indicating the position of the task in the TaskList
     * @throws IOException               if there is an error while updating the txt file
     * @throws DukeTaskNonExistException if the task does not exist
     */
    public void replyDelete(int index) throws IOException, DukeTaskNonExistException {
        System.out.println("Noted. I've removed this task: ");
        shelf.delete(index);
        storage.updateFile(shelf);
        System.out.println("Now you have " + shelf.getSize() + " in the list.");
    }

    /**
     * Method that marks a task from the tasklist as done given the one-based index provided by the user.
     * It also updates the txt file based on the updated tasklist
     *
     * @param index one-based integer index indicating the position of the task in the TaskList
     * @throws DukeTaskNonExistException if there is an error while updating the txt file.
     * @throws IOException               if there is an error while updating the txt file
     */
    public void replyDone(int index) throws DukeTaskNonExistException, IOException {
        if (index >= shelf.getSize() || index < 0) {
            throw new DukeTaskNonExistException("error");
        }
        System.out.println("Nice! I've marked this task as done: ");
        Task book = shelf.completeTask(index);
        storage.updateFile(shelf);
        System.out.println(book);
    }

    /**
     * Method that adds a new todo task into the tasklist.
     * It will create the todo task with the current time.
     * It also updates the txt file based on the updated tasklist.
     *
     * @param response input from the user to create a todo task
     * @throws IOException if there is an error while updating the txt file
     */
    public void addTodo(String response) throws IOException {
        Task book = new ToDo(response, LocalDateTime.now());
        shelf.addTask(book);
        storage.updateFile(shelf);
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + book);
        System.out.println("Now you have " + shelf.getSize() + " tasks in the list.");
    }

    /**
     * Method that adds a new deadline task into the tasklist.
     * It will create the deadline task with the current time and due date indicated in the user's input.
     * It also updates the txt file based on the updated tasklist.
     *
     * @param response input given by the user for the name of the task
     * @param duedate  input given by the user indicating the duedate of the task
     * @throws IOException if there is an error while updating the txt file
     */
    public void addDeadline(String response, String duedate) throws IOException {
        Task book = new Deadline(response, LocalDateTime.now(), duedate);
        shelf.addTask(book);
        storage.updateFile(shelf);
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + book);
        System.out.println("Now you have " + shelf.getSize() + " tasks in the list.");
    }

    /**
     * Method that adds a new event task into the tasklist.
     * It will create the event task with the current time and event date indicated in the user's input.
     * It also updates the txt file based on the updated tasklist.
     *
     * @param response input given by the user for the name of the task
     * @param duedate  input given by the user indicating the eventdate of the task
     * @throws IOException if there is an error while updating the txt file
     */
    public void addEvent(String response, String duedate) throws IOException {
        Task book = new EventTask(response, LocalDateTime.now(), duedate);
        shelf.addTask(book);
        storage.updateFile(shelf);
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + book);
        System.out.println("Now you have " + shelf.getSize() + " tasks in the list.");
    }

    /**
     * Prints the exception.
     *
     * @param except indicates the exception
     */
    public void showError(Exception except) {
        System.out.println(except);
    }
}
