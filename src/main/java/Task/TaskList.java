package main.java.Task;

import main.java.DukeArrayException;
import main.java.DukeException;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> list;

    public TaskList(ArrayList<Task> tasklist){
        this.list = tasklist;
    }

    public void deleteTask(String userinput){
        try {
            if (userinput.length() <= 6) {
                throw new DukeException();
            }
            int taskNumber = Integer.parseInt(userinput.substring(7)) - 1;
            if (taskNumber > list.size()) {
                throw new DukeArrayException();
            }
            Task taskDeleted = list.get(taskNumber);
            list.remove(taskNumber);
            System.out.println("I have removed the task:\n" + taskDeleted.stringify() + "\n" + "Now you have " +
                    list.size() + " tasks in the list.");
        } catch (DukeArrayException e) {
            System.out.println("Number cannot be longer than the list.");
        } catch (DukeException e) {
            System.out.println("Must include number after 'delete'");
        } catch (NumberFormatException e) {
            System.out.println("Must include number after 'delete'");
        }
    }

    public void addTask(Task task){
        list.add(task);
        System.out.println(
                "I have added this task:\n"
                        + task.stringify() + "\n"
                        + "Now you have " + list.size() + " task(s) in the list.");
    }

    public void completeTask(int taskNumber){
        Task taskCompleted = this.list.get(taskNumber);
        taskCompleted.complete = true;
        System.out.println("Nice! I've marked this task as done:\n" + "[✓] " + taskCompleted.task);
    }

    public void showList(){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i+1)+"."+list.get(i).stringify());
        }
    }
}
