public class UnspecifiedItemException extends  DukeException {
    private static String unspecifiedItemMsg1 = "My apologies, please specify an item when using the ";
    private static String unspecifiedItemMsg2 = " command.";
    public UnspecifiedItemException(String command) {
        super(unspecifiedItemMsg1 + command + unspecifiedItemMsg2);
    }
}
