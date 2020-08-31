package Duke.main;

/**
 * This is an enum class which
 * contains all the format related strings.
 */
public enum FormatString {
    UNDERSCORE {
        @Override
        public String toString() {
            return "  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        }
    },

    NEXTLINE {
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
