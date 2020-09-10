public enum Priority {
    HIGH {
        @Override
        public String toString() {
            return "high";
        }
    },
    MEDIUM {
        @Override
        public String toString() {
            return "medium";
        }
    },
    LOW {
        @Override
        public String toString() {
            return "low";
        }
    };

}
