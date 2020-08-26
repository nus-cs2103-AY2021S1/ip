import java.util.List;

public class ListCommand extends Command{

    ListCommand(String str) {
        super(str);
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.showList(list);
    }
}
