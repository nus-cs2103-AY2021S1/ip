public class Evaluator {
    public static String[] extractAction(String command) throws DukeException {
        String[] split = command.split(" ", 2);
        int len = split.length;

        // command is empty
        if (len == 0) {
            throw new DukeException(Messenger.EMPTY_COMMAND_ERROR);
        }

        String status = split[0];
        if (TaskStatus.valueOfStatus(status) == null) {
            throw new DukeException(Messenger.SPELL_ERROR);
        }

        // no description supplied
        if (len == 1) {
            throw new DukeException(Messenger.emptyDescriptionError(command));
        }
        return split;
    }

    public static String[] extractTime(String command) throws DukeException {
        String[] split = command.split(" /by | /at ", 2);
        if (split.length < 2) {
            if (split[0].charAt(0) == '/') {
                throw new DukeException(Messenger.EMPTY_CONTENT_ERROR);
            } else {
                throw new DukeException(Messenger.EMPTY_TIME_ERROR);
            }
        }
        return split;
    }
}
