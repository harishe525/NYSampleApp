package com.java.ny.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.java.ny.data.model.NYTimesListVO;
import com.java.ny.data.model.NYTimesVO;
import com.java.ny.data.rest.NYTimesRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewModel extends ViewModel {

    private final NYTimesRepository nyTimesRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<NYTimesListVO> nyTimes = new MutableLiveData<>();
    private final MutableLiveData<Boolean> timesLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @Inject
    public ListViewModel(NYTimesRepository nyTimesRepository) {
        this.nyTimesRepository = nyTimesRepository;
        disposable = new CompositeDisposable();
        fetchNYTimesList();
    }

    LiveData<NYTimesListVO> getNYTimes() {
        return nyTimes;
    }
    LiveData<Boolean> getError() {
        return timesLoadError;
    }
    LiveData<Boolean> getLoading() {
        return loading;
    }

    private void fetchNYTimesList() {
        loading.setValue(true);
        disposable.add(nyTimesRepository.getMostPopularNews("MREB90o0mgA50WiaHiuEEu4cbsJPkTtX").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<NYTimesListVO>() {
                    @Override
                    public void onSuccess(NYTimesListVO value) {
                        timesLoadError.setValue(false);
                        nyTimes.setValue(value);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        timesLoadError.setValue(true);
                        loading.setValue(false);
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
