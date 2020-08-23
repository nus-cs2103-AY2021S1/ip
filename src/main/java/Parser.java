public class Parser {
    protected Task parseToTask(String input) {
        try {
            String[] inputArr = input.split("\\s{2},", 4);
            if (inputArr.length < 4) {
                throw new InvalidFileFormatException();
            }
            String taskType = inputArr[0].toUpperCase();
            String description = inputArr[1];
            String time = inputArr[2];
            String status = inputArr[3];
            boolean isDone = checkStatus(status);

            if (taskType.equals("TODO")) {
                return new ToDo(description, isDone);
            } else if (taskType.equals("EVENT")) {
                return new Event(description, time, isDone);
            } else if (taskType.equals("DEADLINE")) { // DEADLINE
                return new Deadline(description, time, isDone);
            } else {
                throw new InvalidFileFormatException();
            }
        } catch (InvalidFileFormatException e) {
            Ui.printBasic(e.getMessage());
            return null;
        }
    }

    private boolean checkStatus(String input) throws InvalidFileFormatException {
        String status = input.toLowerCase();
        if (status.equals("done")) {
            return true;
        } else if (status.equals("not done")) {
            return false;
        } else {
            throw new InvalidFileFormatException();
        }
    }
}
