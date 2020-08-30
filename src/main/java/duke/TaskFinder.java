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
    public ArrayList<Task> findToDos(ArrayList<Task> taskList) {
        ArrayList<Task> toDos = new ArrayList<Task>();
        for (Task task : taskList) {
            if (task.getClass().getSimpleName().equals("ToDo")) {
                toDos.add(task);
            }
        }
        return toDos;
    }

    /**
     * Searches through task list and
     * returns an array list of all to do tasks
     * that were marked as done.
     *
     * @param taskList list of tasks to filter
     * @return ArrayList<Task> list of done tasks
     */
    public ArrayList<Task> findToDosDone(ArrayList<Task> taskList) {
        ArrayList<Task> toDosDone = new ArrayList<Task>();
        for (Task task : taskList) {
            if (task.getClass().getSimpleName().equals("ToDo")
                    && task.isDone) {
                toDosDone.add(task);
            }
        }
        return toDosDone;
    }

    /**
     * Searches through task list and
     * returns an array list of all to do tasks
     * that were not marked as done.
     *
     * @param taskList list of tasks to filter
     * @return ArrayList<Task> list of done tasks
     */
    public ArrayList<Task> findToDosNotDone(ArrayList<Task> taskList) {
        ArrayList<Task> toDosNotDone = new ArrayList<Task>();
        for (Task task : taskList) {
            if (task.getClass().getSimpleName().equals("ToDo")
                    && !task.isDone) {
                toDosNotDone.add(task);
            }
        }
        return toDosNotDone;
    }

    /**
     * Searches through task list and
     * returns an array list of all tasks with a deadline.
     *
     * @param taskList list of tasks to filter
     * @return ArrayList<Task> list of done tasks
     */
    public ArrayList<Task> findDeadlines(ArrayList<Task> taskList) {
        ArrayList<Task> deadlines = new ArrayList<Task>();
        for (Task task : taskList) {
            if (task.getClass().getSimpleName().equals("Deadline")) {
                deadlines.add(task);
            }
        }
        return deadlines;
    }

    /**
     * Searches through task list and
     * returns an array list of all tasks with a deadline
     * that were marked as done.
     *
     * @param taskList list of tasks to filter
     * @return ArrayList<Task> list of done tasks
     */
    public ArrayList<Task> findDeadlinesDone(ArrayList<Task> taskList) {
        ArrayList<Task> deadlinesDone = new ArrayList<Task>();
        for (Task task : taskList) {
            if (task.getClass().getSimpleName().equals("Deadline")
                    && task.isDone) {
                deadlinesDone.add(task);
            }
        }
        return deadlinesDone;
    }

    /**
     * Searches through task list and
     * returns an array list of all tasks with a deadline
     * that were not marked as done.
     *
     * @param taskList list of tasks to filter
     * @return ArrayList<Task> list of done tasks
     */
    public ArrayList<Task> findDeadlinesNotDone(ArrayList<Task> taskList) {
        ArrayList<Task> deadlinesNotDone = new ArrayList<Task>();
        for (Task task : taskList) {
            if (task.getClass().getSimpleName().equals("Deadline")
                    && !task.isDone) {
                deadlinesNotDone.add(task);
            }
        }
        return deadlinesNotDone;
    }

    /**
     * Searches through task list and
     * returns an array list of all events.
     *
     * @param taskList list of tasks to filter
     * @return ArrayList<Task> list of done tasks
     */
    public ArrayList<Task> findEvents(ArrayList<Task> taskList) {
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
     * returns an array list of all events
     * that were marked as done.
     *
     * @param taskList list of tasks to filter
     * @return ArrayList<Task> list of done tasks
     */
    public ArrayList<Task> findEventsDone(ArrayList<Task> taskList) {
        ArrayList<Task> eventsDone = new ArrayList<Task>();
        for (Task task : taskList) {
            if (task.getClass().getSimpleName().equals("Event")
                    && task.isDone) {
                eventsDone.add(task);
            }
        }
        return eventsDone;
    }

    /**
     * Searches through task list and
     * returns an array list of all events
     * that were not marked as done.
     *
     * @param taskList list of tasks to filter
     * @return ArrayList<Task> list of done tasks
     */
    public ArrayList<Task> findEventsNotDone(ArrayList<Task> taskList) {
        ArrayList<Task> eventsNotDone = new ArrayList<Task>();
        for (Task task : taskList) {
            if (task.getClass().getSimpleName().equals("Event")
                    && !task.isDone) {
                eventsNotDone.add(task);
            }
        }
        return eventsNotDone;
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
        ArrayList<Task> filteredTasks = new ArrayList<Task>();
        for (Task task: taskList) {
            if (task.getDescription().contains(keyword)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }
}
