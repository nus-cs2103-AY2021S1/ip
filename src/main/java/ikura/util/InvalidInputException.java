// InvalidInputException.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.util;

public class InvalidInputException extends Exception {

	private final String usage;

	public InvalidInputException(String message, String usage) {

		super(message);
		this.usage = usage;
	}

	public String getUsage() {

		return this.usage;
	}

	@Override
	public String toString() {

		// don't include the exception name.
		return this.getMessage();
	}
}
