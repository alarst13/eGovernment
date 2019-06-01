package com.example.egovernment.Library;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.egovernment.DatabaseAccess;
import com.example.egovernment.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.provider.CalendarContract.CalendarCache.URI;

public class LibraryRecyclerViewAdapter extends RecyclerView.Adapter<LibraryRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private Context mContext;

    public LibraryRecyclerViewAdapter(Context context, ArrayList<String> imageNames, ArrayList<String> images) {
        mImageNames = imageNames;
        mImages = images;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_library_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.image);

        holder.imageName.setText(mImageNames.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DatabaseAccess databaseAccess = new DatabaseAccess(view.getContext());
                List<PDF> pdfs = new ArrayList<>();
                Cursor c = databaseAccess.getDb().rawQuery("SELECT * FROM PDF", null);
                c.moveToFirst();
                while (!c.isAfterLast()) {
                    String pdf_name = c.getString(0);
                    String image_url = c.getString(1);
                    String pdf_url = c.getString(2);
                    PDF pdf = new PDF(pdf_name, image_url, pdf_url);
                    pdfs.add(pdf);
                    c.moveToNext();
                }
                if (holder.imageName.getText().equals(pdfs.get(position).getPdf_name())) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(pdfs.get(position).getPdf_url()));
                    view.getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView imageName;
        CardView parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.image_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
