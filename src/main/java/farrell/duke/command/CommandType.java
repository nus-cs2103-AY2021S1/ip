package main.java.farrell.duke.command;

/**
 * Enumeration of the commands accepted by the program.
 * Unsupported commands will fall under UNDEFINED.
 */
public enum CommandType {
    TODO(true, 1, 1) {
        @Override
        public String getUsageString() {
            return "Usage: todo [description]"
                    + "\ne.g. todo read book";
        }
    },
    EVENT(true, 2, 2) {
        @Override
        public String getUsageString() {
            return "Usage: event [description] /at [date]\n"
                    + "e.g. event go home /at 2020-10-10";
        }
    },
    DEADLINE(true, 2, 2) {
        @Override
        public String getUsageString() {
            return "Usage: deadline [description] /by [date]\n"
                    + "deadline return book /by 2020-10-10";
        }
    },
    DONE(true, 1, 1) {
        @Override
        public String getUsageString() {
            return "Usage: done [task number]\n"
                    + "e.g. done 1";
        }
    },
    LIST(false, 0, 0) {
        @Override
        public String getUsageString() {
            return "Usage: list";
        }
    },
    DELETE(true, 1, 1) {
        @Override
        public String getUsageString() {
            return "Usage: delete [task number]\n"
                    + "e.g. delete 1";
        }
    },
    BYE(false, 0, 0) {
        @Override
        public String getUsageString() {
            return "Usage: bye";
        }
    },
    FIND(true, 1, 1) {
        @Override
        public String getUsageString() {
            return "Usage: find [search string]\n"
                    + "e.g. find book";
        }
    },
    SORT(true, 1, 2) {
        @Override
        public String getUsageString() {
            return "Usage: sort [sort type] /order [ascending]\n"
                    + "e.g. sort description\n"
                    + "e.g. sort description /order ascending";
        }
    },
    UNDEFINED(false, 0, 0);

    private final boolean requiresParams;
    private final int minParams;
    private final int maxParams;

    CommandType(boolean requiresParams, int minParams, int maxParams) {
        this.requiresParams = requiresParams;
        this.minParams = minParams;
        this.maxParams = maxParams;
    }

    public boolean requiresParams() {
        return requiresParams;
    }

    public int getMinParams() {
        return minParams;
    }

    public int getMaxParams() {
        return maxParams;
    }

    /**
     * Maps a string to its corresponding CommandType value.
     *
     * @param string The string to be mapped.
     * @return The CommandType value associated with the string.
     */
    public static CommandType enumFromString(String string) {
        try {
            return CommandType.valueOf(string.toUpperCase());
        } catch (IllegalArgumentException exception) {
            return UNDEFINED;
        }
    }

    public String getUsageString() {
        return "";
    }
}
