public class ToDo extends Task {
	public ToDo(String taskContent){
		super(taskContent);
	}

	@Override
	public String getType() {
		return "T";
	}
	@Override
	public String toString() {
		return "[T]" + super.toString();
	}
}