import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    public Command parse(String textToParse) {

        String[] inputArray = textToParse.split(" ");
        String firstWord = inputArray[0];
        Command command = null;

        try {
            if (firstWord.toLowerCase().equals("bye")) { // for termination
                command = new EndCommand();
            } else if (firstWord.toLowerCase().equals("list")) { // to display list of tasks
                command = new ListCommand();
            } else if (firstWord.toLowerCase().equals("done")) {
                if (inputArray.length <= 1) throw new DukeException("Sorry, which item did you want me to mark as done again?");
                int index = Integer.parseInt(inputArray[1]);
                command =  new DoneCommand(index - 1);
            } else if (firstWord.toLowerCase().equals("delete")) {
                if (inputArray.length <= 1) throw new DukeException("Sorry, which item did you want me to strike off again?");
                try {
                    int index = Integer.parseInt(inputArray[1]);
                    command = new DeleteCommand(index - 1);
                } catch (NumberFormatException e) {
                    throw new DukeException("I need a number not a word in this case. Could ya pass that by me one more time?");
                }
            } else { // to add task to list

                Task task;
                int index;

                switch (firstWord.toLowerCase()) {
                    case "todo":
                        if (inputArray.length <= 1)
                            throw new DukeException("Sorry, but I can't do anything if you don't give me the description of your todo!");
                        String desc = stringCombiner(inputArray, 1, inputArray.length - 1);
                        command = AddCommand.addTodo(desc.trim());
                        break;
                    case "event":
                        String[] eventSplit = textToParse.split("/at");
                        index = indexFinder(inputArray, "/at");
                        if (index == 0) throw new DukeException("Think you forgot the /at keyword, pardner!");
                        if (index == 1)
                            throw new DukeException("I'm gonna need a description for this here event!");
                        if (eventSplit.length == 1)
                            throw new DukeException("I'm gonna need a date or time for this!");
                        try {
                            command = AddCommand.addEvent(stringCombiner(inputArray, 1, index - 1).trim(), LocalDate.parse(eventSplit[1].trim()));
                        } catch (DateTimeParseException e) {
                            throw new DukeException("Can't seem to make out this date over here");
                        }
                        break;
                    case "deadline":
                        String[] deadlineSplit = textToParse.split("/by");
                        index = indexFinder(inputArray, "/by");
                        if (index == 0) throw new DukeException("Think you forgot the /by keyword, pardner!");
                        if (index == 1)
                            throw new DukeException("I'm gonna need a description for this here deadline!");
                        if (deadlineSplit.length == 1)
                            throw new DukeException("I'm gonna need a date or time for this!");
                        try {
                            command = AddCommand.addDeadline(stringCombiner(inputArray, 1, index - 1).trim(), LocalDate.parse(deadlineSplit[1].trim()));
                        } catch (DateTimeParseException e) {
                            throw new DukeException("Can't seem to make out this date over here");
                        }
                        break;
                    default:
                        System.out.println("Sorry, I didn't quite catch that!");
                        command = new EmptyCommand();
                }

            }
        } catch (DukeException e) {
            System.out.println(e.toString());
        }
        if (command != null) {
            return command;
        } else {
            return new EmptyCommand();
        }
    }

    private static String stringCombiner(String[] arr, int start, int end) {
        StringBuffer str = new StringBuffer();
        for (int i = start; i <= end; i ++) {
            str.append(arr[i] + " ");
        }
        return str.toString();
    }

    private static int indexFinder(String[] arr, String exp) {
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(exp)) index = i;
        }
        return index;
    }

}
