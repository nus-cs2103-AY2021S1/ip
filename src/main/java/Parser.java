public class Parser {

    private Task parseTaskToAdd(String consoleArg) throws EmptyTaskDescriptionException, InvalidTaskTimeException,
            UnknownCommandException, InvalidInputException {

        if (consoleArg.length() <= 4) {
            throw new UnknownCommandException("Error: Missing task type keyword");
        }
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
                throw new EmptyTaskDescriptionException("Error: Empty task name not allowed");
            }

            return new Todo(name, false);

        } else if (keyword.equals("deadline")) {
            String name = "";
            String time = "";
            boolean nameOrTime = true;
            for (int i = 1; i < parsedArr.length; i++) {
                if (nameOrTime) {
                    if (parsedArr[i].contains("/by")) {
                        if (name.equals("")) {
                            throw new EmptyTaskDescriptionException("Error: Empty task name not allowed");
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
                throw new EmptyTaskDescriptionException("Error: Empty task name not allowed");
            }
            if (time.equals("")) {
                throw new InvalidTaskTimeException("Error: Empty task name not allowed");
            }

            TaskDate dateTime = DateParser.parseDate(time);
            return new Deadline(name, false, dateTime);

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
                throw new EmptyTaskDescriptionException("Error: Empty task name not allowed");
            }
            if (timeRange.equals("")) {
                throw new InvalidTaskTimeException("Error: Please input a time range");
            }

            TaskDate startDateTime = DateParser.getRange(timeRange, true);
            TaskDate endDateTime = DateParser.getRange(timeRange, false);
            return new Event(name, false, startDateTime, endDateTime);
        } else {
            throw new UnknownCommandException("Error: Invalid Event Type, please try again.");
        }
    }

    public Command parseCommand(String userInput) throws InvalidInputException, UnknownCommandException {
        String[] words = userInput.split(" ");

        switch (words[0]) {

        case ("bye"):
            return new ExitCommand();
        case ("list"):
            return new PrintListCommand();
        case ("add"):
            Task taskToAdd = parseTaskToAdd(userInput);
            return new AddCommand(taskToAdd);
        case ("delete"):
            try {
                if (words.length < 2) {
                    throw new InvalidInputException("Error: No number argument after 'delete'");
                }
                int taskIdToDelete = Integer.parseInt(words[1]);
                return new DeleteCommand(taskIdToDelete);
            } catch (NumberFormatException e) {
                throw new InvalidInputException("Error: Invalid number argument after 'delete'");
            }
        case ("done"):
            try {
                if (words.length < 2) {
                    throw new InvalidInputException("Error: No number argument after 'delete'");
                }
                int taskIdToComplete = Integer.parseInt(words[1]);
                return new DoneCommand(taskIdToComplete);
            } catch (NumberFormatException e) {
                throw new InvalidInputException("Error: Invalid number argument after 'delete'");
            }
        }

        throw new InvalidInputException("Error: Invalid command keyword!");
    }

    public Task parseFromStorage(String storageLine) {
        String[] taskData = storageLine.split(" \\| ");
        String taskType = taskData[0];
        boolean taskIsDone = Integer.valueOf(taskData[1]) == 1;
        String taskName = taskData[2];

        if (!taskType.equals("T")) {
            String taskTime = taskData[3];
            if (taskType.equals("D")) {
                TaskDate dueTime = DateParser.parseDateFromStorage(taskTime);
                return new Deadline(taskName, taskIsDone, dueTime);
            } else {
                TaskDate startDate = DateParser.parseRangeFromStorage(taskTime, true);
                TaskDate endDate = DateParser.parseRangeFromStorage(taskTime, false);

                return new Event(taskName, taskIsDone, startDate, endDate);
            }
        } else {
            return new Todo(taskName, taskIsDone);
        }
    }
}