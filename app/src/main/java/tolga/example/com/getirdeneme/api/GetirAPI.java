package tolga.example.com.getirdeneme.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import tolga.example.com.getirdeneme.model.ElementsResponse;

public interface GetirAPI {

    //Get Elements
    @FormUrlEncoded
    @POST("getElements")
    Call<ElementsResponse> getElements(@Field("email") String participantEMail,
                                       @Field("name")  String participantName,
                                       @Field("gsm")   String participantGSM);

}
