import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the list of saved tasks.
 */
public class TaskList {

    private List<Task> taskList = new ArrayList<>();

    /**
     * Generates an Task list based on the retrieved file of the user's saved tasks.
     * @param file The file that the task list is to be generated from.
     * @throws FileNotFoundException If the file does not exist.
     */
    public void generateList(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();
            Task task = convertToTask(nextLine);
            taskList.add(task);
        }
    }

    /**
     * Adds new tasks to the list of tasks.
     * @param task to be added into the task list.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Returns the number of tasks in the list.
     * @return Size of the task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns the task at the specified index.
     * @param index of the task to retrieve
     * @return Task at the specified index of the TaskList.
     */
    public Task getTask(int index) {
        assert(index >= 0 && index < taskList.size());
        return taskList.get(index);
    }

    /**
     * Changes the task at the specified index.
     * @param index The index of the task that we wish to change.
     * @param newTask The new task that is about to replace the old one.
     */
    public void changeTask(int index, Task newTask) {
        this.taskList.set(index, newTask);
    }

    /**
     * Deletes the task at the specific index.
     * @param index of the task to delete.
     */
    public void delete(int index) {
        assert(index >= 0 && index < taskList.size());
        taskList.remove(index);
    }

    /**
     * Marks the task as done in the specified index.
     * @param index of the task that is done.
     */
    public void markDone(int index) {
        assert(index >= 0 && index < taskList.size());
        taskList.set(index, taskList.get(index).markDone());
    }

    /**
     * Changes the description of the task at the specified index.
     * @param index The index of the task that we are wish to change the description of.
     * @param newDesc The new description of the task.
     */
    public void changeDescription(int index, String newDesc) {
        taskList.set(index, taskList.get(index).changeDesc(newDesc));
    }

    /**
     * Modifies the time of the given event or deadline task.
     * @param index The index of the task.
     * @param newTime The new time of the task.
     */
    public void modifyTime(int index, LocalTime newTime) {
        Task taskToBeModified = taskList.get(index);

        if (taskToBeModified instanceof Deadline) {
            Deadline task = (Deadline) taskToBeModified;
            taskList.set(index, task.changeTime(newTime));
        } else if (taskToBeModified instanceof Event) {
            Event task = (Event) taskToBeModified;
            taskList.set(index, task.changeTime(newTime));
        }
    }

    /**
     * Modifies the date of the given event or deadline.
     * @param index The index of the task.
     * @param newDate The new date of the task.
     */
    public void modifyDate(int index, LocalDate newDate) {
        Task taskToBeModified = taskList.get(index);

        if (taskToBeModified instanceof Deadline) {
            Deadline task = (Deadline) taskToBeModified;
            taskList.set(index, task.changeDate(newDate));
        } else if (taskToBeModified instanceof Event) {
            Event task = (Event) taskToBeModified;
            taskList.set(index, task.changeDate(newDate));
        }
    }

    //Method that converts the saved tasks in the file which is in String format
    //into Task objects in the task list.
    private static Task convertToTask(String line) {

        if (line.startsWith("[T]", 2)) {
            //It is an simple Task.
            return convertToTodoTask(line);
        } else if (line.startsWith("[E]", 2)) {

            //It is an event task.
            return convertToEventTask(line);
        } else {
            //Is a deadline task
            return convertToDeadlineTask(line);
        }
    }

    private static Task convertToTodoTask(String line) {
        String[] parts = line.split(" ", 2);

        if (line.contains("[✘]")) {
            return new Task(parts[1]);
        } else {
            return new Task(parts[1]).markDone();
        }
    }

    private static Event convertToEventTask(String line) {
        DateTimeFormatter myDateFormat = DateTimeFormatter.ofPattern("d MMM yyyy");
        DateTimeFormatter myTimeFormat = DateTimeFormatter.ofPattern("h:mm a");

        String[] parts = line.split(" ", 2);
        String[] split = parts[1].split("\\(at:");

        String desc = split[0];
        String timeInfo = split[1].split("\\)")[0];

        String[] dateTime = timeInfo.trim().split(", ");
        String date = dateTime[1];
        String time = dateTime[2];

        if (line.contains("[✘]")) {
            return new Event(desc, LocalDate.parse(date, myDateFormat), LocalTime.parse(time, myTimeFormat));
        } else {
            return new Event(desc, LocalDate.parse(date, myDateFormat),
                    LocalTime.parse(time, myTimeFormat)).markDone();
        }
    }

    private static Deadline convertToDeadlineTask(String line) {
        DateTimeFormatter myDateFormat = DateTimeFormatter.ofPattern("d MMM yyyy");
        DateTimeFormatter myTimeFormat = DateTimeFormatter.ofPattern("h:mm a");

        String[] parts = line.split(" ", 2);
        String[] split = parts[1].split("\\(by:");

        String desc = split[0];
        String timeInfo = split[1].split("\\)")[0];

        String[] dateTime = timeInfo.trim().split(", ");
        String date = dateTime[1];
        String time = dateTime[2];

        if (line.contains("[✘]")) {
            return new Deadline(desc, LocalDate.parse(date, myDateFormat), LocalTime.parse(time, myTimeFormat));
        } else {
            return new Deadline(desc, LocalDate.parse(date, myDateFormat),
                    LocalTime.parse(time, myTimeFormat)).markDone();
        }
    }
}
