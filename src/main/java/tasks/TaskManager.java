package tasks;

import exceptions.*;

import java.util.List;
import java.util.regex.Pattern;

/**
 * TaskManager is a class to handle where 
 */
public class TaskManager {
    private final List<Task> taskList;
    private final TaskIOParser ioparser;
    private final TextParser textParser;

    public TaskManager(String path) throws DukeIOException {
        this.ioparser = new TaskIOParser(path);
        this.taskList = ioparser.loadTaskList();
        this.textParser = new TextParser();
    }

    public TaskManager(String path, boolean isNew) {
        this.ioparser = new TaskIOParser(path);
        this.taskList = ioparser.loadNewTaskList();
        this.textParser = new TextParser();
    }

    /**
     * Parses the current list and prints the output
     * @return String representation of the Task List
     */
    public String parseoutput() {
        StringBuilder sb = new StringBuilder();
        if (this.taskList.size() > 0) {
            sb.append("\tHere are the tasks in your list:\n");
            for (int i = 0; i < this.taskList.size(); i++) {
                sb.append("\t").append(i + 1)
                        .append(". ")
                        .append(this.taskList.get(i).toString())
                        .append("\n");
            }
        } else {
            sb.append("\tThere are no tasks in your list!\n");
        }
        return sb.toString();
    }

    public String doTask(String index) throws DukeCommandException, DukeIndexException {
        try {
            int i = Integer.parseInt(index) - 1;//0 indexing
            this.getTask(i).doTask();
            return "\tNice! I've marked this task as done: \n\t" + this.getTask(i) + "\n";
        } catch (IllegalArgumentException e) {
            throw new DukeCommandException(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIndexException(index, taskList.size());
        }

    }

    public String deleteTask(String index) throws DukeCommandException, DukeIndexException {
        try {
            int i = Integer.parseInt(index) - 1;//0 indexing
            Task t = this.getTask(i);
            this.taskList.remove(i);
            return new StringBuilder().append("\tNoted! I've removed this task from your list: \n\t")
                    .append(t)
                    .append("\n\tNow you have ")
                    .append(this.taskList.size())
                    .append(" tasks in the list.\n").toString();
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
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * generic polymorphic data flow for adding a task to the runtime database
     * @param t task
     * @return String to be wrapped and printed
     */
    private String add(Task t) {
        this.taskList.add(t);
        return this.echo(t);
    }

    /**
     * Returns string builder of the task
     *
     * @param t
     * @return
     */
    private String echo(Task t) {
        return new StringBuilder().append("\tGot it. I've added this task:\n\t  ")
                .append(t).append("\n\tNow you have ")
                .append(this.taskList.size())
                .append(" tasks in the list.\n")
                .toString();
    }

    /**
     * Takes in command to add an "to do" task to task list
     * @param description Description of the Todo Task
     * @return String Representation of the Task to complete
     * @throws DukeNoInputException throw exception if there is no input given to the function
     */
    public String addToDo(String description) throws DukeNoInputException {
        if (description.isBlank()) {
            throw new DukeNoInputException(description);
        }
        ToDo task = new ToDo(description);
        return this.add(task);
    }

    /**
     * Takes in command to add an deadline task to task list
     *
     * @param userInput Input for the user
     * @return returns a string representation of the given input for use by the parser
     * @throws DukeDateTimeException throws the exception from textparser.extractTime
     * @throws DukeNoInputException If no description is given
     */
    public String addDeadline(String userInput) throws DukeDateTimeException, DukeNoInputException {
        if (userInput.isBlank()) {
            throw new DukeNoInputException(userInput);
        }
        String[] timeSEP = textParser.extractTime(userInput);
        Deadline d = new Deadline(timeSEP[0], timeSEP[1]);
        return add(d);
    }

    /**
     * Takes in command to add an event task to task list
     * @param cmd the description of the task
     * @return returns a string representation of the given input for use by the parser
     * @throws DukeDateTimeException throws the exception from textparser.extractTime
     * @throws DukeNoInputException If no description is given
     */

    public String addEvent(String cmd) throws DukeDateTimeException, DukeNoInputException {
        if (cmd.isBlank()) {
            throw new DukeNoInputException(cmd);
        }
        String[] timeSEP = textParser.extractTime(cmd);
        Event e = new Event(timeSEP[0], timeSEP[1]);
        return add(e);
    }

    /**
     * Message Passing for Tasks
     * @throws DukeIOException if the task cannot be read to the file
     */
    public void saveTasks() throws DukeIOException {
        ioparser.writeTask(taskList);
    }

    /**
     * Regex pattern string search
     * @param pattern Regex Pattern or substring of description of any task in the list
     * @return String representation of tasks that match the given pattern
     */
    public String findTasks(String pattern) {
        StringBuilder sb = new StringBuilder("");
        if (this.taskList.size()>0) {
            Pattern stringPattern = Pattern.compile(pattern); 
            for (int i=0; i<taskList.size(); i++){
                if (stringPattern.matcher(taskList.get(i).getDescription()).find()){
                    sb.append("\t").append(i + 1)
                            .append(". ")
                            .append(this.taskList.get(i).toString())
                            .append("\n");
                }
            }
            if (sb.toString().isEmpty()){
                sb.append("\tCannot find a valid task in your list");
            }
        }else{
            sb.append("\tThere are no tasks in your list!\n");
        }
        return sb.toString();
    }
}
