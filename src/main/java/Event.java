public class Event extends Task{
    String schedule;

    Event(String description, String schedule) {
        super(description);
        this.schedule = schedule.stripLeading().stripTrailing();
    }

    Event(String description, String schedule, Boolean isDone) {
        super(description, isDone);
        this.schedule = schedule.stripLeading().stripTrailing();
    }

    public static Event createTask(String message) throws DukeException{
        String errMessage1 = " Oops!! You missed out some vital information/keyword... *woof*\n";
        String errMessage2 = " Oops!! Are you planning to ghost the event?\n" +
                " You didnt state the time of this event... *woof*\n";
        String errMessage3 = " Oops!! You gonna forget what this is about if you\n" +
                " dont give me a description... *woof*\n";
        try {
            String messageLowerCase = message.toLowerCase();
            int indOfTime = messageLowerCase.indexOf("/at");
            String description = message.substring(6, indOfTime);
            String at = message.substring(indOfTime + 3);
            if (description.isBlank() && at.isBlank()) {
                String exMessage = Print.printFormat(errMessage1);
                throw new DukeException(exMessage);
            } else if (at.isBlank()) {
                String exMessage = Print.printFormat(errMessage2);
                throw new DukeException(exMessage);
            } else if (description.isBlank()) {
                String exMessage = Print.printFormat(errMessage3);
                throw new DukeException(exMessage);
            } else {
                return new Event(description, at);
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
        return "[E]" + super.toString() + " (APPEAR at: " + schedule + ")";
    }
}
