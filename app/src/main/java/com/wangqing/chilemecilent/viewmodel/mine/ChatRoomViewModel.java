package com.wangqing.chilemecilent.viewmodel.mine;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.wangqing.chilemecilent.object.dto.ChatDto;
import com.wangqing.chilemecilent.object.dto.UserInfoDto;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomViewModel extends AndroidViewModel {

    private MutableLiveData<List<ChatDto>> chatList;

    private UserInfoDto userInfo;



    public ChatRoomViewModel(@NonNull Application application) {
        super(application);
    }



    public void setUserInfo(UserInfoDto userInfo) {
        this.userInfo = userInfo;
    }

    public MutableLiveData<List<ChatDto>> getChatList() {
        if (chatList == null){
            chatList = new MutableLiveData<>();
            chatList.setValue(new ArrayList<>());
        }
        return chatList;
    }






}
