public class ExitCommand extends Command {
    @Override
    public void execute(TaskList list){
        Ui.goodbyeMessage();
    }

    public boolean isExit(){
        return true;
    }
}
