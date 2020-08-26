import java.util.Arrays;

public class Parser {
    /**
     * Checks if string contains only numbers.
     *
     * @param input input string to check
     * @return if string contains only numbers
     **/
    public static boolean isNumber(String input) {
        return input.matches("[0-9]+");
    }

    public static String getStringFromArray(String[] inputArr, int indexFrom, int indexTo) {
        String output = "";
        for (int i = indexFrom; i < indexTo; i++){
            output += inputArr[i] + " ";
        }
        return output.substring(0, output.length() - 1);
    }

    public static Command parse(String input) throws DukeException {
        String[] inputInformation = input.split(" ");
        if (inputInformation[0].equals(CommandType.BYE.getInput())) {
            return new Command(CommandType.BYE);
        } else if (inputInformation[0].equals(CommandType.LIST.getInput())) {
            return new Command(CommandType.LIST);
        } else if (inputInformation[0].equals(CommandType.DONE.getInput())) {
            if (inputInformation.length > 1 && isNumber(inputInformation[1])) {
                int taskNumber = Integer.parseInt(inputInformation[1]);
                return new Command((CommandType.DONE), taskNumber);
            } else {
                throw new DukeException("You need to include your task number to mark done...");
            }
        } else if (inputInformation[0].equals(CommandType.DELETE.getInput())) {
            if (inputInformation.length > 1 && isNumber(inputInformation[1])) {
                int taskNumber = Integer.parseInt(inputInformation[1]);
                return new Command((CommandType.DELETE), taskNumber);
            } else {
                throw new DukeException("You need to include your task number to delete...");
            }
        } else if (inputInformation[0].equals(CommandType.TODO.getInput())) {
            if (inputInformation.length > 1) {
                String description = getStringFromArray(inputInformation, 1, inputInformation.length);
                return new Command(CommandType.TODO, description);
            } else {
                throw new DukeException("Your todo description can't be empty...");
            }
        } else if (inputInformation[0].equals(CommandType.DEADLINE.getInput())) {
            if (inputInformation.length > 3) {
                int indexOfBy = Arrays.asList(inputInformation).indexOf("/by");
                if (indexOfBy == -1) {
                    throw new DukeException("Did you include /by?");
                } else if (indexOfBy == 1) {
                    throw new DukeException("Did you include a description?");
                } else {
                    String description = getStringFromArray(inputInformation, 1, indexOfBy);
                    String by = getStringFromArray(inputInformation, indexOfBy + 1, inputInformation.length);
                    return new Command(CommandType.DEADLINE, description, by);
                }
            } else {
                throw new DukeException("Your deadline description or deadline can't be empty...");
            }
        } else if (inputInformation[0].equals(CommandType.EVENT.getInput())) {
            if (inputInformation.length > 3) {
                int indexOfAt = Arrays.asList(inputInformation).indexOf("/at");
                if (indexOfAt == -1) {
                    throw new DukeException("Did you include /at?");
                } else if (indexOfAt == 1) {
                    throw new DukeException("Did you include a description?");
                } else {
                    String description = getStringFromArray(inputInformation, 1, indexOfAt);
                    String at = getStringFromArray(inputInformation, indexOfAt + 1, inputInformation.length);
                    return new Command(CommandType.EVENT, description, at);
                }
            } else {
                throw new DukeException("Your event description or event period can't be empty...");
            }
        } else {
            throw new DukeException("My duck instincts tell me your input makes no sense...");
        }
    }

}
