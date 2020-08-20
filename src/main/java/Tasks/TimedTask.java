package Tasks;

import Tasks.Task;

abstract class TimedTask extends Task {
    protected final String dateby;
    protected TimedTask(String desc, String date) {
        super(desc, false);
        this.dateby = date;
    }

    /**
     * Get the dateby for the set task
     * @return dateby for the registered task
     */
    public String getDateby(){
        return dateby;
    }
}
