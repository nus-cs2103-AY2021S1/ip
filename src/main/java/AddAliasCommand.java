import java.io.IOException;
import java.util.HashMap;

public class AddAliasCommand extends Command {

    private final HashMap<String, String> aliasMap;
    private final String newKey;

    public AddAliasCommand(HashMap<String, String> aliasMap, String newKey) {
        super();
        this.aliasMap = aliasMap;
        this.newKey = newKey;
    }

    @Override public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        storage.saveMapping(aliasMap);
        return ui.showMapping(aliasMap.get(newKey), newKey);
    }
}
