package placeme.octopusites.com.placeme;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.kbeanie.multipicker.api.ImagePicker;
import com.kbeanie.multipicker.api.Picker;
import com.kbeanie.multipicker.api.callbacks.ImagePickerCallback;
import com.kbeanie.multipicker.api.entity.ChosenImage;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
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

import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;
import static placeme.octopusites.com.placeme.AES4all.fromString;
import static placeme.octopusites.com.placeme.LoginActivity.md5;

//import cat.ereza.customactivityoncrash.config.CaocConfig;

public class MainActivity extends AppCompatActivity implements ImagePickerCallback {

    File Imgfile;
    final public static int STUDENT_DATA_CHANGE_RESULT_CODE = 333;
    private int previousTotalNotification = 0; // The total number of items in the dataset after the last load
    private boolean loadingNotification = true; // True if we are still waiting for the last set of data to load.
    private int visibleThresholdNotification = 0; // The minimum amount of items to have below your current scroll position before loading more.
    int firstVisibleItemNotification, visibleItemCountNotification, totalItemCountNotification;
    private int page_to_call_notification = 1;
    private int current_page_notification = 1;
    int total_no_of_notifications;
    int[] called_pages_notification;
    boolean isFirstRunNotification = true, isLastPageLoadedNotification = false;
    int lastPageFlagNotification = 0;
    int unreadcountNotification = 0;
    int notificationcount = 0;
    int readstatuscountNotification = 0;
    RelativeLayout notificationcountrl;
    TextView notificationcounttxt;
    String lastupdatedNotification[];
    String[] uniqueUploadersNotification;
    String[] uniqueUploadersEncNotification;
    String notificationids[], notificationtitles[], notificationnotifications[], notificationfilename1[], notificationfilename2[], notificationfilename3[], notificationfilename4[], notificationfilename5[], notificationuploadtime[], notificationlastmodified[], notificationuploadedby[], notificationuploadedbyplain[];
    String notificationreadstatus[];
    int notificationpages = 0;
    private int previousTotalPlacement = 0; // The total number of items in the dataset after the last load
    private boolean loadingPlacement = true; // True if we are still waiting for the last set of data to load.
    private int visibleThresholdPlacement = 0; // The minimum amount of items to have below your current scroll position before loading more.
    int firstVisibleItemPlacement, visibleItemCountPlacement, totalItemCountPlacement;
    private int page_to_call_placement = 1;
    private int current_page_placement = 1;
    int total_no_of_placements;
    int[] called_pages_placement;
    boolean isFirstRunPlacement = true, isLastPageLoadedPlacement = false;
    int lastPageFlagPlacement = 0;
    int unreadcountPlacement = 0;
    int placementcount = 0;
    int readstatuscountPlacement = 0;
    RelativeLayout placementcountrl, messagecountrl;
    TextView placementcounttxt, messagecount;
    String lastupdatedPlacement[];
    String[] uniqueUploadersPlacement;
    String[] uniqueUploadersEncPlacement;
    String placementids[], placementcompanyname[], placementcpackage[], placementpost[], placementforwhichcourse[], placementforwhichstream[], placementvacancies[], placementlastdateofregistration[], placementdateofarrival[], placementbond[], placementnoofapti[], placementnooftechtest[], placementnoofgd[], placementnoofti[], placementnoofhri[], placementstdx[], placementstdxiiordiploma[], placementug[], placementpg[], placementuploadtime[], placementlastmodified[], placementuploadedby[], placementuploadedbyplain[], placementnoofallowedliveatkt[], placementnoofalloweddeadatkt[];
    String placementreadstatus[];
    int placementpages = 0;
    Menu menu;
    public static final String MyPREFERENCES = "MyPrefs";
    private String plainusername, username = "", fname = "", mname = "", sname = "";
    CircleImageView profile;
    boolean doubleBackToExitPressedOnce = false;
    int selectedMenuFlag = 1;

    int searchNotificationFlag = 0, searchPlacementFlag = 0;
    JSONParser jParser = new JSONParser();
    String digest1, digest2;
    JSONObject json;
    FrameLayout mainfragment;
    private MaterialSearchView searchView;
    int navMenuFlag = 0, oldNavMenuFlag = 0;
    FrameLayout crop_layout;
    private ImageView resultView;
    ImagePicker imagePicker;
    private int chooserType;
    private String mediaPath;
    private String finalPath;
    String filepath = "", filename = "";
    private String thumbPath;
    String directory;
    List<String> response;
    public static final String Username = "nameKey";
    public static final String Password = "passKey";
    SwipeRefreshLayout tswipe_refresh_layout;
    int crop_flag = 0;
    String reciever_username[], reciever_uid[];
    String unread_count[];
    int count;
    int unreadMessageCount = 0;
    String sender_uid;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    byte[] demoKeyBytes;
    byte[] demoIVBytes;
    String sPadding = "ISO10126Padding";

    Toolbar toolbar;
    private TextView toolbar_title;

    ViewPager mViewPager;
    TabLayout tabLayout;
    private int[] tabIcons = {
            R.drawable.news_feed_icon,
            R.drawable.videos_icon,
            R.drawable.resume_templates_icon,
            R.drawable.question_sets_icon,
            R.drawable.ebooks_icon,
            R.drawable.question_sets_icon,
    };
    //initial setup
    private RecyclerView recyclerViewNotification, recyclerViewPlacement;
    ArrayList<RecyclerItemEdit> tempListNotification;
    ArrayList<RecyclerItemPlacement> tempListPlacement;


    //new
    private ArrayList<RecyclerItemEdit> itemListNotificationNew = new ArrayList<>();
    private RecyclerItemEditNotificationAdapter mAdapterNotificationEdit;
    ArrayList<RecyclerItemEdit> itemlistfromserver = new ArrayList<>();


    private ArrayList<RecyclerItemPlacement> itemListPlacementnew = new ArrayList<>();
    private RecyclerItemAdapterPlacement mAdapterPlacement;
    ArrayList<RecyclerItemPlacement> placementListfromserver = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("MYTAG", "mainactivity called: ");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setTypeface(Z.getRighteous(MainActivity.this));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbar_title.setText("Notifications");

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);
        username = MySharedPreferencesManager.getUsername(this);
        String role = MySharedPreferencesManager.getRole(this);
        String pass = MySharedPreferencesManager.getPassword(this);

        mViewPager = (ViewPager) findViewById(R.id.blogcontainer);
        tabLayout = (TabLayout) findViewById(R.id.blogtabs);
        tabLayout.setupWithViewPager(mViewPager);

        MySharedPreferencesManager.save(MainActivity.this, "intro", "yes");
        recyclerViewNotification = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerViewPlacement = (RecyclerView) findViewById(R.id.recycler_view_placement);


        mAdapterPlacement = new RecyclerItemAdapterPlacement(itemListPlacementnew, MainActivity.this);
        recyclerViewPlacement.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManagerPlacement = new LinearLayoutManager(this);
        recyclerViewPlacement.setLayoutManager(linearLayoutManagerPlacement);
        recyclerViewPlacement.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerViewPlacement.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPlacement.setAdapter(mAdapterPlacement);


        mAdapterNotificationEdit = new RecyclerItemEditNotificationAdapter(itemListNotificationNew, MainActivity.this);
        recyclerViewNotification.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManagerNotification = new LinearLayoutManager(this);
        recyclerViewNotification.setLayoutManager(linearLayoutManagerNotification);
        recyclerViewNotification.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerViewNotification.setItemAnimator(new DefaultItemAnimator());
        recyclerViewNotification.setAdapter(mAdapterNotificationEdit);


        if (selectedMenuFlag == 1) {
            recyclerViewNotification.setVisibility(View.VISIBLE);
            recyclerViewPlacement.setVisibility(View.GONE);

        } else if (selectedMenuFlag == 2) {
            recyclerViewNotification.setVisibility(View.GONE);
            recyclerViewPlacement.setVisibility(View.VISIBLE);
        }


        setupViewPager(mViewPager);
        setupTabIcons();
        tabLayout.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(mViewPager) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        int tabIconColor = ContextCompat.getColor(MainActivity.this, R.color.colorAccent);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);
                        int tabIconColor = ContextCompat.getColor(MainActivity.this, R.color.while_color);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                }
        );

