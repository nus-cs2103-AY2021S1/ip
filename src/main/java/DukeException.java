public class DukeException extends Throwable {
    String word;
    public DukeException(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return String.format("I'm sorry, I don't know what '%s' means", word);
    }
}
