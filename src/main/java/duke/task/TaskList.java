package duke.task;

import duke.common.CustomException;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        TaskList.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int taskindex) {
        tasks.remove(taskindex);
    }

    public void markAsDone(int taskindex) {
        tasks.get(taskindex).markAsDone();
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    public Task getTask(int taskindex) {
        return tasks.get(taskindex);
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> findTask(String keyword) throws CustomException {
        ArrayList<Task> result = new ArrayList<>();
        if (!keyword.isEmpty()) {
            for (Task task : tasks) {
                if (task.haveKeyword(keyword)) {
                    result.add(task);
                }
            }
        } else {
            throw new CustomException("keyword is left blank.");
        }
        return result;
    }
}