//        FirebaseMessaging.getInstance().subscribeToTopic("ALL");
//        FirebaseMessaging.getInstance().subscribeToTopic("REGISTERED");
//        FirebaseMessaging.getInstance().subscribeToTopic("STUDENT");
//        FirebaseMessaging.getInstance().subscribeToTopic("ALUMNI");
//        FirebaseMessaging.getInstance().subscribeToTopic("ADMIN");
//        FirebaseMessaging.getInstance().subscribeToTopic("HR");


        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if (intent.getAction().equals("pushNotificationChat")) {

                    Log.d("TAG", "push broadcast received: ");
                    new GetUnreadCountOfNotificationAndPlacement().execute();
//                    String sender = intent.getStringExtra("sender");
//                    for(int i=0;i<reciever_username.length;i++)
//                    {
//                        if(reciever_username[i].equals(sender)) {
//                            unread_count[i] = Integer.parseInt(unread_count[i]) + 1+"";
//                            unreadMessageCount++;
//                        }
//                    }
//                    messagecountrl.setVisibility(View.VISIBLE);
//                    messagecount.setText(unreadMessageCount+"");
//                    if(unreadMessageCount==0)
//                    {
//                        messagecountrl.setVisibility(View.GONE);
//                    }
                    new GetNotificationsReadStatus().execute();
                    new GetPlacementsReadStatus().execute();
                    new GetUnreadMessagesCount().execute();
                    MessagesFragment fragment = (MessagesFragment) getSupportFragmentManager().findFragmentById(R.id.mainfragment);
                    if (fragment != null)
                        fragment.addMessages();
                }
            }
        };


        imagePicker = new ImagePicker(this);
        imagePicker.setImagePickerCallback(this);

        imagePicker.shouldGenerateMetadata(false); // Default is true
        imagePicker.shouldGenerateThumbnails(false); // Default is true


        crop_layout = (FrameLayout) findViewById(R.id.crop_layout);
        resultView = (ImageView) findViewById(R.id.result_image);

        try {

            demoKeyBytes = SimpleBase64Encoder.decode(digest1);
            demoIVBytes = SimpleBase64Encoder.decode(digest2);
            sPadding = "ISO10126Padding";

            byte[] demo1EncryptedBytes1 = SimpleBase64Encoder.decode(username);
            byte[] demo1DecryptedBytes1 = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, demo1EncryptedBytes1);
            plainusername = new String(demo1DecryptedBytes1);

            byte[] demo2EncryptedBytes1 = SimpleBase64Encoder.decode(pass);
            byte[] demo2DecryptedBytes1 = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, demo2EncryptedBytes1);
            String data = new String(demo2DecryptedBytes1);
            String hash = md5(data + MySharedPreferencesManager.getDigest3(MainActivity.this));

//            new LoginFirebaseTask().execute(plainusername,hash);
            loginFirebase(plainusername, hash);

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        MySharedPreferencesManager.save(MainActivity.this, "otp", "no");

        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setVoiceSearch(false);
        searchView.setCursorDrawable(R.drawable.custom_cursor);

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (selectedMenuFlag == 0) {

                } else if (selectedMenuFlag == 1) {
                    searchNotificationFlag = 1;
                    filterNotifications(newText);
                } else if (selectedMenuFlag == 2) {
                    searchPlacementFlag = 1;
                    filterPlacements(newText);
                } else if (selectedMenuFlag == 3) {

                } else if (selectedMenuFlag == 4) {

                } else if (selectedMenuFlag == 5) {

                } else if (selectedMenuFlag == 6) {

                }

                return false;
            }
        });
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                final Drawable upArrow = getResources().getDrawable(R.drawable.backarrow);
                upArrow.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                searchView.setBackIcon(upArrow);
            }

            @Override
            public void onSearchViewClosed() {
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            //scrolling toolbar gone problem solve blog
            AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);

                if (oldNavMenuFlag != navMenuFlag) {
                    if (navMenuFlag == 1) {
                        selectedMenuFlag = 0;

                        //scroll
                        params.setScrollFlags(0);

                        crop_layout.setVisibility(View.GONE);
                        MyProfileFragment fragment = new MyProfileFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction =
                                getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.mainfragment, fragment);
                        fragmentTransaction.commit();
                        mainfragment.setVisibility(View.VISIBLE);

                        mViewPager.setVisibility(View.GONE);
                        tabLayout.setVisibility(View.GONE);
                        getSupportActionBar().setTitle("");
                        toolbar_title.setText("My Profile");


//                    int i=4/0;


                    } else if (navMenuFlag == 2) {

                        params.setScrollFlags(0);

                        selectedMenuFlag = 1;
                        crop_layout.setVisibility(View.GONE);
                        getSupportActionBar().setTitle("");
                        toolbar_title.setText("Notifications");
                        mainfragment.setVisibility(View.GONE);
                        tswipe_refresh_layout.setVisibility(View.VISIBLE);

                        getNotifications();
                        recyclerViewNotification.setVisibility(View.VISIBLE);
                        recyclerViewPlacement.setVisibility(View.GONE);
                        mViewPager.setVisibility(View.GONE);
                        tabLayout.setVisibility(View.GONE);

                    } else if (navMenuFlag == 3) {

                        selectedMenuFlag = 2;
                        params.setScrollFlags(0);

                        crop_layout.setVisibility(View.GONE);
                        getSupportActionBar().setTitle("");
                        toolbar_title.setText("Placements");
                        mainfragment.setVisibility(View.GONE);
                        tswipe_refresh_layout.setVisibility(View.VISIBLE);

                        getPlacements();
                        recyclerViewNotification.setVisibility(View.GONE);
                        recyclerViewPlacement.setVisibility(View.VISIBLE);
                        mViewPager.setVisibility(View.GONE);
                        tabLayout.setVisibility(View.GONE);

                    } else if (navMenuFlag == 4) {

                        selectedMenuFlag = 3;
                        params.setScrollFlags(0);

                        crop_layout.setVisibility(View.GONE);
                        MessagesFragment fragment = new MessagesFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction =
                                getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.mainfragment, fragment);
                        fragmentTransaction.commit();

                        mainfragment.setVisibility(View.VISIBLE);
                        getSupportActionBar().setTitle("");
                        toolbar_title.setText("Messages");
                        tswipe_refresh_layout.setVisibility(View.GONE);
                        mViewPager.setVisibility(View.GONE);
                        tabLayout.setVisibility(View.GONE);

                    } else if (navMenuFlag == 5) {

                        selectedMenuFlag = 4;
                        params.setScrollFlags(0);

                        crop_layout.setVisibility(View.GONE);
                        SettingsFragment fragment = new SettingsFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction =
                                getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.mainfragment, fragment);
                        fragmentTransaction.commit();
                        mainfragment.setVisibility(View.VISIBLE);
                        getSupportActionBar().setTitle("");
                        toolbar_title.setText("Settings");
                        tswipe_refresh_layout.setVisibility(View.GONE);
                        mViewPager.setVisibility(View.GONE);
                        tabLayout.setVisibility(View.GONE);

                    } else if (navMenuFlag == 6) {

                        selectedMenuFlag = 5;
                        params.setScrollFlags(5);           // enable scrolling

//                        Toast.makeText(MainActivity.this, ""+params.getScrollFlags(), Toast.LENGTH_SHORT).show();
                        crop_layout.setVisibility(View.GONE);
                        //mainfragment.setVisibility(View.VISIBLE);
                        getSupportActionBar().setTitle("");
                        toolbar_title.setText("Blog");
                        tswipe_refresh_layout.setVisibility(View.GONE);
                        mainfragment.setVisibility(View.GONE);
                        mViewPager.setVisibility(View.VISIBLE);
                        tabLayout.setVisibility(View.VISIBLE);

                    } else if (navMenuFlag == 7) {

                        selectedMenuFlag = 6;
                        params.setScrollFlags(0);

                        crop_layout.setVisibility(View.GONE);
                        AboutFragment fragment = new AboutFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction =
                                getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.mainfragment, fragment);
                        fragmentTransaction.commit();
                        mainfragment.setVisibility(View.VISIBLE);
                        getSupportActionBar().setTitle("");
                        toolbar_title.setText("About");
                        tswipe_refresh_layout.setVisibility(View.GONE);
                        mViewPager.setVisibility(View.GONE);
                        tabLayout.setVisibility(View.GONE);
                    }
                }

            }


            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                oldNavMenuFlag = navMenuFlag;

            }
        };


        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        final View hView = navigationView.getHeaderView(0);

        profile = (CircleImageView) hView.findViewById(R.id.profile_image);

