import Exceptions.*;
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
        ui.currNum++;
        if (ui.currNum >= tasks.allTasks.size()) {
            exit = true;
        }else {
            ui.curr = tasks.allTasks.get(ui.currNum);
        }

    }
}
