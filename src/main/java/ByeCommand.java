public class ByeCommand extends Command {

    public ByeCommand(Category category, String description) throws IllegalArgumentException {
        super(category);
        if(description != null){
            throw new IllegalArgumentException("-------------------------------------------\n" +
                    "☹ OOPS!!! Invalid input. Try again!\n"
                    +"-------------------------------------------");
        }
    }
}