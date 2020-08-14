// Task.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

public abstract class Task {
    private String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean isDone() {
        return this.done;
    }

    public void markAsDone() {
        assert !this.done;
        this.done = true;
    }

    protected String getCheckboxString() {
        return String.format("[%s]", this.done ? "\u2713" : "\u2718");
    }
}
