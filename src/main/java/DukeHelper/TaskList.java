package DukeHelper;

import Task.Task;
import Task.Deadline;
import Task.Event;
import Exception.DukeInvalidArgumentException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
    public void addTaskStr(String task) {
        Task newTask = new Task(task);
        this.taskList.add(newTask);
    }
    public void addTask(Task task) {
        this.taskList.add(task);
    }
    public String doneTask(int markNumber) throws DukeInvalidArgumentException {
        try {
            Task marked = taskList.get(markNumber - 1);
            marked.markAsDone();
            return "Nice! I've marked this task as done:\n      " + marked.returnStringForm();
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            throw new DukeInvalidArgumentException("Invalid number");
        }
    }
    public String deleteTask(int markNumber, int numTasks) throws DukeInvalidArgumentException {
        try {
            Task marked = taskList.get(markNumber - 1);
            this.taskList.remove(markNumber - 1);
            return "Noted. I've removed this task:\n      " + marked.returnStringForm() + "\n    Now you have " + numTasks + " task(s) in the list.";
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            throw new DukeInvalidArgumentException("Invalid number");
        }
    }
    public String filteredTaskList(LocalDate deadline) {
        String res = "Here are the tasks in your list:\n    ";
        for(int i = 0;i < taskList.size();i++) {
            Task task = taskList.get(i);
            if(task instanceof Deadline) {
                if(((Deadline)task).getDeadline().compareTo(deadline) == 0) {
                    res += ((i + 1) + "." + task.returnStringForm());
                    if (i < taskList.size() - 1) res += "\n    ";
                }
            } else if(task instanceof Event) {
                if(((Event)task).getTime().compareTo(deadline) == 0) {
                    res += ((i + 1) + "." + task.returnStringForm());
                    if (i < taskList.size() - 1) res += "\n    ";
                }
            }
        }
        return res;
    }

}
