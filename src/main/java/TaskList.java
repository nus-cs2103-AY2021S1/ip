import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDate;
public class TaskList {
    public ArrayList<Task> tasks;

    /**
     * TaskList constructor
     * @param tasks list of tasks
     */
    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    /**
     * get number of tasks in current list
     * @return size of list of tasks
     */
    public int getSize(){
        return tasks.size();
    }

    /**
     * add a task in list
     * @param task the task to be added
     * @param ui the ui to show reply
     */
    public void addTask(Task task, Ui ui){
        tasks.add(task);
        ui.addResponse("Got it. I've added this task:" + "\n" + " " + task.toString() + "\n"
                + "Now you have " + getSize() + " tasks in the list");
    }

    /**
     * delete a task in list
     * @param taskIndex index of task to be deleted
     * @param ui the ui to show reply
     */
    public void deleteTask(int taskIndex, Ui ui){
        Task deleted = tasks.get(taskIndex - 1);
        tasks.remove(taskIndex - 1);
        ui.addResponse("Noted. I've removed this task: " + "\n" +
                deleted.toString() + "\n" + "Now you have " + getSize() + " tasks in the list.");
    }

    /**
     * done a task in list
     * @param taskIndex index of task to be done
     * @param ui the ui to show reply
     */
    public void doneTask(int taskIndex, Ui ui){
        tasks.get(taskIndex - 1).markAsDone();
        ui.addResponse("Congrats! You are done with task " + taskIndex);
    }

    /**
     * show all tasks in the list in string format
     */
    public void showTaskList(Ui ui){
        String listStrings = "";
        for (int i = 0; i < getSize(); i++) {
            Integer listNum = i + 1;
            listStrings += listNum.toString() + "." + tasks.get(i).toString() + "\n";
        }
        ui.addResponse("Here are the tasks in your list:" + "\n" + listStrings);
    }

    /**
     * find tasks matching a particular keyword
     * @param keyword the keyword to be found
     * @param ui the ui to show reply
     */
    public void findKeyword(String keyword, Ui ui){
        ArrayList<Task> keyTasks = new ArrayList<>();
        for(int i = 0; i < getSize(); i++){
            if(this.tasks.get(i).description.contains(keyword)){
                keyTasks.add(this.tasks.get(i));
            }
        }

        if(keyTasks.size() == 0){
            ui.addResponse("Sorry there is no matching task :-(");
        }

        ui.addResponse("Here are the matching tasks in your list:\n");
        for(int i = 0; i < keyTasks.size(); i++){
            Integer listNum = i + 1;
            ui.addResponse(listNum.toString() + "." + keyTasks.get(i).toString());
        }
    }

    public void snooze(int taskIndex, LocalDate newDate, Ui ui){
        if(tasks.get(taskIndex - 1) instanceof Deadline){
            Deadline editedTask = new Deadline(tasks.get(taskIndex - 1).description, newDate);
            tasks.set(taskIndex - 1, editedTask);
            ui.addResponse("Okay! You have snoozed task " + taskIndex + " to " +
                    newDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
        }else if(tasks.get(taskIndex - 1) instanceof Event){
            Event editedTask = new Event(tasks.get(taskIndex - 1).description, newDate);
            tasks.set(taskIndex - 1, editedTask);
            ui.addResponse("Okay! You have snoozed task " + taskIndex + " to " +
                    newDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
        }else{
            ui.addResponse("This task do not need to be snoozed");
        }
    }
}
