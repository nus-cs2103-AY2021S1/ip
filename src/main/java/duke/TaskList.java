package duke;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskList {

    /**
     * Collection of Task objects for easy modification
     */
    protected List<Task> taskCollections;

    /**
     * Parser object to process input
     */
    protected Parser parser;

    /**
     * Directory of the file to store tasks
     */
    protected String memoFileDir;

    /**
     * Name of the file to store tasks
     */
    protected String memoFileName;


    /**
     * Constructor of TaskList class.
     * Initializes the taskCollections to be an empty ArrayList.
     *
     * @param taskCollections List of Task objects to initialize the class variable taskCollections.
     * @param memoFileDir     Directory of the file to store tasks.
     * @param memoFileName    Name of the file to store tasks.
     */
    public TaskList(List<Task> taskCollections, String memoFileDir, String memoFileName) {
        this.taskCollections = taskCollections;
        this.parser = new Parser();
        this.memoFileDir = memoFileDir;
        this.memoFileName = memoFileName;
    }


    /**
     * Another Constructor of TaskList class.
     * Initializes the taskCollections to be an empty ArrayList.
     *
     * @return Type, status, content, and empty datetime of Task object in the form of an array of Strings
     */
    public TaskList() {
        this.taskCollections = new ArrayList<>();
    }


    /**
     * Returns taskCollections of the TaskList object.
     *
     * @return Current list of Tasks.
     */
    public List<Task> showList() {
        return taskCollections;
    }


    /**
     * Returns List of the Task objects matching the keyword.
     *
     * @param keyword  User input of keyword to look for.
     * @return Matching Task objects (task content or datetime).
     */
    public List<Task> searchTask(String keyword) {
        List<Task> searchResult = new ArrayList<>();
        Iterator itr = taskCollections.iterator();
        String keywordLowerCase = keyword.toLowerCase();

        while (itr.hasNext()) {
            Task currTask = (Task) itr.next();
            if (currTask.getDescription().toLowerCase().contains(keywordLowerCase)) {
                searchResult.add(currTask);
                continue;
            }
            if (currTask.getType().equals("D") || currTask.getType().equals("E")) {
                // search in DateTime section
                if (currTask.getInfo()[3].toLowerCase().contains(keywordLowerCase)) {
                    searchResult.add(currTask);
                }
            }
        }

        return searchResult;
    }


    /**
     * Adds new Task object to the temporary collection and local memory.
     */
    public List<String> addTask(String[] commandArr) {
        String type = commandArr[0];
        String taskContent = commandArr[1];
        List<String> output = new ArrayList<>();

        Task t = type.equals("todo")
                ? new Todo(taskContent)
                : type.equals("event")
                ? new Event(taskContent, commandArr[2])
                : new Deadline(taskContent, commandArr[2]);
        try {
            taskCollections.add(t);
            new Storage(memoFileDir, memoFileName).appendToFile(memoFileDir + memoFileName, t, taskCollections);
            output.add(addSuccessMsg("add", t.toString()));
        } catch (Exception e) {
            output.addAll(HandleException.handleException(
                    DukeException.ExceptionType.READ_FILE));
        }
        return output;
    }


    /**
     * Modifies temporary tasklist and overwrite local memory.
     */
    public List<String> editTask(String[] commandArr) {
        List<String> output = new ArrayList<>();

        try {
            String actionType = commandArr[0];
            int taskNumber = Integer.parseInt(commandArr[1]);
            int actionNumber = taskNumber - 1;
            String taskContent = taskCollections.get(actionNumber).toString();

            editTaskCollections(actionType, actionNumber);
            boolean success = new Storage(memoFileDir, memoFileName).write_memory(taskCollections);
            if (success) {
                output.add(addSuccessMsg(actionType, taskContent));
            } else {
                output.addAll(HandleException.handleException(
                        DukeException.ExceptionType.READ_FILE
                ));
            }

        } catch (Exception ex) {
            output.addAll(HandleException.handleException(
                    DukeException.ExceptionType.EMPTY_ILLEGAL));
        }
        return output;
    }


    /**
     * Modifies local taskCollections in response to a 'done' or 'delete' command.
     */
    public void editTaskCollections(String actionType, int actionNumber) {
        switch (actionType) {
        case "delete":
            taskCollections.remove(actionNumber);
            break;
        case "done":
        default:
            taskCollections.get(actionNumber).markAsDone();
            break;
        }
    }


    /**
     * Adds output message after a successful 'done', 'delete' or 'add' action.
     */
    public String addSuccessMsg(String type, String taskContent) {
        switch (type) {
            case "delete":
                return "Noted. I've removed this task:\n" + taskContent
                        + "\nNow you have " + taskCollections.size() + " tasks in the list.";
            case "done":
                return "Nice! I've marked this task as done:\n" + " [\u2713] "
                        + taskContent.split("] ", 2)[1];
            case "add":
            default:
                return "Got it. I've added ths task:\n" + "  " + taskContent
                        + "\nNow you have " + taskCollections.size() + " tasks in the list.";
        }
    }

}
