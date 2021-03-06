package placeme.octopusites.com.placeme;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import placeme.octopusites.com.placeme.modal.AdminInstituteModal;

import static placeme.octopusites.com.placeme.AES4all.OtoString;
import static placeme.octopusites.com.placeme.AdminActivity.IsSubadmin;

public class AdminInstituteTabFragment extends Fragment {
    public static final String MyPREFERENCES = "MyPrefs";
    //    Button save;
//    ProgressBar saveprogress;
    public static final String Username = "nameKey";
    EditText iname, iemail, iweb, iphone, ialtphone, uniname, caddrline1, caddrline2, caddrline3, ireg;
    String instname = "", instemail = "", instweb = "", instphone = "", instaltrphone = "", universityname = "", instreg = "", strobj = "", instcaddrline1 = "", instcaddrline2 = "", instcaddrline3 = "";
    TextInputLayout instnameinput, instemailinput, instwebinput, instphoneinput, instphoneainput, instuniversityinput, caddrline1input, caddrline2input, caddrline3input, instreginput;
    String encUsername, enciname, encinstemail, encinstweb, encinstphone, encinstaltrphone, encuniversityname, encCinstreg;
    int errorflag1 = 0, errorflag2 = 0, errorflag3 = 0, errorflag4 = 0, errorflag5 = 0, errorflag6 = 0, errorflag7 = 0;
    String digest1, digest2;
    int edittedFlag = 0;
    TextView loctxt;
    String username = "", resultofop;
    JSONObject json;
    JSONParser jParser = new JSONParser();


    AdminData a = new AdminData();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_edit_profile_admin_institute, container, false);

        digest1 = MySharedPreferencesManager.getDigest1(getActivity());
        digest2 = MySharedPreferencesManager.getDigest2(getActivity());
        username = MySharedPreferencesManager.getUsername(getActivity());
        String role = MySharedPreferencesManager.getRole(getActivity());
        encUsername = username;

        instnameinput = (TextInputLayout) rootView.findViewById(R.id.instnameinput);
        instemailinput = (TextInputLayout) rootView.findViewById(R.id.instemailinput);
        instwebinput = (TextInputLayout) rootView.findViewById(R.id.instwebinput);
        instphoneinput = (TextInputLayout) rootView.findViewById(R.id.instphoneinput);
        instphoneainput = (TextInputLayout) rootView.findViewById(R.id.instphoneainput);
        instuniversityinput = (TextInputLayout) rootView.findViewById(R.id.instuniversityinput);
        instreginput = (TextInputLayout) rootView.findViewById(R.id.instreginput);

        caddrline1input = (TextInputLayout) rootView.findViewById(R.id.caddrline1input);
        caddrline2input = (TextInputLayout) rootView.findViewById(R.id.caddrline2input);
        caddrline3input = (TextInputLayout) rootView.findViewById(R.id.caddrline3input);

        instnameinput.setTypeface(Z.getLight(getActivity()));
        instemailinput.setTypeface(Z.getLight(getActivity()));
        instwebinput.setTypeface(Z.getLight(getActivity()));
        instphoneinput.setTypeface(Z.getLight(getActivity()));
        instphoneainput.setTypeface(Z.getLight(getActivity()));
        instuniversityinput.setTypeface(Z.getLight(getActivity()));
        instreginput.setTypeface(Z.getLight(getActivity()));
        caddrline1input.setTypeface(Z.getLight(getActivity()));
        caddrline2input.setTypeface(Z.getLight(getActivity()));
        caddrline3input.setTypeface(Z.getLight(getActivity()));


        iname = (EditText) rootView.findViewById(R.id.instname);
        iemail = (EditText) rootView.findViewById(R.id.instemail);
        iweb = (EditText) rootView.findViewById(R.id.instweb);
        iphone = (EditText) rootView.findViewById(R.id.instphone);
        ialtphone = (EditText) rootView.findViewById(R.id.instphonea);
        uniname = (EditText) rootView.findViewById(R.id.instuniversity);
        ireg = (EditText) rootView.findViewById(R.id.instreg);
        loctxt = (TextView) rootView.findViewById(R.id.loctxt);

        caddrline1 = (EditText) rootView.findViewById(R.id.caddrline1);
        caddrline2 = (EditText) rootView.findViewById(R.id.caddrline2);
        caddrline3 = (EditText) rootView.findViewById(R.id.caddrline3);

        iname.setTypeface(Z.getBold(getActivity()));
        iemail.setTypeface(Z.getBold(getActivity()));
        iweb.setTypeface(Z.getBold(getActivity()));
        iphone.setTypeface(Z.getBold(getActivity()));
        ialtphone.setTypeface(Z.getBold(getActivity()));
        uniname.setTypeface(Z.getBold(getActivity()));
        ireg.setTypeface(Z.getBold(getActivity()));
        loctxt.setTypeface(Z.getBold(getActivity()));

        caddrline1.setTypeface(Z.getBold(getActivity()));
        caddrline2.setTypeface(Z.getBold(getActivity()));
        caddrline3.setTypeface(Z.getBold(getActivity()));
        loctxt.setTypeface(Z.getBold(getActivity()));

