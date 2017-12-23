package placeme.octopusites.com.placeme;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.signature.ObjectKey;

import java.text.Normalizer;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static placeme.octopusites.com.placeme.AES4all.Decrypt;


public class RecyclerItemAdapterPlacement extends RecyclerView.Adapter<RecyclerItemAdapterPlacement.MyViewHolder> {

    private List<RecyclerItemPlacement> itemList;
    private String searchText;
    Context mContext;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView uploadedbyprofile,logo;
        public TextView companyname, lastdateofreg, cpackage,post;

        public MyViewHolder(View view) {
            super(view);
            uploadedbyprofile=(CircleImageView)view.findViewById(R.id.uploadedbyprofile);
            companyname = (TextView) view.findViewById(R.id.companyname);
            lastdateofreg = (TextView) view.findViewById(R.id.lastdateofreg);
            cpackage = (TextView) view.findViewById(R.id.cpackage);
            post=(TextView)view.findViewById(R.id.post);
            logo =( CircleImageView)view.findViewById(R.id.logo);



        }
    }
    public void updateList(List<RecyclerItemPlacement> list,String searchText){
        itemList = list;
        this.searchText = searchText;
        notifyDataSetChanged();
    }

    public RecyclerItemAdapterPlacement(List<RecyclerItemPlacement> itemList,Context mContext) {
        this.itemList = itemList;
        this.mContext=mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_list_row_placements, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        RecyclerItemPlacement item = itemList.get(position);

        Uri uri = new Uri.Builder()
                .scheme("http")
                .authority(Z.VPS_IP)
                .path("AESTest/GetImageThumbnail")
                .appendQueryParameter("u", item.getUploadedby())
                .build();

        GlideApp.with(mContext)
                .load(uri)
                .signature(new ObjectKey(item.getSignature()))
                .into(holder.uploadedbyprofile);


        if(searchText!=null) {
            if (searchText.length() > 0) {
                holder.companyname.setText(highlightText(searchText,item.getCompanyname()));
            }
            else
                holder.companyname.setText(item.getCompanyname());
        }
        else
            holder.companyname.setText(item.getCompanyname());

        holder.lastdateofreg.setText(item.getLastdateofregistration());
        holder.cpackage.setText(item.getCpackage());
        holder.post.setText(item.getPost());

        if(item.isIsread())
        {
            holder.companyname.setTextColor(Color.parseColor("#eeeeee"));
            holder.companyname.setTypeface(null, Typeface.NORMAL);

        }
        else if(!item.isIsread())
        {
            holder.companyname.setTextColor(Color.parseColor("#c59a6d"));
            holder.companyname.setTypeface(null, Typeface.BOLD);

        }


        try {
            Log.d("uploadedbysfdsdf", ": "+item.getUploadedby());
            String s1=item.getUploadedby();
            String s1Plain= Decrypt(s1,"I09jdG9wdXMxMkl0ZXMjJQ==","I1BsYWNlMTJNZSMlJSopXg==");
            if(  s1Plain.equals("sandipsurvase1993@gmail.com")   ){
                holder.logo.setVisibility(View.VISIBLE);
            }else {
                holder.logo.setVisibility(View.INVISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }






    }
    public static CharSequence highlightText(String search, String originalText) {
        if (search != null && !search.equalsIgnoreCase("")) {
            String normalizedText = Normalizer.normalize(originalText, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
            int start = normalizedText.indexOf(search);
            if (start < 0) {
                return originalText;
            } else {
                Spannable highlighted = new SpannableString(originalText);
                while (start >= 0) {
                    int spanStart = Math.min(start, originalText.length());
                    int spanEnd = Math.min(start + search.length(), originalText.length());
                    highlighted.setSpan(new ForegroundColorSpan(Color.parseColor("#4169e1")), spanStart, spanEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    start = normalizedText.indexOf(search, spanEnd);
                }
                return highlighted;
            }
        }
        return originalText;
    }
    @Override
    public int getItemCount() {
        return itemList.size();
    }


}

