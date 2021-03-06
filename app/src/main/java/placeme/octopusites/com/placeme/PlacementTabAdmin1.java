package placeme.octopusites.com.placeme;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


public class PlacementTabAdmin1 extends Fragment {



    TextView companynameview,cpackageview,lastdateofregview,postview,vacanciesview,bondview,dateofarrivalview;
    TextView companynametxt,posttxt,packagetxt,vacancytxt,lastdateofregtxt,bondtxt,dateofarrtxt;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tabadmin1, container, false);


        companynameview=(TextView)rootView.findViewById(R.id.companynameview);
        lastdateofregview=(TextView)rootView.findViewById(R.id.companylastdateofregview);
        postview=(TextView)rootView.findViewById(R.id.companypostview);
        vacanciesview=(TextView)rootView.findViewById(R.id.companyvacanciesview);
        bondview=(TextView)rootView.findViewById(R.id.companybondview);
        dateofarrivalview=(TextView)rootView.findViewById(R.id.companydateofarrview);
        cpackageview=(TextView)rootView.findViewById(R.id.companypackageview);

        companynametxt=(TextView)rootView.findViewById(R.id.companynametxt);
        posttxt=(TextView)rootView.findViewById(R.id.posttxt);
        packagetxt=(TextView)rootView.findViewById(R.id.packagetxt);
        vacancytxt=(TextView)rootView.findViewById(R.id.vacancytxt);
        lastdateofregtxt=(TextView)rootView.findViewById(R.id.lastdateofregtxt);
        bondtxt=(TextView)rootView.findViewById(R.id.bondtxt);
        dateofarrtxt=(TextView)rootView.findViewById(R.id.dateofarrtxt);

        companynametxt.setTypeface(Z.getLight(getActivity()));
        posttxt.setTypeface(Z.getLight(getActivity()));
        packagetxt.setTypeface(Z.getLight(getActivity()));
        vacancytxt.setTypeface(Z.getLight(getActivity()));
        lastdateofregtxt.setTypeface(Z.getLight(getActivity()));
        bondtxt.setTypeface(Z.getLight(getActivity()));
        dateofarrtxt.setTypeface(Z.getLight(getActivity()));

        companynameview.setTypeface(Z.getBold(getActivity()));
        lastdateofregview.setTypeface(Z.getBold(getActivity()));
        postview.setTypeface(Z.getBold(getActivity()));
        vacanciesview.setTypeface(Z.getBold(getActivity()));
        bondview.setTypeface(Z.getBold(getActivity()));
        dateofarrivalview.setTypeface(Z.getBold(getActivity()));
        cpackageview.setTypeface(Z.getBold(getActivity()));




        return rootView;
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
}
