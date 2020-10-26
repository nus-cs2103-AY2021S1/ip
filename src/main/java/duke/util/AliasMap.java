package duke.util;

import java.util.HashMap;

import duke.DukeException;

public class AliasMap {

    private HashMap<String, Parser.CommandType> mapping;

    /**
     * Initializes a newly created alias map.
     */
    public AliasMap() {
        this.mapping = new HashMap<>();
    }

    /**
     * Populates the alias map with default mappings.
     */
    public void populate() {
        this.mapping.put("t", Parser.CommandType.TODO);
        this.mapping.put("d", Parser.CommandType.DEADLINE);
        this.mapping.put("e", Parser.CommandType.EVENT);
        this.mapping.put("ls", Parser.CommandType.LIST);
        this.mapping.put("f", Parser.CommandType.FIND);
        this.mapping.put("dn", Parser.CommandType.DONE);
        this.mapping.put("rm", Parser.CommandType.DELETE);
        this.mapping.put("q", Parser.CommandType.BYE);
    }

    /**
     * Gets a command type with the given alias.
     *
     * @param alias alias associated with command type.
     */
    public Parser.CommandType getCommand(String alias) {

        Parser.CommandType type = this.mapping.get(alias);

        if (type == null) {
            type = Parser.CommandType.UNKNOWN;
        }

        assert type != null;
        return type;
    }

    /**
     * Puts a new alias into the alias map.
     *
     * @param alias new alias.
     * @param type command type.
     *
     * @throws DukeException if a mapping already exists.
     */
    public void putAlias(String alias, Parser.CommandType type) throws DukeException {

        if (this.mapping.containsKey(alias)) {
            throw new DukeException("Oh dear! A mapping already exists for that alias!");
        }

        this.mapping.put(alias, type);
    }
}
