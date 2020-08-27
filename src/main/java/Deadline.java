//@@author {FooJingYi}-reused
//Reused from https://nus-cs2103-ay2021s1.github.io/website/schedule/week2/project.html with minor modifications

public class Deadline extends Task {

    protected String dl; //deadline given

    public Deadline(String taskName, String dl) {
        super(taskName);
        this.dl = dl;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " by: " + dl;
    }
}