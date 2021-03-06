package placeme.octopusites.com.placeme;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kbeanie.multipicker.api.ImagePicker;
import com.kbeanie.multipicker.api.Picker;
import com.kbeanie.multipicker.api.callbacks.ImagePickerCallback;
import com.kbeanie.multipicker.api.entity.ChosenImage;
import com.soundcloud.android.crop.Crop;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import me.shaohui.advancedluban.Luban;
import me.shaohui.advancedluban.OnCompressListener;

import static placeme.octopusites.com.placeme.AES4all.Decrypt;
import static placeme.octopusites.com.placeme.AES4all.Encrypt;
import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;

public class Welcome extends AppCompatActivity implements ImagePickerCallback {


    File Imgfile;
    public static final String Intro = "intro";
    String encUsersName, plainUsername, usertype, isactivated, passwordstr, encPassword, encfname, enclname, encmobile, encinstOrEmail, encrole, encProMail;
    String resultofop = "";
    String SELECTED_ROLE;
    private String EmailCred = "";
    String digest1, digest2, status;
    String fname, lname, mobile;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    int currentPosition = 0, lastPosition = 0;
    int path = 0;
    int crop_flag = 0;
    String filepath = "", filename = "";
    private String finalPath;
    String directory;
    private CustomViewPager viewPager;
    private MyAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private Button btnNext, btnPrev;
    ProgressBar nextProgress;
    Boolean errorFlagIntro = false, genrateCodeFlag = false;
    View WelcomeEmailView, WelcomePasswordView, WelComeIntroView, WelcomeRoleView, WelcomeCreatePasswordView, WelComeIntroThroughAdminView, StudentRoleSelectedView;
    EditText passwordedittext;
    TextInputEditText usernameedittext;
    TextInputLayout usernameTextInputLayout, fnameTextInputLayout, lnameTextInputLayout, mobileTextInputLayout;
    ImageView enteremailimage;
    EditText fnameEditText, lnameEditText, mobileEditText, instOrEmail;
    CircleImageView profilePicture;
    View studentBlock, alumniBlock, adminBlock, hrBlock;
    EditText enterPassword, confirmPassword;
    private ImageView resultView;
    ImagePicker imagePicker;
    FrameLayout crop_layout;
    List<String> response;
    boolean throughAdminFlag = false, errorFlagThroughAdminIntro = false;
    private String instOrEmailstr;
    private String confrimpass;
    TextView adminInfo, forgotpassword;
    boolean instoremailerror = false;
    private static String android_id, device_id;
    String adminInstitute, adminfname, adminlname;
    Typeface fa, bold, light;


    //animation related stuff
    TextView rolewelcometextviewcontext1;
    TextView rolewelcometextviewcontext2;
    TextView rolewelcometextviewcontext3;
    CardView studentrl;
    //    CardView alumnirl;
    CardView tporl;
    CardView hrrl;
    ImageView cancel;
    ImageView enterpasswordimage;
    TextView createpasswordwelcometextviewcontext1, createpasswordwelcometextviewcontext2;
    TextInputLayout passwordTextInputLayout, confirmPasswordTextInputLayout;

    String foundFname;

    TextInputLayout welcomepasswordTextInputLayout;
    TextView welcomepasswordwelcometextviewcontext1, welcomepasswordwelcometextviewcontext2;
    ImageView welcomepasswordenterpasswordimage;
    ProgressBar updateProgress;

    public void setWelComeEmailView(View v) {
        WelcomeEmailView = v;
        usernameedittext = (TextInputEditText) WelcomeEmailView.findViewById(R.id.welcomeusername);
        usernameTextInputLayout = (TextInputLayout) WelcomeEmailView.findViewById(R.id.usernameTextInputLayout);
        TextView welcometextviewcontext1 = (TextView) WelcomeEmailView.findViewById(R.id.welcometextviewcontext1);
        TextView welcometextviewcontext2 = (TextView) WelcomeEmailView.findViewById(R.id.welcometextviewcontext2);
        enteremailimage = (ImageView) WelcomeEmailView.findViewById(R.id.enteremailimage);
        usernameTextInputLayout.setTypeface(light);
        usernameedittext.setTypeface(bold);
        welcometextviewcontext1.setTypeface(bold);
        welcometextviewcontext2.setTypeface(light);
        Z.fade(this, enteremailimage);
        Z.fadeandmovedown(this, usernameTextInputLayout);
        Z.slideinleft1(this, welcometextviewcontext1);
        Z.slideinleft2(this, welcometextviewcontext2);
        usernameedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                usernameTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    void animateAllViews() {
        studentrl.setVisibility(View.VISIBLE);
//        alumnirl.setVisibility(View.VISIBLE);
        tporl.setVisibility(View.VISIBLE);
        hrrl.setVisibility(View.VISIBLE);
        Z.slideinleft1(this, rolewelcometextviewcontext1);
        Z.slideinleft2(this, rolewelcometextviewcontext2);
        Z.fade(this, rolewelcometextviewcontext3);
        Z.scale1(this, studentrl);
//        Z.scale2(this, alumnirl);
        Z.scale3(this, tporl);
        Z.scale4(this, hrrl);
    }

    public void animateStudentRl() {

        Intent intent = new Intent(Welcome.this, StudentRoleSelected.class);

        EasyTransitionOptions options =
                EasyTransitionOptions.makeTransitionOptions(
                        Welcome.this,
                        WelcomeRoleView.findViewById(R.id.iv_icon_student)
                );

        // start transition
        EasyTransition.startActivityForResult(intent, 1111, options);


    }

//    void animateAlumniRl() {
//        Intent intent = new Intent(Welcome.this, AlumniRoleSelected.class);
//
//        EasyTransitionOptions options =
//                EasyTransitionOptions.makeTransitionOptions(
//                        Welcome.this,
//                        WelcomeRoleView.findViewById(R.id.iv_icon_alumni)
//
//                );
//
//        // start transition
//        EasyTransition.startActivityForResult(intent, 2222, options);
//    }

    void animateTPORl() {
        Intent intent = new Intent(Welcome.this, TPORoleSelected.class);

        EasyTransitionOptions options =
                EasyTransitionOptions.makeTransitionOptions(
                        Welcome.this,
                        WelcomeRoleView.findViewById(R.id.iv_icon_tpo)

                );

        // start transition
        EasyTransition.startActivityForResult(intent, 3333, options);
    }

    void animateHRRl() {
        Intent intent = new Intent(Welcome.this, HRRoleSelected.class);

        EasyTransitionOptions options =
                EasyTransitionOptions.makeTransitionOptions(
                        Welcome.this,
                        WelcomeRoleView.findViewById(R.id.iv_icon_hr)

                );

        // start transition
        EasyTransition.startActivityForResult(intent, 4444, options);
    }


    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

    public void setWelComePasswordView(View v) {
        WelcomePasswordView = v;
        passwordedittext = (EditText) WelcomePasswordView.findViewById(R.id.welcomepassword);
        forgotpassword = (TextView) WelcomePasswordView.findViewById(R.id.forgot);
        forgotpassword.setTypeface(Z.getBold(this));
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Welcome.this, ForgotPasswordDialog.class));
            }
        });

        passwordedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                welcomepasswordTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        welcomepasswordwelcometextviewcontext1 = (TextView) WelcomePasswordView.findViewById(R.id.welcometextviewcontext1);
        welcomepasswordwelcometextviewcontext2 = (TextView) WelcomePasswordView.findViewById(R.id.welcometextviewcontext2);
        welcomepasswordenterpasswordimage = (ImageView) WelcomePasswordView.findViewById(R.id.enterpasswordimage);
        welcomepasswordTextInputLayout = (TextInputLayout) WelcomePasswordView.findViewById(R.id.passwordTextInputLayout);

        welcomepasswordwelcometextviewcontext1.setTypeface(bold);
        welcomepasswordwelcometextviewcontext2.setTypeface(light);
        passwordedittext.setTypeface(Z.getBold(this));
        welcomepasswordTextInputLayout.setTypeface(Z.getLight(this));

        try {
            welcomepasswordwelcometextviewcontext1.setText("Welcome back " + Decrypt(foundFname, digest1, digest2) + " !");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("TAG", "exp:" + e.getMessage());
        }


