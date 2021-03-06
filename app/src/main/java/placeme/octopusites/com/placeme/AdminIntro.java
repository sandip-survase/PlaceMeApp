package placeme.octopusites.com.placeme;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import placeme.octopusites.com.placeme.modal.AdminIntroModal;

import static placeme.octopusites.com.placeme.AES4all.Decrypt;
import static placeme.octopusites.com.placeme.AES4all.OtoString;
import static placeme.octopusites.com.placeme.AdminActivity.IsSubadmin;

public class AdminIntro extends AppCompatActivity {

    int errorflagfirstname=0;
    String digest1,digest2;
    AutoCompleteTextView citystaecountry;
    ArrayList<String> listAll=new ArrayList<String>();

    TextInputEditText fname,lname,role,email,inst;
    JSONObject json;
    JSONParser jParser = new JSONParser();
    TextInputLayout fnameTextInputLayout, lnameTextInputLayout, roleinputlayout,instinputlayout, emailinputlayout, citystaecountryinputlayout;
    int countrycount=0,statecount=0,citycount=0;
    String firstname="",lastname="",instname="",CityStateCountry="";
    String oldCountry="",oldState="",oldCity="";
    String countries[],states[],cities[];
    Spinner country,state,city;
    List<String> countrieslist = new ArrayList<String>();
    List<String> stateslist = new ArrayList<String>();
    List<String> citieslist = new ArrayList<String>();
    String selectedCountry="",selectedState="",selectedCity="";
    String encUsername,encRole,encFname,encLname,encCountry,encState,encCity,encInst;
    //ssss
    AdminData a = new AdminData();
    int edittedFlag=0,isCountrySet=0,isStateSet=0,isCitySet=0;
    AdminIntroModal obj;
    String strobj="",encobj="";
    private String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_intro);

        digest1=MySharedPreferencesManager.getDigest1(this);
        digest2=MySharedPreferencesManager.getDigest2(this);

        buildCityStateCountryList();

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Personal Info");
        ab.setDisplayHomeAsUpEnabled(true);

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.while_color), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        TextView loctxt=(TextView)findViewById(R.id.loctxt);
        loctxt.setTypeface(Z.getBold(this));
