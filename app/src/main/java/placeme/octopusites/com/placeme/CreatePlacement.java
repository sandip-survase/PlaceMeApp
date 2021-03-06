package placeme.octopusites.com.placeme;

import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ConnectionQuality;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.AnalyticsListener;
import com.androidnetworking.interfaces.ConnectionQualityChangeListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import mabbas007.tagsedittext.TagsEditText;

import static placeme.octopusites.com.placeme.AES4all.Decrypt;
import static placeme.octopusites.com.placeme.AES4all.Encrypt;
import static placeme.octopusites.com.placeme.AES4all.OtoString;

public class CreatePlacement extends AppCompatActivity {


    String role;
    boolean showPop = false;
    ViewPagerAdapter adapter;
    CheckBox CheckBoxstudent, CheckBoxsAlumni;
    int forstudflag = 0, forallumflag = 0;
    int edittedFlag = 0, containsall = 0;
    RelativeLayout allumiselector;
    ArrayAdapter<String> dataAdapter;
    ArrayList<String> TagCreateList = new ArrayList<>();
    int errorflag = 0;
    String srole = "", instname = "";
    JSONObject json;
    JSONParser jParser = new JSONParser();
    //PARAMSDATA
    String encUsername = "", encforwhom = "", encRole = "";
    String forwhom = "";
    String paramcompanyname = "", cpackage = "", post = "", selected = "", vacancies = "", lastdateofrr = "", dateofarrival = "", bond = "", apti = "", techtest = "",
            groupdisc = "", techinterview = "", Hrinterview = "", xcriteria = "", xiicriteria = "", ugcriteria = "", pgcriteria = "";
    private Toolbar toolbar;
    final List<String> yearList = new ArrayList<String>();
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TagsEditText batchesTags;
    private String digest1, digest2;
    private String TAG = "CreatePlacement";

    //check
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_placement);


        try {
            OkHttpUtil.init(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        OkHttpUtil.getClient();
//        AndroidNetworking.initialize(getApplicationContext());
        AndroidNetworking.initialize(getApplicationContext(), OkHttpUtil.getClient());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPurgeable = true;
        AndroidNetworking.setBitmapDecodeOptions(options);
        AndroidNetworking.enableLogging();
        AndroidNetworking.setConnectionQualityChangeListener(new ConnectionQualityChangeListener() {
            @Override
            public void onChange(ConnectionQuality currentConnectionQuality, int currentBandwidth) {
                Log.d(TAG, "onChange: currentConnectionQuality : " + currentConnectionQuality + " currentBandwidth : " + currentBandwidth);
            }
        });

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);

        toolbar = (Toolbar) findViewById(R.id.placementtoolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Create Placement");


        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.while_color), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        role = MySharedPreferencesManager.getRole(this);


        viewPager = (ViewPager) findViewById(R.id.placementviewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.placementtabs);
        tabLayout.setupWithViewPager(viewPager);


//        TextView createnotitxt = (TextView) findViewById(R.id.createnotitxt);
        TextView createnotinotitxt = (TextView) findViewById(R.id.createnotinotitxt);
//        createnotitxt.setTypeface(Z.getBold(this));
        createnotinotitxt.setTypeface(Z.getLight(this));

        CheckBoxstudent = (CheckBox) findViewById(R.id.CheckBoxstudent);
        CheckBoxsAlumni = (CheckBox) findViewById(R.id.CheckBoxsAlumni);
        CheckBoxstudent.setTypeface(Z.getBold(this));
        CheckBoxsAlumni.setTypeface(Z.getBold(this));

        allumiselector = (RelativeLayout) findViewById(R.id.allumiselector);
        batchesTags = (TagsEditText) findViewById(R.id.batchesTags);
        batchesTags.setHint("Enter the Batches");
        batchesTags.setFocusable(false);

        yearList.add("ALL");
        Calendar currentCalendar = Calendar.getInstance();
        for (int i = currentCalendar.get(Calendar.YEAR) - 1; i >= 2000; i--)
            yearList.add("" + i);


//        dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, getResources().getStringArray(R.array.fruits)) {
        dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, yearList) {
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                Typeface custom_font3 = Typeface.createFromAsset(getAssets(), "fonts/abz.ttf");
                tv.setTypeface(custom_font3);

                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.parseColor("#eeeeee"));
                }
                return view;
            }
        };


        Log.d("Shardpreff", "encUsername: " + encUsername);

        srole = MySharedPreferencesManager.getRole(this);
        instname = MySharedPreferencesManager.getInstitute(this);
        encUsername = MySharedPreferencesManager.getUsername(this);
        if (srole.equals("hr")) {
            createnotinotitxt.setVisibility(View.GONE);
            CheckBoxstudent = (CheckBox) findViewById(R.id.CheckBoxstudent);
            CheckBoxsAlumni = (CheckBox) findViewById(R.id.CheckBoxsAlumni);
            CheckBoxstudent.setVisibility(View.GONE);
            CheckBoxsAlumni.setVisibility(View.GONE);
        }

        CheckBoxstudent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    forstudflag = 1;
                    edittedFlag = 1;
                } else {
                    forstudflag = 0;
                    edittedFlag = 1;
                }
            }
        });


        CheckBoxsAlumni.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    forallumflag = 1;
                    showPop = false;
                    allumiselector.setVisibility(View.VISIBLE);
