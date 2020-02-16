package com.wangqing.chilemecilent.viewmodel.bottomnavigation;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MineViewModel extends ViewModel {

    private MutableLiveData<String> nikeName;
    private MutableLiveData<Integer> uid;

    public void MineViewModel(){

        nikeName = new MutableLiveData<>();
        nikeName.setValue("fdajl");



    }


    public MutableLiveData<String> getNikeName() {
        return nikeName;
    }

    public MutableLiveData<Integer> getUid() {

        uid = new MutableLiveData<>();
        uid.setValue(34);
        return uid;
    }
}
