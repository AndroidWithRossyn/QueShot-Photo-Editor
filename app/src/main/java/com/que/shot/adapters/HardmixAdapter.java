package com.que.shot.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.github.siyamed.shapeimageview.RoundedImageView;
import com.que.shot.R;
import com.que.shot.assets.EffectCodeAsset;
import com.que.shot.listener.HardmixListener;

import java.util.List;

public class HardmixAdapter extends RecyclerView.Adapter<HardmixAdapter.ViewHolder> {
    private List<Bitmap> bitmaps;
    private Context context;
    public List<EffectCodeAsset.EffectsCode> effectsCodeList;
    public HardmixListener hardmixListener;
    public int selectIndex = 0;

    public HardmixAdapter(List<Bitmap> bitmapList, HardmixListener hardmixListener, Context mContext, List<EffectCodeAsset.EffectsCode> effectsCodeList) {
        this.hardmixListener = hardmixListener;
        this.bitmaps = bitmapList;
        this.context = mContext;
        this.effectsCodeList = effectsCodeList;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_overlay, viewGroup, false));
    }

    public void reset() {
        this.selectIndex = 0;
        notifyDataSetChanged();
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.text_view_filter_name.setText(this.effectsCodeList.get(i).getName());
        viewHolder.text_view_filter_name.setTextColor(ContextCompat.getColor(context, R.color.itemColorBlack));
        viewHolder.round_image_view_filter_item.setImageBitmap(this.bitmaps.get(i));
        if (this.selectIndex == i) {
            viewHolder.text_view_filter_name.setTextColor(ContextCompat.getColor(context, R.color.itemColorBlack));
            viewHolder.viewSelected.setVisibility(View.VISIBLE);
            return;
        }
        viewHolder.text_view_filter_name.setTextColor(ContextCompat.getColor(context, R.color.itemColorBlack));
        viewHolder.viewSelected.setVisibility(View.GONE);

    }

    public int getItemCount() {
        return this.bitmaps.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView round_image_view_filter_item;
        TextView text_view_filter_name;
        View viewSelected;

        ViewHolder(View view) {
            super(view);
            this.round_image_view_filter_item = view.findViewById(R.id.round_image_view_filter_item);
            this.text_view_filter_name = view.findViewById(R.id.text_view_filter_name);
            this.viewSelected = view.findViewById(R.id.view_selected);
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    HardmixAdapter.this.selectIndex = ViewHolder.this.getLayoutPosition();
                    HardmixAdapter.this.hardmixListener.onFilterSelected(((EffectCodeAsset.EffectsCode) HardmixAdapter.this.effectsCodeList.get(HardmixAdapter.this.selectIndex)).getImage());
                    HardmixAdapter.this.notifyDataSetChanged();
                }
            });
        }
    }
}
