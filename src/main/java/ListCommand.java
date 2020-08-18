import java.util.ArrayList;
import java.util.List;

public class ListCommand implements Command {
    List<Task> list;

    public ListCommand(List<Task> list) {
        this.list = list;
    }

    @Override
    public void execute() {
        new Duke().print(itemize());
    }

    private List<String> itemize() {
        List<String> itemizedList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            itemizedList.add((i + 1) + ". " + list.get(i));
        }
        return itemizedList;
    }
}
