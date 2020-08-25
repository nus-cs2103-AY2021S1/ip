public enum TaskType {
    TODO ("[T]"),
    DEADLINE ("[D]"),
    EVENT ("[E]");

    private String taskSymbol;

    TaskType(String taskSymbol) {
        this.taskSymbol = taskSymbol;
    }

    @Override
    public String toString() {
        return taskSymbol;
    }
}
