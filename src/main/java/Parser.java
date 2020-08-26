import java.util.Date;

public class Parser {
    String[] commandArr;

    public Parser(String userInput) {
        this.commandArr = userInput.split(" ", 2);
    }

    public String getCommand() {
        return this.commandArr[0];
    }

    public int getIndex() throws MissingInformationException, NumberFormatException {

        if (this.commandArr.length < 2 || commandArr[1].isBlank()) {
            throw new MissingInformationException("Task number is missing!");
        }
        return Integer.parseInt(commandArr[1]);
    }

    public String getDescription(String taskType) throws MissingInformationException {
        if (commandArr.length < 2 || commandArr[1].isBlank()) {
            throw new MissingInformationException(
                    String.format("The description of a %s cannot be empty.", taskType));
        }

        return commandArr[1];
    }

    public String getName(String taskType) throws MissingInformationException {
        String description = getDescription(taskType);
        String splitBy = taskType.equals("deadline") ? " /by " : " /at ";
        String[] detailArr = description.split(splitBy, 2);
        return detailArr[0];
    }

    public Date getDeadlineBy() throws MissingInformationException, DateException {
        String details = commandArr[1];
        String[] detailArr = details.split(" /by ", 2);
        if (detailArr.length < 2 || detailArr[1].isBlank()) {
            throw new MissingInformationException("Deadline is missing a date.");
        }
        return DateFormat.parseDate(detailArr[1]);
    }

    public Date getEventAt() throws MissingInformationException, DateException {
        String details = commandArr[1];
        String[] detailArr = details.split(" /at ", 2);
        if (detailArr.length < 2 || detailArr[1].isBlank()) {
            throw new MissingInformationException("Event is missing a date.");
        }
        return DateFormat.parseDate(detailArr[1]);
    }

    public String getDate() throws MissingInformationException {
        if (commandArr.length < 2 || commandArr[1].isBlank()) {
            throw new MissingInformationException("Date is missing!");
        } else {
            return commandArr[1];
        }
    }
}
