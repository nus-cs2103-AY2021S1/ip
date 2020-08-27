public class EndCommand extends Command{

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        System.out.println("Well I'll see you around, pardner!!");
        ui.deactivate();
    }
}
