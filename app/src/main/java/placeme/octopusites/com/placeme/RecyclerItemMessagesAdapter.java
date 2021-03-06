package placeme.octopusites.com.placeme;

import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;

import java.text.DateFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class RecyclerItemMessagesAdapter extends RecyclerView.Adapter<RecyclerItemMessagesAdapter.MyViewHolder> {

    private List<RecyclerItemMessages> itemList;
    private String searchText;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView profile;
        public TextView name, lastmessage, time, date, unreadmessagecount, username;
        public RelativeLayout unreadmessagecountrl;

        public MyViewHolder(View view) {
            super(view);
            profile = (CircleImageView) view.findViewById(R.id.profile);
            name = (TextView) view.findViewById(R.id.name);
            lastmessage = (TextView) view.findViewById(R.id.lastmessage);
            time = (TextView) view.findViewById(R.id.time);
            date = (TextView) view.findViewById(R.id.date);
            unreadmessagecount = (TextView) view.findViewById(R.id.unreadmessagecount);
            unreadmessagecountrl = (RelativeLayout) view.findViewById(R.id.unreadmessagecountrl);
            username = (TextView) view.findViewById(R.id.username);



        }
    }

    public void updateList(List<RecyclerItemMessages> list, String searchText) {
        itemList = list;
        this.searchText = searchText;
        notifyDataSetChanged();
    }

    public RecyclerItemMessagesAdapter(List<RecyclerItemMessages> itemList) {
        this.itemList = itemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_list_message, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        RecyclerItemMessages item = itemList.get(position);

        Uri uri = new Uri.Builder()
                .scheme("https")
                .authority(Z.VPS_IP)
                .path("AESTest/GetImageThumbnail")
                .appendQueryParameter("u", item.getUploadedby())
                .build();

        if (item.getSignature() != null)
            Glide.with(holder.profile.getContext())
                    .load(uri)
                    .signature(new StringSignature(item.getSignature()))
                    .into(holder.profile);
        else
            Glide.with(holder.profile.getContext())
                    .load(uri)
                    .signature(new StringSignature("Place Me"))
                    .into(holder.profile);


        holder.name.setText(item.getFname() + " " + item.getLname());
        holder.username.setText(item.getUsername());
        holder.name.setTypeface(Z.getBold(holder.profile.getContext()));


        if (searchText != null) {
            if (searchText.length() > 0) {
                holder.name.setText(highlightText(searchText, item.getFname() + " " + item.getLname()));
            } else
                holder.name.setText(item.getFname() + " " + item.getLname());
        } else
            holder.name.setText(item.getFname() + " " + item.getLname());


        holder.lastmessage.setText(item.getLastmessage());
        holder.lastmessage.setTypeface(Z.getLight(holder.profile.getContext()));
        if (item.getUnreadcount() != null) {
            if (item.getUnreadcount().length() > 0) {
                int unreadcount = Integer.parseInt(item.getUnreadcount());
                if (unreadcount == 0) {
                    holder.unreadmessagecountrl.setVisibility(View.GONE);
                    holder.name.setTextColor(Color.parseColor("#03353e"));

                } else {
                    holder.unreadmessagecountrl.setVisibility(View.VISIBLE);
                    holder.unreadmessagecount.setText("" + unreadcount);
                    holder.name.setTextColor(Color.parseColor("#00bcd4"));

                }
            } else {
                holder.unreadmessagecountrl.setVisibility(View.GONE);
                holder.name.setTextColor(Color.parseColor("#03353e"));

            }
        } else {
            holder.unreadmessagecountrl.setVisibility(View.GONE);
            holder.name.setTextColor(Color.parseColor("#03353e"));

        }


        String time = item.getTime();
        if (time != null) {
            if (time.length() > 3) {
                DateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                DateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
                DateFormat sdf2 = new SimpleDateFormat("hh:mm a");
                try {
                    Date date0 = sdf.parse(time);
                    String date1 = sdf1.format(date0);
                    String time1 = sdf2.format(date0);
                    holder.date.setText("" + date1);
                    holder.time.setText("" + time1);
                    holder.date.setTypeface(Z.getLight(holder.profile.getContext()));
                    holder.time.setTypeface(Z.getLight(holder.profile.getContext()));
                } catch (Exception e) {
                }
            }
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


