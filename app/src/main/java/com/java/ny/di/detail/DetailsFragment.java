package com.java.ny.di.detail;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import com.java.ny.R;
import com.java.ny.base.BaseFragment;
import com.java.ny.util.ViewModelFactory;
import javax.inject.Inject;
import butterknife.BindView;

public class DetailsFragment extends BaseFragment {

    @BindView(R.id.tv_times_name)
    TextView timesNameTextView;
    @BindView(R.id.tv_times_description)
    TextView timesDescriptionTextView;
    @BindView(R.id.tv_times_date)
    TextView timesDateTextView;

    @Inject
    ViewModelFactory viewModelFactory;
    private DetailsViewModel detailsViewModel;

    @Override
    protected int layoutRes() {
        return R.layout.screen_details;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        detailsViewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(DetailsViewModel.class);
        //detailsViewModel.restoreFromBundle(savedInstanceState);
        displayNYTimes();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        detailsViewModel.saveToBundle(outState);
    }

    private void displayNYTimes() {
        detailsViewModel.getSelectedNYTimes().observe(this, repo -> {
            if (repo != null) {
                timesNameTextView.setText(repo.title);
                timesDescriptionTextView.setText(repo.byline);
                timesDateTextView.setText(String.valueOf(repo.published_date));
            }
        });
    }
}
