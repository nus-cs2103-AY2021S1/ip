// InvalidInputException.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.util;

/**
 * An exception that is thrown when the user provides an invalid input, for example
 * invalid commands or malformed dates.
 */
public class InvalidInputException extends Exception {

	private final String usage;

    /**
     * Constructs a new InvalidInputException with the given message, and
	 * the expected usage of the command.
     *
     * @param msg   the exception message.
	 * @param usage the expected usage of the command.
     */
	public InvalidInputException(String msg, String usage) {

		super(msg);
		this.usage = usage;
	}

    /**
     * Gets the expected usage of the command, specific to the code that
	 * threw the exception.
     *
     * @return the expected usage.
     */
	public String getUsage() {
		return this.usage;
	}

	@Override
	public String toString() {
		// don't include the exception name.
		return this.getMessage();
	}
}
