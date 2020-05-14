package org.example.investmentfunds.result;

public class NonEqualArgumentsSizeException extends RuntimeException {

    public NonEqualArgumentsSizeException(String message) {
        super(message);
    }

    public NonEqualArgumentsSizeException(String message, Throwable cause) {
        super(message, cause);
    }
}
