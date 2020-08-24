class Todo extends Task {
    Todo(String name) {
        super(name);
    }

    @Override
    public String saveText() {
        String completeStatus = super.isCompleted() ? "1" : "0";
;        return "T," + completeStatus + "," + super.getName();
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
