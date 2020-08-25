public class ToDo extends Task {
        public ToDo(String description) {
            super(description);
        }
        public String recordString() {
            return toString();
        }
        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
}
