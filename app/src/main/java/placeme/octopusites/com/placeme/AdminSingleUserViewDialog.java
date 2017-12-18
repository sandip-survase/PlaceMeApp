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
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;

public class AdminSingleUserViewDialog extends AppCompatActivity {

    int flag = 0;
    CheckBox checkboxplaced, checkboxnotplaced, CheckBoxdebar, CheckBoxsnotdebar, checkboxstudent, checkboxalumni;
    TextInputEditText email, companyname;
    TextInputLayout emailinput, companynameinput;
    String PLACED = "", DEBAR = "", ROLE = "";
    String username, strcompanyname, isactivated, encRole;
    String encUsername, encCompanyname, encPlaced, encdebar, encadminUsername;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    private byte[] demo1DecryptedBytes;
    byte[] demoKeyBytes;
    byte[] demoIVBytes;
    String sPadding;
    ImageView trash2ImageView;
    Boolean changeFlag = false;
    String digest1, digest2;
    TextView ass2txt, passpasstxt, placementstatus2txt, debarstatus2txt, rolestatus2txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_single_user_view_dialog);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit User");
        ab.setDisplayHomeAsUpEnabled(true);

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        checkboxplaced = (CheckBox) findViewById(R.id.checkboxplaced);
        checkboxnotplaced = (CheckBox) findViewById(R.id.checkboxnotplaced);
        CheckBoxdebar = (CheckBox) findViewById(R.id.CheckBoxdebar);
        CheckBoxsnotdebar = (CheckBox) findViewById(R.id.CheckBoxsnotdebar);
        checkboxstudent = (CheckBox) findViewById(R.id.checkboxstudent);
        checkboxalumni = (CheckBox) findViewById(R.id.checkboxalumni);
        trash2ImageView = (ImageView) findViewById(R.id.trash2);
        //default

        checkboxplaced.setTypeface(Z.getBold(this));
        checkboxnotplaced.setTypeface(Z.getBold(this));
        CheckBoxdebar.setTypeface(Z.getBold(this));
        CheckBoxsnotdebar.setTypeface(Z.getBold(this));
        checkboxstudent.setTypeface(Z.getBold(this));
        checkboxalumni.setTypeface(Z.getBold(this));


        ass2txt = (TextView) findViewById(R.id.ass2txt);
        passpasstxt = (TextView) findViewById(R.id.passpasstxt);
        placementstatus2txt = (TextView) findViewById(R.id.placementstatus2txt);
        debarstatus2txt = (TextView) findViewById(R.id.debarstatus2txt);
        rolestatus2txt = (TextView) findViewById(R.id.rolestatus2txt);

        ass2txt.setTypeface(Z.getBold(this));
        passpasstxt.setTypeface(Z.getLight(this));
        placementstatus2txt.setTypeface(Z.getLight(this));
        debarstatus2txt.setTypeface(Z.getLight(this));
        rolestatus2txt.setTypeface(Z.getLight(this));


        emailinput = (TextInputLayout) findViewById(R.id.emailinput);
        companynameinput = (TextInputLayout) findViewById(R.id.companynameinput);
        email = (TextInputEditText) findViewById(R.id.email);
        companyname = (TextInputEditText) findViewById(R.id.companyname);

        emailinput.setTypeface(Z.getLight(this));
        companynameinput.setTypeface(Z.getLight(this));

        email.setTypeface(Z.getBold(this));
        companyname.setTypeface(Z.getBold(this));

        demoKeyBytes = SimpleBase64Encoder.decode(MySharedPreferencesManager.getDigest1(AdminSingleUserViewDialog.this));
        demoIVBytes = SimpleBase64Encoder.decode(MySharedPreferencesManager.getDigest2(AdminSingleUserViewDialog.this));
        sPadding = "ISO10126Padding";
        encadminUsername = MySharedPreferencesManager.getUsername(AdminSingleUserViewDialog.this);

        username = getIntent().getStringExtra("studentUsername");
        ROLE = getIntent().getStringExtra("role");
        Log.d("TAG", "onCreate: role got from back activity " + ROLE);
        isactivated = getIntent().getStringExtra("isactivated");
        email.setText(username);

        if (ROLE.equals("student")) {
            checkboxstudent.setChecked(true);
            checkboxalumni.setChecked(false);
        } else if (ROLE.equals("alumni")) {
            checkboxalumni.setChecked(true);
            checkboxstudent.setChecked(false);
        } else
            checkboxstudent.setChecked(true);


        View trash2 = (View) findViewById(R.id.trash2selectionview);

        if (isactivated.equals("Activated")) {
            trash2.setVisibility(View.GONE);
            trash2ImageView.setVisibility(View.GONE);
            email.setFocusable(false);
        }

        trash2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AdminSingleUserViewDialog.this);

                alertDialogBuilder
                        .setMessage("Are you sure you want to delete this account, by clicking yes this user will be deactivated ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        new DeleteNonActiveUser().execute();
                                        AdminSingleUserViewDialog.super.onBackPressed();
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
        });

        companyname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                companyname.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        checkboxplaced.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkboxnotplaced.setChecked(false);
                    companyname.setError(null);
                    PLACED = "yes";
                    companyname.setVisibility(View.VISIBLE);
                } else {
                    checkboxnotplaced.setChecked(true);
                    PLACED = "no";
                }
                changeFlag = true;
            }
        });

        checkboxnotplaced.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                companyname.setError(null);
                if (isChecked) {
                    checkboxplaced.setChecked(false);
                    companyname.setText("");
                    PLACED = "no";
                    companyname.setVisibility(View.GONE);
                } else {
                    checkboxplaced.setChecked(true);
                    PLACED = "yes";
                }
                changeFlag = true;
            }
        });


        CheckBoxdebar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    CheckBoxsnotdebar.setChecked(false);
                    DEBAR = "yes";
                } else {
                    CheckBoxsnotdebar.setChecked(true);
                    DEBAR = "no";
                }
                changeFlag = true;
            }
        });

        CheckBoxsnotdebar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    CheckBoxdebar.setChecked(false);
                    DEBAR = "no";
                } else {
                    CheckBoxdebar.setChecked(true);
                    DEBAR = "yes";
                }
                changeFlag = true;
            }
        });

        checkboxstudent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkboxalumni.setChecked(false);
                    ROLE = "student";
                } else {
                    checkboxalumni.setChecked(true);
                    ROLE = "alumni";
                }
                changeFlag = true;
            }
        });

        checkboxalumni.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkboxstudent.setChecked(false);
                    ROLE = "alumni";
                } else {
                    checkboxstudent.setChecked(true);
                    ROLE = "student";
                }
                changeFlag = true;
            }
        });

        new GetRegisteredUsersUnderAdmin().execute();

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isactivated.equals("Activated")) {
                    Toast.makeText(AdminSingleUserViewDialog.this, "You can not edit username of Activated user", Toast.LENGTH_SHORT).show();
                }
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                changeFlag = true;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        changeFlag = false;

    }//onc


    void showmenu() {
        AdminSingleUserViewDialog.this.invalidateOptionsMenu();
        Menu m = null;
        flag = 1;
        super.onCreateOptionsMenu(m);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_save:

                validateandSave();
                break;

            case android.R.id.home:

                onBackPressed();

                return (true);
        }

        return (super.onOptionsItemSelected(item));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (flag == 1)
            getMenuInflater().inflate(R.menu.admin_edit, menu);

        getMenuInflater().inflate(R.menu.savemenu, menu);
        return super.onCreateOptionsMenu(menu);


    }


    class GetRegisteredUsersUnderAdmin extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {


            try {

                byte[] usernameBytes = username.getBytes("UTF-8");


                byte[] usernameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, usernameBytes);
                encUsername = new String(SimpleBase64Encoder.encode(usernameEncryptedBytes));

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("u", encUsername));
                Log.d("TAG", "doInBackground: pass username " + username);

                json = jParser.makeHttpRequest(Z.url_GetPlacedDebarInfo, "GET", params);

                String info = json.getString("info");
                Log.d("TAG", "doInBackground: GetRegisteredUsersUnderAdmin info " + info);
                if (info.equals("success")) {

                    String isplaced = json.getString("isplaced");
                    strcompanyname = json.getString("companyname");
                    String isdebar = json.getString("isdebar");

                    Log.d("TAG", "doInBackground: isplaced " + isplaced);
                    Log.d("TAG", "doInBackground: isdebar " + isdebar);

                    if (!isplaced.equals("null")) {
                        byte[] demo1EncryptedBytes = SimpleBase64Encoder.decode(isplaced);
                        demo1DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, demo1EncryptedBytes);
                        isplaced = new String(demo1DecryptedBytes);
                        PLACED = isplaced;

                    }

                    if (!strcompanyname.equals("null")) {
                        byte[] demo1EncryptedBytes = SimpleBase64Encoder.decode(strcompanyname);
                        demo1DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, demo1EncryptedBytes);
                        strcompanyname = new String(demo1DecryptedBytes);


                    }

                    if (!isdebar.equals("null")) {
                        byte[] demo1EncryptedBytes = SimpleBase64Encoder.decode(isdebar);
                        demo1DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, demo1EncryptedBytes);
                        isdebar = new String(demo1DecryptedBytes);
                        DEBAR = isdebar;

                    }


                }

            } catch (Exception e) {
                e.printStackTrace();
                Log.d("TAG", "doInBackground: exp " + e.getMessage());
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {

//            Log.d("TAG", "onPostExecute: PLACED "+PLACED);
//            Log.d("TAG", "onPostExecute: DEBAR "+DEBAR);
//            Log.d("TAG", "onPostExecute: strcompanyname "+strcompanyname);
            if (PLACED.equals("yes")) {
                checkboxplaced.setChecked(true);
                checkboxnotplaced.setChecked(false);
            } else {
                checkboxnotplaced.setChecked(true);
                checkboxplaced.setChecked(false);
            }

            if (DEBAR.equals("yes")) {
                CheckBoxdebar.setChecked(true);
                CheckBoxsnotdebar.setChecked(false);
            } else {
                CheckBoxsnotdebar.setChecked(true);
                CheckBoxdebar.setChecked(false);
            }

            if (strcompanyname != null && !strcompanyname.equals(""))
                companyname.setText(strcompanyname);

            changeFlag = false;
        }

    }

    void validateandSave() {

        int errorflag = 0;
        companyname.setError(null);

        username = email.getText().toString();
        strcompanyname = companyname.getText().toString();


        if (!username.contains("@")) {
            email.setError("Incorrect Username");
            errorflag = 1;
        }
        if (checkboxplaced.isChecked()) {
            if (strcompanyname.length() < 1) {
                errorflag = 1;
                companynameinput.setError("Kindly enter valid company name");
            }
        }

        if (errorflag == 0) {
            try {

                byte[] usernameBytes = username.getBytes("UTF-8");
                byte[] companynameBytes = strcompanyname.getBytes("UTF-8");

                byte[] placedBytes = PLACED.getBytes("UTF-8");
                byte[] debarBytes = DEBAR.getBytes("UTF-8");


                byte[] usernameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, usernameBytes);
                encUsername = new String(SimpleBase64Encoder.encode(usernameEncryptedBytes));

                byte[] companynameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, companynameBytes);
                encCompanyname = new String(SimpleBase64Encoder.encode(companynameEncryptedBytes));

                byte[] placedBytesEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, placedBytes);
                encPlaced = new String(SimpleBase64Encoder.encode(placedBytesEncryptedBytes));

                byte[] debarEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, debarBytes);
                encdebar = new String(SimpleBase64Encoder.encode(debarEncryptedBytes));


                encRole = AES4all.Encrypt(ROLE, digest1, digest2);

                new SaveData().execute();

            } catch (Exception e) {
                Log.d("TAG", "validateandSave: enc exp : " + e.getMessage());
            }
        }
    }


    class SaveData extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encUsername));          //    0
            params.add(new BasicNameValuePair("p", encPlaced));           //     1
            params.add(new BasicNameValuePair("c", encCompanyname));      //     2
            params.add(new BasicNameValuePair("d", encdebar));            //     3
            params.add(new BasicNameValuePair("r", encRole));            //     4

            Log.d("TAG", "validateandSave: input role" + encRole);


            json = jParser.makeHttpRequest(Z.url_SavePlacedDebarInfo, "GET", params);
            try {
                r = json.getString("info");
                Log.d("TAG", ": SavePlacedDebarInfo json " + json);


            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if (result.equals("success")) {
                Toast.makeText(AdminSingleUserViewDialog.this, "Successfully updated..!", Toast.LENGTH_SHORT).show();

                setResult(Z.USER_DATA_CHANGE_RESULT_CODE);
//                Intent returnIntent = new Intent();
//                returnIntent.putExtra("result", result);

                AdminSingleUserViewDialog.super.onBackPressed();
            } else {
                Toast.makeText(AdminSingleUserViewDialog.this, result, Toast.LENGTH_SHORT).show();

            }
        }
    }


    class DeleteNonActiveUser extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encadminUsername));    //0
            params.add(new BasicNameValuePair("ue", encUsername));//1
            Log.d("TAG", "doInBackground: delete admin " + encadminUsername);
            Log.d("TAG", "doInBackground: delete admin " + encUsername);
            json = jParser.makeHttpRequest(Z.url_DeleteNonActiveUser, "GET", params);
            try {
                r = json.getString("info");
                Log.d("TAG", "DeleteNonActiveUser: json " + json);
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("TAG", "DeleteNonActiveUser: exp " + e.getMessage());
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null && result.equals("success"))
                Toast.makeText(AdminSingleUserViewDialog.this, "Successfully deleted..!", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onBackPressed() {
        Log.d("TAG", "changeFlag : " + changeFlag);

        if (changeFlag) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder
                    .setMessage("Do you want to discard changes ?")
                    .setCancelable(false)
                    .setPositiveButton("Discard",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    AdminSingleUserViewDialog.super.onBackPressed();
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
        } else
            AdminSingleUserViewDialog.super.onBackPressed();
    }
}
