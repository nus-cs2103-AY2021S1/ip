public class IllegalCharacterError extends DukeError {
    public IllegalCharacterError() {
        super("You message contains an illegal character!\nPlease remove it and try again!");
    }
}
