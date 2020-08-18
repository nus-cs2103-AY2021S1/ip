package service;

public class DukeResponse {
    private String message;

    public DukeResponse(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
