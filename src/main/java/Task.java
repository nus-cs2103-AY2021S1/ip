import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Task {
    protected String name;
    protected boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void markDone(){
        this.done = true;
    }

    public boolean getDone(){
        return this.done;
    }

    public String getName(){
        return this.name;
    }

    public String getParsedData(){
        return String.valueOf(this.done) + "/" + this.name;
    }

    public String toString(){
        String symbol = done ? "\u2713" : "\u2718";
        return "[" + symbol + "] " + name;
    }
}

class ToDo extends Task {
    public ToDo(String name){
        super(name);
    }

    @Override
    public String getParsedData(){
        return "T" + "/" + String.valueOf(super.done) + "/" + super.name;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    final static private DateTimeFormatter MYDATEFORMATTER =
            DateTimeFormatter.ofPattern("LLL dd uuuu");
    protected String by;
    protected String byFormat;
    protected LocalDate date;


    public Deadline(String name, String by) {
        super(name);
        this.by = by;
        this.date = LocalDate.parse(by);
        this.byFormat = this.date.format(MYDATEFORMATTER);
    }

    @Override
    public String getParsedData(){
        return "D" + "/" + String.valueOf(super.done) + "/" + super.name + "/" + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.byFormat + ")";
    }
}

class Event extends Task {
    protected String at;

    public Event (String name, String at) {
        super(name);
        this.at = at;
    }

    @Override
    public String getParsedData(){
        return "E" + "/" + String.valueOf(super.done) + "/" + super.name + "/" + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}