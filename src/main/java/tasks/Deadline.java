package tasks;

import exceptions.DukeDateTimeException;
/**
 * The Event class contains the information that is entered by the user into the 
 * Duke Chatbot Command Line Interface.
 */
class Deadline extends TimedTask {
    /**
     * Constructor for Deadline Class without done status
     * To be used for creating new tasks by the end user
     * @param desc the description of the task
     * @param date the date on which the task is due 
     * @throws DukeDateTimeException if the fields for the date are not matching autocorrection cases
     */
    Deadline(String desc,String date) throws DukeDateTimeException {
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
    Deadline(String desc,String date,Boolean b) throws DukeDateTimeException {
        super(desc, date, b);
    }
    
    @Override
    public String toString(){
        return "[D]" + super.toString()+" (by: "+getDateby()+")"+ "You have "+timeLeft()+" days left till its due!";
    }
    /**
     * Returns a String Representation of the Deadline object class to write to text file.
     * @return the saved task to write to a text file
     */
    @Override
    public String saveTask() {
        return "D"+SEP+super.saveTask();
    }
}
