package duke;

class DukeException extends Exception {

    private String errorMsg;

    DukeException(String msg) {
        this.errorMsg = msg;
    }

    @Override
    public String toString() {
        return errorMsg;
    }
}