//                    batches.setVisibility(View.VISIBLE);
//                    yearspinner.setVisibility(View.VISIBLE);
//                    batches.setVisibility(View.VISIBLE);
                    batchesTags.setVisibility(View.VISIBLE);
                    edittedFlag = 1;
                } else {
                    forallumflag = 0;
                    batchesTags.setText("");
                    batchesTags.dismissDropDown();
                    allumiselector.setVisibility(View.GONE);
                    edittedFlag = 1;
                }
            }
        });

        batchesTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                batchesTags.setAdapter(dataAdapter);
                batchesTags.setThreshold(1);

                if (showPop == false) {

                    if (batchesTags.getText().toString().contains("ALL")) {
                        //dont popullate
                        Toast.makeText(CreatePlacement.this, "Notification will be sent to All batches", Toast.LENGTH_SHORT).show();
                    } else {
//                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getBaseContext(), R.layout.spinner_item, getResources().getStringArray(R.array.fruits)) {
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getBaseContext(), R.layout.spinner_item, yearList) {
                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                Typeface custom_font3 = Typeface.createFromAsset(getAssets(), "fonts/abz.ttf");
                                tv.setTypeface(custom_font3);

                                if (position == 0) {
                                    // Set the hint text color gray
                                    tv.setTextColor(Color.GRAY);
                                } else {
                                    tv.setTextColor(Color.parseColor("#eeeeee"));
                                }
                                return view;
                            }
                        };


                        batchesTags.setAdapter(dataAdapter);
                        batchesTags.showDropDown();


                    }
                }
            }
        });

        batchesTags.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
