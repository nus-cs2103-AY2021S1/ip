package duke;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Task Finder class is used to filter tasks based on keywords given
 * and returns the tasks in task list according to the filters.
 */
public class TaskFinder {
    /**
     * Searches through task list and
     * returns an array list of all tasks in the task list
     * that were marked as done.
     *
     * @param taskList list of tasks to filter.
     * @return ArrayList<Task> list of done tasks.
     */
    public ArrayList<Task> findAllDone(ArrayList<Task> taskList) {

        ArrayList<Task> tasksDone = taskList
                .stream()
                .filter(task -> task.isDone)
                .collect(Collectors.toCollection(ArrayList::new));

        return tasksDone;
    }

    /**
     * Searches through task list and
     * returns an array list of all tasks in the task list
     * that were not marked as done.
     *
     * @param taskList list of tasks to filter.
     * @return ArrayList<Task> list of done tasks.
     */
    public ArrayList<Task> findAllNotDone(ArrayList<Task> taskList) {

        ArrayList<Task> tasksNotDone = taskList
                .stream()
                .filter(task -> !task.isDone)
                .collect(Collectors.toCollection(ArrayList::new));

        return tasksNotDone;
    }

    /**
     * Searches through task list and
     * returns an array list of all to do tasks.
     *
     * @param taskList list of tasks to filter.
     * @return ArrayList<Task> list of done tasks.
     */
    public ArrayList<Task> findToDos(ArrayList<Task> taskList) {

        ArrayList<Task> toDos = taskList
                .stream()
                .filter(task ->
                        task.getClass().getSimpleName().equals("ToDo"))
                .collect(Collectors.toCollection(ArrayList::new));

        return toDos;
    }

    /**
     * Searches through task list and
     * returns an array list of all to do tasks
     * that were marked as done.
     *
     * @param taskList list of tasks to filter.
     * @return ArrayList<Task> list of done tasks.
     */
    public ArrayList<Task> findToDosDone(ArrayList<Task> taskList) {

        ArrayList<Task> toDosDone = taskList
                .stream()
                .filter(task ->
                        task.getClass().getSimpleName().equals("ToDo")
                                && task.isDone)
                .collect(Collectors.toCollection(ArrayList::new));

        return toDosDone;
    }

    /**
     * Searches through task list and
     * returns an array list of all to do tasks
     * that were not marked as done.
     *
     * @param taskList list of tasks to filter.
     * @return ArrayList<Task> list of done tasks.
     */
    public ArrayList<Task> findToDosNotDone(ArrayList<Task> taskList) {

        ArrayList<Task> toDosNotDone = taskList
                .stream()
                .filter(task ->
                        task.getClass().getSimpleName().equals("ToDo")
                                && !task.isDone)
                .collect(Collectors.toCollection(ArrayList::new));

        return toDosNotDone;
    }

    /**
     * Searches through task list and
     * returns an array list of all tasks with a deadline.
     *
     * @param taskList list of tasks to filter.
     * @return ArrayList<Task> list of done tasks.
     */
    public ArrayList<Task> findDeadlines(ArrayList<Task> taskList) {

        ArrayList<Task> deadlines = taskList
                .stream()
                .filter(task ->
                        task.getClass().getSimpleName().equals("Deadline"))
                .collect(Collectors.toCollection(ArrayList::new));

        return deadlines;
    }

    /**
     * Searches through task list and
     * returns an array list of all tasks with a deadline
     * that were marked as done.
     *
     * @param taskList list of tasks to filter.
     * @return ArrayList<Task> list of done tasks.
     */
    public ArrayList<Task> findDeadlinesDone(ArrayList<Task> taskList) {

        ArrayList<Task> deadlinesDone = taskList
                .stream()
                .filter(task ->
                        task.getClass().getSimpleName().equals("Deadline")
                                && task.isDone)
                .collect(Collectors.toCollection(ArrayList::new));

        return deadlinesDone;
    }

    /**
     * Searches through task list and
     * returns an array list of all tasks with a deadline
     * that were not marked as done.
     *
     * @param taskList list of tasks to filter.
     * @return ArrayList<Task> list of done tasks.
     */
    public ArrayList<Task> findDeadlinesNotDone(ArrayList<Task> taskList) {

        ArrayList<Task> deadlinesNotDone = taskList
                .stream()
                .filter(task ->
                        task.getClass().getSimpleName().equals("Deadline")
                                && !task.isDone)
                .collect(Collectors.toCollection(ArrayList::new));

        return deadlinesNotDone;
    }

    /**
     * Searches through task list and
     * returns an array list of all events.
     *
     * @param taskList list of tasks to filter.
     * @return ArrayList<Task> list of done tasks.
     */
    public ArrayList<Task> findEvents(ArrayList<Task> taskList) {

        ArrayList<Task> events = taskList
                .stream()
                .filter(task ->
                        task.getClass().getSimpleName().equals("Event"))
                .collect(Collectors.toCollection(ArrayList::new));

        return events;
    }

    /**
     * Searches through task list and
     * returns an array list of all events
     * that were marked as done.
     *
     * @param taskList list of tasks to filter.
     * @return ArrayList<Task> list of done tasks.
     */
    public ArrayList<Task> findEventsDone(ArrayList<Task> taskList) {

        ArrayList<Task> eventsDone = taskList
                .stream()
                .filter(task ->
                        task.getClass().getSimpleName().equals("Event")
                                && task.isDone)
                .collect(Collectors.toCollection(ArrayList::new));

        return eventsDone;
    }

    /**
     * Searches through task list and
     * returns an array list of all events
     * that were not marked as done.
     *
     * @param taskList list of tasks to filter.
     * @return ArrayList<Task> list of done tasks.
     */
    public ArrayList<Task> findEventsNotDone(ArrayList<Task> taskList) {

        ArrayList<Task> eventsNotDone = taskList
                .stream()
                .filter(task ->
                        task.getClass().getSimpleName().equals("Event")
                                && !task.isDone)
                .collect(Collectors.toCollection(ArrayList::new));

        return eventsNotDone;
    }

    /**
     * Searches through task list and
     * returns an array list of all tasks containing
     * given keyword.
     *
     * @param taskList list of tasks to filter.
     * @param keyword keyword to be used to filter tasks.
     * @return ArrayList<Task> list of tasks containing given keyword.
     */
    public ArrayList<Task> findByKeyword(ArrayList<Task> taskList, String keyword) {
        assert(!keyword.isEmpty());

        ArrayList<Task> filteredTasks = taskList
                .stream()
                .filter(task ->
                        task.getDescription().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));

        return filteredTasks;
    }
}
