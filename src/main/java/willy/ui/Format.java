package willy.ui;

public enum Format {
    TODO {
        @Override
        public String toString() {
            return "\nFormat: todo [TASK] "
                    + "\n\te.g. 'todo do Homework'";
        }
    },
    DEADLINE {
        @Override
        public String toString() {
            return "\nFormat: deadline [TASK] /by [DATE] [TIME] "
                    + "\n\te.g. 'deadline project /by 20/20/2020 18:00'";
        }
    },
    EVENT {
        @Override
        public String toString() {
            return "\nFormat: event [TASK] /at [DATE] [TIME] "
                    + "\n\te.g. 'event project meeting /at 20/20/2020 18:00'";
        }
    },
    EDIT {
        @Override
        public String toString() {
            return "\nFormat: edit [TASK NUMBER] > [TASK DETAILS] "
                    + "\n\te.g. 'edit 1 > todo sleep' ";
        }
    },
    DONE {
        @Override
        public String toString() {
            return "\nFormat: done [TASK NUMBER]"
                    + "\n\te.g. 'done 1' ";
        }
    },
    DELETE {
        @Override
        public String toString() {
            return "\nFormat: delete [TASK NUMBER]"
                    + "\n\te.g. 'delete 3' ";
        }
    },
    FIND {
        @Override
        public String toString() {
            return "\nFormat: find [KEYWORD(s)]"
                    + "\n\te.g. 'find read book'";
        }
    },
    LIST {
        @Override
        public String toString() {
            return "\nFormat: list";
        }
    };
}
