public class Deadline extends Task {
    String toDoBy;

    Deadline(String description, String toDoBy) {
        super(description);
        this.toDoBy = toDoBy.stripLeading().stripTrailing();
    }

    public static Deadline createTask(String message) throws DukeException{
        String lines = ".~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.\n";
        String errMessage = " Oops!! You missed out some vital information... *woof*\n";
        try {
            String messageLowerCase = message.toLowerCase();
            int indOfTime = messageLowerCase.indexOf("/by");
            String description = message.substring(9, indOfTime);
            String deadline = message.substring(indOfTime + 3);
            if (description.isBlank() || deadline.isBlank()) {
                String exMessage = lines+ errMessage + lines;
                throw new DukeException(exMessage);
            } else {
                return new Deadline(description, deadline);
            }
        } catch (DukeException e) {
            throw e;
        } catch (Exception e) {
            String exMessage = lines + errMessage + lines;
            throw new DukeException(exMessage);
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (FINISH by: " + toDoBy + ")";
    }
}
