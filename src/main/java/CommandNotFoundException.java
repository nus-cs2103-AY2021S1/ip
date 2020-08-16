public class CommandNotFoundException extends DukeException {
    public static final String eM = "OOPS!!! I'm sorry, but I don't know what that means (´∀`)";

    CommandNotFoundException() {
        this("OOPS!!! I'm sorry, but I don't know what that means... (´∀`)\n" +
                "So far I support store different tasks, eg: todo, deadline, event. ٩(ˊᗜˋ*)و");
    }

    CommandNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
