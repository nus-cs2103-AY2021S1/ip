import java.time.LocalDateTime;

public class Parser {

    private Task parseTaskToAdd(String consoleArg) throws EmptyTaskDescriptionException, InvalidTaskTimeException,
            UnknownCommandException, InvalidInputException {

        String[] parsedArr = consoleArg.substring(4).split(" ");
        String keyword = parsedArr[0];
        if (keyword.equals("todo")) {
            String name = "";

            for (int i = 1; i < parsedArr.length; i++) {
                if (i != parsedArr.length - 1) {
                    name += parsedArr[i] + " ";
                } else {
                    name += parsedArr[i];
                }
            }
            if (name.equals("")) {
                throw new EmptyTaskDescriptionException("");
            }

            return new Todo(name);

        } else if (keyword.equals("deadline")) {
            String name = "";
            String time = "";
            boolean nameOrTime = true;
            for (int i = 1; i < parsedArr.length; i++) {
                if (nameOrTime) {
                    if (parsedArr[i].contains("/by")) {
                        if (name.equals("")) {
                            throw new EmptyTaskDescriptionException("");
                        }
                        nameOrTime = false;
                        continue;
                    } else {
                        name += parsedArr[i] + " ";
                    }
                } else {
                    if (i != parsedArr.length - 1) {
                        time += parsedArr[i] + " ";
                    } else {
                        time += parsedArr[i];
                    }
                }
            }
            if (name.equals("")) {
                throw new EmptyTaskDescriptionException("");
            }
            if (time.equals("")) {
                throw new InvalidTaskTimeException("");
            }

            TaskDate dateTime = DateParser.parseDate(time);
            return new Deadline(name, dateTime);

        } else if (keyword.equals("event")) {
            String name = "";
            String timeRange = "";
            boolean nameOrTime = true;
            for (int i = 1; i < parsedArr.length; i++) {
                if (nameOrTime) {
                    if (parsedArr[i].contains("/at")) {
                        nameOrTime = false;
                        continue;
                    } else {
                        name += parsedArr[i] + " ";
                    }
                } else {
                    if (i != parsedArr.length - 1) {
                        timeRange += parsedArr[i] + " ";
                    } else {
                        timeRange += parsedArr[i];
                    }
                }
            }

            if (name.equals("")) {
                throw new EmptyTaskDescriptionException("");
            }
            if (timeRange.equals("")) {
                throw new InvalidTaskTimeException("");
            }

            TaskDate startDateTime = DateParser.getRange(timeRange, true);
            TaskDate endDateTime = DateParser.getRange(timeRange, false);
            return new Event(name, startDateTime, endDateTime);
        } else {
            throw new UnknownCommandException("Error: Invalid Event Type, please try again.");
        }
    }

    public Command parseCommand(String userInput) throws InvalidInputException {
        String[] words = userInput.split(" ");

        switch (words[0]) {

        case ("bye"):
            return new ExitCommand();
        case ("list"):
            return new PrintListCommand();
        case ("add"):
            try {
                Task taskToAdd = parseTaskToAdd(userInput);
                return new AddCommand(taskToAdd);
            } catch (Exception e) {
                return new PrintErrorCommand(e.getMessage());
            }
        case ("delete"):
            try {
                int taskIdToDelete = Integer.parseInt(words[1]);
                return new DeleteCommand(taskIdToDelete);
            } catch (NumberFormatException e) {
                throw new InvalidInputException("Error: Invalid number argument after 'delete'");
            }
        case ("done"):
            try {
                int taskIdToComplete = Integer.parseInt(words[1]);
                return new DoneCommand(taskIdToComplete);
            } catch (NumberFormatException e) {
                throw new InvalidInputException("Error: Invalid number argument after 'delete'");
            }
        }

        return new PrintErrorCommand("Error: Invalid command keyword!");
    }
}