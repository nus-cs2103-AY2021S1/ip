
public class DeadlineCommand extends Command {

    public DeadlineCommand(Category category, String description) throws IllegalArgumentException {
        super(category, description);
        if(description == null) {
            throw new IllegalArgumentException("-------------------------------------------\n" +
                                                "☹ OOPS!!! The description of a deadline cannot be empty. Try again!\n"
                                                +"-------------------------------------------");
        }
    }
}
