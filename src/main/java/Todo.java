// Todo.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