//

        fname=(TextInputEditText)findViewById(R.id.fname);
        lname=(TextInputEditText)findViewById(R.id.lname);
        role=(TextInputEditText)findViewById(R.id.role);
        email=(TextInputEditText)findViewById(R.id.email);
        inst=(TextInputEditText)findViewById(R.id.inst);

        citystaecountry=(AutoCompleteTextView)findViewById(R.id.citystaecountry);

        fnameTextInputLayout = (TextInputLayout) findViewById(R.id.fnameTextInputLayout);
        lnameTextInputLayout = (TextInputLayout) findViewById(R.id.lnameTextInputLayout);
        roleinputlayout = (TextInputLayout) findViewById(R.id.roleinputlayout);
        emailinputlayout = (TextInputLayout) findViewById(R.id.emailinputlayout);
        instinputlayout = (TextInputLayout) findViewById(R.id.instinputlayout);
        citystaecountryinputlayout = (TextInputLayout) findViewById(R.id.citystaecountryinputlayout);

        fname.setTypeface(Z.getBold(this));
        lname.setTypeface(Z.getBold(this));
        role.setTypeface(Z.getBold(this));
        email.setTypeface(Z.getBold(this));
        inst.setTypeface(Z.getBold(this));
        citystaecountry.setTypeface(Z.getBold(this));

        fnameTextInputLayout.setTypeface(Z.getLight(this));
        lnameTextInputLayout.setTypeface(Z.getLight(this));
        roleinputlayout.setTypeface(Z.getLight(this));
        emailinputlayout.setTypeface(Z.getLight(this));
        instinputlayout.setTypeface(Z.getLight(this));
        citystaecountryinputlayout.setTypeface(Z.getLight(this));

        firstname=a.getFname();
        lastname=a.getLname();
        instname = a.getInstitute();
        selectedCountry = a.getCountry();
        selectedState = a.getState();
        selectedCity = a.getCity();

        if(firstname!=null)
            fname.setText(firstname);
        else
            firstname="";

        if(lastname!=null)
            lname.setText(lastname);
        else
            lastname="";

        if (instname != null)
            inst.setText(instname);
        else
            instname="";



        try{
            role.setText(MySharedPreferencesManager.getRole(this));
            email.setText(Decrypt(MySharedPreferencesManager.getUsername(this),digest1,digest2));

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        encUsername=MySharedPreferencesManager.getUsername(this);
        if(selectedCountry!=null && selectedState!=null && selectedCity!=null)
        {
            if(!selectedCountry.equals("") && !selectedState.equals("") && !selectedCity.equals("")) {
                CityStateCountry = selectedCity + " , " + selectedState + " , " + selectedCountry;
                citystaecountry.setText(CityStateCountry);
            }
            else
                citystaecountry.setText("");

        }
        else
            citystaecountry.setText("");



        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,listAll);
        citystaecountry.setAdapter(adapter);

        citystaecountry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                citystaecountryinputlayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        fname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                fnameTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        lname.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lnameTextInputLayout.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }


        });
        inst.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                instinputlayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        if(IsSubadmin)
        {
            inst.setFocusable(false);
        }

        edittedFlag=0;

    }

    private void buildCityStateCountryList() {

        new Thread(new Runnable() {
            public void run() {
                android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

                try {
                    JSONObject jsonObject = new JSONObject(getJson());
                    JSONArray array = jsonObject.getJSONArray("array");
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object = array.getJSONObject(i);
                        String city = object.getString("city");
                        String state = object.getString("state");
                        String country = object.getString("country");

                        listAll.add(city + " , " + state + " , " + country);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public String getJson()
    {
        String json=null;
        try
        {
            InputStream is = getAssets().open("citystatecountrydb/array.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
            return json;
        }
        return json;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.savemenu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:

                if (edittedFlag == 1) {
                    validateandSave();
                }
                else {
                    onBackPressed();
                }
                //Toast.makeText(getBaseContext(),"clicked", Toast.LENGTH_SHORT).show();
                break;

            case android.R.id.home:
                onBackPressed();
                return(true);
        }

        return(super.onOptionsItemSelected(item));
    }
    @Override
    public void onBackPressed() {
        if ( edittedFlag == 1) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder
                    .setMessage("Do you want to discard changes ?")
                    .setCancelable(false)
                    .setPositiveButton("Discard",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    AdminIntro.super.onBackPressed();
                                }
                            })

                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            dialog.cancel();
                        }
                    });

            final AlertDialog alertDialog = alertDialogBuilder.create();

            alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialogInterface) {
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#00bcd4"));
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#00bcd4"));
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(AdminIntro.this));
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(AdminIntro.this));
                }
            });

            alertDialog.show();

        }
        else
            AdminIntro.super.onBackPressed();

    }
    void validateandSave()
    {

        int errorflag1=0,errorflag2=0,errorflag3=0,errorflag4=0, errorflag5=0;

        firstname = fname.getText().toString();
        lastname = lname.getText().toString();
        instname = inst.getText().toString();
        CityStateCountry = citystaecountry.getText().toString();

        if (firstname.length() < 2) {
            fnameTextInputLayout.setError("Kindly enter valid first name");
            errorflag1 = 1;
        } else {
            fnameTextInputLayout.setError(null);
            if (lastname.length() < 2) {
                lnameTextInputLayout.setError("Kindly enter valid last name");
                errorflag2 = 1;
            } else {
                lnameTextInputLayout.setError(null);
                if (instname.length() < 2) {
                    instinputlayout.setError("Kindly enter valid institute name");
                    errorflag3 = 1;
                } else {
                    instinputlayout.setError(null);

                    if(CityStateCountry.length()<2)
                    {
                        citystaecountryinputlayout.setError("Please select your city");
                        errorflag4=1;
                    }
                    else
                    {
                        citystaecountryinputlayout.setError(null);
                        String[] parts = CityStateCountry.split(" , ");
                        if(parts.length==3) {
                            selectedCity = parts[0];
                            selectedState = parts[1];
                            selectedCountry = parts[2];
                        }
                    }

//

                }
            }

        }


        if (errorflag4 == 0 && errorflag2 == 0 && errorflag3 == 0 && errorflag1 == 0  ) {
            try {
//
                String mname = a.getMname();
                String phone = a.getPhone();

                obj = new AdminIntroModal(firstname,mname,lastname,selectedCountry,selectedState,selectedCity,phone,instname);
                try{
                    strobj =OtoString(obj,digest1,digest2);

                }
                catch (Exception e){
                }


                new SaveData().execute();

            } catch (Exception e) {

            }
        }

    }


    class SaveData extends AsyncTask<String, String, String> {

        protected String doInBackground(String... param) {


            String r=null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u",encUsername));    //0
            params.add(new BasicNameValuePair("obj",strobj));       //1
            json = jParser.makeHttpRequest(Z.url_SaveAdminIntro, "GET", params);
            try {
                r = json.getString("info");

            }catch (Exception e){e.printStackTrace();}
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.equals("success"))
            {
                MySharedPreferencesManager.save(AdminIntro.this,"institute",instname);
                Toast.makeText(AdminIntro.this,"Successfully Saved..!",Toast.LENGTH_SHORT).show();

                if(edittedFlag==1){
                    setResult(111);
                }
                AdminIntro.super.onBackPressed();
            } else
                Toast.makeText(AdminIntro.this, "Try again !", Toast.LENGTH_SHORT).show();
        }
    }



}
