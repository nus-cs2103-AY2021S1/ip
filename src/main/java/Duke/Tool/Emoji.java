package Duke.Tool;

/**
 * Represents emoji symbol class.
 */
public enum Emoji {

    CHICKEN(0x1F423),
    ERROR(0x1F616),
    SMILE(0x1F609);
    
    private final int code;

    /**
     * Construct an emoji object.
     * @param emoji unicode of the emoji.
     */
    private Emoji(int emoji) {
        this.code = emoji;
    }

    @Override
    public String toString() {
        return new String(Character.toChars(this.code));
    }
}
