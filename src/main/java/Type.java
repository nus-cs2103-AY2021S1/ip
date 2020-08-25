public enum Type {
    TODO {
        public String toString() {
            return "ToDo";
        }
    }, DEADLINE {
        public String toString() {
            return "Deadline";
        }
    }, EVENT {
        public String toString() {
            return "Event";
        }
    }
}
