public class ViscountDateTimeParseException extends ViscountException {
    private static final String ERROR_MESSAGE = "Alas, I do not understand. Please format your %s\n"
            + "in this format: dd-MM-yyyy HHmm\n"
            + "like the following example: 23-08-2020 1300\n"
            + "The time is optional and the default is 0000.";
    
    private String dateTimeType;
    
    public ViscountDateTimeParseException(String dateTimeType) {
        super();
        this.dateTimeType = dateTimeType;
    }
    
    @Override
    public String toString() {
        return String.format(ViscountDateTimeParseException.ERROR_MESSAGE, dateTimeType);
    }
}
