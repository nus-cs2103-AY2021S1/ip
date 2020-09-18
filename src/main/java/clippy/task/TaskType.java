package clippy.task;

public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private String letter;

    TaskType(String letter) {
        this.letter = letter;
    }

    @Override
    public String toString() {
        return letter;
    }
    
}
