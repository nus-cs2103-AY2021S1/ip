// Task.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.task;

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

    @Override
    public String toString() {

        return String.format("[%s] %s", this.done ? "\u2713" : "\u2718", this.name);
    }
}
