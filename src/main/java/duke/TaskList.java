package duke;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * A class that stores a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> list;
    private int count;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
        this.count = 0;
    }

    /**
     * Constructs a TaskList with pre-existing Tasks from given Optional of a Stream of Strings.
     * @param content  Optional of a Stream of Strings with supposed input of the save file.
     */
    public TaskList(Optional<Stream<String>> content) {
        this.list = new ArrayList<>();
        this.count = 0;
        if (content.isEmpty()) {
            //do nothing
        } else {
            Stream<String> saveContent = content.get();
            saveContent.forEach((line) -> {
                char startChar = line.charAt(0);
                switch (startChar) {
                case 'T': {
                    ToDo taskToAdd = Parser.parseToDoFromSave(line);
                    this.list.add(taskToAdd);
                    this.count++;
                    break;
                }
                case 'D': {
                    Deadline taskToAdd = Parser.parseDeadlineFromSave(line);
                    this.list.add(taskToAdd);
                    this.count++;
                    break;
                }
                case 'E': {
                    Event taskToAdd = Parser.parseEventFromSave(line);
                    this.list.add(taskToAdd);
                    this.count++;
                    break;
                }
                default: {
                }
                }
            });
            saveContent.close();
        }

    }

    /**
     * Adds a task into the TaskList.
     * @param task  The task to be added.
     * @return  String representation of the task added.
     */
    public String addTask(Task task) {
        assert task != null : "trying to add a null task";
        this.list.add(task);
        this.count++;
        return task.toString();
    }

    /**
     * Marks a task in the TaskList as done.
     * @param index  The index of the task to be marked as done.
     * @return  String representation of the task marked as done.
     */
    public String markTaskAsDone(int index) {
        assert index < 0 : "trying to access a negative index";
        this.list.get(index).markAsDone();
        return this.list.get(index).toString();
    }

    /**
     * Deletes a task from the TaskList.
     * @param index  The index of the task to be deleted.
     * @return  String representation of the task deleted.
     */
    public String deleteTask(int index) {
        assert index < 0 : "trying to access a negative index";
        String representation = this.list.get(index).toString();
        this.list.remove(index);
        this.count--;
        return representation;
    }

    /**
     * Gets the number of tasks in the TaskList.
     * @return  int of the number of tasks.
     */
    public int getCount() {
        return this.count;
    }

    /**
     * String representation of the TaskList.
     * @return  String representation of the TaskList.
     */
    @Override
    public String toString() {
        String representation = "";
        if (this.count > 0) {
            for (int i = 0; i < this.count - 1; i++) {
                representation += (i + 1 + ". " + list.get(i) + "\n");
            }
            representation += this.count + ". " + list.get(this.count - 1);
        } else {
            representation += "Your task list is empty! Add a task with todo deadline or event!";
        }
        return representation;
    }

    /**
     * String representation of the TaskList that is to be saved on a file.
     * @return  String representation of the TaskList that is to be saved on a file.
     */
    public String fileText() {
        String representation = "";
        for (int i = 0; i < this.count; i++) {
            representation += (list.get(i).getFileSaveText() + "\n");
        }
        return representation.trim();
    }

    /**
     * Finds all tasks that include keyword in their description.
     * @param keyword  String of word to be queried.
     * @return  String representation of the Tasks that contain the keyword.
     */
    public String findTasks(String keyword) {
        String representation = "";
        for (int i = 0; i < this.count; i++) {
            Task task = list.get(i);
            if (task.getDescription().indexOf(keyword) != -1) {
                representation += (i + 1 + ". " + list.get(i) + "\n");
            }
        }
        return representation.trim();
    }
}
