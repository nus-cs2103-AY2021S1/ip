package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.utils.FileHelper;


public class TaskList {
    private List<Task> tasks;
    private String path;
    private String fileName;

    /**
     * Constructor for TaskList
     */
    public TaskList(String path, String fileName) {
        tasks = new ArrayList<>();
        this.path = path;
        this.fileName = fileName;
        loadDataFromFile();
    }

    /**
     * Constructor for TaskList
     */
    private void loadDataFromFile() {
        List<String> data = FileHelper.readFromFile(path, fileName);
        for (int i = 0; i < data.size(); i++) {
            addTask(data.get(i));
        }
    }

    /**
     * Save task to file
     */
    public void save() {
        FileHelper.save(path, fileName, getStringifiedList());
    }

    /**
     * add a task to task list
     */
    public void addTask(String data) {
        Task task;
        String[] parts = data.split("( \\| )");

        switch (parts[0]) {
        case("T"):
            task = new ToDo(Integer.parseInt(parts[1]) == 1, parts[2]);
            break;
        case("D"):
            task = new Deadline(Integer.parseInt(parts[1]) == 1, parts[2], parts[3]);
            break;
        case("E"):
            task = new Event(Integer.parseInt(parts[1]) == 1, parts[2], parts[3]);
            break;
        default:
            task = new Task("default task");
        }
        tasks.add(task);
        save();
    }

    /**
     * add a task to tasks
     */
    public void addTask(Task task) {
        tasks.add(task);
        save();
    }

    public int getNumberOfTask() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * remove a task from task list
     */
    public void remove(int index) {
        tasks.remove(index);
        save();
    }

    /**
     * mark a task as done by index
     */
    public void markAsDone(int index) {
        tasks.get(index).markAsDone();
        save();
    }

    private List<String> getStringifiedList() {
        List <String> data = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            data.add(tasks.get(i).toStoreFormat());
        }
        return data;
    }

    /**
     * prints task list info
     */
    public String printList() {
        String message = "Here are the tasks in your list:\n";
        int size = tasks.size();
        for (int i = 0; i < size; i++) {
            message += (i + 1) + "." + tasks.get(i).toString() + "\n";
        }
        System.out.println(message);
        return message;
    }

    /**
     * find tasks by keyword
     */
    public List<Task> find(String searchText) {
        List<Task> results = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(searchText)) {
                results.add(tasks.get(i));
            }
        }
        return results;
    }

}
