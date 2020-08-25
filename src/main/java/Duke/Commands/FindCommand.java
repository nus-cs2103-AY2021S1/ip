package Duke.Commands;

import Duke.Errors.DukeException;
import Duke.Errors.FindException;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;
import Duke.Tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * used to handle case where find is keyword
 */
public class FindCommand extends Command {
    private List<Task> tasks = new ArrayList<>();

    /**
     * assigns string to a value of string
     * @param string assigns string to this this.string
     */
    public FindCommand(String string) {
        super(string);
    }
    private void setTasks(String[] strings, TaskList tasks){
        List<Task> allTasks = tasks.getAllTasks();
        for(int i = 0; i < tasks.getAllTasks().size(); i++){
            Task task = allTasks.get(i);
            String string = task.getName();
            String[] comp = string.split(" ", -2);
            boolean contains = false;
            for(int j = 0; j < strings.length; j++){
                String s = strings[j];
                for(int k = 0; k < comp.length; k++){
                    if(comp[k].equals(s)){
                        contains = true;
                        break;
                    }
                }
            }
            if(contains) {
                this.tasks.add(task);
            }
        }
    }

    /**
     * used to find the tasks which contains keyword in string
     * @param tasks to look for the task's string value
     * @param ui
     * @param storage no need
     * @throws DukeException used to throw error when no words mentioned after find or the keyword is not present in
     * tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if(string.length() == 4 || string.length() == 5){
            throw new FindException(false, "");
        }else{
            String find = string.substring(5);
            String[] strings = find.split(" ", -2);
            setTasks(strings, tasks);
            if(this.tasks.size() == 0){
                throw new FindException(true, find);
            }else{
                System.out.println("  Here are the matching tasks in your list:");
                for(int i = 0; i < this.tasks.size(); i++){
                    Task task = this.tasks.get(i);
                    System.out.println("  " + (i + 1) + "." + task.toString());
                }
            }
        }
    }
}
