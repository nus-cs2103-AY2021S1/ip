public enum Status {
    GREET {
        @Override
        public String toString() {
            return "   Wonderful! It is nice to see you! \n" +
                    "   Is there anything I can help?";
        }
    },

    BYE {
        @Override
        public String toString() {
            return "   It is nice to talk to you. \n" +
                    "   Hope to see you again!";
        }
    },

    ADD {
        @Override
        public String toString() {
            return "   added: ";
        }
    }
}
