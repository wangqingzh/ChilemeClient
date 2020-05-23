package com.wangqing.chilemecilent.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.wangqing.chilemecilent.model.dao.UserAuthDao;
import com.wangqing.chilemecilent.model.entity.UserAuth;
import com.wangqing.chilemecilent.utils.DataBaseHandle;

public class UserAuthRepository {
//    private UserAuthDao userAuthDao;
//
//    public UserAuthRepository(Context context){
//        DataBaseHandle dataBaseHandle = DataBaseHandle.getDataBase(context.getApplicationContext());
//        userAuthDao = dataBaseHandle.getUserAuthDao();
//    }
//
//    public LiveData<UserAuth> getUserAuth(){
//        return userAuthDao.getUserAuth();
//    }
//
//    public void insertUserAuth(UserAuth userAuth){
//        new InsertAsyncTask(userAuthDao);
//    }
//
//
//    /**
//     * 在副线程异步处理数据
//     */
//    static class InsertAsyncTask extends AsyncTask<UserAuth, Void, Void>{
//        private UserAuthDao userAuthDao;
//        InsertAsyncTask(UserAuthDao userAuthDao){
//            this.userAuthDao = userAuthDao;
//        }
//
//        @Override
//        protected Void doInBackground(UserAuth... userAuths) {
//            userAuthDao.insertUserAuth(userAuths);
//            return null;
//        }
//    }
}
