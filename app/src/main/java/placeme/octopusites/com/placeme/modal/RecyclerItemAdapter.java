package placeme.octopusites.com.placeme.modal;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;

import java.text.Normalizer;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import placeme.octopusites.com.placeme.R;
import placeme.octopusites.com.placeme.Z;

import static placeme.octopusites.com.placeme.AES4all.Decrypt;


public class RecyclerItemAdapter extends RecyclerView.Adapter<RecyclerItemAdapter.MyViewHolder> {

    private List<RecyclerItem> itemList;
    private String searchText;

    public class MyViewHolder extends RecyclerView.ViewHolder {
    public CircleImageView uploadedbyprofile;
    public TextView title, notification, uploadtime;
    public ImageView imageView,logo;
        public ImageView imageView2;



        public MyViewHolder(View view) {
            super(view);
            uploadedbyprofile=(CircleImageView)view.findViewById(R.id.uploadedbyprofile);
            title = (TextView) view.findViewById(R.id.title);
            notification = (TextView) view.findViewById(R.id.notification);
            uploadtime = (TextView) view.findViewById(R.id.uploadtime);
            imageView=(ImageView)view.findViewById(R.id.attachment);
            logo =( CircleImageView)view.findViewById(R.id.logo);



        }
    }

    public void updateList(List<RecyclerItem> list,String searchText){
        itemList = list;
        this.searchText = searchText;
        notifyDataSetChanged();
    }

    public RecyclerItemAdapter(List<RecyclerItem> itemList) {
        this.itemList = itemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        RecyclerItem item = itemList.get(position);



        Uri uri = new Uri.Builder()
                .scheme("https")
                .authority(Z.VPS_IP)
                .path("AESTest/GetImageThumbnail")
                .appendQueryParameter("u", item.getUploadedby())
                .build();


        Glide.with(item.getContext())
                .load(uri)
                .signature(new StringSignature(item.getSignature()))
                .into(holder.uploadedbyprofile);



        if(searchText!=null) {
            if (searchText.length() > 0) {
                holder.title.setText(highlightText(searchText,item.getTitleToShow()));
            }
            else
                holder.title.setText(item.getTitleToShow());
        }
        else
            holder.title.setText(item.getTitleToShow());


        holder.notification.setText(item.getNotificationToShow());
        holder.uploadtime.setText(item.getUploadtime());
        if(item.getisAttachment())
        {
            Drawable myDrawable = item.getContext().getResources().getDrawable(R.drawable.attachment_icon);
            holder.imageView.setImageDrawable(myDrawable);

        }
        else if(!item.getisAttachment())
        {
            holder.imageView.setImageBitmap(null);
        }
        if(item.getisRead())
        {
            holder.title.setTextColor(Color.parseColor("#eeeeee"));
            holder.title.setTypeface(null, Typeface.NORMAL);

        }
        else if(!item.getisRead())
        {
            holder.title.setTextColor(Color.parseColor("#c59a6d"));
            holder.title.setTypeface(null, Typeface.BOLD);

        }


        //
        try {
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