//        Z.fade(this, welcomepasswordenterpasswordimage);
//        Z.fadeandmovedown(this, forgotpassword);
//        Z.fadeandmovedown(this, welcomepasswordTextInputLayout);
//        Z.slideinleft1(this, welcomepasswordwelcometextviewcontext1);
//        Z.slideinleft2(this, welcomepasswordwelcometextviewcontext2);
    }

    public void setWelComeCreatePasswordView(View v) {

        WelcomeCreatePasswordView = v;
        enterPassword = (EditText) WelcomeCreatePasswordView.findViewById(R.id.enterPassword);
        confirmPassword = (EditText) WelcomeCreatePasswordView.findViewById(R.id.confirmPassword);
//minor
        enterPassword.setTypeface(Z.getBold(this));
        confirmPassword.setTypeface(Z.getBold(this));
        enterPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                passwordTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                confirmPasswordTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        createpasswordwelcometextviewcontext1 = (TextView) WelcomeCreatePasswordView.findViewById(R.id.welcometextviewcontext1);
        createpasswordwelcometextviewcontext2 = (TextView) WelcomeCreatePasswordView.findViewById(R.id.welcometextviewcontext2);
        passwordTextInputLayout = (TextInputLayout) WelcomeCreatePasswordView.findViewById(R.id.passwordTextInputLayout);
        confirmPasswordTextInputLayout = (TextInputLayout) WelcomeCreatePasswordView.findViewById(R.id.confirmPasswordTextInputLayout);

        passwordTextInputLayout.setTypeface(light);
        confirmPasswordTextInputLayout.setTypeface(light);

        createpasswordwelcometextviewcontext2.setText("\"Treat your password like your toothbrush. Don't let anybody else use it, and get a new one every six months\" - Clifford Stoll");
        createpasswordwelcometextviewcontext1.setTypeface(Z.getBold(this));
        createpasswordwelcometextviewcontext2.setTypeface(Z.getBoldItalic(this));

        enterpasswordimage = (ImageView) WelcomeCreatePasswordView.findViewById(R.id.enterpasswordimage);

    }

    public void setWelComeIntroView(View v) {
        WelComeIntroView = v;

        fnameEditText = (EditText) WelComeIntroView.findViewById(R.id.fname);
        lnameEditText = (EditText) WelComeIntroView.findViewById(R.id.lname);
        mobileEditText = (EditText) WelComeIntroView.findViewById(R.id.mobile);
        profilePicture = (CircleImageView) WelComeIntroView.findViewById(R.id.profilePic);
        updateProgress = (ProgressBar) WelComeIntroView.findViewById(R.id.updateProgress);
        ImageButton iv_camera = (ImageButton) WelComeIntroView.findViewById(R.id.iv_camera);

        fnameEditText.setTypeface(Z.getBold(this));
        lnameEditText.setTypeface(Z.getBold(this));
        mobileEditText.setTypeface(Z.getBold(this));

        TextView getProfilePictureMsg = (TextView) WelComeIntroView.findViewById(R.id.getProfilePictureMsg);
        TextView welcometextviewcontext2 = (TextView) WelComeIntroView.findViewById(R.id.welcometextviewcontext2);
        TextView welcometextviewcontext3 = (TextView) WelComeIntroView.findViewById(R.id.welcometextviewcontext3);

        fnameTextInputLayout = (TextInputLayout) WelComeIntroView.findViewById(R.id.fnameTextInputLayout);
        lnameTextInputLayout = (TextInputLayout) WelComeIntroView.findViewById(R.id.lnameTextInputLayout);
        mobileTextInputLayout = (TextInputLayout) WelComeIntroView.findViewById(R.id.mobileTextInputLayout);

        Z.slideinleft1(this, getProfilePictureMsg);
        Z.slideinleft2(this, welcometextviewcontext2);

        Z.fade(this, welcometextviewcontext3);
        Z.fade(this, profilePicture);
        Z.fade(this, iv_camera);
        Z.fadeandmovedown(this, fnameTextInputLayout);
        Z.fadeandmovedown(this, lnameTextInputLayout);
        Z.fadeandmovedown(this, mobileTextInputLayout);


        fnameTextInputLayout.setTypeface(Z.getLight(this));
        lnameTextInputLayout.setTypeface(Z.getLight(this));
        mobileTextInputLayout.setTypeface(Z.getLight(this));

        welcometextviewcontext2.setText("\"A good photograph is knowing where to stand.\" - Ansel Adams");

        getProfilePictureMsg.setTypeface(Z.getBold(this));
        welcometextviewcontext2.setTypeface(Z.getBoldItalic(this));
        welcometextviewcontext3.setTypeface(Z.getLight(this));


        iv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestCropImage();
            }
        });
        fnameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                fnameTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        lnameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                lnameTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mobileEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mobileTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void setWelComeIntroThroughAdminView(View v) {
        WelComeIntroThroughAdminView = v;

        fnameEditText = (EditText) WelComeIntroThroughAdminView.findViewById(R.id.fname);
        lnameEditText = (EditText) WelComeIntroThroughAdminView.findViewById(R.id.lname);
        mobileEditText = (EditText) WelComeIntroThroughAdminView.findViewById(R.id.mobile);
        profilePicture = (CircleImageView) WelComeIntroThroughAdminView.findViewById(R.id.profilePic);
        updateProgress = (ProgressBar) WelComeIntroThroughAdminView.findViewById(R.id.updateProgress);
        ImageButton iv_camera = (ImageButton) WelComeIntroThroughAdminView.findViewById(R.id.iv_camera);

        fnameEditText.setTypeface(Z.getBold(this));
        lnameEditText.setTypeface(Z.getBold(this));
        mobileEditText.setTypeface(Z.getBold(this));

        TextView getProfilePictureMsg = (TextView) WelComeIntroThroughAdminView.findViewById(R.id.getProfilePictureMsg);
        adminInfo = (TextView) WelComeIntroThroughAdminView.findViewById(R.id.adminInfo);
        TextView welcometextviewcontext3 = (TextView) WelComeIntroThroughAdminView.findViewById(R.id.welcometextviewcontext3);

        getProfilePictureMsg.setTypeface(Z.getBold(this));
        adminInfo.setTypeface(Z.getBoldItalic(this));

        fnameTextInputLayout = (TextInputLayout) WelComeIntroThroughAdminView.findViewById(R.id.fnameTextInputLayout);
        lnameTextInputLayout = (TextInputLayout) WelComeIntroThroughAdminView.findViewById(R.id.lnameTextInputLayout);
        mobileTextInputLayout = (TextInputLayout) WelComeIntroThroughAdminView.findViewById(R.id.mobileTextInputLayout);

        Z.slideinleft1(this, getProfilePictureMsg);
        Z.slideinleft2(this, adminInfo);
        Z.fade(this, welcometextviewcontext3);
        Z.fade(this, profilePicture);
        Z.fade(this, iv_camera);
        Z.fadeandmovedown(this, fnameTextInputLayout);
        Z.fadeandmovedown(this, lnameTextInputLayout);
        Z.fadeandmovedown(this, mobileTextInputLayout);

        fnameTextInputLayout.setTypeface(Z.getLight(this));
        lnameTextInputLayout.setTypeface(Z.getLight(this));
        mobileTextInputLayout.setTypeface(Z.getLight(this));

        iv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestCropImage();
            }
        });
        fnameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                fnameTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        lnameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                lnameTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mobileEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mobileTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void setWelComeRoleView(View v) {

        WelcomeRoleView = v;

        AnimationModal animationModal = new AnimationModal();
        animationModal.setRoleView(WelcomeRoleView);

        rolewelcometextviewcontext1 = (TextView) WelcomeRoleView.findViewById(R.id.welcometextviewcontext1);
        rolewelcometextviewcontext2 = (TextView) WelcomeRoleView.findViewById(R.id.welcometextviewcontext2);
        rolewelcometextviewcontext3 = (TextView) WelcomeRoleView.findViewById(R.id.welcometextviewcontext3);
        TextView studentrole = (TextView) WelcomeRoleView.findViewById(R.id.tv_name_student);
//        TextView alumnirole = (TextView) WelcomeRoleView.findViewById(R.id.tv_name_alumni);
        TextView tporole = (TextView) WelcomeRoleView.findViewById(R.id.tv_name_tpo);
        TextView hrrole = (TextView) WelcomeRoleView.findViewById(R.id.tv_name_hr);

        rolewelcometextviewcontext2.setText("\"There are many roles you can play in life, but you know there is one role you must play: TO BE YOURSELF !\"");

        rolewelcometextviewcontext1.setTypeface(Z.getBold(this));
        rolewelcometextviewcontext2.setTypeface(Z.getBoldItalic(this));
        rolewelcometextviewcontext3.setTypeface(Z.getLight(this));
        studentrole.setTypeface(Z.getBold(this));
//        alumnirole.setTypeface(Z.getBold(this));
        tporole.setTypeface(Z.getBold(this));
        hrrole.setTypeface(Z.getBold(this));


        studentrl = (CardView) WelcomeRoleView.findViewById(R.id.studentrl);
//        alumnirl = (CardView) WelcomeRoleView.findViewById(R.id.alumnirl);
        tporl = (CardView) WelcomeRoleView.findViewById(R.id.tporl);
        hrrl = (CardView) WelcomeRoleView.findViewById(R.id.hrrl);

        final RelativeLayout student_rl = (RelativeLayout) WelcomeRoleView.findViewById(R.id.student_rl);
//        RelativeLayout alumni_rl = (RelativeLayout) WelcomeRoleView.findViewById(R.id.alumni_rl);
        RelativeLayout tpo_rl = (RelativeLayout) WelcomeRoleView.findViewById(R.id.tpo_rl);
        RelativeLayout hr_rl = (RelativeLayout) WelcomeRoleView.findViewById(R.id.hr_rl);

        student_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SELECTED_ROLE = "student";

                WelcomeRoleModal obj = new WelcomeRoleModal();
                obj.setCode(null);

                studentrl.setVisibility(View.GONE);
//                alumnirl.setVisibility(View.VISIBLE);
                tporl.setVisibility(View.VISIBLE);
                hrrl.setVisibility(View.VISIBLE);

//                Z.scaledown(Welcome.this, alumnirl);
                Z.scaledown(Welcome.this, tporl);
                Z.scaledown(Welcome.this, hrrl);
                Z.slideoutleft2(Welcome.this, rolewelcometextviewcontext2);
                Z.fadeout(Welcome.this, rolewelcometextviewcontext3);
                animateStudentRl();

            }
        });

