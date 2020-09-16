package command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import task.Task;

/**
 * FindCommand receives a keyword from the parser and searches for it in the task list.
 * It inherits from the Command class.
 *
 * @author Joshua
 */
public class FindCommand extends Command {
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
     * Executes the FindCommand and searches within the tasklist for the tasks that correspond.
     * The ui will return the tasks that were found. The storage will not be affected.
     *
     * @param taskList the taskList that is searched from.
     * @param ui the ui that interacts with the user.
     * @param storage the storage that loads and saves data.
     * @return output to be displayed to the user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskList.getTaskList().isEmpty()) {
            throw new DukeException(
                    "La lista de tareas esta actualmente vacia, agregue una tarea antes de encontrar una.");
        }
        for (Task task : taskList.getTaskList()) {
            if (task.toString().contains(wordToFind)) {
                listOfIncludedTasks.add(task);
            }
        }
        if (listOfIncludedTasks.isEmpty()) {
            throw new DukeException("Lo sentimos, ninguna de las tareas coincide con sus criterios de busqueda.");
        } else {
            output = ui.showFoundList(listOfIncludedTasks);
            return output;
        }
    }
}
