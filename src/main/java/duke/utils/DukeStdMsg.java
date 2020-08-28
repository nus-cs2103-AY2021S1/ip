package duke.utils;

public enum DukeStdMsg {
    WELCOME("Hello, my name is \n" +
            " ____        _        \n" +
            "|  _ \\ _   _| | _____ \n" +
            "| | | | | | | |/ / _ \\\n" +
            "| |_| | |_| |   <  __/\n" +
            "|____/ \\__,_|_|\\_\\___|\n" +
            "How may I help you?"),
    LOAD_FROM_DISK("Duke has loaded from a previously saved file!"),
    FRESH_START("No previously saved file found in ./data/duke.txt. Duke will start from a clean taskList!"),
    EXIT("Saved successfully!\nBye bye! Hope to see you again soon!");

    private final String msg;

    DukeStdMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
