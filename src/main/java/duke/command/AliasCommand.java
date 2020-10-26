package duke.command;

import duke.DukeException;
import duke.util.AliasMap;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class AliasCommand extends Command {

    private final String alias;
    private final Parser.CommandType type;

    /**
     * Initializes a newly created alias command with a key and type.
     *
     * @param alias alias to map.
     * @param type type to map.
     */
    public AliasCommand(String alias, Parser.CommandType type) {
        this.alias = alias;
        this.type = type;
    }

    /**
     * Executes the alias command.
     *
     * @param tasks TaskList of the program.
     * @param ui user interface of the program.
     * @param storage storage of the program.
     * @param aliasMap alias mapping.
     * @return the execution message.
     * @throws DukeException if the alias already exists.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, AliasMap aliasMap) throws DukeException {
        aliasMap.putAlias(this.alias, this.type);
        String typeAsString = this.type.toString().toLowerCase();
        return ui.printAliasMessage(this.alias, typeAsString);
    }

    /**
     * Checks whether an object equals this alias command.
     *
     * @param obj object to be compared with this command.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof AliasCommand) {
            AliasCommand ac = (AliasCommand) obj;
            return this.alias.equals(ac.alias) && this.type.equals(ac.type);
        } else {
            return false;
        }
    }
}
