package placeme.octopusites.com.placeme;

import android.graphics.Bitmap;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import placeme.octopusites.com.placeme.modal.Modelmyprofileintro;
import placeme.octopusites.com.placeme.modal.MyProfileDiplomaModal;
import placeme.octopusites.com.placeme.modal.MyProfileTenthModal;
import placeme.octopusites.com.placeme.modal.MyProfileTwelthModal;
import placeme.octopusites.com.placeme.modal.MyProfileUgModal;
import placeme.octopusites.com.placeme.modal.PgSem;

import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;
import static placeme.octopusites.com.placeme.AES4all.fromString;


public class ViewPlacement extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    JSONObject json;
    JSONParser jParser = new JSONParser();


    String username, resultofop;
    String id, companyname, cpackage, post, forwhichcourse, forwhichstream, vacancies, lastdateofregistration, dateofarrival, bond, noofapti, nooftechtest, noofgd, noofti, noofhri, stdx, stdxiiordiploma, ug, pg, uploadtime, lastmodified, uploadedby, noofallowedliveatkt, noofalloweddeadatkt, studenttenthmarks, studenttwelthordiplomamarks, studentugmarks, studentpgmarks;
    Button registerbutton;
    ProgressBar progressBar;

    int found_box1 = 0, found_tenth = 0, found_twelth = 0, found_diploma = 0, found_ug = 0, found_pgsem = 0, found_pgyear = 0, found_projects = 0, found_lang = 0, found_certificates = 0;
    int found_courses = 0, found_skills = 0, found_honors = 0, found_patents = 0, found_publications = 0, found_careerobj = 0, found_strengths = 0, found_weaknesses = 0, found_locationpreferences = 0;
    int found_contact_details = 0, found_personal = 0;


    String role;
    String fname = "", lname = "";


    String sPadding = "ISO10126Padding";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_placement);


        username = MySharedPreferencesManager.getUsername(this);

        role = MySharedPreferencesManager.getRole(this);

        toolbar = (Toolbar) findViewById(R.id.placementtoolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Placement");

        viewPager = (ViewPager) findViewById(R.id.placementviewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.placementtabs);
        tabLayout.setupWithViewPager(viewPager);


        String uploadedby_enc = getIntent().getStringExtra("uploadedby");

        uploadedby = uploadedby_enc;


        registerbutton = (Button) findViewById(R.id.registerforplacementbutton);
        registerbutton.setTypeface(Z.getBold(this));
        progressBar = (ProgressBar) findViewById(R.id.registerforplacementprogress);

        id = getIntent().getStringExtra("id");
        companyname = getIntent().getStringExtra("companyname");
        cpackage = getIntent().getStringExtra("package");
        post = getIntent().getStringExtra("post");
        forwhichcourse = getIntent().getStringExtra("forwhichcourse");
        forwhichstream = getIntent().getStringExtra("forwhichstream");
        vacancies = getIntent().getStringExtra("vacancies");
        lastdateofregistration = getIntent().getStringExtra("lastdateofregistration");
        dateofarrival = getIntent().getStringExtra("dateofarrival");
        bond = getIntent().getStringExtra("bond");
        noofapti = getIntent().getStringExtra("noofapti");
        nooftechtest = getIntent().getStringExtra("nooftechtest");
        noofgd = getIntent().getStringExtra("noofgd");
        noofti = getIntent().getStringExtra("noofti");
        noofhri = getIntent().getStringExtra("noofhri");
        stdx = getIntent().getStringExtra("stdx");
        stdxiiordiploma = getIntent().getStringExtra("stdxiiordiploma");
        ug = getIntent().getStringExtra("ug");
        pg = getIntent().getStringExtra("pg");
        uploadtime = getIntent().getStringExtra("uploadtime");
        lastmodified = getIntent().getStringExtra("lastmodified");
        noofallowedliveatkt = getIntent().getStringExtra("noofallowedliveatkt");
        noofalloweddeadatkt = getIntent().getStringExtra("noofalloweddeadatkt");


        SavePlacementInfoForFragment save = new SavePlacementInfoForFragment();
        save.setId(id);
        save.setCompanyname(companyname);
        save.setPackage(cpackage);
        save.setPost(post);
        save.setForwhichcourse(forwhichcourse);
        save.setForwhichstream(forwhichstream);
        save.setVacancies(vacancies);
        save.setLastdateofregistration(lastdateofregistration);
        save.setDateofarrival(dateofarrival);
        save.setBond(bond);
        save.setNoofapti(noofapti);
        save.setNooftechtest(nooftechtest);
        save.setNoofgd(noofgd);
        save.setNoofti(noofti);
        save.setNoofhri(noofhri);
        save.setStdx(stdx);
        save.setStdxiiordiploma(stdxiiordiploma);
        save.setUg(ug);
        save.setPg(pg);
        save.setNoofallowedliveatkt(noofallowedliveatkt);
        save.setNoofalloweddeadatkt(noofalloweddeadatkt);
        save.setUploadtime(uploadtime);
        save.setLastmodified(lastmodified);
        save.setUploadedby(uploadedby);

        if (MySharedPreferencesManager.getRole(this).equals("student")) {
            new GetStudentData().execute();

        }
        if (MySharedPreferencesManager.getRole(this).equals("alumni")) {
            new GetStudentData().execute();

        }

        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // download resume in database
                registerbutton.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                new registerforPlacementTask().execute();

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                onBackPressed();

                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PlacementTab1(), "Job Description");
        adapter.addFragment(new PlacementTab2(), "Selection Process");
        adapter.addFragment(new PlacementTab3(), "Selection Criteria");
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

    private class registerforPlacementTask extends AsyncTask<String, Void, Integer> {
        @Override
        protected Integer doInBackground(String... urls) {
            try {


                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("id", id));
                params.add(new BasicNameValuePair("u", username));
                params.add(new BasicNameValuePair("f", fname));
                params.add(new BasicNameValuePair("l", lname));
                params.add(new BasicNameValuePair("lc", companyname));


                json = jParser.makeHttpRequest(Z.url_RegisterForPlacement, "GET", params);
                String s = null;
                resultofop = json.getString("info");

            } catch (Exception e) {
                e.printStackTrace();
            }

            return 0;
        }

        @Override
        protected void onPostExecute(Integer result) {

            if (resultofop.equals("success"))
                Toast.makeText(ViewPlacement.this, "Successfully Registered", Toast.LENGTH_LONG).show();
            else if (resultofop.equals("already"))
                Toast.makeText(ViewPlacement.this, "Already Registered", Toast.LENGTH_LONG).show();
            else if (resultofop.equals("date"))
                Toast.makeText(ViewPlacement.this, "Registrations are closed..!", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(ViewPlacement.this, "Failed..!", Toast.LENGTH_LONG).show();

            registerbutton.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);

        }

    }


