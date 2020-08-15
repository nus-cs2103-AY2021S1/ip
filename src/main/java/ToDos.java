public class ToDos extends task{

    public ToDos(String work) {
        super(work);
    }

    public String toString(){
        return "[T]" + super.toString();
    }
}
