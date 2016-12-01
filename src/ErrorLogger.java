public class ErrorLogger {

    private final ErrorLogger _errorLogger = new ErrorLogger();

    private ErrorLogger() {

    }

    public ErrorLogger getInstance(){
        return _errorLogger;
    }

    //write a message and then error out
    public void die(String errorMessage) {
        System.err.println(errorMessage);
        throw new RuntimeException();
    }
}
