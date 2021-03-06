package id.merahmuda.bcd.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import id.merahmuda.bcd.R;
import id.merahmuda.bcd.activity.DetailTenantActivity;
import id.merahmuda.bcd.model.Tenant;

/**
 * Created by Ujang Wahyu on 03,Oktober,2018
 */
public class TenantAdapter extends RecyclerView.Adapter<TenantAdapter.MyViewHolder> {

    private Context mContext;
    private List<Tenant> tenantList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNama,txtLokasi, txtDeskripsi;
        private ImageView imgCover;
        private AVLoadingIndicatorView loading;

        public MyViewHolder(View view) {
            super(view);
            txtNama = view.findViewById(R.id.txt_nama);
            txtLokasi = view.findViewById(R.id.txt_lokasi);
            txtDeskripsi = view.findViewById(R.id.txt_deskripsi);
            imgCover = view.findViewById(R.id.img_cover);
            loading = view.findViewById(R.id.loading);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, DetailTenantActivity.class);
                    mContext.startActivity(intent);
                }
            });

        }
    }

    public TenantAdapter(Context mContext, List<Tenant> tenants) {
        this.mContext = mContext;
        this.tenantList = tenants;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_tenant, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Tenant tenant = tenantList.get(position);
        holder.txtNama.setText(tenant.getName());
        holder.txtLokasi.setText(tenant.getLokasi());
        holder.txtDeskripsi.setText(tenant.getDeskripsi().substring(0,40));

        Glide.with(mContext)
                .load(tenant.getCover())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.loading.hide();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.loading.hide();
                        return false;
                    }
                })
                .into(holder.imgCover);
    }

    @Override
    public int getItemCount() {
        return tenantList.size();
    }
}
