package tolga.example.com.getirdeneme.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tolga.example.com.getirdeneme.model.ElementsResponse;


public class GetirLib {

    private static GetirAPI getirAPI;

    public static void configure(String BASE_URL) {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request;
                Request.Builder builder = original.newBuilder()
                        .header("Content-Type", "application/json");

                request = builder
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        }).build();

        Retrofit.Builder retrofit = new Retrofit.Builder()
                .client(client);

        getirAPI = retrofit.addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build().create(GetirAPI.class);
    }

    public static void getElements(String eMail, String name, String gsm){
        getirAPI.getElements(eMail, name, gsm)
                .enqueue(new GetirCallback<>("GETELEMENTS", new BaseEvent<ElementsResponse>()));
    }

}

