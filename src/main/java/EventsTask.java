package main.java;

public class EventsTask extends Task {
    protected String period;

    public EventsTask(String period, String task, TaskSymbol taskType){
        super(task, taskType);
        this.period = period;
    }

    @Override
    public String toString() {

        return super.toString() + "(at: " + period + ")";
    }

}
