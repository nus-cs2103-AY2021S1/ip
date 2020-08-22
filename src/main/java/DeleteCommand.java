public class DeleteCommand extends Command {

    public DeleteCommand(Category category, String description) throws IllegalArgumentException {
        super(category, description);
        if(description == null) {
            throw new IllegalArgumentException("-------------------------------------------\n" +
                    "☹ OOPS!!! Not sure which task is to be deleted. Try again!\n"
                    +"-------------------------------------------");
        }
    }
}
