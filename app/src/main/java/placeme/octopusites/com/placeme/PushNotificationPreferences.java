package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.TextView;

import org.json.JSONObject;

public class PushNotificationPreferences extends AppCompatActivity {

    String role;
    SwitchCompat switch1,switch2,switch3,switch4,switch5;
    String preference1="yes",preference2="yes",preference3="yes",preference4="yes",preference5="yes";
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;
    public static final String Username = "nameKey";
    String username;
    String digest1,digest2;
    JSONParser jParser = new JSONParser();
    JSONObject json;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_notification_preferences);

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Push Notifications");
        ab.setDisplayHomeAsUpEnabled(true);

        switch1=(SwitchCompat)findViewById(R.id.switch1);
        switch2=(SwitchCompat)findViewById(R.id.switch2);
        switch3=(SwitchCompat)findViewById(R.id.switch3);
        switch4=(SwitchCompat)findViewById(R.id.switch4);
        switch5=(SwitchCompat)findViewById(R.id.switch5);

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    preference1="yes";
                else
                    preference1="no";
            }
        });
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    preference2="yes";
                else
                    preference2="no";
            }
        });
        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    preference3="yes";
                else
                    preference3="no";
            }
        });
        switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    preference4="yes";
                else
                    preference4="no";
            }
        });
        switch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    preference5="yes";
                else
                    preference5="no";
            }
        });

        TextView pushtxt=(TextView)findViewById(R.id.pushtxt);
        TextView placeinsttxt=(TextView)findViewById(R.id.placeinsttxt);
        TextView placeplacemetxt=(TextView)findViewById(R.id.placeplacemetxt);
        TextView notiftxt=(TextView)findViewById(R.id.notiftxt);
        TextView notifplacemetxt=(TextView)findViewById(R.id.notifplacemetxt);
        TextView blogtxt=(TextView)findViewById(R.id.blogtxt);

        pushtxt.setTypeface(Z.getLight(this));
        placeinsttxt.setTypeface(Z.getBold(this));
        placeplacemetxt.setTypeface(Z.getBold(this));
        notiftxt.setTypeface(Z.getBold(this));
        notifplacemetxt.setTypeface(Z.getBold(this));
        blogtxt.setTypeface(Z.getBold(this));



        sharedpreferences =getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        username=sharedpreferences.getString(Username,null);
        preference1=sharedpreferences.getString("pref1",null);
        preference2=sharedpreferences.getString("pref2",null);
        preference3=sharedpreferences.getString("pref3",null);
        preference4=sharedpreferences.getString("pref4",null);
        preference5=sharedpreferences.getString("pref5",null);


        role=sharedpreferences.getString("role",null);

        if(role.equals("hr"))
        {
            placeinsttxt.setText("Incoming Placement Registrations");
            placeplacemetxt.setText("Registered Students List");
            notiftxt.setText("Status of Rounds Conducted");
        }
        if(preference1!=null)
            if(preference1.equals("yes"))
            switch1.setChecked(true);
        else
            switch1.setChecked(false);
        if(preference2!=null)
        if(preference2.equals("yes"))
            switch2.setChecked(true);
        else
            switch2.setChecked(false);
        if(preference3!=null)
        if(preference3.equals("yes"))
            switch3.setChecked(true);
        else
            switch3.setChecked(false);
        if(preference4!=null)
        if(preference4.equals("yes"))
            switch4.setChecked(true);
        else
            switch4.setChecked(false);
        if(preference5!=null)
        if(preference5.equals("yes"))
            switch5.setChecked(true);
        else
            switch5.setChecked(false);
    }
    void savePreferences()
    {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("pref1", preference1);
        editor.putString("pref2", preference2);
        editor.putString("pref3", preference3);
        editor.putString("pref4", preference4);
        editor.putString("pref5", preference5);

        editor.commit();


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                onBackPressed();

                return(true);
        }

        return(super.onOptionsItemSelected(item));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        savePreferences();
    }
}
