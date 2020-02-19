package com.wangqing.chilemecilent.viewmodel.mine;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.wangqing.chilemecilent.utils.AccountManager;

public class SettingViewModel extends AndroidViewModel {
    private AccountManager accountManager;
    private MutableLiveData<String> phoneNumber;

    public SettingViewModel(@NonNull Application application) {
        super(application);
        accountManager = AccountManager.getInstance(application);
    }

    public MutableLiveData<String> getPhoneNumber() {
        if (phoneNumber == null){
            phoneNumber = new MutableLiveData<>();
        }
        return phoneNumber;
    }

    public void getPhoneNumberFromLocal(){

    }
}
