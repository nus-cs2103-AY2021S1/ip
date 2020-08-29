package tasks;

/**
 * ToDo is a Implementation of the Task Class with no additional fields
 * This is the class that stores todo tasks in the Duke program 
 */
class ToDo extends Task {
    /**
     * Constructor for the ToDo Class for use when creating new tasks
     * by end user.
     * @param desc 
     */
    ToDo(String desc){
        super(desc,false);
    }

    /**
     * Constructor for the ToDo Class for use when populating the list of tasks 
     * recorded by the save text file
     * @param desc
     * @param done
     */
    ToDo(String desc,Boolean done){
        super(desc,done);
    }
    @Override
    public String toString(){
        return "[T]"+super.toString();
    }

    /**
     * Returns a String Representation of the ToDo object class to write to text file.
     * @return the saved task to write to a text file
     */
    public String saveTask(){
        return "T" +SEP+super.saveTask();
    }
}
