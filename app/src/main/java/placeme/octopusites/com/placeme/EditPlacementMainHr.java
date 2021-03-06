package placeme.octopusites.com.placeme;

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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mabbas007.tagsedittext.TagsEditText;

import static placeme.octopusites.com.placeme.AES4all.Encrypt;

public class EditPlacementMainHr extends AppCompatActivity {

    private Toolbar toolbar;
    private TagsEditText batchesTags,expTag;
    ArrayAdapter<String> dataAdapter;
    ArrayAdapter<String> dataAdapterexp;

    ArrayList<String> TagCreateList = new ArrayList<>();
    ArrayList<String> TagCreateList2 = new ArrayList<>();

    private TabLayout tabLayout;
    private ViewPager viewPager;
    ViewPagerAdapter adapter;
    String forwhom = "",encforwhom="";
    String paramcompanyname = "", cpackage = "", post = "", selected = "", vacancies = "", lastdateofrr = "", dateofarrival = "", bond = "", apti = "", techtest = "",
            groupdisc = "", techinterview = "", Hrinterview = "", xcriteria = "", xiicriteria = "", ugcriteria = "", pgcriteria = "";

    String encUsername = "",encRole="";
    JSONObject json;
    JSONParser jParser = new JSONParser();

    String sid,scompanyname,spackage,spost,sforwhichcourse,sforwhichstream,svacancies,slastdateofregistration,sdateofarrival,sbond,snoofapti;
    String snooftechtest,snoofgd,snoofti,snoofhri,sstdx,sstdxiiordiploma,sug,spg,suploadtime,slastmodified,suploadedby,sforwhom="",snoofallowedliveatkt,snoofalloweddeadatkt;


    String passingyear="",experiences="";


    String username="", srole = "",instname="";
    String sbatchesTags="" ,sexptaTags="";
     String digest1="",digest2="";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_placement_main_hr);

        toolbar = (Toolbar) findViewById(R.id.placementtoolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Placement");

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.while_color), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        batchesTags = (TagsEditText) findViewById(R.id.batchesTags);
        batchesTags.setFocusable(false);
        expTag = (TagsEditText) findViewById(R.id.expTag);
        expTag.setFocusable(false);

        viewPager = (ViewPager) findViewById(R.id.placementviewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.placementtabs);
        tabLayout.setupWithViewPager(viewPager);

        dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, getResources().getStringArray(R.array.fruits)) {
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
        dataAdapterexp = new ArrayAdapter<String>(this, R.layout.spinner_item, getResources().getStringArray(R.array.ExpYears)) {
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
        batchesTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                batchesTags.setAdapter(dataAdapter);
                batchesTags.setThreshold(1);
                if (batchesTags.getText().toString().contains("ALL")) {
                    //dont popullate
                    Toast.makeText(EditPlacementMainHr.this, "Notification will be sent to All batches", Toast.LENGTH_SHORT).show();
                } else {
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getBaseContext(), R.layout.spinner_item, getResources().getStringArray(R.array.fruits)) {
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
        });

        batchesTags.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String temp = getResources().getStringArray(R.array.fruits)[position];
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

                String temp = "";
                temp = batchesTags.getText().toString();
                Log.d("tag", "onTagsChanged: " + temp);
                if (temp.equals("")) {
                    batchesTags.dismissDropDown();
                }


            }

            @Override
            public void onEditingFinished() {


            }
        });

        expTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expTag.setAdapter(dataAdapterexp);
                expTag.setThreshold(1);
                if (expTag.getText().toString().length()>1) {
                    //dont popullate
//                    Toast.makeText(CreatePlacementHr.this, "Notification will be sent to All batches", Toast.LENGTH_SHORT).show();
                } else {
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getBaseContext(), R.layout.spinner_item, getResources().getStringArray(R.array.ExpYears)) {
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


                    expTag.setAdapter(dataAdapter);
                    expTag.showDropDown();
                }

            }
        });
        expTag.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String temp = getResources().getStringArray(R.array.ExpYears)[position];
                temp = temp.trim();

                if (temp.contains("ALL")) {
                    TagCreateList2.clear();
                    TagCreateList2.add("ALL");
                    String[] TagCreateArray = new String[TagCreateList2.size()];
                    TagCreateArray = TagCreateList2.toArray(TagCreateArray);
                    expTag.setText("");
                    expTag.setTags(TagCreateArray);
                }
                if (TagCreateList2.contains(temp)) ;
                {
                    int occurance = TagCreateList2.indexOf(temp);
                    int Lastelement = TagCreateList2.size() - 1;
                    Log.d("occurance", "onItemClick:" + occurance);
                    Log.d("Lastelement", "onItemClick:" + Lastelement);

                    if (occurance != TagCreateList2.size() - 1) {
                        Log.d("deletethis", "onItemClick:");
                        TagCreateList2.remove(TagCreateList2.size() - 1);
                        String[] TagCreateArray = new String[TagCreateList2.size()];
                        TagCreateArray = TagCreateList2.toArray(TagCreateArray);
                        expTag.setTags(TagCreateArray);
                    }

                }
            }

        });



        //getters
        srole=MySharedPreferencesManager.getRole(this);
        instname=MySharedPreferencesManager.getInstitute(this);
        encUsername=MySharedPreferencesManager.getUsername(this);
        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);

        sid=getIntent().getStringExtra("id");
        passingyear=getIntent().getStringExtra("passingyear");
        experiences=getIntent().getStringExtra("experiences");
