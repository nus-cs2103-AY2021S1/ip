package duke.task;

import duke.utils.FileHelper;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;
    private String path;
    private String fileName;

    public TaskList(String path, String fileName) {
        tasks = new ArrayList<>();
        this.path = path;
        this.fileName = fileName;
        loadDataFromFile();
    }

    private void loadDataFromFile() {
        List<String> data = FileHelper.ReadFromFile(path, fileName);
        for (int i = 0; i < data.size(); i++) {
            addTask(data.get(i));
        }
    }

    public void save(){
        FileHelper.save(path, fileName, getStringifiedList());
    }


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

    public int getNumberOfTask() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void remove(int index) {
        tasks.remove(index);
        save();
    }

    public void addTask(Task task) {
        tasks.add(task);
        save();
    }

    public void markAsDone(int index) {
        tasks.get(index).markAsDone();
        save();
    }

    private List<String> getStringifiedList(){
        List <String> data = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            data.add(tasks.get(i).toStoreFormat());
        }
        return data;
    }

    public String printList() {
        String message = "Here are the tasks in your list:\n";
        int size = tasks.size();
        for (int i = 0; i < size; i++) {
            message += (i + 1) + "." + tasks.get(i).toString()+ "\n";
        }
        System.out.println(message);
        return message;
    }

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
