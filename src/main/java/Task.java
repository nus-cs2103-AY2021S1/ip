class Task {
    private boolean isCompleted;
    private String name;

    Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String saveText() {
        String completeStatus = isCompleted ? "1" : "0";
        return "Task," +  completeStatus + "," + name;
    }

    public void markDone() {
        isCompleted = true;
        System.out.println("Nice, I've marked this task as done!");
        System.out.println(this);
    }

    @Override
    public String toString() {
        String symbol = isCompleted ? "\u2713" : "x";
        return "[" + symbol + "] " + name; 
    }
}
