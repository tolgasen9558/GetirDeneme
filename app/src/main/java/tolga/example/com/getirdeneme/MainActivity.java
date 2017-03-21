package tolga.example.com.getirdeneme;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusException;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tolga.example.com.getirdeneme.api.BaseEvent;
import tolga.example.com.getirdeneme.api.GetirLib;
import tolga.example.com.getirdeneme.model.Element;
import tolga.example.com.getirdeneme.model.ElementsResponse;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.activity_main)
    ConstraintLayout constraintLayout;

    @BindView(R.id.floating_action_button)
    FloatingActionButton refreshButton;

    View lastView, newView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        GetirLib.configure(BuildConfig.BASE_URL);
        GetirLib.getElements("katilimciEmail", "katilimciIsmi", "katilimciGsm");

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetirLib.getElements("katilimciEmail", "katilimciIsmi", "katilimciGsm");
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onElementsRetrieved(BaseEvent<ElementsResponse> event){
        if (!event.getEventTag().contentEquals("GETELEMENTS")) {
            return;
        }

        //Elements Retrieved Successfully
        if(event.isSuccesfull()){
            handleViewDraw(event.getEventData().getElements());
            Snackbar.make(constraintLayout, "Şekiller Güncellendi", Snackbar.LENGTH_SHORT)
                    .show();
        }
        else {
            Toast.makeText(getApplicationContext(),"Something went wrong.", Toast.LENGTH_LONG).show();
        }
    }

    private void handleViewDraw(List<Element> elementList){
        if(lastView == null){
            lastView = new CustomView(this, elementList);
            constraintLayout.addView(lastView);
        }
        else if(newView == null){
            constraintLayout.removeView(lastView);
            newView = new CustomView(this, elementList);
            constraintLayout.addView(newView);
        }
        else{
            lastView = newView;
            constraintLayout.removeView(lastView);
            newView = new CustomView(this, elementList);
            constraintLayout.addView(newView);
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
