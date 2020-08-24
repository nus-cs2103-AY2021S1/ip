class Task {
    boolean completed = false;
    String name;
    Type type;

    Task(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public void setCompleted() {
        completed = true;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return (completed ? "[✓]" : "[✗]") + " " + name;
    }

    enum Type {
        TODO, DEADLINE, EVENT;
    }
}

