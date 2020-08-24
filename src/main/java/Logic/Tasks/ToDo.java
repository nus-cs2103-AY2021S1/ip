package Logic.Tasks;

public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toData(){
        return "D|" + super.toData();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
