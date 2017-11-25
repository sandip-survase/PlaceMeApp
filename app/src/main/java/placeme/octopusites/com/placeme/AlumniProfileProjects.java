package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;

public class AlumniProfileProjects extends AppCompatActivity implements TextWatcher {

    int projectscount=0;
    View addmoreproject;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    public static final String Username = "nameKey";
    String username;
    String digest1,digest2;
    JSONParser jParser = new JSONParser();
    JSONObject json;

    EditText proj1,domain1,team1,duration1,proj2,domain2,team2,duration2,proj3,domain3,team3,duration3,proj4,domain4,team4,duration4,proj5,domain5,team5,duration5,proj6,domain6,team6,duration6,proj7,domain7,team7,duration7,proj8,domain8,team8,duration8,proj9,domain9,team9,duration9,proj10,domain10,team10,duration10;
    String sproj1="",sdomain1="",steam1="",sduration1="",sproj2="",sdomain2="",steam2="",sduration2="",sproj3="",sdomain3="",steam3="",sduration3="",sproj4="",sdomain4="",steam4="",sduration4="",sproj5="",sdomain5="",steam5="",sduration5="",sproj6="",sdomain6="",steam6="",sduration6="",sproj7="",sdomain7="",steam7="",sduration7="",sproj8="",sdomain8="",steam8="",sduration8="",sproj9="",sdomain9="",steam9="",sduration9="",sproj10="",sdomain10="",steam10="",sduration10="";
    String encproj1,encdomain1,encteam1,encduration1,encproj2,encdomain2,encteam2,encduration2,encproj3,encdomain3,encteam3,encduration3,encproj4,encdomain4,encteam4,encduration4,encproj5,encdomain5,encteam5,encduration5,encproj6,encdomain6,encteam6,encduration6,encproj7,encdomain7,encteam7,encduration7,encproj8,encdomain8,encteam8,encduration8,encproj9,encdomain9,encteam9,encduration9,encproj10,encdomain10,encteam10,encduration10;
    View trash1selectionview,trash2selectionview,trash3selectionview,trash4selectionview,trash5selectionview,trash6selectionview,trash7selectionview,trash8selectionview,trash9selectionview,trash10selectionview;
    int edittedFlag=0;;
    int d=0;
    StudentData s=new StudentData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni_profile_projects);

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);
        username=MySharedPreferencesManager.getUsername(this);
        String role=MySharedPreferencesManager.getRole(this);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Projects Info");
        ab.setDisplayHomeAsUpEnabled(true);

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        TextView projtxt=(TextView)findViewById(R.id.projtxt);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/arba.ttf");
        projtxt.setTypeface(custom_font1);

        trash1selectionview=(View)findViewById(R.id.trash1selectionview);
        trash2selectionview=(View)findViewById(R.id.trash2selectionview);
        trash3selectionview=(View)findViewById(R.id.trash3selectionview);
        trash4selectionview=(View)findViewById(R.id.trash4selectionview);
        trash5selectionview=(View)findViewById(R.id.trash5selectionview);
        trash6selectionview=(View)findViewById(R.id.trash6selectionview);
        trash7selectionview=(View)findViewById(R.id.trash7selectionview);
        trash8selectionview=(View)findViewById(R.id.trash8selectionview);
        trash9selectionview=(View)findViewById(R.id.trash9selectionview);
        trash10selectionview=(View)findViewById(R.id.trash10selectionview);

        trash1selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=1;
                showDeletDialog();
            }
        });
        trash2selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=2;
                showDeletDialog();

            }
        });
        trash3selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=3;
                showDeletDialog();
            }
        });
        trash4selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=4;
                showDeletDialog();
            }
        });
        trash5selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=5;
                showDeletDialog();
            }
        });
        trash6selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=6;
                showDeletDialog();
            }
        });
        trash7selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=7;
                showDeletDialog();
            }
        });
        trash8selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=8;
                showDeletDialog();
            }
        });
        trash9selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=9;
                showDeletDialog();
            }
        });
        trash10selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=10;
                showDeletDialog();
            }
        });

        proj1=(EditText)findViewById(R.id.proj1);
        domain1=(EditText)findViewById(R.id.domain1);
        team1=(EditText)findViewById(R.id.team1);
        duration1=(EditText)findViewById(R.id.duration1);
        proj2=(EditText)findViewById(R.id.proj2);
        domain2=(EditText)findViewById(R.id.domain2);
        team2=(EditText)findViewById(R.id.team2);
        duration2=(EditText)findViewById(R.id.duration2);
        proj3=(EditText)findViewById(R.id.proj3);
        domain3=(EditText)findViewById(R.id.domain3);
        team3=(EditText)findViewById(R.id.team3);
        duration3=(EditText)findViewById(R.id.duration3);
        proj4=(EditText)findViewById(R.id.proj4);
        domain4=(EditText)findViewById(R.id.domain4);
        team4=(EditText)findViewById(R.id.team4);
        duration4=(EditText)findViewById(R.id.duration4);
        proj5=(EditText)findViewById(R.id.proj5);
        domain5=(EditText)findViewById(R.id.domain5);
        team5=(EditText)findViewById(R.id.team5);
        duration5=(EditText)findViewById(R.id.duration5);
        proj6=(EditText)findViewById(R.id.proj6);
        domain6=(EditText)findViewById(R.id.domain6);
        team6=(EditText)findViewById(R.id.team6);
        duration6=(EditText)findViewById(R.id.duration6);
        proj7=(EditText)findViewById(R.id.proj7);
        domain7=(EditText)findViewById(R.id.domain7);
        team7=(EditText)findViewById(R.id.team7);
        duration7=(EditText)findViewById(R.id.duration7);
        proj8=(EditText)findViewById(R.id.proj8);
        domain8=(EditText)findViewById(R.id.domain8);
        team8=(EditText)findViewById(R.id.team8);
        duration8=(EditText)findViewById(R.id.duration8);
        proj9=(EditText)findViewById(R.id.proj9);
        domain9=(EditText)findViewById(R.id.domain9);
        team9=(EditText)findViewById(R.id.team9);
        duration9=(EditText)findViewById(R.id.duration9);
        proj10=(EditText)findViewById(R.id.proj10);
        domain10=(EditText)findViewById(R.id.domain10);
        team10=(EditText)findViewById(R.id.team10);
        duration10=(EditText)findViewById(R.id.duration10);

        proj1.addTextChangedListener(this);
        domain1.addTextChangedListener(this);
        team1.addTextChangedListener(this);
        duration1.addTextChangedListener(this);
        proj2.addTextChangedListener(this);
        domain2.addTextChangedListener(this);
        team2.addTextChangedListener(this);
        duration2.addTextChangedListener(this);
        proj3.addTextChangedListener(this);
        domain3.addTextChangedListener(this);
        team3.addTextChangedListener(this);
        duration3.addTextChangedListener(this);
        proj4.addTextChangedListener(this);
        domain4.addTextChangedListener(this);
        team4.addTextChangedListener(this);
        duration4.addTextChangedListener(this);
        proj5.addTextChangedListener(this);
        domain5.addTextChangedListener(this);
        team5.addTextChangedListener(this);
        duration5.addTextChangedListener(this);
        proj6.addTextChangedListener(this);
        domain6.addTextChangedListener(this);
        team6.addTextChangedListener(this);
        duration6.addTextChangedListener(this);
        proj7.addTextChangedListener(this);
        domain7.addTextChangedListener(this);
        team7.addTextChangedListener(this);
        duration7.addTextChangedListener(this);
        proj8.addTextChangedListener(this);
        domain8.addTextChangedListener(this);
        team8.addTextChangedListener(this);
        duration8.addTextChangedListener(this);
        proj9.addTextChangedListener(this);
        domain9.addTextChangedListener(this);
        team9.addTextChangedListener(this);
        duration9.addTextChangedListener(this);
        proj10.addTextChangedListener(this);
        domain10.addTextChangedListener(this);
        team10.addTextChangedListener(this);
        duration10.addTextChangedListener(this);

        addmoreproject=(View)findViewById(R.id.addmoreproject);
        addmoreproject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(projectscount==0)
                {
                    View v=(View)findViewById(R.id.projectline1);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout projectrl=(RelativeLayout)findViewById(R.id.projectrl2);
                    projectrl.setVisibility(View.VISIBLE);

                    projectscount++;

                }
                else  if(projectscount==1)
                {
                    View v=(View)findViewById(R.id.projectline2);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout projectrl=(RelativeLayout)findViewById(R.id.projectrl3);
                    projectrl.setVisibility(View.VISIBLE);

                    projectscount++;

                }
                else  if(projectscount==2)
                {
                    View v=(View)findViewById(R.id.projectline3);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout projectrl=(RelativeLayout)findViewById(R.id.projectrl4);
                    projectrl.setVisibility(View.VISIBLE);
                    projectscount++;

                }
                else  if(projectscount==3)
                {
                    View v=(View)findViewById(R.id.projectline4);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout projectrl=(RelativeLayout)findViewById(R.id.projectrl5);
                    projectrl.setVisibility(View.VISIBLE);

                    projectscount++;

                }
                else  if(projectscount==4)
                {
                    View v=(View)findViewById(R.id.projectline5);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout projectrl=(RelativeLayout)findViewById(R.id.projectrl6);
                    projectrl.setVisibility(View.VISIBLE);

                    projectscount++;

                }
                else  if(projectscount==5)
                {
                    View v=(View)findViewById(R.id.projectline6);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout projectrl=(RelativeLayout)findViewById(R.id.projectrl7);
                    projectrl.setVisibility(View.VISIBLE);

                    projectscount++;

                }
                else  if(projectscount==6)
                {
                    View v=(View)findViewById(R.id.projectline7);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout projectrl=(RelativeLayout)findViewById(R.id.projectrl8);
                    projectrl.setVisibility(View.VISIBLE);

                    projectscount++;

                }
                else  if(projectscount==7)
                {
                    View v=(View)findViewById(R.id.projectline8);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout projectrl=(RelativeLayout)findViewById(R.id.projectrl9);
                    projectrl.setVisibility(View.VISIBLE);

                    projectscount++;

                }
                else  if(projectscount==8)
                {
                    View v=(View)findViewById(R.id.projectline9);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout projectrl=(RelativeLayout)findViewById(R.id.projectrl10);
                    projectrl.setVisibility(View.VISIBLE);

                    projectscount++;

                    TextView t=(TextView)findViewById(R.id.addmoreprojecttxt);
                    ImageView i=(ImageView)findViewById(R.id.addmoreprojectimg);
                    addmoreproject.setVisibility(View.GONE);
                    t.setVisibility(View.GONE);
                    i.setVisibility(View.GONE);

                }

            }
        });
        ScrollView myprofileintroscrollview=(ScrollView)findViewById(R.id.myprofileprojects);
        disableScrollbars(myprofileintroscrollview);



        sproj1=s.getProj1();
        sdomain1=s.getDomain1();
        steam1=s.getTeam1();
        sduration1=s.getDuration1();
        sproj2=s.getProj2();
        sdomain2=s.getDomain2();
        steam2=s.getTeam2();
        sduration2=s.getDuration2();
        sproj3=s.getProj3();
        sdomain3=s.getDomain3();
        steam3=s.getTeam3();
        sduration3=s.getDuration3();
        sproj4=s.getProj4();
        sdomain4=s.getDomain4();
        steam4=s.getTeam4();
        sduration4=s.getDuration4();
        sproj5=s.getProj5();
        sdomain5=s.getDomain5();
        steam5=s.getTeam5();
        sduration5=s.getDuration5();
        sproj6=s.getProj6();
        sdomain6=s.getDomain6();
        steam6=s.getTeam6();
        sduration6=s.getDuration6();
        sproj7=s.getProj7();
        sdomain7=s.getDomain7();
        steam7=s.getTeam7();
        sduration7=s.getDuration7();
        sproj8=s.getProj8();
        sdomain8=s.getDomain8();
        steam8=s.getTeam8();
        sduration8=s.getDuration8();
        sproj9=s.getProj9();
        sdomain9=s.getDomain9();
        steam9=s.getTeam9();
        sduration9=s.getDuration9();
        sproj10=s.getProj10();
        sdomain10=s.getDomain10();
        steam10=s.getTeam10();
        sduration10=s.getDuration10();

        if(sproj1!=null) {
            if (sproj1.length() > 2) {
                proj1.setText(sproj1);
                domain1.setText(sdomain1);
                team1.setText(steam1);
                duration1.setText(sduration1);
            }
        }
        if(sproj2!=null) {
            if (sproj2.length() > 2) {
                View v = (View) findViewById(R.id.projectline1);
                v.setVisibility(View.VISIBLE);
                RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl2);
                projectrl.setVisibility(View.VISIBLE);

                proj2.setText(sproj2);
                domain2.setText(sdomain2);
                team2.setText(steam2);
                duration2.setText(sduration2);
                projectscount++;
            }
        }
        if(sproj3!=null) {
            if (sproj3.length() > 2) {
                View v = (View) findViewById(R.id.projectline2);
                v.setVisibility(View.VISIBLE);
                RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl3);
                projectrl.setVisibility(View.VISIBLE);

                proj3.setText(sproj3);
                domain3.setText(sdomain3);
                team3.setText(steam3);
                duration3.setText(sduration3);
                projectscount++;
            }
        }
        if(sproj4!=null) {
            if (sproj4.length() > 2) {
                View v = (View) findViewById(R.id.projectline3);
                v.setVisibility(View.VISIBLE);
                RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl4);
                projectrl.setVisibility(View.VISIBLE);

                proj4.setText(sproj4);
                domain4.setText(sdomain4);
                team4.setText(steam4);
                duration4.setText(sduration4);
                projectscount++;
            }
        }
        if(sproj5!=null) {
            if (sproj5.length() > 2) {
                View v = (View) findViewById(R.id.projectline4);
                v.setVisibility(View.VISIBLE);
                RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl5);
                projectrl.setVisibility(View.VISIBLE);
                proj5.setText(sproj5);
                domain5.setText(sdomain5);
                team5.setText(steam5);
                duration5.setText(sduration5);
                projectscount++;
            }
        }
        if(sproj6!=null) {
            if (sproj6.length() > 2) {
                View v = (View) findViewById(R.id.projectline5);
                v.setVisibility(View.VISIBLE);
                RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl6);
                projectrl.setVisibility(View.VISIBLE);

                proj6.setText(sproj6);
                domain6.setText(sdomain6);
                team6.setText(steam6);
                duration6.setText(sduration6);
                projectscount++;
            }
        }
        if(sproj7!=null) {
            if (sproj7.length() > 2) {
                View v = (View) findViewById(R.id.projectline6);
                v.setVisibility(View.VISIBLE);
                RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl7);
                projectrl.setVisibility(View.VISIBLE);

                proj7.setText(sproj7);
                domain7.setText(sdomain7);
                team7.setText(steam7);
                duration7.setText(sduration7);
                projectscount++;
            }
        }
        if(sproj8!=null) {
            if (sproj8.length() > 2) {
                View v = (View) findViewById(R.id.projectline7);
                v.setVisibility(View.VISIBLE);
                RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl8);
                projectrl.setVisibility(View.VISIBLE);

                proj8.setText(sproj8);
                domain8.setText(sdomain8);
                team8.setText(steam8);
                duration8.setText(sduration8);
                projectscount++;
            }
        }
        if(sproj9!=null) {
            if (sproj9.length() > 2) {
                View v = (View) findViewById(R.id.projectline8);
                v.setVisibility(View.VISIBLE);
                RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl9);
                projectrl.setVisibility(View.VISIBLE);

                proj9.setText(sproj9);
                domain9.setText(sdomain9);
                team9.setText(steam9);
                duration9.setText(sduration9);
                projectscount++;
            }
        }
        if(sproj10!=null) {
            if (sproj10.length() > 2) {
                View v = (View) findViewById(R.id.projectline9);
                v.setVisibility(View.VISIBLE);
                RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl10);
                projectrl.setVisibility(View.VISIBLE);

                proj10.setText(sproj10);
                domain10.setText(sdomain10);
                team10.setText(steam10);
                duration10.setText(sduration10);
                projectscount++;
                TextView t = (TextView) findViewById(R.id.addmoreprojecttxt);
                ImageView i = (ImageView) findViewById(R.id.addmoreprojectimg);
                addmoreproject.setVisibility(View.GONE);
                t.setVisibility(View.GONE);
                i.setVisibility(View.GONE);
            }
        }

        edittedFlag=0;
    }
    private void disableScrollbars(ScrollView scrollView) {
        if (scrollView != null) {

            scrollView.setVerticalScrollBarEnabled(false);

        }
    }
    void validateandSave()
    {

        proj1.setError(null);
        domain1.setError(null);
        team1.setError(null);
        duration1.setError(null);
        proj2.setError(null);
        domain2.setError(null);
        team2.setError(null);
        duration2.setError(null);
        proj3.setError(null);
        domain3.setError(null);
        team3.setError(null);
        duration3.setError(null);
        proj4.setError(null);
        domain4.setError(null);
        team4.setError(null);
        duration4.setError(null);
        proj5.setError(null);
        domain5.setError(null);
        team5.setError(null);
        duration5.setError(null);
        proj6.setError(null);
        domain6.setError(null);
        team6.setError(null);
        duration6.setError(null);
        proj7.setError(null);
        domain7.setError(null);
        team7.setError(null);
        duration7.setError(null);
        proj8.setError(null);
        domain8.setError(null);
        team8.setError(null);
        duration8.setError(null);
        proj9.setError(null);
        domain9.setError(null);
        team9.setError(null);
        duration9.setError(null);
        proj10.setError(null);
        domain10.setError(null);
        team10.setError(null);
        duration10.setError(null);

        sproj1=proj1.getText().toString();
        sdomain1=domain1.getText().toString();
        steam1=team1.getText().toString();
        sduration1=duration1.getText().toString();
        sproj2=proj2.getText().toString();
        sdomain2=domain2.getText().toString();
        steam2=team2.getText().toString();
        sduration2=duration2.getText().toString();
        sproj3=proj3.getText().toString();
        sdomain3=domain3.getText().toString();
        steam3=team3.getText().toString();
        sduration3=duration3.getText().toString();
        sproj4=proj4.getText().toString();
        sdomain4=domain4.getText().toString();
        steam4=team4.getText().toString();
        sduration4=duration4.getText().toString();
        sproj5=proj5.getText().toString();
        sdomain5=domain5.getText().toString();
        steam5=team5.getText().toString();
        sduration5=duration5.getText().toString();
        sproj6=proj6.getText().toString();
        sdomain6=domain6.getText().toString();
        steam6=team6.getText().toString();
        sduration6=duration6.getText().toString();
        sproj7=proj7.getText().toString();
        sdomain7=domain7.getText().toString();
        steam7=team7.getText().toString();
        sduration7=duration7.getText().toString();
        sproj8=proj8.getText().toString();
        sdomain8=domain8.getText().toString();
        steam8=team8.getText().toString();
        sduration8=duration8.getText().toString();
        sproj9=proj9.getText().toString();
        sdomain9=domain9.getText().toString();
        steam9=team9.getText().toString();
        sduration9=duration9.getText().toString();
        sproj10=proj10.getText().toString();
        sdomain10=domain10.getText().toString();
        steam10=team10.getText().toString();
        sduration10=duration10.getText().toString();

        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
        String sPadding = "ISO10126Padding";

        int errorflag=0;
        if(projectscount==0)
        {
            if(sproj1.length()<3) {
                errorflag=1;
                proj1.setError("Invalid Project Name");
            }
            else
            {
                errorflag=0;
                if(sdomain1.length()<3)
                {
                    errorflag=1;
                    domain1.setError("Invalid Domain");
                }
                else
                {
                    errorflag=0;
                    if(steam1.length()<1)
                    {
                        errorflag=1;
                        team1.setError("Invalid Teamsize");
                    }
                    else
                    {
                        errorflag=0;
                        if(sduration1.length()<1)
                        {
                            errorflag=1;
                            duration1.setError("Invalid Duration");
                        }

                    }
                }
            }
        }
        if(projectscount==1)
        {
            if(sproj1.length()<3) {
                errorflag=1;
                proj1.setError("Invalid Project Name");
            }
            else
            {
                errorflag=0;
                if(sdomain1.length()<3)
                {
                    errorflag=1;
                    domain1.setError("Invalid Domain");
                }
                else
                {
                    errorflag=0;
                    if(steam1.length()<1)
                    {
                        errorflag=1;
                        team1.setError("Invalid Teamsize");
                    }
                    else
                    {
                        errorflag=0;
                        if(sduration1.length()<1)
                        {
                            errorflag=1;
                            duration1.setError("Invalid Duration");
                        }
                        else
                        {
                            errorflag=0;
                            if(sproj2.length()<3) {
                                errorflag=1;
                                proj2.setError("Invalid Project Name");
                            }
                            else
                            {
                                errorflag=0;
                                if(sdomain2.length()<3)
                                {
                                    errorflag=1;
                                    domain2.setError("Invalid Domain");
                                }
                                else
                                {
                                    errorflag=0;
                                    if(steam2.length()<1)
                                    {
                                        errorflag=1;
                                        team2.setError("Invalid Teamsize");
                                    }
                                    else
                                    {
                                        errorflag=0;
                                        if(sduration2.length()<1)
                                        {
                                            errorflag=1;
                                            duration2.setError("Invalid Duration");
                                        }

                                    }
                                }
                            }
                        }

                    }
                }
            }

        }
        if(projectscount==2)
        {
            if(sproj3.length()<3) {
                errorflag=1;
                proj3.setError("Invalid Project Name");
            }
            else
            {
                errorflag=0;
                if(sdomain3.length()<3)
                {
                    errorflag=1;
                    domain3.setError("Invalid Domain");
                }
                else
                {
                    errorflag=0;
                    if(steam3.length()<1)
                    {
                        errorflag=1;
                        team3.setError("Invalid Teamsize");
                    }
                    else
                    {
                        errorflag=0;
                        if(sduration3.length()<1)
                        {
                            errorflag=1;
                            duration3.setError("Invalid Duration");
                        }

                    }
                }
            }
        }
        if(projectscount==3)
        {
            if(sproj4.length()<3) {
                errorflag=1;
                proj4.setError("Invalid Project Name");
            }
            else
            {
                errorflag=0;
                if(sdomain4.length()<3)
                {
                    errorflag=1;
                    domain4.setError("Invalid Domain");
                }
                else
                {
                    errorflag=0;
                    if(steam4.length()<1)
                    {
                        errorflag=1;
                        team4.setError("Invalid Teamsize");
                    }
                    else
                    {
                        errorflag=0;
                        if(sduration4.length()<1)
                        {
                            errorflag=1;
                            duration4.setError("Invalid Duration");
                        }

                    }
                }
            }
        }
        if(projectscount==4)
        {
            if(sproj5.length()<3) {
                errorflag=1;
                proj5.setError("Invalid Project Name");
            }
            else
            {
                errorflag=0;
                if(sdomain5.length()<3)
                {
                    errorflag=1;
                    domain5.setError("Invalid Domain");
                }
                else
                {
                    errorflag=0;
                    if(steam5.length()<1)
                    {
                        errorflag=1;
                        team5.setError("Invalid Teamsize");
                    }
                    else
                    {
                        errorflag=0;
                        if(sduration5.length()<1)
                        {
                            errorflag=1;
                            duration5.setError("Invalid Duration");
                        }

                    }
                }
            }
        }
        if(projectscount==5)
        {
            if(sproj6.length()<3) {
                errorflag=1;
                proj6.setError("Invalid Project Name");
            }
            else
            {
                errorflag=0;
                if(sdomain6.length()<3)
                {
                    errorflag=1;
                    domain6.setError("Invalid Domain");
                }
                else
                {
                    errorflag=0;
                    if(steam6.length()<1)
                    {
                        errorflag=1;
                        team6.setError("Invalid Teamsize");
                    }
                    else
                    {
                        errorflag=0;
                        if(sduration6.length()<1)
                        {
                            errorflag=1;
                            duration6.setError("Invalid Duration");
                        }

                    }
                }
            }
        }
        if(projectscount==6)
        {
            if(sproj7.length()<3) {
                errorflag=1;
                proj7.setError("Invalid Project Name");
            }
            else
            {
                errorflag=0;
                if(sdomain7.length()<3)
                {
                    errorflag=1;
                    domain7.setError("Invalid Domain");
                }
                else
                {
                    errorflag=0;
                    if(steam7.length()<1)
                    {
                        errorflag=1;
                        team7.setError("Invalid Teamsize");
                    }
                    else
                    {
                        errorflag=0;
                        if(sduration7.length()<1)
                        {
                            errorflag=1;
                            duration7.setError("Invalid Duration");
                        }

                    }
                }
            }
        }
        if(projectscount==7)
        {
            if(sproj8.length()<3) {
                errorflag=1;
                proj8.setError("Invalid Project Name");
            }
            else
            {
                errorflag=0;
                if(sdomain8.length()<3)
                {
                    errorflag=1;
                    domain8.setError("Invalid Domain");
                }
                else
                {
                    errorflag=0;
                    if(steam8.length()<1)
                    {
                        errorflag=1;
                        team8.setError("Invalid Teamsize");
                    }
                    else
                    {
                        errorflag=0;
                        if(sduration8.length()<1)
                        {
                            errorflag=1;
                            duration8.setError("Invalid Duration");
                        }

                    }
                }
            }
        }
        if(projectscount==8)
        {
            if(sproj9.length()<3) {
                errorflag=1;
                proj9.setError("Invalid Project Name");
            }
            else
            {
                errorflag=0;
                if(sdomain9.length()<3)
                {
                    errorflag=1;
                    domain9.setError("Invalid Domain");
                }
                else
                {
                    errorflag=0;
                    if(steam9.length()<1)
                    {
                        errorflag=1;
                        team9.setError("Invalid Teamsize");
                    }
                    else
                    {
                        errorflag=0;
                        if(sduration9.length()<1)
                        {
                            errorflag=1;
                            duration9.setError("Invalid Duration");
                        }

                    }
                }
            }
        }
        if(projectscount==9)
        {
            if(sproj10.length()<3) {
                errorflag=1;
                proj10.setError("Invalid Project Name");
            }
            else
            {
                errorflag=0;
                if(sdomain10.length()<3)
                {
                    errorflag=1;
                    domain10.setError("Invalid Domain");
                }
                else
                {
                    errorflag=0;
                    if(steam10.length()<1)
                    {
                        errorflag=1;
                        team10.setError("Invalid Teamsize");
                    }
                    else
                    {
                        errorflag=0;
                        if(sduration10.length()<1)
                        {
                            errorflag=1;
                            duration10.setError("Invalid Duration");
                        }

                    }
                }
            }
        }
        if(errorflag==0)
        {
            try
            {
                byte[] proj1Bytes = sproj1.getBytes("UTF-8");
                byte[] domain1Bytes = sdomain1.getBytes("UTF-8");
                byte[] team1Bytes = steam1.getBytes("UTF-8");
                byte[] duration1Bytes = sduration1.getBytes("UTF-8");

                byte[] proj1EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, proj1Bytes);
                encproj1=new String(SimpleBase64Encoder.encode(proj1EncryptedBytes));
                byte[] domain1EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, domain1Bytes);
                encdomain1=new String(SimpleBase64Encoder.encode(domain1EncryptedBytes));
                byte[] team1EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, team1Bytes);
                encteam1=new String(SimpleBase64Encoder.encode(team1EncryptedBytes));
                byte[] duration1EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, duration1Bytes);
                encduration1=new String(SimpleBase64Encoder.encode(duration1EncryptedBytes));

                byte[] proj2Bytes = sproj2.getBytes("UTF-8");
                byte[] domain2Bytes= sdomain2.getBytes("UTF-8");
                byte[] team2Bytes= steam2.getBytes("UTF-8");
                byte[] duration2Bytes= sduration2.getBytes("UTF-8");

                byte[] proj2EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, proj2Bytes);
                encproj2=new String(SimpleBase64Encoder.encode(proj2EncryptedBytes));
                byte[] domain2EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, domain2Bytes);
                encdomain2=new String(SimpleBase64Encoder.encode(domain2EncryptedBytes));
                byte[] team2EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, team2Bytes);
                encteam2=new String(SimpleBase64Encoder.encode(team2EncryptedBytes));
                byte[] duration2EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, duration2Bytes);
                encduration2=new String(SimpleBase64Encoder.encode(duration2EncryptedBytes));

                byte[] proj3Bytes = sproj3.getBytes("UTF-8");
                byte[] domain3Bytes= sdomain3.getBytes("UTF-8");
                byte[] team3Bytes= steam3.getBytes("UTF-8");
                byte[] duration3Bytes= sduration3.getBytes("UTF-8");

                byte[] proj3EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, proj3Bytes);
                encproj3=new String(SimpleBase64Encoder.encode(proj3EncryptedBytes));
                byte[] domain3EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, domain3Bytes);
                encdomain3=new String(SimpleBase64Encoder.encode(domain3EncryptedBytes));
                byte[] team3EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, team3Bytes);
                encteam3=new String(SimpleBase64Encoder.encode(team3EncryptedBytes));
                byte[] duration3EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, duration3Bytes);
                encduration3=new String(SimpleBase64Encoder.encode(duration3EncryptedBytes));

                byte[] proj4Bytes = sproj4.getBytes("UTF-8");
                byte[] domain4Bytes= sdomain4.getBytes("UTF-8");
                byte[] team4Bytes= steam4.getBytes("UTF-8");
                byte[] duration4Bytes= sduration4.getBytes("UTF-8");

                byte[] proj4EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, proj4Bytes);
                encproj4=new String(SimpleBase64Encoder.encode(proj4EncryptedBytes));
                byte[] domain4EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, domain4Bytes);
                encdomain4=new String(SimpleBase64Encoder.encode(domain4EncryptedBytes));
                byte[] team4EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, team4Bytes);
                encteam4=new String(SimpleBase64Encoder.encode(team4EncryptedBytes));
                byte[] duration4EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, duration4Bytes);
                encduration4=new String(SimpleBase64Encoder.encode(duration4EncryptedBytes));

                byte[] proj5Bytes = sproj5.getBytes("UTF-8");
                byte[] domain5Bytes= sdomain5.getBytes("UTF-8");
                byte[] team5Bytes= steam5.getBytes("UTF-8");
                byte[] duration5Bytes= sduration5.getBytes("UTF-8");

                byte[] proj5EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, proj5Bytes);
                encproj5=new String(SimpleBase64Encoder.encode(proj5EncryptedBytes));
                byte[] domain5EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, domain5Bytes);
                encdomain5=new String(SimpleBase64Encoder.encode(domain5EncryptedBytes));
                byte[] team5EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, team5Bytes);
                encteam5=new String(SimpleBase64Encoder.encode(team5EncryptedBytes));
                byte[] duration5EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, duration5Bytes);
                encduration5=new String(SimpleBase64Encoder.encode(duration5EncryptedBytes));

                byte[] proj6Bytes = sproj6.getBytes("UTF-8");
                byte[] domain6Bytes= sdomain6.getBytes("UTF-8");
                byte[] team6Bytes= steam6.getBytes("UTF-8");
                byte[] duration6Bytes= sduration6.getBytes("UTF-8");

                byte[] proj6EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, proj6Bytes);
                encproj6=new String(SimpleBase64Encoder.encode(proj6EncryptedBytes));
                byte[] domain6EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, domain6Bytes);
                encdomain6=new String(SimpleBase64Encoder.encode(domain6EncryptedBytes));
                byte[] team6EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, team6Bytes);
                encteam6=new String(SimpleBase64Encoder.encode(team6EncryptedBytes));
                byte[] duration6EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, duration6Bytes);
                encduration6=new String(SimpleBase64Encoder.encode(duration6EncryptedBytes));

                byte[] proj7Bytes = sproj7.getBytes("UTF-8");
                byte[] domain7Bytes= sdomain7.getBytes("UTF-8");
                byte[] team7Bytes= steam7.getBytes("UTF-8");
                byte[] duration7Bytes= sduration7.getBytes("UTF-8");

                byte[] proj7EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, proj7Bytes);
                encproj7=new String(SimpleBase64Encoder.encode(proj7EncryptedBytes));
                byte[] domain7EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, domain7Bytes);
                encdomain7=new String(SimpleBase64Encoder.encode(domain7EncryptedBytes));
                byte[] team7EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, team7Bytes);
                encteam7=new String(SimpleBase64Encoder.encode(team7EncryptedBytes));
                byte[] duration7EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, duration7Bytes);
                encduration7=new String(SimpleBase64Encoder.encode(duration7EncryptedBytes));

                byte[] proj8Bytes = sproj8.getBytes("UTF-8");
                byte[] domain8Bytes= sdomain8.getBytes("UTF-8");
                byte[] team8Bytes= steam8.getBytes("UTF-8");
                byte[] duration8Bytes= sduration8.getBytes("UTF-8");

                byte[] proj8EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, proj8Bytes);
                encproj8=new String(SimpleBase64Encoder.encode(proj8EncryptedBytes));
                byte[] domain8EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, domain8Bytes);
                encdomain8=new String(SimpleBase64Encoder.encode(domain8EncryptedBytes));
                byte[] team8EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, team8Bytes);
                encteam8=new String(SimpleBase64Encoder.encode(team8EncryptedBytes));
                byte[] duration8EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, duration8Bytes);
                encduration8=new String(SimpleBase64Encoder.encode(duration8EncryptedBytes));

                byte[] proj9Bytes = sproj9.getBytes("UTF-8");
                byte[] domain9Bytes= sdomain9.getBytes("UTF-8");
                byte[] team9Bytes= steam9.getBytes("UTF-8");
                byte[] duration9Bytes= sduration9.getBytes("UTF-8");

                byte[] proj9EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, proj9Bytes);
                encproj9=new String(SimpleBase64Encoder.encode(proj9EncryptedBytes));
                byte[] domain9EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, domain9Bytes);
                encdomain9=new String(SimpleBase64Encoder.encode(domain9EncryptedBytes));
                byte[] team9EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, team9Bytes);
                encteam9=new String(SimpleBase64Encoder.encode(team9EncryptedBytes));
                byte[] duration9EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, duration9Bytes);
                encduration9=new String(SimpleBase64Encoder.encode(duration9EncryptedBytes));

                byte[] proj10Bytes = sproj10.getBytes("UTF-8");
                byte[] domain10Bytes= sdomain10.getBytes("UTF-8");
                byte[] team10Bytes= steam10.getBytes("UTF-8");
                byte[] duration10Bytes= sduration10.getBytes("UTF-8");

                byte[] proj10EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, proj10Bytes);
                encproj10=new String(SimpleBase64Encoder.encode(proj10EncryptedBytes));
                byte[] domain10EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, domain10Bytes);
                encdomain10=new String(SimpleBase64Encoder.encode(domain10EncryptedBytes));
                byte[] team10EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, team10Bytes);
                encteam10=new String(SimpleBase64Encoder.encode(team10EncryptedBytes));
                byte[] duration10EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, duration10Bytes);
                encduration10=new String(SimpleBase64Encoder.encode(duration10EncryptedBytes));


                new SaveProjects().execute();

            }catch (Exception e){
                Toast.makeText(AlumniProfileProjects.this,e.getMessage(),Toast.LENGTH_LONG).show();}

        }
    }
    class SaveProjects extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r=null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u",username));       //0
            params.add(new BasicNameValuePair("p1",encproj1));      //1
            params.add(new BasicNameValuePair("do1",encdomain1));   //2
            params.add(new BasicNameValuePair("t1",encteam1));      //3
            params.add(new BasicNameValuePair("du1",encduration1)); //4
            params.add(new BasicNameValuePair("p2",encproj2));      //5
            params.add(new BasicNameValuePair("do2",encdomain2));   //6
            params.add(new BasicNameValuePair("t2",encteam2));      //7
            params.add(new BasicNameValuePair("du2",encduration2)); //8
            params.add(new BasicNameValuePair("p3",encproj3));      //9
            params.add(new BasicNameValuePair("do3",encdomain3));   //10
            params.add(new BasicNameValuePair("t3",encteam3));      //11
            params.add(new BasicNameValuePair("du3",encduration3)); //12
            params.add(new BasicNameValuePair("p4",encproj4));      //13
            params.add(new BasicNameValuePair("do4",encdomain4));   //14
            params.add(new BasicNameValuePair("t4",encteam4));      //15
            params.add(new BasicNameValuePair("du4",encduration4)); //16
            params.add(new BasicNameValuePair("p5",encproj5));      //17
            params.add(new BasicNameValuePair("do5",encdomain5));   //18
            params.add(new BasicNameValuePair("t5",encteam5));      //19
            params.add(new BasicNameValuePair("du5",encduration5)); //20
            params.add(new BasicNameValuePair("p6",encproj6));      //21
            params.add(new BasicNameValuePair("do6",encdomain6));   //22
            params.add(new BasicNameValuePair("t6",encteam6));      //23
            params.add(new BasicNameValuePair("du6",encduration6)); //24
            params.add(new BasicNameValuePair("p7",encproj7));      //25
            params.add(new BasicNameValuePair("do7",encdomain7));   //26
            params.add(new BasicNameValuePair("t7",encteam7));      //27
            params.add(new BasicNameValuePair("du7",encduration7)); //28
            params.add(new BasicNameValuePair("p8",encproj8));      //29
            params.add(new BasicNameValuePair("do8",encdomain8));   //30
            params.add(new BasicNameValuePair("t8",encteam8));      //31
            params.add(new BasicNameValuePair("du8",encduration8)); //32
            params.add(new BasicNameValuePair("p9",encproj9));      //33
            params.add(new BasicNameValuePair("do9",encdomain9));   //34
            params.add(new BasicNameValuePair("t9",encteam9));      //35
            params.add(new BasicNameValuePair("du9",encduration9)); //36
            params.add(new BasicNameValuePair("p10",encproj10));    //37
            params.add(new BasicNameValuePair("do10",encdomain10)); //38
            params.add(new BasicNameValuePair("t10",encteam10));    //39
            params.add(new BasicNameValuePair("du10",encduration10));//40

            json = jParser.makeHttpRequest(MyConstants.URL_SAVE_ALUMNI_PROJECTS, "GET", params);
            try {
                r = json.getString("info");


            }catch (Exception e){e.printStackTrace();}
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.equals("success"))
            {
                Toast.makeText(AlumniProfileProjects.this,"Successfully Saved..!",Toast.LENGTH_SHORT).show();
                setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);
                updateObject();
                AlumniProfileProjects.super.onBackPressed();
            }
        }
    }

    private void updateObject() {
        s.setProj1(sproj1);
        s.setDomain1(sdomain1);
        s.setTeam1(steam1);
        s.setDuration1(sduration1);
        s.setProj2(sproj2);
        s.setDomain2(sdomain2);
        s.setTeam2(steam2);
        s.setDuration2(sduration2);
        s.setProj3(sproj3);
        s.setDomain3(sdomain3);
        s.setTeam3(steam3);
        s.setDuration3(sduration3);
        s.setProj4(sproj4);
        s.setDomain4(sdomain4);
        s.setTeam4(steam4);
        s.setDuration4(sduration4);
        s.setProj5(sproj5);
        s.setDomain5(sdomain5);
        s.setTeam5(steam5);
        s.setDuration5(sduration5);
        s.setProj6(sproj6);
        s.setDomain6(sdomain6);
        s.setTeam6(steam6);
        s.setDuration6(sduration6);
        s.setProj7(sproj7);
        s.setDomain7(sdomain7);
        s.setTeam7(steam7);
        s.setDuration7(sduration7);
        s.setProj8(sproj8);
        s.setDomain8(sdomain8);
        s.setTeam8(steam8);
        s.setDuration8(sduration8);
        s.setProj9(sproj9);
        s.setDomain9(sdomain9);
        s.setTeam9(steam9);
        s.setDuration9(sduration9);
        s.setProj10(sproj10);
        s.setDomain10(sdomain10);
        s.setTeam10(steam10);
        s.setDuration10(sduration10);
    }

    void showDeletDialog()
    {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setMessage("Do you want to delete this project ?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                edittedFlag=1;
                                deleteProject();
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        final AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#282f35"));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#282f35"));
            }
        });

        alertDialog.show();
    }

    void deleteProject() {
        View v = (View) findViewById(R.id.projectline9);
        if (v.getVisibility() == View.VISIBLE) {

            View v1 = (View) findViewById(R.id.projectline9);
            v1.setVisibility(View.GONE);

            RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl10);
            projectrl.setVisibility(View.GONE);

            projectscount--;

            TextView t = (TextView) findViewById(R.id.addmoreprojecttxt);
            ImageView i = (ImageView) findViewById(R.id.addmoreprojectimg);
            addmoreproject.setVisibility(View.VISIBLE);
            t.setVisibility(View.VISIBLE);
            i.setVisibility(View.VISIBLE);
        } else {
            v = (View) findViewById(R.id.projectline8);
            if (v.getVisibility() == View.VISIBLE) {


                View v1 = (View) findViewById(R.id.projectline8);
                v1.setVisibility(View.GONE);

                RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl9);
                projectrl.setVisibility(View.GONE);

                projectscount--;

            } else {
                v = (View) findViewById(R.id.projectline7);
                if (v.getVisibility() == View.VISIBLE) {

                    View v1 = (View) findViewById(R.id.projectline7);
                    v1.setVisibility(View.GONE);

                    RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl8);
                    projectrl.setVisibility(View.GONE);

                    projectscount--;
                } else {
                    v = (View) findViewById(R.id.projectline6);
                    if (v.getVisibility() == View.VISIBLE) {

                        View v1 = (View) findViewById(R.id.projectline6);
                        v1.setVisibility(View.GONE);

                        RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl7);
                        projectrl.setVisibility(View.GONE);

                        projectscount--;

                    } else {
                        v = (View) findViewById(R.id.projectline5);
                        if (v.getVisibility() == View.VISIBLE) {

                            View v1 = (View) findViewById(R.id.projectline5);
                            v1.setVisibility(View.GONE);

                            RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl6);
                            projectrl.setVisibility(View.GONE);

                            projectscount--;


                        } else {
                            v = (View) findViewById(R.id.projectline4);
                            if (v.getVisibility() == View.VISIBLE) {

                                View v1 = (View) findViewById(R.id.projectline4);
                                v1.setVisibility(View.GONE);

                                RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl5);
                                projectrl.setVisibility(View.GONE);

                                projectscount--;
                            } else {
                                v = (View) findViewById(R.id.projectline3);
                                if (v.getVisibility() == View.VISIBLE) {

                                    View v1 = (View) findViewById(R.id.projectline3);
                                    v1.setVisibility(View.GONE);

                                    RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl4);
                                    projectrl.setVisibility(View.GONE);

                                    projectscount--;

                                } else {
                                    v = (View) findViewById(R.id.projectline2);
                                    if (v.getVisibility() == View.VISIBLE) {

                                        View v1 = (View) findViewById(R.id.projectline2);
                                        v1.setVisibility(View.GONE);

                                        RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl3);
                                        projectrl.setVisibility(View.GONE);

                                        projectscount--;

                                    } else {
                                        v = (View) findViewById(R.id.projectline1);
                                        if (v.getVisibility() == View.VISIBLE) {

                                            View v1 = (View) findViewById(R.id.projectline1);
                                            v1.setVisibility(View.GONE);

                                            RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl2);
                                            projectrl.setVisibility(View.GONE);

                                            projectscount--;


                                        }
                                        if (projectscount == 0) {
                                            proj1.setText("");
                                            domain1.setText("");
                                            team1.setText("");
                                            duration1.setText("");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
//        Toast.makeText(MyProfileProjects.this,"delete "+d,Toast.LENGTH_LONG).show();
        if (d == 10) {
            proj10.setText("");
            domain10.setText("");
            team10.setText("");
            duration10.setText("");
        } else if (d == 9) {
            sproj10 = proj10.getText().toString();
            sdomain10 = domain10.getText().toString();
            steam10 = team10.getText().toString();
            sduration10 = duration10.getText().toString();

            sproj9 = sproj10;
            sdomain9 = sdomain10;
            steam9 = steam10;
            sduration9 = sduration10;

            proj10.setText("");
            domain10.setText("");
            team10.setText("");
            duration10.setText("");

            proj9.setText(sproj9);
            domain9.setText(sdomain9);
            team9.setText(steam9);
            duration9.setText(sduration9);

        }
        else if(d==8)
        {
            sproj10=proj10.getText().toString();
            sdomain10=domain10.getText().toString();
            steam10=team10.getText().toString();
            sduration10=duration10.getText().toString();
            sproj9=proj9.getText().toString();
            sdomain9=domain9.getText().toString();
            steam9=team9.getText().toString();
            sduration9=duration9.getText().toString();


            sproj8=sproj9;
            sdomain8=sdomain9;
            steam8=steam9;
            sduration8=sduration9;

            proj9.setText("");
            domain9.setText("");
            team9.setText("");
            duration9.setText("");

            proj8.setText(sproj8);
            domain8.setText(sdomain8);
            team8.setText(steam8);
            duration8.setText(sduration8);

            sproj9=sproj10;
            sdomain9=sdomain10;
            steam9=steam10;
            sduration9=sduration10;

            proj10.setText("");
            domain10.setText("");
            team10.setText("");
            duration10.setText("");

            proj9.setText(sproj9);
            domain9.setText(sdomain9);
            team9.setText(steam9);
            duration9.setText(sduration9);

        }
        else if(d==7)
        {
            sproj10=proj10.getText().toString();
            sdomain10=domain10.getText().toString();
            steam10=team10.getText().toString();
            sduration10=duration10.getText().toString();
            sproj9=proj9.getText().toString();
            sdomain9=domain9.getText().toString();
            steam9=team9.getText().toString();
            sduration9=duration9.getText().toString();
            sproj8=proj8.getText().toString();
            sdomain8=domain8.getText().toString();
            steam8=team8.getText().toString();
            sduration8=duration8.getText().toString();


            sproj7=sproj8;
            sdomain7=sdomain8;
            steam7=steam8;
            sduration7=sduration8;

            proj8.setText("");
            domain8.setText("");
            team8.setText("");
            duration8.setText("");

            proj7.setText(sproj7);
            domain7.setText(sdomain7);
            team7.setText(steam7);
            duration7.setText(sduration7);

            sproj8=sproj9;
            sdomain8=sdomain9;
            steam8=steam9;
            sduration8=sduration9;

            proj9.setText("");
            domain9.setText("");
            team9.setText("");
            duration9.setText("");

            proj8.setText(sproj8);
            domain8.setText(sdomain8);
            team8.setText(steam8);
            duration8.setText(sduration8);

            sproj9=sproj10;
            sdomain9=sdomain10;
            steam9=steam10;
            sduration9=sduration10;

            proj10.setText("");
            domain10.setText("");
            team10.setText("");
            duration10.setText("");

            proj9.setText(sproj9);
            domain9.setText(sdomain9);
            team9.setText(steam9);
            duration9.setText(sduration9);

        }
        else if(d==6)
        {
            sproj10=proj10.getText().toString();
            sdomain10=domain10.getText().toString();
            steam10=team10.getText().toString();
            sduration10=duration10.getText().toString();
            sproj9=proj9.getText().toString();
            sdomain9=domain9.getText().toString();
            steam9=team9.getText().toString();
            sduration9=duration9.getText().toString();
            sproj8=proj8.getText().toString();
            sdomain8=domain8.getText().toString();
            steam8=team8.getText().toString();
            sduration8=duration8.getText().toString();
            sproj7=proj7.getText().toString();
            sdomain7=domain7.getText().toString();
            steam7=team7.getText().toString();
            sduration7=duration7.getText().toString();


            sproj6=sproj7;
            sdomain6=sdomain7;
            steam6=steam7;
            sduration6=sduration7;

            proj7.setText("");
            domain7.setText("");
            team7.setText("");
            duration7.setText("");

            proj6.setText(sproj6);
            domain6.setText(sdomain6);
            team6.setText(steam6);
            duration6.setText(sduration6);

            sproj7=sproj8;
            sdomain7=sdomain8;
            steam7=steam8;
            sduration7=sduration8;

            proj8.setText("");
            domain8.setText("");
            team8.setText("");
            duration8.setText("");

            proj7.setText(sproj7);
            domain7.setText(sdomain7);
            team7.setText(steam7);
            duration7.setText(sduration7);

            sproj8=sproj9;
            sdomain8=sdomain9;
            steam8=steam9;
            sduration8=sduration9;

            proj9.setText("");
            domain9.setText("");
            team9.setText("");
            duration9.setText("");

            proj8.setText(sproj8);
            domain8.setText(sdomain8);
            team8.setText(steam8);
            duration8.setText(sduration8);

            sproj9=sproj10;
            sdomain9=sdomain10;
            steam9=steam10;
            sduration9=sduration10;

            proj10.setText("");
            domain10.setText("");
            team10.setText("");
            duration10.setText("");

            proj9.setText(sproj9);
            domain9.setText(sdomain9);
            team9.setText(steam9);
            duration9.setText(sduration9);

        }
        else if(d==5)
        {
            sproj10=proj10.getText().toString();
            sdomain10=domain10.getText().toString();
            steam10=team10.getText().toString();
            sduration10=duration10.getText().toString();
            sproj9=proj9.getText().toString();
            sdomain9=domain9.getText().toString();
            steam9=team9.getText().toString();
            sduration9=duration9.getText().toString();
            sproj8=proj8.getText().toString();
            sdomain8=domain8.getText().toString();
            steam8=team8.getText().toString();
            sduration8=duration8.getText().toString();
            sproj7=proj7.getText().toString();
            sdomain7=domain7.getText().toString();
            steam7=team7.getText().toString();
            sduration7=duration7.getText().toString();
            sproj6=proj6.getText().toString();
            sdomain6=domain6.getText().toString();
            steam6=team6.getText().toString();
            sduration6=duration6.getText().toString();



            sproj5=sproj6;
            sdomain5=sdomain6;
            steam5=steam6;
            sduration5=sduration6;

            proj6.setText("");
            domain6.setText("");
            team6.setText("");
            duration6.setText("");

            proj5.setText(sproj5);
            domain5.setText(sdomain5);
            team5.setText(steam5);
            duration5.setText(sduration5);

            sproj6=sproj7;
            sdomain6=sdomain7;
            steam6=steam7;
            sduration6=sduration7;

            proj7.setText("");
            domain7.setText("");
            team7.setText("");
            duration7.setText("");

            proj6.setText(sproj6);
            domain6.setText(sdomain6);
            team6.setText(steam6);
            duration6.setText(sduration6);

            sproj7=sproj8;
            sdomain7=sdomain8;
            steam7=steam8;
            sduration7=sduration8;

            proj8.setText("");
            domain8.setText("");
            team8.setText("");
            duration8.setText("");

            proj7.setText(sproj7);
            domain7.setText(sdomain7);
            team7.setText(steam7);
            duration7.setText(sduration7);

            sproj8=sproj9;
            sdomain8=sdomain9;
            steam8=steam9;
            sduration8=sduration9;

            proj9.setText("");
            domain9.setText("");
            team9.setText("");
            duration9.setText("");

            proj8.setText(sproj8);
            domain8.setText(sdomain8);
            team8.setText(steam8);
            duration8.setText(sduration8);

            sproj9=sproj10;
            sdomain9=sdomain10;
            steam9=steam10;
            sduration9=sduration10;

            proj10.setText("");
            domain10.setText("");
            team10.setText("");
            duration10.setText("");

            proj9.setText(sproj9);
            domain9.setText(sdomain9);
            team9.setText(steam9);
            duration9.setText(sduration9);

        }
        else if(d==4)
        {
            sproj10=proj10.getText().toString();
            sdomain10=domain10.getText().toString();
            steam10=team10.getText().toString();
            sduration10=duration10.getText().toString();
            sproj9=proj9.getText().toString();
            sdomain9=domain9.getText().toString();
            steam9=team9.getText().toString();
            sduration9=duration9.getText().toString();
            sproj8=proj8.getText().toString();
            sdomain8=domain8.getText().toString();
            steam8=team8.getText().toString();
            sduration8=duration8.getText().toString();
            sproj7=proj7.getText().toString();
            sdomain7=domain7.getText().toString();
            steam7=team7.getText().toString();
            sduration7=duration7.getText().toString();
            sproj6=proj6.getText().toString();
            sdomain6=domain6.getText().toString();
            steam6=team6.getText().toString();
            sduration6=duration6.getText().toString();
            sproj5=proj5.getText().toString();
            sdomain5=domain5.getText().toString();
            steam5=team5.getText().toString();
            sduration5=duration5.getText().toString();



            sproj4=sproj5;
            sdomain4=sdomain5;
            steam4=steam5;
            sduration4=sduration5;

            proj5.setText("");
            domain5.setText("");
            team5.setText("");
            duration5.setText("");

            proj4.setText(sproj4);
            domain4.setText(sdomain4);
            team4.setText(steam4);
            duration4.setText(sduration4);

            sproj5=sproj6;
            sdomain5=sdomain6;
            steam5=steam6;
            sduration5=sduration6;

            proj6.setText("");
            domain6.setText("");
            team6.setText("");
            duration6.setText("");

            proj5.setText(sproj5);
            domain5.setText(sdomain5);
            team5.setText(steam5);
            duration5.setText(sduration5);

            sproj6=sproj7;
            sdomain6=sdomain7;
            steam6=steam7;
            sduration6=sduration7;

            proj7.setText("");
            domain7.setText("");
            team7.setText("");
            duration7.setText("");

            proj6.setText(sproj6);
            domain6.setText(sdomain6);
            team6.setText(steam6);
            duration6.setText(sduration6);

            sproj7=sproj8;
            sdomain7=sdomain8;
            steam7=steam8;
            sduration7=sduration8;

            proj8.setText("");
            domain8.setText("");
            team8.setText("");
            duration8.setText("");

            proj7.setText(sproj7);
            domain7.setText(sdomain7);
            team7.setText(steam7);
            duration7.setText(sduration7);

            sproj8=sproj9;
            sdomain8=sdomain9;
            steam8=steam9;
            sduration8=sduration9;

            proj9.setText("");
            domain9.setText("");
            team9.setText("");
            duration9.setText("");

            proj8.setText(sproj8);
            domain8.setText(sdomain8);
            team8.setText(steam8);
            duration8.setText(sduration8);

            sproj9=sproj10;
            sdomain9=sdomain10;
            steam9=steam10;
            sduration9=sduration10;

            proj10.setText("");
            domain10.setText("");
            team10.setText("");
            duration10.setText("");

            proj9.setText(sproj9);
            domain9.setText(sdomain9);
            team9.setText(steam9);
            duration9.setText(sduration9);

        }
        else if(d==3)
        {
            sproj10=proj10.getText().toString();
            sdomain10=domain10.getText().toString();
            steam10=team10.getText().toString();
            sduration10=duration10.getText().toString();
            sproj9=proj9.getText().toString();
            sdomain9=domain9.getText().toString();
            steam9=team9.getText().toString();
            sduration9=duration9.getText().toString();
            sproj8=proj8.getText().toString();
            sdomain8=domain8.getText().toString();
            steam8=team8.getText().toString();
            sduration8=duration8.getText().toString();
            sproj7=proj7.getText().toString();
            sdomain7=domain7.getText().toString();
            steam7=team7.getText().toString();
            sduration7=duration7.getText().toString();
            sproj6=proj6.getText().toString();
            sdomain6=domain6.getText().toString();
            steam6=team6.getText().toString();
            sduration6=duration6.getText().toString();
            sproj5=proj5.getText().toString();
            sdomain5=domain5.getText().toString();
            steam5=team5.getText().toString();
            sduration5=duration5.getText().toString();
            sproj4=proj4.getText().toString();
            sdomain4=domain4.getText().toString();
            steam4=team4.getText().toString();
            sduration4=duration4.getText().toString();


            sproj3=sproj4;
            sdomain3=sdomain4;
            steam3=steam4;
            sduration3=sduration4;

            proj4.setText("");
            domain4.setText("");
            team4.setText("");
            duration4.setText("");

            proj3.setText(sproj3);
            domain3.setText(sdomain3);
            team3.setText(steam3);
            duration3.setText(sduration3);

            sproj4=sproj5;
            sdomain4=sdomain5;
            steam4=steam5;
            sduration4=sduration5;

            proj5.setText("");
            domain5.setText("");
            team5.setText("");
            duration5.setText("");

            proj4.setText(sproj4);
            domain4.setText(sdomain4);
            team4.setText(steam4);
            duration4.setText(sduration4);

            sproj5=sproj6;
            sdomain5=sdomain6;
            steam5=steam6;
            sduration5=sduration6;

            proj6.setText("");
            domain6.setText("");
            team6.setText("");
            duration6.setText("");

            proj5.setText(sproj5);
            domain5.setText(sdomain5);
            team5.setText(steam5);
            duration5.setText(sduration5);

            sproj6=sproj7;
            sdomain6=sdomain7;
            steam6=steam7;
            sduration6=sduration7;

            proj7.setText("");
            domain7.setText("");
            team7.setText("");
            duration7.setText("");

            proj6.setText(sproj6);
            domain6.setText(sdomain6);
            team6.setText(steam6);
            duration6.setText(sduration6);

            sproj7=sproj8;
            sdomain7=sdomain8;
            steam7=steam8;
            sduration7=sduration8;

            proj8.setText("");
            domain8.setText("");
            team8.setText("");
            duration8.setText("");

            proj7.setText(sproj7);
            domain7.setText(sdomain7);
            team7.setText(steam7);
            duration7.setText(sduration7);

            sproj8=sproj9;
            sdomain8=sdomain9;
            steam8=steam9;
            sduration8=sduration9;

            proj9.setText("");
            domain9.setText("");
            team9.setText("");
            duration9.setText("");

            proj8.setText(sproj8);
            domain8.setText(sdomain8);
            team8.setText(steam8);
            duration8.setText(sduration8);

            sproj9=sproj10;
            sdomain9=sdomain10;
            steam9=steam10;
            sduration9=sduration10;

            proj10.setText("");
            domain10.setText("");
            team10.setText("");
            duration10.setText("");

            proj9.setText(sproj9);
            domain9.setText(sdomain9);
            team9.setText(steam9);
            duration9.setText(sduration9);

        }
        else if(d==2)
        {
            sproj10=proj10.getText().toString();
            sdomain10=domain10.getText().toString();
            steam10=team10.getText().toString();
            sduration10=duration10.getText().toString();
            sproj9=proj9.getText().toString();
            sdomain9=domain9.getText().toString();
            steam9=team9.getText().toString();
            sduration9=duration9.getText().toString();
            sproj8=proj8.getText().toString();
            sdomain8=domain8.getText().toString();
            steam8=team8.getText().toString();
            sduration8=duration8.getText().toString();
            sproj7=proj7.getText().toString();
            sdomain7=domain7.getText().toString();
            steam7=team7.getText().toString();
            sduration7=duration7.getText().toString();
            sproj6=proj6.getText().toString();
            sdomain6=domain6.getText().toString();
            steam6=team6.getText().toString();
            sduration6=duration6.getText().toString();
            sproj5=proj5.getText().toString();
            sdomain5=domain5.getText().toString();
            steam5=team5.getText().toString();
            sduration5=duration5.getText().toString();
            sproj4=proj4.getText().toString();
            sdomain4=domain4.getText().toString();
            steam4=team4.getText().toString();
            sduration4=duration4.getText().toString();
            sproj3=proj3.getText().toString();
            sdomain3=domain3.getText().toString();
            steam3=team3.getText().toString();
            sduration3=duration3.getText().toString();


            sproj2=sproj3;
            sdomain2=sdomain3;
            steam2=steam3;
            sduration2=sduration3;

            proj3.setText("");
            domain3.setText("");
            team3.setText("");
            duration3.setText("");

            proj2.setText(sproj2);
            domain2.setText(sdomain2);
            team2.setText(steam2);
            duration2.setText(sduration2);

            sproj3=sproj4;
            sdomain3=sdomain4;
            steam3=steam4;
            sduration3=sduration4;

            proj4.setText("");
            domain4.setText("");
            team4.setText("");
            duration4.setText("");

            proj3.setText(sproj3);
            domain3.setText(sdomain3);
            team3.setText(steam3);
            duration3.setText(sduration3);

            sproj4=sproj5;
            sdomain4=sdomain5;
            steam4=steam5;
            sduration4=sduration5;

            proj5.setText("");
            domain5.setText("");
            team5.setText("");
            duration5.setText("");

            proj4.setText(sproj4);
            domain4.setText(sdomain4);
            team4.setText(steam4);
            duration4.setText(sduration4);

            sproj5=sproj6;
            sdomain5=sdomain6;
            steam5=steam6;
            sduration5=sduration6;

            proj6.setText("");
            domain6.setText("");
            team6.setText("");
            duration6.setText("");

            proj5.setText(sproj5);
            domain5.setText(sdomain5);
            team5.setText(steam5);
            duration5.setText(sduration5);

            sproj6=sproj7;
            sdomain6=sdomain7;
            steam6=steam7;
            sduration6=sduration7;

            proj7.setText("");
            domain7.setText("");
            team7.setText("");
            duration7.setText("");

            proj6.setText(sproj6);
            domain6.setText(sdomain6);
            team6.setText(steam6);
            duration6.setText(sduration6);

            sproj7=sproj8;
            sdomain7=sdomain8;
            steam7=steam8;
            sduration7=sduration8;

            proj8.setText("");
            domain8.setText("");
            team8.setText("");
            duration8.setText("");

            proj7.setText(sproj7);
            domain7.setText(sdomain7);
            team7.setText(steam7);
            duration7.setText(sduration7);

            sproj8=sproj9;
            sdomain8=sdomain9;
            steam8=steam9;
            sduration8=sduration9;

            proj9.setText("");
            domain9.setText("");
            team9.setText("");
            duration9.setText("");

            proj8.setText(sproj8);
            domain8.setText(sdomain8);
            team8.setText(steam8);
            duration8.setText(sduration8);

            sproj9=sproj10;
            sdomain9=sdomain10;
            steam9=steam10;
            sduration9=sduration10;

            proj10.setText("");
            domain10.setText("");
            team10.setText("");
            duration10.setText("");

            proj9.setText(sproj9);
            domain9.setText(sdomain9);
            team9.setText(steam9);
            duration9.setText(sduration9);

        }
        else if(d==1)
        {
            sproj10=proj10.getText().toString();
            sdomain10=domain10.getText().toString();
            steam10=team10.getText().toString();
            sduration10=duration10.getText().toString();
            sproj9=proj9.getText().toString();
            sdomain9=domain9.getText().toString();
            steam9=team9.getText().toString();
            sduration9=duration9.getText().toString();
            sproj8=proj8.getText().toString();
            sdomain8=domain8.getText().toString();
            steam8=team8.getText().toString();
            sduration8=duration8.getText().toString();
            sproj7=proj7.getText().toString();
            sdomain7=domain7.getText().toString();
            steam7=team7.getText().toString();
            sduration7=duration7.getText().toString();
            sproj6=proj6.getText().toString();
            sdomain6=domain6.getText().toString();
            steam6=team6.getText().toString();
            sduration6=duration6.getText().toString();
            sproj5=proj5.getText().toString();
            sdomain5=domain5.getText().toString();
            steam5=team5.getText().toString();
            sduration5=duration5.getText().toString();
            sproj4=proj4.getText().toString();
            sdomain4=domain4.getText().toString();
            steam4=team4.getText().toString();
            sduration4=duration4.getText().toString();
            sproj3=proj3.getText().toString();
            sdomain3=domain3.getText().toString();
            steam3=team3.getText().toString();
            sduration3=duration3.getText().toString();
            sproj2=proj2.getText().toString();
            sdomain2=domain2.getText().toString();
            steam2=team2.getText().toString();
            sduration2=duration2.getText().toString();


            sproj1=sproj2;
            sdomain1=sdomain2;
            steam1=steam2;
            sduration1=sduration2;

            proj2.setText("");
            domain2.setText("");
            team2.setText("");
            duration2.setText("");

            proj1.setText(sproj1);
            domain1.setText(sdomain1);
            team1.setText(steam1);
            duration1.setText(sduration1);

            sproj2=sproj3;
            sdomain2=sdomain3;
            steam2=steam3;
            sduration2=sduration3;

            proj3.setText("");
            domain3.setText("");
            team3.setText("");
            duration3.setText("");

            proj2.setText(sproj2);
            domain2.setText(sdomain2);
            team2.setText(steam2);
            duration2.setText(sduration2);

            sproj3=sproj4;
            sdomain3=sdomain4;
            steam3=steam4;
            sduration3=sduration4;

            proj4.setText("");
            domain4.setText("");
            team4.setText("");
            duration4.setText("");

            proj3.setText(sproj3);
            domain3.setText(sdomain3);
            team3.setText(steam3);
            duration3.setText(sduration3);

            sproj4=sproj5;
            sdomain4=sdomain5;
            steam4=steam5;
            sduration4=sduration5;

            proj5.setText("");
            domain5.setText("");
            team5.setText("");
            duration5.setText("");

            proj4.setText(sproj4);
            domain4.setText(sdomain4);
            team4.setText(steam4);
            duration4.setText(sduration4);

            sproj5=sproj6;
            sdomain5=sdomain6;
            steam5=steam6;
            sduration5=sduration6;

            proj6.setText("");
            domain6.setText("");
            team6.setText("");
            duration6.setText("");

            proj5.setText(sproj5);
            domain5.setText(sdomain5);
            team5.setText(steam5);
            duration5.setText(sduration5);

            sproj6=sproj7;
            sdomain6=sdomain7;
            steam6=steam7;
            sduration6=sduration7;

            proj7.setText("");
            domain7.setText("");
            team7.setText("");
            duration7.setText("");

            proj6.setText(sproj6);
            domain6.setText(sdomain6);
            team6.setText(steam6);
            duration6.setText(sduration6);

            sproj7=sproj8;
            sdomain7=sdomain8;
            steam7=steam8;
            sduration7=sduration8;

            proj8.setText("");
            domain8.setText("");
            team8.setText("");
            duration8.setText("");

            proj7.setText(sproj7);
            domain7.setText(sdomain7);
            team7.setText(steam7);
            duration7.setText(sduration7);

            sproj8=sproj9;
            sdomain8=sdomain9;
            steam8=steam9;
            sduration8=sduration9;

            proj9.setText("");
            domain9.setText("");
            team9.setText("");
            duration9.setText("");

            proj8.setText(sproj8);
            domain8.setText(sdomain8);
            team8.setText(steam8);
            duration8.setText(sduration8);

            sproj9=sproj10;
            sdomain9=sdomain10;
            steam9=steam10;
            sduration9=sduration10;

            proj10.setText("");
            domain10.setText("");
            team10.setText("");
            duration10.setText("");

            proj9.setText(sproj9);
            domain9.setText(sdomain9);
            team9.setText(steam9);
            duration9.setText(sduration9);

        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_save:

                validateandSave();
                break;

            case android.R.id.home:

                onBackPressed();

                return(true);
        }

        return(super.onOptionsItemSelected(item));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.savemenu, menu);
        return super.onCreateOptionsMenu(menu);


    }
    @Override
    public void onBackPressed() {
        if(edittedFlag==1) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder
                    .setMessage("Do you want to discard changes ?")
                    .setCancelable(false)
                    .setPositiveButton("Discard",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    AlumniProfileProjects.super.onBackPressed();
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
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#282f35"));
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#282f35"));
                }
            });

            alertDialog.show();
        }else
            AlumniProfileProjects.super.onBackPressed();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        edittedFlag=1;
    }

    @Override
    public void afterTextChanged(Editable s) {

    }


}
