public class Deadline extends Task {
    String toDoBy;

    Deadline(String description, String toDoBy) {
        super(description);
        this.toDoBy = toDoBy.stripLeading().stripTrailing();
    }

    Deadline(String description, String toDoBy, Boolean isDone) {
        super(description, isDone);
        this.toDoBy = toDoBy.stripLeading().stripTrailing();
    }

    public static Deadline createTask(String message) throws DukeException{
        String errMessage1 = " Oops!! You missed out some vital information/keyword... *woof*\n";
        String errMessage2 = " Oops!! You gonna forget what this is about if you\n" +
                " dont give me a description... *woof*\n";
        String errMessage3 = " Oops!! You did not state when you wanna finish this by...\n" +
                " Are you planning to procrastinate? *woof*\n";
        try {
            String messageLowerCase = message.toLowerCase();
            int indOfTime = messageLowerCase.indexOf("/by");
            String description = message.substring(9, indOfTime);
            String deadline = message.substring(indOfTime + 3);
            if (description.isBlank() && deadline.isBlank()) {
                String exMessage = Print.printFormat(errMessage1);
                throw new DukeException(exMessage);
            }else if (deadline.isBlank()) {
                String exMessage = Print.printFormat(errMessage3);
                throw new DukeException(exMessage);
            } else if (description.isBlank()) {
                String exMessage = Print.printFormat(errMessage2);
                throw new DukeException(exMessage);
            } else {
                return new Deadline(description, deadline);
            }
        } catch (DukeException e) {
            throw e;
        } catch (Exception e) {
            String exMessage = Print.printFormat(errMessage1);
            throw new DukeException(exMessage);
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (FINISH by: " + toDoBy + ")";
    }
}
