import java.text.ParseException;

import java.util.ArrayList;

import java.util.List;
import java.util.Date;

import java.text.SimpleDateFormat;

public class TaskList {
    protected List<Task> tasks;
    protected int taskCounts;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.taskCounts = 0;
    }

    public TaskList(List<String> data) {

        if (!data.isEmpty()) {

            for (String command : data) {
                command = command.substring(3);
                String[] commandPortions = command.split("]", 3);
                String category = commandPortions[0];
                String mark = commandPortions[1].substring(commandPortions[1].length() - 1);
                int status = (mark == "\u2713") ? Task.DONE : Task.DOING;
                String commandDetails = commandPortions[2].substring(1, commandPortions[2].length());

                Task task = new Task(0, 0, "");
                String[] array;
                String ORIGINAL_FORMAT = "MMM dd yyyy";
                String UPDATED_FORMAT = "yyyy/MM/dd";

                switch (category) {
                    case "T":
                        task = new ToDo(Task.TASK_TODO, status, commandDetails);
                        break;

                    case "D":
                        String[] deadlineSplitter = commandDetails.split("by: ");

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
                        task = new Deadline(Task.TASK_DEADLINE, status, deadlineSplitter[0], updatedDate);
                        break;

                    case "E":
                        String[] eventSplitter = commandDetails.split("at: ");

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
                        task = new Deadline(Task.TASK_EVENT, status, eventSplitter[0], updatedEventDate);
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
    }

    public int getTaskCounts() {
        return this.taskCounts;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        taskCounts++;
    }

    public void deleteTask(Task task) {
        this.tasks.remove(task);
        taskCounts--;
    }
}