/**
 * Represents the task statistics.
 */
public class Statistics {
    private int numberOfTasks;
    private int numberOfEvents;
    private int numberOfDeadlines;
    private int numberOfTodos;
    private int numberOfCompletedTasks;

    /**
     * Creates a Statistics object.
     * Initially all statistics are set to zero.
     */
    public Statistics() {
        this.numberOfTasks = 0;
        this.numberOfEvents = 0;
        this.numberOfDeadlines = 0;
        this.numberOfTodos = 0;
        this.numberOfCompletedTasks = 0;
    }

    /**
     * Records a new event.
     */
    public void eventIncrement() {
        numberOfTasks++;
        numberOfEvents++;
    }

    /**
     * Records a new deadline.
     */
    public void deadlineIncrement() {
        numberOfTasks++;
        numberOfDeadlines++;
    }

    /**
     * Records a new todo.
     */
    public void todoIncrement() {
        numberOfTasks++;
        numberOfTodos++;
    }

    /**
     * Records a new completed task.
     */
    public void completeTask() {
        numberOfCompletedTasks++;
    }

    /**
     * Gets the total number of tasks.
     * @return number of tasks.
     */
    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    /**
     * Gets the total number of events.
     * @return number of events.
     */
    public int getNumberOfEvents() {
        return numberOfEvents;
    }

    /**
     * Gets the total number of todos.
     * @return number of todos.
     */
    public int getNumberOfTodos() {
        return numberOfTodos;
    }

    /**
     * Gets the total number of deadlines.
     * @return number of deadlines.
     */
    public int getNumberOfDeadlines() {
        return numberOfDeadlines;
    }

    /**
     * Gets the total number of completed tasks.
     * @return number of CompletedTasks.
     */
    public int getNumberOfCompletedTasks() {
        return numberOfCompletedTasks;
    }
}