//                String temp = getResources().getStringArray(R.array.fruits)[position];
                String temp = yearList.get(position);
                temp = temp.trim();

                if (temp.contains("ALL")) {
                    TagCreateList.clear();
                    TagCreateList.add("ALL");
                    String[] TagCreateArray = new String[TagCreateList.size()];
                    TagCreateArray = TagCreateList.toArray(TagCreateArray);
                    batchesTags.setText("");
                    batchesTags.setTags(TagCreateArray);
                }
                if (TagCreateList.contains(temp)) ;
                {
                    int occurance = TagCreateList.indexOf(temp);
                    int Lastelement = TagCreateList.size() - 1;
                    Log.d("occurance", "onItemClick:" + occurance);
                    Log.d("Lastelement", "onItemClick:" + Lastelement);

                    if (occurance != TagCreateList.size() - 1) {
                        Log.d("deletethis", "onItemClick:");
                        TagCreateList.remove(TagCreateList.size() - 1);
                        String[] TagCreateArray = new String[TagCreateList.size()];
                        TagCreateArray = TagCreateList.toArray(TagCreateArray);
                        batchesTags.setTags(TagCreateArray);

                        Toast.makeText(CreatePlacement.this, "Batch " + temp + " is already present", Toast.LENGTH_SHORT).show();
                    }

                }
            }

        });

        batchesTags.setTagsListener(new TagsEditText.TagsEditListener() {
            @Override
            public void onTagsChanged(Collection<String> collection) {
                TagCreateList.clear();
                List<String> newList = new ArrayList<String>(batchesTags.getTags());
                TagCreateList.addAll(newList);
                Log.d("setTagsListener", "onEditingFinished: " + containsall);

                String temp = "";
                temp = batchesTags.getText().toString();
                Log.d("tag", "onTagsChanged: " + temp);
                if (temp.equals("")) {
                    batchesTags.dismissDropDown();
                    allumiselector.setVisibility(View.GONE);
                    CheckBoxsAlumni.setChecked(false);
                    showPop = true;
                }

            }

            @Override
            public void onEditingFinished() {


            }
        });
    }

    void validate() {
        try {


            errorflag = 0;


            if (srole.equals("hr")) {
//                forwhom = Encrypt("PLACEME", digest1, digest2);
                forwhom = "PLACEME()";
                Log.d("Forwhome", "validate: " + encforwhom);
            } else {
                Log.d(TAG, "from Admin: ");
                String selectedBatchesForWhome = android.text.TextUtils.join(",", TagCreateList);
                Log.d("Forwhome", "validate: " + selectedBatchesForWhome);

                Log.d(TAG, "forstudflag: "+forstudflag);
                Log.d(TAG, "forallumflag: "+forallumflag);

                if (forstudflag==0 && forallumflag==0) {
                    Log.d(TAG, "aat ala donhi 0");
                    errorflag = 1;
                    Toast.makeText(CreatePlacement.this, "Select Student Or Alumni to send This Placement ", Toast.LENGTH_SHORT).show();

                } else {
                    if (forstudflag == 1) {
                        //notification for Student
                        forwhom = MySharedPreferencesManager.getData(CreatePlacement.this,"ucode") + "(" + Decrypt(encUsername, digest1, digest2) + ",STUDENT";                  //for testing  purpose ADMIN IS sTUDENT
                        if (forallumflag == 1) {
                            //for Stud + alumni
                            forwhom = forwhom + "," + selectedBatchesForWhome + ")";
                            Log.d("forwhomeStringAppend", "onCreate: " + forwhom);
                        } else {
                            //only for Stud
                            forwhom = forwhom + ")";
                            Log.d("forwhomeStringAppend", "onCreate: " + forwhom);

                        }
                    } else {
                        //notification not for Student
                        if (forallumflag == 1) {
                            //for ALLUMNI
                            forwhom = MySharedPreferencesManager.getData(CreatePlacement.this,"ucode") + "(" + Decrypt(encUsername, digest1, digest2) + " ," + selectedBatchesForWhome + ")";
                            Log.d("forwhomeStringAppend", "onCreate: " + forwhom);

                        } else {
                            //NOTIFICATION FOR NONE
                            forwhom = forwhom + "(NONE)";
                            Log.d("forwhomeStringAppend", "onCreate: " + forwhom);

                        }
                    }
                    Log.d(TAG, "forwhom at Bottom" + forwhom);


                    PlacementCreateTab1 PlaceTab1 = (PlacementCreateTab1) adapter.getItem(0);
                    Log.d("gettingtabData", "size to send: "+PlaceTab1.TagCreateList.size());
                    for (int i = 0; i < PlaceTab1.TagCreateList.size(); i++) {
                        Log.d(TAG, "contains"+PlaceTab1.TagCreateList.get(i));
                    }




                    PlacementCreateTab2 PlaceTab2 = (PlacementCreateTab2) adapter.getItem(1);
                    PlacementCreateTab3 PlaceTab3 = (PlacementCreateTab3) adapter.getItem(2);
                    viewPager.setOffscreenPageLimit(3);

                    Boolean PlaceTab1_success = true;
                    Boolean PlaceTab2_success = true;
                    Boolean PlaceTab3_success = true;

                    PlaceTab1_success = PlaceTab1.Validate();


                    if (!PlaceTab1_success) {
                        viewPager.setCurrentItem(0);
                        PlaceTab1.Validate();
//            personalflag = 1;
                    } else {
                        PlaceTab2_success = PlaceTab2.validate();
                        if (!PlaceTab2_success) {
                            viewPager.setCurrentItem(1);
                            PlaceTab2.validate();
                        } else {
                            PlaceTab3_success = PlaceTab3.validate();

                            if (!PlaceTab3_success) {
                                viewPager.setCurrentItem(3);
                                PlaceTab3.validate();
                            } else {

//                            Toast.makeText(this, "Tab1 & Tab2 & tab3 OK", Toast.LENGTH_SHORT).show();
                                //call ENCRYPT ND create save method
                                if (errorflag == 0) {
                                    encrypt();
                                } else {
                                    Toast.makeText(CreatePlacement.this, "Try Again...", Toast.LENGTH_SHORT).show();

                                }
                            }
                        }
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    String CreateTags(String a) {
//        String SbatchesTags = batchesTags.getText().toString();
        if (a.contains("ALL")) {

            TagCreateList.clear();
            TagCreateList.add("ALL");
        } else {
            TagCreateList.add(a);
        }


        String[] TagCreateArray = new String[TagCreateList.size()];
        TagCreateArray = TagCreateList.toArray(TagCreateArray);
        batchesTags.setTags(TagCreateArray);

        return null;
    }

    void encrypt() {
        try {
            PlacementCreateTab1 PlaceTab1 = (PlacementCreateTab1) adapter.getItem(0);
            PlacementCreateTab2 PlaceTab2 = (PlacementCreateTab2) adapter.getItem(1);
            PlacementCreateTab3 PlaceTab3 = (PlacementCreateTab3) adapter.getItem(2);


            digest1 = MySharedPreferencesManager.getDigest1(this);
            digest2 = MySharedPreferencesManager.getDigest2(this);
            encRole = Encrypt(srole, digest1, digest2);
            encforwhom = Encrypt(forwhom, digest1, digest2);
            Log.d("gettingtabData", "encRole: " + encRole);
            Log.d("gettingtabData", "encforwhom: " + encforwhom);

//            String encparamcompanyname="";

//            byte[] paramcompanynameBytes = srole.getBytes("UTF-8");
//            byte[] paramcompanynameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, paramcompanynameBytes);
//            encparamcompanyname=new String(SimpleBase64Encoder.encode(paramcompanynameEncryptedBytes));

            paramcompanyname = PlaceTab1.companyname.getText().toString();
            cpackage = PlaceTab1.cpackage.getText().toString();
            post = PlaceTab1.post.getText().toString();
//            selected = PlaceTab1.selected.getText().toString();
            StringBuilder sb=new StringBuilder("");
            for (int i = 0; i <PlaceTab1.TagCreateList.size() ; i++) {
               sb.append(PlaceTab1.TagCreateList.get(i)+",");
                Log.d(TAG, "String Builder"+sb.toString());
            }
            selected=sb.toString();
//            selected =OtoString(PlaceTab1.TagCreateList.size(),MySharedPreferencesManager.getDigest1(CreatePlacement.this),MySharedPreferencesManager.getDigest2(CreatePlacement.this));
            Log.d(TAG, "size to send: "+PlaceTab1.TagCreateList.size());

            vacancies = PlaceTab1.vacancies.getText().toString();
            lastdateofrr = PlaceTab1.lastdateofrr.getText().toString();
            dateofarrival = PlaceTab1.dateofarrival.getText().toString();
            bond = PlaceTab1.bond.getText().toString();
            Log.d("gettingtabData", "encrypt: " + paramcompanyname);
            Log.d("gettingtabData", "size to send: "+PlaceTab1.TagCreateList.size());
            Log.d("gettingtabData", "cpackage: " + cpackage);
            Log.d("gettingtabData", "post: " + post);
            Log.d("gettingtabData", "selected: " + selected);
            Log.d("gettingtabData", "vacancies: " + vacancies);
            Log.d("gettingtabData", "lastdateofrr: " + lastdateofrr);
            Log.d("gettingtabData", "dateofarrival: " + dateofarrival);
            Log.d("gettingtabData", "bond: " + bond);

            apti = PlaceTab2.apti.getText().toString();
            techtest = PlaceTab2.techtest.getText().toString();
            groupdisc = PlaceTab2.groupdisc.getText().toString();
            techinterview = PlaceTab2.techinterview.getText().toString();
            Hrinterview = PlaceTab2.Hrinterview.getText().toString();
            Log.d("gettingtabData", "apti: " + apti);
            Log.d("gettingtabData", "techtest: " + techtest);
            Log.d("gettingtabData", "groupdisc: " + groupdisc);
            Log.d("gettingtabData", "techinterview: " + techinterview);
            Log.d("gettingtabData", "Hrinterview: " + Hrinterview);

//
            xcriteria = PlaceTab3.xcriteria.getText().toString();
            xiicriteria = PlaceTab3.xiicriteria.getText().toString();
            ugcriteria = PlaceTab3.ugcriteria.getText().toString();
            pgcriteria = PlaceTab3.pgcriteria.getText().toString();

            Log.d("gettingtabData", "xcriteria: " + xcriteria);
            Log.d("gettingtabData", "xcritexiicriteriaria: " + xiicriteria);
            Log.d("gettingtabData", "ugcriteria: " + ugcriteria);
            Log.d("gettingtabData", "pgcriteria: " + pgcriteria);
            new save().execute();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_save:

                validate();
                break;

            case android.R.id.home:

                onBackPressed();

                return (true);
        }

        return (super.onOptionsItemSelected(item));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_create, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onBackPressed() {

        PlacementCreateTab1 PlaceTab1 = (PlacementCreateTab1) adapter.getItem(0);
        PlacementCreateTab2 PlaceTab2 = (PlacementCreateTab2) adapter.getItem(1);
        PlacementCreateTab3 PlaceTab3 = (PlacementCreateTab3) adapter.getItem(2);

        if (edittedFlag == 1 || PlaceTab1.isTabEditted() || PlaceTab2.isTabEditted() || PlaceTab3.isTabEditted()) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder
                    .setMessage("Do you want to discard changes ?")
                    .setCancelable(false)
                    .setPositiveButton("Discard",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    setResult(299);
                                    CreatePlacement.super.onBackPressed();
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
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(CreatePlacement.this));
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(CreatePlacement.this));
                }
            });

            alertDialog.show();
        } else{

            setResult(299);
            CreatePlacement.super.onBackPressed();
        }
    }

    private void setupViewPager(ViewPager viewPager) {

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PlacementCreateTab1(), "Job Description");
        adapter.addFragment(new PlacementCreateTab2(), "Selection Process");
        adapter.addFragment(new PlacementCreateTab3(), "Selection Criteria");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    class save extends AsyncTask<String, String, String> {
        String sbatchesTags="", sexptaTags="";
        @Override
        protected String doInBackground(String... param) {
            Log.d("gettingtabData", "encUsername: " + encUsername);


            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encUsername));    //0
            params.add(new BasicNameValuePair("a", encRole));   //1
            params.add(new BasicNameValuePair("b", encforwhom));       //2
            params.add(new BasicNameValuePair("c", paramcompanyname));       //3
            params.add(new BasicNameValuePair("d", cpackage));       //4
            params.add(new BasicNameValuePair("e", post));     //5
            params.add(new BasicNameValuePair("f", selected));     //6
            params.add(new BasicNameValuePair("g", vacancies));     //7
            params.add(new BasicNameValuePair("h", lastdateofrr));     //8
            params.add(new BasicNameValuePair("i", dateofarrival));     //9
            params.add(new BasicNameValuePair("j", bond));     //10
            params.add(new BasicNameValuePair("k", apti));     //11
            params.add(new BasicNameValuePair("l", techtest));     //12
            params.add(new BasicNameValuePair("m", groupdisc));     //13
            params.add(new BasicNameValuePair("n", techinterview));     //14
            params.add(new BasicNameValuePair("o", Hrinterview));     //15
            params.add(new BasicNameValuePair("p", xcriteria));     //16
            params.add(new BasicNameValuePair("q", xiicriteria));     //17
            params.add(new BasicNameValuePair("r", ugcriteria));     //18
            params.add(new BasicNameValuePair("s", pgcriteria));     //19
            params.add(new BasicNameValuePair("t", sbatchesTags));     //20
            params.add(new BasicNameValuePair("v", sexptaTags));     //21


            json = jParser.makeHttpRequest(Z.url_CreatePlacements, "GET", params);
            try {
                r = json.getString("info1");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {
            setResult(AdminActivity.ADMIN_CREATE_DATA_CHANGE_RESULT_CODE);
            Toast.makeText(CreatePlacement.this, result, Toast.LENGTH_SHORT).show();
            CreatePlacement.super.onBackPressed();

        }
    }




}
