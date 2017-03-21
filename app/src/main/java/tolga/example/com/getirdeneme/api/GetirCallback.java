package tolga.example.com.getirdeneme.api;




    /*Blueprint Class for a request with returning 3-Ways:
     *1.Process have been done without any errors       ->  onSuccess()
     *2.Problem with the server, returns the error code ->  onError()
     *3.No response from server                         ->  onFail()    */

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetirCallback<T> implements Callback<T> {

    private String name;
    private String errorMessage = null;
    public BaseEvent<T> event;

    GetirCallback(String name, BaseEvent<T> event) {
        this.name = name;
        this.event = event;
        event.setEventTag(name);
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (parseResponseStatus(call, response, this.name)) {
            this.event.setSuccesfull(true);
            this.event.setEventData(response.body());
            this.event.setResponse(response);
            onSuccess(response.body());
        } else {
            this.event.setSuccesfull(false);
            this.event.setResponse(response);
            onError(response);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable throwable) {
        if (throwable != null && throwable.getMessage() != null) {
            Log.e(this.name, throwable.getMessage());
        }
        this.event.setSuccesfull(false);
        this.event.setThrowable(throwable);
        onError(null);
    }

    public void onSuccess(T result) {
        if (result == null) {
            return;
        }
        EventBus.getDefault().post(this.event);
    }

    public void onError(Response res) {
        EventBus.getDefault().post(this.event);
    }

    //Log the message and return true if the response is successful, return false if not
    private boolean parseResponseStatus(Call call, Response resp, String endpoint) {
       // Log.w(endpoint, resp.message());
        return resp.isSuccessful();
    }
}
