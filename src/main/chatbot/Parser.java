public class Parser {

    public static Command parse(String fullCmd) throws ChatbotException{

        String text = fullCmd.trim();
        String typeStr = text.split(" ")[0].trim();
        String body = text.substring(typeStr.length()).trim();

        Command command = null;

        try {
            Type type = Type.valueOf(typeStr.toUpperCase());

            boolean isShow = type == Type.LIST || type == Type.DATE;
            boolean isAdd = type == Type.TODO || type == Type.DEADLINE || type == Type.EVENT;
            boolean isAction = type == Type.DELETE || type == Type.DONE;
            boolean isExit = type == Type.BYE;

            if (isShow) {
                command = new ShowCommand(type, body);
            } else if (isAdd) {
                command =  new AddCommand(type, body);
            } else if (isAction) {
                command = new ActionCommand(type, body);
            } else if (isExit) {
                command = new ExitCommand();
            } else {
                parseError();
            }

        } catch (IllegalArgumentException e){
            parseError();
        }

        return command;
    }

    private static void parseError() throws ChatbotException {
        throw new ChatbotException("Arghh! I do not know what you mean, are you using the right\n    " +
                "commands?");
    }

}
