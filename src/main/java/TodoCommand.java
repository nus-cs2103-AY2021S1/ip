public class TodoCommand extends Command {

    public TodoCommand(Category category, String description) throws IllegalArgumentException {
        super(category, description);
        if(description == null) {
            throw new IllegalArgumentException("-------------------------------------------\n" +
                                                    "☹ OOPS!!! The description of a todo cannot be empty. Try again!\n"
                                                            +"-------------------------------------------");
        }
    }
}
