package placeme.octopusites.com.placeme;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.appnext.banners.BannerAdRequest;
import com.appnext.banners.BannerView;
import com.appnext.base.Appnext;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import placeme.octopusites.com.placeme.modal.Projects;

import static placeme.octopusites.com.placeme.AES4all.OtoString;


public class ProjectsProfileTabFragment extends Fragment {
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Username = "nameKey";
    ArrayList<Projects> projectsList = new ArrayList<>();

    View addmoreproject;
    SharedPreferences sharedpreferences;
    String username, role;
    String digest1, digest2;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    int errorflag = 0,editproj=0;
    String resultofop = "";
    EditText proj1, domain1, team1, duration1, proj2, domain2, team2, duration2, proj3, domain3, team3, duration3, proj4, domain4, team4, duration4, proj5, domain5, team5, duration5, proj6, domain6, team6, duration6, proj7, domain7, team7, duration7, proj8, domain8, team8, duration8, proj9, domain9, team9, duration9, proj10, domain10, team10, duration10;
    TextInputLayout projinput1, domaininput1, teaminput1, durationinput1, projinput2, domaininput2, teaminput2, durationinput2, projinput3, domaininput3, teaminput3, durationinput3, projinput4, domaininput4, teaminput4, durationinput4, projinput5, domaininput5, teaminput5, durationinput5, projinput6, domaininput6, teaminput6, durationinput6, projinput7, domaininput7, teaminput7, durationinput7, projinput8, domaininput8, teaminput8, durationinput8, projinput9, domaininput9, teaminput9, durationinput9, projinput10, domaininput10, teaminput10, durationinput10;

    String sproj1 = "", sdomain1 = "", steam1 = "", sduration1 = "", sproj2 = "", sdomain2 = "", steam2 = "", sduration2 = "", sproj3 = "", sdomain3 = "", steam3 = "", sduration3 = "", sproj4 = "", sdomain4 = "", steam4 = "", sduration4 = "", sproj5 = "", sdomain5 = "", steam5 = "", sduration5 = "", sproj6 = "", sdomain6 = "", steam6 = "", sduration6 = "", sproj7 = "", sdomain7 = "", steam7 = "", sduration7 = "", sproj8 = "", sdomain8 = "", steam8 = "", sduration8 = "", sproj9 = "", sdomain9 = "", steam9 = "", sduration9 = "", sproj10 = "", sdomain10 = "", steam10 = "", sduration10 = "";
    String encproj1, encdomain1, encteam1, encduration1, encproj2, encdomain2, encteam2, encduration2, encproj3, encdomain3, encteam3, encduration3, encproj4, encdomain4, encteam4, encduration4, encproj5, encdomain5, encteam5, encduration5, encproj6, encdomain6, encteam6, encduration6, encproj7, encdomain7, encteam7, encduration7, encproj8, encdomain8, encteam8, encduration8, encproj9, encdomain9, encteam9, encduration9, encproj10, encdomain10, encteam10, encduration10;
    View trash1selectionview, trash2selectionview, trash3selectionview, trash4selectionview, trash5selectionview, trash6selectionview, trash7selectionview, trash8selectionview, trash9selectionview, trash10selectionview;
    int edittedFlag = 0;
    int d = 0;
    StudentData s = new StudentData();
    View rootView;
    byte[] demoKeyBytes;
    byte[] demoIVBytes;
    //    Button save;
//    ProgressBar projectsprogress;
    String sPadding;
    private int projectscount = 0;
    private int projectscount2 = 0;
    private AdView mAdView;
    BannerView bannerView, bannerView2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_edit_profile_projects, container, false);
        Appnext.init(getActivity());

//        MobileAds.initialize(getActivity(), Z.APP_ID);
//        mAdView = rootView.findViewById(R.id.ad_view);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        bannerView = rootView.findViewById(R.id.banner);
        bannerView2 = rootView.findViewById(R.id.banner2);
        bannerView.loadAd(new BannerAdRequest());
        bannerView2.loadAd(new BannerAdRequest());

        username = MySharedPreferencesManager.getUsername(getActivity());
        digest1 = MySharedPreferencesManager.getDigest1(getActivity());
        digest2 = MySharedPreferencesManager.getDigest2(getActivity());
        role = MySharedPreferencesManager.getRole(getActivity());

        TextView projtxt = (TextView) rootView.findViewById(R.id.projtxt);
        projtxt.setTypeface(Z.getBold(getActivity()));
        TextView addmoreprojecttxt=(TextView) rootView.findViewById(R.id.addmoreprojecttxt);
        addmoreprojecttxt.setTypeface(Z.getBold(getActivity()));


