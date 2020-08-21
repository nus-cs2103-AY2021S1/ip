package main.java;

public class EventsTask extends Task {
    protected String period;
    protected String symbol = "[E]";

    public EventsTask(String period, String task){
        super(task);
        this.period = period;
    }

    @Override
    public String toString() {

        return symbol + super.toString() + "(at: " + period + ")";
    }

}
