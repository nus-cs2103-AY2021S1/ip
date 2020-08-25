public class InvalidTaskDateException extends InvalidUserCommandException{
    public InvalidTaskDateException(String invalidDate) {
        super("OOPS! " + invalidDate + " does not exist." 
            + "\nPlease check to ensure that the date and time are correct.");
    } 
}
