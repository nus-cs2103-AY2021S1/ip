public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String printTask() {
        StringBuilder sb = new StringBuilder();
        sb.append("[T]");
        sb.append(super.printTask());
        return sb.toString();
    }

}