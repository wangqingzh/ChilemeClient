package com.wangqing.chilemecilent.viewmodel.bottomnavigation;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MineViewModel extends ViewModel {

    private MutableLiveData<String> nikeName;
    private MutableLiveData<Integer> uid;

    public void MineViewModel(){





    }


    public MutableLiveData<String> getNikeName() {
        nikeName = new MutableLiveData<>();
        nikeName.setValue("fdajl");

        return nikeName;
    }

    public MutableLiveData<Integer> getUid() {

        uid = new MutableLiveData<>();
        uid.setValue(34);
        return uid;
    }
}
