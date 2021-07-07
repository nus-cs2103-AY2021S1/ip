/**
 * Represents the result of executing a command.
 */
public class CommandResult {
    public final String responseToUser;

    CommandResult(String responseToUser) {
        this.responseToUser = responseToUser;
        assert !this.responseToUser.equals("") : "Response cannot be empty";
    }
}
