// Event.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

public class Event extends Task {

	private final String eventDate;

    public Event(String name, String date) {
        super(name);
		this.eventDate = date;
    }

    @Override
    public String toString() {
        return String.format("[E] %s %s (at: %s)",
            this.getCheckboxString(),
            this.getName(),
			this.eventDate
        );
    }
}
