package manager;

/**
 * Represents the set of commands recognised by the converter.
 */
public enum Commands {
    DEADLINE {
        @Override
        public String toString() {
            return "Set a deadline --> deadline <description> /by <yyyy-mm-dd hhmm, yyyy-mm-dd or any text>\n";
        }
    }, DELETE {
        @Override
        public String toString() {
            return "Delete a task --> delete <task no.>\n";
        }
    }, DELETE_ALL {
        @Override
        public String toString() {
            return "Delete all tasks --> delete all\n";
        }
    }, DONE {
        @Override
        public String toString() {
            return "Mark task as done --> done <task no.>\n";
        }
    }, EVENT {
        @Override
        public String toString() {
            return "Create an event --> event <description> /at <yyyy-mm-dd hhmm, yyyy-mm-dd or any text>\n";
        }
    }, FIND {
        @Override
        public String toString() {
            return "Find tasks --> find <keyword>\n";
        }
    }, HELP {
        @Override
        public String toString() {
            return "Get help --> help\n";
        }
    }, LIST {
        @Override
        public String toString() {
            return "Get task list --> list\n";
        }
    }, TAG {
        @Override
        public String toString() {
            return "Tag task --> #<tag name (without spacing)> <task no.>\n";
        }
    }, TODO {
        @Override
        public String toString() {
            return "Add a todo --> todo <description>";
        }
    };
}
