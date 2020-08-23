package tasks;

public class ToDos extends Task {

    public ToDos(String work) {
        super(work);
    }

    public String toString(){
        return "[T]" + super.toString();
    }

    public boolean istodo(){
        return true;
    }
}
