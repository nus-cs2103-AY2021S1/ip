public enum TaskType {
    DEADLINE {
        @Override
        public String toString() {
            return "[D]";
        }
    },
    EVENT {
        @Override
        public String toString() {
            return "[E]";
        }
    },
    TODO {
        @Override
        public String toString() {
            return "[T]";
        }
    }
}
