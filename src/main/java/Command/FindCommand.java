package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import task.Task;

import java.util.ArrayList;

/**
 * FindCommand receives a keyword from the parser and searches for it in the task list.
 * It inherits from the Command class.
 *
 * @author Joshua
 */
public class FindCommand extends Command{
    /**
     * wordToFind is the keyword retrieved from the parser.
     * listOfIncludedTasks contains the list of tasks the have the keyword in their description.
     */
    private String wordToFind;
    private ArrayList<Task> listOfIncludedTasks;

    /**
     * Creates the FindCommand and initializes the keyword to be searched with.
     *
     * @param wordToFind the keyword to search for.
     */
   public FindCommand(String wordToFind) {
        this.wordToFind = wordToFind;
        listOfIncludedTasks = new ArrayList<>();
   }

    /**
     * Returns a true or false to terminate Duke.
     *
     * @return boolean that does not terminate Duke.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the FindCommand and searches within the tasklist for the tasks that correspond.
     * The ui will return the tasks that were found. The storage will not be affected.
     *
     * @param taskList the taskList that is searched from.
     * @param ui the ui that interacts with the user.
     * @param storage the storage that loads and saves data.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
       if (taskList.getTaskList().isEmpty()) {
           ui.showError("La lista de tareas está actualmente vacía, agregue una tarea antes de encontrar una.");
       }
       for (Task task : taskList.getTaskList()) {
           if (task.toString().contains(wordToFind)) {
               listOfIncludedTasks.add(task);
           }
       }
       if (listOfIncludedTasks.isEmpty()) {
           ui.showError("Lo sentimos, ninguna de las tareas coincide con sus criterios de búsqueda.");
       } else {
           ui.showFoundList(listOfIncludedTasks);
       }
    }
}
