public class DukeException extends Exception {
	String msg;
	public DukeException(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
}