//        new GetProfileImage().execute();
        new Getsingnature().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        final ImageView profilei = (ImageView) hView.findViewById(R.id.profile);
        final ImageView notificationi = (ImageView) hView.findViewById(R.id.notification);
        final ImageView placementi = (ImageView) hView.findViewById(R.id.placement);
        final ImageView settingsi = (ImageView) hView.findViewById(R.id.settings);
        final ImageView newsi = (ImageView) hView.findViewById(R.id.blog);
        final ImageView chati = (ImageView) hView.findViewById(R.id.chat);

        notificationcounttxt = (TextView) hView.findViewById(R.id.notificationcount);
        notificationcountrl = (RelativeLayout) hView.findViewById(R.id.notificationcountrl);

        placementcounttxt = (TextView) hView.findViewById(R.id.placementcount);
        placementcountrl = (RelativeLayout) hView.findViewById(R.id.placementcountrl);

        messagecount = (TextView) hView.findViewById(R.id.messagecount);
        messagecountrl = (RelativeLayout) hView.findViewById(R.id.messagecountrl);

        View v1 = (View) hView.findViewById(R.id.prifileselectionview);
        View v2 = (View) hView.findViewById(R.id.notificationselectionview);
        View v3 = (View) hView.findViewById(R.id.placementselectionview);
        View v5 = (View) hView.findViewById(R.id.settingselectionview);
        View v6 = (View) hView.findViewById(R.id.blogselectionview);
        View v7 = (View) hView.findViewById(R.id.abtselectionview);
        View v8 = (View) hView.findViewById(R.id.chatselectionview);

        mainfragment = (FrameLayout) findViewById(R.id.mainfragment);

        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navMenuFlag = 1;

                Drawable myDrawable1 = getResources().getDrawable(R.drawable.my_profile_icon_selected);
                profilei.setImageDrawable(myDrawable1);

                TextView pt1 = (TextView) hView.findViewById(R.id.profiletxt);
                pt1.setTypeface(Z.getBold(MainActivity.this));
                pt1.setTextColor(getResources().getColor(R.color.sky_blue_color));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification_icon);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2 = (TextView) hView.findViewById(R.id.notificationtxt);
                pt2.setTypeface(Z.getLight(MainActivity.this));
                pt2.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement_icon);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3 = (TextView) hView.findViewById(R.id.placementtxt);
                pt3.setTypeface(Z.getLight(MainActivity.this));
                pt3.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable8 = getResources().getDrawable(R.drawable.messages_icon);
                chati.setImageDrawable(myDrawable8);

                TextView pt8 = (TextView) hView.findViewById(R.id.chattxt);
                pt8.setTypeface(Z.getLight(MainActivity.this));
                pt8.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings_icon);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5 = (TextView) hView.findViewById(R.id.settingstxt);
                pt5.setTypeface(Z.getLight(MainActivity.this));
                pt5.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog_icon);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6 = (TextView) hView.findViewById(R.id.blogtxt);
                pt6.setTypeface(Z.getLight(MainActivity.this));
                pt6.setTextColor(getResources().getColor(R.color.while_color));


                TextView pt7 = (TextView) hView.findViewById(R.id.abttxt);
                pt7.setTypeface(Z.getLight(MainActivity.this));
                pt7.setTextColor(getResources().getColor(R.color.while_color));

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);


            }
        });
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navMenuFlag = 2;

                tswipe_refresh_layout.setVisibility(View.VISIBLE);

                Drawable myDrawable1 = getResources().getDrawable(R.drawable.my_profile_icon);
                profilei.setImageDrawable(myDrawable1);

                TextView pt1 = (TextView) hView.findViewById(R.id.profiletxt);
                pt1.setTypeface(Z.getLight(MainActivity.this));
                pt1.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification_icon_selected);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2 = (TextView) hView.findViewById(R.id.notificationtxt);
                pt2.setTypeface(Z.getBold(MainActivity.this));
                pt2.setTextColor(getResources().getColor(R.color.sky_blue_color));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement_icon);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3 = (TextView) hView.findViewById(R.id.placementtxt);
                pt3.setTypeface(Z.getLight(MainActivity.this));
                pt3.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable8 = getResources().getDrawable(R.drawable.messages_icon);
                chati.setImageDrawable(myDrawable8);

                TextView pt8 = (TextView) hView.findViewById(R.id.chattxt);
                pt8.setTypeface(Z.getLight(MainActivity.this));
                pt8.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings_icon);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5 = (TextView) hView.findViewById(R.id.settingstxt);
                pt5.setTypeface(Z.getLight(MainActivity.this));
                pt5.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog_icon);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6 = (TextView) hView.findViewById(R.id.blogtxt);
                pt6.setTypeface(Z.getLight(MainActivity.this));
                pt6.setTextColor(getResources().getColor(R.color.while_color));

                TextView pt7 = (TextView) hView.findViewById(R.id.abttxt);
                pt7.setTypeface(Z.getLight(MainActivity.this));
                pt7.setTextColor(getResources().getColor(R.color.while_color));

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

            }
        });
        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navMenuFlag = 3;

                tswipe_refresh_layout.setVisibility(View.VISIBLE);

                Drawable myDrawable1 = getResources().getDrawable(R.drawable.my_profile_icon);
                profilei.setImageDrawable(myDrawable1);

                TextView pt1 = (TextView) hView.findViewById(R.id.profiletxt);
                pt1.setTypeface(Z.getLight(MainActivity.this));
                pt1.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification_icon);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2 = (TextView) hView.findViewById(R.id.notificationtxt);
                pt2.setTypeface(Z.getLight(MainActivity.this));
                pt2.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement_icon_selected);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3 = (TextView) hView.findViewById(R.id.placementtxt);
                pt3.setTypeface(Z.getBold(MainActivity.this));
                pt3.setTextColor(getResources().getColor(R.color.sky_blue_color));

                Drawable myDrawable8 = getResources().getDrawable(R.drawable.messages_icon);
                chati.setImageDrawable(myDrawable8);

                TextView pt8 = (TextView) hView.findViewById(R.id.chattxt);
                pt8.setTypeface(Z.getLight(MainActivity.this));
                pt8.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings_icon);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5 = (TextView) hView.findViewById(R.id.settingstxt);
                pt5.setTypeface(Z.getLight(MainActivity.this));
                pt5.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog_icon);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6 = (TextView) hView.findViewById(R.id.blogtxt);
                pt6.setTypeface(Z.getLight(MainActivity.this));
                pt6.setTextColor(getResources().getColor(R.color.while_color));

                TextView pt7 = (TextView) hView.findViewById(R.id.abttxt);
                pt7.setTypeface(Z.getLight(MainActivity.this));
                pt7.setTextColor(getResources().getColor(R.color.while_color));

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);


            }
        });
        v8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navMenuFlag = 4;

                Drawable myDrawable1 = getResources().getDrawable(R.drawable.my_profile_icon);
                profilei.setImageDrawable(myDrawable1);

                TextView pt1 = (TextView) hView.findViewById(R.id.profiletxt);
                pt1.setTypeface(Z.getLight(MainActivity.this));
                pt1.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification_icon);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2 = (TextView) hView.findViewById(R.id.notificationtxt);
                pt2.setTypeface(Z.getLight(MainActivity.this));
                pt2.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement_icon);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3 = (TextView) hView.findViewById(R.id.placementtxt);
                pt3.setTypeface(Z.getLight(MainActivity.this));
                pt3.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable8 = getResources().getDrawable(R.drawable.messages_icon_selected);
                chati.setImageDrawable(myDrawable8);

                TextView pt8 = (TextView) hView.findViewById(R.id.chattxt);
                pt8.setTypeface(Z.getBold(MainActivity.this));
                pt8.setTextColor(getResources().getColor(R.color.sky_blue_color));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings_icon);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5 = (TextView) hView.findViewById(R.id.settingstxt);
                pt5.setTypeface(Z.getLight(MainActivity.this));
                pt5.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog_icon);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6 = (TextView) hView.findViewById(R.id.blogtxt);
                pt6.setTypeface(Z.getLight(MainActivity.this));
                pt6.setTextColor(getResources().getColor(R.color.while_color));

                TextView pt7 = (TextView) hView.findViewById(R.id.abttxt);
                pt7.setTypeface(Z.getLight(MainActivity.this));
                pt7.setTextColor(getResources().getColor(R.color.while_color));

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        v5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navMenuFlag = 5;
//                Toast.makeText(MainActivity.this, "settings", Toast.LENGTH_SHORT).show();

                Drawable myDrawable1 = getResources().getDrawable(R.drawable.my_profile_icon);
                profilei.setImageDrawable(myDrawable1);

                TextView pt1 = (TextView) hView.findViewById(R.id.profiletxt);
                pt1.setTypeface(Z.getLight(MainActivity.this));
                pt1.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification_icon);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2 = (TextView) hView.findViewById(R.id.notificationtxt);
                pt2.setTypeface(Z.getLight(MainActivity.this));
                pt2.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement_icon);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3 = (TextView) hView.findViewById(R.id.placementtxt);
                pt3.setTypeface(Z.getLight(MainActivity.this));
                pt3.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable8 = getResources().getDrawable(R.drawable.messages_icon);
                chati.setImageDrawable(myDrawable8);

                TextView pt8 = (TextView) hView.findViewById(R.id.chattxt);
                pt8.setTypeface(Z.getLight(MainActivity.this));
                pt8.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings_icon_selected);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5 = (TextView) hView.findViewById(R.id.settingstxt);
                pt5.setTypeface(Z.getBold(MainActivity.this));
                pt5.setTextColor(getResources().getColor(R.color.sky_blue_color));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog_icon);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6 = (TextView) hView.findViewById(R.id.blogtxt);
                pt6.setTypeface(Z.getLight(MainActivity.this));
                pt6.setTextColor(getResources().getColor(R.color.while_color));

                TextView pt7 = (TextView) hView.findViewById(R.id.abttxt);
                pt7.setTypeface(Z.getLight(MainActivity.this));
                pt7.setTextColor(getResources().getColor(R.color.while_color));

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

            }
        });
        v6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navMenuFlag = 6;

                Drawable myDrawable1 = getResources().getDrawable(R.drawable.my_profile_icon);
                profilei.setImageDrawable(myDrawable1);

                TextView pt1 = (TextView) hView.findViewById(R.id.profiletxt);
                pt1.setTypeface(Z.getLight(MainActivity.this));
                pt1.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification_icon);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2 = (TextView) hView.findViewById(R.id.notificationtxt);
                pt2.setTypeface(Z.getLight(MainActivity.this));
                pt2.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement_icon);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3 = (TextView) hView.findViewById(R.id.placementtxt);
                pt3.setTypeface(Z.getLight(MainActivity.this));
                pt3.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable8 = getResources().getDrawable(R.drawable.messages_icon);
                chati.setImageDrawable(myDrawable8);

                TextView pt8 = (TextView) hView.findViewById(R.id.chattxt);
                pt8.setTypeface(Z.getLight(MainActivity.this));
                pt8.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings_icon);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5 = (TextView) hView.findViewById(R.id.settingstxt);
                pt5.setTypeface(Z.getLight(MainActivity.this));
                pt5.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog_icon_selected);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6 = (TextView) hView.findViewById(R.id.blogtxt);
                pt6.setTypeface(Z.getBold(MainActivity.this));
                pt6.setTextColor(getResources().getColor(R.color.sky_blue_color));

                TextView pt7 = (TextView) hView.findViewById(R.id.abttxt);
                pt7.setTypeface(Z.getLight(MainActivity.this));
                pt7.setTextColor(getResources().getColor(R.color.while_color));

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);


            }
        });
        v7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navMenuFlag = 7;

                Drawable myDrawable1 = getResources().getDrawable(R.drawable.my_profile_icon);
                profilei.setImageDrawable(myDrawable1);

                TextView pt1 = (TextView) hView.findViewById(R.id.profiletxt);
                pt1.setTypeface(Z.getLight(MainActivity.this));
                pt1.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification_icon);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2 = (TextView) hView.findViewById(R.id.notificationtxt);
                pt2.setTypeface(Z.getLight(MainActivity.this));
                pt2.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement_icon);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3 = (TextView) hView.findViewById(R.id.placementtxt);
                pt3.setTypeface(Z.getLight(MainActivity.this));
                pt3.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable8 = getResources().getDrawable(R.drawable.messages_icon);
                chati.setImageDrawable(myDrawable8);

                TextView pt8 = (TextView) hView.findViewById(R.id.chattxt);
                pt8.setTypeface(Z.getLight(MainActivity.this));
                pt8.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings_icon);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5 = (TextView) hView.findViewById(R.id.settingstxt);
                pt5.setTypeface(Z.getLight(MainActivity.this));
                pt5.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog_icon);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6 = (TextView) hView.findViewById(R.id.blogtxt);
                pt6.setTypeface(Z.getLight(MainActivity.this));
                pt6.setTextColor(getResources().getColor(R.color.while_color));

                TextView pt7 = (TextView) hView.findViewById(R.id.abttxt);
                pt7.setTypeface(Z.getLight(MainActivity.this));
                pt7.setTextColor(getResources().getColor(R.color.sky_blue_color));

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);


            }
        });


        Drawable myDrawable = getResources().getDrawable(R.drawable.notification_icon_selected);
        notificationi.setImageDrawable(myDrawable);

        TextView pt = (TextView) hView.findViewById(R.id.notificationtxt);
        pt.setTypeface(Z.getBold(MainActivity.this));
        pt.setTextColor(getResources().getColor(R.color.sky_blue_color));

        TextView pt1 = (TextView) hView.findViewById(R.id.profiletxt);
        pt1.setTypeface(Z.getLight(MainActivity.this));
        pt1.setTextColor(getResources().getColor(R.color.while_color));

        TextView pt3 = (TextView) hView.findViewById(R.id.placementtxt);
        pt3.setTypeface(Z.getLight(MainActivity.this));
        pt3.setTextColor(getResources().getColor(R.color.while_color));

        TextView pt8 = (TextView) hView.findViewById(R.id.chattxt);
        pt8.setTypeface(Z.getLight(MainActivity.this));
        pt8.setTextColor(getResources().getColor(R.color.while_color));

        TextView pt5 = (TextView) hView.findViewById(R.id.settingstxt);
        pt5.setTypeface(Z.getLight(MainActivity.this));
        pt5.setTextColor(getResources().getColor(R.color.while_color));

        TextView pt6 = (TextView) hView.findViewById(R.id.blogtxt);
        pt6.setTypeface(Z.getLight(MainActivity.this));
        pt6.setTextColor(getResources().getColor(R.color.while_color));

        TextView pt7 = (TextView) hView.findViewById(R.id.abttxt);
        pt7.setTypeface(Z.getLight(MainActivity.this));
        pt7.setTextColor(getResources().getColor(R.color.while_color));


        recyclerViewNotification = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerViewPlacement = (RecyclerView) findViewById(R.id.recycler_view_placement);
        //placements


        recyclerViewNotification.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerViewNotification, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                try {


                    RecyclerItemEdit item = null;
                    if (searchNotificationFlag == 1)
                        item = tempListNotification.get(position);
                    else
                        item = itemListNotificationNew.get(position);
                    if (!item.isIsread()) {
                        item.setIsread(true);
                        unreadcountNotification--;
                        notificationcountrl.setVisibility(View.VISIBLE);
                        notificationcounttxt.setText(unreadcountNotification + "");
                        if (unreadcountNotification == 0) {
                            notificationcountrl.setVisibility(View.GONE);
                        }

                    }

                    mAdapterNotificationEdit.notifyDataSetChanged();
                    changeReadStatusNotification(item.getId());

                    crop_flag = 1;
                    Intent i1 = new Intent(MainActivity.this, ViewNotification.class);
                    i1.putExtra("id", item.getId());
                    i1.putExtra("title", item.getTitle());
                    i1.putExtra("notification", item.getNotification());

                    if (item.getFilename1() != null) {
                        i1.putExtra("file1", Z.Decrypt(item.getFilename1(), MainActivity.this));
                    } else {
                        i1.putExtra("file1", item.getFilename1());
                    }

                    if (item.getFilename2() != null) {
                        i1.putExtra("file2", Z.Decrypt(item.getFilename2(), MainActivity.this));
                    } else {
                        i1.putExtra("file2", item.getFilename2());
                    }
                    if (item.getFilename3() != null) {
                        i1.putExtra("file3", Z.Decrypt(item.getFilename3(), MainActivity.this));
                    } else {
                        i1.putExtra("file3", item.getFilename3());
                    }
                    if (item.getFilename4() != null) {
                        i1.putExtra("file4", Z.Decrypt(item.getFilename4(), MainActivity.this));
                    } else {
                        i1.putExtra("file4", item.getFilename4());
                    }

                    if (item.getFilename5() != null) {
                        i1.putExtra("file5", Z.Decrypt(item.getFilename5(), MainActivity.this));
                    } else {
                        i1.putExtra("file5", item.getFilename5());
                    }

//                    i1.putExtra("file2", item.getFilename2());
//                    i1.putExtra("file3", item.getFilename3());
//                    i1.putExtra("file4", item.getFilename4());
//                    i1.putExtra("file5", item.getFilename5());
                    i1.putExtra("uploadedby", item.getUploadedby());
                    i1.putExtra("uploadtime", item.getUploadtime());
                    i1.putExtra("lastmodified", item.getLastmodified());
                    Log.d("putextra", "uploadedby: " + item.getUploadedby());
                    Log.d("putextra", "lastmodified: " + item.getLastmodified());

                    startActivity(i1);

                } catch (Exception e) {
                    Log.d("Exception", " " + e.getMessage());
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        recyclerViewNotification.setOnScrollListener(new EndlessRecyclerOnScrollListenerNotification(linearLayoutManagerNotification) {
            @Override
            public void onLoadMore(int current_page) {

                if (total_no_of_notifications > 20) {
                    simulateLoadingNotification();
                }

            }
        });

        recyclerViewPlacement.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerViewPlacement, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                RecyclerItemPlacement item = null;
                if (searchPlacementFlag == 1)
                    item = tempListPlacement.get(position);
                else
                    item = itemListPlacementnew.get(position);


                if (!item.isIsread()) {
                    item.setIsread(true);
                    unreadcountPlacement--;
                    placementcountrl.setVisibility(View.VISIBLE);
                    placementcounttxt.setText(unreadcountPlacement + "");
                    if (unreadcountPlacement == 0) {
                        placementcountrl.setVisibility(View.GONE);
                    }

                }

                mAdapterPlacement.notifyDataSetChanged();

                changeReadStatusPlacement(item.getId());

                crop_flag = 1;

                Intent i1 = new Intent(MainActivity.this, ViewPlacement.class);
                i1.putExtra("id", item.getId());
                i1.putExtra("companyname", item.getCompanyname());
                i1.putExtra("package", item.getCpackage());
                i1.putExtra("post", item.getPost());
                i1.putExtra("forwhichcourse", item.getForwhichcourse());
                i1.putExtra("forwhichstream", item.getForwhichstream());
                i1.putExtra("vacancies", item.getVacancies());
                i1.putExtra("lastdateofregistration", item.getLastdateofregistration());
                i1.putExtra("dateofarrival", item.getDateofarrival());
                i1.putExtra("bond", item.getBond());
                i1.putExtra("noofapti", item.getNoofapti());
                i1.putExtra("nooftechtest", item.getNooftechtest());
                i1.putExtra("noofgd", item.getNoofgd());
                i1.putExtra("noofti", item.getNoofti());
                i1.putExtra("noofhri", item.getNoofhri());
                i1.putExtra("stdx", item.getStdx());
                i1.putExtra("stdxiiordiploma", item.getStdxiiordiploma());
                i1.putExtra("ug", item.getUg());
                i1.putExtra("pg", item.getPg());
                i1.putExtra("uploadtime", item.getUploadtime());
                i1.putExtra("lastmodified", item.getLastmodified());
                i1.putExtra("uploadedby", item.getUploadedby());
                i1.putExtra("noofallowedliveatkt", item.getNoofallowedliveatkt());
                i1.putExtra("noofalloweddeadatkt", item.getNoofalloweddeadatkt());
                startActivity(i1);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        recyclerViewPlacement.setOnScrollListener(new EndlessRecyclerOnScrollListenerPlacement(linearLayoutManagerPlacement) {
            @Override
            public void onLoadMore(int current_page) {

                if (total_no_of_placements > 20) {
                    simulateLoadingPlacement();
                }

            }
        });

        disableNavigationViewScrollbars(navigationView);


        tswipe_refresh_layout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        tswipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                itemListNotificationNew.clear();
                itemListPlacementnew.clear();
                if (selectedMenuFlag == 1) {
                    getNotifications();
                } else if (selectedMenuFlag == 2) {
                    getPlacements();
                }

            }
        });