//        alumni_rl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                SELECTED_ROLE = "alumni";
//
//                WelcomeRoleModal obj = new WelcomeRoleModal();
//                obj.setCode(null);
//
//                studentrl.setVisibility(View.VISIBLE);
//                alumnirl.setVisibility(View.GONE);
//                tporl.setVisibility(View.VISIBLE);
//                hrrl.setVisibility(View.VISIBLE);
//
//                Z.scaledown(Welcome.this, studentrl);
//                Z.scaledown(Welcome.this, tporl);
//                Z.scaledown(Welcome.this, hrrl);
//                Z.slideoutleft2(Welcome.this, rolewelcometextviewcontext2);
//                Z.fadeout(Welcome.this, rolewelcometextviewcontext3);
//                animateAlumniRl();
//
//            }
//        });
        tpo_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SELECTED_ROLE = "admin";

                WelcomeRoleModal obj = new WelcomeRoleModal();
                obj.setCode(null);

                studentrl.setVisibility(View.VISIBLE);
//                alumnirl.setVisibility(View.VISIBLE);
                tporl.setVisibility(View.GONE);
                hrrl.setVisibility(View.VISIBLE);

                Z.scaledown(Welcome.this, studentrl);
//                Z.scaledown(Welcome.this, alumnirl);
                Z.scaledown(Welcome.this, hrrl);
                Z.slideoutleft2(Welcome.this, rolewelcometextviewcontext2);
                Z.fadeout(Welcome.this, rolewelcometextviewcontext3);
                animateTPORl();

            }
        });
        hr_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SELECTED_ROLE = "hr";

                WelcomeRoleModal obj = new WelcomeRoleModal();
                obj.setCode(null);

                studentrl.setVisibility(View.VISIBLE);
//                alumnirl.setVisibility(View.VISIBLE);
                tporl.setVisibility(View.VISIBLE);
                hrrl.setVisibility(View.GONE);

                Z.scaledown(Welcome.this, studentrl);
