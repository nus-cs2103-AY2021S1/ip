/**
 * The exception that is thrown when a user intends to act on a task number that does not exist.
 */
public class InvalidTaskNumber extends DukeException {
    public InvalidTaskNumber() {
        super("Oh no, don't be blur like Nobita >_<. This task number does not exist!");
    }
}