//    student data

    private class GetStudentData extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("u", username));
                json = jParser.makeHttpRequest(Z.load_student_data, "GET", params);


                //shift this to class
                String studenttenthmarksObj = "", studenttwelthordiplomamarksobj = "", diplomadataobject, ugdataobject = "";


                resultofop = json.getString("info");
                if (resultofop.equals("found")) {
                    String s = json.getString("intro");
                    if (s.equals("found")) {
                        found_box1 = 1;
                        Modelmyprofileintro obj2 = (Modelmyprofileintro) fromString(json.getString("introObj"), MySharedPreferencesManager.getDigest1(ViewPlacement.this), MySharedPreferencesManager.getDigest2(ViewPlacement.this));
                        fname = obj2.getFirstname();
                        lname = obj2.getLastname();

                    }
                    s = json.getString("tenth");
                    if (s.equals("found")) {

                        studenttenthmarksObj = json.getString("tenthobj");
                        MyProfileTenthModal obj2 = (MyProfileTenthModal) fromString(studenttenthmarksObj, MySharedPreferencesManager.getDigest1(ViewPlacement.this), MySharedPreferencesManager.getDigest2(ViewPlacement.this));
                        studenttenthmarks = obj2.percentage;
                        found_tenth = 1;
                    }
                    s = json.getString("twelth");
                    if (s.equals("found")) {
                        studenttwelthordiplomamarksobj = json.getString("twelthobj");
                        MyProfileTwelthModal obj2 = (MyProfileTwelthModal) fromString(studenttwelthordiplomamarksobj, MySharedPreferencesManager.getDigest1(ViewPlacement.this), MySharedPreferencesManager.getDigest2(ViewPlacement.this));
                        studenttwelthordiplomamarks = obj2.getPercentage();
                        if (studenttwelthordiplomamarks != null) {
                            found_twelth = 1;
                        } else {
                            found_twelth = 0;
                            s = json.getString("diploma");
                            if (s.equals("found")) {
                                diplomadataobject = json.getString("diplomaobj");
                                MyProfileDiplomaModal obj3 = (MyProfileDiplomaModal) fromString(diplomadataobject, MySharedPreferencesManager.getDigest1(ViewPlacement.this), MySharedPreferencesManager.getDigest2(ViewPlacement.this));
                                studenttwelthordiplomamarks = obj3.getAggregate();
                                found_diploma = 1;
                            }
                        }
                    }

                    s = json.getString("ug");
                    if (s.equals("found")) {
                        ugdataobject = json.getString("ugobj");
                        MyProfileUgModal obj2 = (MyProfileUgModal) fromString(ugdataobject, MySharedPreferencesManager.getDigest1(ViewPlacement.this), MySharedPreferencesManager.getDigest2(ViewPlacement.this));
                        studentugmarks = obj2.aggregate;
                        found_ug = 1;
                    }
                    s = json.getString("pgsem");
                    if (s.equals("found")) {

                        PgSem obj = (PgSem) fromString(json.getString("pgsemdata"), MySharedPreferencesManager.getDigest1(ViewPlacement.this), MySharedPreferencesManager.getDigest2(ViewPlacement.this));
                        studentpgmarks = obj.getAggregatepgsem();
                        found_pgsem = 1;
                    }

                    s = json.getString("pgyear");
                    if (s.equals("found")) {
                        found_pgyear = 1;
                    }

                    s = json.getString("projects");
                    if (s.equals("found")) {
                        found_projects = 1;
                    }
                    s = json.getString("knownlang");
                    if (s.equals("found")) {
                        found_lang = 1;
                    }
                    s = json.getString("certificates");
                    if (s.equals("found")) {
                        found_certificates = 1;
                    }
                    s = json.getString("courses");
                    if (s.equals("found")) {
                        found_courses = 1;
                    }
                    s = json.getString("skills");
                    if (s.equals("found")) {
                        found_skills = 1;
                    }
                    s = json.getString("honors");
                    if (s.equals("found")) {
                        found_honors = 1;
                    }
                    s = json.getString("patents");
                    if (s.equals("found")) {
                        found_patents = 1;
                    }
                    s = json.getString("publications");
                    if (s.equals("found")) {
                        found_publications = 1;
                    }
                    s = json.getString("career");
                    if (s.equals("found")) {
                        found_careerobj = 1;
                    }
                    s = json.getString("strengths");
                    if (s.equals("found")) {
                        found_strengths = 1;
                    }
                    s = json.getString("weaknesses");
                    if (s.equals("found")) {
                        found_weaknesses = 1;
                    }
                    s = json.getString("locationpreferences");
                    if (s.equals("found")) {
                        found_locationpreferences = 1;
                    }
                    s = json.getString("contact_details");
                    if (s.equals("found")) {
                        found_contact_details = 1;
                    }
                    s = json.getString("personal");
                    if (s.equals("found")) {
                        found_personal = 1;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return map;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
//            int found_box1=0,found_tenth=0,found_twelth=0,found_diploma=0,found_ug=0,found_pgsem=0,found_pgyear=0,found_projects=0,found_lang=0,found_certificates=0;
//            int found_courses=0,found_skills=0,found_honors=0,found_patents=0,found_publications=0,found_careerobj=0,found_strengths=0,found_weaknesses=0,found_locationpreferences=0;
//            int found_contact_details=0,found_personal=0;

            if (found_box1 == 0) {
//                    please fill intro information
                Toast.makeText(ViewPlacement.this, " please fill introduction information", Toast.LENGTH_SHORT).show();
            } else {
                if (found_tenth == 0) {
//                        please fill tenth information
                    Toast.makeText(ViewPlacement.this, " please fill tenth information", Toast.LENGTH_SHORT).show();

                } else {
                    if (found_twelth == 0 && found_diploma == 0) {
//                        please fill twelth or diploma information
                        Toast.makeText(ViewPlacement.this, " please fill twelth or diploma information", Toast.LENGTH_SHORT).show();

                    } else {
                        if (found_ug == 0) {
//                        please fill ug information
                            Toast.makeText(ViewPlacement.this, " please fill gradution information", Toast.LENGTH_SHORT).show();

                        } else {
                            if (found_projects == 0) {
//                        please fill project information
                                Toast.makeText(ViewPlacement.this, " please fill project information", Toast.LENGTH_SHORT).show();

                            } else {
                                if (found_lang == 0) {
//                        please fill language information
                                    Toast.makeText(ViewPlacement.this, " please fill language information", Toast.LENGTH_SHORT).show();

                                } else {
                                    if (found_skills == 0) {
//                        please fill skill information
                                        Toast.makeText(ViewPlacement.this, " please fill skill information", Toast.LENGTH_SHORT).show();

                                    } else {
                                        if (found_careerobj == 0) {
//                        please fill career objective information
                                            Toast.makeText(ViewPlacement.this, " please fill career objective information", Toast.LENGTH_SHORT).show();

                                        } else {
                                            if (found_strengths == 0) {
//                        please fill strength information
                                                Toast.makeText(ViewPlacement.this, " please fill strength information", Toast.LENGTH_SHORT).show();

                                            } else {
                                                if (found_weaknesses == 0) {
//                        please fill weaknesses information
                                                    Toast.makeText(ViewPlacement.this, " please fill weaknesses information", Toast.LENGTH_SHORT).show();

                                                } else {
                                                    if (found_contact_details == 0) {
//                        please fill contact details information
                                                        Toast.makeText(ViewPlacement.this, " please fill contact details information", Toast.LENGTH_SHORT).show();

                                                    } else {
                                                        if (found_personal == 0) {
//                        please fill personal information
                                                            Toast.makeText(ViewPlacement.this, " please fill personal information", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                }

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
            }

            SavePlacementInfoForFragment save = new SavePlacementInfoForFragment();
            Float c10 = 0.0f, s10 = 0.0f, c12 = 0.0f, s12 = 0.0f, cu = 0.0f, su = 0.0f;
            c10 = Float.parseFloat(stdx);
            c12 = Float.parseFloat(stdxiiordiploma);
            cu = Float.parseFloat(ug);
            int tenthflag = 0, twelthordiplomaflag = 0, ugflag = 0;


            if (found_tenth == 0) {
//10 th not filled
  save.setStudenttenthmarks("0.00");

            } else {

                save.setStudenttenthmarks(studenttenthmarks);
                s10 = Float.parseFloat(studenttenthmarks);
                if (s10 >= c10) {
                    tenthflag = 1;
                }


            }

            if (found_twelth == 1) {

                save.setStudenttwelthordiplomamarks(studenttwelthordiplomamarks);
                s12 = Float.parseFloat(studenttwelthordiplomamarks);
                if (s12 >= c12) {
                    twelthordiplomaflag = 1;
                }

            } else   if (found_diploma == 1)  {
                save.setStudenttwelthordiplomamarks(studenttwelthordiplomamarks);
                s12 = Float.parseFloat(studenttwelthordiplomamarks);
                if (s12 >= c12) {
                    twelthordiplomaflag = 1;
                }


            }else{
                save.setStudenttwelthordiplomamarks("0.00");

            }




            if (found_ug == 0) {
                save.setStudentugmarks("0.00");

//udgnot found
            } else {

                save.setStudentugmarks(studentugmarks);
                su = Float.parseFloat(studentugmarks);

                if (su >= cu) {
                    ugflag = 1;
                }


            }

            if (found_pgsem == 0) {
//studentpgmarks found
                save.setStudentpgmarks("0.00");

            } else {
                save.setStudentpgmarks(studentpgmarks);


            }


            LinearLayout registerbuttonlayout = (LinearLayout) findViewById(R.id.registerbuttonlayout);
            if (tenthflag == 1 && twelthordiplomaflag == 1 && ugflag == 1) {
                registerbuttonlayout.setVisibility(View.VISIBLE);
                registerbutton.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);

                if (found_box1 == 1 && found_tenth == 1 && (found_diploma == 1 || found_twelth == 1) && found_ug == 1 && found_projects == 1 && found_lang == 1 && found_contact_details == 1 && found_skills == 1 && found_careerobj == 1 && found_strengths == 1 && found_weaknesses == 1 && found_personal == 1) {
                    new SaveResumedatabase().execute();
                } else {
                    Toast.makeText(ViewPlacement.this, " Resume not generated", Toast.LENGTH_SHORT).show();
                    new GetStudentData().execute();
                }

            } else {
                registerbuttonlayout.setVisibility(View.GONE);

            }


        }
    }

    private class SaveResumedatabase extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String r = "";
            String format = "pdf";

            try {

                String template = MySharedPreferencesManager.getData(ViewPlacement.this, "template");
                if (template == null) {
                    int temp = 2;
                    template = temp + "";
                }


                Log.d("TAG", "doInBackground: username -" + username);
                Log.d("TAG", "doInBackground: format -" + format);
                Log.d("TAG", "doInBackground: template -" + template);
                List<NameValuePair> params = new ArrayList<NameValuePair>();

                params.add(new BasicNameValuePair("username", username));
                params.add(new BasicNameValuePair("format", format));
                params.add(new BasicNameValuePair("template", template));

                json = jParser.makeHttpRequest(Z.url_SaveResume, "GET", params);

                r = json.getString("info");
                Log.d("TAG", "doInBackground: result -" + r);


            } catch (Exception e) {
                Log.d("TAG", "doInBackground: exception - " + e.getMessage());
            }
            return r;
        }

        protected void onPostExecute(String result) {
            try {

                if (result.equals("found"))
                    Toast.makeText(ViewPlacement.this, "Successfully Register..!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ViewPlacement.this, "Not Register..!", Toast.LENGTH_SHORT).show();

                registerbutton.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);

            } catch (Exception e) {
            }
        }
    }
}