//                Z.scaledown(Welcome.this, alumnirl);
                Z.scaledown(Welcome.this, tporl);
                Z.slideoutleft2(Welcome.this, rolewelcometextviewcontext2);
                Z.fadeout(Welcome.this, rolewelcometextviewcontext3);
                animateHRRl();

            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        fa = Typeface.createFromAsset(this.getAssets(), "fonts/fa.ttf");
        bold = Typeface.createFromAsset(this.getAssets(), "fonts/nunitobold.ttf");
        light = Typeface.createFromAsset(this.getAssets(), "fonts/nunitolight.ttf");

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);

        viewPager = (CustomViewPager) findViewById(R.id.view_pager);
        viewPager.setPagingEnabled(false);

        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnPrev = (Button) findViewById(R.id.btnPrev);
        resultView = (ImageView) findViewById(R.id.result_image);
        nextProgress = (ProgressBar) findViewById(R.id.nextProgress);
        crop_layout = (FrameLayout) findViewById(R.id.crop_layout);
        btnNext.setTypeface(bold);
        btnPrev.setTypeface(bold);

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                lastPosition = position;
            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;

                if (currentPosition == 0)
                    btnPrev.setVisibility(View.GONE);
                else
                    btnPrev.setVisibility(View.VISIBLE);

                if (currentPosition == 2 && path == 2) {
                    animateAllViews();
                }
                if (currentPosition == 3 && path == 2) {
                    Z.fade(Welcome.this, enterpasswordimage);
                    Z.slideinleft1(Welcome.this, createpasswordwelcometextviewcontext1);
                    Z.slideinleft2(Welcome.this, createpasswordwelcometextviewcontext2);
                    Z.fadeandmovedown(Welcome.this, passwordTextInputLayout);
                    Z.fadeandmovedown(Welcome.this, confirmPasswordTextInputLayout);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        myViewPagerAdapter = new MyAdapter(getSupportFragmentManager());
        myViewPagerAdapter.addFrag(new WelcomeEmailFragment());
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.setCurrentItem(0);
        addBottomDots(0, 2);

        android_id = Settings.Secure.getString(getApplication().getContentResolver(), Settings.Secure.ANDROID_ID);

//        try {
//            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//            device_id = telephonyManager.getDeviceId();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //--------------------------------------------------- NEXT BUTTON ---------------------------------------------//

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("TAG", "race :" + path);

                if (currentPosition == 0) {                       //---------------------------------  0
                    plainUsername = usernameedittext.getText().toString().trim();

                    boolean usernameflag = false;
                    if (plainUsername.equals("")) {
                        usernameflag = true;
                        usernameTextInputLayout.setError("Kindly provide your email address");
                    } else if (plainUsername.length() < 5) {
                        usernameflag = true;
                        usernameTextInputLayout.setError("Kindly provide valid email address");
                    } else if (!plainUsername.contains("@")) {
                        usernameflag = true;
                        usernameTextInputLayout.setError("Kindly provide valid email address");
                    }

                    if (usernameflag == false) {
                        nextProgress.setVisibility(View.VISIBLE);
                        btnNext.setVisibility(View.GONE);
                        new ValidateUser().execute();

                    }

                } else if (currentPosition == 1)                             //---------------------------------  1
                {
                    if (path == 1)     // existing user
                    {
                        passwordstr = passwordedittext.getText().toString();
                        if (passwordstr.equals("")) {
                            welcomepasswordTextInputLayout.setError("Kindly provide your password");
                        } else {
                            try {
                                byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                                byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                                String sPadding = "ISO10126Padding";

                                byte[] passwordBytes = passwordstr.getBytes("UTF-8");

                                byte[] usernameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, passwordBytes);
                                encPassword = new String(SimpleBase64Encoder.encode(usernameEncryptedBytes));

                                MySharedPreferencesManager.save(Welcome.this, "passKey", encPassword);

                                nextProgress.setVisibility(View.VISIBLE);
                                btnNext.setVisibility(View.GONE);
                                attemptLogin(encUsersName, encPassword);

                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.d("TAG", "exp:" + e.getMessage());
                            }
                        }
                    } else if (path == 2)        // new user
                    {
                        mobileTextInputLayout.setError(null);
                        fnameTextInputLayout.setError(null);
                        lnameTextInputLayout.setError(null);

                        errorFlagIntro = false;
                        fname = fnameEditText.getText().toString().trim();
                        lname = lnameEditText.getText().toString().trim();
                        mobile = mobileEditText.getText().toString().trim();


                        if (fname.length() < 1) {
                            errorFlagIntro = true;
                            fnameTextInputLayout.setError("Kindly provide your first name");
                        } else if (lname.length() < 1) {
                            lnameTextInputLayout.setError("Kindly provide your last name");
                            errorFlagIntro = true;
                        } else if (mobile.length() != 10) {
                            mobileTextInputLayout.setError("Kindly provide your correct 10-digit mobile number");
                            errorFlagIntro = true;
                        }

                        if (!errorFlagIntro) {

                            try {


                                encfname = Encrypt(fname, digest1, digest2);
                                enclname = Encrypt(lname, digest1, digest2);
                                encmobile = Encrypt(mobile, digest1, digest2);

                                viewPager.setCurrentItem(2);
                                addBottomDots(2, 4);

                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.d("TAG", "exp:" + e.getMessage());
                            }
                        }
                    }
                } else if (currentPosition == 2) {
                    if (path == 2) {
                        Toast.makeText(Welcome.this, "Please select your role !", Toast.LENGTH_SHORT).show();
                    }

                    if (path == 3) {

                        errorFlagThroughAdminIntro = false;
                        fname = fnameEditText.getText().toString().trim();
                        lname = lnameEditText.getText().toString().trim();
                        mobile = mobileEditText.getText().toString().trim();

                        if (fname.length() < 2) {
                            errorFlagThroughAdminIntro = true;
                            fnameTextInputLayout.setError("Kindly provide your first name");
                        } else if (lname.length() < 2) {
                            lnameTextInputLayout.setError("Kindly provide your last name");
                            errorFlagThroughAdminIntro = true;
                        } else if (mobile.length() != 10) {
                            mobileTextInputLayout.setError("Kindly provide your correct mobile number");
                            errorFlagThroughAdminIntro = true;
                        }


                        if (errorFlagThroughAdminIntro == false) {
                            new SaveDataUserCreatedThroughAdmin().execute();
                        }
                    }
                } else if (currentPosition == 3)                                     //---------------------------------  3
                {
                    if (path == 2) {
                        passwordTextInputLayout.setError(null);
                        confirmPasswordTextInputLayout.setError(null);

                        boolean errorflag = false;
                        String enterpass = enterPassword.getText().toString();
                        confrimpass = confirmPassword.getText().toString();

                        if (enterpass.length() < 6) {
                            errorflag = true;
                            passwordTextInputLayout.setError("Password must be at least 6 characters long");
                        } else if (!enterpass.equals(confrimpass)) {
                            errorflag = true;
                            confirmPasswordTextInputLayout.setError("Passwords did not match");
                        }
                        if (errorflag == false) {

                            try {
                                byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                                byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                                String sPadding = "ISO10126Padding";
                                byte[] passwordBytes = confrimpass.getBytes("UTF-8");
                                byte[] passwordEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, passwordBytes);
                                encPassword = new String(SimpleBase64Encoder.encode(passwordEncryptedBytes));
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.d("TAG", "exp:" + e.getMessage());
                            }
                            if (genrateCodeFlag == true) {
                                //save pref  password instcode
                                //call a servlet that will send activation code
                                //call otp activty
                                //verify otp (activation code)
                                //call activity like welcome

                                MySharedPreferencesManager.save(Welcome.this, "passKey", encPassword);
                                MySharedPreferencesManager.save(Welcome.this, "role", SELECTED_ROLE);
                                MySharedPreferencesManager.save(Welcome.this, "nameKey", encUsersName);

                                MySharedPreferencesManager.save(Welcome.this, "fname", encfname);
                                MySharedPreferencesManager.save(Welcome.this, "lname", enclname);
                                MySharedPreferencesManager.save(Welcome.this, "phone", encmobile);


                                new SendActivationCode().execute();
                            } else {
                                nextProgress.setVisibility(View.INVISIBLE);
                                new SaveData().execute();
                            }
                        }
                    }
                }


            }

        });

        resultView = (ImageView) findViewById(R.id.result_image);
        imagePicker = new ImagePicker(this);
        imagePicker.setImagePickerCallback(Welcome.this);
        imagePicker.shouldGenerateMetadata(false); // Default is true
        imagePicker.shouldGenerateThumbnails(false); // Default is true


    }//onc


    class SaveData extends AsyncTask<String, String, Boolean> {

        String result;

        protected Boolean doInBackground(String... param) {
            try {
                byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                String sPadding = "ISO10126Padding";

                byte[] fnameBytes = fname.getBytes("UTF-8");
                byte[] lnameBytes = lname.getBytes("UTF-8");
                byte[] mobileBytes = mobile.getBytes("UTF-8");
                byte[] passwordBytes = confrimpass.getBytes("UTF-8");
                byte[] instOrEmailstrBytes = instOrEmailstr.getBytes("UTF-8");
                byte[] usernameBytes = plainUsername.getBytes("UTF-8");
                byte[] roleBytes = SELECTED_ROLE.getBytes("UTF-8");
                byte[] usernameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, usernameBytes);
                encUsersName = new String(SimpleBase64Encoder.encode(usernameEncryptedBytes));
                byte[] passwordEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, passwordBytes);
                encPassword = new String(SimpleBase64Encoder.encode(passwordEncryptedBytes));
                byte[] fnameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, fnameBytes);
                encfname = new String(SimpleBase64Encoder.encode(fnameEncryptedBytes));
                byte[] lnameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, lnameBytes);
                enclname = new String(SimpleBase64Encoder.encode(lnameEncryptedBytes));
                byte[] mobileEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, mobileBytes);
                encmobile = new String(SimpleBase64Encoder.encode(mobileEncryptedBytes));
                byte[] instOrEmailstrEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, instOrEmailstrBytes);
                encinstOrEmail = new String(SimpleBase64Encoder.encode(instOrEmailstrEncryptedBytes));

                byte[] roleEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, roleBytes);
                encrole = new String(SimpleBase64Encoder.encode(roleEncryptedBytes));


            } catch (Exception e) {
                e.printStackTrace();
                Log.d("TAG", "exp:" + e.getMessage());
            }

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encUsersName));       // 0
            params.add(new BasicNameValuePair("r", encrole));            // 1
            params.add(new BasicNameValuePair("f", encfname));           // 2
            params.add(new BasicNameValuePair("l", enclname));           // 3
            params.add(new BasicNameValuePair("m", encmobile));          // 4
            params.add(new BasicNameValuePair("ie", encinstOrEmail));    // 5
            params.add(new BasicNameValuePair("p", encPassword));        // 6
            params.add(new BasicNameValuePair("id", android_id));        // 7

            JSONObject json = jParser.makeHttpRequest(Z.url_SaveWelcomeIntroData, "GET", params);
            if (json != null) {
                try {
                    result = json.getString("info");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else
                return false;

            return true;
        }

        @Override
        protected void onPostExecute(Boolean notNull) {

            if (notNull) {

                if (result.equals("success")) {

                    MySharedPreferencesManager.save(Welcome.this, "role", SELECTED_ROLE);       //0
                    MySharedPreferencesManager.save(Welcome.this, "nameKey", encUsersName);     //1
                    MySharedPreferencesManager.save(Welcome.this, "passKey", encPassword);      //2
                    MySharedPreferencesManager.save(Welcome.this, "fname", encfname);           //3
                    MySharedPreferencesManager.save(Welcome.this, "lname", enclname);            //4
                    nextProgress.setVisibility(View.GONE);
                    Intent loginintent = new Intent(Welcome.this, LoginActivity.class);
                    loginintent.putExtra("showOTP", "yes");
                    loginintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(loginintent);
                }
            } else
                Toast.makeText(Welcome.this, Z.FAIL_TO_PROCESS, Toast.LENGTH_SHORT).show();
        }
    }

    class checkUcode extends AsyncTask<String, String, Boolean> {
        String result;

        protected Boolean doInBackground(String... param) {

            String inputUcode = param[0];
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", inputUcode));       //0
            JSONObject json = jParser.makeHttpRequest(Z.url_checkUcode, "GET", params);
            if (json != null) {
                try {
                    result = json.getString("info");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else
                return false;

            return true;
        }

        @Override
        protected void onPostExecute(Boolean notNull) {

            if (notNull) {
                if (result != null && result.equals("found")) {
                    viewPager.setCurrentItem(3);
                    addBottomDots(3, 4);
                } else {
                    instOrEmail.setError("Invalid Institute Code. Please contact your Training and placement officer");
                }

            } else
                Toast.makeText(Welcome.this, Z.FAIL_TO_PROCESS, Toast.LENGTH_SHORT).show();
        }
    }

    class SaveDataUserCreatedThroughAdmin extends AsyncTask<String, String, Boolean> {

        String result = null, ROLE;

        protected Boolean doInBackground(String... param) {

            try {
                encfname = Z.Encrypt(fname, Welcome.this);
                enclname = Z.Encrypt(lname, Welcome.this);
                encmobile = Z.Encrypt(mobile, Welcome.this);
            } catch (Exception e) {
                e.printStackTrace();
            }


            Log.d("TAG", "doInBackground: encUsersName " + encUsersName);
            Log.d("TAG", "doInBackground: encfname " + encfname);
            Log.d("TAG", "doInBackground: enclname " + enclname);
            Log.d("TAG", "doInBackground: encmobile " + encmobile);

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encUsersName));       //0
            params.add(new BasicNameValuePair("f", encfname));           //1
            params.add(new BasicNameValuePair("l", enclname));           //2
            params.add(new BasicNameValuePair("m", encmobile));          //3
            JSONObject json = jParser.makeHttpRequest(Z.url_SaveStudentFnameLnameMobile, "GET", params);
            if (json != null) {
                try {
                    result = json.getString("info");
                    if (result.equals("success")) {
                        ROLE = json.getString("role");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else
                return false;

            return true;
        }

        @Override
        protected void onPostExecute(Boolean notNull) {

            if (notNull) {
                if (result.equals("success")) {

                    MySharedPreferencesManager.save(Welcome.this, "nameKey", encUsersName);

                    CreateFirebaseUser(encUsersName, encPassword);
                    try {
                        loginFirebase(Z.Decrypt(encUsersName, Welcome.this), Z.md5(Z.Decrypt(encPassword, Welcome.this) + MySharedPreferencesManager.getDigest3(Welcome.this)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (ROLE != null && ROLE.equals("student")) {
                        startActivity(new Intent(Welcome.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                        finish();
                    } else if (ROLE != null && ROLE.equals("admin")) {
                        startActivity(new Intent(Welcome.this, AdminActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                        finish();
                    } else
                        Toast.makeText(Welcome.this, "role is not A/S", Toast.LENGTH_SHORT).show();


                }
            } else
                Toast.makeText(Welcome.this, Z.FAIL_TO_PROCESS, Toast.LENGTH_SHORT).show();
        }
    }


    void CreateFirebaseUser(final String u, final String p) {
        String u1 = null, p1 = null;
        try {
            u1 = Z.Decrypt(u, Welcome.this);
            p1 = Z.Decrypt(p, Welcome.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final String u2 = u1;
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(u1, Z.md5(p1 + MySharedPreferencesManager.getDigest3(Welcome.this)))
                .addOnCompleteListener(Welcome.this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            String uid = user.getUid();
                            MySharedPreferencesManager.save(Welcome.this, "uid", uid);
                            Log.d("TAG", "firebase user created in otp activity with email: " + u2 + "\nuid: " + uid);


                        } else {
                            Log.d("TAG", "firebase user creation failed in otp activity:");

                        }
                    }
                });


    }

    void loginFirebase(String username, String hash) {

        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(username, hash)
                .addOnCompleteListener(Welcome.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()) {
                            Toast.makeText(Welcome.this, "Successfully logged in to Firebase from otp activity", Toast.LENGTH_SHORT).show();


                        } else {
                            Toast.makeText(Welcome.this, "Failed to login to Firebase from otp activity", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


//    class CreateFirebaseUser extends AsyncTask<String, String, String> {
//
//        String u, p;
//
//        CreateFirebaseUser(String u, String p) {
//            this.u = u;
//            this.p = p;
//        }
//
//        protected String doInBackground(String... param) {
//
//
//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//            params.add(new BasicNameValuePair("u", u));
//            params.add(new BasicNameValuePair("p", p));
//            params.add(new BasicNameValuePair("t", new SharedPrefUtil(getApplicationContext()).getString("firebaseToken"))); //5
//
//            json = jParser.makeHttpRequest(Z.url_create_firebase, "GET", params);
//            try {
//                resultofop = json.getString("info");
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return resultofop;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//
//            String salt = MySharedPreferencesManager.getDigest3(Welcome.this);
//
//            String hash = md5(passwordstr + salt);
//            loginFirebase(plainUsername, hash);
//
//        }
//
//        void loginFirebase(String username, String hash) {
//            FirebaseAuth.getInstance()
//                    .signInWithEmailAndPassword(username, hash)
//                    .addOnCompleteListener(Welcome.this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//
//
//                            if (task.isSuccessful()) {
////                                Toast.makeText(Welcome.this, "Successfully logged in to Firebase", Toast.LENGTH_SHORT).show();
//                                Log.d("TAG", "bhajala");
//
//                            } else {
////                                Toast.makeText(Welcome.this, "Failed to login to Firebase", Toast.LENGTH_SHORT).show();
//                                Log.d("TAG", "nay bhajala");
//                            }
//                        }
//                    });
//        }
//    }


    private void addBottomDots(int currentPage, int totalPages) {
        dots = new TextView[totalPages];

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(5, 5, 5, 5);


        int colorsActive = getResources().getColor(R.color.sky_blue_color);
        int colorsInactive = getResources().getColor(R.color.dark_color);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setTypeface(fa);
            dots[i].setLayoutParams(params);
            dots[i].setText(getString(R.string.dot_unselected));
            dots[i].setTextSize(8);
            dots[i].setTextColor(colorsInactive);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[currentPage].setTextColor(colorsActive);
            dots[currentPage].setText(getString(R.string.dot_selected));
            dots[currentPage].setTextSize(10);
        }
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    //	viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {

            if (path == 1) {
                addBottomDots(position, 2);

                if (position == 2 - 1) {
                    // last page. make button text to GOT IT
                    btnNext.setText("LOGIN");

                } else {
                    // still pages are left
                    btnNext.setText("NEXT");

                }
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * Making notification bar transparent
     */


    public void onError(String s) {
        crop_layout.setVisibility(View.GONE);
        // tswap       tswipe_refresh_layout.setVisibility(View.GONE);
        Toast.makeText(Welcome.this, "Try again !", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onImagesChosen(List<ChosenImage> list) {
        final ChosenImage file = list.get(0);

        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (file != null) {

                    finalPath = file.getOriginalPath().toString();
                }
            }
        });
    }


    public class MyAdapter extends FragmentStatePagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();

        MyAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void clear() {
            mFragmentList.clear();
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = mFragmentList.get(position);
            return fragment;
        }

        public void addFrag(Fragment fragment) {
            mFragmentList.add(fragment);
        }

        void deletePage(int position) {
            if (canDelete()) {

                mFragmentList.remove(position);
                notifyDataSetChanged();
            }
        }

        boolean canDelete() {
            return mFragmentList.size() > 0;
        }

        // This is called when notifyDataSetChanged() is called
        @Override
        public int getItemPosition(Object object) {
            // refresh all fragments when data set changed
            return PagerAdapter.POSITION_NONE;
        }
    }


    class SendActivationCode extends AsyncTask<String, String, Boolean> {

        String result = null;
        String encRole;

        protected Boolean doInBackground(String... param) {
            try {

                byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                String sPadding = "ISO10126Padding";


                byte[] proEmailBytes = instOrEmailstr.getBytes("UTF-8");
                byte[] proEmailEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, proEmailBytes);
                encProMail = new String(SimpleBase64Encoder.encode(proEmailEncryptedBytes));

                Log.d("TAG", "doInBackground: SELECTED_ROLE SELECTED_ROLE " + SELECTED_ROLE);
                encRole = Z.Encrypt(SELECTED_ROLE, Welcome.this);


            } catch (Exception e) {
                e.printStackTrace();
                Log.d("TAG", "exp 143:" + e.getMessage());
            }

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encProMail));              //0
            params.add(new BasicNameValuePair("f", encfname));                  //1
            params.add(new BasicNameValuePair("l", enclname));                //2
            params.add(new BasicNameValuePair("r", encRole));               //3
            JSONObject json = jParser.makeHttpRequest(Z.url_SendActivationCode, "GET", params);
            Log.d("kunal", "doInBackground: SendActivationCode " + json);
            if (json != null) {
                try {
                    result = json.getString("info");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else
                return false;

            return true;
        }

        @Override
        protected void onPostExecute(Boolean notNull) {

            if (notNull) {
                if (result.equals("success")) {
                    MySharedPreferencesManager.save(Welcome.this, "activationMessage", "yes");
                    MySharedPreferencesManager.save(Welcome.this, "proEmail", encProMail);
                    startActivity(new Intent(Welcome.this, OTPActivity.class));

                } else if (result.equals("exist")) {
                    Toast.makeText(Welcome.this, "Account already exists on PlaceMe", Toast.LENGTH_SHORT).show();
                } else {
                    if (instOrEmail != null) {
                        instOrEmail.setError("Incorrect Professional Email");
                    } else
                        Toast.makeText(Welcome.this, "Incorrect Professional Email", Toast.LENGTH_SHORT).show();

                    String enterpass = enterPassword.getText().toString();
                    confrimpass = confirmPassword.getText().toString();

                    if (enterPassword != null && confirmPassword != null) {
                        enterPassword.setText("");
                        confirmPassword.setText("");
                    }
                    onBackPressed();
                }

            } else
                Toast.makeText(Welcome.this, Z.FAIL_TO_PROCESS, Toast.LENGTH_SHORT).show();
        }
    }

    class ValidateUser extends AsyncTask<String, String, Boolean> {

        String result = null;

        protected Boolean doInBackground(String... param) {
            try {

                byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                String sPadding = "ISO10126Padding";

                byte[] usernameBytes = plainUsername.getBytes("UTF-8");
                byte[] fnameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, usernameBytes);
                encUsersName = new String(SimpleBase64Encoder.encode(fnameEncryptedBytes));
                MySharedPreferencesManager.save(Welcome.this, "nameKey", encUsersName);

            } catch (Exception e) {
                e.printStackTrace();
                Log.d("TAG", "exp wife:" + e.getMessage());
            }

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encUsersName));       //0

            JSONObject json = jParser.makeHttpRequest(Z.url_Welcome, "GET", params);
            Log.d("TAG", "welcome: json " + json);
            if (json != null) {
                try {
                    result = json.getString("info");
                    if (result != null)
                        if (result.equals("found"))
                            foundFname = json.getString("fname");

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("TAG", "exp wifebaby:" + e.getMessage());
                }
            } else
                return false;

            return true;
        }

        @Override
        protected void onPostExecute(Boolean notNull) {
            nextProgress.setVisibility(View.GONE);
            btnNext.setVisibility(View.VISIBLE);

            if (notNull) {

                if (result.equals("found")) {

                    path = 1;
//                setupViewPagerExistingActivatedUser(viewPager);

                    if (myViewPagerAdapter.getCount() > 1) {


                        myViewPagerAdapter.clear();
                        myViewPagerAdapter.addFrag(new WelcomeEmailFragment());
                        myViewPagerAdapter.addFrag(new WelcomePasswordFragment());
                        myViewPagerAdapter.notifyDataSetChanged();
                        viewPager.setCurrentItem(1);
                        addBottomDots(1, 2);

                    } else {

                        myViewPagerAdapter.addFrag(new WelcomePasswordFragment());
                        myViewPagerAdapter.notifyDataSetChanged();
                        viewPager.setCurrentItem(1);
                        addBottomDots(1, 2);
                    }

                } else if (result.equals("notfound")) {

                    path = 2;
                    if (myViewPagerAdapter.getCount() > 1) {
                        //clear the adapter first and then add all the fragments
                        myViewPagerAdapter.clear();
                        myViewPagerAdapter.addFrag(new WelcomeEmailFragment());
                        myViewPagerAdapter.addFrag(new WelcomeIntroFragment());
                        myViewPagerAdapter.addFrag(new WelcomeRoleFragment());
                        myViewPagerAdapter.addFrag(new WelcomeCreatePasswordFragment());
                        myViewPagerAdapter.notifyDataSetChanged();
                        viewPager.setCurrentItem(1);
                        addBottomDots(1, 4);


                    } else {
                        myViewPagerAdapter.addFrag(new WelcomeIntroFragment());
                        myViewPagerAdapter.addFrag(new WelcomeRoleFragment());
                        myViewPagerAdapter.addFrag(new WelcomeCreatePasswordFragment());
                        myViewPagerAdapter.notifyDataSetChanged();
                        viewPager.setCurrentItem(1);
                        addBottomDots(1, 4);
                    }

                }

            } else
                Toast.makeText(Welcome.this, Z.FAIL_TO_PROCESS, Toast.LENGTH_SHORT).show();

        }
    }

    void attemptLogin(String u, String p) {

        UserLoginTask userLoginTask = new UserLoginTask(u, p);
        userLoginTask.execute();


    }

    public class UserLoginTask extends AsyncTask<Void, Void, Integer> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Integer doInBackground(Void... args) {

            int returnCode = 0;
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("u", mEmail));
                params.add(new BasicNameValuePair("p", mPassword));
                json = jParser.makeHttpRequest(Z.url_login, "GET", params);
                String s = null;

                s = json.getString("info");
                resultofop = s;

                if (!s.equals("notactivated"))
                    MySharedPreferencesManager.save(Welcome.this, "role", s);
                else {
                    MySharedPreferencesManager.save(Welcome.this, "role", json.getString("role"));
                }

                if (s.equals("student")) {

                    EmailCred = mEmail;
                    returnCode = 1;
                    return 1;
                } else if (s.equals("admin")) {

                    EmailCred = mEmail;
                    returnCode = 3;
                    return 3;
                } else if (s.equals("hr")) {

                    EmailCred = mEmail;
                    returnCode = 4;
                    return 4;
                }
//                else if (s.equals("alumni")) {
//
//                    EmailCred = mEmail;
//                    returnCode = 5;
//                    return 5;
//                }
                if (s.equals("notactivated")) {             // throughAdmin
                    Log.d("TAG", "dead");

                    String throughAdmin = json.getString("throughAdmin");
                    String subAdmin = json.getString("subadmin");
                    Log.d("TAG, ", "daddy");


                    if (throughAdmin != null && throughAdmin.equals("yes")) {
                        throughAdminFlag = true;

                        adminInstitute = json.getString("adminInst");
                        adminfname = json.getString("adminfname");
                        adminlname = json.getString("adminlname");
                        try {
                            byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                            byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                            String sPadding = "ISO10126Padding";

                            byte[] adminInstituteEncryptedBytes = SimpleBase64Encoder.decode(adminInstitute);
                            byte[] adminInstituteDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, adminInstituteEncryptedBytes);
                            adminInstitute = new String(adminInstituteDecryptedBytes);

                            byte[] fnameEncryptedBytes = SimpleBase64Encoder.decode(adminfname);
                            byte[] fnameDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, fnameEncryptedBytes);
                            adminfname = new String(fnameDecryptedBytes);

                            byte[] lnameEncryptedBytes = SimpleBase64Encoder.decode(adminlname);
                            byte[] lnameDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, lnameEncryptedBytes);
                            adminlname = new String(lnameDecryptedBytes);
                            returnCode = 7;
                            return 7;
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.d("TAG", "exp ex:" + e.getMessage());
                        }


                    } else if (subAdmin != null && subAdmin.equals("yes")) {

                        adminInstitute = json.getString("adminInst");
                        adminfname = json.getString("adminfname");
                        adminlname = json.getString("adminlname");
                        try {

                            adminInstitute = Z.Decrypt(adminInstitute, Welcome.this);
                            adminfname = Z.Decrypt(adminfname, Welcome.this);
                            adminlname = Z.Decrypt(adminlname, Welcome.this);

                            Log.d("TAG", "doInBackground: " + "" + adminInstitute + "\n" + adminfname + "\n" + adminlname);

                            return 7;
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.d("TAG", "exp ex:" + e.getMessage());
                        }

                    } else {
                        String role = json.getString("role");
                        if (!s.equals("notactivated"))
                            MySharedPreferencesManager.save(Welcome.this, "role", s);
                        else {
                            MySharedPreferencesManager.save(Welcome.this, "role", json.getString("role"));
                        }
                        EmailCred = mEmail;
                        returnCode = 6;
                        return 6;
                    }


                }


            } catch (Exception e) {
                Log.d("exp ex:", e.getMessage());
                e.printStackTrace();
            }
            return returnCode;
        }

        @Override
        protected void onPostExecute(final Integer success) {

            processOutput(success);

        }

        void processOutput(final int success) {
            if (success == 7) { // through admin
                path = 3;

                String MSG = "Your account has already been created by " + adminfname + " " + adminlname + " from " + adminInstitute;

                if (myViewPagerAdapter.getCount() > 2) {
                    //clear the adapter first and then add all the fragments
                    myViewPagerAdapter.clear();
                    myViewPagerAdapter.addFrag(new WelcomeEmailFragment());
                    myViewPagerAdapter.addFrag(new WelcomePasswordFragment());
                    myViewPagerAdapter.addFrag(new WelcomeIntroThroughAdminFragment());
                    myViewPagerAdapter.notifyDataSetChanged();
                    viewPager.setCurrentItem(2);
                    addBottomDots(2, 3);

                } else {
                    myViewPagerAdapter.addFrag(new WelcomeIntroThroughAdminFragment());
                    myViewPagerAdapter.notifyDataSetChanged();
                    viewPager.setCurrentItem(2);
                    addBottomDots(2, 3);
                }
                adminInfo.setText(MSG);


            } else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (success == 1) {
                            new SaveSessionDetails().execute();
//                            ProfileRole r = new ProfileRole();
//                            r.setRole("student");
//                            r.setUsername(EmailCred);
                            MySharedPreferencesManager.save(Welcome.this, "nameKey", EmailCred);
                            MySharedPreferencesManager.save(Welcome.this, "role", "student");
                            Log.d("TAG", "loving 56");
                            startActivity(new Intent(Welcome.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                            finish();
                        } else if (success == 3) {
                            new SaveSessionDetails().execute();
                            Log.d("TAG", "loving 287");
                            MySharedPreferencesManager.save(Welcome.this, "role", "admin");
                            MySharedPreferencesManager.save(Welcome.this, "nameKey", EmailCred);
                            startActivity(new Intent(Welcome.this, AdminActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                            finish();
                        } else if (success == 4) {
                            new SaveSessionDetails().execute();
                            Log.d("TAG", "loving 311");
                            MySharedPreferencesManager.save(Welcome.this, "role", "hr");
                            MySharedPreferencesManager.save(Welcome.this, "nameKey", EmailCred);
                            startActivity(new Intent(Welcome.this, HRActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                            finish();
                        }
//                        else if (success == 5) {
//                            new SaveSessionDetails().execute();
//                            MySharedPreferencesManager.save(Welcome.this, "role", "alumni");
//                            MySharedPreferencesManager.save(Welcome.this, "nameKey", EmailCred);
//
//                            startActivity(new Intent(Welcome.this, AlumniActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
//                            finish();
//                        }
                    }
                }).start();
            }

            if (success == 6) {
                Toast.makeText(Welcome.this, "You are already registered but not verified.Enter OTP sent on email address", Toast.LENGTH_LONG).show();
                Intent loginintent = new Intent(Welcome.this, LoginActivity.class);
                loginintent.putExtra("showOTP", "yes");
                startActivity(loginintent);
                finish();
            } else if (resultofop.equals("notpresent")) {
                Toast.makeText(Welcome.this, "Incorrect email address. If you are a new user, please Sign Up.", Toast.LENGTH_LONG).show();

            } else if (resultofop.equals("fail")) {
                Toast.makeText(Welcome.this, "Incorrect Password !", Toast.LENGTH_LONG).show();
                WelcomePasswordFragment fragment = (WelcomePasswordFragment) myViewPagerAdapter.getItem(1);
                passwordedittext.setText("");
            } else {
                passwordedittext.setText("");

            }

            nextProgress.setVisibility(View.GONE);
            btnNext.setVisibility(View.VISIBLE);
        }
    }


    class SaveSessionDetails extends AsyncTask<String, String, Boolean> {


        protected Boolean doInBackground(String... param) {


            String platform = "Android (" + getDeviceName() + ")";
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encUsersName));    //0
            params.add(new BasicNameValuePair("m", platform));      //1
            JSONObject json = jParser.makeHttpRequest(Z.url_savesessiondetails, "GET", params);
            if (json != null) {
                try {
                    String r = json.getString("info");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else
                return false;

            return true;
        }

    }


    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;

        StringBuilder phrase = new StringBuilder();
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c));
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase.append(c);
        }

        return phrase.toString();
    }
    //prfile image ***

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {

        if (requestCode == 1111) {
            WelcomeRoleModal obj = new WelcomeRoleModal();
            instOrEmailstr = obj.getCode();
            if (resultCode == 1111 && instOrEmailstr != null) {
                viewPager.setCurrentItem(3);
                addBottomDots(3, 4);
            }
        } else if (requestCode == 2222) {
            WelcomeRoleModal obj = new WelcomeRoleModal();
            instOrEmailstr = obj.getCode();
            if (resultCode == 2222 && instOrEmailstr != null) {
                viewPager.setCurrentItem(3);
                addBottomDots(3, 4);
            }
        } else if (requestCode == 3333) {
            WelcomeRoleModal obj = new WelcomeRoleModal();
            instOrEmailstr = obj.getCode();
            if (resultCode == 3333 && instOrEmailstr != null) {
                genrateCodeFlag = true;
                viewPager.setCurrentItem(3);
                addBottomDots(3, 4);
            }
        } else if (requestCode == 4444) {
            WelcomeRoleModal obj = new WelcomeRoleModal();
            instOrEmailstr = obj.getCode();
            if (resultCode == 4444 && instOrEmailstr != null) {
                genrateCodeFlag = true;
                viewPager.setCurrentItem(3);
                addBottomDots(3, 4);
            }
        } else if (requestCode == Picker.PICK_IMAGE_DEVICE) {

            try {

                if (imagePicker == null) {
                    imagePicker = new ImagePicker(this);
                    imagePicker.setImagePickerCallback((ImagePickerCallback) this);
                }
                imagePicker.submit(result);
                crop_layout.setVisibility(View.VISIBLE);

                crop_flag = 1;
                beginCrop(result.getData());

            } catch (Exception e) {
                e.printStackTrace();
                crop_layout.setVisibility(View.GONE);
            }
        } else if (resultCode == RESULT_CANCELED) {
            crop_layout.setVisibility(View.GONE);


            crop_flag = 0;
        } else if (requestCode == Crop.REQUEST_CROP) {
            // Toast.makeText(this, "cropped", Toast.LENGTH_SHORT).show();
            handleCrop(resultCode, result);
        }

    }

    public void requestCropImage() {
        resultView.setImageDrawable(null);

        MySharedPreferencesManager.save(Welcome.this, "crop", "yes");

        chooseImage();
    }

    private void chooseImage() {

        imagePicker.pickImage();
    }

    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(this);
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {
            File f = new File(getCacheDir(), "cropped");
            filepath = f.getAbsolutePath();

            filename = "";
            int index = filepath.lastIndexOf("/");
            directory = "";
            for (int i = 0; i < index; i++)
                directory += filepath.charAt(i);

            for (int i = index + 1; i < filepath.length(); i++)
                filename += filepath.charAt(i);

            crop_layout.setVisibility(View.GONE);
//            new UploadProfile().execute();
            new CompressTask().execute();

        } else if (resultCode == Crop.RESULT_ERROR) {
            crop_layout.setVisibility(View.GONE);

            Toast.makeText(this, "Try Again..!", Toast.LENGTH_SHORT).show();

        }
    }

    class CompressTask extends AsyncTask<String, String, Boolean> {
        protected Boolean doInBackground(String... param) {
            File sourceFile = new File(filepath);

            Luban.compress(Welcome.this, sourceFile)
                    .setMaxSize(256)                // limit the final image size（unit：Kb）
                    .putGear(Luban.CUSTOM_GEAR)
                    .launch(new OnCompressListener() {
                        @Override
                        public void onStart() {
                        }

                        @Override
                        public void onSuccess(File file) {

                            if (file.exists()) {
                                String filepath = file.getAbsolutePath();
                                String filename = "";
                                int index = filepath.lastIndexOf("/");
                                directory = "";
                                for (int i = 0; i < index; i++)
                                    directory += filepath.charAt(i);
                                for (int i = index + 1; i < filepath.length(); i++)
                                    filename += filepath.charAt(i);
                                Imgfile = file;

                                new UploadProfile().execute();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d("TAG", "karina exp:" + e.getMessage());
                        }
                    });
            return true;
        }
    }

    class UploadProfile extends AsyncTask<String, String, Boolean> {

        @Override
        protected void onPreExecute() {
            updateProgress.setVisibility(View.VISIBLE);
        }

        protected Boolean doInBackground(String... param) {

            if (Imgfile != null) {

                MultipartUtility multipart = null;
                try {
                    multipart = new MultipartUtility(Z.upload_profile, "UTF-8");
                    multipart.addFormField("u", encUsersName);
                    if (filename != "") {
                        multipart.addFormField("f", filename);
                        multipart.addFilePart("uf", Imgfile);
                    } else
                        multipart.addFormField("f", "null");
                    response = multipart.finish();

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("TAG", "fb exp:" + e.getMessage());

                }

            } else
                return false;

            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {

            crop_layout.setVisibility(View.GONE);
            updateProgress.setVisibility(View.GONE);
            if (result) {

                if (response != null && response.get(0).contains("success")) {
                    MySharedPreferencesManager.save(Welcome.this, "crop", "no");
                    requestProfileImage();
                    refreshContent();
                    Toast.makeText(Welcome.this, "Photo uploaded successfully !", Toast.LENGTH_SHORT).show();
                    DeleteRecursive(new File(directory));
                } else if (response != null && response.get(0).contains("null")) {
                    requestProfileImage();
                    Toast.makeText(Welcome.this, "Upload failed, please try again !", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(Welcome.this, Z.FAIL_TO_UPLOAD_IMAGE, Toast.LENGTH_SHORT).show();

            } else
                Toast.makeText(Welcome.this, Z.FAIL_TO_UPLOAD_IMAGE, Toast.LENGTH_SHORT).show();
        }

        void DeleteRecursive(File fileOrDirectory) {

            if (fileOrDirectory.isDirectory())
                for (File child : fileOrDirectory.listFiles())
                    DeleteRecursive(child);

            fileOrDirectory.delete();

        }

    }

    public void refreshContent() {

        downloadImage();
    }

    public void requestProfileImage() {
        downloadImage();
    }

    private void downloadImage() {
        Uri uri = new Uri.Builder()
                .scheme("https")
                .authority(Z.VPS_IP)
                .path("AESTest/GetImage")
                .appendQueryParameter("u", encUsersName)
                .build();

        Glide.with(this)
                .load(uri)
                .signature(new StringSignature(System.currentTimeMillis() + ""))
                .into(profilePicture);


    }

    @Override
    public void onBackPressed() {

        if (path == 2 && currentPosition == 3) {
            enterPassword.setText("");
            confirmPassword.setText("");
            enterPassword.setError(null);
            confirmPassword.setError(null);
            passwordTextInputLayout.setError(null);
            confirmPasswordTextInputLayout.setError(null);
        }

        if (path == 3 && currentPosition == 2)
            path = 1;

        if (currentPosition != 0) {
            currentPosition--;
            viewPager.setCurrentItem(currentPosition);


            switch (currentPosition) {
                case 0: {
                    if (path == 1) {
                        addBottomDots(0, 2);
                    } else if (path == 2) {
                        addBottomDots(0, 4);
                    }
                    break;
                }
                case 1: {
                    if (path == 1) {
                        addBottomDots(1, 2);
                    } else if (path == 2) {
                        addBottomDots(1, 4);
                    }
                    break;
                }
                case 2: {
                    if (path == 2) {
                        addBottomDots(2, 4);
                    }
                    if (path == 3) {
                        addBottomDots(2, 3);
                    }

                    break;
                }
            }

        } else
            super.onBackPressed();
    }


}
