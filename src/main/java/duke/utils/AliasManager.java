package duke.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import com.google.gson.reflect.TypeToken;

import duke.enums.Command;

/**
 * Keeps track of aliases.
 */
public class AliasManager {
    /** Map of aliases. */
    private final Map<String, Command> aliases = new PersistentMap<>("./data/aliases.txt",
            new TypeToken<HashMap<String, Command>>(){}.getType());

    /** Constructs a {@code AliasManager} object. */
    public AliasManager() {}

    /**
     * Associates an alias with a command.
     *
     * @param alias the alias for the command.
     * @param command the command to be aliased.
     * @return a string representation of the action of adding an alias.
     */
    public String addAlias(String alias, Command command) {
        Command previousCommand = aliases.putIfAbsent(alias, command);
        String response;
        if (previousCommand == null) {
            response = ResourceHandler.getString("aliasManager.addAlias");
        } else {
            response = ResourceHandler.getString("aliasManager.aliasInUse");
        }
        return response;
    }

    /**
     * Returns a list of aliases under the {@code AliasManager}.
     *
     * @return a list of aliases under the {@code AliasManager}.
     */
    @Override
    public String toString() {
        StringBuilder formattedList =
                new StringBuilder(ResourceHandler.getString("aliasManager.listAliasesPrefix") + "\n");
        List<String> keys = new ArrayList<>(aliases.keySet());
        IntStream.range(0, aliases.size())
                .mapToObj(i -> String.format("%d. %s -> %s\n", i + 1,
                        keys.get(i).toLowerCase(), aliases.get(keys.get(i)).toString().toLowerCase()))
                .forEach(formattedList::append);
        return formattedList.toString();
    }
}
