package duke.command;

/**
 * Represents the different types of commands the user input can have.
 */
public enum CommandType {

    /**
     * Represents a help command.
     */
    HELP {
        @Override
        public String toString() {
            return "- Help: Provides a list of possible commands\n"
                    + "        \"help\"";
        }
    },
    /**
     * Represents a list command.
     */
    LIST {
        @Override
        public String toString() {
            return "- List: Shows the list of tasks\n"
                    + "        \"list\"";
        }
    },
    /**
     * Represents a done command.
     */
    DONE {
        @Override
        public String toString() {
            return "- Done: Marks a task as complete\n"
                    + "        \"done <task number>\"";
        }
    },
    /**
     * Represents a delete command.
     */
    DELETE {
        @Override
        public String toString() {
            return "- Delete: Deletes a task from the list\n"
                    + "        \"delete <task number>\"";
        }
    },
    /**
     * Represents a clear command.
     */
    CLEAR {
        @Override
        public String toString() {
            return "- Clear: Deletes ALL tasks from the list\n"
                    + "        \"clear\"";
        }
    },
    /**
     * Represents a todo command.
     */
    TODO {
        @Override
        public String toString() {
            return "- Todo: Creates a todo task with specific description\n"
                    + "        \"todo <description>\"";
        }
    },
    /**
     * Represents an event command.
     */
    EVENT {
        @Override
        public String toString() {
            return "- Event: Creates an event task with specific description and date/time\n"
                    + "        \"event <description> /at <time>\"\n"
                    + "        Time formatting: dd-MM-yyyy HH:mm";
        }
    },
    /**
     * Represents a deadline command.
     */
    DEADLINE {
        @Override
        public String toString() {
            return "- Deadline: Creates a deadline task with specific description and date/time\n"
                    + "        \"deadline <description> /by <time>\"\n"
                    + "        Time formatting: dd-MM-yyyy HH:mm";
        }
    },
    /**
     * Represents a find command.
     */
    FIND {
        @Override
        public String toString() {
            return "- Find: Searches for task(s) which match the given keyword(s)\n"
                    + "        \"find <keyword(s)>\"";
        }
    },
    SCHEDULE {
        @Override
        public String toString() {
            return "- Schedule: Shows the tasks due/at a specific date\n"
                    + "        \"schedule <date>\"\n"
                    + "        Date formatting: dd-MM-yyyy";
        }
    },
    /**
     * Represents a bye command.
     */
    BYE {
        @Override
        public String toString() {
            return "- Bye: Ends the conversation with Awesome-O\n"
                    + "        \"bye\"";
        }
    }

}
