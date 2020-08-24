package Duke.Commands;
import Duke.Errors.DukeException;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;

abstract public class Command {
    String string;
    boolean exit = false;
    Command(String string){
        this.string = string;
    }
    public boolean isExit(){
        return exit;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.increment();
        if (ui.getCurrNum()>= tasks.getAllTasks().size()) {
            exit = true;
        }else {
            ui.setCurr(tasks.getAllTasks().get(ui.getCurrNum()));
        }

    }
}
