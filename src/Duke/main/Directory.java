package Duke.main;

/**
 * This is an enum class that contains
 * the directories needed for the operation
 * of Duke
 */
public enum Directory {
    FILEDIRECTORY {
        @Override
        public String toString() {
            return "./data/Duke.txt";
        }
    }
}
