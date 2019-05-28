package com.java.ny.di.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Bundle;

import com.java.ny.data.model.NYTimesVO;
import com.java.ny.data.rest.NYTimesRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DetailsViewModel extends ViewModel {

    private final NYTimesRepository timesRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<NYTimesVO> selectedNYTimes = new MutableLiveData<>();

    public LiveData<NYTimesVO> getSelectedNYTimes() {
        return selectedNYTimes;
    }

    @Inject
    public DetailsViewModel(NYTimesRepository repoRepository) {
        this.timesRepository = repoRepository;
        disposable = new CompositeDisposable();
    }

    public void setSelectedTimesData(NYTimesVO repo) {
        selectedNYTimes.setValue(repo);
    }

    public void saveToBundle(Bundle outState) {
        if(selectedNYTimes.getValue() != null) {
            outState.putStringArray("repo_details", new String[] {
                    selectedNYTimes.getValue().title,
                    selectedNYTimes.getValue().byline
            });
        }
    }

    public void restoreFromBundle(Bundle savedInstanceState) {
        if(selectedNYTimes.getValue() == null) {
            if(savedInstanceState != null && savedInstanceState.containsKey("times_details")) {
                loadRepo(savedInstanceState.getStringArray("times_details"));
            }
        }
    }

    private void loadRepo(String[] repo_details) {
        disposable.add(timesRepository.getNYTime(repo_details[0], repo_details[1]).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<NYTimesVO>() {
            @Override
            public void onSuccess(NYTimesVO value) {
                selectedNYTimes.setValue(value);
            }

            @Override
            public void onError(Throwable e) {

            }
        }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
