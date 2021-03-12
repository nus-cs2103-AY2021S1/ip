import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Represents a list of tasks that can execute operations like
 * add and delete tasks inside.
 */
public class TaskList {
    public List<Task> list;
    public int noOfTasks;

    /**
     * Default constructor for a task list without any task.
     */
    public TaskList() {
        this.list = new ArrayList<>();
        this.noOfTasks = 0;
    }

    /**
     * Constructor for a task list with given String input.
     * @param tasks Multiple lines of strings that are formatted
     * in a specific way to represent task information of existing task(s).
     */
    public TaskList(String tasks) {
        this.list = new ArrayList<>();
        this.noOfTasks = 0;
        String lines[] = tasks.split("\\r?\\n");
        List<String> taskInfo = Arrays.asList(lines);

        if (!taskInfo.isEmpty()) {
            for (String command : taskInfo) {
                if (command.length() < 3) {
                    break;
                }
                command = command.substring(3);
                String[] pieces = command.split("]", 3);
                String type = pieces[0];
                String icon = pieces[1].substring(pieces[1].length() - 1); // status icon
                boolean isDone = (icon.equals("\u2713")); // \u2713 is the icon for ✓
                String description = "";
                String tag = null;
                if (pieces[2].contains("<")) {
                    String[] smallerPieces = pieces[2].split("<", 2);
                    description = (smallerPieces[0]).substring(1);
                    tag = smallerPieces[1].substring(0, smallerPieces[1].length() - 1);
                } else {
                    description = pieces[2].substring(1);
                }

                Task t = new Task("");
                String[] array;
                String OLD_FORMAT = "MMM dd yyyy";
                String NEW_FORMAT = "yyyy-MM-dd";

                switch (type) {
                    case "T":
                        t = new ToDo(description, isDone);
                        break;

                    case "D":
                        array = description.split("\\(by: ");

                        String oldDateString = array[1];
                        String newDateString;

                        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
                        Date d = null;
                        try {
                            d = sdf.parse(oldDateString);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        sdf.applyPattern(NEW_FORMAT);
                        newDateString = sdf.format(d);
                        t = new Deadline(array[0], isDone, newDateString);
                        break;

                    case "E":
                        array = description.split("\\(at: ");

                        String oldDateStr = array[1];
                        String newDateStr;

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(OLD_FORMAT);
                        Date date = null;
                        try {
                            date = simpleDateFormat.parse(oldDateStr);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        simpleDateFormat.applyPattern(NEW_FORMAT);
                        newDateStr = simpleDateFormat.format(date);
                        t = new Event(array[0], isDone, newDateStr);
                        break;

                    default:
                        break;
                }
                if (tag != null) {
                    t.setTag(tag);
                }
                list.add(t);
                noOfTasks++;
            }
        }
    }

    /**
     * A getter of number of tasks in the task list.
     * @param args unused
     * return Number of tasks
     */
    public int getNoOfTasks() {
        return this.noOfTasks;
    }

    /**
     * Add task to the task list.
     * @param task Task to be added
     * return nothing
     */
    public void addTask(Task task) {
        this.list.add(task);
        noOfTasks ++;
    }

    /**
     * Delete task from the task list.
     * @param task Task to be deleted
     * return nothing
     */
    public void deleteTask(Task task) {
        this.list.remove(task);
        noOfTasks --;
    }
}
