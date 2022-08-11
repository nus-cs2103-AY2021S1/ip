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
        parser = new Parser();
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
            String taskContentLowerCase = currTask.getDescription().toLowerCase();
            // If keyword is present in the task content, it is a matching result
            if (taskContentLowerCase.contains(keywordLowerCase)) {
                searchResult.add(currTask);
                continue;
            }

            // search keyword in DateTime section of a 'Event' or 'Deadline' task
            String taskType = currTask.getType();
            if (taskType.equals("D") || taskType.equals("E")) {
                String dateTimeLowerCase = currTask.getInfo()[3].toLowerCase();
                if (dateTimeLowerCase.contains(keywordLowerCase)) {
                    searchResult.add(currTask);
                }
            }
        }

        return searchResult;
    }


    /**
     * Returns output array after a successful 'done' or 'delete' action.
     * Adds new Task object to the temporary collection and local memory.
     *
     * @param commandArr  processed user input of 'done' or 'delete' command.
     * @return  output array after a successful 'modify' operation.
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
            // Add the task to the current task list
            taskCollections.add(t);

            // Update the local memory file with the new task
            Storage s = new Storage(memoFileDir, memoFileName);
            boolean success = s.appendToFile(memoFileDir + memoFileName, t, taskCollections);

            if (success) {
                output.add(addSuccessMsg("add", t.toString()));
            } else {
                output.addAll(HandleException.handleException(
                        DukeException.ExceptionType.READ_FILE
                ));
            }
        } catch (Exception e) {
            output.addAll(HandleException.handleException(
                    DukeException.ExceptionType.READ_FILE));
        }
        return output;
    }


    /**
     * Returns output array after a successful 'done' or 'delete' action.
     * Modifies temporary tasklist and overwrite local memory.
     *
     * @param commandArr  processed user input of 'done' or 'delete' command.
     * @return  output array after a successful 'modify' operation.
     */
    public List<String> editTask(String[] commandArr) {
        List<String> output = new ArrayList<>();

        try {
            String actionType = commandArr[0];
            int taskNumber = Integer.parseInt(commandArr[1]);
            int actionNumber = taskNumber - 1;
            String taskContent = taskCollections.get(actionNumber).toString();

            // Update the task list with the new task status
            editTaskCollections(actionType, actionNumber);
            // Update local file with new task status
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
     *
     * @param actionType  type of 'modification' command.
     * @param actionNumber  actual index of task in taskCollections (starts from 0).
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
     * Returns output message after a successful 'done', 'delete' or 'add' action.
     *
     * @param type  type of command.
     * @param taskContent  content of the task that is added or modified.
     * @return  success message after a successful 'add' or 'modify' operation.
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
