package duke.tasks;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import duke.exceptions.DukeCommandException;
import duke.exceptions.DukeDateTimeException;
import duke.exceptions.DukeIndexException;
import duke.exceptions.DukeIoException;
import duke.exceptions.DukeNoInputException;


/**
 * TaskManager is a class to handle where duke.tasks are CRUD.
 */
public class TaskManager {
    private final List<Task> taskList;
    private final TaskIoParser ioParser;
    private final TextParser textParser;

    /**
     * Constructs TaskManager for the Duke Application with loading from the save file
     * @param path File Path from Main Class
     * @throws DukeIoException If no loaded save is read
     */
    public TaskManager(String path) throws DukeIoException {
        this.ioParser = new TaskIoParser(path);
        this.taskList = ioParser.loadTaskList();
        this.textParser = new TextParser();
    }

    /**
     * Constructs TaskManger for the first time.
     * @param path File Path from Main Class
     * @param isNew Boolean to indicate that the TaskManager is first initialised.
     */
    public TaskManager(String path, boolean isNew) {
        this.ioParser = new TaskIoParser(path);
        this.taskList = ioParser.loadNewTaskList();
        this.textParser = new TextParser();
    }
    
    /**
     * Indicate that a task is done
     * @param index index of the list as displayed from the application
     * @return String representation of indicating the task is done.
     * @throws DukeCommandException if a illegal index is given
     * @throws DukeIndexException If a given index is out of bounds
     */
    public String doTask(String index) throws DukeCommandException, DukeIndexException {
        try {
            int i = Integer.parseInt(index) - 1;
            //0 indexing
            this.getTask(i).doTask();
            return "\tNice! I've marked this task as done: \n\t" + this.getTask(i) + "\n";
        } catch (IllegalArgumentException e) {
            throw new DukeCommandException(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIndexException(index, taskList.size());
        }

    }

    /**
     * Deletes task
     * @param index index of the list as displayed from the application
     * @return String representation of the confirmation of deletion of task
     * @throws DukeCommandException if a illegal index is given
     * @throws DukeIndexException if a given index is out of bounds
     */
    public String deleteTask(String index) throws DukeCommandException, DukeIndexException {
        try {
            int i = Integer.parseInt(index) - 1;
            //0 indexing
            Task t = this.getTask(i);
            this.taskList.remove(i);
            return new StringBuilder().append("\tNoted! I've removed this task from your list: \n\t")
                    .append(t)
                    .append("\n\tNow you have ")
                    .append(this.taskList.size())
                    .append("tasks in the list.\n").toString();
        } catch (IllegalArgumentException e) {
            throw new DukeCommandException(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIndexException(index, taskList.size());
        }

    }

    /**
     * Get task from the internal list
     * @param index index of the internal list
     * @return Task the task at that index
     */
    private Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * generic polymorphic data flow for adding a task to the runtime database
     * @param t task
     * @return String to be wrapped and printed
     */
    public String add(Task t) {
        this.taskList.add(t);
        return this.echo(t);
    }

    /**
     * Returns string builder of the task
     *
     * @param task Task
     * @return String echow when task is completed
     */
    private String echo(Task task) {
        return new StringBuilder().append("\tGot it. I've added this task:\n\t  ")
                .append(task).append("\n\tNow you have ")
                .append(this.taskList.size())
                .append("tasks in the list.\n")
                .toString();
    }

    /**
     * Message Passing for Tasks
     * @throws DukeIoException if the task cannot be read to the file
     */
    public void saveTasks() throws DukeIoException {
        ioParser.writeTask(taskList);
    }
    /**
     * Parses the current list and prints the output
     * @return String representation of the Task List
     */
    public String listTasks() {
        return findTasks("");
    }
    /**
     * Regex pattern string search
     * @param pattern Regex Pattern or substring of description of any task in the list
     * @return String representation of duke.tasks that match the given pattern
     */
    public String findTasks(String pattern) {
        StringBuilder sb = new StringBuilder("");
        if (this.taskList.size() > 0) {
            Pattern stringPattern = Pattern.compile(pattern);
            AtomicInteger index = new AtomicInteger();
            sb.append(taskList.stream()
                    .filter(task -> {
                        index.incrementAndGet();
                        return stringPattern.matcher(task.getDescription()).find();
                        }
                    ).map(task -> String.format("\t%d. %s\n",index.get(),task))
                    .reduce("", (accumulate , next) -> accumulate + next));

            if (sb.toString().isEmpty()) {
                sb.append("\tCannot find a valid task in your list");
            }
        } else {
            sb.append("\tThere are no tasks in your list!\n");
        }
        return sb.toString();
    }
}
