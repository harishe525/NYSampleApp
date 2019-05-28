package com.java.ny.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.java.ny.di.detail.DetailsViewModel;
import com.java.ny.di.util.ViewModelKey;
import com.java.ny.list.ListViewModel;
import com.java.ny.util.ViewModelFactory;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Singleton
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel.class)
    abstract ViewModel bindListViewModel(ListViewModel listViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel.class)
    abstract ViewModel bindDetailsViewModel(DetailsViewModel detailsViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
