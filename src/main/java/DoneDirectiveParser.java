public class DoneDirectiveParser {
    private final String ERROR_INCORRECT_INDEX = "sToP TrYiNg tO FoOl mE. tHe \"done\" ComMand"
            + " mUsT Be FolLoWed bY tHe InDEx Of THe TAsK.";

    public DoneDirective parse(String[] args) throws IncorrectArgumentException {
        try {
            // The index provided by the user is off by one
            int index = Integer.parseInt(args[1]) - 1;

            return new DoneDirective(index);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new IncorrectArgumentException(ERROR_INCORRECT_INDEX);
        }
    }
}
