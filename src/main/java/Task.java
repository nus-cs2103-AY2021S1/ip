class Task {
    private boolean isCompleted;
    private String name;

    Task(String name) {
        this.name = name;
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
