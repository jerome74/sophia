package indra.com.icertify.services.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import indra.com.icertify.R;
import indra.com.icertify.activities.ricerca_certificazioni.DettaglioCertificazioneActivity;
import indra.com.icertify.services.models.ricerca_certificazioni.Item;


/**
 * Created by adifrancesco on 21/10/2017.
 */

public class ListCertificazioniAdapter extends BaseAdapter
{
    private final Context mContext;
    private List<Item> certItems;
    private int count = 0;

    static class ViewHolder
    {
        public Item item;
        public ImageView image;
        public TextView textCert;
        public TextView dataCert;
    }

    public ListCertificazioniAdapter(Context context, List<Item> certItems)
    {
        this.mContext = context;
        if( certItems != null )
        {
            this.certItems = certItems;
        }
        else
        {
            this.certItems = new ArrayList<>();
        }
    }


    @Override
    public int getCount()
    {
        return certItems.size();
    }


    @Override
    public Object getItem(int position)
    {
        return certItems.get(position);
    }


    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View rowView = convertView;
        // reuse views
        if ( rowView == null )
        {
            rowView = LayoutInflater.from(mContext).inflate(R.layout.risultato_ricerca_certificazioni_cell_layout, parent, false);

            // configure view holder
            ViewHolder viewHolder = new ViewHolder();

            viewHolder.textCert = (TextView) rowView.findViewById(R.id.id_nome_certificazione);
            rowView.setTag(viewHolder);

            rowView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    ViewHolder vhldr = (ViewHolder)view.getTag();

                    Intent intent = new Intent(mContext, DettaglioCertificazioneActivity.class);
                    intent.putExtra("ITEM_CERT_DETT", vhldr.item);
                    mContext.startActivity(intent);

                    //Toast.makeText(mContext, "Questo è un elemento della lista (" + vhldr.textCert.getText() + ")", Toast.LENGTH_SHORT).show();
                }
            });
        }

        // fill data

        ViewHolder holder = (ViewHolder) rowView.getTag();

        Item item = certItems.get(position);

        holder.textCert.setText(item.getOwner().getDisplayName());
        holder.item = item;

        return rowView;
    }

    /**
     *
     * @param items
     */
    public void updateAnswers(List<Item> items)
    {
        certItems = items;
        notifyDataSetChanged();
    }
}





/*
public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.ViewHolder> {

    private List<Item> mItems;
    private Context mContext;
    private PostItemListener mItemListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView titleTv;
        public ImageView imageCert;
        PostItemListener mItemListener;

        public ViewHolder(View itemView, PostItemListener postItemListener) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(android.R.id.text1);
            imageCert = (ImageView) itemView.findViewById()


            this.mItemListener = postItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Item item = getItem(getAdapterPosition());
            this.mItemListener.onPostClick(item.getAnswerId());

            notifyDataSetChanged();
        }
    }

    public AnswersAdapter(Context context, List<Item> posts, PostItemListener itemListener) {
        mItems = posts;
        mContext = context;
        mItemListener = itemListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.risultato_ricerca_certificazioni_cell_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(postView, this.mItemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Item item = mItems.get(position);
        TextView textView = holder.titleTv;
        textView.setText(item.getOwner().getDisplayName());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updateAnswers(List<Item> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    private Item getItem(int adapterPosition) {
        return mItems.get(adapterPosition);
    }

    public interface PostItemListener {
        void onPostClick(long id);
    }
}*/
