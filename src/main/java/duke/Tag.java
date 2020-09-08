package duke;

public enum Tag {
    HAPPY, ANGRY, SAD, CONFUSED, EXCITED, DOGTAG;

    public static Tag StringToTag(String input) throws DukeException {
        switch (input) {
            case "happy":
                return HAPPY;
            case "angry":
                return ANGRY;
            case "sad":
                return SAD;
            case "confused":
                return CONFUSED;
            case "excited":
                return EXCITED;
            default:
                throw new DukeException("Sorry, Duke does not recognise that tag name :(");
        }
    }

    public static String TagToString(Tag tag) {
        switch (tag) {
            case HAPPY:
                return "happy";
            case ANGRY:
                return "angry";
            case SAD:
                return "sad";
            case CONFUSED:
                return "confused";
            case EXCITED:
                return "excited";
            default:
                return "dogtag";
        }
    }
}
