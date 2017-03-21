package tolga.example.com.getirdeneme.api;

import retrofit2.Response;

public class BaseEvent<T> {

    private T eventData;
    private Response response;
    private boolean isSuccesfull;
    private Throwable throwable;
    private String eventTag;

    public BaseEvent(){

    }

    public BaseEvent(T data, boolean isSuccesfull) {
        this.eventData = data;
        this.isSuccesfull = isSuccesfull;
    }

    public BaseEvent(Response error, boolean isSuccesfull) {
        this.response = error;
        this.isSuccesfull = isSuccesfull;
    }


    public T getEventData() {
        return eventData;
    }

    public void setEventData(T eventData) {
        this.eventData = eventData;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public boolean isSuccesfull() {
        return isSuccesfull;
    }

    public void setSuccesfull(boolean succesfull) {
        isSuccesfull = succesfull;
    }

    public String getEventTag() {
        return eventTag;
    }

    public void setEventTag(String eventTag) {
        this.eventTag = eventTag;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
