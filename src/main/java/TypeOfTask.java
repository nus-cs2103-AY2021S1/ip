public enum TypeOfTask {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    public String letter;

    TypeOfTask(String letter) {
        this.letter = letter;
    }

    @Override
    public String toString() {
        return letter;
    }



}
