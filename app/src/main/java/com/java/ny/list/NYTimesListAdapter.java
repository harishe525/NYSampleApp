package com.java.ny.list;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.java.ny.R;
import com.java.ny.data.model.NYTimesVO;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NYTimesListAdapter extends RecyclerView.Adapter<NYTimesListAdapter.RepoViewHolder>{

    private NYTimesSelectedListener repoSelectedListener;
    private final List<NYTimesVO> data = new ArrayList<>();

    NYTimesListAdapter(ListViewModel viewModel, LifecycleOwner lifecycleOwner, NYTimesSelectedListener repoSelectedListener) {
        this.repoSelectedListener = repoSelectedListener;
        viewModel.getNYTimes().observe(lifecycleOwner, repos -> {
            data.clear();
            if (repos != null) {
                data.addAll(repos.getNyTimesVOS());
                notifyDataSetChanged();
            }
        });
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_times_list_item, parent, false);
        return new RepoViewHolder(view, repoSelectedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).id;
    }

    static final class RepoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_times_name)
        TextView repoNameTextView;
        @BindView(R.id.tv_times_byname)
        TextView repoDescriptionTextView;
        @BindView(R.id.tv_times_date)
        TextView forksTextView;

        private NYTimesVO timesVO;

        RepoViewHolder(View itemView, NYTimesSelectedListener repoSelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if(timesVO != null) {
                    repoSelectedListener.onNYTimesSelected(timesVO);
                }
            });
        }

        void bind(NYTimesVO timesVO) {
            this.timesVO = timesVO;
            repoNameTextView.setText(timesVO.title);
            repoDescriptionTextView.setText(timesVO.byline);
            forksTextView.setText(String.valueOf(timesVO.published_date));
        }
    }
}
