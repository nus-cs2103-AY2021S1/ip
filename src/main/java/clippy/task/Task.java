package clippy.task;

import clippy.exception.UpdateToDoTimeException;

public abstract class Task {
    protected String desc;
    protected boolean isDone;
    protected TaskType taskType;
    private int index;
    
    private final String SYMBOL_TICK = "\u2713";
    private final String SYMBOL_CROSS = "\u2718";

    protected Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }

    public int getTaskIndexInList() {
        return index;
    }

    public String getStatusIcon() {
        return (isDone ? SYMBOL_TICK : SYMBOL_CROSS); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }
    
    public void updateDescription(String newDescription) {
        this.desc = newDescription;
    }
    
    public abstract void updateTime(String newTime) throws UpdateToDoTimeException;
    
    public abstract String generateSaveFileData();
    
    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + desc;
    }
}
