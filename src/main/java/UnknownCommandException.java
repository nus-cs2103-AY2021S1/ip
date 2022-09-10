
public class UnknownCommandException extends DukeException {

    UnknownCommandException(String unknown) {
        this.errorMessage = String.format("Error: W-What does *BUURRRRRP* '%s' mean Morty?\n" +
                " You're probably speaking english but I still need a universal translator" +
                " to understand your freaking words! "
                + " You might as well try using the 'help' to see what I do understand", unknown);
    }
}
