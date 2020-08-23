public enum TaskType {
    DEADLINE {
        /**
         * Overrides toString method so as to customize output string format.
         * @return String in our desired format.
         */
        @Override
        public String toString() {
            return "[D]";
        }
    },
    EVENT {
        /**
         * Overrides toString method so as to customize output string format.
         * @return String in our desired format.
         */
        @Override
        public String toString() {
            return "[E]";
        }
    },
    TODO {
        /**
         * Overrides toString method so as to customize output string format.
         * @return String in our desired format.
         */
        @Override
        public String toString() {
            return "[T]";
        }
    }
}
