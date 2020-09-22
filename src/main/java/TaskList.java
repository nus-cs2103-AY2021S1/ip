import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

/**
 * TaskList stores an ArrayList of Task to be used by Duke.
 */
public class TaskList {

    protected ArrayList<Task> tasks;

    /**
     * TaskList constructor initializing empty ArrayList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * TaskList constructor specifying an ArrayList to be used.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Sort TaskList by description.
     * @return TaskList
     */
    public TaskList sortByDescription() {
        Collections.sort(tasks, new DescripComparator());
        return this;
    }

    /**
     * Sort TaskList by due date and time.
     * @return TaskList
     */
    public TaskList sortByDueDateTime() {
        Collections.sort(tasks, new DateComparator());
        return this;
    }

    /**
     * Return the number of Tasks saved in TaskList.
     *
     * @return int
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Return a task which occupies in the position of TaskList.
     *
     * @param pos int
     * @return Task
     * @throws IndexExceedException
     */
    public Task getTask(int pos) throws IndexExceedException {
        if (pos + 1 > tasks.size()) {
            throw new IndexExceedException();
        }
        return tasks.get(pos);
    }

    /**
     * Remove a task which occupies in the position of TaskList.
     *
     * @param pos int
     */
    public void removeTask(int pos) {
        tasks.remove(pos);
    }

    /**
     * Add a task into TaskList at the last position.
     *
     * @param task
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Test sorting
     * @param args
     */
    public static void main(String[] args) {
        try {
            ArrayList<Task> tasksArrayList = new ArrayList<>();
            String[] tempDateTime1 = {"11-09-2020", "18:00"};
            String[] tempDateTime2 = {"11-09-2020", "10:00"};
            String[] tempDate1 = {"09/09/2020"};
            String[] tempDate2 = {"12/09/2020"};
            tasksArrayList.add(new Todo("read book"));
            tasksArrayList.add(new Deadline("return book", LocalDate.now()));
            tasksArrayList.add(new Deadline("return book", Parser.changeDate(tempDate1)));
            tasksArrayList.add(new Deadline("return book", Parser.changeDate(tempDate2)));
            tasksArrayList.add(new Event("dinner with family", Parser.changeDateAndTime(tempDateTime1)));
            tasksArrayList.add(new Event("dinner with friends", Parser.changeDateAndTime(tempDateTime2)));
            TaskList tasks = new TaskList(tasksArrayList);
            tasks.sortByDueDateTime();
            Ui ui = new Ui();
            System.out.println(ui.printAllTask(tasks));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
