import java.util.ArrayList;

/**
 * Represents the task list of the Duke application. The task list is
 * responsible for storing and modifying the current tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<String> savedTasks) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (String savedTask : savedTasks) {
            Task processedTask = processTask(savedTask);
            tasks.add(processedTask);
        }
        this.tasks = tasks;
    }

    private Task processTask(String savedTask) {
        String[] taskDetails = savedTask.split(" \\| ");
        String taskType = taskDetails[0];
        Task processedTask = null;
        if (taskType.equals("T")) {
            String todoDescription = taskDetails[2];
            ToDo todo = new ToDo(todoDescription);
            boolean isTodoDone = taskDetails[1].equals("1");
            if (isTodoDone) {
                todo.markAsDone();
            }
            processedTask = todo;
        }
        if (taskType.equals("D")) {
            String deadlineDescription = taskDetails[2];
            String deadlineDate = taskDetails[3];
            Deadline deadline = new Deadline(deadlineDescription, deadlineDate);
            boolean isDeadlineDone = taskDetails[1].equals("1");
            if (isDeadlineDone) {
                deadline.markAsDone();
            }
            processedTask = deadline;
        }
        if (taskType.equals("E")) {
            String eventDescription = taskDetails[2];
            String eventDate = taskDetails[3];
            Event event = new Event(eventDescription, eventDate);
            boolean isEventDone = taskDetails[1].equals("1");
            if (isEventDone) {
                event.markAsDone();
            }
            processedTask = event;
        }
        return processedTask;
    }

    /**
     * Returns the current size of the task list.
     * @return Current size of the task list.
     */
    public int getLength() {
        return tasks.size();
    }

    /**
     * Returns the task list.
     * @return Task list.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Creates and sends a list of String objects representing the
     * tasks in the task list to the user interface of the Duke
     * application for display.
     * @param ui User interface of the Duke application.
     */
    public String showList(Ui ui) {
        ArrayList<Task> displayedTasks = new ArrayList<>();
        for (Task task : tasks) {
            displayedTasks.add(task);
        }
        return ui.showList(displayedTasks);
    }

    /**
     * Marks a specified task as done and alerts the user interface
     * of the Duke application to display the corresponding message.
     * @param taskPosition Position of the task to be marked as done in the task list.
     * @param ui User interface of the Duke application.
     */
    public String markDone(int taskPosition, Ui ui) {
        Task doneTask = tasks.get(taskPosition);
        doneTask.markAsDone();
        return ui.showDone(doneTask);
    }

    public String deleteTasks(ArrayList<String> deletedTaskPositions, Ui ui) {
        ArrayList<Task> removedTasks = new ArrayList<>();
        ArrayList<Task> remainingTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            String taskPosition = Integer.toString(i);
            if (deletedTaskPositions.contains(taskPosition)) {
                removedTasks.add(tasks.get(i));
            } else {
                remainingTasks.add(tasks.get(i));
            }
        }
        tasks.clear();
        for (Task task : remainingTasks) {
            tasks.add(task);
        }
        return ui.showDelete(removedTasks, tasks.size());
    }

    public String deleteAll(Ui ui) {
        ArrayList<Task> removedTasks = new ArrayList<>();
        for (Task task : tasks) {
            removedTasks.add(task);
        }
        tasks.clear();
        return ui.showDelete(removedTasks, tasks.size());
    }

    /**
     * Adds a new task to the task list and alerts the user interface
     * of the Duke application to display the corresponding message.
     * @param task New task to be added to the task list.
     * @param ui User interface of the Duke application.
     */
    public String addTask(Task task, Ui ui) {
        tasks.add(task);
        return ui.showAdd(task, tasks.size());
    }

    /**
     * Finds the tasks in the task list that match the given keyword.
     * @param keyword Keyword given by the user.
     * @param ui User interface of the Duke application.
     */
    public String findTask(String keyword, Ui ui) {
        ArrayList<String> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                matchingTasks.add(task.toString());
            }
        }
        return ui.showFind(matchingTasks);
    }
}
