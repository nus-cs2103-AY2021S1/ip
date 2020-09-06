package duke.Command;

import duke.AppUi;
import duke.Finder;
import duke.TaskList;

public class FindCommand extends Command {

    private String[] command;
    private int ptr;
    private TaskList list;
    private AppUi appUi;

    public FindCommand(String input, String[] command, int ptr, TaskList list) {
        super(input);
        this.command = command;
        this.ptr = ptr;
        this.list = list;
        this.appUi = new AppUi();
    }

    public String execute(){
        if (ptr + 1 >= command.length) {
            return appUi.getWrongFindFormatMsg();
        }
        return appUi.getFindResult(Finder.appFind(list, command[ptr + 1]));
    }
}
