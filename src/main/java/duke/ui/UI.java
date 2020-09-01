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
     * Empty line to be printed after each command
     */
    private final static String LINE = "************************************\n";


    /**
     * This method shows the welcome message for the chatbot. It is what users see when they launch the chatbot.
     */
    public String welcome() {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        StringBuilder sb = new StringBuilder(LINE);
        sb.append("Hello from \n");
        sb.append(logo);
        sb.append("What can I do for you pal? :D\n");
        sb.append(LINE);
        return sb.toString();
    }


    /**
     * Method that prints the goodbye statement
     */
    public String replyBye() {
        return "CYA PAL. Hope to see you again!";
    }

    /**
     * Method that would invoke the iterate statement from the Tasklist.
     * This iterates through the entire list and prints out each task with a 1-based index position
     * of where the task is on the Tasklist.
     */
    public String replyList() {
        return shelf.iterate();
    }

    /**
     * Method that would invoke the find statement from the TaskList.
     *
     * @param response Input from the user
     */
    public String replyFind(String response) {
        return shelf.find(response);
    }


    /**
     * method that deletes a task from the tasklist given the one-based index provided by the user.
     * It also updates the txt file based on the updated tasklist.
     *
     * @param index one-based integer index indicating the position of the task in the TaskList
     * @throws IOException               if there is an error while updating the txt file
     * @throws DukeTaskNonExistException if the task does not exist
     */
    public String replyDelete(int index) throws IOException, DukeTaskNonExistException {
        StringBuilder sb = new StringBuilder("Noted. I've removed this task: ").append('\n');
        shelf.delete(index);
        storage.updateFile(shelf);
        sb.append("Now you have ").append(shelf.getSize()).append(" in the list.");
        return sb.toString();
    }

    /**
     * Method that marks a task from the tasklist as done given the one-based index provided by the user.
     * It also updates the txt file based on the updated tasklist
     *
     * @param index one-based integer index indicating the position of the task in the TaskList
     * @throws DukeTaskNonExistException if there is an error while updating the txt file.
     * @throws IOException               if there is an error while updating the txt file
     */
    public String replyDone(int index) throws DukeTaskNonExistException, IOException {
        if (index >= shelf.getSize() || index < 0) {
            throw new DukeTaskNonExistException("error");
        }
        StringBuilder sb = new StringBuilder("Nice! I've marked this task as done: ").append('\n');
        Task book = shelf.completeTask(index);
        storage.updateFile(shelf);
        sb.append(book.toString());
        return sb.toString();
    }

    /**
     * Method that adds a new todo task into the tasklist.
     * It will create the todo task with the current time.
     * It also updates the txt file based on the updated tasklist.
     *
     * @param response input from the user to create a todo task
     * @throws IOException if there is an error while updating the txt file
     */
    public String addTodo(String response) throws IOException {
        Task book = new ToDo(response, LocalDateTime.now());
        shelf.addTask(book);
        storage.updateFile(shelf);
        return "Got it. I've added this task: " + '\n' +
                "  " + book + '\n' +
                "Now you have " + shelf.getSize() + " tasks in the list.";
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
    public String addDeadline(String response, String duedate) throws IOException {
        Task book = new Deadline(response, LocalDateTime.now(), duedate);
        shelf.addTask(book);
        storage.updateFile(shelf);
        return "Got it. I've added this task: " + '\n' +
                "  " + book + '\n' +
                "Now you have " + shelf.getSize() + " tasks in the list.";
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
    public String addEvent(String response, String duedate) throws IOException {
        Task book = new EventTask(response, LocalDateTime.now(), duedate);
        shelf.addTask(book);
        storage.updateFile(shelf);
        return "Got it. I've added this task: " + '\n' +
                "  " + book + '\n' +
                "Now you have " + shelf.getSize() + " tasks in the list.";
    }

    /**
     * Prints the exception.
     *
     * @param except indicates the exception
     */
    public String showError(Exception except) {
        return except.toString();
    }
}
