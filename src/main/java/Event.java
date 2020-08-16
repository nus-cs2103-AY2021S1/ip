public class Event extends Task{
    String schedule;

    Event(String description, String schedule) {
        super(description);
        this.schedule = schedule.stripLeading().stripTrailing();
    }

    public static Event createTask(String message) throws DukeException{
        String lines = ".~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.\n";
        String errMessage = " Oops!! You missed out some vital information... *woof*\n";
        try {
            String messageLowerCase = message.toLowerCase();
            int indOfTime = messageLowerCase.indexOf("/at");
            String description = message.substring(6, indOfTime);
            String at = message.substring(indOfTime + 3);
            if (description.isBlank() || at.isBlank()) {
                String exMessage = lines + errMessage + lines;
                throw new DukeException(exMessage);
            } else {
                return new Event(description, at);
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
        return "[E]" + super.toString() + " (Appear at: " + schedule + ")";
    }
}
