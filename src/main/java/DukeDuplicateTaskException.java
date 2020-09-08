public class DukeDuplicateTaskException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + "I can't add that task, you already have one just like it!";
    }
}
