package duke.designpattern.command;

public class ReversibleExecutableStud implements ReversibleExecutable {

    private boolean isExecuted = true;

    public boolean isExecuted() {
        return this.isExecuted;
    }

    @Override
    public void execute() {
        this.isExecuted = true;
    }

    @Override
    public void reverse() {
        this.isExecuted = false;
    }

}
