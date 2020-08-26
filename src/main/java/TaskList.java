import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Date;
import java.text.SimpleDateFormat;

public class TaskList {
    public List<Task> list;
    public int noOfTasks;

    public TaskList() {
        this.list = new ArrayList<>();
        this.noOfTasks = 0;
    }

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
                command = command.substring(3, command.length());
                String[] pieces = command.split("]", 3);
                String type = pieces[0];
                String icon = pieces[1].substring(pieces[1].length() - 1); // status icon
                boolean isDone = (icon == "\u2713") ? true : false;
                String description = pieces[2].substring(1, pieces[2].length());

                Task t = new Task("");
                String[] array;
                String OLD_FORMAT = "MMM dd yyyy";
                String NEW_FORMAT = "yyyy/MM/dd";

                switch (type) {
                    case "T":
                        t = new ToDo(description, isDone);
                        break;

                    case "deadline":
                        array = description.split("(by: ");

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

                    case "event":
                        array = description.split("(at: ");

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
                        t = new Deadline(array[0], isDone, newDateStr);
                        break;

                    default:
                        break;
                }
                list.add(t);
                noOfTasks++;
            }
        }
    }

    public int getNoOfTasks() {
        return this.noOfTasks;
    }

    public void addTask(Task task) {
        this.list.add(task);
        noOfTasks ++;
    }

    public void deleteTask(Task task) {
        this.list.remove(task);
        noOfTasks --;
    }
}
