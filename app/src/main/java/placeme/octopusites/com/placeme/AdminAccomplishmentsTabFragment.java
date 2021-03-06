package placeme.octopusites.com.placeme;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class AdminAccomplishmentsTabFragment extends Fragment {

    View knownlang,skills,honors,patents,publications;
    String username="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_edit_profile_admin_accomplishments, container, false);


        TextView knownlangtxt=(TextView)rootView.findViewById(R.id.knownlangtxt);
        TextView skillstxt=(TextView)rootView.findViewById(R.id.skillstxt);
        TextView honortxt=(TextView)rootView.findViewById(R.id.honortxt);
        TextView patenttxt=(TextView)rootView.findViewById(R.id.patenttxt);
        TextView publicationtxt=(TextView)rootView.findViewById(R.id.publicationtxt);

        knownlangtxt.setTypeface(Z.getBold(getActivity()));
        skillstxt.setTypeface(Z.getBold(getActivity()));
        honortxt.setTypeface(Z.getBold(getActivity()));
        patenttxt.setTypeface(Z.getBold(getActivity()));
        publicationtxt.setTypeface(Z.getBold(getActivity()));

        knownlang=(View)rootView.findViewById(R.id.studentBlock);
        skills=(View)rootView.findViewById(R.id.alumniBlock);
        honors=(View)rootView.findViewById(R.id.adminBlock);
        patents=(View)rootView.findViewById(R.id.patentbutton);
        publications=(View)rootView.findViewById(R.id.publicationbutton);

        knownlang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfileKnownLang.class).putExtra("username",username),0);
            }
        });

        skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfileSkills.class).putExtra("username",username),0);
            }
        });
        honors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfileAchievements.class).putExtra("username",username),0);
            }
        });
        patents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfilePatents.class).putExtra("username",username),0);
            }
        });
        publications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfilePublications.class).putExtra("username",username),0);

            }
        });



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