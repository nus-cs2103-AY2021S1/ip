package duke.task;

import duke.ui.Ui;

import static java.util.stream.Collectors.toCollection;

import java.util.ArrayList;

/**
 * Encapsulates the task list that Mrs Dino use to store tasks.
 */
public class TaskList {
    private ArrayList<Task> todoList;
    private Ui ui;

    /**
     * Constructs new TaskList object.
     */
    public TaskList() {
        this.todoList = new ArrayList<>();
        this.ui = new Ui();
    }

    /**
     * Prints all tasks in the TaskList object.
     */
    public void list() {
        this.ui.generateDivider();
        for (int i = 0; i < todoList.size(); i++) {
            int index = i + 1;
            this.ui.generateLeftPadding();
            System.out.println(index + ". " + todoList.get(i).toString());
        }
        this.ui.generateDivider();
    }

    /**
     * Retrieves a specific task from the TaskList, by index.
     *
     * @param index Index of the task to retrieve.
     * @return Task to be retrieved.
     */
    public Task get(int index) {
        return this.todoList.get(index);
    }

    /**
     * Removes  specific task from the TaskList, by index.
     *
     * @param index Index of the task to remove.
     */
    public void remove(int index) {
        this.todoList.remove(index);
    }

    /**
     * Gets the number of elements in the TaskList object.
     *
     * @return Number of elements in the TaskList object.
     */
    public int getSize() {
        return this.todoList.size();
    }

    /**
     * Retrieves the ArrayList of Tasks stored by the TaskList object.
     *
     * @return ArrayList of Tasks stored by the TaskList object.
     */
    public ArrayList<Task> getList() {
        return this.todoList;
    }

    /**
     * Adds a new Todo task.
     *
     * @param isCompleted Whether the task has been completed.
     * @param task Task to be added.
     */
    public void addTodo(String isCompleted, String task) {
        Todo newTodo = new Todo(isCompleted, task);
        this.todoList.add(newTodo);
    }

    /**
     * Adds a new Deadline task.
     *
     * @param isCompleted Whether the task has been completed.
     * @param task Task to be added.
     * @param deadline Date and time that task is due.
     */
    public void addDeadline(String isCompleted, String task, String deadline) {
        Deadline newDeadline = new Deadline(isCompleted, task, deadline);
        this.todoList.add(newDeadline);
    }

    /**
     * Adds a new Event task.
     *
     * @param isCompleted Whether the task has been completed.
     * @param task Task to be added.
     * @param eventDate Date and time that task occurs.
     */
    public void addEvent(String isCompleted, String task, String eventDate) {
        Event newEvent = new Event(isCompleted, task, eventDate);
        this.todoList.add(newEvent);
    }

    /**
     * Finds a list of tasks that matches the keywords given.
     *
     * @param keywords Keywords that tasks need to contain to be matched.
     */
    public void find(String[] keywords) {
        ArrayList<Task> tempArrayList = todoList.stream()
                .filter(task -> matchesAllKeywords(task, keywords)).collect(toCollection(ArrayList::new));
        this.ui.printMatchingTasks(tempArrayList);
    }

    /**
     * Find method specific to GUI.
     *
     * @param keywords Keywords that tasks need to contain to be matched.
     * @return ArrayList containing matching tasks.
     */
    public ArrayList<Task> findForGui(String[] keywords) {
        ArrayList<Task> tempArrayList = todoList.stream()
                .filter(task -> matchesAllKeywords(task, keywords)).collect(toCollection(ArrayList::new));
        return tempArrayList;
    }

    /**
     * For find methods with multiple keywords, checks if the task contains all the keywords.
     *
     * @param task Task to be checked for keywords.
     * @param keywords Keywords that task needs to contain in order to be matched.
     * @return Boolean value, true if task matches all the keywords given, false otherwise.
     */
    private boolean matchesAllKeywords(Task task, String[] keywords) {
        boolean containsKeyword = true;
        for (String keyword : keywords) {
            if (!task.toString().contains(keyword)) {
                containsKeyword = false;
                break;
            }
        }
        return containsKeyword;
    }
}
