package duke.Tasks;

import duke.Exceptions.DukeException;

public class Task {

    private String name;
    protected boolean isDone;

    public Task(String name){
        this.name = name;
        isDone = false;
    }

    public boolean isResult(String description) throws DukeException {
        try {
            return this.name.contains(description);
        } catch (NullPointerException e) {
            throw new DukeException("    Please avoid using null as a search keyword.");
        }
    }

    public void completed(){
        this.isDone = true;
    }

    public String toData(){
        return (isDone ? "1|" : "0|") + name + "|";
    }

    @Override
    public String toString(){
        return (isDone ? "[\u2705]" : "[\u2718]") + " " + name;
    }
}
