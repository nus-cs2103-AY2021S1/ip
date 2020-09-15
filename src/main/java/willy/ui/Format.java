package willy.ui;

public enum Format {
    TODO {
        @Override
        public String toString() {
            return "\nFormat: todo [task] "
                    + "\n\te.g. 'todo do Homework'";
        }
    },
    DEADLINE {
        @Override
        public String toString() {
            return "\nFormat: deadline [task] /by <date> <time> "
                    + "\n\te.g. 'deadline project /by 20/20/2020 18:00'";
        }
    },
    EVENT {
        @Override
        public String toString() {
            return "\nFormat: event [task] /at <date> <time> "
                    + "\n\te.g. 'event project meeting /at 20/20/2020 18:00'";
        }
    },
    EDIT {
        @Override
        public String toString() {
            return "\nFormat: edit [task number] > [task details] "
                    + "\n\te.g. 'edit 1 > todo sleep ";
        }
    }
}
