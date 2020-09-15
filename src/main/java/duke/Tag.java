package duke;

public enum Tag {
    FUN, BORING, EXCITING, URGENT, CHILL, SIAN, LAZE, DUMMY;

    /**
     * Converts the input string into a Tag.
     *
     * @return The tag corresponding to the input string.
     * @throws DukeException  If Duke does not recognise the specified tag.
     */
    public static Tag stringToTag(String input) throws DukeException {
        switch (input) {
        case "fun":
            return FUN;
        case "boring":
            return BORING;
        case "exciting":
            return EXCITING;
        case "urgent":
            return URGENT;
        case "chill":
            return CHILL;
        case "sian":
            return SIAN;
        case "laze":
            return LAZE;
        case "dummy":
            return DUMMY;
        default:
            throw new DukeException("Sorry, Duke does not recognise that tag name :(");
        }
    }

    /**
     * Converts the input tag into a String.
     *
     * @return The string corresponding to the input tag.
     */
    public static String tagToString(Tag tag) {
        switch (tag) {
        case FUN:
            return "fun";
        case BORING:
            return "boring";
        case EXCITING:
            return "exciting";
        case URGENT:
            return "urgent";
        case CHILL:
            return "chill";
        case SIAN:
            return "sian";
        case LAZE:
            return "laze";
        default:
            return "dummy";
        }
    }
}
