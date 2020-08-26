package duke.task;

import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<String> taskStrings) {
        tasks = new ArrayList<>();
        for (String taskString : taskStrings) {
            this.tasks.add(lineToTask(taskString));
        }
    }

    public Task lineToTask(String taskString) {
        String[] taskLine = taskString.split("~");
        Task task = null;
        switch (taskLine[0]) {
            case "T":
                task = new ToDo(taskLine[2]);
                break;
            case "D":
                task = new Deadline(taskLine[2], taskLine[3]);
                break;
            case "E":
                task = new Event(taskLine[2], taskLine[3]);
                break;
        }
        if (taskLine[1].equals("1")) {
            assert task != null;
            task.markAsDone();
        }
        return task;
    }

    public String listTasks() {
        String tasks = "";
        Task t;
        for (int i = 0; i < this.tasks.size(); i++) {
            t = this.tasks.get(i);
            tasks += String.format("\t %d.%s%n", i + 1, t);
        }
        return tasks;
    }

    public void addTasks(Task task) {
        tasks.add(task);
    }

    public int numTasks() {
        return tasks.size();
    }

    public ArrayList<String> tasksToText() {
        ArrayList<String> strings = new ArrayList<>();
        for (Task task : tasks) {
            strings.add(task.toData());
        }
        return strings;
    }

    public String findTasks(String keyword) {
        String tasks = "";
        Task t;
        int numMatch = 0;
        for (int i = 0; i < this.tasks.size(); i++) {
            t = this.tasks.get(i);
            if (t.hasKeyword(keyword)) {
                numMatch++;
                tasks += String.format("\t %d.%s%n", numMatch, t);
            }
        }
        return tasks;
    }
}
