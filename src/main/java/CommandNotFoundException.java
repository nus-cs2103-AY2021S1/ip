public class CommandNotFoundException extends DukeException {

    CommandNotFoundException() {
        this("OOPS!!! I'm sorry, but I don't know what that means... (´∀`)\n" +
                "So far I support store different tasks, eg: todo, deadline, event. ٩(ˊᗜˋ*)و");
    }

    CommandNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
