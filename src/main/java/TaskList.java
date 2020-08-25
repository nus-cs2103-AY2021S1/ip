import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TaskList {
    //abstract the arraylist access and parsing of file
    ArrayList<Task> taskList;

    TaskList(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        taskList = new ArrayList<>();
        while (line != null) {
            String[] split = line.split("%d%");
            if (split.length > 3) {
                taskList.add(Task.createTask(split[0], split[1], split[2], split[3]));
            } else {
                taskList.add(Task.createTask(split[0], split[1], split[2], ""));
            }
            line = br.readLine();
        }
    }

    public void done(int doneTask) {
        taskList.get(doneTask).setDone();
    }

    public void delete(int deleteTask) {
        taskList.remove(deleteTask);
    }

    public Task addDeadline(String task, String deadline) {
        Deadline dl = new Deadline(task, deadline);
        taskList.add(dl);
        return dl;
    }

    public Task addTodo(String task) {
        Todo td = new Todo(task);
        taskList.add(td);
        return td;
    }

    public Task addEvent(String task, String at) {
        Event e = new Event(task, at);
        taskList.add(e);
        return e;
    }

    public String get(int doneTask) {
        return taskList.get(doneTask).toString();
    }

    public String taskCount() {
        return "Now you have " + taskList.size() + " tasks in the list.";
    }

    @Override
    public String toString() {
        String rtn = taskList.stream().map(task -> task.toString()).collect(Collectors.joining("\n"));
        String[] rtnSplit = rtn.split("\n");
        for (int i = 0; i <  rtnSplit.length; i++) {
            rtnSplit[i] = (i + 1) + ". "+ rtnSplit[i];
        }
        return Arrays.asList(rtnSplit).stream().collect(Collectors.joining("\n"));
    }
}
