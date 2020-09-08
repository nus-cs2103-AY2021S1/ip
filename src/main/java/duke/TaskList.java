package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {

    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public int size() {
        return this.tasks.size();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Delete the task that is at the index position from the task list
     * And change the sequence number of the task list
     *
     * @param index which is the position of the deleted task
     */
    public void delete(int index) {
        this.tasks.remove(index);
        for (int i = index; i < tasks.size(); i++) {
            tasks.get(i).sequence = tasks.get(i).sequence - 1;
        }
    }

    /**
     * Maps the tasks into storage format and store it in a list.
     *
     * @return fileString which is an arraylist of strings to be recorded in duke.txt
     */
    public List<String> convertToFileFormat() {
        List<String> fileString = this.tasks
                .stream()
                .map(x -> x.writeToFile())
                .collect(Collectors.toList());
        return fileString;
    }
    /**
     * Uses stream.filter to find the matching tasks.
     *
     * @return List of Tasks which contains all matching tasks.
     */
    public List<Task> findMatchingTask(String keyWord) {
        List<Task> matchingTask = this.tasks
                .stream()
                .filter(x -> x.description.contains(keyWord))
                .collect(Collectors.toList());
        return matchingTask;
    }

}
