package command;

import java.util.Objects;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;


public class FindCommand extends Command {
    private String findString;

    /**
     * Constructor for the find command
     * @param findString string to find
     */
    public FindCommand(String findString) {
        super(CommandType.Find);
        this.findString = findString;
    }
  @Override
  public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
    TaskList filteredTaskList = taskList.find(this.findString);
    return ui.displayMatchingTasks(filteredTaskList);
  }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FindCommand that = (FindCommand) o;
        return Objects.equals(findString, that.findString);
    }
}
