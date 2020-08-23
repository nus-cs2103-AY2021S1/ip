import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    TaskList () {
        tasks = new ArrayList<Task>();
    }

    public void creatingFromStorage (String str) throws ParseException {
        // System.out.println(str);
        boolean isDone = str.charAt(4) == '\u2713';
        Task task;
        if (str.charAt(1) == 'T') { // TODO
            String decription = str.substring(str.indexOf(' ') + 1);
            task = new Todo(decription);
            tasks.add(task);
        } else if (str.charAt(1) == 'D') { // DEADLINE
            String description = str.substring(str.indexOf(' ') + 1, str.indexOf("(by: "));
            String by = str.substring(str.indexOf("(by: ") + 5, str.length() - 1); //Aug 28 2020 4:00 pm
            int thirdIndex = by.indexOf(' ', 10);
            String dt = by.substring(0, thirdIndex);
            String tm = by.substring(thirdIndex + 1);
            LocalDate date = LocalDate.parse(dt, DateTimeFormatter.ofPattern("MMM d yyyy"));
            task = new Deadline(description, tm, date);
            tasks.add(task);
        } else { // EVENT
            String description = str.substring(str.indexOf(' ') + 1, str.indexOf("(at: "));
            String at = str.substring(str.indexOf("(at: ") + 5, str.length() - 1);
            int thirdIndex = at.indexOf(' ', 10);
            String dt = at.substring(0, thirdIndex);
            String tm = at.substring(thirdIndex + 1);
            LocalDate date = LocalDate.parse(dt, DateTimeFormatter.ofPattern("MMM d yyyy"));
            task = new Event(description, tm, date);
            tasks.add(task);
        }

        if (isDone) {
            task.setDone();
        }
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void addToList (Task task) {
        (this.tasks).add(task);
    }

    public String getListedTasks () {
        int i = 0;
        String all_tasks = "\n    ";
        for (Task task : (this.tasks)) {
            i++;
            all_tasks = all_tasks + i + ". " + task.getDescription() + "\n    ";
        }
        if (i == 0) {
            all_tasks = all_tasks + "The task list is currently empty.\n    ";
        }
        return all_tasks;
    }

    public String getScheduledTasks (LocalDate date) {
        String message = "\n    ";
        int counter = 0;
        for (Task task: tasks) {
            if (task instanceof TimedTask) {
                TimedTask timedTask = (TimedTask)task;
                if (date.equals(timedTask.getDate())) {
                    message = message + String.format("%d. %s\n    ", ++counter, timedTask.getDescription());
                }
            }
        }
        return counter == 0 ? message + "The task list is currently empty.\n    " : message;
    }
    public Task getTask (int index) {
        return this.tasks.get(index);
    }

    public void removeTask (int index) {
        this.tasks.remove(index);
    }
 }
