package duke.dukehelper;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.exception.DukeInvalidArgumentException;
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

    /**
     * Marks task as done
     * @param markNumber
     * @return status string
     * @throws DukeInvalidArgumentException
     */
    public String doneTask(int markNumber) throws DukeInvalidArgumentException {
        try {
            Task marked = taskList.get(markNumber - 1);
            marked.markAsDone();
            return "Nice! I've marked this task as done:\n      " + marked.returnStringForm();
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            throw new DukeInvalidArgumentException("Invalid number");
        }
    }

    /**
     * Deletes task
     * @param markNumber
     * @param numTasks
     * @return status string
     * @throws DukeInvalidArgumentException
     */
    public String deleteTask(int markNumber, int numTasks) throws DukeInvalidArgumentException {
        try {
            Task marked = taskList.get(markNumber - 1);
            this.taskList.remove(markNumber - 1);
            return "Noted. I've removed this task:\n      " + marked.returnStringForm() + "\n    Now you have " + numTasks + " task(s) in the list.";
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            throw new DukeInvalidArgumentException("Invalid number");
        }
    }

    /**
     * Filters tasks based on deadline
     * @param deadline
     * @return message to user
     */
    public String filteredTaskList(LocalDate deadline) {
        String res = "Here are the matching tasks in your list:\n    ";
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

    /**
     * Filters tasks based on keywords
     * @param tokens
     * @return matched tasks
     */
    public String findTasks(String[] tokens) {
        String keyword = "";
        int numMatch = 0;
        String res = "Here are the matching tasks in your list:\n    ";
        for(int i = 1;i < tokens.length;i++) {
            keyword += tokens[i];
        }
        for(int i = 0;i < taskList.size();i++) {
            Task task = taskList.get(i);
            if(task.getContent().contains(keyword)) {
                numMatch++;
                res += ((i + 1) + "." + task.returnStringForm());
                if (i < taskList.size() - 1) res += "\n    ";
            }
        }
        if(numMatch == 0) {
            return "Nothing matches. Watchout for typos\n";
        }
        return res;
    }

}
