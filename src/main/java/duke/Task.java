package duke;

public abstract class Task {
    private static final String NONE_TAG = "#NONE";
    protected String description;
    protected boolean isDone;
    protected static int total = 0;
    protected String tag;

    public Task(String name, boolean isCompleted) {
        this.description = name;
        this.isDone = isCompleted;
        this.total ++;
        this.tag = NONE_TAG;
    }


    public static int remainingTasks() {
        return Task.total;
    }

    public String toString() {
        
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setDone() {
        if (!this.isDone) {
            this.total--;
        }
        this.isDone = true;
    }

    public String getSymbol() {
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    public abstract String getTaskSymbol();
    
    public abstract String storeFormat();

    public static void decrementTask() {
        Task.total --;
    }
    
    public boolean contains(String keyword) {
        return description.contains(keyword);
    }
    @Override
    public boolean equals(Object other) {
        if (this.getClass().equals(other.getClass())) {
            Task object2 = (Task)other;
            return this.description.equals(object2.description) && this.isDone==object2.isDone();
        }
        return false;
    }
    
    public void addTag(String tag) {
        this.tag = "#" + tag;
    }
    
    public String getTag() {
        return this.tag;
    }
    
    public void reTag(String tag) {
        this.tag = tag;
    }
    
    public String displayTag() {
        return tag.equals(NONE_TAG)? "":tag;
    }
}
