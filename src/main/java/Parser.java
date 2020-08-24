public class Parser {

    enum Keyword {
        EXIT("exit"),
        LIST("list"),
        DONE("done"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DELETE("delete");

        private final String input;

        Keyword(String keyword) {
            this.input = keyword;
        }

        public static Keyword findKeyword(String keyword) throws DukeException {
            for (Keyword k : values()) {
                if (keyword.equals(k.input)) {
                    return k;
                }
            }
            throw new DukeException("Wakarimasen~");
        }
    }

    public static Command parse(String fullCommand) {
        String[] inputs = fullCommand.split(" ", 2);
        // By spec, inputs is guaranteed to have at least one element.
        String userInput = inputs[0];
        Keyword keyword = Keyword.findKeyword(userInput);
        switch (keyword) {
        case EXIT:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case DONE:
            try {
                int index = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
                return new DoneCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException("This isn't harry potter, please use only integers.");
            }
        case DELETE:
            try {
                int index = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
                return new DeleteCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException("This isn't harry potter, please use only integers.");
            }
        case DEADLINE:
            try {
                return new AddCommand(TaskType.DEADLINE, inputs[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("What are you rushing for? To wait?");
            }
        case TODO:
            try {
                return new AddCommand(TaskType.TODO, inputs[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("I know your life is empty but your todo can't be empty.");
            }
        case EVENT:
            try {
                return new AddCommand(TaskType.EVENT, inputs[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Are you going to attend a nameless event?");
            }
        default:
            throw new DukeException("Wakarimasen~");
        }
    }
}
