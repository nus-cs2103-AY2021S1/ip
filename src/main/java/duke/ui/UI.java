package duke.ui;

import java.io.IOException;
import java.time.LocalDateTime;

import duke.dukeexception.DukeTaskNonExistException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.deadline.Deadline;
import duke.task.eventtask.EventTask;
import duke.task.todo.ToDo;
import duke.tasklist.TaskList;

/**
 * Class that deals with interactions with the user.
 */
public class UI {
    /**
     * Empty line to be printed after each command
     */
    private static final String LINE = "************************************\n";
    private final TaskList taskList;
    private final Storage storage;

    /**
     * Constructor for the UI object
     *
     * @param taskList   Tasklist
     * @param storage Storage
     */
    public UI(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * This method shows the welcome message for the chatbot. It is what users see when they launch the chatbot.
     */
    public String performWelcome() {
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
    public String performBye() {
        return "CYA PAL. Hope to see you again!";
    }

    /**
     * Method that would invoke the iterate statement from the Tasklist.
     * This iterates through the entire list and prints out each task with a 1-based index position
     * of where the task is on the Tasklist.
     */
    public String performShowList() {
        return taskList.iterate();
    }

    /**
     * Method that would invoke the find statement from the TaskList.
     *
     * @param response Input from the user.
     */
    public String performFind(String response) {
        return taskList.findTask(response);
    }


    /**
     * method that deletes a task from the tasklist given the one-based index provided by the user.
     * It also updates the txt file based on the updated tasklist.
     *
     * @param index one-based integer index indicating the position of the task in the TaskList.
     * @throws IOException               if there is an error while updating the txt file.
     * @throws DukeTaskNonExistException if the task does not exist.
     */
    public String performDelete(int index) throws IOException, DukeTaskNonExistException {
        StringBuilder sb = new StringBuilder("Noted. I've removed this task: ").append('\n');
        taskList.delete(index);
        storage.updateFile(taskList);
        sb.append("Now you have ").append(taskList.getSize()).append(" in the list.");
        return sb.toString();
    }

    /**
     * Method that marks a task from the tasklist as done given the one-based index provided by the user.
     * It also updates the txt file based on the updated tasklist.
     *
     * @param index one-based integer index indicating the position of the task in the TaskList.
     * @throws DukeTaskNonExistException if there is an error while updating the txt file.
     * @throws IOException               if there is an error while updating the txt file
     */
    public String performDone(int index) throws DukeTaskNonExistException, IOException {
        checkTaskExists(index);
        StringBuilder sb = new StringBuilder("Nice! I've marked this task as done: ").append('\n');
        Task book = taskList.completeTask(index);
        storage.updateFile(taskList);
        sb.append(book.toString());
        return sb.toString();
    }

    private void checkTaskExists(int index) throws DukeTaskNonExistException {
        if (index >= taskList.getSize() || index < 0) {
            throw new DukeTaskNonExistException("error");
        }
    }

    /**
     * Method that adds a new todo task into the tasklist.
     * It will create the todo task with the current time.
     * It also updates the txt file based on the updated tasklist.
     *
     * @param response input from the user to create a todo task.
     * @throws IOException if there is an error while updating the txt file.
     */
    public String performAddTodo(String response) throws IOException {
        Task book = new ToDo(response, LocalDateTime.now());
        taskList.addTask(book);
        storage.updateFile(taskList);
        return "Got it. I've added this task: " + '\n'
                + "  " + book + '\n'
                + "Now you have " + taskList.getSize() + " tasks in the list.";
    }

    /**
     * Method that adds a new deadline task into the tasklist.
     * It will create the deadline task with the current time and due date indicated in the user's input.
     * It also updates the txt file based on the updated tasklist.
     *
     * @param response input given by the user for the name of the task.
     * @param duedate  input given by the user indicating the duedate of the task.
     * @throws IOException if there is an error while updating the txt file.
     */
    public String performAddDeadline(String response, String duedate) throws IOException {
        Task book = new Deadline(response, LocalDateTime.now(), duedate);
        taskList.addTask(book);
        storage.updateFile(taskList);
        return "Got it. I've added this task: " + '\n'
                + "  " + book + '\n'
                + "Now you have " + taskList.getSize() + " tasks in the list.";
    }

    /**
     * Method that adds a new event task into the tasklist.
     * It will create the event task with the current time and event date indicated in the user's input.
     * It also updates the txt file based on the updated tasklist.
     *
     * @param response input given by the user for the name of the task.
     * @param duedate  input given by the user indicating the eventdate of the task.
     * @throws IOException if there is an error while updating the txt file.
     */
    public String performAddEvent(String response, String duedate) throws IOException {
        Task book = new EventTask(response, LocalDateTime.now(), duedate);
        taskList.addTask(book);
        storage.updateFile(taskList);
        return "Got it. I've added this task: " + '\n'
                + "  " + book + '\n'
                + "Now you have " + taskList.getSize() + " tasks in the list.";
    }

    /**
     * Prints the exception.
     *
     * @param except indicates the exception
     */
    public String performShowError(Exception except) {
        return except.toString();
    }
}