//
        instname = a.getInstitute();
        instemail = a.getInstemail();
        instweb = a.getInstweb();
        instphone = a.getInstphone();
        instaltrphone = a.getInstaltrphone();
        universityname = a.getUnivname();
        instreg = a.getInstregno();

        instcaddrline1 = a.getInstcaddrline1();
        instcaddrline2 = a.getInstcaddrline2();
        instcaddrline3 = a.getInstcaddrline3();

        if (instname != null)
            iname.setText(instname);
        if (instemail != null)
            iemail.setText(instemail);
        if (instweb != null)
            iweb.setText(instweb);
        if (instphone != null)
            iphone.setText(instphone);
        if (instaltrphone != null)
            ialtphone.setText(instaltrphone);
        if (universityname != null)
            uniname.setText(universityname);
        if (instreg != null)
            ireg.setText(instreg);


        if (instcaddrline1 != null)
            caddrline1.setText(instcaddrline1);

        if (instcaddrline2 != null)
            caddrline2.setText(instcaddrline2);

        if (instcaddrline3 != null)
            caddrline3.setText(instcaddrline3);


        iname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                instnameinput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        iemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                instemailinput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        iweb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                instwebinput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        iphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                instphoneinput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ialtphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                instphoneainput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        uniname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                instuniversityinput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ireg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                instreginput.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        caddrline1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                caddrline1input.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        caddrline2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                caddrline2input.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        caddrline3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                caddrline3input.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        if (IsSubadmin) {
            iname.setFocusable(false);
            iemail.setFocusable(false);
            iweb.setFocusable(false);
            iphone.setFocusable(false);
            ialtphone.setFocusable(false);
            uniname.setFocusable(false);
            caddrline1.setFocusable(false);
            caddrline2.setFocusable(false);
            caddrline3.setFocusable(false);
            ireg.setFocusable(false);

        }


        return rootView;
    }

    public boolean validate() {

        errorflag1 = 0;
        errorflag2 = 0;
        errorflag3 = 0;
        errorflag4 = 0;
        errorflag5 = 0;
        errorflag6 = 0;
        errorflag7 = 0;

        instname = iname.getText().toString();
        instemail = iemail.getText().toString();
        instweb = iweb.getText().toString();
        instphone = iphone.getText().toString();
        instaltrphone = ialtphone.getText().toString();
        universityname = uniname.getText().toString();
        instreg = ireg.getText().toString();

        instcaddrline1 = caddrline1.getText().toString();
        instcaddrline2 = caddrline2.getText().toString();
        instcaddrline3 = caddrline3.getText().toString();

        boolean flag = false;
        if (!instemail.contains(".edu")) {
            if (!instemail.contains(".org")) {
                if (!instemail.contains(".ac.in")) {
                    flag = true;
                }
            }
        }



        if (instname.length() < 2) {

            instnameinput.setError("Kindly enter valid institute name");
            errorflag7 = 1;

        } else {
            instnameinput.setError(null);
            if (!instemail.contains("@") || flag == true) {
                instemailinput.setError("Incorrect professional email.(Must contain .edu, .org, .ac.in)");
                errorflag1 = 1;
            } else {
                instemailinput.setError(null);
                if (instweb.length() < 3 || !instweb.contains(".")) {
                    instwebinput.setError("Kindly enter valid website URL");
                    errorflag2 = 1;
                } else {
                    instwebinput.setError(null);
                    if (instphone.length() < 10) {
                        instphoneinput.setError("Kindly enter valid phone number ");
                        errorflag3 = 1;
                    } else {
                        instphoneinput.setError(null);

                        if (universityname.length() < 2) {
                            instuniversityinput.setError("Kindly enter valid university name");
                            errorflag5 = 1;
                        } else {
                            instuniversityinput.setError(null);
                            if (instreg.length() < 2) {
                                instreginput.setError("Kindly enter valid registration number");
                                errorflag6 = 1;
                            } else {
                                instreginput.setError(null);
                                if (instcaddrline1.length() < 2) {
                                    caddrline1input.setError("Kindly enter valid address");
                                    errorflag6 = 1;
                                } else {
                                    if (instcaddrline2.length() < 2) {
                                        caddrline2input.setError("Kindly enter valid address");
                                        errorflag6 = 1;
                                    } else {
                                        if (instcaddrline3.length() < 2) {
                                            caddrline3input.setError("Kindly enter valid address");
                                            errorflag6 = 1;
                                        }
                                    }
                                }
                            }

                        }
                    }

                }
            }
        }
        if (errorflag1 == 0 && errorflag2 == 0 && errorflag3 == 0 && errorflag4 == 0 && errorflag5 == 0 && errorflag6 == 0 && errorflag7 == 0) {
            return true;
        }

        return false;
    }

    public void save() {
        if (errorflag1 == 0 && errorflag2 == 0 && errorflag3 == 0 && errorflag4 == 0 && errorflag5 == 0 && errorflag6 == 0 && errorflag7 == 0) {
            try {

                AdminInstituteModal obj = new AdminInstituteModal(instname, instemail, instweb, instphone, instaltrphone, universityname, instreg, instcaddrline1, instcaddrline2, instcaddrline3);
                digest1 = MySharedPreferencesManager.getDigest1(getActivity());
                digest2 = MySharedPreferencesManager.getDigest2(getActivity());

                strobj = OtoString(obj, digest1, digest2);

                new SaveData().execute();


            } catch (Exception e) {

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

                    // ...other AnimationListener methods go here...
                });
            }
        }

        return animation;
    }

    class SaveData extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            Log.d("TAG", "doInBackground: here");
            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encUsername));    //0
            params.add(new BasicNameValuePair("obj", strobj));    //1


            json = jParser.makeHttpRequest(Z.url_SaveAdminInstituteData, "GET", params);
            try {
                r = json.getString("info");
                Log.d("TAG", "doInBackground: r - " + r);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if (result.equals("success")) {
                if (edittedFlag == 1) {
                    ShouldAnimateProfile.EditProfileAdmin.setResult(AdminActivity.ADMIN_DATA_CHANGE_RESULT_CODE);

                }
                edittedFlag = 0;

            } else
                Toast.makeText(getActivity(), "Try again !", Toast.LENGTH_SHORT).show();


        }
    }
}