if(passingyear!=null){

        if(passingyear.length()!=0){


            String batchyears[]= passingyear.split(" ");
            batchesTags.setTags(batchyears);

        }
        if(experiences.length()!=0){
            expTag.setTags(experiences);

        }
}else{
    Toast.makeText(this, "expwerience and passing year null (send it from server)", Toast.LENGTH_SHORT).show();
}




//        new GetPassingYearAndExp().execute();









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





    void validate() {
        try {

             sbatchesTags = batchesTags.getText().toString();
             sexptaTags = expTag.getText().toString();
            Log.d("gettingtabData", "sbatchesTags: "+sbatchesTags);
            Log.d("gettingtabData", "sbatchesTags: "+sexptaTags);


            if(sbatchesTags.length()<=2){
                batchesTags.setError("enter passing Year ");
            }else if(sexptaTags.length()<=2){

                expTag.setError("enter passing Year ");
            }
            else {

                forwhom = "PLACEME(ALL)";
                Log.d("forwhomeStringAppend", "onCreate: " + forwhom);

                PlacementCreateTab1 PlaceTab1 = (PlacementCreateTab1) adapter.getItem(0);
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

                            Toast.makeText(this, "Tab1 & Tab2 & tab3 OK", Toast.LENGTH_SHORT).show();
                            //call ENCRYPT ND SAVE save method
                            encrypt();
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void encrypt() {
        try {
            PlacementCreateTab1 PlaceTab1 = (PlacementCreateTab1) adapter.getItem(0);
            PlacementCreateTab2 PlaceTab2 = (PlacementCreateTab2) adapter.getItem(1);
            PlacementCreateTab3 PlaceTab3 = (PlacementCreateTab3) adapter.getItem(2);


            digest1 = MySharedPreferencesManager.getDigest1(this);
            digest2 = MySharedPreferencesManager.getDigest2(this);

            Log.d("gettingtabData", "encforwhom: " + encforwhom);

//            String encparamcompanyname="";
//            byte[] paramcompanynameBytes = srole.getBytes("UTF-8");
//            byte[] paramcompanynameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, paramcompanynameBytes);
//            encparamcompanyname=new String(SimpleBase64Encoder.encode(paramcompanynameEncryptedBytes));

            paramcompanyname = PlaceTab1.companyname.getText().toString();
            cpackage = PlaceTab1.cpackage.getText().toString();
            post = PlaceTab1.post.getText().toString();
            selected = PlaceTab1.selected.getText().toString();
            vacancies = PlaceTab1.vacancies.getText().toString();
            lastdateofrr = PlaceTab1.lastdateofrr.getText().toString();
            dateofarrival = PlaceTab1.dateofarrival.getText().toString();
            bond = PlaceTab1.bond.getText().toString();
            Log.d("gettingtabData", "encrypt: " + paramcompanyname);
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

            encRole = Encrypt(MySharedPreferencesManager.getRole(this), digest1, digest2);
            encUsername =MySharedPreferencesManager.getUsername(this);
            encforwhom = Encrypt(forwhom, digest1, digest2);

            new save().execute();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }

    class save extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... param) {

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
            params.add(new BasicNameValuePair("w",  sid));     //22



            json = jParser.makeHttpRequest(Z.url_ModifyPlacementHr, "GET", params);
            try {
                r = json.getString("info");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(EditPlacementMainHr.this, result, Toast.LENGTH_SHORT).show();

            EditPlacementMainHr.super.onBackPressed();

//            if(result.equals("success"))
//            {
//                Toast.makeText(CreateNotification.this,"Successfully Saved..!",Toast.LENGTH_SHORT).show();
//
////                Intent returnIntent = new Intent();
////                returnIntent.putExtra("result", result);
////                if(edittedFlag==1){
////                    setResult(111);
////                }
//                CreateNotification.super.onBackPressed();
//            }
//            else {
//                Toast.makeText(CreateNotification.this,result,Toast.LENGTH_SHORT).show();
//
//            }
        }
    }




//    class GetPassingYearAndExp extends AsyncTask<String, String, String> {
//
//        protected String doInBackground(String... param) {
//
//
//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//            params.add(new BasicNameValuePair("id", sid)); //0
//
//            json = jParser.makeHttpRequest(url_getforwhome, "GET", params);
//            try {
//                Forwhomefromdb = json.getString("forwhom");
//
//            }catch (Exception e){e.printStackTrace();}
//            return "";
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            try{
//
//                byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
//                byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
//                String sPadding = "ISO10126Padding";
//                if (!Forwhomefromdb.equals("")) {
//                    byte[] ForwhomefromdbEncryptedBytes = SimpleBase64Encoder.decode(Forwhomefromdb);
//                    byte[] ForwhomefromdbDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, ForwhomefromdbEncryptedBytes);
//                    Forwhomefromdb = new String(ForwhomefromdbDecryptedBytes);
//                    Log.d("Forwhomefromdb", "onPostExecute: "+Forwhomefromdb);
//                }
//
//                if(Forwhomefromdb.contains("ALL")){
//                    allumiselector.setVisibility(View.VISIBLE);
//                    CheckBoxsAlumni.setChecked(true);
//                    batchesTagsedittext.setText("ALL");
//                }
//                if(Forwhomefromdb.contains("ADMIN"))          //CHANGE IT TO sTUDENT
//                {
//                    CheckBoxstudent.setChecked(true);
//                }
//
//                int index1=Forwhomefromdb.indexOf("(");
//                int index2=Forwhomefromdb.indexOf(")");
//                String whomsYears="";
//                for(int i=index1+1;i<index2;i++) {
//                    whomsYears += Forwhomefromdb.charAt(i);
//                }
////                String testStr="STUDENT";
//                Log.d("TAG1", "before: "+whomsYears);
//                whomsYears=whomsYears.replace("ADMIN,","");
//                Log.d("TAG1", "after1: "+whomsYears);
//                whomsYears=whomsYears.replace("STUDENT,","");
//                Log.d("TAG1", "after2: "+whomsYears);
//                whomsYears=whomsYears.replace("ADMIN","");
//                Log.d("TAG1", "after3: "+whomsYears);
//                whomsYears=whomsYears.replace("STUDENT","");
//                Log.d("TAG1", "after4: "+whomsYears);
//                whomsYears=whomsYears.replace("ALL","");
//
//                if(whomsYears.length()>=2)
//                {
//                    CheckBoxsAlumni.setChecked(true);
//                    Log.d("whomsYears3:",whomsYears);
//                    whomsYears=whomsYears.replace(","," ");
//                    String batchyears[]= whomsYears.split(" ");
//                    allumiselector.setVisibility(View.VISIBLE);
//                    batchesTagsedittext.setTags(batchyears);
//
//                }
//
//            }catch (Exception e){
//
//                Log.d("whomsYears e:", e.getMessage());
//                Toast.makeText(EditPlacementMain.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }
//    }



}
