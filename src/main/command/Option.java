package main.command;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import main.exception.InvalidOptionException;

/**
 * Represents the possible options a command can have.
 * @author Lin Geyu
 * @version v0.3
 * @since v0.3
 */
public enum Option {
    RECURRING_DAILY(new String[] { "rd" }, new String[] { "recurring-daily" }),
    RECURRING_WEEKLY(new String[] { "rw" }, new String[] { "recurring-weekly" }),
    RECURRING_MONTHLY(new String[] { "rm" }, new String[] { "recurring-monthly" }),
    RECURRING_YEARLY(new String[] { "ry" }, new String[] { "recurring-yearly" });

    private static final Map<String, Option> shortAliasMap = new HashMap<>();
    private static final Map<String, Option> fullAliasMap = new HashMap<>();

    private final String[] shortAliases;
    private final String[] fullAliases;

    static {
        Arrays.stream(Option.values())
                .forEach(option -> {
                    Arrays.stream(option.shortAliases)
                            .forEach(shortAlias -> shortAliasMap.put(shortAlias, option));

                    Arrays.stream(option.fullAliases)
                            .forEach(fullAlias -> fullAliasMap.put(fullAlias, option));
                });
    }

    /**
     * Creates an Option that corresponds to a list of aliases.
     * @param shortAliases the shorthand aliases that correspond to this option.
     * @param fullAliases the full aliases that correspond to this option.
     */
    Option(String[] shortAliases, String[] fullAliases) {
        this.shortAliases = shortAliases;
        this.fullAliases = fullAliases;
    }

    /**
     * Returns an option that corresponds to the short alias given.
     * @param shortAlias Alias given by user.
     * @return Option that corresponds to the short alias.
     * @throws InvalidOptionException When alias does not correspond to any option.
     */
    public static Option getOptionFromShortAlias(String shortAlias)
            throws InvalidOptionException {
        Option option = shortAliasMap.get(shortAlias);
        boolean isInvalidAlias = option == null;

        if (isInvalidAlias) {
            throw new InvalidOptionException(shortAlias);
        }

        return option;
    }

    /**
     * Returns an option the corresponds to the full alias given.
     * @param fullAlias Alias given by user.
     * @return Option that corresponds to the full alias.
     * @throws InvalidOptionException When alias does not correspond to any option.
     */
    public static Option getOptionFromFullAlias(String fullAlias)
            throws InvalidOptionException {
        Option option = fullAliasMap.get(fullAlias);
        boolean isInvalidAlias = option == null;

        if (isInvalidAlias) {
            throw new InvalidOptionException(fullAlias);
        }

        return option;
    }

    /**
     * Gets the first short alias corresponding to the option.
     * @param option the option we want to get an alias from.
     * @return the alias that corresponds to the option.
     */
    public static String getAlias(Option option) {
        return option.shortAliases[0];
    }
}
