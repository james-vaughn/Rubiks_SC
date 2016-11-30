public class ErrorLogger {

    private final ErrorLogger _errorLogger = new ErrorLogger();

    private ErrorLogger() {

    }

    public ErrorLogger getInstance(){
        return _errorLogger;
    }


}
