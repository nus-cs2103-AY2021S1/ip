// InvalidInputException.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

public class InvalidInputException extends Exception {

	private final String usage;

	public InvalidInputException(String message, String usage) {

		super(message);
		this.usage = usage;
	}

	public String getUsage() {

		return this.usage;
	}
}
