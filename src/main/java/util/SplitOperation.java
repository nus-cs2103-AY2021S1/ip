package util;

/**
 * Represents a functional interface with a operate function to split a string.
 */
public interface SplitOperation {
    /**
     * Returns properly formatted string after splitting.
     * @param input string
     * @return Properly formatted string after splitting.
     */
    String operate(String input);
}
