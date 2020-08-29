package tasks;

import exceptions.DukeDateTimeException;

/**
 * The Event class contains the information that is entered by the user into the 
 * Duke Chatbot Command Line Interface.
 */
class Event extends TimedTask {
    /**
     * Constructor for Event Class without done status
     * To be used for creating new tasks by the end user
     * @param desc the description of the task
     * @param date the date on which the task is due 
     * @throws DukeDateTimeException
     */
    Event(String desc,String date) throws DukeDateTimeException {
        super(desc, date);
    }

    /**
     * Constructor for Event class with done status
     * To be used by the I/O manager to read tasks and populate the system at runtime
     * @param desc the description of the task
     * @param date the date on which the task is due 
     * @param b the Done Status of the Task
     * @throws DukeDateTimeException if the fields for the date are not matching autocorrection cases
     */
    Event(String desc,String date,Boolean b) throws DukeDateTimeException {
        super(desc, date, b);
    }
    @Override
    public String toString(){
        return "[E]" + super.toString()+" (at: "+getDateby()+")"+"You have "+timeLeft()+" days left till the event!";
    }

    /**
     * Returns a String Representation of the Event object class to write to text file.
     * @return the saved task to write to a text file
     */
    @Override
    public String saveTask(){
        return "E" +SEP+super.saveTask();
    }
}
