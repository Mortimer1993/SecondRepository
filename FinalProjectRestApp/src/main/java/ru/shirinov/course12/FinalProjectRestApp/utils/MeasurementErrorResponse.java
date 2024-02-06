package ru.shirinov.course12.FinalProjectRestApp.utils;

public class MeasurementErrorResponse {
    private String errorMessage;
    private long timeStamp;

    public MeasurementErrorResponse(String errorMessage, long timeStamp) {
        this.errorMessage = errorMessage;
        this.timeStamp = timeStamp;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