//        Toast.makeText(getActivity(), "onCrreate", Toast.LENGTH_SHORT).show();
        trash1selectionview = (View) rootView.findViewById(R.id.trash1selectionview);
        trash2selectionview = (View) rootView.findViewById(R.id.trash2selectionview);
        trash3selectionview = (View) rootView.findViewById(R.id.trash3selectionview);
        trash4selectionview = (View) rootView.findViewById(R.id.trash4selectionview);
        trash5selectionview = (View) rootView.findViewById(R.id.trash5selectionview);
        trash6selectionview = (View) rootView.findViewById(R.id.trash6selectionview);
        trash7selectionview = (View) rootView.findViewById(R.id.trash7selectionview);
        trash8selectionview = (View) rootView.findViewById(R.id.trash8selectionview);
        trash9selectionview = (View) rootView.findViewById(R.id.trash9selectionview);
        trash10selectionview = (View) rootView.findViewById(R.id.trash10selectionview);

        trash1selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 1;
                showDeletDialog();
            }
        });
        trash2selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 2;
                showDeletDialog();

            }
        });
        trash3selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 3;
                showDeletDialog();
            }
        });
        trash4selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 4;
                showDeletDialog();
            }
        });
        trash5selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 5;
                showDeletDialog();
            }
        });
        trash6selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 6;
                showDeletDialog();
            }
        });
        trash7selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 7;
                showDeletDialog();
            }
        });
        trash8selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 8;
                showDeletDialog();
            }
        });
        trash9selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 9;
                showDeletDialog();
            }
        });
        trash10selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 10;
                showDeletDialog();
            }
        });

        proj1 = (EditText) rootView.findViewById(R.id.proj1);
        domain1 = (EditText) rootView.findViewById(R.id.domain1);
        team1 = (EditText) rootView.findViewById(R.id.team1);
        duration1 = (EditText) rootView.findViewById(R.id.duration1);
        proj2 = (EditText) rootView.findViewById(R.id.proj2);
        domain2 = (EditText) rootView.findViewById(R.id.domain2);
        team2 = (EditText) rootView.findViewById(R.id.team2);
        duration2 = (EditText) rootView.findViewById(R.id.duration2);
        proj3 = (EditText) rootView.findViewById(R.id.proj3);
        domain3 = (EditText) rootView.findViewById(R.id.domain3);
        team3 = (EditText) rootView.findViewById(R.id.team3);
        duration3 = (EditText) rootView.findViewById(R.id.duration3);
        proj4 = (EditText) rootView.findViewById(R.id.proj4);
        domain4 = (EditText) rootView.findViewById(R.id.domain4);
        team4 = (EditText) rootView.findViewById(R.id.team4);
        duration4 = (EditText) rootView.findViewById(R.id.duration4);
        proj5 = (EditText) rootView.findViewById(R.id.proj5);
        domain5 = (EditText) rootView.findViewById(R.id.domain5);
        team5 = (EditText) rootView.findViewById(R.id.team5);
        duration5 = (EditText) rootView.findViewById(R.id.duration5);
        proj6 = (EditText) rootView.findViewById(R.id.proj6);
        domain6 = (EditText) rootView.findViewById(R.id.domain6);
        team6 = (EditText) rootView.findViewById(R.id.team6);
        duration6 = (EditText) rootView.findViewById(R.id.duration6);
        proj7 = (EditText) rootView.findViewById(R.id.proj7);
        domain7 = (EditText) rootView.findViewById(R.id.domain7);
        team7 = (EditText) rootView.findViewById(R.id.team7);
        duration7 = (EditText) rootView.findViewById(R.id.duration7);
        proj8 = (EditText) rootView.findViewById(R.id.proj8);
        domain8 = (EditText) rootView.findViewById(R.id.domain8);
        team8 = (EditText) rootView.findViewById(R.id.team8);
        duration8 = (EditText) rootView.findViewById(R.id.duration8);
        proj9 = (EditText) rootView.findViewById(R.id.proj9);
        domain9 = (EditText) rootView.findViewById(R.id.domain9);
        team9 = (EditText) rootView.findViewById(R.id.team9);
        duration9 = (EditText) rootView.findViewById(R.id.duration9);
        proj10 = (EditText) rootView.findViewById(R.id.proj10);
        domain10 = (EditText) rootView.findViewById(R.id.domain10);
        team10 = (EditText) rootView.findViewById(R.id.team10);
        duration10 = (EditText) rootView.findViewById(R.id.duration10);

        proj1 = (EditText) rootView.findViewById(R.id.proj1);
        domain1 = (EditText) rootView.findViewById(R.id.domain1);
        team1 = (EditText) rootView.findViewById(R.id.team1);
        duration1 = (EditText) rootView.findViewById(R.id.duration1);


        projinput1 = (TextInputLayout) rootView.findViewById(R.id.proj1input);
        domaininput1 = (TextInputLayout) rootView.findViewById(R.id.domain1input);
        teaminput1 = (TextInputLayout) rootView.findViewById(R.id.team1input);
        durationinput1 = (TextInputLayout) rootView.findViewById(R.id.duration1input);

        projinput2 = (TextInputLayout) rootView.findViewById(R.id.proj2input);
        domaininput2 = (TextInputLayout) rootView.findViewById(R.id.domain2input);
        teaminput2 = (TextInputLayout) rootView.findViewById(R.id.team2input);
        durationinput2 = (TextInputLayout) rootView.findViewById(R.id.duration2input);

        projinput3 = (TextInputLayout) rootView.findViewById(R.id.proj3input);
        domaininput3 = (TextInputLayout) rootView.findViewById(R.id.domain3input);
        teaminput3 = (TextInputLayout) rootView.findViewById(R.id.team3input);
        durationinput3 = (TextInputLayout) rootView.findViewById(R.id.duration3input);

        projinput4 = (TextInputLayout) rootView.findViewById(R.id.proj4input);
        domaininput4 = (TextInputLayout) rootView.findViewById(R.id.domain4input);
        teaminput4 = (TextInputLayout) rootView.findViewById(R.id.team4input);
        durationinput4 = (TextInputLayout) rootView.findViewById(R.id.duration4input);

        projinput5 = (TextInputLayout) rootView.findViewById(R.id.proj5input);
        domaininput5 = (TextInputLayout) rootView.findViewById(R.id.domain5input);
        teaminput5 = (TextInputLayout) rootView.findViewById(R.id.team5input);
        durationinput5 = (TextInputLayout) rootView.findViewById(R.id.duration5input);

        projinput6 = (TextInputLayout) rootView.findViewById(R.id.proj6input);
        domaininput6 = (TextInputLayout) rootView.findViewById(R.id.domain6input);
        teaminput6 = (TextInputLayout) rootView.findViewById(R.id.team6input);
        durationinput6 = (TextInputLayout) rootView.findViewById(R.id.duration6input);

        projinput7 = (TextInputLayout) rootView.findViewById(R.id.proj7input);
        domaininput7 = (TextInputLayout) rootView.findViewById(R.id.domain7input);
        teaminput7 = (TextInputLayout) rootView.findViewById(R.id.team7input);
        durationinput7 = (TextInputLayout) rootView.findViewById(R.id.duration7input);


        projinput8 = (TextInputLayout) rootView.findViewById(R.id.proj8input);
        domaininput8 = (TextInputLayout) rootView.findViewById(R.id.domain8input);
        teaminput8 = (TextInputLayout) rootView.findViewById(R.id.team8input);
        durationinput8 = (TextInputLayout) rootView.findViewById(R.id.duration8input);

        projinput9 = (TextInputLayout) rootView.findViewById(R.id.proj9input);
        domaininput9 = (TextInputLayout) rootView.findViewById(R.id.domain9input);
        teaminput9 = (TextInputLayout) rootView.findViewById(R.id.team9input);
        durationinput9 = (TextInputLayout) rootView.findViewById(R.id.duration9input);

        projinput10 = (TextInputLayout) rootView.findViewById(R.id.proj10input);
        domaininput10 = (TextInputLayout) rootView.findViewById(R.id.domain10input);
        teaminput10 = (TextInputLayout) rootView.findViewById(R.id.team10input);
        durationinput10 = (TextInputLayout) rootView.findViewById(R.id.duration10input);


        proj1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                projinput1.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        domain1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                domaininput1.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        team1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                teaminput1.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        duration1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                durationinput1.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        proj2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                projinput2.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        domain2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                domaininput2 .setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        team2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                teaminput2.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        duration2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                durationinput2.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
       proj3.addTextChangedListener(new TextWatcher() {
             @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                projinput3.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        domain3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                domaininput3.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        team3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                teaminput3 .setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        duration3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                durationinput3 .setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        proj4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                projinput4 .setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        domain4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                domaininput4 .setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        team4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                teaminput4 .setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        duration4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                durationinput4.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        proj5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                projinput5 .setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        domain5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                domaininput5 .setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        team5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                teaminput5.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        duration5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                durationinput5 .setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        proj6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                projinput6 .setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        domain6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                domaininput6 .setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        team6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                teaminput6 .setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        duration6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                durationinput6 .setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        proj7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                projinput7.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        domain7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                domaininput7 .setError(null);
                edittedFlag = 1;
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        team7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                teaminput7.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        duration7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                durationinput7 .setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        proj8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                projinput8.setError(null);
                edittedFlag = 1;
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        domain8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                domaininput8.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        team8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                teaminput8 .setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        duration8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                durationinput8  .setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        proj9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                projinput9 .setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        domain9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                domaininput9 .setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        team9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                teaminput9 .setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        duration9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                durationinput9  .setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        proj10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                projinput10.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        domain10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                domaininput10 .setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        team10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                teaminput10.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        duration10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                durationinput10.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        proj1.setTypeface(Z.getBold(getActivity()));
        domain1.setTypeface(Z.getBold(getActivity()));
        team1.setTypeface(Z.getBold(getActivity()));
        duration1.setTypeface(Z.getBold(getActivity()));

        proj2.setTypeface(Z.getBold(getActivity()));
        domain2.setTypeface(Z.getBold(getActivity()));
        team2.setTypeface(Z.getBold(getActivity()));
        duration2.setTypeface(Z.getBold(getActivity()));

        proj3.setTypeface(Z.getBold(getActivity()));
        domain3.setTypeface(Z.getBold(getActivity()));
        team3.setTypeface(Z.getBold(getActivity()));
        duration3.setTypeface(Z.getBold(getActivity()));

        proj4.setTypeface(Z.getBold(getActivity()));
        domain4.setTypeface(Z.getBold(getActivity()));
        team4.setTypeface(Z.getBold(getActivity()));
        duration4.setTypeface(Z.getBold(getActivity()));

        proj5.setTypeface(Z.getBold(getActivity()));
        domain5.setTypeface(Z.getBold(getActivity()));
        team5.setTypeface(Z.getBold(getActivity()));
        duration5.setTypeface(Z.getBold(getActivity()));

        proj6.setTypeface(Z.getBold(getActivity()));
        domain6.setTypeface(Z.getBold(getActivity()));
        team6.setTypeface(Z.getBold(getActivity()));
        duration6.setTypeface(Z.getBold(getActivity()));

        proj7.setTypeface(Z.getBold(getActivity()));
        domain7.setTypeface(Z.getBold(getActivity()));
        team7.setTypeface(Z.getBold(getActivity()));
        duration7.setTypeface(Z.getBold(getActivity()));

        proj8.setTypeface(Z.getBold(getActivity()));
        domain8.setTypeface(Z.getBold(getActivity()));
        team8.setTypeface(Z.getBold(getActivity()));
        duration8.setTypeface(Z.getBold(getActivity()));

        proj9.setTypeface(Z.getBold(getActivity()));
        domain9.setTypeface(Z.getBold(getActivity()));
        team9.setTypeface(Z.getBold(getActivity()));
        duration9.setTypeface(Z.getBold(getActivity()));

        proj10.setTypeface(Z.getBold(getActivity()));
        domain10.setTypeface(Z.getBold(getActivity()));
        team10.setTypeface(Z.getBold(getActivity()));
        duration10.setTypeface(Z.getBold(getActivity()));

        projinput1.setTypeface(Z.getLight(getActivity()));
        domaininput1.setTypeface(Z.getLight(getActivity()));
        teaminput1.setTypeface(Z.getLight(getActivity()));
        durationinput1.setTypeface(Z.getLight(getActivity()));
        projinput2.setTypeface(Z.getLight(getActivity()));
        domaininput2.setTypeface(Z.getLight(getActivity()));
        teaminput2.setTypeface(Z.getLight(getActivity()));
        durationinput2.setTypeface(Z.getLight(getActivity()));
        projinput3.setTypeface(Z.getLight(getActivity()));
        domaininput3.setTypeface(Z.getLight(getActivity()));
        teaminput3.setTypeface(Z.getLight(getActivity()));
        durationinput3.setTypeface(Z.getLight(getActivity()));

        projinput4.setTypeface(Z.getLight(getActivity()));
        domaininput4.setTypeface(Z.getLight(getActivity()));
        teaminput4.setTypeface(Z.getLight(getActivity()));
        durationinput4.setTypeface(Z.getLight(getActivity()));
        projinput5.setTypeface(Z.getLight(getActivity()));
        domaininput5.setTypeface(Z.getLight(getActivity()));
        teaminput5.setTypeface(Z.getLight(getActivity()));
        durationinput5.setTypeface(Z.getLight(getActivity()));
        projinput6.setTypeface(Z.getLight(getActivity()));
        domaininput6.setTypeface(Z.getLight(getActivity()));
        teaminput6.setTypeface(Z.getLight(getActivity()));
        durationinput6.setTypeface(Z.getLight(getActivity()));
        projinput7.setTypeface(Z.getLight(getActivity()));
        domaininput7.setTypeface(Z.getLight(getActivity()));
        teaminput7.setTypeface(Z.getLight(getActivity()));
        durationinput7.setTypeface(Z.getLight(getActivity()));
        projinput8.setTypeface(Z.getLight(getActivity()));
        domaininput8.setTypeface(Z.getLight(getActivity()));
        teaminput8.setTypeface(Z.getLight(getActivity()));
        durationinput8.setTypeface(Z.getLight(getActivity()));
        projinput9.setTypeface(Z.getLight(getActivity()));
        domaininput9.setTypeface(Z.getLight(getActivity()));
        teaminput9.setTypeface(Z.getLight(getActivity()));
        durationinput9.setTypeface(Z.getLight(getActivity()));
        projinput10.setTypeface(Z.getLight(getActivity()));
        domaininput10.setTypeface(Z.getLight(getActivity()));

        teaminput10.setTypeface(Z.getLight(getActivity()));
        durationinput10.setTypeface(Z.getLight(getActivity()));


        addmoreproject = (View) rootView.findViewById(R.id.addmoreproject);
        addmoreproject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editproj=0;
                if (projectscount == 0) {
                    if(proj1.getText().toString()!=null && domain1.getText().toString()!=null && team1.getText().toString()!=null && duration1.getText().toString()!=null) {
                    if (!proj1.getText().toString().equals("") && !domain1.getText().toString().equals("") && !team1.getText().toString().equals("") && !duration1.getText().toString().equals("")) {

                        View v = (View) rootView.findViewById(R.id.projectline1);
                        v.setVisibility(View.VISIBLE);

                        RelativeLayout projectrl = (RelativeLayout) rootView.findViewById(R.id.projectrl2);
                        projectrl.setVisibility(View.VISIBLE);

                        projectscount++;
                    } else
                        Toast.makeText(getActivity(), "Please fill the empty project details", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(getActivity(), "Please fill the empty project details", Toast.LENGTH_SHORT).show();

                } else if (projectscount == 1) {
                    if(proj2.getText().toString()!=null && domain2.getText().toString()!=null && team2.getText().toString()!=null && duration2.getText().toString()!=null) {
                    if (!proj2.getText().toString().equals("") && !domain2.getText().toString().equals("") && !team2.getText().toString().equals("") && !duration2.getText().toString().equals("")) {

                        View v = (View) rootView.findViewById(R.id.projectline2);
                        v.setVisibility(View.VISIBLE);

                        RelativeLayout projectrl = (RelativeLayout) rootView.findViewById(R.id.projectrl3);
                        projectrl.setVisibility(View.VISIBLE);
                        projectscount++;

                    } else
                        Toast.makeText(getActivity(), "Please fill the empty project details", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(getActivity(), "Please fill the empty project details", Toast.LENGTH_SHORT).show();

                } else if (projectscount == 2) {

                    if(proj3.getText().toString()!=null && domain3.getText().toString()!=null && team3.getText().toString()!=null && duration3.getText().toString()!=null) {
                    if (!proj3.getText().toString().equals("") && !domain3.getText().toString().equals("") && !team3.getText().toString().equals("") && !duration3.getText().toString().equals("")) {

                        View v = (View) rootView.findViewById(R.id.projectline3);
                        v.setVisibility(View.VISIBLE);

                        RelativeLayout projectrl = (RelativeLayout) rootView.findViewById(R.id.projectrl4);
                        projectrl.setVisibility(View.VISIBLE);
                        projectscount++;

                    } else
                        Toast.makeText(getActivity(), "Please fill the empty project details", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(getActivity(), "Please fill the empty project details", Toast.LENGTH_SHORT).show();


                } else if (projectscount == 3) {

                    if (proj4.getText().toString() != null && domain4.getText().toString() != null && team4.getText().toString() != null && duration4.getText().toString() != null) {
                        if (!proj4.getText().toString().equals("") && !domain4.getText().toString().equals("") && !team4.getText().toString().equals("") && !duration4.getText().toString().equals("")) {

                            View v = (View) rootView.findViewById(R.id.projectline4);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout projectrl = (RelativeLayout) rootView.findViewById(R.id.projectrl5);
                            projectrl.setVisibility(View.VISIBLE);

                            projectscount++;

                        } else
                            Toast.makeText(getActivity(), "Please fill the empty project details", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getActivity(), "Please fill the empty project details", Toast.LENGTH_SHORT).show();


                } else if (projectscount == 4) {

                    if (proj5.getText().toString() != null && domain5.getText().toString() != null && team5.getText().toString() != null && duration5.getText().toString() != null) {
                        if (!proj5.getText().toString().equals("") && !domain5.getText().toString().equals("") && !team5.getText().toString().equals("") && !duration5.getText().toString().equals("")) {

                            View v = (View) rootView.findViewById(R.id.projectline5);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout projectrl = (RelativeLayout) rootView.findViewById(R.id.projectrl6);
                            projectrl.setVisibility(View.VISIBLE);

                            projectscount++;

                        } else
                            Toast.makeText(getActivity(), "Please fill the empty project details", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getActivity(), "Please fill the empty project details", Toast.LENGTH_SHORT).show();


                } else if (projectscount == 5) {
                    if (proj6.getText().toString() != null && domain6.getText().toString() != null && team6.getText().toString() != null && duration6.getText().toString() != null) {
                        if (!proj6.getText().toString().equals("") && !domain6.getText().toString().equals("") && !team6.getText().toString().equals("") && !duration6.getText().toString().equals("")) {

                            View v = (View) rootView.findViewById(R.id.projectline6);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout projectrl = (RelativeLayout) rootView.findViewById(R.id.projectrl7);
                            projectrl.setVisibility(View.VISIBLE);

                            projectscount++;

                        } else
                            Toast.makeText(getActivity(), "Please fill the empty project details", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getActivity(), "Please fill the empty project details", Toast.LENGTH_SHORT).show();

                } else if (projectscount == 6) {
                    if (proj7.getText().toString() != null && domain7.getText().toString() != null && team7.getText().toString() != null && duration7.getText().toString() != null) {
                        if (!proj7.getText().toString().equals("") && !domain7.getText().toString().equals("") && !team7.getText().toString().equals("") && !duration7.getText().toString().equals("")) {

                            View v = (View) rootView.findViewById(R.id.projectline7);
                            v.setVisibility(View.VISIBLE);
                            RelativeLayout projectrl = (RelativeLayout) rootView.findViewById(R.id.projectrl8);
                            projectrl.setVisibility(View.VISIBLE);

                            projectscount++;

                        } else
                            Toast.makeText(getActivity(), "Please fill the empty project details", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getActivity(), "Please fill the empty project details", Toast.LENGTH_SHORT).show();

                } else if (projectscount == 7) {
                    if (proj8.getText().toString() != null && domain8.getText().toString() != null && team8.getText().toString() != null && duration8.getText().toString() != null) {
                        if (!proj8.getText().toString().equals("") && !domain8.getText().toString().equals("") && !team8.getText().toString().equals("") && !duration8.getText().toString().equals("")) {

                            View v = (View) rootView.findViewById(R.id.projectline8);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout projectrl = (RelativeLayout) rootView.findViewById(R.id.projectrl9);
                            projectrl.setVisibility(View.VISIBLE);

                            projectscount++;

                        } else
                            Toast.makeText(getActivity(), "Please fill the empty project details", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getActivity(), "Please fill the empty project details", Toast.LENGTH_SHORT).show();

                } else if (projectscount == 8) {

                    if (proj9.getText().toString() != null && domain9.getText().toString() != null && team9.getText().toString() != null && duration9.getText().toString() != null) {
                        if (!proj9.getText().toString().equals("") && !domain9.getText().toString().equals("") && !team9.getText().toString().equals("") && !duration9.getText().toString().equals("")) {

                            View v = (View) rootView.findViewById(R.id.projectline9);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout projectrl = (RelativeLayout) rootView.findViewById(R.id.projectrl10);
                            projectrl.setVisibility(View.VISIBLE);

                            projectscount++;

                            TextView t = (TextView) rootView.findViewById(R.id.addmoreprojecttxt);
                            ImageView i = (ImageView) rootView.findViewById(R.id.addmoreprojectimg);
                            addmoreproject.setVisibility(View.GONE);
                            t.setVisibility(View.GONE);
                            i.setVisibility(View.GONE);

                        } else
                            Toast.makeText(getActivity(), "Please fill the empty project details", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getActivity(), "Please fill the empty project details", Toast.LENGTH_SHORT).show();

                }

            }
        });



        sproj1 = s.getProj1();
        sdomain1 = s.getDomain1();
        steam1 = s.getTeam1();
        sduration1 = s.getDuration1();
        sproj2 = s.getProj2();
        sdomain2 = s.getDomain2();
        steam2 = s.getTeam2();
        sduration2 = s.getDuration2();
        sproj3 = s.getProj3();
        sdomain3 = s.getDomain3();
        steam3 = s.getTeam3();
        sduration3 = s.getDuration3();
        sproj4 = s.getProj4();
        sdomain4 = s.getDomain4();
        steam4 = s.getTeam4();
        sduration4 = s.getDuration4();
        sproj5 = s.getProj5();
        sdomain5 = s.getDomain5();
        steam5 = s.getTeam5();
        sduration5 = s.getDuration5();
        sproj6 = s.getProj6();
        sdomain6 = s.getDomain6();
        steam6 = s.getTeam6();
        sduration6 = s.getDuration6();
        sproj7 = s.getProj7();
        sdomain7 = s.getDomain7();
        steam7 = s.getTeam7();
        sduration7 = s.getDuration7();
        sproj8 = s.getProj8();
        sdomain8 = s.getDomain8();
        steam8 = s.getTeam8();
        sduration8 = s.getDuration8();
        sproj9 = s.getProj9();
        sdomain9 = s.getDomain9();
        steam9 = s.getTeam9();
        sduration9 = s.getDuration9();
        sproj10 = s.getProj10();
        sdomain10 = s.getDomain10();
        steam10 = s.getTeam10();
        sduration10 = s.getDuration10();

//        projectscount=0;
        if (sproj1 != null) {
            if (sproj1.length() > 2) {
                proj1.setText(sproj1);
                domain1.setText(sdomain1);
                team1.setText(steam1);
                duration1.setText(sduration1);
            }
        }
        if (sproj2 != null) {
            if (sproj2.length() > 2) {
                View v = (View) rootView.findViewById(R.id.projectline1);
                v.setVisibility(View.VISIBLE);
                RelativeLayout projectrl = (RelativeLayout) rootView.findViewById(R.id.projectrl2);
                projectrl.setVisibility(View.VISIBLE);

                proj2.setText(sproj2);
                domain2.setText(sdomain2);
                team2.setText(steam2);
                duration2.setText(sduration2);
                projectscount++;


            }
        }
        if (sproj3 != null) {
            if (sproj3.length() > 2) {
                View v = (View) rootView.findViewById(R.id.projectline2);
                v.setVisibility(View.VISIBLE);
                RelativeLayout projectrl = (RelativeLayout) rootView.findViewById(R.id.projectrl3);
                projectrl.setVisibility(View.VISIBLE);

                proj3.setText(sproj3);
                domain3.setText(sdomain3);
                team3.setText(steam3);
                duration3.setText(sduration3);
                projectscount++;

            }
        }
        if (sproj4 != null) {
            if (sproj4.length() > 2) {
                View v = (View) rootView.findViewById(R.id.projectline3);
                v.setVisibility(View.VISIBLE);
                RelativeLayout projectrl = (RelativeLayout) rootView.findViewById(R.id.projectrl4);
                projectrl.setVisibility(View.VISIBLE);

                proj4.setText(sproj4);
                domain4.setText(sdomain4);
                team4.setText(steam4);
                duration4.setText(sduration4);
                projectscount++;
            }
        }
        if (sproj5 != null) {
            if (sproj5.length() > 2) {
                View v = (View) rootView.findViewById(R.id.projectline4);
                v.setVisibility(View.VISIBLE);
                RelativeLayout projectrl = (RelativeLayout) rootView.findViewById(R.id.projectrl5);
                projectrl.setVisibility(View.VISIBLE);
                proj5.setText(sproj5);
                domain5.setText(sdomain5);
                team5.setText(steam5);
                duration5.setText(sduration5);
                projectscount++;
            }
        }
        if (sproj6 != null) {
            if (sproj6.length() > 2) {
                View v = (View) rootView.findViewById(R.id.projectline5);
                v.setVisibility(View.VISIBLE);
                RelativeLayout projectrl = (RelativeLayout) rootView.findViewById(R.id.projectrl6);
                projectrl.setVisibility(View.VISIBLE);

                proj6.setText(sproj6);
                domain6.setText(sdomain6);
                team6.setText(steam6);
                duration6.setText(sduration6);
                projectscount++;
            }
        }
        if (sproj7 != null) {
            if (sproj7.length() > 2) {
                View v = (View) rootView.findViewById(R.id.projectline6);
                v.setVisibility(View.VISIBLE);
                RelativeLayout projectrl = (RelativeLayout) rootView.findViewById(R.id.projectrl7);
                projectrl.setVisibility(View.VISIBLE);

                proj7.setText(sproj7);
                domain7.setText(sdomain7);
                team7.setText(steam7);
                duration7.setText(sduration7);
                projectscount++;
            }
        }
        if (sproj8 != null) {
            if (sproj8.length() > 2) {
                View v = (View) rootView.findViewById(R.id.projectline7);
                v.setVisibility(View.VISIBLE);
                RelativeLayout projectrl = (RelativeLayout) rootView.findViewById(R.id.projectrl8);
                projectrl.setVisibility(View.VISIBLE);

                proj8.setText(sproj8);
                domain8.setText(sdomain8);
                team8.setText(steam8);
                duration8.setText(sduration8);
                projectscount++;
            }
        }
        if (sproj9 != null) {
            if (sproj9.length() > 2) {
                View v = (View) rootView.findViewById(R.id.projectline8);
                v.setVisibility(View.VISIBLE);
                RelativeLayout projectrl = (RelativeLayout) rootView.findViewById(R.id.projectrl9);
                projectrl.setVisibility(View.VISIBLE);

                proj9.setText(sproj9);
                domain9.setText(sdomain9);
                team9.setText(steam9);
                duration9.setText(sduration9);
                projectscount++;
            }
        }
        if (sproj10 != null) {
            if (sproj10.length() > 2) {
                View v = (View) rootView.findViewById(R.id.projectline9);
                v.setVisibility(View.VISIBLE);
                RelativeLayout projectrl = (RelativeLayout) rootView.findViewById(R.id.projectrl10);
                projectrl.setVisibility(View.VISIBLE);

                proj10.setText(sproj10);
                domain10.setText(sdomain10);
                team10.setText(steam10);
                duration10.setText(sduration10);
                projectscount++;
                TextView t = (TextView) rootView.findViewById(R.id.addmoreprojecttxt);
                ImageView i = (ImageView) rootView.findViewById(R.id.addmoreprojectimg);
                addmoreproject.setVisibility(View.GONE);
                t.setVisibility(View.GONE);
                i.setVisibility(View.GONE);
            }
        }

        edittedFlag = 0;

        return rootView;
    }


    public boolean myvalidate() {
        errorflag = 0;

        projinput1.setError(null);
        domaininput1.setError(null);
        teaminput1.setError(null);
        durationinput1.setError(null);

        projinput2.setError(null);
        domaininput2.setError(null);
        teaminput2.setError(null);
        durationinput2.setError(null);

        projinput3.setError(null);
        domaininput3.setError(null);
        teaminput3.setError(null);
        durationinput3.setError(null);

        projinput4.setError(null);
        domaininput4.setError(null);
        teaminput4.setError(null);
        durationinput4.setError(null);

        projinput5.setError(null);
        domaininput5.setError(null);
        teaminput5.setError(null);
        durationinput5.setError(null);

        projinput6.setError(null);
        domaininput6.setError(null);
        teaminput6.setError(null);
        durationinput6.setError(null);

        projinput7.setError(null);
        domaininput7.setError(null);
        teaminput7.setError(null);
        durationinput7.setError(null);

        projinput8.setError(null);
        domaininput8.setError(null);
        teaminput8.setError(null);
        durationinput8.setError(null);

        projinput9.setError(null);
        domaininput9.setError(null);
        teaminput9.setError(null);
        durationinput9.setError(null);

        projinput10.setError(null);
        domaininput10.setError(null);
        teaminput10.setError(null);
        durationinput10.setError(null);

        sproj1 = proj1.getText().toString();
        sdomain1 = domain1.getText().toString();
        steam1 = team1.getText().toString();
        sduration1 = duration1.getText().toString();
        sproj2 = proj2.getText().toString();
        sdomain2 = domain2.getText().toString();
        steam2 = team2.getText().toString();
        sduration2 = duration2.getText().toString();
        sproj3 = proj3.getText().toString();
        sdomain3 = domain3.getText().toString();
        steam3 = team3.getText().toString();
        sduration3 = duration3.getText().toString();
        sproj4 = proj4.getText().toString();
        sdomain4 = domain4.getText().toString();
        steam4 = team4.getText().toString();
        sduration4 = duration4.getText().toString();
        sproj5 = proj5.getText().toString();
        sdomain5 = domain5.getText().toString();
        steam5 = team5.getText().toString();
        sduration5 = duration5.getText().toString();
        sproj6 = proj6.getText().toString();
        sdomain6 = domain6.getText().toString();
        steam6 = team6.getText().toString();
        sduration6 = duration6.getText().toString();
        sproj7 = proj7.getText().toString();
        sdomain7 = domain7.getText().toString();
        steam7 = team7.getText().toString();
        sduration7 = duration7.getText().toString();
        sproj8 = proj8.getText().toString();
        sdomain8 = domain8.getText().toString();
        steam8 = team8.getText().toString();
        sduration8 = duration8.getText().toString();
        sproj9 = proj9.getText().toString();
        sdomain9 = domain9.getText().toString();
        steam9 = team9.getText().toString();
        sduration9 = duration9.getText().toString();
        sproj10 = proj10.getText().toString();
        sdomain10 = domain10.getText().toString();
        steam10 = team10.getText().toString();
        sduration10 = duration10.getText().toString();


        if (projectscount == 0) {
            if (sproj1.length() < 3) {
                errorflag = 1;
                projinput1.setError("Kindly enter valid project name");
            } else if (sdomain1.length() < 3) {
                errorflag = 1;
                domaininput1.setError("Kindly enter valid domain");
            } else if (steam1.length() < 1) {
                errorflag = 1;
                teaminput1.setError("Kindly enter valid team size");
            } else if (sduration1.length() < 1) {
                errorflag = 1;
                durationinput1.setError("Kindly enter valid duration in months");
            }

        } else if (projectscount == 1) {


            if (sproj1.length() < 3) {
                errorflag = 1;
                projinput1.setError("Kindly enter valid project name");
            } else if (sdomain1.length() < 3) {
                errorflag = 1;
                domaininput1.setError("Kindly enter valid domain");
            } else if (steam1.length() < 1) {
                errorflag = 1;
                teaminput1.setError("Kindly enter valid team size");
            } else if (sduration1.length() < 1) {
                errorflag = 1;
                durationinput1.setError("Kindly enter valid duration in months");

            } else if (sproj2.length() < 3) {
                errorflag = 1;
                projinput2.setError("Kindly enter valid project name");
            } else if (sdomain2.length() < 3) {
                errorflag = 1;
                domaininput2.setError("Kindly enter valid domain");
            } else if (steam2.length() < 1) {
                errorflag = 1;
                teaminput2.setError("Kindly enter valid team size");
            } else if (sduration2.length() < 1) {
                errorflag = 1;
                durationinput2.setError("Kindly enter valid duration in months");
            }
        } else if (projectscount == 2) {

            if (sproj1.length() < 3) {
                errorflag = 1;
                projinput1.setError("Kindly enter valid project name");
            } else if (sdomain1.length() < 3) {
                errorflag = 1;
                domaininput1.setError("Kindly enter valid domain");
            } else if (steam1.length() < 1) {
                errorflag = 1;
                teaminput1.setError("Kindly enter valid team size");
            } else if (sduration1.length() < 1) {
                errorflag = 1;
                durationinput1.setError("Kindly enter valid duration in months");

            } else if (sproj2.length() < 3) {
                errorflag = 1;
                projinput2.setError("Kindly enter valid project name");
            } else if (sdomain2.length() < 3) {
                errorflag = 1;
                domaininput2.setError("Kindly enter valid domain");
            } else if (steam2.length() < 1) {
                errorflag = 1;
                teaminput2.setError("Kindly enter valid team size");
            } else if (sduration2.length() < 1) {
                errorflag = 1;
                durationinput2.setError("Kindly enter valid duration in months");
            } else if (sproj3.length() < 3) {
                errorflag = 1;
                projinput3.setError("Kindly enter valid project name");
            } else if (sdomain3.length() < 3) {
                errorflag = 1;
                domaininput3.setError("Kindly enter valid domain");
            } else if (steam3.length() < 1) {
                errorflag = 1;
                teaminput3.setError("Kindly enter valid team size");
            } else if (sduration3.length() < 1) {
                errorflag = 1;
                durationinput3.setError("Kindly enter valid duration in months");
            }
        } else if (projectscount == 3) {


            if (sproj1.length() < 3) {
                errorflag = 1;
                projinput1.setError("Kindly enter valid project name");
            } else if (sdomain1.length() < 3) {
                errorflag = 1;
                domaininput1.setError("Kindly enter valid domain");
            } else if (steam1.length() < 1) {
                errorflag = 1;
                teaminput1.setError("Kindly enter valid team size");
            } else if (sduration1.length() < 1) {
                errorflag = 1;
                durationinput1.setError("Kindly enter valid duration in months");

            } else if (sproj2.length() < 3) {
                errorflag = 1;
                projinput2.setError("Kindly enter valid project name");
            } else if (sdomain2.length() < 3) {
                errorflag = 1;
                domaininput2.setError("Kindly enter valid domain");
            } else if (steam2.length() < 1) {
                errorflag = 1;
                teaminput2.setError("Kindly enter valid team size");
            } else if (sduration2.length() < 1) {
                errorflag = 1;
                durationinput2.setError("Kindly enter valid duration in months");
            } else if (sproj3.length() < 3) {
                errorflag = 1;
                projinput3.setError("Kindly enter valid project name");
            } else if (sdomain3.length() < 3) {
                errorflag = 1;
                domaininput3.setError("Kindly enter valid domain");
            } else if (steam3.length() < 1) {
                errorflag = 1;
                teaminput3.setError("Kindly enter valid team size");
            } else if (sduration3.length() < 1) {
                errorflag = 1;
                durationinput3.setError("Kindly enter valid duration in months");
            } else if (sproj4.length() < 3) {
                errorflag = 1;
                projinput4.setError("Kindly enter valid project name");
            } else if (sdomain4.length() < 3) {
                errorflag = 1;
                domaininput4.setError("Kindly enter valid domain");
            } else if (steam4.length() < 1) {
                errorflag = 1;
                teaminput4.setError("Kindly enter valid team size");
            } else if (sduration4.length() < 1) {
                errorflag = 1;
                durationinput4.setError("Kindly enter valid duration in months");
            }
        } else if (projectscount == 4) {

            if (sproj1.length() < 3) {
                errorflag = 1;
                projinput1.setError("Kindly enter valid project name");
            } else if (sdomain1.length() < 3) {
                errorflag = 1;
                domaininput1.setError("Kindly enter valid domain");
            } else if (steam1.length() < 1) {
                errorflag = 1;
                teaminput1.setError("Kindly enter valid team size");
            } else if (sduration1.length() < 1) {
                errorflag = 1;
                durationinput1.setError("Kindly enter valid duration in months");

            } else if (sproj2.length() < 3) {
                errorflag = 1;
                projinput2.setError("Kindly enter valid project name");
            } else if (sdomain2.length() < 3) {
                errorflag = 1;
                domaininput2.setError("Kindly enter valid domain");
            } else if (steam2.length() < 1) {
                errorflag = 1;
                teaminput2.setError("Kindly enter valid team size");
            } else if (sduration2.length() < 1) {
                errorflag = 1;
                durationinput2.setError("Kindly enter valid duration in months");
            } else if (sproj3.length() < 3) {
                errorflag = 1;
                projinput3.setError("Kindly enter valid project name");
            } else if (sdomain3.length() < 3) {
                errorflag = 1;
                domaininput3.setError("Kindly enter valid domain");
            } else if (steam3.length() < 1) {
                errorflag = 1;
                teaminput3.setError("Kindly enter valid team size");
            } else if (sduration3.length() < 1) {
                errorflag = 1;
                durationinput3.setError("Kindly enter valid duration in months");
            } else if (sproj4.length() < 3) {
                errorflag = 1;
                projinput4.setError("Kindly enter valid project name");
            } else if (sdomain4.length() < 3) {
                errorflag = 1;
                domaininput4.setError("Kindly enter valid domain");
            } else if (steam4.length() < 1) {
                errorflag = 1;
                teaminput4.setError("Kindly enter valid team size");
            } else if (sduration4.length() < 1) {
                errorflag = 1;
                durationinput4.setError("Kindly enter valid duration in months");
            } else if (sproj5.length() < 3) {
                errorflag = 1;
                projinput5.setError("Kindly enter valid project name");
            } else if (sdomain5.length() < 3) {
                errorflag = 1;
                domaininput5.setError("Kindly enter valid domain");
            } else if (steam5.length() < 1) {
                errorflag = 1;
                teaminput5.setError("Kindly enter valid team size");
            } else if (sduration5.length() < 1) {
                errorflag = 1;
                durationinput5.setError("Kindly enter valid duration in months");             //
            }
        } else if (projectscount == 5) {

            if (sproj1.length() < 3) {
                errorflag = 1;
                projinput1.setError("Kindly enter valid project name");
            } else if (sdomain1.length() < 3) {
                errorflag = 1;
                domaininput1.setError("Kindly enter valid domain");
            } else if (steam1.length() < 1) {
                errorflag = 1;
                teaminput1.setError("Kindly enter valid team size");
            } else if (sduration1.length() < 1) {
                errorflag = 1;
                durationinput1.setError("Kindly enter valid duration in months");

            } else if (sproj2.length() < 3) {
                errorflag = 1;
                projinput2.setError("Kindly enter valid project name");
            } else if (sdomain2.length() < 3) {
                errorflag = 1;
                domaininput2.setError("Kindly enter valid domain");
            } else if (steam2.length() < 1) {
                errorflag = 1;
                teaminput2.setError("Kindly enter valid team size");
            } else if (sduration2.length() < 1) {
                errorflag = 1;
                durationinput2.setError("Kindly enter valid duration in months");
            } else if (sproj3.length() < 3) {
                errorflag = 1;
                projinput3.setError("Kindly enter valid project name");
            } else if (sdomain3.length() < 3) {
                errorflag = 1;
                domaininput3.setError("Kindly enter valid domain");
            } else if (steam3.length() < 1) {
                errorflag = 1;
                teaminput3.setError("Kindly enter valid team size");
            } else if (sduration3.length() < 1) {
                errorflag = 1;
                durationinput3.setError("Kindly enter valid duration in months");
            } else if (sproj4.length() < 3) {
                errorflag = 1;
                projinput4.setError("Kindly enter valid project name");
            } else if (sdomain4.length() < 3) {
                errorflag = 1;
                domaininput4.setError("Kindly enter valid domain");
            } else if (steam4.length() < 1) {
                errorflag = 1;
                teaminput4.setError("Kindly enter valid team size");
            } else if (sduration4.length() < 1) {
                errorflag = 1;
                durationinput4.setError("Kindly enter valid duration in months");
            } else if (sproj5.length() < 3) {
                errorflag = 1;
                projinput5.setError("Kindly enter valid project name");
            } else if (sdomain5.length() < 3) {
                errorflag = 1;
                domaininput5.setError("Kindly enter valid domain");
            } else if (steam5.length() < 1) {
                errorflag = 1;
                teaminput5.setError("Kindly enter valid team size");
            } else if (sduration5.length() < 1) {
                errorflag = 1;
                durationinput5.setError("Kindly enter valid duration in months");             //
            } else if (sproj6.length() < 3) {
                errorflag = 1;
                projinput6.setError("Kindly enter valid project name");
            } else if (sdomain6.length() < 3) {
                errorflag = 1;
                domaininput6.setError("Kindly enter valid domain");
            } else if (steam6.length() < 1) {
                errorflag = 1;
                teaminput6.setError("Kindly enter valid team size");
            } else if (sduration6.length() < 1) {
                errorflag = 1;
                durationinput6.setError("Kindly enter valid duration in months");
            }

        } else if (projectscount == 6) {

            if (sproj1.length() < 3) {
                errorflag = 1;
                projinput1.setError("Kindly enter valid project name");
            } else if (sdomain1.length() < 3) {
                errorflag = 1;
                domaininput1.setError("Kindly enter valid domain");
            } else if (steam1.length() < 1) {
                errorflag = 1;
                teaminput1.setError("Kindly enter valid team size");
            } else if (sduration1.length() < 1) {
                errorflag = 1;
                durationinput1.setError("Kindly enter valid duration in months");
            } else if (sproj2.length() < 3) {
                errorflag = 1;
                projinput2.setError("Kindly enter valid project name");
            } else if (sdomain2.length() < 3) {
                errorflag = 1;
                domaininput2.setError("Kindly enter valid domain");
            } else if (steam2.length() < 1) {
                errorflag = 1;
                teaminput2.setError("Kindly enter valid team size");
            } else if (sduration2.length() < 1) {
                errorflag = 1;
                durationinput2.setError("Kindly enter valid duration in months");
            } else if (sproj3.length() < 3) {
                errorflag = 1;
                projinput3.setError("Kindly enter valid project name");
            } else if (sdomain3.length() < 3) {
                errorflag = 1;
                domaininput3.setError("Kindly enter valid domain");
            } else if (steam3.length() < 1) {
                errorflag = 1;
                teaminput3.setError("Kindly enter valid team size");
            } else if (sduration3.length() < 1) {
                errorflag = 1;
                durationinput3.setError("Kindly enter valid duration in months");
            } else if (sproj4.length() < 3) {
                errorflag = 1;
                projinput4.setError("Kindly enter valid project name");
            } else if (sdomain4.length() < 3) {
                errorflag = 1;
                domaininput4.setError("Kindly enter valid domain");
            } else if (steam4.length() < 1) {
                errorflag = 1;
                teaminput4.setError("Kindly enter valid team size");
            } else if (sduration4.length() < 1) {
                errorflag = 1;
                durationinput4.setError("Kindly enter valid duration in months");
            } else if (sproj5.length() < 3) {
                errorflag = 1;
                projinput5.setError("Kindly enter valid project name");
            } else if (sdomain5.length() < 3) {
                errorflag = 1;
                domaininput5.setError("Kindly enter valid domain");
            } else if (steam5.length() < 1) {
                errorflag = 1;
                teaminput5.setError("Kindly enter valid team size");
            } else if (sduration5.length() < 1) {
                errorflag = 1;
                durationinput5.setError("Kindly enter valid duration in months");             //
            } else if (sproj6.length() < 3) {
                errorflag = 1;
                projinput6.setError("Kindly enter valid project name");
            } else if (sdomain6.length() < 3) {
                errorflag = 1;
                domaininput6.setError("Kindly enter valid domain");
            } else if (steam6.length() < 1) {
                errorflag = 1;
                teaminput6.setError("Kindly enter valid team size");
            } else if (sduration6.length() < 1) {
                errorflag = 1;
                durationinput6.setError("Kindly enter valid duration in months");
            } else if (sproj7.length() < 3) {
                errorflag = 1;
                projinput7.setError("Kindly enter valid project name");
            } else if (sdomain7.length() < 3) {
                errorflag = 1;
                domaininput7.setError("Kindly enter valid domain");
            } else if (steam7.length() < 1) {
                errorflag = 1;
                teaminput7.setError("Kindly enter valid team size");
            } else if (sduration7.length() < 1) {
                errorflag = 1;
                durationinput7.setError("Kindly enter valid duration in months");
            }

        } else if (projectscount == 7) {

            if (sproj1.length() < 3) {
                errorflag = 1;
                projinput1.setError("Kindly enter valid project name");
            } else if (sdomain1.length() < 3) {
                errorflag = 1;
                domaininput1.setError("Kindly enter valid domain");
            } else if (steam1.length() < 1) {
                errorflag = 1;
                teaminput1.setError("Kindly enter valid team size");
            } else if (sduration1.length() < 1) {
                errorflag = 1;
                durationinput1.setError("Kindly enter valid duration in months");
            } else if (sproj2.length() < 3) {
                errorflag = 1;
                projinput2.setError("Kindly enter valid project name");
            } else if (sdomain2.length() < 3) {
                errorflag = 1;
                domaininput2.setError("Kindly enter valid domain");
            } else if (steam2.length() < 1) {
                errorflag = 1;
                teaminput2.setError("Kindly enter valid team size");
            } else if (sduration2.length() < 1) {
                errorflag = 1;
                durationinput2.setError("Kindly enter valid duration in months");
            } else if (sproj3.length() < 3) {
                errorflag = 1;
                projinput3.setError("Kindly enter valid project name");
            } else if (sdomain3.length() < 3) {
                errorflag = 1;
                domaininput3.setError("Kindly enter valid domain");
            } else if (steam3.length() < 1) {
                errorflag = 1;
                teaminput3.setError("Kindly enter valid team size");
            } else if (sduration3.length() < 1) {
                errorflag = 1;
                durationinput3.setError("Kindly enter valid duration in months");
            } else if (sproj4.length() < 3) {
                errorflag = 1;
                projinput4.setError("Kindly enter valid project name");
            } else if (sdomain4.length() < 3) {
                errorflag = 1;
                domaininput4.setError("Kindly enter valid domain");
            } else if (steam4.length() < 1) {
                errorflag = 1;
                teaminput4.setError("Kindly enter valid team size");
            } else if (sduration4.length() < 1) {
                errorflag = 1;
                durationinput4.setError("Kindly enter valid duration in months");
            } else if (sproj5.length() < 3) {
                errorflag = 1;
                projinput5.setError("Kindly enter valid project name");
            } else if (sdomain5.length() < 3) {
                errorflag = 1;
                domaininput5.setError("Kindly enter valid domain");
            } else if (steam5.length() < 1) {
                errorflag = 1;
                teaminput5.setError("Kindly enter valid team size");
            } else if (sduration5.length() < 1) {
                errorflag = 1;
                durationinput5.setError("Kindly enter valid duration in months");             //
            } else if (sproj6.length() < 3) {
                errorflag = 1;
                projinput6.setError("Kindly enter valid project name");
            } else if (sdomain6.length() < 3) {
                errorflag = 1;
                domaininput6.setError("Kindly enter valid domain");
            } else if (steam6.length() < 1) {
                errorflag = 1;
                teaminput6.setError("Kindly enter valid team size");
            } else if (sduration6.length() < 1) {
                errorflag = 1;
                durationinput6.setError("Kindly enter valid duration in months");
            } else if (sproj7.length() < 3) {
                errorflag = 1;
                projinput7.setError("Kindly enter valid project name");
            } else if (sdomain7.length() < 3) {
                errorflag = 1;
                domaininput7.setError("Kindly enter valid domain");
            } else if (steam7.length() < 1) {
                errorflag = 1;
                teaminput7.setError("Kindly enter valid team size");
            } else if (sduration7.length() < 1) {
                errorflag = 1;
                durationinput7.setError("Kindly enter valid duration in months");
            } else if (sproj8.length() < 3) {
                errorflag = 1;
                projinput8.setError("Kindly enter valid project name");
            } else if (sdomain8.length() < 3) {
                errorflag = 1;
                domaininput8.setError("Kindly enter valid domain");
            } else if (steam8.length() < 1) {
                errorflag = 1;
                teaminput8.setError("Kindly enter valid team size");
            } else if (sduration8.length() < 1) {
                errorflag = 1;
                durationinput8.setError("Kindly enter valid duration in months");
            }
//
        } else if (projectscount == 8) {

            if (sproj1.length() < 3) {
                errorflag = 1;
                projinput1.setError("Kindly enter valid project name");
            } else if (sdomain1.length() < 3) {
                errorflag = 1;
                domaininput1.setError("Kindly enter valid domain");
            } else if (steam1.length() < 1) {
                errorflag = 1;
                teaminput1.setError("Kindly enter valid team size");
            } else if (sduration1.length() < 1) {
                errorflag = 1;
                durationinput1.setError("Kindly enter valid duration in months");
            } else if (sproj2.length() < 3) {
                errorflag = 1;
                projinput2.setError("Kindly enter valid project name");
            } else if (sdomain2.length() < 3) {
                errorflag = 1;
                domaininput2.setError("Kindly enter valid domain");
            } else if (steam2.length() < 1) {
                errorflag = 1;
                teaminput2.setError("Kindly enter valid team size");
            } else if (sduration2.length() < 1) {
                errorflag = 1;
                durationinput2.setError("Kindly enter valid duration in months");
            } else if (sproj3.length() < 3) {
                errorflag = 1;
                projinput3.setError("Kindly enter valid project name");
            } else if (sdomain3.length() < 3) {
                errorflag = 1;
                domaininput3.setError("Kindly enter valid domain");
            } else if (steam3.length() < 1) {
                errorflag = 1;
                teaminput3.setError("Kindly enter valid team size");
            } else if (sduration3.length() < 1) {
                errorflag = 1;
                durationinput3.setError("Kindly enter valid duration in months");
            } else if (sproj4.length() < 3) {
                errorflag = 1;
                projinput4.setError("Kindly enter valid project name");
            } else if (sdomain4.length() < 3) {
                errorflag = 1;
                domaininput4.setError("Kindly enter valid domain");
            } else if (steam4.length() < 1) {
                errorflag = 1;
                teaminput4.setError("Kindly enter valid team size");
            } else if (sduration4.length() < 1) {
                errorflag = 1;
                durationinput4.setError("Kindly enter valid duration in months");
            } else if (sproj5.length() < 3) {
                errorflag = 1;
                projinput5.setError("Kindly enter valid project name");
            } else if (sdomain5.length() < 3) {
                errorflag = 1;
                domaininput5.setError("Kindly enter valid domain");
            } else if (steam5.length() < 1) {
                errorflag = 1;
                teaminput5.setError("Kindly enter valid team size");
            } else if (sduration5.length() < 1) {
                errorflag = 1;
                durationinput5.setError("Kindly enter valid duration in months");             //
            } else if (sproj6.length() < 3) {
                errorflag = 1;
                projinput6.setError("Kindly enter valid project name");
            } else if (sdomain6.length() < 3) {
                errorflag = 1;
                domaininput6.setError("Kindly enter valid domain");
            } else if (steam6.length() < 1) {
                errorflag = 1;
                teaminput6.setError("Kindly enter valid team size");
            } else if (sduration6.length() < 1) {
                errorflag = 1;
                durationinput6.setError("Kindly enter valid duration in months");
            } else if (sproj7.length() < 3) {
                errorflag = 1;
                projinput7.setError("Kindly enter valid project name");
            } else if (sdomain7.length() < 3) {
                errorflag = 1;
                domaininput7.setError("Kindly enter valid domain");
            } else if (steam7.length() < 1) {
                errorflag = 1;
                teaminput7.setError("Kindly enter valid team size");
            } else if (sduration7.length() < 1) {
                errorflag = 1;
                durationinput7.setError("Kindly enter valid duration in months");
            } else if (sproj8.length() < 3) {
                errorflag = 1;
                projinput8.setError("Kindly enter valid project name");
            } else if (sdomain8.length() < 3) {
                errorflag = 1;
                domaininput8.setError("Kindly enter valid domain");
            } else if (steam8.length() < 1) {
                errorflag = 1;
                teaminput8.setError("Kindly enter valid team size");
            } else if (sduration8.length() < 1) {
                errorflag = 1;
                durationinput8.setError("Kindly enter valid duration in months");
            } else if (sproj9.length() < 3) {
                errorflag = 1;
                projinput9.setError("Kindly enter valid project name");
            } else if (sdomain9.length() < 3) {
                errorflag = 1;
                domaininput9.setError("Kindly enter valid domain");
            } else if (steam9.length() < 1) {
                errorflag = 1;
                teaminput9.setError("Kindly enter valid team size");
            } else if (sduration9.length() < 1) {
                errorflag = 1;
                durationinput9.setError("Kindly enter valid duration in months");
            }

        } else if (projectscount == 9) {
            if (sproj1.length() < 3) {
                errorflag = 1;
                projinput1.setError("Kindly enter valid project name");
            } else if (sdomain1.length() < 3) {
                errorflag = 1;
                domaininput1.setError("Kindly enter valid domain");
            } else if (steam1.length() < 1) {
                errorflag = 1;
                teaminput1.setError("Kindly enter valid team size");
            } else if (sduration1.length() < 1) {
                errorflag = 1;
                durationinput1.setError("Kindly enter valid duration in months");
            } else if (sproj2.length() < 3) {
                errorflag = 1;
                projinput2.setError("Kindly enter valid project name");
            } else if (sdomain2.length() < 3) {
                errorflag = 1;
                domaininput2.setError("Kindly enter valid domain");
            } else if (steam2.length() < 1) {
                errorflag = 1;
                teaminput2.setError("Kindly enter valid team size");
            } else if (sduration2.length() < 1) {
                errorflag = 1;
                durationinput2.setError("Kindly enter valid duration in months");
            } else if (sproj3.length() < 3) {
                errorflag = 1;
                projinput3.setError("Kindly enter valid project name");
            } else if (sdomain3.length() < 3) {
                errorflag = 1;
                domaininput3.setError("Kindly enter valid domain");
            } else if (steam3.length() < 1) {
                errorflag = 1;
                teaminput3.setError("Kindly enter valid team size");
            } else if (sduration3.length() < 1) {
                errorflag = 1;
                durationinput3.setError("Kindly enter valid duration in months");
            } else if (sproj4.length() < 3) {
                errorflag = 1;
                projinput4.setError("Kindly enter valid project name");
            } else if (sdomain4.length() < 3) {
                errorflag = 1;
                domaininput4.setError("Kindly enter valid domain");
            } else if (steam4.length() < 1) {
                errorflag = 1;
                teaminput4.setError("Kindly enter valid team size");
            } else if (sduration4.length() < 1) {
                errorflag = 1;
                durationinput4.setError("Kindly enter valid duration in months");
            } else if (sproj5.length() < 3) {
                errorflag = 1;
                projinput5.setError("Kindly enter valid project name");
            } else if (sdomain5.length() < 3) {
                errorflag = 1;
                domaininput5.setError("Kindly enter valid domain");
            } else if (steam5.length() < 1) {
                errorflag = 1;
                teaminput5.setError("Kindly enter valid team size");
            } else if (sduration5.length() < 1) {
                errorflag = 1;
                durationinput5.setError("Kindly enter valid duration in months");             //
            } else if (sproj6.length() < 3) {
                errorflag = 1;
                projinput6.setError("Kindly enter valid project name");
            } else if (sdomain6.length() < 3) {
                errorflag = 1;
                domaininput6.setError("Kindly enter valid domain");
            } else if (steam6.length() < 1) {
                errorflag = 1;
                teaminput6.setError("Kindly enter valid team size");
            } else if (sduration6.length() < 1) {
                errorflag = 1;
                durationinput6.setError("Kindly enter valid duration in months");
            } else if (sproj7.length() < 3) {
                errorflag = 1;
                projinput7.setError("Kindly enter valid project name");
            } else if (sdomain7.length() < 3) {
                errorflag = 1;
                domaininput7.setError("Kindly enter valid domain");
            } else if (steam7.length() < 1) {
                errorflag = 1;
                teaminput7.setError("Kindly enter valid team size");
            } else if (sduration7.length() < 1) {
                errorflag = 1;
                durationinput7.setError("Kindly enter valid duration in months");
            } else if (sproj8.length() < 3) {
                errorflag = 1;
                projinput8.setError("Kindly enter valid project name");
            } else if (sdomain8.length() < 3) {
                errorflag = 1;
                domaininput8.setError("Kindly enter valid domain");
            } else if (steam8.length() < 1) {
                errorflag = 1;
                teaminput8.setError("Kindly enter valid team size");
            } else if (sduration8.length() < 1) {
                errorflag = 1;
                durationinput8.setError("Kindly enter valid duration in months");
            } else if (sproj9.length() < 3) {
                errorflag = 1;
                projinput9.setError("Kindly enter valid project name");
            } else if (sdomain9.length() < 3) {
                errorflag = 1;
                domaininput9.setError("Kindly enter valid domain");
            } else if (steam9.length() < 1) {
                errorflag = 1;
                teaminput9.setError("Kindly enter valid team size");
            } else if (sduration9.length() < 1) {
                errorflag = 1;
                durationinput9.setError("Kindly enter valid duration in months");
            } else if (sproj10.length() < 3) {
                errorflag = 1;
                projinput10.setError("Kindly enter valid project name");
            } else if (sdomain10.length() < 3) {
                errorflag = 1;
                domaininput10.setError("Kindly enter valid domain");
            } else if (steam10.length() < 1) {
                errorflag = 1;
                teaminput10.setError("Kindly enter valid team size");
            } else if (sduration10.length() < 1) {
                errorflag = 1;
                durationinput10.setError("Kindly enter valid duration in months");
            }
        }


        if (errorflag == 0)
            return true;
        else
            return false;

    }

    public void save() {

        try {
            Projects obj1 = new Projects(sproj1, sdomain1, steam1, sduration1);
            Projects obj2 = new Projects(sproj2, sdomain2, steam2, sduration2);
            Projects obj3 = new Projects(sproj3, sdomain3, steam3, sduration3);
            Projects obj4 = new Projects(sproj4, sdomain4, steam4, sduration4);
            Projects obj5 = new Projects(sproj5, sdomain5, steam5, sduration5);
            Projects obj6 = new Projects(sproj6, sdomain6, steam6, sduration6);
            Projects obj7 = new Projects(sproj7, sdomain7, steam7, sduration7);
            Projects obj8 = new Projects(sproj8, sdomain8, steam8, sduration8);
            Projects obj9 = new Projects(sproj9, sdomain9, steam9, sduration9);
            Projects obj10 = new Projects(sproj10, sdomain10, steam10, sduration10);


            projectsList.add(obj1);
            projectsList.add(obj2);
            projectsList.add(obj3);
            projectsList.add(obj4);
            projectsList.add(obj5);
            projectsList.add(obj6);
            projectsList.add(obj7);
            projectsList.add(obj8);
            projectsList.add(obj9);
            projectsList.add(obj10);


            String encObjString = OtoString(projectsList, MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));

            new SaveProjects().execute(encObjString);

        } catch (Exception e) {
        }
    }

    void showDeletDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder
                .setMessage("Do you want to delete this project ?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                edittedFlag = 1;
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

                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#00bcd4"));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#00bcd4"));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(getActivity()));
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(getActivity()));
            }
        });

        alertDialog.show();

    }

    void deleteProject() {
        View v = (View) rootView.findViewById(R.id.projectline9);
        if (v.getVisibility() == View.VISIBLE) {

            View v1 = (View) rootView.findViewById(R.id.projectline9);
            v1.setVisibility(View.GONE);

            RelativeLayout projectrl = (RelativeLayout) rootView.findViewById(R.id.projectrl10);
            projectrl.setVisibility(View.GONE);

            projectscount--;

            TextView t = (TextView) rootView.findViewById(R.id.addmoreprojecttxt);
            ImageView i = (ImageView) rootView.findViewById(R.id.addmoreprojectimg);
            addmoreproject.setVisibility(View.VISIBLE);
            t.setVisibility(View.VISIBLE);
            i.setVisibility(View.VISIBLE);
        } else {
            v = (View) rootView.findViewById(R.id.projectline8);
            if (v.getVisibility() == View.VISIBLE) {


                View v1 = (View) rootView.findViewById(R.id.projectline8);
                v1.setVisibility(View.GONE);

                RelativeLayout projectrl = (RelativeLayout) rootView.findViewById(R.id.projectrl9);
                projectrl.setVisibility(View.GONE);

                projectscount--;

            } else {
                v = (View) rootView.findViewById(R.id.projectline7);
                if (v.getVisibility() == View.VISIBLE) {

                    View v1 = (View) rootView.findViewById(R.id.projectline7);
                    v1.setVisibility(View.GONE);

                    RelativeLayout projectrl = (RelativeLayout) rootView.findViewById(R.id.projectrl8);
                    projectrl.setVisibility(View.GONE);

                    projectscount--;
                } else {
                    v = (View) rootView.findViewById(R.id.projectline6);
                    if (v.getVisibility() == View.VISIBLE) {

                        View v1 = (View) rootView.findViewById(R.id.projectline6);
                        v1.setVisibility(View.GONE);

                        RelativeLayout projectrl = (RelativeLayout) rootView.findViewById(R.id.projectrl7);
                        projectrl.setVisibility(View.GONE);

                        projectscount--;

                    } else {
                        v = (View) rootView.findViewById(R.id.projectline5);
                        if (v.getVisibility() == View.VISIBLE) {

                            View v1 = (View) rootView.findViewById(R.id.projectline5);
                            v1.setVisibility(View.GONE);

                            RelativeLayout projectrl = (RelativeLayout) rootView.findViewById(R.id.projectrl6);
                            projectrl.setVisibility(View.GONE);

                            projectscount--;


                        } else {
                            v = (View) rootView.findViewById(R.id.projectline4);
                            if (v.getVisibility() == View.VISIBLE) {

                                View v1 = (View) rootView.findViewById(R.id.projectline4);
                                v1.setVisibility(View.GONE);

                                RelativeLayout projectrl = (RelativeLayout) rootView.findViewById(R.id.projectrl5);
                                projectrl.setVisibility(View.GONE);

                                projectscount--;
                            } else {
                                v = (View) rootView.findViewById(R.id.projectline3);
                                if (v.getVisibility() == View.VISIBLE) {

                                    View v1 = (View) rootView.findViewById(R.id.projectline3);
                                    v1.setVisibility(View.GONE);

                                    RelativeLayout projectrl = (RelativeLayout) rootView.findViewById(R.id.projectrl4);
                                    projectrl.setVisibility(View.GONE);

                                    projectscount--;

                                } else {
                                    v = (View) rootView.findViewById(R.id.projectline2);
                                    if (v.getVisibility() == View.VISIBLE) {

                                        View v1 = (View) rootView.findViewById(R.id.projectline2);
                                        v1.setVisibility(View.GONE);

                                        RelativeLayout projectrl = (RelativeLayout) rootView.findViewById(R.id.projectrl3);
                                        projectrl.setVisibility(View.GONE);

                                        projectscount--;

                                    } else {
                                        v = (View) rootView.findViewById(R.id.projectline1);
                                        if (v.getVisibility() == View.VISIBLE) {

                                            View v1 = (View) rootView.findViewById(R.id.projectline1);
                                            v1.setVisibility(View.GONE);

                                            RelativeLayout projectrl = (RelativeLayout) rootView.findViewById(R.id.projectrl2);
                                            projectrl.setVisibility(View.GONE);

                                            projectscount--;


                                        }
//                                        if(projectscount==0)
                                        else {
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

        } else if (d == 8) {
            sproj10 = proj10.getText().toString();
            sdomain10 = domain10.getText().toString();
            steam10 = team10.getText().toString();
            sduration10 = duration10.getText().toString();
            sproj9 = proj9.getText().toString();
            sdomain9 = domain9.getText().toString();
            steam9 = team9.getText().toString();
            sduration9 = duration9.getText().toString();


            sproj8 = sproj9;
            sdomain8 = sdomain9;
            steam8 = steam9;
            sduration8 = sduration9;

            proj9.setText("");
            domain9.setText("");
            team9.setText("");
            duration9.setText("");

            proj8.setText(sproj8);
            domain8.setText(sdomain8);
            team8.setText(steam8);
            duration8.setText(sduration8);

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

        } else if (d == 7) {
            sproj10 = proj10.getText().toString();
            sdomain10 = domain10.getText().toString();
            steam10 = team10.getText().toString();
            sduration10 = duration10.getText().toString();
            sproj9 = proj9.getText().toString();
            sdomain9 = domain9.getText().toString();
            steam9 = team9.getText().toString();
            sduration9 = duration9.getText().toString();
            sproj8 = proj8.getText().toString();
            sdomain8 = domain8.getText().toString();
            steam8 = team8.getText().toString();
            sduration8 = duration8.getText().toString();

            sproj7 = sproj8;
            sdomain7 = sdomain8;
            steam7 = steam8;
            sduration7 = sduration8;

            proj8.setText("");
            domain8.setText("");
            team8.setText("");
            duration8.setText("");

            proj7.setText(sproj7);
            domain7.setText(sdomain7);
            team7.setText(steam7);
            duration7.setText(sduration7);

            sproj8 = sproj9;
            sdomain8 = sdomain9;
            steam8 = steam9;
            sduration8 = sduration9;

            proj9.setText("");
            domain9.setText("");
            team9.setText("");
            duration9.setText("");

            proj8.setText(sproj8);
            domain8.setText(sdomain8);
            team8.setText(steam8);
            duration8.setText(sduration8);

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

        } else if (d == 6) {
            sproj10 = proj10.getText().toString();
            sdomain10 = domain10.getText().toString();
            steam10 = team10.getText().toString();
            sduration10 = duration10.getText().toString();
            sproj9 = proj9.getText().toString();
            sdomain9 = domain9.getText().toString();
            steam9 = team9.getText().toString();
            sduration9 = duration9.getText().toString();
            sproj8 = proj8.getText().toString();
            sdomain8 = domain8.getText().toString();
            steam8 = team8.getText().toString();
            sduration8 = duration8.getText().toString();
            sproj7 = proj7.getText().toString();
            sdomain7 = domain7.getText().toString();
            steam7 = team7.getText().toString();
            sduration7 = duration7.getText().toString();


            sproj6 = sproj7;
            sdomain6 = sdomain7;
            steam6 = steam7;
            sduration6 = sduration7;

            proj7.setText("");
            domain7.setText("");
            team7.setText("");
            duration7.setText("");

            proj6.setText(sproj6);
            domain6.setText(sdomain6);
            team6.setText(steam6);
            duration6.setText(sduration6);

            sproj7 = sproj8;
            sdomain7 = sdomain8;
            steam7 = steam8;
            sduration7 = sduration8;

            proj8.setText("");
            domain8.setText("");
            team8.setText("");
            duration8.setText("");

            proj7.setText(sproj7);
            domain7.setText(sdomain7);
            team7.setText(steam7);
            duration7.setText(sduration7);

            sproj8 = sproj9;
            sdomain8 = sdomain9;
            steam8 = steam9;
            sduration8 = sduration9;

            proj9.setText("");
            domain9.setText("");
            team9.setText("");
            duration9.setText("");

            proj8.setText(sproj8);
            domain8.setText(sdomain8);
            team8.setText(steam8);
            duration8.setText(sduration8);

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

        } else if (d == 5) {
            sproj10 = proj10.getText().toString();
            sdomain10 = domain10.getText().toString();
            steam10 = team10.getText().toString();
            sduration10 = duration10.getText().toString();
            sproj9 = proj9.getText().toString();
            sdomain9 = domain9.getText().toString();
            steam9 = team9.getText().toString();
            sduration9 = duration9.getText().toString();
            sproj8 = proj8.getText().toString();
            sdomain8 = domain8.getText().toString();
            steam8 = team8.getText().toString();
            sduration8 = duration8.getText().toString();
            sproj7 = proj7.getText().toString();
            sdomain7 = domain7.getText().toString();
            steam7 = team7.getText().toString();
            sduration7 = duration7.getText().toString();
            sproj6 = proj6.getText().toString();
            sdomain6 = domain6.getText().toString();
            steam6 = team6.getText().toString();
            sduration6 = duration6.getText().toString();


            sproj5 = sproj6;
            sdomain5 = sdomain6;
            steam5 = steam6;
            sduration5 = sduration6;

            proj6.setText("");
            domain6.setText("");
            team6.setText("");
            duration6.setText("");

            proj5.setText(sproj5);
            domain5.setText(sdomain5);
            team5.setText(steam5);
            duration5.setText(sduration5);

            sproj6 = sproj7;
            sdomain6 = sdomain7;
            steam6 = steam7;
            sduration6 = sduration7;

            proj7.setText("");
            domain7.setText("");
            team7.setText("");
            duration7.setText("");

            proj6.setText(sproj6);
            domain6.setText(sdomain6);
            team6.setText(steam6);
            duration6.setText(sduration6);

            sproj7 = sproj8;
            sdomain7 = sdomain8;
            steam7 = steam8;
            sduration7 = sduration8;

            proj8.setText("");
            domain8.setText("");
            team8.setText("");
            duration8.setText("");

            proj7.setText(sproj7);
            domain7.setText(sdomain7);
            team7.setText(steam7);
            duration7.setText(sduration7);

            sproj8 = sproj9;
            sdomain8 = sdomain9;
            steam8 = steam9;
            sduration8 = sduration9;

            proj9.setText("");
            domain9.setText("");
            team9.setText("");
            duration9.setText("");

            proj8.setText(sproj8);
            domain8.setText(sdomain8);
            team8.setText(steam8);
            duration8.setText(sduration8);

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

        } else if (d == 4) {
            sproj10 = proj10.getText().toString();
            sdomain10 = domain10.getText().toString();
            steam10 = team10.getText().toString();
            sduration10 = duration10.getText().toString();
            sproj9 = proj9.getText().toString();
            sdomain9 = domain9.getText().toString();
            steam9 = team9.getText().toString();
            sduration9 = duration9.getText().toString();
            sproj8 = proj8.getText().toString();
            sdomain8 = domain8.getText().toString();
            steam8 = team8.getText().toString();
            sduration8 = duration8.getText().toString();
            sproj7 = proj7.getText().toString();
            sdomain7 = domain7.getText().toString();
            steam7 = team7.getText().toString();
            sduration7 = duration7.getText().toString();
            sproj6 = proj6.getText().toString();
            sdomain6 = domain6.getText().toString();
            steam6 = team6.getText().toString();
            sduration6 = duration6.getText().toString();
            sproj5 = proj5.getText().toString();
            sdomain5 = domain5.getText().toString();
            steam5 = team5.getText().toString();
            sduration5 = duration5.getText().toString();


            sproj4 = sproj5;
            sdomain4 = sdomain5;
            steam4 = steam5;
            sduration4 = sduration5;

            proj5.setText("");
            domain5.setText("");
            team5.setText("");
            duration5.setText("");

            proj4.setText(sproj4);
            domain4.setText(sdomain4);
            team4.setText(steam4);
            duration4.setText(sduration4);

            sproj5 = sproj6;
            sdomain5 = sdomain6;
            steam5 = steam6;
            sduration5 = sduration6;

            proj6.setText("");
            domain6.setText("");
            team6.setText("");
            duration6.setText("");

            proj5.setText(sproj5);
            domain5.setText(sdomain5);
            team5.setText(steam5);
            duration5.setText(sduration5);

            sproj6 = sproj7;
            sdomain6 = sdomain7;
            steam6 = steam7;
            sduration6 = sduration7;

            proj7.setText("");
            domain7.setText("");
            team7.setText("");
            duration7.setText("");

            proj6.setText(sproj6);
            domain6.setText(sdomain6);
            team6.setText(steam6);
            duration6.setText(sduration6);

            sproj7 = sproj8;
            sdomain7 = sdomain8;
            steam7 = steam8;
            sduration7 = sduration8;

            proj8.setText("");
            domain8.setText("");
            team8.setText("");
            duration8.setText("");

            proj7.setText(sproj7);
            domain7.setText(sdomain7);
            team7.setText(steam7);
            duration7.setText(sduration7);

            sproj8 = sproj9;
            sdomain8 = sdomain9;
            steam8 = steam9;
            sduration8 = sduration9;

            proj9.setText("");
            domain9.setText("");
            team9.setText("");
            duration9.setText("");

            proj8.setText(sproj8);
            domain8.setText(sdomain8);
            team8.setText(steam8);
            duration8.setText(sduration8);

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

        } else if (d == 3) {
            sproj10 = proj10.getText().toString();
            sdomain10 = domain10.getText().toString();
            steam10 = team10.getText().toString();
            sduration10 = duration10.getText().toString();
            sproj9 = proj9.getText().toString();
            sdomain9 = domain9.getText().toString();
            steam9 = team9.getText().toString();
            sduration9 = duration9.getText().toString();
            sproj8 = proj8.getText().toString();
            sdomain8 = domain8.getText().toString();
            steam8 = team8.getText().toString();
            sduration8 = duration8.getText().toString();
            sproj7 = proj7.getText().toString();
            sdomain7 = domain7.getText().toString();
            steam7 = team7.getText().toString();
            sduration7 = duration7.getText().toString();
            sproj6 = proj6.getText().toString();
            sdomain6 = domain6.getText().toString();
            steam6 = team6.getText().toString();
            sduration6 = duration6.getText().toString();
            sproj5 = proj5.getText().toString();
            sdomain5 = domain5.getText().toString();
            steam5 = team5.getText().toString();
            sduration5 = duration5.getText().toString();
            sproj4 = proj4.getText().toString();
            sdomain4 = domain4.getText().toString();
            steam4 = team4.getText().toString();
            sduration4 = duration4.getText().toString();


            sproj3 = sproj4;
            sdomain3 = sdomain4;
            steam3 = steam4;
            sduration3 = sduration4;

            proj4.setText("");
            domain4.setText("");
            team4.setText("");
            duration4.setText("");

            proj3.setText(sproj3);
            domain3.setText(sdomain3);
            team3.setText(steam3);
            duration3.setText(sduration3);

            sproj4 = sproj5;
            sdomain4 = sdomain5;
            steam4 = steam5;
            sduration4 = sduration5;

            proj5.setText("");
            domain5.setText("");
            team5.setText("");
            duration5.setText("");

            proj4.setText(sproj4);
            domain4.setText(sdomain4);
            team4.setText(steam4);
            duration4.setText(sduration4);

            sproj5 = sproj6;
            sdomain5 = sdomain6;
            steam5 = steam6;
            sduration5 = sduration6;

            proj6.setText("");
            domain6.setText("");
            team6.setText("");
            duration6.setText("");

            proj5.setText(sproj5);
            domain5.setText(sdomain5);
            team5.setText(steam5);
            duration5.setText(sduration5);

            sproj6 = sproj7;
            sdomain6 = sdomain7;
            steam6 = steam7;
            sduration6 = sduration7;

            proj7.setText("");
            domain7.setText("");
            team7.setText("");
            duration7.setText("");

            proj6.setText(sproj6);
            domain6.setText(sdomain6);
            team6.setText(steam6);
            duration6.setText(sduration6);

            sproj7 = sproj8;
            sdomain7 = sdomain8;
            steam7 = steam8;
            sduration7 = sduration8;

            proj8.setText("");
            domain8.setText("");
            team8.setText("");
            duration8.setText("");

            proj7.setText(sproj7);
            domain7.setText(sdomain7);
            team7.setText(steam7);
            duration7.setText(sduration7);

            sproj8 = sproj9;
            sdomain8 = sdomain9;
            steam8 = steam9;
            sduration8 = sduration9;

            proj9.setText("");
            domain9.setText("");
            team9.setText("");
            duration9.setText("");

            proj8.setText(sproj8);
            domain8.setText(sdomain8);
            team8.setText(steam8);
            duration8.setText(sduration8);

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

        } else if (d == 2) {
            sproj10 = proj10.getText().toString();
            sdomain10 = domain10.getText().toString();
            steam10 = team10.getText().toString();
            sduration10 = duration10.getText().toString();
            sproj9 = proj9.getText().toString();
            sdomain9 = domain9.getText().toString();
            steam9 = team9.getText().toString();
            sduration9 = duration9.getText().toString();
            sproj8 = proj8.getText().toString();
            sdomain8 = domain8.getText().toString();
            steam8 = team8.getText().toString();
            sduration8 = duration8.getText().toString();
            sproj7 = proj7.getText().toString();
            sdomain7 = domain7.getText().toString();
            steam7 = team7.getText().toString();
            sduration7 = duration7.getText().toString();
            sproj6 = proj6.getText().toString();
            sdomain6 = domain6.getText().toString();
            steam6 = team6.getText().toString();
            sduration6 = duration6.getText().toString();
            sproj5 = proj5.getText().toString();
            sdomain5 = domain5.getText().toString();
            steam5 = team5.getText().toString();
            sduration5 = duration5.getText().toString();
            sproj4 = proj4.getText().toString();
            sdomain4 = domain4.getText().toString();
            steam4 = team4.getText().toString();
            sduration4 = duration4.getText().toString();
            sproj3 = proj3.getText().toString();
            sdomain3 = domain3.getText().toString();
            steam3 = team3.getText().toString();
            sduration3 = duration3.getText().toString();

            sproj2 = sproj3;
            sdomain2 = sdomain3;
            steam2 = steam3;
            sduration2 = sduration3;

            proj3.setText("");
            domain3.setText("");
            team3.setText("");
            duration3.setText("");

            proj2.setText(sproj2);
            domain2.setText(sdomain2);
            team2.setText(steam2);
            duration2.setText(sduration2);

            sproj3 = sproj4;
            sdomain3 = sdomain4;
            steam3 = steam4;
            sduration3 = sduration4;

            proj4.setText("");
            domain4.setText("");
            team4.setText("");
            duration4.setText("");

            proj3.setText(sproj3);
            domain3.setText(sdomain3);
            team3.setText(steam3);
            duration3.setText(sduration3);

            sproj4 = sproj5;
            sdomain4 = sdomain5;
            steam4 = steam5;
            sduration4 = sduration5;

            proj5.setText("");
            domain5.setText("");
            team5.setText("");
            duration5.setText("");

            proj4.setText(sproj4);
            domain4.setText(sdomain4);
            team4.setText(steam4);
            duration4.setText(sduration4);

            sproj5 = sproj6;
            sdomain5 = sdomain6;
            steam5 = steam6;
            sduration5 = sduration6;

            proj6.setText("");
            domain6.setText("");
            team6.setText("");
            duration6.setText("");

            proj5.setText(sproj5);
            domain5.setText(sdomain5);
            team5.setText(steam5);
            duration5.setText(sduration5);

            sproj6 = sproj7;
            sdomain6 = sdomain7;
            steam6 = steam7;
            sduration6 = sduration7;

            proj7.setText("");
            domain7.setText("");
            team7.setText("");
            duration7.setText("");

            proj6.setText(sproj6);
            domain6.setText(sdomain6);
            team6.setText(steam6);
            duration6.setText(sduration6);

            sproj7 = sproj8;
            sdomain7 = sdomain8;
            steam7 = steam8;
            sduration7 = sduration8;

            proj8.setText("");
            domain8.setText("");
            team8.setText("");
            duration8.setText("");

            proj7.setText(sproj7);
            domain7.setText(sdomain7);
            team7.setText(steam7);
            duration7.setText(sduration7);

            sproj8 = sproj9;
            sdomain8 = sdomain9;
            steam8 = steam9;
            sduration8 = sduration9;

            proj9.setText("");
            domain9.setText("");
            team9.setText("");
            duration9.setText("");

            proj8.setText(sproj8);
            domain8.setText(sdomain8);
            team8.setText(steam8);
            duration8.setText(sduration8);

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

        } else if (d == 1) {
            sproj10 = proj10.getText().toString();
            sdomain10 = domain10.getText().toString();
            steam10 = team10.getText().toString();
            sduration10 = duration10.getText().toString();
            sproj9 = proj9.getText().toString();
            sdomain9 = domain9.getText().toString();
            steam9 = team9.getText().toString();
            sduration9 = duration9.getText().toString();
            sproj8 = proj8.getText().toString();
            sdomain8 = domain8.getText().toString();
            steam8 = team8.getText().toString();
            sduration8 = duration8.getText().toString();
            sproj7 = proj7.getText().toString();
            sdomain7 = domain7.getText().toString();
            steam7 = team7.getText().toString();
            sduration7 = duration7.getText().toString();
            sproj6 = proj6.getText().toString();
            sdomain6 = domain6.getText().toString();
            steam6 = team6.getText().toString();
            sduration6 = duration6.getText().toString();
            sproj5 = proj5.getText().toString();
            sdomain5 = domain5.getText().toString();
            steam5 = team5.getText().toString();
            sduration5 = duration5.getText().toString();
            sproj4 = proj4.getText().toString();
            sdomain4 = domain4.getText().toString();
            steam4 = team4.getText().toString();
            sduration4 = duration4.getText().toString();
            sproj3 = proj3.getText().toString();
            sdomain3 = domain3.getText().toString();
            steam3 = team3.getText().toString();
            sduration3 = duration3.getText().toString();
            sproj2 = proj2.getText().toString();
            sdomain2 = domain2.getText().toString();
            steam2 = team2.getText().toString();
            sduration2 = duration2.getText().toString();


            sproj1 = sproj2;
            sdomain1 = sdomain2;
            steam1 = steam2;
            sduration1 = sduration2;

            proj2.setText("");
            domain2.setText("");
            team2.setText("");
            duration2.setText("");

            proj1.setText(sproj1);
            domain1.setText(sdomain1);
            team1.setText(steam1);
            duration1.setText(sduration1);

            sproj2 = sproj3;
            sdomain2 = sdomain3;
            steam2 = steam3;
            sduration2 = sduration3;

            proj3.setText("");
            domain3.setText("");
            team3.setText("");
            duration3.setText("");

            proj2.setText(sproj2);
            domain2.setText(sdomain2);
            team2.setText(steam2);
            duration2.setText(sduration2);

            sproj3 = sproj4;
            sdomain3 = sdomain4;
            steam3 = steam4;
            sduration3 = sduration4;

            proj4.setText("");
            domain4.setText("");
            team4.setText("");
            duration4.setText("");

            proj3.setText(sproj3);
            domain3.setText(sdomain3);
            team3.setText(steam3);
            duration3.setText(sduration3);

            sproj4 = sproj5;
            sdomain4 = sdomain5;
            steam4 = steam5;
            sduration4 = sduration5;

            proj5.setText("");
            domain5.setText("");
            team5.setText("");
            duration5.setText("");

            proj4.setText(sproj4);
            domain4.setText(sdomain4);
            team4.setText(steam4);
            duration4.setText(sduration4);

            sproj5 = sproj6;
            sdomain5 = sdomain6;
            steam5 = steam6;
            sduration5 = sduration6;

            proj6.setText("");
            domain6.setText("");
            team6.setText("");
            duration6.setText("");

            proj5.setText(sproj5);
            domain5.setText(sdomain5);
            team5.setText(steam5);
            duration5.setText(sduration5);

            sproj6 = sproj7;
            sdomain6 = sdomain7;
            steam6 = steam7;
            sduration6 = sduration7;

            proj7.setText("");
            domain7.setText("");
            team7.setText("");
            duration7.setText("");

            proj6.setText(sproj6);
            domain6.setText(sdomain6);
            team6.setText(steam6);
            duration6.setText(sduration6);

            sproj7 = sproj8;
            sdomain7 = sdomain8;
            steam7 = steam8;
            sduration7 = sduration8;

            proj8.setText("");
            domain8.setText("");
            team8.setText("");
            duration8.setText("");

            proj7.setText(sproj7);
            domain7.setText(sdomain7);
            team7.setText(steam7);
            duration7.setText(sduration7);

            sproj8 = sproj9;
            sdomain8 = sdomain9;
            steam8 = steam9;
            sduration8 = sduration9;

            proj9.setText("");
            domain9.setText("");
            team9.setText("");
            duration9.setText("");

            proj8.setText(sproj8);
            domain8.setText(sdomain8);
            team8.setText(steam8);
            duration8.setText(sduration8);

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

            sproj1 = proj1.getText().toString();
            sdomain1 = domain1.getText().toString();
            steam1 = team1.getText().toString();
            sduration1 = duration1.getText().toString();

            if(sproj1.equals("") && sdomain1.equals("") && steam1.equals("") && sduration1.equals("")){
                editproj =1;
            }

            if(editproj==1){
                save();
            }

        }

    }



    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Animation animation = super.onCreateAnimation(transit, enter, nextAnim);

        // HW layer support only exists on API 11+
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

    class SaveProjects extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0

            params.add(new BasicNameValuePair("d", param[0]));       //0

            json = jParser.makeHttpRequest(Z.url_saveprojects, "GET", params);
            try {
                r = json.getString("info");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }


        @Override
        protected void onPostExecute(String result) {

            if (result.equals("success")) {

                if (role.equals("student"))
                    ShouldAnimateProfile.EditProfile.setResult(MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE);
                else if (role.equals("alumni"))
                    ShouldAnimateProfile.EditProfileAlumni.setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);

                edittedFlag = 0;
            } else
                Toast.makeText(getActivity(), "Try again !", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        bannerView.destroy();
        bannerView2.destroy();
        super.onDestroy();
    }
}