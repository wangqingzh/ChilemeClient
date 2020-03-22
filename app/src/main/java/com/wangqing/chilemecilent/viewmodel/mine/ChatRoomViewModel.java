package com.wangqing.chilemecilent.viewmodel.mine;

import android.app.Application;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.wangqing.chilemecilent.object.dto.ChatDto;
import com.wangqing.chilemecilent.object.dto.UserInfoDto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatRoomViewModel extends AndroidViewModel {

    private MutableLiveData<List<ChatDto>> chatList;

    private UserInfoDto userInfo;

    private Gson gson = new Gson();

    private Socket client;

    private BufferedReader br;

    private PrintWriter pw;


    /**
     * 构造方法
     * @param application
     */
    public ChatRoomViewModel(@NonNull Application application) {
        super(application);
    }



    /* setter & getter */
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

    /* socket */

    public void connServer(){
        new Thread(){
            @Override
            public void run() {
                try {
                    client = new Socket("", 54);
                    br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    pw = new PrintWriter(new BufferedWriter(
                            new OutputStreamWriter(client.getOutputStream())
                    ));
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void sendMsg(String msg){
        ChatDto chatDto = new ChatDto(userInfo.getUserId(), userInfo.getAvatarUrl(), userInfo.getNickName(),
                                msg, new Date());
        String data = gson.toJson(chatDto);
        pw.println(data);
        pw.flush();
    }

    public void longConn(){
        final ChatHandler handler = new ChatHandler();
        new Thread(){
            @Override
            public void run() {
                while (true){
                    if (client != null && client.isClosed() && !client.isInputShutdown()){
                        try {
                            String response;
                            if ((response = br.readLine()) != null){
                                ChatDto chatDto = gson.fromJson(response, ChatDto.class);
                                Message message = Message.obtain();
                                message.what = 1;
                                message.obj = chatDto;
                                handler.sendMessage(message);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
    }

    class ChatHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                List<ChatDto> tmpList = new ArrayList<>();
                tmpList = chatList.getValue();
                tmpList.add((ChatDto) msg.obj);

                chatList.setValue(tmpList);
            }
        }
    }




}
