package placeme.octopusites.com.placeme;


import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.StringSignature;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import de.hdodenhof.circleimageview.CircleImageView;
import placeme.octopusites.com.placeme.modal.AdminContactDetailsModal;
import placeme.octopusites.com.placeme.modal.Certificates;
import placeme.octopusites.com.placeme.modal.Courses;
import placeme.octopusites.com.placeme.modal.Experiences;
import placeme.octopusites.com.placeme.modal.Honors;
import placeme.octopusites.com.placeme.modal.KnownLangs;
import placeme.octopusites.com.placeme.modal.Modelmyprofileintro;
import placeme.octopusites.com.placeme.modal.MyProfileCareerObjModal;
import placeme.octopusites.com.placeme.modal.MyProfileDiplomaModal;
import placeme.octopusites.com.placeme.modal.MyProfileLocationModal;
import placeme.octopusites.com.placeme.modal.MyProfilePersonal;
import placeme.octopusites.com.placeme.modal.MyProfileStrengthsModal;
import placeme.octopusites.com.placeme.modal.MyProfileTenthModal;
import placeme.octopusites.com.placeme.modal.MyProfileTwelthModal;
import placeme.octopusites.com.placeme.modal.MyProfileUgModal;
import placeme.octopusites.com.placeme.modal.MyProfileWeaknessesModal;
import placeme.octopusites.com.placeme.modal.Patents;
import placeme.octopusites.com.placeme.modal.PgSem;
import placeme.octopusites.com.placeme.modal.PgYear;
import placeme.octopusites.com.placeme.modal.Projects;
import placeme.octopusites.com.placeme.modal.Publications;
import placeme.octopusites.com.placeme.modal.Skills;

import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;
import static placeme.octopusites.com.placeme.AES4all.fromString;

public class MyProfileAlumniFragment extends Fragment {
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Username = "nameKey";
    public static final String alumniLog = "alumniLog";
    final static CharSequence[] items = {"Update Profile Picture", "Delete Profile Picture"};
    public String role = "", ucode = "";
    ImageView insti4, exp2, exp3, exp1 , gear2,gear3;
    CircleImageView myprofileimg;
    ImageButton iv_camera;
    ImageView introedit, eduedit, projectsedit, accomplishmentsedit, expedit, careeredit, contactedit;
    String dataobject = "", careerdataobject = "", strengthdataobject = "", weaknessesdataobject = "", locationpreferencesdataobject = "", tenthdataobject = "", ugdataobject = "", personaldataobject = "", contact_details_dataobject = "", twelthdataobject = "", diplomadataobject = "", experiencesataobject;
    String fname = "", lname = "", country = "", state = "", city = "";
    String marks = "", outof = "", percentage = "", schoolname10 = "", board10 = "", yearofpassing10 = "";
    String marks12 = "", outof12 = "", percentage12 = "", schoolname12 = "", board12 = "", stream12 = "", yearofpassing12 = "", markssem1diploma = "", outofsem1diploma = "", percentagesem1diploma = "", markssem2diploma = "", outofsem2diploma = "", percentagesem2diploma = "", markssem3diploma = "", outofsem3diploma = "", percentagesem3diploma = "", markssem4diploma = "", outofsem4diploma = "", percentagesem4diploma = "", markssem5diploma = "", outofsem5diploma = "", percentagesem5diploma = "", markssem6diploma = "", outofsem6diploma = "", percentagesem6diploma = "", aggregatediploma = "", coursediploma = "", streamdiploma = "", universitydiploma = "", collegenamediploma = "", yearofpassingdiploma = "";
    String markssem1ug = "", outofsem1ug = "", percentagesem1ug = "", markssem2ug = "", outofsem2ug = "", percentagesem2ug = "", markssem3ug = "", outofsem3ug = "", percentagesem3ug = "", markssem4ug = "", outofsem4ug = "", percentagesem4ug = "", markssem5ug = "", outofsem5ug = "", percentagesem5ug = "", markssem6ug = "", outofsem6ug = "", percentagesem6ug = "", markssem7ug = "", outofsem7ug = "", percentagesem7ug = "", markssem8ug = "", outofsem8ug = "", percentagesem8ug = "", aggregateug = "", courseug = "", streamug = "", universityug = "", collegenameug = "", yearofpassingug = "";
    String markssem1pgsem = "", outofsem1pgsem = "", percentagesem1pgsem = "", markssem2pgsem = "", outofsem2pgsem = "", percentagesem2pgsem = "", markssem3pgsem = "", outofsem3pgsem = "", percentagesem3pgsem = "", markssem4pgsem = "", outofsem4pgsem = "", percentagesem4pgsem = "", markssem5pgsem = "", outofsem5pgsem = "", percentagesem5pgsem = "", markssem6pgsem = "", outofsem6pgsem = "", percentagesem6pgsem = "", aggregatepgsem = "", coursepgsem = "", streampgsem = "", universitypgsem = "", collegenamepgsem = "", yearofpassingpgsem = "";
    String marksyear1pgyear = "", outofyear1pgyear = "", percentageyear1pgyear = "", marksyear2pgyear = "", outofyear2pgyear = "", percentageyear2pgyear = "", marksyear3pgyear = "", outofyear3pgyear = "", percentageyear3pgyear = "", aggregatepgyear = "", coursepgyear = "", streampgyear = "", universitypgyear = "", collegenamepgyear = "", yearofpassingpgyear = "";
    String nameasten = "", mothername = "", dob = "", gender = "", mothertongue = "", hobbies = "", bloodgroup = "", category = "", religion = "", caste = "", prn = "", paddrline1 = "", paddrline2 = "", paddrline3 = "", handicapped = "", sports = "", defenceex = "";
    String posts1 = "", posts2 = "", posts3 = "", posts4 = "", posts5 = "", posts6 = "", posts7 = "", posts8 = "", posts9 = "", posts10 = "";
    String inst1s1 = "", inst1s2 = "", inst1s3 = "", inst1s4 = "", inst1s5 = "", inst1s6 = "", inst1s7 = "", inst1s8 = "", inst1s9 = "", inst1s10 = "";
    String fromdates1 = "", todates1 = "", fromdates2 = "", todates2 = "", fromdates3 = "", todates3 = "", fromdates4 = "", todates4 = "", fromdates5 = "", todates5 = "", fromdates6 = "", todates6 = "", fromdates7 = "", todates7 = "", fromdates8 = "", todates8 = "", fromdates9 = "", todates9 = "", fromdates10 = "", todates10 = "";
    String username = "", resultofop = "";
    String digest1, digest2, plainusername = "";
    boolean hrinfobox1 = false, hrinfobox2 = false, hrinfobox3 = false;
    int found_exp = 0, found_personal = 0;
    int found_box1 = 0, found_tenth = 0, found_twelth = 0, found_diploma = 0, found_ug = 0, found_pgsem = 0, found_pgyear = 0;
    int found_intro_box = 0, found_tenth_box = 0;
    int proj_count = 0, exps_count = 0, lang_count = 0, cert_count = 0, courses_count = 0, skills_count = 0, patent_count = 0, public_count = 0, honor_count = 0, strength_count = 0, weakness_count = 0, location_count = 0;
    int percentProfile = 0;
    TextView instcodetxt,myprofilename, myprofilrole, myprofiledu, myprofilloc, myprofilemail, myprofilepercenttxt, editprofiletxt, eduboxtxt, projboxtxt, accomplishmentsboxtxt, careerboxtxt, contactboxtxt, myprofilecource, myprofilecource2, myprofilecource3;
    TextView myprofilecource4, myprofileproj1, myprofileproj2, myprofileproj3, acc1txt, acc2txt, acc3txt, acc4txt, acc5txt, acc6txt, acc7txt, careerobjtxt, strengthtxt, weaktxt, locpretxt, nametxt, mobiletxt, emailtxt, myprofileclgname, myprofileclgyearofpassing, myprofileclgname2, myprofileclgyearofpassing2, myprofileclgname3, myprofileclgname4, myprofileclgyearofpassing3, myprofileclgyearofpassing4, myprofiledomain1, myprofileduration1, myprofiledomain2, myprofileduration2, myprofiledomain3, myprofileduration3, careerobjtxttxt, strengthstxt, weaknessestxt, locationpreferences, contactaddr1, contactmobile, contactemail, myprofilepreview, acc1txttxt, acc2txttxt, acc3txttxt, acc4txttxt, acc5txttxt, acc6txttxt, acc7txttxt;
    TextView exp1txt, myprofileexpfromto, myprofileexp1name, myprofileexp2name, exp2txt, myprofileexpfromto2, myprofileexp3name, exp3txt, myprofileexpfromto3;
    TextView extraprojectscount, noexptxt,extraexpcount;
    JSONParser jParser = new JSONParser();
    JSONParser jParserlang = new JSONParser();
    JSONObject json, jsonlang;
    RelativeLayout edutab4, edutab1, edutab2, edutab3, noedutab;
    RelativeLayout box1, editprofilerl, exptab2, exptab3, exptab1;
    RelativeLayout noprojtab, projtab1, noexptab,projtab2,projtab3;
    View rootView;
    SwipeRefreshLayout swipe_refresh_layout;
    byte[] demoKeyBytes;
    byte[] demoIVBytes;
    String sPadding;
    StudentData studentData = new StudentData();
    HrData hrData = new HrData();
    AdminData a = new AdminData();
    HashMap<String, Integer> hashMap;
    String email2 = "", addressline1 = "", addressline2 = "", addressline3 = "", telephone = "", mobile2 = "", phone = "";
    String proj1 = "", domain1 = "", team1 = "", duration1 = "", proj2 = "", domain2 = "", team2 = "", duration2 = "", proj3 = "", domain3 = "", team3 = "", duration3 = "", proj4 = "", domain4 = "", team4 = "", duration4 = "", proj5 = "", domain5 = "", team5 = "", duration5 = "", proj6 = "", domain6 = "", team6 = "", duration6 = "", proj7 = "", domain7 = "", team7 = "", duration7 = "", proj8 = "", domain8 = "", team8 = "", duration8 = "", proj9 = "", domain9 = "", team9 = "", duration9 = "", proj10 = "", domain10 = "", team10 = "", duration10 = "";
    String strength1 = "", strength2 = "", strength3 = "", strength4 = "", strength5 = "", strength6 = "", strength7 = "", strength8 = "", strength9 = "", strength10 = "";
    String weak1 = "", weak2 = "", weak3 = "", weak4 = "", weak5 = "", weak6 = "", weak7 = "", weak8 = "", weak9 = "", weak10 = "";
    String location1 = "", location2 = "", location3 = "", location4 = "", location5 = "", careerobj = "";
    String lang1 = "", proficiency1 = "", lang2 = "", proficiency2 = "", lang3 = "", proficiency3 = "", lang4 = "", proficiency4 = "", lang5 = "", proficiency5 = "", lang6 = "", proficiency6 = "", lang7 = "", proficiency7 = "", lang8 = "", proficiency8 = "", lang9 = "", proficiency9 = "", lang10 = "", proficiency10 = "";
    String title1 = "", issuer1 = "", license1 = "", title2 = "", issuer2 = "", license2 = "", title3 = "", issuer3 = "", license3 = "", title4 = "", issuer4 = "", license4 = "", title5 = "", issuer5 = "", license5 = "", title6 = "", issuer6 = "", license6 = "", title7 = "", issuer7 = "", license7 = "", title8 = "", issuer8 = "", license8 = "", title9 = "", issuer9 = "", license9 = "", title10 = "", issuer10 = "", license10 = "", startdate1certificate = "", enddate1certificate = "", startdate2certificate = "", enddate2certificate = "", startdate3certificate = "", enddate3certificate = "", startdate4certificate = "", enddate4certificate = "", startdate5certificate = "", enddate5certificate = "", startdate6certificate = "", enddate6certificate = "", startdate7certificate = "", enddate7certificate = "", startdate8certificate = "", enddate8certificate = "", startdate9certificate = "", enddate9certificate = "", startdate10certificate = "", enddate10certificate = "", willexpire1certificate = "", willexpire2certificate = "", willexpire3certificate = "", willexpire4certificate = "", willexpire5certificate = "", willexpire6certificate = "", willexpire7certificate = "", willexpire8certificate = "", willexpire9certificate = "", willexpire10certificate = "";
    String skill1 = "", skill2 = "", skill3 = "", skill4 = "", skill5 = "", skill6 = "", skill7 = "", skill8 = "", skill9 = "", skill10 = "", skill11 = "", skill12 = "", skill13 = "", skill14 = "", skill15 = "", skill16 = "", skill17 = "", skill18 = "", skill19 = "", skill20 = "", sproficiency1 = "", sproficiency2 = "", sproficiency3 = "", sproficiency4 = "", sproficiency5 = "", sproficiency6 = "", sproficiency7 = "", sproficiency8 = "", sproficiency9 = "", sproficiency10 = "", sproficiency11 = "", sproficiency12 = "", sproficiency13 = "", sproficiency14 = "", sproficiency15 = "", sproficiency16 = "", sproficiency17 = "", sproficiency18 = "", sproficiency19 = "", sproficiency20 = "";
    String htitle1 = "", hissuer1 = "", hdescription1 = "", htitle2 = "", hissuer2 = "", hdescription2 = "", htitle3 = "", hissuer3 = "", hdescription3 = "", htitle4 = "", hissuer4 = "", hdescription4 = "", htitle5 = "", hissuer5 = "", hdescription5 = "", htitle6 = "", hissuer6 = "", hdescription6 = "", htitle7 = "", hissuer7 = "", hdescription7 = "", htitle8 = "", hissuer8 = "", hdescription8 = "", htitle9 = "", hissuer9 = "", hdescription9 = "", htitle10 = "", hissuer10 = "", hdescription10 = "", yearofhonor1 = "", yearofhonor2 = "", yearofhonor3 = "", yearofhonor4 = "", yearofhonor5 = "", yearofhonor6 = "", yearofhonor7 = "", yearofhonor8 = "", yearofhonor9 = "", yearofhonor10 = "";
    ;
    String ptitle1 = "", pappno1 = "", pinventor1 = "", pissue1 = "", pfiling1 = "", purl1 = "", pdescription1 = "", ptitle2 = "", pappno2 = "", pinventor2 = "", pissue2 = "", pfiling2 = "", purl2 = "", pdescription2 = "", ptitle3 = "", pappno3 = "", pinventor3 = "", pissue3 = "", pfiling3 = "", purl3 = "", pdescription3 = "", ptitle4 = "", pappno4 = "", pinventor4 = "", pissue4 = "", pfiling4 = "", purl4 = "", pdescription4 = "", ptitle5 = "", pappno5 = "", pinventor5 = "", pissue5 = "", pfiling5 = "", purl5 = "", pdescription5 = "", ptitle6 = "", pappno6 = "", pinventor6 = "", pissue6 = "", pfiling6 = "", purl6 = "", pdescription6 = "", ptitle7 = "", pappno7 = "", pinventor7 = "", pissue7 = "", pfiling7 = "", purl7 = "", pdescription7 = "", ptitle8 = "", pappno8 = "", pinventor8 = "", pissue8 = "", pfiling8 = "", purl8 = "", pdescription8 = "", ptitle9 = "", pappno9 = "", pinventor9 = "", pissue9 = "", pfiling9 = "", purl9 = "", pdescription9 = "", ptitle10 = "", pappno10 = "", pinventor10 = "", pissue10 = "", pfiling10 = "", purl10 = "", pdescription10 = "", pselectedcountry1 = "", pselectedcountry2 = "", pselectedcountry3 = "", pselectedcountry4 = "", pselectedcountry5 = "", pselectedcountry6 = "", pselectedcountry7 = "", pselectedcountry8 = "", pselectedcountry9 = "", pselectedcountry10 = "", issuedorpending1 = "", issuedorpending2 = "", issuedorpending3 = "", issuedorpending4 = "", issuedorpending5 = "", issuedorpending6 = "", issuedorpending7 = "", issuedorpending8 = "", issuedorpending9 = "", issuedorpending10 = "";
    String pubtitle1 = "", publication1 = "", author1 = "", puburl1 = "", pubdescription1 = "", pubtitle2 = "", publication2 = "", author2 = "", puburl2 = "", pubdescription2 = "", pubtitle3 = "", publication3 = "", author3 = "", puburl3 = "", pubdescription3 = "", pubtitle4 = "", publication4 = "", author4 = "", puburl4 = "", pubdescription4 = "", pubtitle5 = "", publication5 = "", author5 = "", puburl5 = "", pubdescription5 = "", pubtitle6 = "", publication6 = "", author6 = "", puburl6 = "", pubdescription6 = "", pubtitle7 = "", publication7 = "", author7 = "", puburl7 = "", pubdescription7 = "", pubtitle8 = "", publication8 = "", author8 = "", puburl8 = "", pubdescription8 = "", pubtitle9 = "", publication9 = "", author9 = "", puburl9 = "", pubdescription9 = "", pubtitle10 = "", publication10 = "", author10 = "", puburl10 = "", pubdescription10 = "", publicationdate1 = "", publicationdate2 = "", publicationdate3 = "", publicationdate4 = "", publicationdate5 = "", publicationdate6 = "", publicationdate7 = "", publicationdate8 = "", publicationdate9 = "", publicationdate10 = "";
    String course1 = "", inst1 = "", fromdate1 = "", todate1 = "", course2 = "", inst2 = "", fromdate2 = "", todate2 = "", course3 = "", inst3 = "", fromdate3 = "", todate3 = "", course4 = "", inst4 = "", fromdate4 = "", todate4 = "", course5 = "", inst5 = "", fromdate5 = "", todate5 = "", course6 = "", inst6 = "", fromdate6 = "", todate6 = "", course7 = "", inst7 = "", fromdate7 = "", todate7 = "", course8 = "", inst8 = "", fromdate8 = "", todate8 = "", course9 = "", inst9 = "", fromdate9 = "", todate9 = "", course10 = "", inst10 = "", fromdate10 = "", todate10 = "";
    int found_contact_details = 0, found_projects = 0;
    int found_certificates = 0, found_courses = 0, found_skills = 0, found_honors = 0, found_patents = 0, found_publications = 0, found_lang = 0, found_careerobj = 0, found_strengths = 0, found_weaknesses = 0, found_locationpreferences = 0;
    View box2;
    private String signature = "";
    private String mname = "";
    private ProgressBar profileprogress, updateProgress;

    RelativeLayout knownLanguagesRelativeLayout, CertificationsRelativeLayout, CoursesRelativeLayout, SkillsRelativeLayout, HonorsRelativeLayout, PatentsRelativeLayout, PublicationsRelativeLayout;
    RelativeLayout CareerObjectiveRelativeLayout, StrengthsRelativeLayout, WeaknessesRelativeLayout, LocationPreferencesRelativeLayout;
    public MyProfileAlumniFragment() {
    }

