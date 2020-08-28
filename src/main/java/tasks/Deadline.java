package tasks;


import exceptions.DukeDateTimeException;

class Deadline extends TimedTask {
    Deadline(String desc, String date) throws DukeDateTimeException {
        super(desc, date);
    }

    Deadline(String desc, String date, Boolean b) throws DukeDateTimeException {
        super(desc, date, b);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateby() + ")" + "You have " + timeLeft() + " days left till its due!";
    }

    @Override
    public String saveTask() {
        return "D" + SEPERATOR + super.saveTask();
    }
}
