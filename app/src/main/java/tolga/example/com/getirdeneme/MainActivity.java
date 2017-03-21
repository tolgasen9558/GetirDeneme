package tolga.example.com.getirdeneme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusException;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import butterknife.BindView;
import butterknife.ButterKnife;
import tolga.example.com.getirdeneme.api.BaseEvent;
import tolga.example.com.getirdeneme.api.GetirLib;
import tolga.example.com.getirdeneme.model.ElementsResponse;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.newIDofTextview)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        GetirLib.configure(BuildConfig.BASE_URL);
        GetirLib.getElements("katilimciEmail", "katilimciIsmi", "katilimciGsm");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onElementsRetrieved(BaseEvent<ElementsResponse> event){
        if (!event.getEventTag().contentEquals("GETELEMENTS")) {
            return;
        }

        //Elements Retrieved Successfully
        if(event.isSuccesfull()){
            textView.setText(event.getEventData().getElements().get(0).getType());
        }
        else {
            Toast.makeText(getApplicationContext(),"Something went wrong.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)) {
            try {
                EventBus.getDefault().register(this);
            } catch (EventBusException exc) {
                Log.w("EVENTBUS", "No subscriber for " + this.getClass());
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
}
