package duke.command;

/**
 * Represents the different types of commands available for users.
 */
public enum CommandType {
    ADD_DEADLINE {
        /**
         * Returns the command instruction.
         *
         * @return String of command instruction.
         */
        @Override
        public String toString() {
            return "deadline: adds a task with deadline.\n"
                    + "    eg. deadline <description> /by <dd/MM/yyyy> <HHmm>\n";
        }
    },

    ADD_EVENT {
        /**
         * Returns the command instruction.
         *
         * @return String of command instruction.
         */
        @Override
        public String toString() {
            return "event: adds a task with a scheduled time. \n"
                    + "    eg. event <description> /at <dd/MM/yyyy> <HHmm>\n";
        }
    },

    ADD_TODO {
        /**
         * Returns the command instruction.
         *
         * @return String of command instruction.
         */
        @Override
        public String toString() {
            return "todo: adds a todo task to the list.\n"
                    + "    eg. todo <description>'\n";
        }
    },

    BYE {
        /**
         * Returns the command instruction.
         *
         * @return String of command instruction.
         */
        @Override
        public String toString() {
            return "bye: exits the application.\n";
        }
    },

    DELETE {
        /**
         * Returns the command instruction.
         *
         * @return String of command instruction.
         */
        @Override
        public String toString() {
            return "delete: removes a task from your list.\n"
                    + "    eg. delete <index>\n";
        }
    },

    DONE {
        /**
         * Returns the command instruction.
         *
         * @return String of command instruction.
         */
        @Override
        public String toString() {
            return "done: marks a task as done.\n"
                    + "    eg. done <index>\n";
        }
    },

    FIND {
        /**
         * Returns the command instruction.
         *
         * @return String of command instruction.
         */
        @Override
        public String toString() {
            return "find: finds tasks with the keyword.\n"
                    + "    eg. find <keyword>\n";
        }
    },

    LIST {
        /**
         * Returns the command instruction.
         *
         * @return String of command instruction.
         */
        @Override
        public String toString() {
            return "list: lists out all the tasks you have.\n";
        }
    },

    LIST_DATE {
        /**
         * Returns the command instruction.
         *
         * @return String of command instruction.
         */
        @Override
        public String toString() {
            return "list date: lists out all the tasks you have on that date.\n"
                    + "    eg. list <dd/MM/yyyy>\n";
        }
    }
}