//        tswipe_refresh_layout.setRefreshing(true);
//
//        new GetUnreadCountOfNotificationAndPlacement().execute();
        new GetUnreadMessagesCount().execute();


        getNotifications();
        new UpdateFirebaseToken().execute();
    }

    void loginFirebase(String username, String hash) {

        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(username, hash)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Successfully logged in to Firebase", Toast.LENGTH_SHORT).show();


                        } else {
                            Toast.makeText(MainActivity.this, "Failed to login to Firebase", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    void filterNotifications(String text) {
        tempListNotification = new ArrayList();
        for (RecyclerItemEdit d : itemListNotificationNew) {

            if (containsIgnoreCase(d.getTitle(), text)) {
                tempListNotification.add(d);
            }
        }
        mAdapterNotificationEdit.updateList(tempListNotification, text);
    }

    void filterPlacements(String text) {
        tempListPlacement = new ArrayList();
        for (RecyclerItemPlacement d : itemListPlacementnew) {

            if (containsIgnoreCase(d.getCompanyname(), text)) {
                tempListPlacement.add(d);
            }
        }
        mAdapterPlacement.updateList(tempListPlacement, text);
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);
        tabLayout.getTabAt(5).setIcon(tabIcons[5]);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new NoDataAvailableFragment(), "News Feed");
        adapter.addFrag(new NoDataAvailableFragment(), "Videos");
        adapter.addFrag(new ResumeTemplatesFragment(), "Resume Templates");
        adapter.addFrag(new NoDataAvailableFragment(), "Interview Questions");
        adapter.addFrag(new NoDataAvailableFragment(), "Ebooks");
        adapter.addFrag(new NoDataAvailableFragment(), "Question Sets");
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

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public static boolean containsIgnoreCase(String str, String searchStr) {
        if (str == null || searchStr == null) return false;

        final int length = searchStr.length();
        if (length == 0)
            return true;

        for (int i = str.length() - length; i >= 0; i--) {
            if (str.regionMatches(true, i, searchStr, 0, length))
                return true;
        }
        return false;
    }

    public void updateUnreadMessageCount(int readCount) {
        unreadMessageCount -= readCount;
        messagecountrl.setVisibility(View.VISIBLE);
        messagecount.setText(unreadMessageCount + "");
        if (unreadMessageCount <= 0) {
            messagecountrl.setVisibility(View.GONE);
        }
    }

    class GetUnreadMessagesCount extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));
            json = jParser.makeHttpRequest(Z.url_get_chatrooms, "GET", params);

            try {

                count = Integer.parseInt(json.getString("count"));
                sender_uid = json.getString("uid");

                reciever_username = new String[count];
                reciever_uid = new String[count];
                unread_count = new String[count];

                for (int i = 0; i < count; i++) {
                    unread_count[i] = "0";
                    reciever_username[i] = json.getString("username" + i);
                    reciever_uid[i] = json.getString("uid" + i);
                }

            } catch (Exception ex) {

            }

            return "";
        }

        @Override
        protected void onPostExecute(String result) {

            if (count > 0) {

                for (int i = 0; i < count; i++) {

                    String tempusername = null;
                    try {
                        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                        String sPadding = "ISO10126Padding";

                        byte[] usernameBytes = reciever_username[i].getBytes("UTF-8");

                        byte[] usernameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, usernameBytes);
                        tempusername = new String(SimpleBase64Encoder.encode(usernameEncryptedBytes));


                    } catch (Exception e) {
                    }
                    if (reciever_uid != null)
                        new GetMessagesReadStatus(username, tempusername, sender_uid, reciever_uid[i], i).execute();
                }

            }


        }
    }

    class GetMessagesReadStatus extends AsyncTask<String, String, String> {

        String sender, reciever, senderuid, recieveruid;
        int index;

        GetMessagesReadStatus(String sender, String reciever, String senderuid, String recieveruid, int index) {
            this.sender = sender;
            this.reciever = reciever;
            this.senderuid = senderuid;
            this.recieveruid = recieveruid;
            this.index = index;
        }

        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("s", sender));       //0
            params.add(new BasicNameValuePair("r", reciever));     //1
            params.add(new BasicNameValuePair("su", senderuid));    //2
            params.add(new BasicNameValuePair("ru", recieveruid));  //3

            try {

                json = jParser.makeHttpRequest(Z.url_getmessagesreadstatus, "GET", params);
                unread_count[index] = json.getString("unreadcount");


            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            unreadMessageCount = 0;
            if (index == count - 1) {

                for (int i = 0; i < count; i++) {

                    unreadMessageCount += Integer.parseInt(unread_count[i]);

                }
                messagecountrl.setVisibility(View.VISIBLE);
                messagecount.setText(unreadMessageCount + "");
                if (unreadMessageCount == 0) {
                    messagecountrl.setVisibility(View.GONE);
                }
            }
        }
    }

    class GetUnreadCountOfNotificationAndPlacement extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0

            try {

                json = jParser.makeHttpRequest(Z.url_getnotificationsmetadata, "GET", params);
                unreadcountNotification = Integer.parseInt(json.getString("unreadcount"));
                json = jParser.makeHttpRequest(Z.url_getplacementsmetadata, "GET", params);
                unreadcountPlacement = Integer.parseInt(json.getString("unreadcount"));


            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {
            placementcountrl.setVisibility(View.VISIBLE);
            placementcounttxt.setText(unreadcountPlacement + "");
            if (unreadcountPlacement == 0) {
                placementcountrl.setVisibility(View.GONE);
            }
            notificationcountrl.setVisibility(View.VISIBLE);
            notificationcounttxt.setText(unreadcountNotification + "");
            if (unreadcountNotification == 0) {
                notificationcountrl.setVisibility(View.GONE);
            }

            getNotifications();

        }
    }

    void getNotifications() {
        itemListNotificationNew.clear();
        tswipe_refresh_layout.setRefreshing(true);
        previousTotalNotification = 0;
        loadingNotification = true;
        page_to_call_notification = 1;
        isFirstRunNotification = true;
        isLastPageLoadedNotification = false;
        lastPageFlagNotification = 0;

        new GetNotificationsReadStatus().execute();
    }

    void getPlacements() {
        Log.d("pbacktrack", "getPlacements: accessed ");
        itemListPlacementnew.clear();

        tswipe_refresh_layout.setRefreshing(true);
        previousTotalPlacement = 0;
        loadingPlacement = true;
        page_to_call_placement = 1;
        isFirstRunPlacement = true;
        isLastPageLoadedPlacement = false;
        lastPageFlagPlacement = 0;
        new GetPlacementsReadStatus().execute();
    }

    private void simulateLoadingNotification() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                tswipe_refresh_layout.setRefreshing(true);
            }

            @Override
            protected Void doInBackground(Void... param) {
                try {


                    Log.d("TAG", "simulateLoadingNotification: accessed");
                    Log.d("TAG", "page_to_call_notification:" + page_to_call_notification);
                    Log.d("TAG", "notificationpages:" + notificationpages);

                    if (page_to_call_notification < notificationpages)
                        page_to_call_notification++;


                    if (page_to_call_notification != notificationpages) {

                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("u", username));       //0
                        params.add(new BasicNameValuePair("p", page_to_call_notification + ""));
                        json = jParser.makeHttpRequest(Z.url_getnotifications, "GET", params);

                        notificationcount = Integer.parseInt(json.getString("count"));

                        Log.d("json1", "jsonparamsList " + json.getString("jsonparamsList"));
                        itemlistfromserver = (ArrayList<RecyclerItemEdit>) fromString(json.getString("jsonparamsList"), "I09jdG9wdXMxMkl0ZXMjJQ==", "I1BsYWNlMTJNZSMlJSopXg==");
                        Log.d("itemlistfromserver", "reg=======================" + itemlistfromserver.size());


                    } else {
                        if (!isLastPageLoadedNotification) {

                            lastPageFlagNotification = 1;

                            List<NameValuePair> params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("u", username));       //0
                            params.add(new BasicNameValuePair("p", page_to_call_notification + ""));
                            json = jParser.makeHttpRequest(Z.url_getnotifications, "GET", params);

                            notificationcount = Integer.parseInt(json.getString("count"));

                            Log.d("json1", "jsonparamsList " + json.getString("jsonparamsList"));
                            itemlistfromserver = (ArrayList<RecyclerItemEdit>) fromString(json.getString("jsonparamsList"), "I09jdG9wdXMxMkl0ZXMjJQ==", "I1BsYWNlMTJNZSMlJSopXg==");
                            Log.d("itemlistfromserver", "reg=======================" + itemlistfromserver.size());
                        }

                    }

                } catch (Exception e) {

                }
                return null;
            }

            @Override
            protected void onPostExecute(Void param) {

                if (!isLastPageLoadedNotification) {

                    setserverlisttoadapter(itemlistfromserver);


                }


                tswipe_refresh_layout.setRefreshing(false);
//                new GetLastUpdatedNotification().execute();
            }
        }.execute();
    }

    private void simulateLoadingPlacement() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                tswipe_refresh_layout.setRefreshing(true);
            }

            @Override
            protected Void doInBackground(Void... param) {

                try {
                    if (page_to_call_placement < placementpages)
                        page_to_call_placement++;

                    if (page_to_call_placement != placementpages) {

                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("u", username));
                        params.add(new BasicNameValuePair("p", page_to_call_placement + ""));

                        json = jParser.makeHttpRequest(Z.url_getplacements, "GET", params);
                        try {

                            Log.d("json1", "placementlistfromserver " + json.getString("placementlistfromserver"));
                            placementListfromserver = (ArrayList<RecyclerItemPlacement>) fromString(json.getString("placementlistfromserver"), "I09jdG9wdXMxMkl0ZXMjJQ==", "I1BsYWNlMTJNZSMlJSopXg==");
                            Log.d("itemlistfromserver", "reg=======================" + placementListfromserver.size());
                            Log.d("itemlistfromserver", "getNotification1=======================" + placementListfromserver.get(0).getCompanyname());
                            Log.d("itemlistfromserver", "getNotification2=======================" + placementListfromserver.get(2).getDateofarrival());


                        } catch (Exception e) {
                        }


                    } else {
                        if (!isLastPageLoadedPlacement) {

                            lastPageFlagPlacement = 1;

                            List<NameValuePair> params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("u", username));
                            params.add(new BasicNameValuePair("p", page_to_call_placement + ""));

                            json = jParser.makeHttpRequest(Z.url_getplacements, "GET", params);
                            try {

                                Log.d("json1", "placementlistfromserver " + json.getString("placementlistfromserver"));
                                placementListfromserver = (ArrayList<RecyclerItemPlacement>) fromString(json.getString("placementlistfromserver"), "I09jdG9wdXMxMkl0ZXMjJQ==", "I1BsYWNlMTJNZSMlJSopXg==");
                                Log.d("itemlistfromserver", "reg=======================" + placementListfromserver.size());
                                Log.d("itemlistfromserver", "getNotification1=======================" + placementListfromserver.get(0).getCompanyname());
                                Log.d("itemlistfromserver", "getNotification2=======================" + placementListfromserver.get(2).getDateofarrival());


                            } catch (Exception e) {
                            }

                        }
                    }
                } catch (Exception e) {
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void param) {


                tswipe_refresh_layout.setVisibility(View.VISIBLE);
                tswipe_refresh_layout.setRefreshing(false);
                if (!isLastPageLoadedPlacement) {


                    setplacementListtoadapter(placementListfromserver);
                }
                tswipe_refresh_layout.setRefreshing(false);


            }
        }.execute();
    }

    public abstract class EndlessRecyclerOnScrollListenerNotification extends RecyclerView.OnScrollListener {


        private LinearLayoutManager mLinearLayoutManager;

        public EndlessRecyclerOnScrollListenerNotification(LinearLayoutManager linearLayoutManager) {
            this.mLinearLayoutManager = linearLayoutManager;
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            visibleItemCountNotification = recyclerView.getChildCount();
            totalItemCountNotification = mLinearLayoutManager.getItemCount();
            firstVisibleItemNotification = mLinearLayoutManager.findFirstVisibleItemPosition();

            if (loadingNotification) {
                if (totalItemCountNotification > previousTotalNotification) {
                    loadingNotification = false;
                    previousTotalNotification = totalItemCountNotification;
                }
            }
            if (!loadingNotification && (totalItemCountNotification - visibleItemCountNotification)
                    <= (firstVisibleItemNotification + visibleThresholdNotification)) {
                // End has been reached

                // Do something
                current_page_notification++;

                onLoadMore(current_page_notification);

                loadingNotification = true;
            }
        }

        public abstract void onLoadMore(int current_page);
    }

    public abstract class EndlessRecyclerOnScrollListenerPlacement extends RecyclerView.OnScrollListener {


        private LinearLayoutManager mLinearLayoutManager;

        public EndlessRecyclerOnScrollListenerPlacement(LinearLayoutManager linearLayoutManager) {
            this.mLinearLayoutManager = linearLayoutManager;
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            visibleItemCountPlacement = recyclerView.getChildCount();
            totalItemCountPlacement = mLinearLayoutManager.getItemCount();
            firstVisibleItemPlacement = mLinearLayoutManager.findFirstVisibleItemPosition();

            if (loadingPlacement) {
                if (totalItemCountPlacement > previousTotalPlacement) {
                    loadingPlacement = false;
                    previousTotalPlacement = totalItemCountPlacement;
                }
            }
            if (!loadingPlacement && (totalItemCountPlacement - visibleItemCountPlacement)
                    <= (firstVisibleItemPlacement + visibleThresholdPlacement)) {
                // End has been reached

                // Do something
                current_page_placement++;

                onLoadMore(current_page_placement);

                loadingPlacement = true;
            }
        }

        public abstract void onLoadMore(int current_page);
    }


    class GetPlacementsReadStatus extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0

            try {
                json = jParser.makeHttpRequest(Z.url_getplacementsmetadata, "GET", params);


                placementpages = Integer.parseInt(json.getString("pages"));
                called_pages_placement = new int[placementpages];
                total_no_of_placements = Integer.parseInt(json.getString("count"));
                unreadcountPlacement = Integer.parseInt(json.getString("unreadcount"));


            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {
            placementcountrl.setVisibility(View.VISIBLE);

            placementcounttxt.setText(unreadcountPlacement + "");
            if (unreadcountPlacement == 0) {
                placementcountrl.setVisibility(View.GONE);
            }


            new GetPlacements2().execute();


        }
    }

    class GetNotifications2 extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0
            params.add(new BasicNameValuePair("p", page_to_call_notification + ""));
            Log.d("class", "accessed");
            json = jParser.makeHttpRequest(Z.url_getnotifications, "GET", params);
            try {

                Log.d("json1", "jsonparamsList " + json.getString("jsonparamsList"));
                itemlistfromserver = (ArrayList<RecyclerItemEdit>) fromString(json.getString("jsonparamsList"), "I09jdG9wdXMxMkl0ZXMjJQ==", "I1BsYWNlMTJNZSMlJSopXg==");
                Log.d("itemlistfromserver", "reg=======================" + itemlistfromserver.size());
                Log.d("itemlistfromserver", "getNotification1=======================" + itemlistfromserver.get(0).getNotification());
                Log.d("itemlistfromserver", "getNotification2=======================" + itemlistfromserver.get(2).getNotification());


            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            setserverlisttoadapter(itemlistfromserver);

        }


    }



    class GetPlacements2 extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {
            String r = null;

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0
            params.add(new BasicNameValuePair("p", page_to_call_placement + ""));


            json = jParser.makeHttpRequest(Z.url_getplacements, "GET", params);
            try {

                Log.d("json1", "placementlistfromserver " + json.getString("placementlistfromserver"));
                placementListfromserver = (ArrayList<RecyclerItemPlacement>) fromString(json.getString("placementlistfromserver"), "I09jdG9wdXMxMkl0ZXMjJQ==", "I1BsYWNlMTJNZSMlJSopXg==");
                Log.d("itemlistfromserver", "reg=======================" + placementListfromserver.size());
                Log.d("itemlistfromserver", "getNotification1=======================" + placementListfromserver.get(0).getCompanyname());
                Log.d("itemlistfromserver", "getNotification2=======================" + placementListfromserver.get(2).getDateofarrival());


            } catch (Exception e) {
            }

            return r;
        }

        @Override
        protected void onPostExecute(String result) {


            setplacementListtoadapter(placementListfromserver);

        }
    }


    private void disableScrollbars(ScrollView scrollView) {
        if (scrollView != null) {

            scrollView.setVerticalScrollBarEnabled(false);

        }
    }

    void changeReadStatusNotification(String id) {
        new ChangeReadStatusNotification().execute(id);

    }

    void changeReadStatusPlacement(String id) {
        new ChangeReadStatusPlacement().execute(id);

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

    @Override
    public void onError(String s) {
        crop_layout.setVisibility(View.GONE);
        tswipe_refresh_layout.setVisibility(View.GONE);
        mainfragment.setVisibility(View.VISIBLE);
        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

    }

    class ChangeReadStatusNotification extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0
            params.add(new BasicNameValuePair("id", param[0]));       //0
            json = jParser.makeHttpRequest(Z.url_changenotificationsreadstatus, "GET", params);
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

        }
    }

    class ChangeReadStatusPlacement extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0
            params.add(new BasicNameValuePair("id", param[0]));       //0
            json = jParser.makeHttpRequest(Z.url_changeplacementsreadstatus, "GET", params);
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

        }
    }

    private void disableNavigationViewScrollbars(NavigationView navigationView) {
        if (navigationView != null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
            if (navigationMenuView != null) {
                navigationMenuView.setVerticalScrollBarEnabled(false);
            }
        }
    }

    @Override
    public void onBackPressed() {

        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                if (doubleBackToExitPressedOnce) {
                    super.onBackPressed();
                    return;
                }
                this.doubleBackToExitPressedOnce = true;
                Toast.makeText(this, "Press Back again to exit", Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    public void requestProfileImage() {
//        new GetProfileImage().execute();
        new Getsingnature().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    }

    public void requestCropImage() {
        resultView.setImageDrawable(null);

        MySharedPreferencesManager.save(MainActivity.this, "crop", "yes");
        chooseImage();


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {

        if (requestCode == Picker.PICK_IMAGE_DEVICE) {

            try {

                if (imagePicker == null) {
                    imagePicker = new ImagePicker(this);
                    imagePicker.setImagePickerCallback(this);
                }
                imagePicker.submit(result);
                crop_layout.setVisibility(View.VISIBLE);
                tswipe_refresh_layout.setVisibility(View.GONE);
                mainfragment.setVisibility(View.GONE);
                crop_flag = 1;
                beginCrop(result.getData());
                // Toast.makeText(this, "crop initiated", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                crop_layout.setVisibility(View.GONE);
                tswipe_refresh_layout.setVisibility(View.GONE);
                mainfragment.setVisibility(View.VISIBLE);
            }
        } else if (resultCode == RESULT_CANCELED) {
            crop_layout.setVisibility(View.GONE);
            tswipe_refresh_layout.setVisibility(View.GONE);
            mainfragment.setVisibility(View.VISIBLE);
            crop_flag = 0;
        } else if (requestCode == Crop.REQUEST_CROP) {
            // Toast.makeText(this, "cropped", Toast.LENGTH_SHORT).show();
            handleCrop(resultCode, result);
        }

        if (resultCode == STUDENT_DATA_CHANGE_RESULT_CODE) {
            Log.d("TAG", "onActivityResult: personal save");
            MyProfileFragment fragment = (MyProfileFragment) getSupportFragmentManager().findFragmentById(R.id.mainfragment);
            fragment.refreshContent();
        }

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
            tswipe_refresh_layout.setVisibility(View.GONE);
            mainfragment.setVisibility(View.VISIBLE);
            MyProfileFragment fragment = (MyProfileFragment) getSupportFragmentManager().findFragmentById(R.id.mainfragment);
            fragment.showUpdateProgress();
//            new UploadProfile().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new CompressTask().execute();

        } else if (resultCode == Crop.RESULT_ERROR) {
            crop_layout.setVisibility(View.GONE);
            tswipe_refresh_layout.setVisibility(View.GONE);
            mainfragment.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Try Again..!", Toast.LENGTH_SHORT).show();

        }
    }



    private void chooseImage() {

        imagePicker.pickImage();
    }


//    public class GetProfileImage extends AsyncTask<String, Void, Bitmap> {
//        @Override
//        protected Bitmap doInBackground(String... urls) {
//            Bitmap map = null;
//
//            return map;
//        }
//        @Override
//        protected void onPostExecute(Bitmap result) {
//
//            downloadImage();
//        }
//
//    }

    class Getsingnature extends AsyncTask<String, String, String> {
        String signature = "";

        protected String doInBackground(String... param) {
            JSONParser jParser = new JSONParser();
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));
            JSONObject json = jParser.makeHttpRequest(Z.load_last_updated, "GET", params);
            Log.d("TAG", "doInBackground: Getsingnature json " + json);
            try {
                signature = json.getString("lastupdated");
            } catch (Exception ex) {
            }
            return signature;
        }

        @Override
        protected void onPostExecute(String result) {

            Log.d("TAG", "downloadImage signature : " + signature);
            Log.d("TAG", "downloadImage: GetImage username " + username);
            Uri uri = new Uri.Builder()
                    .scheme("http")
                    .authority(Z.VPS_IP)
                    .path("AESTest/GetImage")
                    .appendQueryParameter("u", username)
                    .build();

            Glide.with(MainActivity.this)
                    .load(uri)
                    .signature(new StringSignature(signature))
                    .into(profile);

        }
    }

    class CompressTask extends AsyncTask<String, String, Boolean> {
        protected Boolean doInBackground(String... param) {
            File sourceFile = new File(filepath);
            try {
                Log.d("TAG", "before compress :   " + sourceFile.length() / 1024 + " kb");
            } catch (Exception e) {}
            Luban.compress(MainActivity.this, sourceFile)
                    .setMaxSize(256)                // limit the final image size（unit：Kb）
                    .putGear(Luban.CUSTOM_GEAR)
                    .launch(new OnCompressListener() {
                        @Override
                        public void onStart() {
                        }
                        @Override
                        public void onSuccess(File file) {
                            try {
                                Log.d("TAG", "After compress :   " + file.length() / 1024 + " kb");
                            } catch (Exception e) {
                            }
                            if (file.exists()) {
                                String filepath = file.getAbsolutePath();
                                String filename = "";
                                int index = filepath.lastIndexOf("/");
                                directory = "";
                                for (int i = 0; i < index; i++)
                                    directory += filepath.charAt(i);
                                for (int i = index + 1; i < filepath.length(); i++)
                                    filename += filepath.charAt(i);
                                Log.d("TAG", "before : f name- " + filename);
                                Imgfile = file;


                                    new UploadProfile().execute();

                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d("TAG", "onError: "+e.getMessage());
                        }
                    });
            return true;
        }
    }

    class UploadProfile extends AsyncTask<String, String, Boolean> {
        protected Boolean doInBackground(String... param) {

            if (Imgfile != null) {

                MultipartUtility multipart = null;
                try {
                    multipart = new MultipartUtility(Z.upload_profile, "UTF-8");
                    Log.d("TAG", "UploadProfile : input  username " + username);
                    multipart.addFormField("u", username);
                    if (filename != "") {
                        multipart.addFormField("f", filename);
                        multipart.addFilePart("uf", Imgfile);
                        Log.d("TAG", "onSuccess: f name- " + filename);
                    } else
                        multipart.addFormField("f", "null");
                    response = multipart.finish();

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("TAG", "exp : " + e.getMessage());

                }

            } else
                return false;

            return true;

        }

        @Override
        protected void onPostExecute(Boolean result) {

            MyProfileFragment fragment = (MyProfileFragment) getSupportFragmentManager().findFragmentById(R.id.mainfragment);
            crop_layout.setVisibility(View.GONE);
            tswipe_refresh_layout.setVisibility(View.GONE);
            mainfragment.setVisibility(View.VISIBLE);

            if(result) {
                if (response != null && response.get(0).contains("success")) {
                    MySharedPreferencesManager.save(MainActivity.this, "crop", "no");
                    requestProfileImage();
                    fragment.downloadImage();
                    Toast.makeText(MainActivity.this, "Successfully Updated..!", Toast.LENGTH_SHORT).show();
                    DeleteRecursive(new File(directory));
                } else if (response != null && response.get(0).contains("null")) {
                    requestProfileImage();
                    fragment.refreshContent();
                    Toast.makeText(MainActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, Z.FAIL_TO_UPLOAD_IMAGE, Toast.LENGTH_SHORT).show();
                    fragment.HideUpdateProgress();
                }

            }else {
                Toast.makeText(MainActivity.this, Z.FAIL_TO_UPLOAD_IMAGE, Toast.LENGTH_SHORT).show();
                fragment.HideUpdateProgress();
            }

        }

        void DeleteRecursive(File fileOrDirectory) {

            if (fileOrDirectory.isDirectory())
                for (File child : fileOrDirectory.listFiles())
                    DeleteRecursive(child);

            if (fileOrDirectory.delete())
                Log.d("TAG", "deleted : ");

        }

    }

    class LoginFirebaseTask extends AsyncTask<String, String, String> {
        protected String doInBackground(String... param) {
            String user = param[0];
            String hash = param[1];
            FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(user, hash)
                    .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                MySharedPreferencesManager.save(MainActivity.this, "fireLoginStatus", "Successfully logged in to Firebase");
                            } else {
                                MySharedPreferencesManager.save(MainActivity.this, "fireLoginStatus", "Failed to login to Firebase");
                            }
                        }
                    });
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            String status = MySharedPreferencesManager.getData(MainActivity.this, "fireLoginStatus");
            Toast.makeText(MainActivity.this, status, Toast.LENGTH_SHORT).show();
            // remove value from shared
            MySharedPreferencesManager.removeKey(MainActivity.this, "fireLoginStatus");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter("pushNotificationChat"));

    }

    @Override
    public void onPause() {
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }
    class UpdateFirebaseToken extends AsyncTask<String, String, String> {

        // TODO move UpdateFirebaseToken code to all base activity
        // TODO update AID,DID
        JSONObject json;
        JSONParser jParser = new JSONParser();
        String resultofop = null;

        protected String doInBackground(String... param) {
            try {

                String encUsername = MySharedPreferencesManager.getUsername(getApplicationContext());
                String token = new SharedPrefUtil(getApplicationContext()).getString("firebaseToken");
                Log.d("TAG", "mainactivity token\n" + token);

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("u", encUsername));       //0
                params.add(new BasicNameValuePair("t", token));             //1
                json = jParser.makeHttpRequest(Z.url_UpdateFirebaseToken, "GET", params);


                resultofop = json.getString("info");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
//            if (resultofop.equals("success")) {
//                Log.d("TAG_FIRE_IDService", "Successfully Updated token..!");
//            }
        }
    }


    class GetNotificationsReadStatus extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0

            try {

                json = jParser.makeHttpRequest(Z.url_getnotificationsmetadata, "GET", params);

                notificationpages = Integer.parseInt(json.getString("pages"));
                called_pages_notification = new int[notificationpages];
                total_no_of_notifications = Integer.parseInt(json.getString("count"));
                unreadcountNotification = Integer.parseInt(json.getString("unreadcount"));

                Log.d("FinaltestN", "notificationpages: " + notificationpages);
                Log.d("FinaltestN", "total_no_of_notifications: " + total_no_of_notifications);
                Log.d("FinaltestN", "unreadcountNotification: " + unreadcountNotification);

//
            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                notificationcountrl.setVisibility(View.VISIBLE);
                notificationcounttxt.setText(unreadcountNotification + "");
                if (unreadcountNotification == 0) {
                    notificationcountrl.setVisibility(View.GONE);
                }


                new GetNotifications2().execute();


            } catch (Exception e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }


        }
    }


    void setserverlisttoadapter(ArrayList<RecyclerItemEdit> itemlist) {

        itemListNotificationNew.addAll(itemlist);
        Log.d("tag2", "itemListNotificationNew size ===========" + itemListNotificationNew.size());

        if (lastPageFlagNotification == 1)
            isLastPageLoadedNotification = true;

        mAdapterNotificationEdit.notifyDataSetChanged();
        tswipe_refresh_layout.setVisibility(View.VISIBLE);
        tswipe_refresh_layout.setRefreshing(false);

        Log.d("tag2", "mAdapterNotificationEdit itemcount ===========" + mAdapterNotificationEdit.getItemCount());

    }

    private void setplacementListtoadapter(ArrayList<RecyclerItemPlacement> itemList2) {

        Log.d("tag2", "itemListPlacement size ===========" + itemListPlacementnew.size());

        if (lastPageFlagPlacement == 1)
            isLastPageLoadedPlacement = true;
        itemListPlacementnew.addAll(itemList2);

        mAdapterPlacement.notifyDataSetChanged();
        tswipe_refresh_layout.setVisibility(View.VISIBLE);
        tswipe_refresh_layout.setRefreshing(false);


        Log.d("tag2", "itemcount size ===========" + mAdapterPlacement.getItemCount());


    }


}
