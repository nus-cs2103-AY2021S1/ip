package duke;

import java.util.ArrayList;
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
     * @param taskList list of tasks to filter
     * @return ArrayList<Task> list of done tasks
     */
    public ArrayList<Task> findAllDone(ArrayList<Task> taskList) {
        ArrayList<Task> tasksDone = new ArrayList<Task>();
        for (Task task : taskList) {
            if (task.isDone) {
                tasksDone.add(task);
            }
        }
        return tasksDone;
    }

    /**
     * Searches through task list and
     * returns an array list of all tasks in the task list
     * that were not marked as done.
     *
     * @param taskList list of tasks to filter
     * @return ArrayList<Task> list of done tasks
     */
    public ArrayList<Task> findAllNotDone(ArrayList<Task> taskList) {
        ArrayList<Task> tasksNotDone = new ArrayList<Task>();
        for (Task task : taskList) {
            if (!task.isDone) {
                tasksNotDone.add(task);
            }
        }
        return tasksNotDone;
    }

    /**
     * Searches through task list and
     * returns an array list of all to do tasks.
     *
     * @param taskList list of tasks to filter
     * @return ArrayList<Task> list of done tasks
     */
    public ArrayList<Task> findAllToDos(ArrayList<Task> taskList) {
        ArrayList<Task> toDos = new ArrayList<Task>();
        for (Task task : taskList) {
            if (task.getClass().getSimpleName() == "ToDo") {
                toDos.add(task);
            }
        }
        return toDos;
    }

    /**
     * Searches through task list and
     * returns an array list of all tasks with a deadline.
     *
     * @param taskList list of tasks to filter
     * @return ArrayList<Task> list of done tasks
     */
    public ArrayList<Task> findAllDeadlines(ArrayList<Task> taskList) {
        ArrayList<Task> deadlines = new ArrayList<Task>();
        for (Task task : taskList) {
            if (task.getClass().getSimpleName() == "Deadline") {
                deadlines.add(task);
            }
        }
        return deadlines;
    }

    /**
     * Searches through task list and
     * returns an array list of all events.
     *
     * @param taskList list of tasks to filter
     * @return ArrayList<Task> list of done tasks
     */
    public ArrayList<Task> findAllEvents(ArrayList<Task> taskList) {
        ArrayList<Task> events = new ArrayList<Task>();
        for (Task task : taskList) {
            if (task.getClass().getSimpleName().equals("Event")) {
                events.add(task);
            }
        }
        return events;
    }

    /**
     * Searches through task list and
     * returns an array list of all tasks containing
     * given keyword.
     *
     * @param taskList list of tasks to filter
     * @param keyword key word to be used to filter tasks
     * @return ArrayList<Task> list of tasks containing given keyword
     */
    public ArrayList<Task> findByKeyword(ArrayList<Task> taskList, String keyword) {
        return new ArrayList<>();
    }
}
