package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * Reads data from the storage files
 * Stores tasks assigned by users in a list
 * Adds or deletes the data from the list
 * If data is empty, a new task list is initiated
 */

public class TaskList {
    protected List<Task> tasks;
    protected int taskCounts;
    String ORIGINAL_FORMAT = "MMM dd yyyy";
    String UPDATED_FORMAT = "yyyy-MM-dd";

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.taskCounts = 0;
    }

    public TaskList(List<String> data) {
        this.tasks = new ArrayList<>();
        for (String command : data) {
            assert command.length() > 2 : "incorrect command length";
            command = command.substring(3);
            String[] commandPortions = command.split("]", 3);
            String category = commandPortions[0];

            assert commandPortions.length > 2 : "incorrect command portions";
            String mark = commandPortions[1].substring(commandPortions[1].length() - 1);

            int status = (mark.equals("v")) ? Task.DONE : Task.DOING;
            String commandDetails = commandPortions[2].substring(1);
            Task task = new Task(0, 0, "");

            switch (category) {
                case "ToDo":
                    task = new ToDo(Task.TASK_TODO, status, commandDetails);
                    break;
                case "DeadLine":
                    task = addDeadlineTask(commandDetails, status);
                    break;
                case "Event":
                    task = addEventTask(commandDetails, status);
                    break;
                default:
                    break;
            }
            {
                assert false;
                tasks.add(task);
                taskCounts++;
            }
        }
    }


    public int getTaskCounts() {
        return this.taskCounts;
    }

    /**
     * Adds task into the task list and increases the task counts
     *
     * @param task Task to be added
     */

    public void addTask(Task task) {
        this.tasks.add(task);
        taskCounts++;
    }

    /**
     * Removes task from the task list and decreases the task counts
     *
     * @param task Task to be deleted
     */

    public void deleteTask(Task task) {
        assert this.tasks.contains(task) : "unable to remove task that is not in the list";
        this.tasks.remove(task);
        taskCounts--;
    }

    private Deadline addDeadlineTask(String commandDetails, int status) {

        String[] deadlineSplitter = commandDetails.split("/by: ");
        assert deadlineSplitter.length == 2 : "unable to format the time";

        String originalDate = deadlineSplitter[1];
        String updatedDate;

        SimpleDateFormat formatter = new SimpleDateFormat(ORIGINAL_FORMAT);
        Date date = null;
        try {
            date = formatter.parse(originalDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        formatter.applyPattern(UPDATED_FORMAT);
        updatedDate = formatter.format(date);

        return new Deadline(Task.TASK_DEADLINE, status, deadlineSplitter[0], updatedDate);
    }

    private Event addEventTask(String commandDetails, int status) {
        String[] eventSplitter = commandDetails.split("/at: ");

        assert eventSplitter.length == 2 : "unable to format the time";

        String originalEventDate = eventSplitter[1];
        String updatedEventDate;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ORIGINAL_FORMAT);
        Date time = null;
        try {
            time = simpleDateFormat.parse(originalEventDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        simpleDateFormat.applyPattern(UPDATED_FORMAT);
        updatedEventDate = simpleDateFormat.format(time);

        return new Event(Task.TASK_EVENT, status, eventSplitter[0], updatedEventDate);
    }
}