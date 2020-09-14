package junimo.ui;

public class Response {
    private String messsage;
    private Boolean isErrorMessage;

    public Response(String messsage, Boolean isErrorMessage) {
        this.messsage = messsage;
        this.isErrorMessage = isErrorMessage;
    }

    public String getMesssage() {
        return this.messsage;
    }

    public Boolean getIsErrorMessage() {
        return this.isErrorMessage;
    }
}