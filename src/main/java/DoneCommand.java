public class DoneCommand extends Command {

    //constructor function - constructs an object
    public DoneCommand(Category category, String description) throws IllegalArgumentException {
        super(category, description);
            if(description == null) {
                throw new IllegalArgumentException("-------------------------------------------\n" +
                        "☹ OOPS!!! Not sure which task is to be indicated as done. Try again!\n"
                        +"-------------------------------------------");
            }
    }
}

