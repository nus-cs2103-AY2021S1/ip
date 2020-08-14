// Deadline.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

public class Deadline extends Task {

	private final String deadline;

    public Deadline(String name, String deadline) {
        super(name);
		this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[E] %s %s (by: %s)",
            this.getCheckboxString(),
            this.getName(),
			this.deadline
        );
    }
}
