package duke.tools;

/**
 * Contains all the format related strings.
 */
public enum FormatString {
    NEXT_LINE {
        @Override
        public String toString() {
            return "\n";
        }
    },

    TICK {
        @Override
        public String toString() {
            return "\u2713";
        }
    },

    CROSS {
        @Override
        public String toString() {
            return "\u2718";
        }
    },

    SPACE {
        @Override
        public String toString() {
            return " ";
        }
    },

    EMPTY {
        @Override
        public String toString() {
            return "";
        }
    }

}
