package tasks;

import exceptions.DukeDateTimeException;

class Event extends TimedTask {
    Event(String desc, String date) throws DukeDateTimeException {
        super(desc, date);
    }

    Event(String desc, String date, Boolean b) throws DukeDateTimeException {
        super(desc, date, b);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getDateby() + ")" + "You have " + timeLeft() + " days left till the event!";
    }

    public String saveTask() {
        return "E" + SEPERATOR + super.saveTask();
    }
}
