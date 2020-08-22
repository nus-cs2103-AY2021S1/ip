public class ListCommand extends Command {

    public ListCommand(Category category, String description) throws IllegalArgumentException {
        super(category);
        if(description != null){
            throw new IllegalArgumentException("-------------------------------------------\n" +
                    "☹ OOPS!!! Invalid input. Try again!\n"
                    +"-------------------------------------------");
        }
    }
}