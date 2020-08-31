package duke.task;

import duke.DukeException;
import duke.datetime.DateTimeFormat;
import duke.datetime.DateTimeUtility;

import java.time.DateTimeException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<String> tasksStr) throws DukeException {
        this.tasks = new ArrayList<>();
        for (String taskStr : tasksStr) {
            this.tasks.add(TaskList.parseTaskFromString(taskStr));
        }
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

    public String getTaskByIdx(int idx) {
        return tasks.get(idx).toString();
    }

    public ArrayList<Task> filterTasksByDate(String by) throws DukeException {
        if (DateTimeUtility.checkDateTimeType(by) == DateTimeFormat.String) {
            throw new DukeException("U NID 2 GIV CORRECT DATE FOMAT!");
        } else {
            ArrayList<Task> filtered = new ArrayList<>();

            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i) instanceof TimedTask) {
                    try {
                        if (DateTimeUtility.compare(by,
                                ((TimedTask) tasks.get(i)).getByString()) >= 0) {
                            filtered.add(tasks.get(i));
                        }
                    } catch (DateTimeException e) {}
                }
            }

            return filtered;
        }
    }

    public String addTodo(String name) {
        Todo newTask = new Todo(name);
        tasks.add(newTask);
        return newTask.toString();
    }

    public String addDeadline(String name, String by) {
        Deadline newTask = new Deadline(name, by);
        tasks.add(newTask);
        return newTask.toString();
    }

    public String addEvent(String name, String by) {
        Event newTask = new Event(name, by);
        tasks.add(newTask);
        return newTask.toString();
    }

    public void markTaskAsDone(int idx) throws DukeException {
        if (idx < 0 || idx >= tasks.size()) {
            throw new DukeException("U DOAN HAS TASK WIF DIS LABEL");
        } else {
            tasks.get(idx).setDone(true);
        }
    }

    public String popTask(int idx) throws DukeException {
        if (tasks.isEmpty()) {
            throw new DukeException("U CANT DELET ANYTHIN COZ U HAS NO TASKZ NAO LOLOL");
        }
        if (idx < 0 || idx >= tasks.size()) {
            throw new DukeException("U DOAN HAS TASK WIF DIS LABEL");
        } else {
            return tasks.remove(idx).toString();
        }
    }

    public static String tasks2String(ArrayList<Task> tasks) {
        String ret = "";
        for (int i = 0; i < tasks.size(); i++) {
            ret += (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return ret;
    }

    public static Task parseTaskFromString(String str) throws DukeException {
        Task task;
        String[] temp = str.split("]");
        String taskType = String.valueOf(temp[0].charAt(1));
        boolean isDone = (String.valueOf(temp[1].charAt(1)).equals(Task.ICON_TICK));

        if (taskType.equals(Todo.taskIcon)) {
            task = new Todo(temp[2].trim());
        } else if (taskType.equals(Deadline.taskIcon)) {
            String taskName = temp[2].split("\\(by:")[0].trim();
            String by = temp[2].split(" \\(by:")[1].trim();
            by = by.substring(0, by.length()-1);
            task = new Deadline(taskName, by);
        } else if (taskType.equals(Event.taskIcon)) {
            String taskName = temp[2].split(" \\(at:")[0].trim();
            String by = temp[2].split(" \\(at:")[1].trim();
            by = by.substring(0, by.length()-1);
            task = new Event(taskName, by);
        } else {
            throw new DukeException("TASK ICON NOT RECOGNISED");
        }

        task.setDone(isDone);
        return task;
    }

    @Override
    public String toString() {
        return TaskList.tasks2String(this.tasks);

    }
}