    public void bottomupbox2(Activity activity, View view) {
        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.bottom_up_box2);
        view.startAnimation(animation1);
        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                profileprogress.setVisibility(View.VISIBLE);
                View box2section = rootView.findViewById(R.id.box2section);
                box2section.setVisibility(View.VISIBLE);
                editprofiletxt.setVisibility(View.VISIBLE);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_my_profile_alumni, container, false);

        box1 = (RelativeLayout) rootView.findViewById(R.id.box1);
        box2 = rootView.findViewById(R.id.box2);
        ImageView box2pencil = (ImageView) rootView.findViewById(R.id.box2pencil);
        username = MySharedPreferencesManager.getUsername(getActivity());
        String pass = MySharedPreferencesManager.getPassword(getActivity());
        digest1 = MySharedPreferencesManager.getDigest1(getActivity());
        digest2 = MySharedPreferencesManager.getDigest2(getActivity());
        role = MySharedPreferencesManager.getRole(getActivity());
        TextView noedudetailstxt = (TextView) rootView.findViewById(R.id.noedudetailstxt);
        TextView nomyprofileproj = (TextView) rootView.findViewById(R.id.nomyprofileproj);
        instcodetxt = (TextView) rootView.findViewById(R.id.instcodetxt);
        myprofileimg = (CircleImageView) rootView.findViewById(R.id.myprofileimg);
        iv_camera = (ImageButton) rootView.findViewById(R.id.iv_camera);
        myprofilename = (TextView) rootView.findViewById(R.id.myprofilename);
        myprofilrole = (TextView) rootView.findViewById(R.id.myprofilrole);
        myprofiledu = (TextView) rootView.findViewById(R.id.myprofiledu);
        myprofilloc = (TextView) rootView.findViewById(R.id.myprofilloc);
        myprofilemail = (TextView) rootView.findViewById(R.id.myprofilemail);
        myprofilepercenttxt = (TextView) rootView.findViewById(R.id.myprofilepercenttxt);
        editprofiletxt = (TextView) rootView.findViewById(R.id.editprofiletxt);
        eduboxtxt = (TextView) rootView.findViewById(R.id.eduboxtxt);
        projboxtxt = (TextView) rootView.findViewById(R.id.projboxtxt);
        accomplishmentsboxtxt = (TextView) rootView.findViewById(R.id.accomplishmentsboxtxt);
        careerboxtxt = (TextView) rootView.findViewById(R.id.careerboxtxt);
        contactboxtxt = (TextView) rootView.findViewById(R.id.contactboxtxt);
        myprofilecource = (TextView) rootView.findViewById(R.id.myprofilecource);
        myprofilecource2 = (TextView) rootView.findViewById(R.id.myprofilecource2);
        myprofilecource3 = (TextView) rootView.findViewById(R.id.myprofilecource3);
        myprofileproj1 = (TextView) rootView.findViewById(R.id.myprofileproj1);
        myprofileproj2 = (TextView) rootView.findViewById(R.id.myprofileproj2);
        myprofileproj3 = (TextView) rootView.findViewById(R.id.myprofileproj3);
        myprofilecource4 = (TextView) rootView.findViewById(R.id.myprofilecource4);
        acc1txt = (TextView) rootView.findViewById(R.id.acc1txt);
        acc2txt = (TextView) rootView.findViewById(R.id.acc2txt);
        acc3txt = (TextView) rootView.findViewById(R.id.acc3txt);
        acc4txt = (TextView) rootView.findViewById(R.id.acc4txt);
        acc5txt = (TextView) rootView.findViewById(R.id.acc5txt);
        acc6txt = (TextView) rootView.findViewById(R.id.acc6txt);
        acc7txt = (TextView) rootView.findViewById(R.id.acc7txt);
        acc1txttxt = (TextView) rootView.findViewById(R.id.acc1txttxt);
        acc2txttxt = (TextView) rootView.findViewById(R.id.acc2txttxt);
        acc3txttxt = (TextView) rootView.findViewById(R.id.acc3txttxt);
        acc4txttxt = (TextView) rootView.findViewById(R.id.acc4txttxt);
        acc5txttxt = (TextView) rootView.findViewById(R.id.acc5txttxt);
        acc6txttxt = (TextView) rootView.findViewById(R.id.acc6txttxt);
        acc7txttxt = (TextView) rootView.findViewById(R.id.acc7txttxt);
        careerobjtxt = (TextView) rootView.findViewById(R.id.careerobjtxt);
        strengthtxt = (TextView) rootView.findViewById(R.id.strengthtxt);
        weaktxt = (TextView) rootView.findViewById(R.id.weaktxt);
        locpretxt = (TextView) rootView.findViewById(R.id.locpretxt);
        nametxt = (TextView) rootView.findViewById(R.id.nametxt);
        mobiletxt = (TextView) rootView.findViewById(R.id.mobiletxt);
        emailtxt = (TextView) rootView.findViewById(R.id.emailtxt);
        myprofileclgname = (TextView) rootView.findViewById(R.id.myprofileclgname);
        myprofileclgyearofpassing = (TextView) rootView.findViewById(R.id.myprofileclgyearofpassing);
        myprofileclgname2 = (TextView) rootView.findViewById(R.id.myprofileclgname2);
        myprofileclgyearofpassing2 = (TextView) rootView.findViewById(R.id.myprofileclgyearofpassing2);
        myprofileclgname3 = (TextView) rootView.findViewById(R.id.myprofileclgname3);
        myprofileclgyearofpassing3 = (TextView) rootView.findViewById(R.id.myprofileclgyearofpassing3);
        myprofiledomain1 = (TextView) rootView.findViewById(R.id.myprofiledomain1);
        myprofileduration1 = (TextView) rootView.findViewById(R.id.myprofileduration1);
        myprofiledomain2 = (TextView) rootView.findViewById(R.id.myprofiledomain2);
        myprofileduration2 = (TextView) rootView.findViewById(R.id.myprofileduration2);
        myprofiledomain3 = (TextView) rootView.findViewById(R.id.myprofiledomain3);
        myprofileduration3 = (TextView) rootView.findViewById(R.id.myprofileduration3);
        careerobjtxttxt = (TextView) rootView.findViewById(R.id.careerobj);
        strengthstxt = (TextView) rootView.findViewById(R.id.strengths);
        weaknessestxt = (TextView) rootView.findViewById(R.id.weaknesses);
        locationpreferences = (TextView) rootView.findViewById(R.id.locationpreferences);
        contactaddr1 = (TextView) rootView.findViewById(R.id.contactaddr);
        contactmobile = (TextView) rootView.findViewById(R.id.contactmobile);
        contactemail = (TextView) rootView.findViewById(R.id.contactemail);
        myprofilepreview = (TextView) rootView.findViewById(R.id.myprofilepreview);
        extraprojectscount = (TextView) rootView.findViewById(R.id.extraprojectscount);
        extraexpcount = (TextView) rootView.findViewById(R.id.extraexpcount);
        noexptxt= (TextView) rootView.findViewById(R.id.noexptxt);
        insti4 = (ImageView) rootView.findViewById(R.id.insti4);
        edutab2 = (RelativeLayout) rootView.findViewById(R.id.edutab2);
        edutab1 = (RelativeLayout) rootView.findViewById(R.id.edutab1);
        noedutab = (RelativeLayout) rootView.findViewById(R.id.noedutab);
        edutab3 = (RelativeLayout) rootView.findViewById(R.id.edutab3);
        edutab4 = (RelativeLayout) rootView.findViewById(R.id.edutab4);
        projtab1 = (RelativeLayout) rootView.findViewById(R.id.projtab1);
        noprojtab = (RelativeLayout) rootView.findViewById(R.id.noprojtab);
        noexptab = (RelativeLayout) rootView.findViewById(R.id.noexptab);
        exptab1 = (RelativeLayout) rootView.findViewById(R.id.exptab1);
        exptab2 = (RelativeLayout) rootView.findViewById(R.id.exptab2);
        exptab3 = (RelativeLayout) rootView.findViewById(R.id.exptab3);
        exp1 = (ImageView) rootView.findViewById(R.id.exp1);
        exp2 = (ImageView) rootView.findViewById(R.id.exp2);
        exp3 = (ImageView) rootView.findViewById(R.id.exp3);
        TextView expboxtxt = (TextView) rootView.findViewById(R.id.expboxtxt);
        swipe_refresh_layout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
        SwipeRefreshLayout tswipe_refresh_layout = (SwipeRefreshLayout) getActivity().findViewById(R.id.swipe_refresh_layout);
        tswipe_refresh_layout.setVisibility(View.GONE);
        exp1txt = (TextView) rootView.findViewById(R.id.exp1txt);
        myprofileexp1name = (TextView) rootView.findViewById(R.id.myprofileexp1name);
        myprofileexpfromto = (TextView) rootView.findViewById(R.id.myprofileexpfromto);
        exp2txt = (TextView) rootView.findViewById(R.id.exp2txt);
        myprofileexp2name = (TextView) rootView.findViewById(R.id.myprofileexp2name);
        myprofileexpfromto2 = (TextView) rootView.findViewById(R.id.myprofileexpfromto1);
        exp3txt = (TextView) rootView.findViewById(R.id.exp3txt);
        myprofileexp3name = (TextView) rootView.findViewById(R.id.myprofileexp3name);
        myprofileexpfromto3 = (TextView) rootView.findViewById(R.id.myprofileexpfromto2);
        myprofileclgname4 = (TextView) rootView.findViewById(R.id.myprofileclgname4);
        myprofileclgyearofpassing4 = (TextView) rootView.findViewById(R.id.myprofileclgyearofpassing4);

        instcodetxt.setTypeface(Z.getLight(getActivity()));
        instcodetxt.setText("Institute Code");
        noexptxt.setTypeface(Z.getBold(getActivity()));
        exp1txt.setTypeface(Z.getBold(getActivity()));
        myprofileexp1name.setTypeface(Z.getLight(getActivity()));
        myprofileexpfromto.setTypeface(Z.getLight(getActivity()));
        exp2txt.setTypeface(Z.getBold(getActivity()));
        myprofileexp2name.setTypeface(Z.getLight(getActivity()));
        myprofileexpfromto2.setTypeface(Z.getLight(getActivity()));
        exp3txt.setTypeface(Z.getBold(getActivity()));
        myprofileexp3name.setTypeface(Z.getLight(getActivity()));
        myprofileexpfromto3.setTypeface(Z.getLight(getActivity()));
        myprofilename.setTypeface(Z.getBold(getActivity()));
        myprofilrole.setTypeface(Z.getBold(getActivity()));
        myprofiledu.setTypeface(Z.getBold(getActivity()));
        myprofilloc.setTypeface(Z.getLight(getActivity()));
        myprofilemail.setTypeface(Z.getLight(getActivity()));
        myprofilepercenttxt.setTypeface(Z.getItalic(getActivity()));
        editprofiletxt.setTypeface(Z.getBold(getActivity()));
        eduboxtxt.setTypeface(Z.getBold(getActivity()));
        projboxtxt.setTypeface(Z.getBold(getActivity()));
        accomplishmentsboxtxt.setTypeface(Z.getBold(getActivity()));
        careerboxtxt.setTypeface(Z.getBold(getActivity()));
        contactboxtxt.setTypeface(Z.getBold(getActivity()));
        expboxtxt.setTypeface(Z.getBold(getActivity()));
        myprofilecource.setTypeface(Z.getBold(getActivity()));
        myprofilecource2.setTypeface(Z.getBold(getActivity()));
        myprofilecource3.setTypeface(Z.getBold(getActivity()));
        myprofilecource4.setTypeface(Z.getBold(getActivity()));
        myprofileproj1.setTypeface(Z.getBold(getActivity()));
        myprofileproj2.setTypeface(Z.getBold(getActivity()));
        myprofileproj3.setTypeface(Z.getBold(getActivity()));
        acc1txt.setTypeface(Z.getLight(getActivity()));
        acc2txt.setTypeface(Z.getLight(getActivity()));
        acc3txt.setTypeface(Z.getLight(getActivity()));
        acc4txt.setTypeface(Z.getLight(getActivity()));
        acc5txt.setTypeface(Z.getLight(getActivity()));
        acc6txt.setTypeface(Z.getLight(getActivity()));
        acc7txt.setTypeface(Z.getLight(getActivity()));
        careerobjtxt.setTypeface(Z.getBold(getActivity()));
        strengthtxt.setTypeface(Z.getBold(getActivity()));
        weaktxt.setTypeface(Z.getBold(getActivity()));
        locpretxt.setTypeface(Z.getBold(getActivity()));
        nametxt.setTypeface(Z.getBold(getActivity()));
        mobiletxt.setTypeface(Z.getLight(getActivity()));
        emailtxt.setTypeface(Z.getLight(getActivity()));
        extraexpcount.setTypeface(Z.getLight(getActivity()));
        extraprojectscount.setTypeface(Z.getLight(getActivity()));
        myprofileclgname.setTypeface(Z.getLight(getActivity()));
        myprofileclgyearofpassing.setTypeface(Z.getLight(getActivity()));
        myprofileclgname2.setTypeface(Z.getLight(getActivity()));
        myprofileclgyearofpassing2.setTypeface(Z.getLight(getActivity()));
        myprofileclgname3.setTypeface(Z.getLight(getActivity()));
        myprofileclgyearofpassing3.setTypeface(Z.getLight(getActivity()));
        myprofileclgname4.setTypeface(Z.getLight(getActivity()));
        myprofileclgyearofpassing4.setTypeface(Z.getLight(getActivity()));
        myprofiledomain1.setTypeface(Z.getLight(getActivity()));
        myprofileduration1.setTypeface(Z.getLight(getActivity()));
        myprofiledomain2.setTypeface(Z.getLight(getActivity()));
        myprofileduration2.setTypeface(Z.getLight(getActivity()));
        myprofiledomain3.setTypeface(Z.getLight(getActivity()));
        myprofileduration3.setTypeface(Z.getLight(getActivity()));
        careerobjtxttxt.setTypeface(Z.getLight(getActivity()));
        strengthstxt.setTypeface(Z.getLight(getActivity()));
        weaknessestxt.setTypeface(Z.getLight(getActivity()));
        locationpreferences.setTypeface(Z.getLight(getActivity()));
        contactaddr1.setTypeface(Z.getLight(getActivity()));
        acc1txttxt.setTypeface(Z.getBold(getActivity()));
        acc2txttxt.setTypeface(Z.getBold(getActivity()));
        acc3txttxt.setTypeface(Z.getBold(getActivity()));
        acc4txttxt.setTypeface(Z.getBold(getActivity()));
        acc5txttxt.setTypeface(Z.getBold(getActivity()));
        acc6txttxt.setTypeface(Z.getBold(getActivity()));
        acc7txttxt.setTypeface(Z.getBold(getActivity()));
        noedudetailstxt.setTypeface(Z.getBold(getActivity()));
        nomyprofileproj.setTypeface(Z.getBold(getActivity()));
        contactmobile.setTypeface(Z.getBold(getActivity()));
        contactemail.setTypeface(Z.getBold(getActivity()));
        myprofilepreview.setTypeface(Z.getBold(getActivity()));
        introedit = (ImageView) rootView.findViewById(R.id.introedit);
        eduedit = (ImageView) rootView.findViewById(R.id.eduedit);
        projectsedit = (ImageView) rootView.findViewById(R.id.projectsedit);
        accomplishmentsedit = (ImageView) rootView.findViewById(R.id.accomplishmentsedit);
        expedit = (ImageView) rootView.findViewById(R.id.expedit);
        careeredit = (ImageView) rootView.findViewById(R.id.careeredit);
        contactedit = (ImageView) rootView.findViewById(R.id.contactedit);
        profileprogress = (ProgressBar) rootView.findViewById(R.id.profileprogress);
        updateProgress = (ProgressBar) rootView.findViewById(R.id.updateProgress);

        knownLanguagesRelativeLayout = (RelativeLayout) rootView.findViewById(R.id.acctab1);
        CertificationsRelativeLayout = (RelativeLayout) rootView.findViewById(R.id.acctab2);
        CoursesRelativeLayout = (RelativeLayout) rootView.findViewById(R.id.acctab3);
        SkillsRelativeLayout = (RelativeLayout) rootView.findViewById(R.id.acctab4);
        HonorsRelativeLayout = (RelativeLayout) rootView.findViewById(R.id.acctab5);
        PatentsRelativeLayout = (RelativeLayout) rootView.findViewById(R.id.acctab6);
        PublicationsRelativeLayout = (RelativeLayout) rootView.findViewById(R.id.acctab7);

        CareerObjectiveRelativeLayout = (RelativeLayout) rootView.findViewById(R.id.careertab1);
        StrengthsRelativeLayout = (RelativeLayout) rootView.findViewById(R.id.careertab2);
        WeaknessesRelativeLayout = (RelativeLayout) rootView.findViewById(R.id.careertab3);
        LocationPreferencesRelativeLayout = (RelativeLayout) rootView.findViewById(R.id.careertab4);


        knownLanguagesRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), MyProfileKnownLang.class).putExtra("username", username), 0);
            }
        });

        CertificationsRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), MyProfileCertifications.class).putExtra("username", username), 0);
            }
        });

        CoursesRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), MyProfileCourses.class).putExtra("username", username), 0);
            }
        });

        SkillsRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), MyProfileSkills.class).putExtra("username", username), 0);
            }
        });

        HonorsRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), MyProfileAchievements.class).putExtra("username", username), 0);
            }
        });


        PatentsRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), MyProfilePatents.class).putExtra("username", username), 0);
            }
        });


        PublicationsRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), MyProfilePublications.class).putExtra("username", username), 0);
            }
        });


        CareerObjectiveRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), MyProfileCareerObj.class).putExtra("username", username), 0);
            }
        });


        StrengthsRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), MyProfileStrengths.class).putExtra("username", username), 0);
            }
        });


        WeaknessesRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), MyProfileWeaknesses.class).putExtra("username", username), 0);
            }
        });

        LocationPreferencesRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), MyProfileLocationPreferences.class).putExtra("username", username), 0);
            }
        });




        if (!ShouldAnimateProfile.shouldAnimate) {
            Z.bottomupbox1(getActivity(), box1);
            bottomupbox2(getActivity(), box2);
            Z.bottomupbox4(getActivity(), edutab4);
            Z.fade(getActivity(), myprofileimg);
            Z.fade(getActivity(), iv_camera);
            Z.bottomupbox3(getActivity(), eduboxtxt);
            Z.bottomupbox3(getActivity(), box2pencil);
            Z.fadeandmovedown(getActivity(), myprofilepreview);
            ShouldAnimateProfile.shouldAnimate = true;
        } else {
            profileprogress.setVisibility(View.VISIBLE);
            View box2section = rootView.findViewById(R.id.box2section);
            box2section.setVisibility(View.VISIBLE);
            editprofiletxt.setVisibility(View.VISIBLE);
        }
        demoKeyBytes = SimpleBase64Encoder.decode(digest1);
        demoIVBytes = SimpleBase64Encoder.decode(digest2);
        sPadding = "ISO10126Padding";
        try {
            byte[] usernameEncryptedBytes = SimpleBase64Encoder.decode(username);
            byte[] usernameDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, usernameEncryptedBytes);
            plainusername = new String(usernameDecryptedBytes);
            myprofilemail.setText(plainusername);
            myprofilrole.setText(role.toUpperCase());
            contactemail.setText(plainusername);
        } catch (Exception e) {
            Log.d("cricket", "Match 1 lost : " + e.getMessage());
            e.printStackTrace();
        }

        introedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), MyProfileIntro.class), 0);
            }
        });
        eduedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), MyProfileEdu.class), 0);
            }
        });
        projectsedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), MyProfileProjects.class), 0);
            }
        });
        accomplishmentsedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), MyProfileAccomplishments.class), 0);
            }
        });
        expedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), AdminExperiences.class), 0);
            }
        });
        careeredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), MyProfileCareerDetails.class), 0);
            }
        });
        contactedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), MyProfileContact.class), 0);
            }
        });
        myprofileimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ViewProfileImage.class));
            }
        });
        iv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        editprofilerl = (RelativeLayout) rootView.findViewById(R.id.editprofilerl);
        editprofilerl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), EditProfileAlumni.class), 0);
            }
        });
        hashMap = new HashMap<>();
        hashMap.put("Jan", 1);
        hashMap.put("Feb", 2);
        hashMap.put("Mar", 3);
        hashMap.put("Apr", 4);
        hashMap.put("May", 5);
        hashMap.put("Jun", 6);
        hashMap.put("Jul", 7);
        hashMap.put("Aug", 8);
        hashMap.put("Sep", 9);
        hashMap.put("Oct", 10);
        hashMap.put("Nov", 11);
        hashMap.put("Dec", 12);
        swipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new GetAlumniData().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                ((AlumniActivity) getActivity()).requestProfileImage();
            }
        });
        refreshContent();

        Log.d("cricket", "virender sehwag opening to bat");
        return rootView;
    }
    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        setHasOptionsMenu(true);
    }
    @Override
    public void onPrepareOptionsMenu(final Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.clear();
    }
    public void setVisibilityExpbox() {
        int exp_count = 0;
        if (!fromdates1.equals(""))
            exp_count++;
        if (!fromdates2.equals(""))
            exp_count++;
        if (!fromdates3.equals(""))
            exp_count++;
        if (!fromdates4.equals(""))
            exp_count++;
        if (!fromdates5.equals(""))
            exp_count++;
        if (!fromdates6.equals(""))
            exp_count++;
        if (!fromdates7.equals(""))
            exp_count++;
        if (!fromdates8.equals(""))
            exp_count++;
        if (!fromdates9.equals(""))
            exp_count++;
        if (!fromdates10.equals(""))
            exp_count++;
        if (exp_count > 3) {
            extraexpcount.setVisibility(View.VISIBLE);
            extraexpcount.setText("and " + exps_count + " more");
        }
        else
            extraexpcount.setVisibility(View.GONE);
        if (!fromdates1.equals("")) {
            exptab1.setVisibility(View.VISIBLE);
            exp1.setVisibility(View.VISIBLE);
            noexptab.setVisibility(View.GONE);
            exptab2.setVisibility(View.GONE);
            exp2.setVisibility(View.GONE);
            exptab3.setVisibility(View.GONE);
            exp3.setVisibility(View.GONE);
        }
        else {
            exptab1.setVisibility(View.GONE);
            exp1.setVisibility(View.GONE);
            noexptab.setVisibility(View.VISIBLE);
            exptab2.setVisibility(View.GONE);
            exp2.setVisibility(View.GONE);
            exptab3.setVisibility(View.GONE);
            exp3.setVisibility(View.GONE);
        }
        if (!fromdates2.equals("")) {
            exptab2.setVisibility(View.VISIBLE);
            exp2.setVisibility(View.VISIBLE);
            noexptab.setVisibility(View.GONE);
            exptab3.setVisibility(View.GONE);
            exp3.setVisibility(View.GONE);
        }
        if (!fromdates3.equals("")) {
            exptab3.setVisibility(View.VISIBLE);
            exp3.setVisibility(View.VISIBLE);
            noexptab.setVisibility(View.GONE);
        }
    }
    void populateData() {
        Log.d("TAG", "populateData:  executed");
        setVisibilityExpbox();
        percentProfile = 0;
        if (ucode != null) {
            Log.d("TAG", "ucode: null  executed");
            if (!ucode.equals(""))
                Log.d("TAG", "ucode: equals  executed");
                Log.d("cricket", "sachin tendulkar massive inning");
                myprofilepreview.setText(ucode);
        }
        if (phone != null) {
            if (!phone.equals("")) {
                contactmobile.setText(phone);
            }
        }
        if (found_box1 == 1) {
            Log.d("cricket", "virat kohli massive inning");
            percentProfile++;
            if (!fname.equals("") && !lname.equals("")) {
                String firstLetterCapFirstname = fname.substring(0, 1).toUpperCase() + fname.substring(1);
                String firstLetterCapLastname = lname.substring(0, 1).toUpperCase() + lname.substring(1);
                myprofilename.setText(firstLetterCapFirstname + " " + firstLetterCapLastname);
                nametxt.setText(fname + " " + lname);
            }
            if (!country.equals("") && !state.equals("") && !city.equals("")) {
                myprofilloc.setText(city + ", " + state + ", " + country);
            }
        }
        if (found_tenth == 1) {
            if (!board10.equals("")) {
                Log.d("cricket", " Sourav ganguly massive inning");
                noedutab.setVisibility(View.GONE);
                edutab3.setVisibility(View.VISIBLE);
                Hashtable<String, Integer> source = new Hashtable<String, Integer>();
                HashMap<String, Integer> map = new HashMap(source);
                map.put("Jan", 1);
                map.put("Feb", 2);
                map.put("Mar", 3);
                map.put("Apr", 4);
                map.put("May", 5);
                map.put("Jun", 6);
                map.put("Jul", 7);
                map.put("Aug", 8);
                map.put("Sep", 9);
                map.put("Oct", 10);
                map.put("Nov", 11);
                map.put("Dec", 12);
                Date date = new Date();
                SimpleDateFormat sdfm = new SimpleDateFormat("MMM");
                SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
                String currentMonth = sdfm.format(date);
                String currentYears = sdfy.format(date);
                String month = "", years = "";
                for (int i = 0; i < yearofpassing10.length() - 6; i++) {
                    month += yearofpassing10.charAt(i);
                }
                for (int i = 5; i < yearofpassing10.length(); i++) {
                    years += yearofpassing10.charAt(i);
                }
                int currentYear = Integer.parseInt(currentYears);
                int year = Integer.parseInt(years);
                if (currentYear > year)
                    myprofilecource3.setText("Attended Std. X in " + board10 + "  at");
                else if (currentYear == year) {
                    if (map.get(currentMonth) > map.get(month)) {
                        myprofilecource3.setText("Attended Std. X in " + board10 + "  at");
                    } else {
                        myprofilecource3.setText("Attending Std. X in " + board10 + "  at");
                        myprofiledu.setText("Std. X  (" + board10 + ")");
                    }
                } else {
                    myprofilecource3.setText("Attending Std. X in " + board10 + "  at");
                    myprofiledu.setText("Std. X  (" + board10 + ")");
                }
                if (!schoolname10.equals(""))
                    myprofileclgname3.setText(schoolname10);
                if (!yearofpassing10.equals(""))
                    myprofileclgyearofpassing3.setText(yearofpassing10);
                percentProfile++;
            }
        }
        if (found_twelth == 1) {
            Log.d("cricket", " Rohit Sharma coming to bat");
            if (!schoolname12.equals("")) {
                noedutab.setVisibility(View.GONE);
                edutab2.setVisibility(View.VISIBLE);
                Hashtable<String, Integer> source = new Hashtable<String, Integer>();
                HashMap<String, Integer> map = new HashMap(source);
                map.put("Jan", 1);
                map.put("Feb", 2);
                map.put("Mar", 3);
                map.put("Apr", 4);
                map.put("May", 5);
                map.put("Jun", 6);
                map.put("Jul", 7);
                map.put("Aug", 8);
                map.put("Sep", 9);
                map.put("Oct", 10);
                map.put("Nov", 11);
                map.put("Dec", 12);
                Date date = new Date();
                SimpleDateFormat sdfm = new SimpleDateFormat("MMM");
                SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
                String currentMonth = sdfm.format(date);
                String currentYears = sdfy.format(date);
                String month = "", years = "";
                for (int i = 0; i < yearofpassing12.length() - 6; i++) {
                    month += yearofpassing12.charAt(i);
                }
                for (int i = 5; i < yearofpassing12.length(); i++) {
                    years += yearofpassing12.charAt(i);
                }
                int currentYear = Integer.parseInt(currentYears);
                int year = Integer.parseInt(years);
                if (currentYear > year)
                    myprofilecource2.setText("Attended Std. XII in " + board12 + "  at");
                else if (currentYear == year) {
                    if (map.get(currentMonth) > map.get(month)) {
                        myprofilecource2.setText("Attended Std. XII in " + board12 + "  at");
                    } else {
                        myprofilecource2.setText("Attending Std. XII in " + board12 + "  at");
                        myprofiledu.setText("Std. XII (" + board12 + ")");
                    }
                } else {
                    myprofilecource2.setText("Attending Std. XII in " + board12 + "  at");
                    myprofiledu.setText("Std. XII (" + board12 + ")");
                }

                if (!schoolname12.equals(""))
                    myprofileclgname2.setText(schoolname12);
                if (!yearofpassing12.equals(""))
                    myprofileclgyearofpassing2.setText(yearofpassing12);
                percentProfile++;
            }
        }
        if (found_diploma == 1) {
            if (!collegenamediploma.equals("")) {
                Log.d("cricket", " VVS laxman  massive inning");
                noedutab.setVisibility(View.GONE);
                edutab2.setVisibility(View.VISIBLE);
                Hashtable<String, Integer> source = new Hashtable<String, Integer>();
                HashMap<String, Integer> map = new HashMap(source);
                map.put("Jan", 1);
                map.put("Feb", 2);
                map.put("Mar", 3);
                map.put("Apr", 4);
                map.put("May", 5);
                map.put("Jun", 6);
                map.put("Jul", 7);
                map.put("Aug", 8);
                map.put("Sep", 9);
                map.put("Oct", 10);
                map.put("Nov", 11);
                map.put("Dec", 12);
                Date date = new Date();
                SimpleDateFormat sdfm = new SimpleDateFormat("MMM");
                SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
                String currentMonth = sdfm.format(date);
                String currentYears = sdfy.format(date);
                String month = "", years = "";
                for (int i = 0; i < yearofpassingdiploma.length() - 6; i++) {
                    month += yearofpassingdiploma.charAt(i);
                }
                for (int i = 5; i < yearofpassingdiploma.length(); i++) {
                    years += yearofpassingdiploma.charAt(i);
                }
                int currentYear = Integer.parseInt(currentYears);
                int year = Integer.parseInt(years);
                if (currentYear > year)
                    myprofilecource2.setText("Attended Diploma in " + coursediploma + "  at");
                else if (currentYear == year) {
                    if (map.get(currentMonth) > map.get(month)) {
                        myprofilecource2.setText("Attended Diploma in " + coursediploma + "  at");
                    } else {
                        myprofilecource2.setText("Attending Diploma in " + coursediploma + "  at");
                        myprofiledu.setText("Diploma (" + coursediploma + ")");
                    }
                } else {
                    myprofilecource2.setText("Attending Diploma in " + coursediploma + "  at");
                    myprofiledu.setText("Diploma (" + coursediploma + ")");
                }

                if (!collegenamediploma.equals(""))
                    myprofileclgname2.setText(collegenamediploma);
                if (!yearofpassingdiploma.equals(""))
                    myprofileclgyearofpassing2.setText(yearofpassingdiploma);
            }
            percentProfile++;
        }
        if (found_ug == 1) {
            if (!collegenameug.equals("")) {
                Log.d("cricket", "  Rahul Dravid massive inning");
                noedutab.setVisibility(View.GONE);
                edutab1.setVisibility(View.VISIBLE);
                percentProfile++;
                Hashtable<String, Integer> source = new Hashtable<String, Integer>();
                HashMap<String, Integer> map = new HashMap(source);
                map.put("Jan", 1);
                map.put("Feb", 2);
                map.put("Mar", 3);
                map.put("Apr", 4);
                map.put("May", 5);
                map.put("Jun", 6);
                map.put("Jul", 7);
                map.put("Aug", 8);
                map.put("Sep", 9);
                map.put("Oct", 10);
                map.put("Nov", 11);
                map.put("Dec", 12);
                Date date = new Date();
                SimpleDateFormat sdfm = new SimpleDateFormat("MMM");
                SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
                String currentMonth = sdfm.format(date);
                String currentYears = sdfy.format(date);
                String month = "", years = "";
                for (int i = 0; i < yearofpassingug.length() - 6; i++) {
                    month += yearofpassingug.charAt(i);
                }
                for (int i = 5; i < yearofpassingug.length(); i++) {
                    years += yearofpassingug.charAt(i);
                }
                int currentYear = Integer.parseInt(currentYears);
                int year = Integer.parseInt(years);
                if (currentYear > year) {
                    myprofilecource.setText("Attended " + courseug + "  in " + streamug + " at");
                } else if (currentYear == year) {
                    if (map.get(currentMonth) > map.get(month)) {
                        myprofilecource.setText("Attended " + courseug + "  in " + streamug + " at");
                    } else {
                        myprofilecource.setText("Attending " + courseug + "  in " + streamug + " at");
                        myprofiledu.setText(courseug + " (" + streamug + ")");
                    }
                } else {
                    myprofilecource.setText("Attending " + courseug + "  in " + streamug + " at");
                    myprofiledu.setText(courseug + " (" + streamug + ")");
                }
                myprofileclgname.setText(collegenameug);
                myprofileclgyearofpassing.setText(yearofpassingug);
                ImageView insti4 = (ImageView) rootView.findViewById(R.id.insti4);
                RelativeLayout edutab4 = (RelativeLayout) rootView.findViewById(R.id.edutab4);
                insti4.setVisibility(View.GONE);
                edutab4.setVisibility(View.GONE);
            }
        }
        if (found_pgsem == 1) {
            Log.d("cricket", " VVS laxman  massive inning");
            if (!collegenamepgsem.equals("")) {
                percentProfile++;
                noedutab.setVisibility(View.GONE);
                edutab4.setVisibility(View.VISIBLE);
                Hashtable<String, Integer> source = new Hashtable<String, Integer>();
                HashMap<String, Integer> map = new HashMap(source);
                map.put("Jan", 1);
                map.put("Feb", 2);
                map.put("Mar", 3);
                map.put("Apr", 4);
                map.put("May", 5);
                map.put("Jun", 6);
                map.put("Jul", 7);
                map.put("Aug", 8);
                map.put("Sep", 9);
                map.put("Oct", 10);
                map.put("Nov", 11);
                map.put("Dec", 12);
                Date date = new Date();
                SimpleDateFormat sdfm = new SimpleDateFormat("MMM");
                SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
                String currentMonth = sdfm.format(date);
                String currentYears = sdfy.format(date);
                String month = "", years = "";
                for (int i = 0; i < yearofpassingpgsem.length() - 6; i++) {
                    month += yearofpassingpgsem.charAt(i);
                }
                for (int i = 5; i < yearofpassingpgsem.length(); i++) {
                    years += yearofpassingpgsem.charAt(i);
                }
                int currentYear = Integer.parseInt(currentYears);
                int year = Integer.parseInt(years);
                if (currentYear > year)
                    myprofilecource4.setText("Attended " + coursepgsem + " at");
                else if (currentYear == year) {
                    if (map.get(currentMonth) > map.get(month)) {
                        myprofilecource4.setText("Attended " + coursepgsem + " at");
                    } else {
                        myprofilecource4.setText("Attending " + coursepgsem + " at");
                        myprofiledu.setText(coursepgsem);
                    }
                } else {
                    myprofilecource4.setText("Attending " + coursepgsem + " at");
                    myprofiledu.setText(coursepgsem);
                }
                myprofileclgname4.setText(collegenamepgsem);
                myprofileclgyearofpassing4.setText(yearofpassingpgsem);
                ImageView insti4 = (ImageView) rootView.findViewById(R.id.insti4);
                RelativeLayout edutab4 = (RelativeLayout) rootView.findViewById(R.id.edutab4);
                insti4.setVisibility(View.VISIBLE);
                edutab4.setVisibility(View.VISIBLE);
            }
        }
        if (found_pgyear == 1) {
            if (!collegenamepgyear.equals("")) {
                percentProfile++;
                Log.d("cricket", " VVS Sunil Gavskar  massive inning");
                noedutab.setVisibility(View.GONE);
                edutab4.setVisibility(View.VISIBLE);
                Hashtable<String, Integer> source = new Hashtable<String, Integer>();
                HashMap<String, Integer> map = new HashMap(source);
                map.put("Jan", 1);
                map.put("Feb", 2);
                map.put("Mar", 3);
                map.put("Apr", 4);
                map.put("May", 5);
                map.put("Jun", 6);
                map.put("Jul", 7);
                map.put("Aug", 8);
                map.put("Sep", 9);
                map.put("Oct", 10);
                map.put("Nov", 11);
                map.put("Dec", 12);
                Date date = new Date();
                SimpleDateFormat sdfm = new SimpleDateFormat("MMM");
                SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
                String currentMonth = sdfm.format(date);
                String currentYears = sdfy.format(date);
                String month = "", years = "";
                for (int i = 0; i < yearofpassingpgyear.length() - 6; i++) {
                    month += yearofpassingpgyear.charAt(i);
                }
                for (int i = 5; i < yearofpassingpgyear.length(); i++) {
                    years += yearofpassingpgyear.charAt(i);
                }
                int currentYear = Integer.parseInt(currentYears);
                int year = Integer.parseInt(years);
                if (currentYear > year)
                    myprofilecource4.setText("Attended " + coursepgyear + " at");
                else if (currentYear == year) {
                    if (map.get(currentMonth) > map.get(month)) {
                        myprofilecource4.setText("Attended " + coursepgyear + " at");
                    } else {
                        myprofilecource4.setText("Attending " + coursepgyear + " at");
                        myprofiledu.setText(coursepgyear);
                    }
                } else {
                    myprofilecource4.setText("Attending " + coursepgsem + " at");
                    myprofiledu.setText(coursepgyear);
                }
                myprofileclgname4.setText(collegenamepgyear);
                myprofileclgyearofpassing4.setText(yearofpassingpgyear);
                ImageView insti4 = (ImageView) rootView.findViewById(R.id.insti4);
                RelativeLayout edutab4 = (RelativeLayout) rootView.findViewById(R.id.edutab4);
                insti4.setVisibility(View.VISIBLE);
                edutab4.setVisibility(View.VISIBLE);
            }
        }
        if (found_contact_details == 1) {
            percentProfile++;
            Log.d("cricket", " Dinesh kartik massive inning");
            if (!addressline1.equals("") && !addressline2.equals("") && !addressline3.equals("")) {
                contactaddr1.setText(addressline1 + ", " + addressline2 + ", " + addressline3);
                percentProfile++;
            }
        }
        if (found_projects == 1) {
            percentProfile++;
            Log.d("cricket", " suresh raina massive inning");
            if (!proj1.equals("")) {
                myprofileproj1.setText(proj1);
                myprofiledomain1.setText(domain1);
                myprofileduration1.setText(duration1 + " Months");
                ImageView gear2 = (ImageView) rootView.findViewById(R.id.gear2);
                 projtab2 = (RelativeLayout) rootView.findViewById(R.id.projtab2);
                ImageView gear3 = (ImageView) rootView.findViewById(R.id.gear3);
                 projtab3 = (RelativeLayout) rootView.findViewById(R.id.projtab3);
                gear2.setVisibility(View.GONE);
                projtab2.setVisibility(View.GONE);
                gear3.setVisibility(View.GONE);
                projtab3.setVisibility(View.GONE);
                noprojtab.setVisibility(View.GONE);
                projtab1.setVisibility(View.VISIBLE);
            }
            else
            {
                noprojtab.setVisibility(View.VISIBLE);
                projtab1.setVisibility(View.GONE);
                projtab2.setVisibility(View.GONE);
                projtab3.setVisibility(View.GONE);
                extraprojectscount.setVisibility(View.GONE);
            }
            if (!proj2.equals("")) {
                myprofileproj2.setText(proj2);
                myprofiledomain2.setText(domain2);
                myprofileduration2.setText(duration2 + " Months");
                ImageView gear2 = (ImageView) rootView.findViewById(R.id.gear2);
                 projtab2 = (RelativeLayout) rootView.findViewById(R.id.projtab2);
                ImageView gear3 = (ImageView) rootView.findViewById(R.id.gear3);
                 projtab3 = (RelativeLayout) rootView.findViewById(R.id.projtab3);
                gear2.setVisibility(View.VISIBLE);
                projtab2.setVisibility(View.VISIBLE);
                gear3.setVisibility(View.GONE);
                projtab3.setVisibility(View.GONE);
                noprojtab.setVisibility(View.GONE);
            }
            if (!proj3.equals("")) {
                myprofileproj3.setText(proj3);
                myprofiledomain3.setText(domain3);
                myprofileduration3.setText(duration3 + " Months");
                ImageView gear2 = (ImageView) rootView.findViewById(R.id.gear2);
                 projtab2 = (RelativeLayout) rootView.findViewById(R.id.projtab2);
                ImageView gear3 = (ImageView) rootView.findViewById(R.id.gear3);
                 projtab3 = (RelativeLayout) rootView.findViewById(R.id.projtab3);
                gear2.setVisibility(View.VISIBLE);
                projtab2.setVisibility(View.VISIBLE);
                gear3.setVisibility(View.VISIBLE);
                projtab3.setVisibility(View.VISIBLE);
                noprojtab.setVisibility(View.GONE);
                if (!proj4.equals("")) {
                    extraprojectscount.setVisibility(View.VISIBLE);
                    extraprojectscount.setText("and " + proj_count + " more");
                }
            }
        }
        if (found_lang == 1) {
            if (!lang1.equals("- Select Language -")) {
                Log.d("cricket", " ajinkya rahane massive inning");
                if (!lang1.equals("") && !lang1.equals("- Select Language -"))
                    acc1txttxt.setText(lang1);
                if (!lang1.equals("") && !lang1.equals("- Select Language -") && !lang2.equals("") && !lang2.equals("- Select Language -"))
                    acc1txttxt.setText(lang1 + ", " + lang2);
                if (!lang1.equals("") && !lang1.equals("- Select Language -") && !lang2.equals("") && !lang2.equals("- Select Language -") && !lang3.equals("") && !lang3.equals("- Select Language -"))
                    acc1txttxt.setText(lang1 + ", " + lang2 + ", " + lang3);
                if (!lang1.equals("") && !lang1.equals("- Select Language -") && !lang2.equals("") && !lang2.equals("- Select Language -") && !lang3.equals("") && !lang3.equals("- Select Language -") && !lang4.equals("") && !lang4.equals("- Select Language -"))
                    acc1txttxt.setText(lang1 + ", " + lang2 + ", " + lang3 + " and " + lang_count + " more");
                percentProfile++;
            }
            else {
                acc1txttxt.setText("No known languages filled.");
            }
        }
        if (found_certificates == 1) {
            if (!title1.equals("")){
                Log.d("cricket", " cheteshwar pujara massive inning");
            if (!title1.equals(""))
                acc2txttxt.setText(title1);
            if (!title1.equals("") && !title2.equals(""))
                acc2txttxt.setText(title1 + ", " + title2);
            if (!title1.equals("") && !title2.equals("") && !title3.equals(""))
                acc2txttxt.setText(title1 + ", " + title2 + ", " + title3);
            if (!title1.equals("") && !title2.equals("") && !title3.equals("") && !title4.equals(""))
                acc2txttxt.setText(title1 + ", " + title2 + ", " + title3 + " and " + cert_count + " more");
            percentProfile++;
        }
            else
            {
                acc2txttxt.setText("No certifications filled.");
            }
        }
        if (found_courses == 1) {
            if (!course1.equals("")) {
                Log.d("cricket", " kl rahul  massive inning");
                if (!course1.equals(""))
                acc3txttxt.setText(course1);
            if (!course1.equals("") && !course2.equals(""))
                acc3txttxt.setText(course1 + ", " + course2);
            if (!course1.equals("") && !course2.equals("") && !course3.equals(""))
                acc3txttxt.setText(course1 + ", " + course2 + ", " + course3);
            if (!course1.equals("") && !course2.equals("") && !course3.equals("") && !course4.equals(""))
                acc3txttxt.setText(course1 + ", " + course2 + ", " + course3 + " and " + courses_count + " more");
            percentProfile++;
        }
            else
            {
                acc3txttxt.setText("No courses filled.");
            }
        }
        if (found_skills == 1) {
            if(!skill1.equals("")) {
                Log.d("cricket", " Ab divillers  massive inning");
            if (!skill1.equals(""))
                acc4txttxt.setText(skill1);
            if (!skill1.equals("") && !skill2.equals(""))
                acc4txttxt.setText(skill1 + ", " + skill2);
            if (!skill1.equals("") && !skill2.equals("") && !skill3.equals(""))
                acc4txttxt.setText(skill1 + ", " + skill2 + ", " + skill3);
            if (!skill1.equals("") && !skill2.equals("") && !skill3.equals("") && !skill4.equals(""))
                acc4txttxt.setText(skill1 + ", " + skill2 + ", " + skill3 + " and " + skills_count + " more");

            percentProfile++;
        }
            else {
                acc4txttxt.setText("No skills filled.");
            }
        }
        if (found_honors == 1) {
            if(!htitle1.equals("")) {
                Log.d("cricket", " hardik pandya massive inning");
            if (!htitle1.equals(""))
                acc5txttxt.setText(htitle1);
            if (!htitle1.equals("") && !htitle2.equals(""))
                acc5txttxt.setText(htitle1 + ", " + htitle2);
            if (!htitle1.equals("") && !htitle2.equals("") && !htitle3.equals(""))
                acc5txttxt.setText(htitle1 + ", " + htitle2 + ", " + htitle3);
            if (!htitle1.equals("") && !htitle2.equals("") && !htitle3.equals("") && !htitle4.equals(""))
                acc5txttxt.setText(htitle1 + ", " + htitle2 + ", " + htitle3 + " and " + honor_count + " more");
            percentProfile++;
        }
            else {
                acc5txttxt.setText("No awards filled.");
            }

        }
        if (found_patents == 1) {
            if(!ptitle1.equals("")) {
                Log.d("cricket", " shreyash ayyar  massive inning");
            if (!ptitle1.equals(""))
                acc6txttxt.setText(ptitle1);
            if (!ptitle1.equals("") && !ptitle2.equals(""))
                acc6txttxt.setText(ptitle1 + ", " + ptitle2);
            if (!ptitle1.equals("") && !ptitle2.equals("") && !ptitle3.equals(""))
                acc6txttxt.setText(ptitle1 + ", " + ptitle2 + ", " + ptitle3);
            if (!ptitle1.equals("") && !ptitle2.equals("") && !ptitle3.equals("") && !ptitle4.equals(""))
                acc6txttxt.setText(ptitle1 + ", " + ptitle2 + ", " + ptitle3 + " and " + patent_count + " more");
            percentProfile++;
            }
            else {
                acc6txttxt.setText("No patents filled.");
            }

        }
        if (found_publications == 1) {
            if(!pubtitle1.equals("")) {
                Log.d("cricket", " ambati rayadu  massive inning");
                if (!pubtitle1.equals(""))
                acc7txttxt.setText(pubtitle1);
            if (!pubtitle1.equals("") && !pubtitle2.equals(""))
                acc7txttxt.setText(pubtitle1 + ", " + pubtitle2);
            if (!pubtitle1.equals("") && !pubtitle2.equals("") && !pubtitle3.equals(""))
                acc7txttxt.setText(pubtitle1 + ", " + pubtitle2 + ", " + pubtitle3);
            if (!pubtitle1.equals("") && !pubtitle2.equals("") && !pubtitle3.equals("") && !pubtitle4.equals(""))
                acc7txttxt.setText(pubtitle1 + ", " + pubtitle2 + ", " + pubtitle3 + " and " + public_count + " more");
            percentProfile++;

        }
        else {
            acc7txttxt.setText("No publications filled.");
        }

    }
        if (found_careerobj == 1) {
            Log.d("cricket", "yuvraj singh massive inning");
            if (!careerobj.equals(""))
                careerobjtxttxt.setText(careerobj);
            percentProfile++;
        }
        if (found_strengths == 1) {
            if(!strength1.equals("")) {
                Log.d("cricket", "Mahendra singh dhoni massive inning");
            if (!strength1.equals(""))
                strengthstxt.setText(strength1);
            if (!strength1.equals("") && !strength2.equals(""))
                strengthstxt.setText(strength1 + ", " + strength2);
            if (!strength1.equals("") && !strength2.equals("") && !strength3.equals(""))
                strengthstxt.setText(strength1 + ", " + strength2 + ", " + strength3);
            if (!strength1.equals("") && !strength2.equals("") && !strength3.equals("") && !strength4.equals(""))
                strengthstxt.setText(strength1 + ", " + strength2 + ", " + strength3 + " and " + strength_count + " more");
            percentProfile++;
            }
            else {
                strengthstxt.setText("No Strengths filled.");
            }

        }
        if (found_weaknesses == 1) {
            if(!weak1.equals("")) {
                Log.d("cricket", " guatam gambhir massive inning");
                if (!weak1.equals(""))
                weaknessestxt.setText(weak1);
            if (!weak1.equals("") && !weak2.equals(""))
                weaknessestxt.setText(weak1 + ", " + weak2);
            if (!weak1.equals("") && !weak2.equals("") && !weak3.equals(""))
                weaknessestxt.setText(weak1 + ", " + weak2 + ", " + weak3);
            if (!weak1.equals("") && !weak2.equals("") && !weak3.equals("") && !weak4.equals(""))
                weaknessestxt.setText(weak1 + ", " + weak2 + ", " + weak3 + " and " + weakness_count + " more");
            percentProfile++;
            }
            else {
                weaknessestxt.setText("No Weaknesses filled.");
            }

        }
        if (found_locationpreferences == 1) {
            if(!location1.equals("")) {
                Log.d("cricket", " shikhar dhawan massive inning");
                if (!location1.equals(""))
                locationpreferences.setText(location1);
            if (!location1.equals("") && !location2.equals(""))
                locationpreferences.setText(location1 + ", " + location2);
            if (!location1.equals("") && !location2.equals("") && !location3.equals(""))
                locationpreferences.setText(location1 + ", " + location2 + ", " + location3);
            if (!location1.equals("") && !location2.equals("") && !location3.equals("") && !location4.equals(""))
                locationpreferences.setText(location1 + ", " + location2 + ", " + location3 + " and " + location_count + " more");
            percentProfile++;
            }
            else {
                locationpreferences.setText("No location preferences filled.");
            }

        }
        populateHrInfo();
        if (hrinfobox1 == true)
            percentProfile++;

        float R = (1000 - 0) / (19 - 0);
        float y = (percentProfile - 0) * R + 0;
        int val = Math.round(y);

        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(profileprogress, "progress", 0, val);
        progressAnimator.setDuration(1000);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.start();

    }

    public int alltoDatestoInt(String mydate) {
        int m = 0, t = 0;

        String[] splited;
        splited = mydate.split(", ");
        if (splited.length == 2) {
            int mixdate;
            String fromMonths = "";
            fromMonths = splited[0];
            m = hashMap.get(fromMonths);
            t = Integer.parseInt(splited[1]);
            mixdate = t * 10 + m;
            return mixdate;
        }

        return 0;
    }

    public int[] expYearMonth(String fromdate, String todate) {

        int fromYear = 0, frommonth = 0, toyear = 0, tomonth = 0;
        String[] splited;
        splited = fromdate.split(", ");
        if (splited.length == 2) {
            String fromMonths = "";
            fromMonths = splited[0];
            frommonth = hashMap.get(fromMonths);
            fromYear = Integer.parseInt(splited[1]);
        }
        if (!todate.equals("")) {

            splited = todate.split(", ");
            if (splited.length == 2) {
                String mstr = "";
                mstr = splited[0];
                tomonth = hashMap.get(mstr);
                toyear = Integer.parseInt(splited[1]);
            }

        } else {
            Date date = new Date();
            SimpleDateFormat sdfm = new SimpleDateFormat("MMM");
            SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");

            String currentMonth = sdfm.format(date);
            String currentYears = sdfy.format(date);
            tomonth = hashMap.get(currentMonth);
            toyear = Integer.parseInt(currentYears);

        }
        DateTime date2 = new DateTime().withDate(toyear, tomonth, 1);
        DateTime date1 = new DateTime().withDate(fromYear, frommonth, 1);
        int exp_in_months2 = Months.monthsBetween(date1, date2).getMonths();
        int year = exp_in_months2 / 12;
        int months = exp_in_months2 % 12;
        int expMY[] = {year, months};

        return expMY;
    }

    public void populateHrInfo() {
        TreeMap<Integer, Integer> continuseWork = new TreeMap<>();
        TreeMap<Integer, Integer> workDoneExp = new TreeMap<>(Collections.reverseOrder());
        hrinfobox1 = false;
        hrinfobox2 = false;
        hrinfobox3 = false;
        int fulltodate1 = 0, fulltodate2 = 0, fulltodate3 = 0, fulltodate4 = 0, fulltodate5 = 0, fulltodate6 = 0, fulltodate7 = 0, fulltodate8 = 0, fulltodate9 = 0, fulltodate10 = 0;
        String MONTH = "";
        String YEAR = "";


        if (todates1.equals("") && !fromdates1.equals("")) {
            Log.d("cricket", " Ravidra jadeja massive inning");
            fulltodate1 = alltoDatestoInt(fromdates1);
            continuseWork.put(fulltodate1, 1);
        }

        if (todates2.equals("") && !fromdates2.equals("")) {
            fulltodate2 = alltoDatestoInt(fromdates2);
            continuseWork.put(fulltodate2, 2);
        }

        if (todates3.equals("") && !fromdates3.equals("")) {
            fulltodate3 = alltoDatestoInt(fromdates3);
            continuseWork.put(fulltodate3, 3);
        }

        if (todates4.equals("") && !fromdates4.equals("")) {
            fulltodate4 = alltoDatestoInt(fromdates4);
            continuseWork.put(fulltodate4, 4);
        }

        if (todates5.equals("") && !fromdates5.equals("")) {
            fulltodate5 = alltoDatestoInt(fromdates5);
            continuseWork.put(fulltodate5, 5);
        }

        if (todates6.equals("") && !fromdates6.equals("")) {
            fulltodate6 = alltoDatestoInt(todates6);
            continuseWork.put(fulltodate6, 6);
        }

        if (todates7.equals("") && !fromdates7.equals("")) {
            fulltodate7 = alltoDatestoInt(fromdates7);
            continuseWork.put(fulltodate7, 7);
        }

        if (todates8.equals("") && !fromdates8.equals("")) {
            fulltodate8 = alltoDatestoInt(fromdates8);
            continuseWork.put(fulltodate8, 8);
        }

        if (todates9.equals("") && !fromdates9.equals("")) {
            fulltodate9 = alltoDatestoInt(fromdates9);
            continuseWork.put(fulltodate9, 9);
        }

        if (todates10.equals("") && !fromdates10.equals("")) {
            fulltodate10 = alltoDatestoInt(fromdates10);
            continuseWork.put(fulltodate10, 10);
        }
        for (Map.Entry<Integer, Integer> entry : continuseWork.entrySet()) {
            int fullDatekey = entry.getKey();
            int expItemIndex = entry.getValue();

            if (expItemIndex == 1) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts1);
                    myprofileexp1name.setText(inst1s1);
                    int[] YM = expYearMonth(fromdates1, todates1);

                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";


                    if (YM[1] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;

                } else if (hrinfobox2 == false) {

                    exp2txt.setText(posts1);
                    myprofileexp2name.setText(inst1s1);
                    int[] YM = expYearMonth(fromdates1, todates1);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts1);
                    myprofileexp3name.setText(inst1s1);
                    int[] YM = expYearMonth(fromdates1, todates1);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 2) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts2);
                    myprofileexp1name.setText(inst1s2);
                    int[] YM = expYearMonth(fromdates2, todates2);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;

                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts2);
                    myprofileexp2name.setText(inst1s2);
                    int[] YM = expYearMonth(fromdates2, todates2);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts3);
                    myprofileexp3name.setText(inst1s3);
                    int[] YM = expYearMonth(fromdates3, todates3);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }

            }
            if (expItemIndex == 3) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts3);
                    myprofileexp1name.setText(inst1s3);
                    int[] YM = expYearMonth(fromdates3, todates3);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts3);
                    myprofileexp2name.setText(inst1s3);
                    int[] YM = expYearMonth(fromdates3, todates3);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts3);
                    myprofileexp3name.setText(inst1s3);
                    int[] YM = expYearMonth(fromdates3, todates3);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 4) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts4);
                    myprofileexp1name.setText(inst1s4);
                    int[] YM = expYearMonth(fromdates4, todates4);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts4);
                    myprofileexp2name.setText(inst1s4);
                    int[] YM = expYearMonth(fromdates4, todates4);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts4);
                    myprofileexp3name.setText(inst1s4);
                    int[] YM = expYearMonth(fromdates4, todates4);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 5) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts5);
                    myprofileexp1name.setText(inst1s5);
                    int[] YM = expYearMonth(fromdates5, todates5);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts5);
                    myprofileexp2name.setText(inst1s5);
                    int[] YM = expYearMonth(fromdates5, todates5);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts5);
                    myprofileexp3name.setText(inst1s5);
                    int[] YM = expYearMonth(fromdates5, todates5);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 6) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts6);
                    myprofileexp1name.setText(inst1s6);
                    int[] YM = expYearMonth(fromdates6, todates6);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts6);
                    myprofileexp2name.setText(inst1s6);
                    int[] YM = expYearMonth(fromdates6, todates6);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts6);
                    myprofileexp3name.setText(inst1s6);
                    int[] YM = expYearMonth(fromdates6, todates6);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 7) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts7);
                    myprofileexp1name.setText(inst1s7);
                    int[] YM = expYearMonth(fromdates7, todates7);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts7);
                    myprofileexp2name.setText(inst1s7);
                    int[] YM = expYearMonth(fromdates7, todates7);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts7);
                    myprofileexp3name.setText(inst1s7);
                    int[] YM = expYearMonth(fromdates7, todates7);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 8) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts8);
                    myprofileexp1name.setText(inst1s8);
                    int[] YM = expYearMonth(fromdates8, todates8);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts8);
                    myprofileexp2name.setText(inst1s8);
                    int[] YM = expYearMonth(fromdates8, todates8);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts8);
                    myprofileexp3name.setText(inst1s8);
                    int[] YM = expYearMonth(fromdates8, todates8);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 9) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts9);
                    myprofileexp1name.setText(inst1s9);
                    int[] YM = expYearMonth(fromdates9, todates9);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts9);
                    myprofileexp2name.setText(inst1s9);
                    int[] YM = expYearMonth(fromdates9, todates9);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts9);
                    myprofileexp3name.setText(inst1s9);
                    int[] YM = expYearMonth(fromdates9, todates9);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 10) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts10);
                    myprofileexp1name.setText(inst1s10);
                    int[] YM = expYearMonth(fromdates10, todates10);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts10);
                    myprofileexp2name.setText(inst1s10);
                    int[] YM = expYearMonth(fromdates10, todates10);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts10);
                    myprofileexp3name.setText(inst1s10);
                    int[] YM = expYearMonth(fromdates10, todates10);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
        }

        if (!todates1.equals("") && !fromdates1.equals("")) {
            Log.d("cricket", " Ravidra jadeja massive inning");
            fulltodate1 = alltoDatestoInt(todates1);
            workDoneExp.put(fulltodate1, 1);
        }
        if (!todates2.equals("") && !fromdates2.equals("")) {
            fulltodate2 = alltoDatestoInt(todates2);
            workDoneExp.put(fulltodate2, 2);
        }
        if (!todates3.equals("") && !fromdates3.equals("")) {
            fulltodate3 = alltoDatestoInt(todates3);
            workDoneExp.put(fulltodate3, 3);
        }
        if (!todates4.equals("") && !fromdates4.equals("")) {
            fulltodate4 = alltoDatestoInt(todates4);
            workDoneExp.put(fulltodate4, 4);
        }
        if (!todates5.equals("") && !fromdates5.equals("")) {
            fulltodate5 = alltoDatestoInt(todates5);
            workDoneExp.put(fulltodate5, 5);
        }
        if (!todates6.equals("") && !fromdates6.equals("")) {
            fulltodate6 = alltoDatestoInt(todates6);
            workDoneExp.put(fulltodate6, 6);
        }
        if (!todates7.equals("") && !fromdates7.equals("")) {
            fulltodate7 = alltoDatestoInt(todates7);
            workDoneExp.put(fulltodate7, 7);
        }
        if (!todates8.equals("") && !fromdates8.equals("")) {
            fulltodate8 = alltoDatestoInt(todates8);
            workDoneExp.put(fulltodate8, 8);
        }
        if (!todates9.equals("") && !fromdates9.equals("")) {
            fulltodate9 = alltoDatestoInt(todates9);
            workDoneExp.put(fulltodate9, 9);
        }
        if (!todates10.equals("") && !fromdates10.equals("")) {
            fulltodate10 = alltoDatestoInt(todates10);
            workDoneExp.put(fulltodate10, 10);
        }

        for (Map.Entry<Integer, Integer> entry : workDoneExp.entrySet()) {
            int expItemIndex = entry.getValue();          // post+index  all set

            if (expItemIndex == 1) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts1);
                    myprofileexp1name.setText(inst1s1);
                    int[] YM = expYearMonth(fromdates1, todates1);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText(fromdates1 + " - " + todates1 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText(fromdates1 + " - " + todates1 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText(fromdates1 + " - " + todates1 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts1);
                    myprofileexp2name.setText(inst1s1);
                    int[] YM = expYearMonth(fromdates1, todates1);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText(fromdates1 + " - " + todates1 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText(fromdates1 + " - " + todates1 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText(fromdates1 + " - " + todates1 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts1);
                    myprofileexp3name.setText(inst1s1);
                    int[] YM = expYearMonth(fromdates1, todates1);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText(fromdates1 + " - " + todates1 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText(fromdates1 + " - " + todates1 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText(fromdates1 + " - " + todates1 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 2) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts2);
                    myprofileexp1name.setText(inst1s2);
                    int[] YM = expYearMonth(fromdates2, todates2);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText(fromdates2 + " - " + todates2 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText(fromdates2 + " - " + todates2 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText(fromdates2 + " - " + todates2 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;

                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts2);
                    myprofileexp2name.setText(inst1s2);
                    int[] YM = expYearMonth(fromdates2, todates2);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText(fromdates2 + " - " + todates2 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText(fromdates2 + " - " + todates2 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText(fromdates2 + " - " + todates2 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts2);
                    myprofileexp3name.setText(inst1s2);
                    int[] YM = expYearMonth(fromdates2, todates2);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText(fromdates2 + " - " + todates2 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText(fromdates2 + " - " + todates2 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText(fromdates2 + " - " + todates2 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }

            }
            if (expItemIndex == 3) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts3);
                    myprofileexp1name.setText(inst1s3);
                    int[] YM = expYearMonth(fromdates3, todates3);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText(fromdates3 + " - " + todates3 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText(fromdates3 + " - " + todates3 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText(fromdates3 + " - " + todates3 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts3);
                    myprofileexp2name.setText(inst1s3);
                    int[] YM = expYearMonth(fromdates3, todates3);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText(fromdates3 + " - " + todates3 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText(fromdates3 + " - " + todates3 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText(fromdates3 + " - " + todates3 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts3);
                    myprofileexp3name.setText(inst1s3);
                    int[] YM = expYearMonth(fromdates3, todates3);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText(fromdates3 + " - " + todates3 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText(fromdates3 + " - " + todates3 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText(fromdates3 + " - " + todates3 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 4) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts4);
                    myprofileexp1name.setText(inst1s4);
                    int[] YM = expYearMonth(fromdates4, todates4);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText(fromdates4 + " - " + todates4 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText(fromdates4 + " - " + todates4 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText(fromdates4 + " - " + todates4 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts4);
                    myprofileexp2name.setText(inst1s4);
                    int[] YM = expYearMonth(fromdates4, todates4);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText(fromdates4 + " - " + todates4 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText(fromdates4 + " - " + todates4 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText(fromdates4 + " - " + todates4 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts4);
                    myprofileexp3name.setText(inst1s4);
                    int[] YM = expYearMonth(fromdates4, todates4);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText(fromdates4 + " - " + todates4 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText(fromdates4 + " - " + todates4 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText(fromdates4 + " - " + todates4 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 5) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts5);
                    myprofileexp1name.setText(inst1s5);
                    int[] YM = expYearMonth(fromdates5, todates5);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText(fromdates5 + " - " + todates5 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText(fromdates5 + " - " + todates5 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText(fromdates5 + " - " + todates5 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts5);
                    myprofileexp2name.setText(inst1s5);
                    int[] YM = expYearMonth(fromdates5, todates5);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText(fromdates5 + " - " + todates5 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText(fromdates5 + " - " + todates5 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText(fromdates5 + " - " + todates5 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts5);
                    myprofileexp3name.setText(inst1s5);
                    int[] YM = expYearMonth(fromdates5, todates5);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText(fromdates5 + " - " + todates5 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText(fromdates5 + " - " + todates5 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText(fromdates5 + " - " + todates5 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 6) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts6);
                    myprofileexp1name.setText(inst1s6);
                    int[] YM = expYearMonth(fromdates6, todates6);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText(fromdates6 + " - " + todates6 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText(fromdates6 + " - " + todates6 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText(fromdates6 + " - " + todates6 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts6);
                    myprofileexp2name.setText(inst1s6);
                    int[] YM = expYearMonth(fromdates6, todates6);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText(fromdates6 + " - " + todates6 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText(fromdates6 + " - " + todates6 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText(fromdates6 + " - " + todates6 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts6);
                    myprofileexp3name.setText(inst1s6);
                    int[] YM = expYearMonth(fromdates6, todates6);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText(fromdates6 + " - " + todates6 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText(fromdates6 + " - " + todates6 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText(fromdates6 + " - " + todates6 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 7) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts7);
                    myprofileexp1name.setText(inst1s7);
                    int[] YM = expYearMonth(fromdates7, todates7);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText(fromdates7 + " - " + todates7 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText(fromdates7 + " - " + todates7 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText(fromdates7 + " - " + todates7 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts7);
                    myprofileexp2name.setText(inst1s7);
                    int[] YM = expYearMonth(fromdates7, todates7);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText(fromdates7 + " - " + todates7 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText(fromdates7 + " - " + todates7 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText(fromdates7 + " - " + todates7 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts7);
                    myprofileexp3name.setText(inst1s7);
                    int[] YM = expYearMonth(fromdates7, todates7);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText(fromdates7 + " - " + todates7 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText(fromdates7 + " - " + todates7 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText(fromdates7 + " - " + todates7 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 8) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts8);
                    myprofileexp1name.setText(inst1s8);
                    int[] YM = expYearMonth(fromdates8, todates8);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText(fromdates8 + " - " + todates8 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText(fromdates8 + " - " + todates8 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText(fromdates8 + " - " + todates8 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts8);
                    myprofileexp2name.setText(inst1s8);
                    int[] YM = expYearMonth(fromdates8, todates8);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText(fromdates8 + " - " + todates8 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText(fromdates8 + " - " + todates8 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText(fromdates8 + " - " + todates8 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts8);
                    myprofileexp3name.setText(inst1s8);
                    int[] YM = expYearMonth(fromdates8, todates8);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText(fromdates8 + " - " + todates8 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText(fromdates8 + " - " + todates8 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText(fromdates8 + " - " + todates8 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 9) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts9);
                    myprofileexp1name.setText(inst1s9);
                    int[] YM = expYearMonth(fromdates9, todates9);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText(fromdates9 + " - " + todates9 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText(fromdates9 + " - " + todates9 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText(fromdates9 + " - " + todates9 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts9);
                    myprofileexp2name.setText(inst1s9);
                    int[] YM = expYearMonth(fromdates9, todates9);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText(fromdates9 + " - " + todates9 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText(fromdates9 + " - " + todates9 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText(fromdates9 + " - " + todates9 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts9);
                    myprofileexp3name.setText(inst1s9);
                    int[] YM = expYearMonth(fromdates9, todates9);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText(fromdates9 + " - " + todates9 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText(fromdates9 + " - " + todates9 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText(fromdates9 + " - " + todates9 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 10) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts10);
                    myprofileexp1name.setText(inst1s10);
                    int[] YM = expYearMonth(fromdates10, todates10);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText(fromdates10 + " - " + todates10 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText(fromdates10 + " - " + todates10 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText(fromdates10 + " - " + todates10 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts10);
                    myprofileexp2name.setText(inst1s10);
                    int[] YM = expYearMonth(fromdates10, todates10);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText(fromdates10 + " - " + todates10 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText(fromdates10 + " - " + todates10 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText(fromdates10 + " - " + todates10 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts10);
                    myprofileexp3name.setText(inst1s10);
                    int[] YM = expYearMonth(fromdates10, todates10);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText(fromdates10 + " - " + todates10 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText(fromdates10 + " - " + todates10 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText(fromdates10 + " - " + todates10 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
        }
    }
    public void refreshContent() {
        Log.d("TAG", "refreshContent: executed");
        new GetAlumniData().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        ((AlumniActivity) getActivity()).requestProfileImage();
        updateProgress.setVisibility(View.VISIBLE);
    }
    public void showUpdateProgress() {
        updateProgress.setVisibility(View.VISIBLE);
    }

    public void hideUpdateProgress() {
        updateProgress.setVisibility(View.VISIBLE);
    }

    void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose Action").setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {
                    dialog.cancel();
                    ((AlumniActivity) getActivity()).requestCropImage();
                } else if (which == 1) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    new DeleteProfile().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    dialog.cancel();
                                    break;
                            }
                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();
                }
            }
        });
        builder.show();
    }

    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Animation animation = super.onCreateAnimation(transit, enter, nextAnim);
        if (Build.VERSION.SDK_INT >= 11) {
            if (animation == null && nextAnim != 0) {
                animation = AnimationUtils.loadAnimation(getActivity(), nextAnim);
            }

            if (animation != null) {
                getView().setLayerType(View.LAYER_TYPE_HARDWARE, null);

                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    public void onAnimationEnd(Animation animation) {
                        getView().setLayerType(View.LAYER_TYPE_NONE, null);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        }

        return animation;
    }

    public void downloadImage() {

        new Getsingnature().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
    private class GetAlumniData extends AsyncTask<String, Void, Bitmap> {
        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;
            try {
                Log.d("TAG", "doInBackground: executed");

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("u", username));
                json = jParser.makeHttpRequest(Z.url_load_alumni_data, "GET", params);
                String s = "";
                resultofop = json.getString("info");
                if (resultofop.equals("found")) {
                    Log.d("TAG", "doInBackground: resultofop executed");

                    Log.d("cricket", "sachin tendulkar opening to bat");
                    ucode = json.getString("ucode");
                    String encphone = json.getString("phone");
                    phone=Z.Decrypt(encphone,getContext());
                    s = json.getString("intro");
                    if (s.equals("found")) {
                        Log.d("cricket", "virat kohli coming to bat");
                        found_box1 = 1;
                        dataobject = json.getString("introObj");
                        Modelmyprofileintro obj2 = (Modelmyprofileintro) fromString(dataobject, MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));
                        fname = obj2.getFirstname();
                        lname = obj2.getLastname();
                        country = obj2.getSelectedCountry();
                        state = obj2.getSelectedState();
                        city = obj2.getSelectedCity();
//
                        studentData.setFname(fname);
                        studentData.setMname(mname);
                        studentData.setLname(lname);
                        studentData.setCountry(country);
                        studentData.setState(state);
                        studentData.setCity(city);
                    }

                    s = json.getString("career");
                    if (s.equals("found")) {
                        Log.d("cricket", "yuvraj singh coming to bat");
                        found_careerobj = 1;
                        careerdataobject = json.getString("careerobj");
                        MyProfileCareerObjModal obj2 = (MyProfileCareerObjModal) fromString(careerdataobject, MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));
                        careerobj = obj2.careerobj;
                        studentData.setCareerobj(careerobj);
                    }
                    s = json.getString("strengths");
                    if (s.equals("found")) {
                        Log.d("cricket", "Mahendra singh dhoni coming to bat");
                        found_strengths = 1;
                        strengthdataobject = json.getString("strengthsobj");
                        MyProfileStrengthsModal obj2 = (MyProfileStrengthsModal) fromString(strengthdataobject, MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));
                        strength1 = obj2.sstrength1;
                        strength2 = obj2.sstrength2;
                        strength3 = obj2.sstrength3;
                        strength4 = obj2.sstrength4;
                        strength5 = obj2.sstrength5;
                        strength6 = obj2.sstrength6;
                        strength7 = obj2.sstrength7;
                        strength8 = obj2.sstrength8;
                        strength9 = obj2.sstrength9;
                        strength10 = obj2.sstrength10;

                        studentData.setStrength1(strength1);
                        studentData.setStrength2(strength2);
                        studentData.setStrength3(strength3);
                        studentData.setStrength4(strength4);
                        studentData.setStrength5(strength5);
                        studentData.setStrength6(strength6);
                        studentData.setStrength7(strength7);
                        studentData.setStrength8(strength8);
                        studentData.setStrength9(strength9);
                        studentData.setStrength10(strength10);

                        if (!strength4.equals(""))
                            strength_count = 1;
                        if (!strength5.equals(""))
                            strength_count = 2;
                        if (!strength6.equals(""))
                            strength_count = 3;
                        if (!strength7.equals(""))
                            strength_count = 4;
                        if (!strength8.equals(""))
                            strength_count = 5;
                        if (!strength9.equals(""))
                            strength_count = 6;
                        if (!strength10.equals(""))
                            strength_count = 7;

                    }
                    s = json.getString("weaknesses");
                    if (s.equals("found")) {
                        Log.d("cricket", " guatam gambhir coming to bat");
                        found_weaknesses = 1;
                        weaknessesdataobject = json.getString("weaknessesobj");
                        MyProfileWeaknessesModal obj2 = (MyProfileWeaknessesModal) fromString(weaknessesdataobject, MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));
                        weak1 = obj2.sweak1;
                        weak2 = obj2.sweak2;
                        weak3 = obj2.sweak3;
                        weak4 = obj2.sweak4;
                        weak5 = obj2.sweak5;
                        weak6 = obj2.sweak6;
                        weak7 = obj2.sweak7;
                        weak8 = obj2.sweak8;
                        weak9 = obj2.sweak9;
                        weak10 = obj2.sweak10;
                        studentData.setWeak1(weak1);
                        studentData.setWeak2(weak2);
                        studentData.setWeak3(weak3);
                        studentData.setWeak4(weak4);
                        studentData.setWeak5(weak5);
                        studentData.setWeak6(weak6);
                        studentData.setWeak7(weak7);
                        studentData.setWeak8(weak8);
                        studentData.setWeak9(weak9);
                        studentData.setWeak10(weak10);

                        if (!weak5.equals(""))
                            weakness_count = 2;

                        if (!weak6.equals(""))
                            weakness_count = 3;

                        if (!weak7.equals(""))
                            weakness_count = 4;

                        if (!weak8.equals(""))
                            weakness_count = 5;

                        if (!weak9.equals(""))
                            weakness_count = 6;

                        if (!weak10.equals(""))
                            weakness_count = 7;

                    }

                    s = json.getString("locationpreferences");
                    if (s.equals("found")) {
                        Log.d("cricket", " shikhar dhawan coming to bat");
                        found_locationpreferences = 1;
                        locationpreferencesdataobject = json.getString("locationpreferencesobj");
                        MyProfileLocationModal obj2 = (MyProfileLocationModal) fromString(locationpreferencesdataobject, MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));
                        location1 = obj2.slocation1;
                        location2 = obj2.slocation2;
                        location3 = obj2.slocation3;
                        location4 = obj2.slocation4;
                        location5 = obj2.slocation5;
                        studentData.setLocation1(location1);
                        studentData.setLocation2(location2);
                        studentData.setLocation3(location3);
                        studentData.setLocation4(location4);
                        studentData.setLocation5(location5);

                        if (!location4.equals(""))
                            location_count = 1;

                        if (!location5.equals(""))
                            location_count = 2;
                    }
                    s = json.getString("tenth");
                    if (s.equals("found")) {
                        Log.d("cricket", " Sourav ganguly coming to bat");
                        found_tenth = 1;
                        tenthdataobject = json.getString("tenthobj");
                        MyProfileTenthModal obj2 = (MyProfileTenthModal) fromString(tenthdataobject, MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));
                        yearofpassing10 = obj2.monthandyearofpassing;
                        board10 = obj2.selectedBoard;
                        schoolname10 = obj2.schoolname;

                        studentData.setYearofpassing10(yearofpassing10);
                        studentData.setBoard10(board10);
                        studentData.setSchoolname10(schoolname10);
                        studentData.setMarks10(obj2.marksobtained);
                        studentData.setOutof10(obj2.outofmarks);
                        studentData.setPercentage10(obj2.percentage);
                        studentData.setSchoolname10(obj2.schoolname);
                        studentData.setYearofpassing10(obj2.monthandyearofpassing);
                        studentData.setBoard10(obj2.selectedBoard);
                    }
                    s = json.getString("ug");
                    if (s.equals("found")) {
                        Log.d("cricket", " Rahul Dravid coming to bat");
                        found_ug = 1;
                        ugdataobject = json.getString("ugobj");
                        MyProfileUgModal obj2 = (MyProfileUgModal) fromString(ugdataobject, MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));
                        yearofpassingug = obj2.monthandyearofpassing;
                        collegenameug = obj2.schoolname;
                        universityug = obj2.selectedUniversity;
                        courseug = obj2.selectedCourse;
                        streamug = obj2.selectedStream;
                        studentData.setYearofpassingug(yearofpassingpgyear);
                        studentData.setCollegenameug(collegenameug);
                        studentData.setUniversityug(universityug);
                        studentData.setMarkssem1ug(obj2.markssem1);
                        studentData.setOutofsem1ug(obj2.outofsem1);
                        studentData.setPercentagesem1ug(obj2.percentsem1);
                        studentData.setMarkssem2ug(obj2.markssem2);
                        studentData.setOutofsem2ug(obj2.outofsem2);
                        studentData.setPercentagesem2ug(obj2.percentsem2);
                        studentData.setMarkssem3ug(obj2.markssem3);
                        studentData.setOutofsem3ug(obj2.outofsem3);
                        studentData.setPercentagesem3ug(obj2.percentsem3);
                        studentData.setMarkssem4ug(obj2.markssem4);
                        studentData.setOutofsem4ug(obj2.outofsem4);
                        studentData.setPercentagesem4ug(obj2.percentsem4);
                        studentData.setMarkssem5ug(obj2.markssem5);
                        studentData.setOutofsem5ug(obj2.outofsem5);
                        studentData.setPercentagesem5ug(obj2.percentsem5);
                        studentData.setMarkssem6ug(obj2.markssem6);
                        studentData.setOutofsem6ug(obj2.outofsem6);
                        studentData.setPercentagesem6ug(obj2.percentsem6);
                        studentData.setMarkssem7ug(obj2.markssem7);
                        studentData.setOutofsem7ug(obj2.outofsem7);
                        studentData.setPercentagesem7ug(obj2.percentsem7);
                        studentData.setMarkssem8ug(obj2.markssem8);
                        studentData.setOutofsem8ug(obj2.outofsem8);
                        studentData.setPercentagesem8ug(obj2.percentsem8);
                        studentData.setAggregateug(obj2.aggregate);
                        studentData.setCollegenameug(obj2.schoolname);
                        studentData.setYearofpassingug(obj2.monthandyearofpassing);
                        studentData.setCourseug(obj2.selectedCourse);
                        studentData.setStreamug(obj2.selectedStream);
                    }
                    s = json.getString("twelth");
                    if (s.equals("found")) {
                        Log.d("cricket", " Rohit Sharma coming to bat");
                        found_twelth = 1;
                        twelthdataobject = json.getString("twelthobj");
                        MyProfileTwelthModal obj2 = (MyProfileTwelthModal) fromString(twelthdataobject, MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));
                        yearofpassing12 = obj2.monthandyearofpassing12;
                        schoolname12 = obj2.schoolnametwelth;
                        board12 = obj2.selectedboardBytes1;
                        studentData.setMarks12(obj2.marksobtained);
                        studentData.setOutof12(obj2.outofmarks);
                        studentData.setPercentage12(obj2.percentage);
                        studentData.setSchoolname12(obj2.schoolnametwelth);
                        studentData.setStream12(obj2.selectedstreamBytes1);
                        studentData.setBoard12(obj2.selectedboardBytes1);
                        studentData.setYearofpassing12(obj2.monthandyearofpassing12);

                    }

                    s = json.getString("diploma");
                    if (s.equals("found")) {
                        Log.d("cricket", " VVS laxman  coming to bat");
                        found_diploma = 1;
                        diplomadataobject = json.getString("diplomaobj");
                        MyProfileDiplomaModal obj2 = (MyProfileDiplomaModal) fromString(diplomadataobject, MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));
                        yearofpassingdiploma = obj2.monthandyearofpassingdiploma;
                        collegenamediploma = obj2.schoolnamediploma;
                        universitydiploma = obj2.selectedboardBytes1;
                        coursediploma = obj2.selectedcourseBytes1;
                        studentData.setMarkssem1diploma(obj2.markssem1);
                        studentData.setOutofsem1diploma(obj2.outofsem1);
                        studentData.setPercentagesem1diploma(obj2.percentsem1);
                        studentData.setMarkssem2diploma(obj2.markssem2);
                        studentData.setOutofsem2diploma(obj2.outofsem2);
                        studentData.setPercentagesem2diploma(obj2.percentsem2);
                        studentData.setMarkssem3diploma(obj2.markssem3);
                        studentData.setOutofsem3diploma(obj2.outofsem3);
                        studentData.setPercentagesem3diploma(obj2.percentsem3);
                        studentData.setMarkssem4diploma(obj2.markssem4);
                        studentData.setOutofsem4diploma(obj2.outofsem4);
                        studentData.setPercentagesem4diploma(obj2.percentsem4);
                        studentData.setMarkssem5diploma(obj2.markssem5);
                        studentData.setOutofsem5diploma(obj2.outofsem5);
                        studentData.setPercentagesem5diploma(obj2.percentsem5);
                        studentData.setMarkssem6diploma(obj2.markssem6);
                        studentData.setOutofsem6diploma(obj2.outofsem6);
                        studentData.setPercentagesem6diploma(obj2.percentsem6);
                        studentData.setAggregatediploma(obj2.aggregate);
                        studentData.setUniversitydiploma(obj2.selectedboardBytes1);
                        studentData.setCollegenamediploma(obj2.schoolnamediploma);
                        studentData.setYearofpassingdiploma(obj2.monthandyearofpassingdiploma);
                        studentData.setCoursediploma(obj2.selectedcourseBytes1);
                    }
                    s = json.getString("pgsem");
                    if (s.equals("found")) {
                        Log.d("cricket", " VVS laxman  coming to bat");
                        found_pgsem = 1;
                        PgSem obj = (PgSem) fromString(json.getString("pgsemdata"), MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));
                        markssem1pgsem = obj.getMarkssem1();
                        outofsem1pgsem = obj.getOutofsem1();
                        percentagesem1pgsem = obj.getPercentsem1();
                        markssem2pgsem = obj.getMarkssem2();
                        outofsem2pgsem = obj.getOutofsem2();
                        percentagesem2pgsem = obj.getPercentsem2();
                        markssem3pgsem = obj.getMarkssem3();
                        outofsem3pgsem = obj.getOutofsem3();
                        percentagesem3pgsem = obj.getPercentsem3();
                        markssem4pgsem = obj.getMarkssem4();
                        outofsem4pgsem = obj.getOutofsem4();
                        percentagesem4pgsem = obj.getPercentsem4();
                        markssem5pgsem = obj.getMarkssem5();
                        outofsem5pgsem = obj.getOutofsem5();
                        percentagesem5pgsem = obj.getPercentsem5();
                        markssem6pgsem = obj.getMarkssem6();
                        outofsem6pgsem = obj.getOutofsem6();
                        percentagesem6pgsem = obj.getPercentsem6();
                        aggregatepgsem = obj.getAggregatepgsem();
                        coursepgsem = obj.getSelectedCoursepgsem();
                        streampgsem = obj.getSelectedStreampgsem();
                        universitypgsem = obj.getSelectedUniversitypgsem();
                        collegenamepgsem = obj.getSchoolnamepgsemester();
                        yearofpassingpgsem = obj.getMonthandyearofpassingpgsem();
                        studentData.setMarkssem1pgsem(markssem1pgsem);
                        studentData.setOutofsem1pgsem(outofsem1pgsem);
                        studentData.setPercentagesem1pgsem(percentagesem1pgsem);
                        studentData.setMarkssem2pgsem(markssem2pgsem);
                        studentData.setOutofsem2pgsem(outofsem2pgsem);
                        studentData.setPercentagesem2pgsem(percentagesem2pgsem);
                        studentData.setMarkssem3pgsem(markssem3pgsem);
                        studentData.setOutofsem3pgsem(outofsem3pgsem);
                        studentData.setPercentagesem3pgsem(percentagesem3pgsem);
                        studentData.setMarkssem4pgsem(markssem4pgsem);
                        studentData.setOutofsem4pgsem(outofsem4pgsem);
                        studentData.setPercentagesem4pgsem(percentagesem4pgsem);
                        studentData.setMarkssem5pgsem(markssem5pgsem);
                        studentData.setOutofsem5pgsem(outofsem5pgsem);
                        studentData.setPercentagesem5pgsem(percentagesem5pgsem);
                        studentData.setMarkssem6pgsem(markssem6pgsem);
                        studentData.setOutofsem6pgsem(outofsem6pgsem);
                        studentData.setPercentagesem6pgsem(percentagesem6pgsem);
                        studentData.setAggregatepgsem(aggregatepgsem);
                        studentData.setCoursepgsem(coursepgsem);
                        studentData.setStreampgsem(streampgsem);
                        studentData.setUniversitypgsem(universitypgsem);
                        studentData.setCollegenamepgsem(collegenamepgsem);
                        studentData.setYearofpassingpgsem(yearofpassingpgsem);
                    }
                    s = json.getString("pgyear");
                    if (s.equals("found")) {
                        Log.d("cricket", " VVS Sunil Gavskar  coming to bat");
                        found_pgyear = 1;
                        PgYear obj = (PgYear) fromString(json.getString("pgyeardata"), MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));
                        marksyear1pgyear = obj.getMarksyear1();
                        outofyear1pgyear = obj.getOutofyear1();
                        percentageyear1pgyear = obj.getPercentyear1();
                        marksyear2pgyear = obj.getMarksyear2();
                        outofyear2pgyear = obj.getOutofyear2();
                        percentageyear2pgyear = obj.getPercentyear2();
                        marksyear3pgyear = obj.getMarksyear3();
                        outofyear3pgyear = obj.getOutofyear3();
                        percentageyear3pgyear = obj.getPercentyear3();
                        aggregatepgyear = obj.getAggregatepgyear();
                        coursepgyear = obj.getSelectedCoursepgyear();
                        streampgyear = obj.getSelectedStreampgyear();
                        universitypgyear = obj.getSelectedUniversitypgyear();
                        collegenamepgyear = obj.getSchoolnamepgyears();
                        yearofpassingpgyear = obj.getMonthandyearofpassingpgyear();
                        studentData.setMarksyear1pgyear(marksyear1pgyear);
                        studentData.setOutofyear1pgyear(outofyear1pgyear);
                        studentData.setPercentageyear1pgyear(percentageyear1pgyear);
                        studentData.setMarksyear2pgyear(marksyear2pgyear);
                        studentData.setOutofyear2pgyear(outofyear2pgyear);
                        studentData.setPercentageyear2pgyear(percentageyear2pgyear);
                        studentData.setMarksyear3pgyear(marksyear3pgyear);
                        studentData.setOutofyear3pgyear(outofyear3pgyear);
                        studentData.setPercentageyear3pgyear(percentageyear3pgyear);
                        studentData.setAggregatepgyear(aggregatepgyear);
                        studentData.setCoursepgyear(coursepgyear);
                        studentData.setStreampgyear(streampgyear);
                        studentData.setUniversitypgyear(universitypgyear);
                        studentData.setCollegenamepgyear(collegenamepgyear);
                        studentData.setYearofpassingpgyear(yearofpassingpgyear);
                    }
                    s = json.getString("projects");
                    if (s.equals("found")) {
                        Log.d("cricket", " suresh raina coming to bat");
                        found_projects = 1;
                        ArrayList<Projects> projectsList = (ArrayList<Projects>) fromString(json.getString("projectsdata"), MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));
                        Projects obj1 = projectsList.get(0);
                        Projects obj2 = projectsList.get(1);
                        Projects obj3 = projectsList.get(2);
                        Projects obj4 = projectsList.get(3);
                        Projects obj5 = projectsList.get(4);
                        Projects obj6 = projectsList.get(5);
                        Projects obj7 = projectsList.get(6);
                        Projects obj8 = projectsList.get(7);
                        Projects obj9 = projectsList.get(8);
                        Projects obj10 = projectsList.get(9);

                        proj1 = obj1.getProj1();
                        domain1 = obj1.getDomain1();
                        team1 = obj1.getTeam1();
                        duration1 = obj1.getDuration1();

                        proj2 = obj2.getProj1();
                        domain2 = obj2.getDomain1();
                        team2 = obj2.getTeam1();
                        duration2 = obj2.getDuration1();

                        proj3 = obj3.getProj1();
                        domain3 = obj3.getDomain1();
                        team3 = obj3.getTeam1();
                        duration3 = obj3.getDuration1();

                        proj4 = obj4.getProj1();
                        domain4 = obj4.getDomain1();
                        team4 = obj4.getTeam1();
                        duration4 = obj4.getDuration1();

                        proj5 = obj5.getProj1();
                        domain5 = obj5.getDomain1();
                        team5 = obj5.getTeam1();
                        duration5 = obj5.getDuration1();

                        proj6 = obj6.getProj1();
                        domain6 = obj6.getDomain1();
                        team6 = obj6.getTeam1();
                        duration6 = obj6.getDuration1();

                        proj7 = obj7.getProj1();
                        domain7 = obj7.getDomain1();
                        team7 = obj7.getTeam1();
                        duration7 = obj7.getDuration1();

                        proj8 = obj8.getProj1();
                        domain8 = obj8.getDomain1();
                        team8 = obj8.getTeam1();
                        duration8 = obj8.getDuration1();

                        proj9 = obj9.getProj1();
                        domain9 = obj9.getDomain1();
                        team9 = obj9.getTeam1();
                        duration9 = obj9.getDuration1();

                        proj10 = obj10.getProj1();
                        domain10 = obj10.getDomain1();
                        team10 = obj10.getTeam1();
                        duration10 = obj10.getDuration1();

                        studentData.setProj1(proj1);
                        studentData.setDomain1(domain1);
                        studentData.setTeam1(team1);
                        studentData.setDuration1(duration1);
                        studentData.setProj2(proj2);
                        studentData.setDomain2(domain2);
                        studentData.setTeam2(team2);
                        studentData.setDuration2(duration2);
                        studentData.setProj3(proj3);
                        studentData.setDomain3(domain3);
                        studentData.setTeam3(team3);
                        studentData.setDuration3(duration3);
                        studentData.setProj4(proj4);
                        studentData.setDomain4(domain4);
                        studentData.setTeam4(team4);
                        studentData.setDuration4(duration4);
                        studentData.setProj5(proj5);
                        studentData.setDomain5(domain5);
                        studentData.setTeam5(team5);
                        studentData.setDuration5(duration5);
                        studentData.setProj6(proj6);
                        studentData.setDomain6(domain6);
                        studentData.setTeam6(team6);
                        studentData.setDuration6(duration6);
                        studentData.setProj7(proj7);
                        studentData.setDomain7(domain7);
                        studentData.setTeam7(team7);
                        studentData.setDuration7(duration7);
                        studentData.setProj8(proj8);
                        studentData.setDomain8(domain8);
                        studentData.setTeam8(team8);
                        studentData.setDuration8(duration8);
                        studentData.setProj9(proj9);
                        studentData.setDomain9(domain9);
                        studentData.setTeam9(team9);
                        studentData.setDuration9(duration9);
                        studentData.setProj10(proj10);
                        studentData.setDomain10(domain10);
                        studentData.setTeam10(team10);
                        studentData.setDuration10(duration10);

                        if (!proj4.equals(""))
                            proj_count = 1;
                        if (!proj5.equals(""))
                            proj_count = 2;
                        if (!proj6.equals(""))
                            proj_count = 3;
                        if (!proj7.equals(""))
                            proj_count = 4;
                        if (!proj8.equals(""))
                            proj_count = 5;
                        if (!proj9.equals(""))
                            proj_count = 6;
                        if (!proj10.equals(""))
                            proj_count = 7;
                    }

                    s = json.getString("knownlang");
                    if (s.equals("found")) {
                        found_lang = 1;
                        Log.d("cricket", " ajinkya rahane coming to bat");
                        ArrayList<KnownLangs> knownLangsList = (ArrayList<KnownLangs>) fromString(json.getString("knownlangdata"), MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));

                        KnownLangs obj1 = knownLangsList.get(0);
                        KnownLangs obj2 = knownLangsList.get(1);
                        KnownLangs obj3 = knownLangsList.get(2);
                        KnownLangs obj4 = knownLangsList.get(3);
                        KnownLangs obj5 = knownLangsList.get(4);
                        KnownLangs obj6 = knownLangsList.get(5);
                        KnownLangs obj7 = knownLangsList.get(6);
                        KnownLangs obj8 = knownLangsList.get(7);
                        KnownLangs obj9 = knownLangsList.get(8);
                        KnownLangs obj10 = knownLangsList.get(9);

                        lang1 = obj1.getKnownlang();
                        proficiency1 = obj1.getProficiency();
                        lang2 = obj2.getKnownlang();
                        proficiency2 = obj2.getProficiency();
                        lang3 = obj3.getKnownlang();
                        proficiency3 = obj3.getProficiency();
                        lang4 = obj4.getKnownlang();
                        proficiency4 = obj4.getProficiency();
                        lang5 = obj5.getKnownlang();
                        proficiency5 = obj5.getProficiency();
                        lang6 = obj6.getKnownlang();
                        proficiency6 = obj6.getProficiency();
                        lang7 = obj7.getKnownlang();
                        proficiency7 = obj7.getProficiency();
                        lang8 = obj8.getKnownlang();
                        proficiency8 = obj8.getProficiency();
                        lang9 = obj9.getKnownlang();
                        proficiency9 = obj9.getProficiency();
                        lang10 = obj10.getKnownlang();
                        proficiency10 = obj10.getProficiency();

                        studentData.setLang1(lang1);
                        studentData.setProficiency1(proficiency1);
                        studentData.setLang2(lang2);
                        studentData.setProficiency2(proficiency2);
                        studentData.setLang3(lang3);
                        studentData.setProficiency3(proficiency3);
                        studentData.setLang4(lang4);
                        studentData.setProficiency4(proficiency4);
                        studentData.setLang5(lang5);
                        studentData.setProficiency5(proficiency5);
                        studentData.setLang6(lang6);
                        studentData.setProficiency6(proficiency6);
                        studentData.setLang7(lang7);
                        studentData.setProficiency7(proficiency7);
                        studentData.setLang8(lang8);
                        studentData.setProficiency8(proficiency8);
                        studentData.setLang9(lang9);
                        studentData.setProficiency9(proficiency9);
                        studentData.setLang10(lang10);
                        studentData.setProficiency10(proficiency10);


                        if (!lang4.equals("") && !lang4.equals("- Select Language -"))
                            lang_count = 1;
                        if (!lang5.equals("") && !lang5.equals("- Select Language -"))
                            lang_count = 2;
                        if (!lang6.equals("") && !lang6.equals("- Select Language -"))
                            lang_count = 3;
                        if (!lang7.equals("") && !lang7.equals("- Select Language -"))
                            lang_count = 4;
                        if (!lang8.equals("") && !lang8.equals("- Select Language -"))
                            lang_count = 5;
                        if (!lang9.equals("") && !lang9.equals("- Select Language -"))
                            lang_count = 6;
                        if (!lang10.equals("") && !lang10.equals("- Select Language -"))
                            lang_count = 7;


                    }
                    s = json.getString("certificates");
                    if (s.equals("found")) {
                        found_certificates = 1;
                        Log.d("cricket", " cheteshwar pujara coming to bat");
                        ArrayList<Certificates> certificatesList = (ArrayList<Certificates>) fromString(json.getString("certificatesdata"), MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));

                        Certificates obj1 = certificatesList.get(0);
                        Certificates obj2 = certificatesList.get(1);
                        Certificates obj3 = certificatesList.get(2);
                        Certificates obj4 = certificatesList.get(3);
                        Certificates obj5 = certificatesList.get(4);
                        Certificates obj6 = certificatesList.get(5);
                        Certificates obj7 = certificatesList.get(6);
                        Certificates obj8 = certificatesList.get(7);
                        Certificates obj9 = certificatesList.get(8);
                        Certificates obj10 = certificatesList.get(9);

                        title1 = obj1.getTitle();
                        issuer1 = obj1.getIssuer();
                        license1 = obj1.getLicense();
                        startdate1certificate = obj1.getStartdate();
                        enddate1certificate = obj1.getEnddate();
                        willexpire1certificate = obj1.getWillexpire();

                        title2 = obj2.getTitle();
                        issuer2 = obj2.getIssuer();
                        license2 = obj2.getLicense();
                        startdate2certificate = obj2.getStartdate();
                        enddate2certificate = obj2.getEnddate();
                        willexpire2certificate = obj2.getWillexpire();

                        title3 = obj3.getTitle();
                        issuer3 = obj3.getIssuer();
                        license3 = obj3.getLicense();
                        startdate3certificate = obj3.getStartdate();
                        enddate3certificate = obj3.getEnddate();
                        willexpire3certificate = obj3.getWillexpire();

                        title4 = obj4.getTitle();
                        issuer4 = obj4.getIssuer();
                        license4 = obj4.getLicense();
                        startdate4certificate = obj4.getStartdate();
                        enddate4certificate = obj4.getEnddate();
                        willexpire4certificate = obj4.getWillexpire();

                        title5 = obj5.getTitle();
                        issuer5 = obj5.getIssuer();
                        license5 = obj5.getLicense();
                        startdate5certificate = obj5.getStartdate();
                        enddate5certificate = obj5.getEnddate();
                        willexpire5certificate = obj5.getWillexpire();

                        title6 = obj6.getTitle();
                        issuer6 = obj6.getIssuer();
                        license6 = obj6.getLicense();
                        startdate6certificate = obj6.getStartdate();
                        enddate6certificate = obj6.getEnddate();
                        willexpire6certificate = obj6.getWillexpire();

                        title7 = obj7.getTitle();
                        issuer7 = obj7.getIssuer();
                        license7 = obj7.getLicense();
                        startdate7certificate = obj7.getStartdate();
                        enddate7certificate = obj7.getEnddate();
                        willexpire7certificate = obj7.getWillexpire();

                        title8 = obj8.getTitle();
                        issuer8 = obj8.getIssuer();
                        license8 = obj8.getLicense();
                        startdate8certificate = obj8.getStartdate();
                        enddate8certificate = obj8.getEnddate();
                        willexpire8certificate = obj8.getWillexpire();

                        title9 = obj9.getTitle();
                        issuer9 = obj9.getIssuer();
                        license9 = obj9.getLicense();
                        startdate9certificate = obj9.getStartdate();
                        enddate9certificate = obj9.getEnddate();
                        willexpire9certificate = obj9.getWillexpire();

                        title10 = obj10.getTitle();
                        issuer10 = obj10.getIssuer();
                        license10 = obj10.getLicense();
                        startdate10certificate = obj10.getStartdate();
                        enddate10certificate = obj10.getEnddate();
                        willexpire10certificate = obj10.getWillexpire();

                        studentData.setTitle1(title1);
                        studentData.setIssuer1(issuer1);
                        studentData.setLicense1(license1);
                        studentData.setStartdate1certificate(startdate1certificate);
                        studentData.setEnddate1certificate(enddate1certificate);
                        studentData.setWillexpire1certificate(willexpire1certificate);
                        studentData.setTitle2(title2);
                        studentData.setIssuer2(issuer2);
                        studentData.setLicense2(license2);
                        studentData.setStartdate2certificate(startdate2certificate);
                        studentData.setEnddate2certificate(enddate2certificate);
                        studentData.setWillexpire2certificate(willexpire2certificate);
                        studentData.setTitle3(title3);
                        studentData.setIssuer3(issuer3);
                        studentData.setLicense3(license3);
                        studentData.setStartdate3certificate(startdate3certificate);
                        studentData.setEnddate3certificate(enddate3certificate);
                        studentData.setWillexpire3certificate(willexpire3certificate);
                        studentData.setTitle4(title4);
                        studentData.setIssuer4(issuer4);
                        studentData.setLicense4(license4);
                        studentData.setStartdate4certificate(startdate4certificate);
                        studentData.setEnddate4certificate(enddate4certificate);
                        studentData.setWillexpire4certificate(willexpire4certificate);
                        studentData.setTitle5(title5);
                        studentData.setIssuer5(issuer5);
                        studentData.setLicense5(license5);
                        studentData.setStartdate5certificate(startdate5certificate);
                        studentData.setEnddate5certificate(enddate5certificate);
                        studentData.setWillexpire5certificate(willexpire5certificate);
                        studentData.setTitle6(title6);
                        studentData.setIssuer6(issuer6);
                        studentData.setLicense6(license6);
                        studentData.setStartdate6certificate(startdate6certificate);
                        studentData.setEnddate6certificate(enddate6certificate);
                        studentData.setWillexpire6certificate(willexpire6certificate);
                        studentData.setTitle7(title7);
                        studentData.setIssuer7(issuer7);
                        studentData.setLicense7(license7);
                        studentData.setStartdate7certificate(startdate7certificate);
                        studentData.setEnddate7certificate(enddate7certificate);
                        studentData.setWillexpire7certificate(willexpire7certificate);
                        studentData.setTitle8(title8);
                        studentData.setIssuer8(issuer8);
                        studentData.setLicense8(license8);
                        studentData.setStartdate8certificate(startdate8certificate);
                        studentData.setEnddate8certificate(enddate8certificate);
                        studentData.setWillexpire8certificate(willexpire8certificate);
                        studentData.setTitle9(title9);
                        studentData.setIssuer9(issuer9);
                        studentData.setLicense9(license9);
                        studentData.setStartdate9certificate(startdate9certificate);
                        studentData.setEnddate9certificate(enddate9certificate);
                        studentData.setWillexpire9certificate(willexpire9certificate);
                        studentData.setTitle10(title10);
                        studentData.setIssuer10(issuer10);
                        studentData.setLicense10(license10);
                        studentData.setStartdate10certificate(startdate10certificate);
                        studentData.setEnddate10certificate(enddate10certificate);
                        studentData.setWillexpire10certificate(willexpire10certificate);

                        if (!title4.equals(""))
                            cert_count = 1;
                        if (!title5.equals(""))
                            cert_count = 2;
                        if (!title6.equals(""))
                            cert_count = 3;
                        if (!title7.equals(""))
                            cert_count = 4;
                        if (!title8.equals(""))
                            cert_count = 5;
                        if (!title9.equals(""))
                            cert_count = 6;
                        if (!title10.equals(""))
                            cert_count = 7;

                    }
                    s = json.getString("courses");
                    if (s.equals("found")) {
                        found_courses = 1;
                        Log.d("cricket", " kl rahul  coming to bat");
                        ArrayList<Courses> coursesList = (ArrayList<Courses>) fromString(json.getString("coursesdata"), MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));

                        Courses obj1 = coursesList.get(0);
                        Courses obj2 = coursesList.get(1);
                        Courses obj3 = coursesList.get(2);
                        Courses obj4 = coursesList.get(3);
                        Courses obj5 = coursesList.get(4);
                        Courses obj6 = coursesList.get(5);
                        Courses obj7 = coursesList.get(6);
                        Courses obj8 = coursesList.get(7);
                        Courses obj9 = coursesList.get(8);
                        Courses obj10 = coursesList.get(9);

                        course1 = obj1.getName();
                        inst1 = obj1.getInst();
                        fromdate1 = obj1.getFromdate();
                        todate1 = obj1.getTodate();

                        course2 = obj2.getName();
                        inst2 = obj2.getInst();
                        fromdate2 = obj2.getFromdate();
                        todate2 = obj2.getTodate();

                        course3 = obj3.getName();
                        inst3 = obj3.getInst();
                        fromdate3 = obj3.getFromdate();
                        todate3 = obj3.getTodate();

                        course4 = obj4.getName();
                        inst4 = obj4.getInst();
                        fromdate4 = obj4.getFromdate();
                        todate4 = obj4.getTodate();

                        course5 = obj5.getName();
                        inst5 = obj5.getInst();
                        fromdate5 = obj5.getFromdate();
                        todate5 = obj5.getTodate();

                        course6 = obj6.getName();
                        inst6 = obj6.getInst();
                        fromdate6 = obj6.getFromdate();
                        todate6 = obj6.getTodate();

                        course7 = obj7.getName();
                        inst7 = obj7.getInst();
                        fromdate7 = obj7.getFromdate();
                        todate7 = obj7.getTodate();

                        course8 = obj8.getName();
                        inst8 = obj8.getInst();
                        fromdate8 = obj8.getFromdate();
                        todate8 = obj8.getTodate();

                        course9 = obj9.getName();
                        inst9 = obj9.getInst();
                        fromdate9 = obj9.getFromdate();
                        todate9 = obj9.getTodate();

                        course10 = obj10.getName();
                        inst10 = obj10.getInst();
                        fromdate10 = obj10.getFromdate();
                        todate10 = obj10.getTodate();

                        studentData.setCourse1(course1);
                        studentData.setInst1(inst1);
                        studentData.setFromdate1(fromdate1);
                        studentData.setTodate1(todate1);
                        studentData.setCourse2(course2);
                        studentData.setInst2(inst2);
                        studentData.setFromdate2(fromdate2);
                        studentData.setTodate2(todate2);
                        studentData.setCourse3(course3);
                        studentData.setInst3(inst3);
                        studentData.setFromdate3(fromdate3);
                        studentData.setTodate3(todate3);
                        studentData.setCourse4(course4);
                        studentData.setInst4(inst4);
                        studentData.setFromdate4(fromdate4);
                        studentData.setTodate4(todate4);
                        studentData.setCourse5(course5);
                        studentData.setInst5(inst5);
                        studentData.setFromdate5(fromdate5);
                        studentData.setTodate5(todate5);
                        studentData.setCourse6(course6);
                        studentData.setInst6(inst6);
                        studentData.setFromdate6(fromdate6);
                        studentData.setTodate6(todate6);
                        studentData.setCourse7(course7);
                        studentData.setInst7(inst7);
                        studentData.setFromdate7(fromdate7);
                        studentData.setTodate7(todate7);
                        studentData.setCourse8(course8);
                        studentData.setInst8(inst8);
                        studentData.setFromdate8(fromdate8);
                        studentData.setTodate8(todate8);
                        studentData.setCourse9(course9);
                        studentData.setInst9(inst9);
                        studentData.setFromdate9(fromdate9);
                        studentData.setTodate9(todate9);
                        studentData.setCourse10(course10);
                        studentData.setInst10(inst10);
                        studentData.setFromdate10(fromdate10);
                        studentData.setTodate10(todate10);

                        if (!course4.equals(""))
                            courses_count = 1;

                        if (!course5.equals(""))
                            courses_count = 2;

                        if (!course6.equals(""))
                            courses_count = 3;

                        if (!course7.equals(""))
                            courses_count = 4;

                        if (!course8.equals(""))
                            courses_count = 5;

                        if (!course9.equals(""))
                            courses_count = 6;

                        if (!course10.equals(""))
                            courses_count = 7;
                    }
                    s = json.getString("skills");
                    if (s.equals("found")) {
                        found_skills = 1;
                        Log.d("cricket", " Ab divillers  coming to bat");
                        ArrayList<Skills> skillsList = (ArrayList<Skills>) fromString(json.getString("skillsdata"), MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));

                        Skills obj1 = skillsList.get(0);
                        Skills obj2 = skillsList.get(1);
                        Skills obj3 = skillsList.get(2);
                        Skills obj4 = skillsList.get(3);
                        Skills obj5 = skillsList.get(4);
                        Skills obj6 = skillsList.get(5);
                        Skills obj7 = skillsList.get(6);
                        Skills obj8 = skillsList.get(7);
                        Skills obj9 = skillsList.get(8);
                        Skills obj10 = skillsList.get(9);
                        Skills obj11 = skillsList.get(10);
                        Skills obj12 = skillsList.get(11);
                        Skills obj13 = skillsList.get(12);
                        Skills obj14 = skillsList.get(13);
                        Skills obj15 = skillsList.get(14);
                        Skills obj16 = skillsList.get(15);
                        Skills obj17 = skillsList.get(16);
                        Skills obj18 = skillsList.get(17);
                        Skills obj19 = skillsList.get(18);
                        Skills obj20 = skillsList.get(19);

                        skill1 = obj1.getSkill();
                        sproficiency1 = obj1.getProficiency();

                        skill2 = obj2.getSkill();
                        sproficiency2 = obj2.getProficiency();

                        skill3 = obj3.getSkill();
                        sproficiency3 = obj3.getProficiency();

                        skill4 = obj4.getSkill();
                        sproficiency4 = obj4.getProficiency();

                        skill5 = obj5.getSkill();
                        sproficiency5 = obj5.getProficiency();

                        skill6 = obj6.getSkill();
                        sproficiency6 = obj6.getProficiency();

                        skill7 = obj7.getSkill();
                        sproficiency7 = obj7.getProficiency();

                        skill8 = obj8.getSkill();
                        sproficiency8 = obj8.getProficiency();

                        skill9 = obj9.getSkill();
                        sproficiency9 = obj9.getProficiency();

                        skill10 = obj10.getSkill();
                        sproficiency10 = obj10.getProficiency();

                        skill11 = obj11.getSkill();
                        sproficiency11 = obj11.getProficiency();

                        skill12 = obj12.getSkill();
                        sproficiency12 = obj12.getProficiency();

                        skill13 = obj13.getSkill();
                        sproficiency13 = obj13.getProficiency();

                        skill14 = obj14.getSkill();
                        sproficiency14 = obj14.getProficiency();

                        skill15 = obj15.getSkill();
                        sproficiency15 = obj15.getProficiency();

                        skill16 = obj16.getSkill();
                        sproficiency16 = obj16.getProficiency();

                        skill17 = obj17.getSkill();
                        sproficiency17 = obj17.getProficiency();

                        skill18 = obj18.getSkill();
                        sproficiency18 = obj18.getProficiency();

                        skill19 = obj19.getSkill();
                        sproficiency19 = obj19.getProficiency();

                        skill20 = obj20.getSkill();
                        sproficiency20 = obj20.getProficiency();

                        studentData.setSkill1(skill1);
                        studentData.setSproficiency1(sproficiency1);
                        studentData.setSkill2(skill2);
                        studentData.setSproficiency2(sproficiency2);
                        studentData.setSkill3(skill3);
                        studentData.setSproficiency3(sproficiency3);
                        studentData.setSkill4(skill4);
                        studentData.setSproficiency4(sproficiency4);
                        studentData.setSkill5(skill5);
                        studentData.setSproficiency5(sproficiency5);
                        studentData.setSkill6(skill6);
                        studentData.setSproficiency6(sproficiency6);
                        studentData.setSkill7(skill7);
                        studentData.setSproficiency7(sproficiency7);
                        studentData.setSkill8(skill8);
                        studentData.setSproficiency8(sproficiency8);
                        studentData.setSkill9(skill9);
                        studentData.setSproficiency9(sproficiency9);
                        studentData.setSkill10(skill10);
                        studentData.setSproficiency10(sproficiency10);
                        studentData.setSkill11(skill11);
                        studentData.setSproficiency11(sproficiency11);
                        studentData.setSkill12(skill12);
                        studentData.setSproficiency12(sproficiency12);
                        studentData.setSkill13(skill13);
                        studentData.setSproficiency13(sproficiency13);
                        studentData.setSkill14(skill14);
                        studentData.setSproficiency14(sproficiency14);
                        studentData.setSkill15(skill15);
                        studentData.setSproficiency15(sproficiency15);
                        studentData.setSkill16(skill16);
                        studentData.setSproficiency16(sproficiency16);
                        studentData.setSkill17(skill17);
                        studentData.setSproficiency17(sproficiency17);
                        studentData.setSkill18(skill18);
                        studentData.setSproficiency18(sproficiency18);
                        studentData.setSkill19(skill19);
                        studentData.setSproficiency19(sproficiency19);
                        studentData.setSkill20(skill20);
                        studentData.setSproficiency20(sproficiency20);

                        if (!skill4.equals(""))
                            skills_count = 1;

                        if (!skill5.equals(""))
                            skills_count = 2;

                        if (!skill6.equals(""))
                            skills_count = 3;

                        if (!skill7.equals(""))
                            skills_count = 4;

                        if (!skill8.equals(""))
                            skills_count = 5;

                        if (!skill9.equals(""))
                            skills_count = 6;

                        if (!skill10.equals(""))
                            skills_count = 7;

                        if (!skill11.equals(""))
                            skills_count = 8;

                        if (!skill12.equals(""))
                            skills_count = 9;

                        if (!skill13.equals(""))
                            skills_count = 10;

                        if (!skill14.equals(""))
                            skills_count = 11;

                        if (!skill15.equals(""))
                            skills_count = 12;

                        if (!skill16.equals(""))
                            skills_count = 13;

                        if (!skill17.equals(""))
                            skills_count = 14;

                        if (!skill18.equals(""))
                            skills_count = 15;

                        if (!skill19.equals(""))
                            skills_count = 16;

                        if (!skill20.equals(""))
                            skills_count = 17;

                    }
                    s = json.getString("honors");
                    if (s.equals("found")) {
                        found_honors = 1;
                        Log.d("cricket", " hardik pandya coming to bat");
                        ArrayList<Honors> honorsList = (ArrayList<Honors>) fromString(json.getString("honorsdata"), MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));

                        Honors obj1 = honorsList.get(0);
                        Honors obj2 = honorsList.get(1);
                        Honors obj3 = honorsList.get(2);
                        Honors obj4 = honorsList.get(3);
                        Honors obj5 = honorsList.get(4);
                        Honors obj6 = honorsList.get(5);
                        Honors obj7 = honorsList.get(6);
                        Honors obj8 = honorsList.get(7);
                        Honors obj9 = honorsList.get(8);
                        Honors obj10 = honorsList.get(9);

                        htitle1 = obj1.getTitle();
                        hissuer1 = obj1.getIssuer();
                        hdescription1 = obj1.getDescription();
                        yearofhonor1 = obj1.getYearofhonor();

                        htitle2 = obj2.getTitle();
                        hissuer2 = obj2.getIssuer();
                        hdescription2 = obj2.getDescription();
                        yearofhonor2 = obj2.getYearofhonor();

                        htitle3 = obj3.getTitle();
                        hissuer3 = obj3.getIssuer();
                        hdescription3 = obj3.getDescription();
                        yearofhonor3 = obj3.getYearofhonor();

                        htitle4 = obj4.getTitle();
                        hissuer4 = obj4.getIssuer();
                        hdescription4 = obj4.getDescription();
                        yearofhonor4 = obj4.getYearofhonor();

                        htitle5 = obj5.getTitle();
                        hissuer5 = obj5.getIssuer();
                        hdescription5 = obj5.getDescription();
                        yearofhonor5 = obj5.getYearofhonor();

                        htitle6 = obj6.getTitle();
                        hissuer6 = obj6.getIssuer();
                        hdescription6 = obj6.getDescription();
                        yearofhonor6 = obj6.getYearofhonor();

                        htitle7 = obj7.getTitle();
                        hissuer7 = obj7.getIssuer();
                        hdescription7 = obj7.getDescription();
                        yearofhonor7 = obj7.getYearofhonor();

                        htitle8 = obj8.getTitle();
                        hissuer8 = obj8.getIssuer();
                        hdescription8 = obj8.getDescription();
                        yearofhonor8 = obj8.getYearofhonor();

                        htitle9 = obj9.getTitle();
                        hissuer9 = obj9.getIssuer();
                        hdescription9 = obj9.getDescription();
                        yearofhonor9 = obj9.getYearofhonor();

                        htitle10 = obj10.getTitle();
                        hissuer10 = obj10.getIssuer();
                        hdescription10 = obj10.getDescription();
                        yearofhonor10 = obj10.getYearofhonor();

                        studentData.setHtitle1(htitle1);
                        studentData.setHissuer1(hissuer1);
                        studentData.setHdescription1(hdescription1);
                        studentData.setYearofhonor1(yearofhonor1);
                        studentData.setHtitle2(htitle2);
                        studentData.setHissuer2(hissuer2);
                        studentData.setHdescription2(hdescription2);
                        studentData.setYearofhonor2(yearofhonor2);
                        studentData.setHtitle3(htitle3);
                        studentData.setHissuer3(hissuer3);
                        studentData.setHdescription3(hdescription3);
                        studentData.setYearofhonor3(yearofhonor3);
                        studentData.setHtitle4(htitle4);
                        studentData.setHissuer4(hissuer4);
                        studentData.setHdescription4(hdescription4);
                        studentData.setYearofhonor4(yearofhonor4);
                        studentData.setHtitle5(htitle5);
                        studentData.setHissuer5(hissuer5);
                        studentData.setHdescription5(hdescription5);
                        studentData.setYearofhonor5(yearofhonor5);
                        studentData.setHtitle6(htitle6);
                        studentData.setHissuer6(hissuer6);
                        studentData.setHdescription6(hdescription6);
                        studentData.setYearofhonor6(yearofhonor6);
                        studentData.setHtitle7(htitle7);
                        studentData.setHissuer7(hissuer7);
                        studentData.setHdescription7(hdescription7);
                        studentData.setYearofhonor7(yearofhonor7);
                        studentData.setHtitle8(htitle8);
                        studentData.setHissuer8(hissuer8);
                        studentData.setHdescription8(hdescription8);
                        studentData.setYearofhonor8(yearofhonor8);
                        studentData.setHtitle9(htitle9);
                        studentData.setHissuer9(hissuer9);
                        studentData.setHdescription9(hdescription9);
                        studentData.setYearofhonor9(yearofhonor9);
                        studentData.setHtitle10(htitle10);
                        studentData.setHissuer10(hissuer10);
                        studentData.setHdescription10(hdescription10);
                        studentData.setYearofhonor10(yearofhonor10);

                        if (!htitle4.equals(""))
                            honor_count = 1;

                        if (!htitle5.equals(""))
                            honor_count = 2;
                        if (!htitle6.equals(""))
                            honor_count = 3;
                        if (!htitle7.equals(""))
                            honor_count = 4;
                        if (!htitle8.equals(""))
                            honor_count = 5;
                        if (!htitle9.equals(""))
                            honor_count = 6;
                        if (!htitle10.equals(""))
                            honor_count = 7;


                    }

                    s = json.getString("patents");
                    if (s.equals("found")) {
                        found_patents = 1;
                        Log.d("cricket", " shreyash ayyar  coming to bat");
                        ArrayList<Patents> patentsList = (ArrayList<Patents>) fromString(json.getString("patentsdata"), MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));

                        Patents obj1 = patentsList.get(0);
                        Patents obj2 = patentsList.get(1);
                        Patents obj3 = patentsList.get(2);
                        Patents obj4 = patentsList.get(3);
                        Patents obj5 = patentsList.get(4);
                        Patents obj6 = patentsList.get(5);
                        Patents obj7 = patentsList.get(6);
                        Patents obj8 = patentsList.get(7);
                        Patents obj9 = patentsList.get(8);
                        Patents obj10 = patentsList.get(9);

                        ptitle1 = obj1.getTitle();
                        pappno1 = obj1.getAppno();
                        pselectedcountry1 = obj1.getPatoffice();
                        pinventor1 = obj1.getInventor();
                        issuedorpending1 = obj1.getIssuedorpending();
                        pissue1 = obj1.getIssue();
                        pfiling1 = obj1.getFiling();
                        purl1 = obj1.getUrl();
                        pdescription1 = obj1.getDescription();

                        ptitle2 = obj2.getTitle();
                        pappno2 = obj2.getAppno();
                        pselectedcountry2 = obj2.getPatoffice();
                        pinventor2 = obj2.getInventor();
                        issuedorpending2 = obj2.getIssuedorpending();
                        pissue2 = obj2.getIssue();
                        pfiling2 = obj2.getFiling();
                        purl2 = obj2.getUrl();
                        pdescription2 = obj2.getDescription();

                        ptitle3 = obj3.getTitle();
                        pappno3 = obj3.getAppno();
                        pselectedcountry3 = obj3.getPatoffice();
                        pinventor3 = obj3.getInventor();
                        issuedorpending3 = obj3.getIssuedorpending();
                        pissue3 = obj3.getIssue();
                        pfiling3 = obj3.getFiling();
                        purl3 = obj3.getUrl();
                        pdescription3 = obj3.getDescription();

                        ptitle4 = obj4.getTitle();
                        pappno4 = obj4.getAppno();
                        pselectedcountry4 = obj4.getPatoffice();
                        pinventor4 = obj4.getInventor();
                        issuedorpending4 = obj4.getIssuedorpending();
                        pissue4 = obj4.getIssue();
                        pfiling4 = obj4.getFiling();
                        purl4 = obj4.getUrl();
                        pdescription4 = obj4.getDescription();

                        ptitle5 = obj5.getTitle();
                        pappno5 = obj5.getAppno();
                        pselectedcountry5 = obj5.getPatoffice();
                        pinventor5 = obj5.getInventor();
                        issuedorpending5 = obj5.getIssuedorpending();
                        pissue5 = obj5.getIssue();
                        pfiling5 = obj5.getFiling();
                        purl5 = obj5.getUrl();
                        pdescription5 = obj5.getDescription();

                        ptitle6 = obj6.getTitle();
                        pappno6 = obj6.getAppno();
                        pselectedcountry6 = obj6.getPatoffice();
                        pinventor6 = obj6.getInventor();
                        issuedorpending6 = obj6.getIssuedorpending();
                        pissue6 = obj6.getIssue();
                        pfiling6 = obj6.getFiling();
                        purl6 = obj6.getUrl();
                        pdescription6 = obj6.getDescription();

                        ptitle7 = obj7.getTitle();
                        pappno7 = obj7.getAppno();
                        pselectedcountry7 = obj7.getPatoffice();
                        pinventor7 = obj7.getInventor();
                        issuedorpending7 = obj7.getIssuedorpending();
                        pissue7 = obj7.getIssue();
                        pfiling7 = obj7.getFiling();
                        purl7 = obj7.getUrl();
                        pdescription7 = obj7.getDescription();

                        ptitle8 = obj8.getTitle();
                        pappno8 = obj8.getAppno();
                        pselectedcountry8 = obj8.getPatoffice();
                        pinventor8 = obj8.getInventor();
                        issuedorpending8 = obj8.getIssuedorpending();
                        pissue8 = obj8.getIssue();
                        pfiling8 = obj8.getFiling();
                        purl8 = obj8.getUrl();
                        pdescription8 = obj8.getDescription();

                        ptitle9 = obj9.getTitle();
                        pappno9 = obj9.getAppno();
                        pselectedcountry9 = obj9.getPatoffice();
                        pinventor9 = obj9.getInventor();
                        issuedorpending9 = obj9.getIssuedorpending();
                        pissue9 = obj9.getIssue();
                        pfiling9 = obj9.getFiling();
                        purl9 = obj9.getUrl();
                        pdescription9 = obj9.getDescription();

                        ptitle10 = obj10.getTitle();
                        pappno10 = obj10.getAppno();
                        pselectedcountry10 = obj10.getPatoffice();
                        pinventor10 = obj10.getInventor();
                        issuedorpending10 = obj10.getIssuedorpending();
                        pissue10 = obj10.getIssue();
                        pfiling10 = obj10.getFiling();
                        purl10 = obj10.getUrl();
                        pdescription10 = obj10.getDescription();

                        studentData.setPtitle1(ptitle1);
                        studentData.setPappno1(pappno1);
                        studentData.setPinventor1(pinventor1);
                        studentData.setPissue1(pissue1);
                        studentData.setPfiling1(pfiling1);
                        studentData.setPurl1(purl1);
                        studentData.setPdescription1(pdescription1);
                        studentData.setPselectedcountry1(pselectedcountry1);
                        studentData.setIssuedorpending1(issuedorpending1);
                        studentData.setPtitle2(ptitle2);
                        studentData.setPappno2(pappno2);
                        studentData.setPinventor2(pinventor2);
                        studentData.setPissue2(pissue2);
                        studentData.setPfiling2(pfiling2);
                        studentData.setPurl2(purl2);
                        studentData.setPdescription2(pdescription2);
                        studentData.setPselectedcountry2(pselectedcountry2);
                        studentData.setIssuedorpending2(issuedorpending2);
                        studentData.setPtitle3(ptitle3);
                        studentData.setPappno3(pappno3);
                        studentData.setPinventor3(pinventor3);
                        studentData.setPissue3(pissue3);
                        studentData.setPfiling3(pfiling3);
                        studentData.setPurl3(purl3);
                        studentData.setPdescription3(pdescription3);
                        studentData.setPselectedcountry3(pselectedcountry3);
                        studentData.setIssuedorpending3(issuedorpending3);
                        studentData.setPtitle4(ptitle4);
                        studentData.setPappno4(pappno4);
                        studentData.setPinventor4(pinventor4);
                        studentData.setPissue4(pissue4);
                        studentData.setPfiling4(pfiling4);
                        studentData.setPurl4(purl4);
                        studentData.setPdescription4(pdescription4);
                        studentData.setPselectedcountry4(pselectedcountry4);
                        studentData.setIssuedorpending4(issuedorpending4);
                        studentData.setPtitle5(ptitle5);
                        studentData.setPappno5(pappno5);
                        studentData.setPinventor5(pinventor5);
                        studentData.setPissue5(pissue5);
                        studentData.setPfiling5(pfiling5);
                        studentData.setPurl5(purl5);
                        studentData.setPdescription5(pdescription5);
                        studentData.setPselectedcountry5(pselectedcountry5);
                        studentData.setIssuedorpending5(issuedorpending5);
                        studentData.setPtitle6(ptitle6);
                        studentData.setPappno6(pappno6);
                        studentData.setPinventor6(pinventor6);
                        studentData.setPissue6(pissue6);
                        studentData.setPfiling6(pfiling6);
                        studentData.setPurl6(purl6);
                        studentData.setPdescription6(pdescription6);
                        studentData.setPselectedcountry6(pselectedcountry6);
                        studentData.setIssuedorpending6(issuedorpending6);
                        studentData.setPtitle7(ptitle7);
                        studentData.setPappno7(pappno7);
                        studentData.setPinventor7(pinventor7);
                        studentData.setPissue7(pissue7);
                        studentData.setPfiling7(pfiling7);
                        studentData.setPurl7(purl7);
                        studentData.setPdescription7(pdescription7);
                        studentData.setPselectedcountry7(pselectedcountry7);
                        studentData.setIssuedorpending7(issuedorpending7);
                        studentData.setPtitle8(ptitle8);
                        studentData.setPappno8(pappno8);
                        studentData.setPinventor8(pinventor8);
                        studentData.setPissue8(pissue8);
                        studentData.setPfiling8(pfiling8);
                        studentData.setPurl8(purl8);
                        studentData.setPdescription8(pdescription8);
                        studentData.setPselectedcountry8(pselectedcountry8);
                        studentData.setIssuedorpending8(issuedorpending8);
                        studentData.setPtitle9(ptitle9);
                        studentData.setPappno9(pappno9);
                        studentData.setPinventor9(pinventor9);
                        studentData.setPissue9(pissue9);
                        studentData.setPfiling9(pfiling9);
                        studentData.setPurl9(purl9);
                        studentData.setPdescription9(pdescription9);
                        studentData.setPselectedcountry9(pselectedcountry9);
                        studentData.setIssuedorpending9(issuedorpending9);
                        studentData.setPtitle10(ptitle10);
                        studentData.setPappno10(pappno10);
                        studentData.setPinventor10(pinventor10);
                        studentData.setPissue10(pissue10);
                        studentData.setPfiling10(pfiling10);
                        studentData.setPurl10(purl10);
                        studentData.setPdescription10(pdescription10);
                        studentData.setPselectedcountry10(pselectedcountry10);
                        studentData.setIssuedorpending10(issuedorpending10);

                        if (!ptitle4.equals(""))
                            patent_count = 1;

                        if (!ptitle5.equals(""))
                            patent_count = 2;
                        if (!ptitle6.equals(""))
                            patent_count = 3;
                        if (!ptitle7.equals(""))
                            patent_count = 4;
                        if (!ptitle8.equals(""))
                            patent_count = 5;
                        if (!ptitle9.equals(""))
                            patent_count = 6;
                        if (!ptitle10.equals(""))
                            patent_count = 7;

                    }
                    s = json.getString("publications");
                    if (s.equals("found")) {
                        found_publications = 1;
                        Log.d("cricket", " ambati rayadu  coming to bat");
                        ArrayList<Publications> publicationsList = (ArrayList<Publications>) fromString(json.getString("publicationsdata"), MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));

                        Publications obj1 = publicationsList.get(0);
                        Publications obj2 = publicationsList.get(1);
                        Publications obj3 = publicationsList.get(2);
                        Publications obj4 = publicationsList.get(3);
                        Publications obj5 = publicationsList.get(4);
                        Publications obj6 = publicationsList.get(5);
                        Publications obj7 = publicationsList.get(6);
                        Publications obj8 = publicationsList.get(7);
                        Publications obj9 = publicationsList.get(8);
                        Publications obj10 = publicationsList.get(9);

                        pubtitle1 = obj1.getTitle();
                        publication1 = obj1.getPublication();
                        author1 = obj1.getAuthor();
                        publicationdate1 = obj1.getPublicationdate();
                        puburl1 = obj1.getUrl();
                        pubdescription1 = obj1.getDescription();

                        pubtitle2 = obj2.getTitle();
                        publication2 = obj2.getPublication();
                        author2 = obj2.getAuthor();
                        publicationdate2 = obj2.getPublicationdate();
                        puburl2 = obj2.getUrl();
                        pubdescription2 = obj2.getDescription();

                        pubtitle3 = obj3.getTitle();
                        publication3 = obj3.getPublication();
                        author3 = obj3.getAuthor();
                        publicationdate3 = obj3.getPublicationdate();
                        puburl3 = obj3.getUrl();
                        pubdescription3 = obj3.getDescription();

                        pubtitle4 = obj4.getTitle();
                        publication4 = obj4.getPublication();
                        author4 = obj4.getAuthor();
                        publicationdate4 = obj4.getPublicationdate();
                        puburl4 = obj4.getUrl();
                        pubdescription4 = obj4.getDescription();

                        pubtitle5 = obj5.getTitle();
                        publication5 = obj5.getPublication();
                        author5 = obj5.getAuthor();
                        publicationdate5 = obj5.getPublicationdate();
                        puburl5 = obj5.getUrl();
                        pubdescription5 = obj5.getDescription();

                        pubtitle6 = obj6.getTitle();
                        publication6 = obj6.getPublication();
                        author6 = obj6.getAuthor();
                        publicationdate6 = obj6.getPublicationdate();
                        puburl6 = obj6.getUrl();
                        pubdescription6 = obj6.getDescription();

                        pubtitle7 = obj7.getTitle();
                        publication7 = obj7.getPublication();
                        author7 = obj7.getAuthor();
                        publicationdate7 = obj7.getPublicationdate();
                        puburl7 = obj7.getUrl();
                        pubdescription7 = obj7.getDescription();

                        pubtitle8 = obj8.getTitle();
                        publication8 = obj8.getPublication();
                        author8 = obj8.getAuthor();
                        publicationdate8 = obj8.getPublicationdate();
                        puburl8 = obj8.getUrl();
                        pubdescription8 = obj8.getDescription();

                        pubtitle9 = obj9.getTitle();
                        publication9 = obj9.getPublication();
                        author9 = obj9.getAuthor();
                        publicationdate9 = obj9.getPublicationdate();
                        puburl9 = obj9.getUrl();
                        pubdescription9 = obj9.getDescription();

                        pubtitle10 = obj10.getTitle();
                        publication10 = obj10.getPublication();
                        author10 = obj10.getAuthor();
                        publicationdate10 = obj10.getPublicationdate();
                        puburl10 = obj10.getUrl();
                        pubdescription10 = obj10.getDescription();

                        studentData.setPubtitle1(pubtitle1);
                        studentData.setPublication1(publication1);
                        studentData.setAuthor1(author1);
                        studentData.setPublicationdate1(publicationdate1);
                        studentData.setPuburl1(puburl1);
                        studentData.setPubdescription1(pubdescription1);
                        studentData.setPubtitle2(pubtitle2);
                        studentData.setPublication2(publication2);
                        studentData.setAuthor2(author2);
                        studentData.setPublicationdate2(publicationdate2);
                        studentData.setPubdescription2(pubdescription2);
                        studentData.setPubtitle3(pubtitle3);
                        studentData.setPublication3(publication3);
                        studentData.setAuthor3(author3);
                        studentData.setPublicationdate3(publicationdate3);
                        studentData.setPuburl3(puburl3);
                        studentData.setPubdescription3(pubdescription3);
                        studentData.setPubtitle4(pubtitle4);
                        studentData.setPublication4(publication4);
                        studentData.setAuthor4(author4);
                        studentData.setPublicationdate4(publicationdate4);
                        studentData.setPuburl4(puburl4);
                        studentData.setPubdescription4(pubdescription4);
                        studentData.setPubtitle5(pubtitle5);
                        studentData.setPublication5(publication5);
                        studentData.setAuthor5(author5);
                        studentData.setPublicationdate5(publicationdate5);
                        studentData.setPuburl5(puburl5);
                        studentData.setPubdescription5(pubdescription5);
                        studentData.setPubtitle6(pubtitle6);
                        studentData.setPublication6(publication6);
                        studentData.setAuthor6(author6);
                        studentData.setPublicationdate6(publicationdate6);
                        studentData.setPuburl6(puburl6);
                        studentData.setPubdescription6(pubdescription6);
                        studentData.setPubtitle7(pubtitle7);
                        studentData.setPublication7(publication7);
                        studentData.setAuthor7(author7);
                        studentData.setPublicationdate7(publicationdate7);
                        studentData.setPuburl7(puburl7);
                        studentData.setPubdescription7(pubdescription7);
                        studentData.setPubtitle8(pubtitle8);
                        studentData.setPublication8(publication8);
                        studentData.setAuthor8(author8);
                        studentData.setPublicationdate8(publicationdate8);
                        studentData.setPuburl8(puburl8);
                        studentData.setPubdescription8(pubdescription8);
                        studentData.setPubtitle9(pubtitle9);
                        studentData.setPublication9(publication9);
                        studentData.setAuthor9(author9);
                        studentData.setPublicationdate9(publicationdate9);
                        studentData.setPuburl9(puburl9);
                        studentData.setPubdescription9(pubdescription9);
                        studentData.setPubtitle10(pubtitle10);
                        studentData.setPublication10(publication10);
                        studentData.setAuthor10(author10);
                        studentData.setPublicationdate10(publicationdate10);
                        studentData.setPuburl10(puburl10);
                        studentData.setPubdescription10(pubdescription10);


                        if (!pubtitle4.equals(""))
                            public_count = 1;
                        if (!pubtitle5.equals(""))
                            public_count = 2;
                        if (!pubtitle6.equals(""))
                            public_count = 3;
                        if (!pubtitle7.equals(""))
                            public_count = 4;
                        if (!pubtitle8.equals(""))
                            public_count = 5;
                        if (!pubtitle9.equals(""))
                            public_count = 6;
                        if (!pubtitle10.equals(""))
                            public_count = 7;

                    }
                    s = json.getString("personal");
                    if (s.equals("found")) {
                        Log.d("cricket", " manish pandey  coming to bat");
                        found_personal = 1;
                        personaldataobject = json.getString("personalobj");
                        MyProfilePersonal obj2 = (MyProfilePersonal) fromString(personaldataobject, MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));

                        fname = obj2.fname;
                        mname = obj2.mname;
                        lname = obj2.sname;
                        nameasten = obj2.nameasten;
                        email2 = obj2.alternateemail;
                        mothername = obj2.mothername;
                        dob = obj2.dob;
                        gender = obj2.gender;
                        telephone = obj2.phone;
                        phone = obj2.mobile;
                        mobile2 = obj2.alternatemobile;
                        mothertongue = obj2.mothertongue;
                        hobbies = obj2.hobbies;
                        bloodgroup = obj2.bloodgroup;
                        category = obj2.category;
                        religion = obj2.religion;
                        caste = obj2.caste;
                        prn = obj2.prn;

                        addressline1 = obj2.addrline1c;
                        addressline2 = obj2.addrline2c;
                        addressline3 = obj2.addrline3c;

                        paddrline1 = obj2.addrline1p;
                        paddrline2 = obj2.addrline2p;
                        paddrline3 = obj2.addrline3p;
                        handicapped = obj2.handicapped;
                        sports = obj2.sports;
                        defenceex = obj2.defenceex;

                        studentData.setFname(fname);
                        studentData.setMname(mname);
                        studentData.setLname(lname);
                        studentData.setNameasten(nameasten);
                        studentData.setEmail2(email2);
                        studentData.setMothername(mothername);
                        studentData.setDob(dob);
                        studentData.setGender(gender);
                        studentData.setTelephone(telephone);
                        studentData.setPhone(phone);
                        studentData.setMobile2(mobile2);
                        studentData.setMothertongue(mothertongue);
                        studentData.setHobbies(hobbies);
                        studentData.setBloodgroup(bloodgroup);
                        studentData.setCategory(category);
                        studentData.setReligion(religion);
                        studentData.setCaste(caste);
                        studentData.setPrn(prn);
                        studentData.setLang1(lang1);
                        studentData.setLang2(lang2);
                        studentData.setLang3(lang3);
                        studentData.setLang4(lang4);
                        studentData.setLang5(lang5);
                        studentData.setLang6(lang6);
                        studentData.setLang7(lang7);
                        studentData.setLang8(lang8);
                        studentData.setLang9(lang9);
                        studentData.setLang10(lang10);
                        studentData.setAddressline1(addressline1);
                        studentData.setAddressline2(addressline2);
                        studentData.setAddressline3(addressline3);
                        studentData.setPaddrline1(paddrline1);
                        studentData.setPaddrline2(paddrline2);
                        studentData.setPaddrline3(paddrline3);
                        studentData.setHandicapped(handicapped);
                        studentData.setSports(sports);
                        studentData.setDefenceex(defenceex);

                    }
                    s = json.getString("contact_details");
                    if (s.equals("found")) {
                        found_contact_details = 1;
                        Log.d("cricket", " Dinesh kartik coming to bat");
                        contact_details_dataobject = json.getString("contact_detailsdata");

                        AdminContactDetailsModal obj2 = (AdminContactDetailsModal) fromString(contact_details_dataobject, MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));

                        fname = obj2.getFname();
                        lname = obj2.getLname();
                        email2 = obj2.getEmail2();
                        telephone = obj2.getPhone();
                        phone = obj2.getMobile();
                        mobile2 = obj2.getMobile2();
                        addressline1 = obj2.getAddressline1();
                        addressline2 = obj2.getAddressline2();
                        addressline3 = obj2.getAddressline3();

                        studentData.setFname(fname);
                        studentData.setLname(lname);
                        studentData.setEmail2(email2);
                        studentData.setTelephone(telephone);
                        studentData.setPhone(phone);
                        studentData.setMobile2(mobile2);
                        studentData.setAddressline1(addressline1);
                        studentData.setAddressline2(addressline2);
                        studentData.setAddressline3(addressline3);
                    }
                    s = json.getString("experiences");
                    if (s.equals("found")) {
                        Log.d("cricket", " Ravidra jadeja coming to bat");
                        found_exp = 1;
                        experiencesataobject = json.getString("experiencesdata");
                        ArrayList<Experiences> ExperiencesList = (ArrayList<Experiences>) fromString(experiencesataobject, MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));

                        Experiences obj1 = ExperiencesList.get(0);
                        Experiences obj2 = ExperiencesList.get(1);
                        Experiences obj3 = ExperiencesList.get(2);
                        Experiences obj4 = ExperiencesList.get(3);
                        Experiences obj5 = ExperiencesList.get(4);
                        Experiences obj6 = ExperiencesList.get(5);
                        Experiences obj7 = ExperiencesList.get(6);
                        Experiences obj8 = ExperiencesList.get(7);
                        Experiences obj9 = ExperiencesList.get(8);
                        Experiences obj10 = ExperiencesList.get(9);

                        posts1 = obj1.getPost();
                        inst1s1 = obj1.getInst();
                        fromdates1 = obj1.getFromdate();
                        todates1 = obj1.getTodate();

                        posts2 = obj2.getPost();
                        inst1s2 = obj2.getInst();
                        fromdates2 = obj2.getFromdate();
                        todates2 = obj2.getTodate();

                        posts3 = obj3.getPost();
                        inst1s3 = obj3.getInst();
                        fromdates3 = obj3.getFromdate();
                        todates3 = obj3.getTodate();

                        posts4 = obj4.getPost();
                        inst1s4 = obj4.getInst();
                        fromdates4 = obj4.getFromdate();
                        todates4 = obj4.getTodate();

                        posts5 = obj5.getPost();
                        inst1s5 = obj5.getInst();
                        fromdates5 = obj5.getFromdate();
                        todates5 = obj5.getTodate();

                        posts6 = obj6.getPost();
                        inst1s6 = obj6.getInst();
                        fromdates6 = obj6.getFromdate();
                        todates6 = obj6.getTodate();

                        posts7 = obj7.getPost();
                        inst1s7 = obj7.getInst();
                        fromdates7 = obj7.getFromdate();
                        todates7 = obj7.getTodate();

                        posts8 = obj8.getPost();
                        inst1s8 = obj8.getInst();
                        fromdates8 = obj8.getFromdate();
                        todates8 = obj8.getTodate();

                        posts9 = obj9.getPost();
                        inst1s9 = obj9.getInst();
                        fromdates9 = obj9.getFromdate();
                        todates9 = obj9.getTodate();

                        posts10 = obj10.getPost();
                        inst1s10 = obj10.getInst();
                        fromdates10 = obj10.getFromdate();
                        todates10 = obj10.getTodate();

                        a.setPost1e(posts1);
                        a.setInst1e(inst1s1);
                        a.setFromdate1e(fromdates1);
                        a.setTodate1e(todates1);

                        a.setPost2e(posts2);
                        a.setInst2e(inst1s2);
                        a.setFromdate2e(fromdates2);
                        a.setTodate2e(todates2);

                        a.setPost3e(posts3);
                        a.setInst3e(inst1s3);
                        a.setFromdate3e(fromdates3);
                        a.setTodate3e(todates3);

                        a.setPost4e(posts4);
                        a.setInst4e(inst1s4);
                        a.setFromdate4e(fromdates4);
                        a.setTodate4e(todates4);

                        a.setPost5e(posts5);
                        a.setInst5e(inst1s5);
                        a.setFromdate5e(fromdates5);
                        a.setTodate5e(todates5);

                        a.setPost6e(posts6);
                        a.setInst6e(inst1s6);
                        a.setFromdate6e(fromdates6);
                        a.setTodate6e(todates6);

                        a.setPost7e(posts7);
                        a.setInst7e(inst1s7);
                        a.setFromdate7e(fromdates7);
                        a.setTodate7e(todates7);

                        a.setPost8e(posts8);
                        a.setInst8e(inst1s8);
                        a.setFromdate8e(fromdates8);
                        a.setTodate8e(todates8);

                        a.setPost9e(posts9);
                        a.setInst9e(inst1s9);
                        a.setFromdate9e(fromdates9);
                        a.setTodate9e(todates9);

                        a.setPost10e(posts10);
                        a.setInst10e(inst1s10);
                        a.setFromdate10e(fromdates10);
                        a.setTodate10e(todates10);


                        if (!posts4.equals(""))
                            exps_count = 1;

                        if (!posts5.equals(""))
                            exps_count = 2;

                        if (!posts6.equals(""))
                            exps_count = 3;

                        if (!posts7.equals(""))
                            exps_count = 4;

                        if (!posts8.equals(""))
                            exps_count = 5;

                        if (!posts9.equals(""))
                            exps_count = 6;

                        if (!posts10.equals(""))
                            exps_count = 7;
                    }
                }

            } catch (Exception e) {
                Log.d("cricket", "Match 2 lost : " + e.getMessage());
                e.printStackTrace();
            }
            return map;
        }

        protected void onPostExecute(Bitmap result) {
            try {
                updateProgress.setVisibility(View.GONE);
                populateData();
                downloadImage();
                Log.d("TAG", "onPostExecute:  executed");
                swipe_refresh_layout.setRefreshing(false);
            } catch (Exception e) {
                Log.d("cricket", "Match 3 lost : " + e.getMessage());
                e.printStackTrace();

            }
        }
    }

    class DeleteProfile extends AsyncTask<String, String, String> {
        protected String doInBackground(String... param) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));
            json = jParser.makeHttpRequest(Z.url_remove_profile, "GET", params);
            try {
                resultofop = json.getString("info");

            } catch (Exception ex) {
                Log.d("cricket", "Match 4 lost " + ex.getMessage());
                ex.printStackTrace();

            }

            return resultofop;
        }

        @Override
        protected void onPostExecute(String result) {

            if (resultofop.equals("success")) {
                Toast.makeText(getActivity(), "Profile Picture removed..!", Toast.LENGTH_LONG).show();
                refreshContent();
                ((AlumniActivity) getActivity()).requestProfileImage();
            } else if (resultofop.equals("fail"))
                Toast.makeText(getActivity(), "Failed..!", Toast.LENGTH_LONG).show();

            else if (resultofop.equals("notfound"))
                Toast.makeText(getActivity(), "No Profile Picture..!", Toast.LENGTH_LONG).show();


        }
    }

    class Getsingnature extends AsyncTask<String, String, String> {

        protected String doInBackground(String... param) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));
            json = jParser.makeHttpRequest(Z.load_last_updated, "GET", params);
            try {
                signature = json.getString("lastupdated");
                ShouldAnimateProfile.photo =signature;
            } catch (Exception ex) {}
            return signature;
        }

        @Override
        protected void onPostExecute(String result) {
            Uri uri = new Uri.Builder()
                    .scheme("https")
                    .authority(Z.VPS_IP)
                    .path("AESTest/GetImage")
                    .appendQueryParameter("u", username)
                    .build();

            try {
            Glide.with(ShouldAnimateProfile.AlumniActivity)
                    .load(uri)
                    .crossFade()
                    .signature(new StringSignature(signature))
                    .listener(new RequestListener<Uri, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, Uri model, Target<GlideDrawable> target, boolean isFirstResource) {
                            updateProgress.setVisibility(View.GONE);
                            return false;
                        }
                        @Override
                        public boolean onResourceReady(GlideDrawable resource, Uri model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            updateProgress.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(myprofileimg);
            }
            catch (Exception e){
                Log.d("TAG", "onPostExecute: glide except");

            }

        }
    }
}
