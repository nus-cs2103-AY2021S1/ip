import java.util.Arrays;

public class StringProcessor {

    private String rawInput;

//    StringProcessor(String rawInput) {
//        this.rawInput = rawInput;
//    }

    public static Task stringProcessor(String rawInput) throws InvalidInputException{
        String[] splitString = rawInput.split(" ");
        int splitStringLength = splitString.length;


        if (splitString[0].equals("todo")) {

            if (splitString.length == 1) {
                throw new InvalidInputException("Description of To Do cannot be empty");
            } else {
                String[] taskStringArray = Arrays.copyOfRange(splitString, 1, splitStringLength);
                return new Todo(String.join(" ", taskStringArray));
            }

        } else if (splitString[0].equals("deadline")) {

            if (splitString.length == 1) {
                throw new InvalidInputException("Description of Deadline cannot be empty");
            } else {
                String[] taskStringArray = Arrays.copyOfRange(splitString, 1, splitStringLength);
                String toSplit = String.join(" ", taskStringArray);
                int indexOfSlash = toSplit.indexOf("/");
                if (indexOfSlash == -1) {
                    throw new InvalidInputException("Date/time for Deadline cannot be empty/not recognised");
                } else {
                    int indexOfNextWord = toSplit.indexOf(" ", indexOfSlash);
                    return new Deadline(toSplit.substring(0, indexOfSlash),
                            toSplit.substring(indexOfSlash + 1, indexOfNextWord),
                            toSplit.substring(indexOfNextWord + 1));
                }
            }

        } else if (splitString[0].equals("event")) {
            if (splitString.length == 1) {
                throw new InvalidInputException("Description of Event cannot be empty");
            } else {
                String[] taskStringArray = Arrays.copyOfRange(splitString, 1, splitStringLength);
                String toSplit = String.join(" ", taskStringArray);
                int indexOfSlash = toSplit.indexOf("/");
                if (indexOfSlash == -1) {
                    throw new InvalidInputException("Date/time for Event cannot be empty/not recognised");
                } else {
                    int indexOfNextWord = toSplit.indexOf(" ", indexOfSlash);
                    return new Event(toSplit.substring(0, indexOfSlash - 1),
                            toSplit.substring(indexOfSlash + 1, indexOfNextWord),
                            toSplit.substring(indexOfNextWord + 1));
                }
            }
        } else {
            throw new InvalidInputException("Unrecognized task");
        }
    }
}
