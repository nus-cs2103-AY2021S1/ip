public class ToDo extends Task {
	public ToDo(String taskContent){
		super(taskContent);
	}

	@Override
	public String toString() {
		return "[T]" + super.toString();
	}
}