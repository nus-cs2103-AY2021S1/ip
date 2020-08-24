public class EventCommand extends Command {

    public EventCommand(Category category, String description) throws IllegalArgumentException {
        super(category, description);
        if(description == null) {
            throw new IllegalArgumentException("-------------------------------------------\n" +
                    "☹ OOPS!!! The description of an event cannot be empty. Try again!\n"
                    +"-------------------------------------------");
        } else {
            System.out.println("-------------------------------------------\n" +
                    "Got it. I've added this task:");
        }
    }
}
