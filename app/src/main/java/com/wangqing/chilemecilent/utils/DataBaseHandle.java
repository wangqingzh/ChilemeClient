package com.wangqing.chilemecilent.utils;

import android.content.Context;

import androidx.annotation.NonNull;
//import androidx.room.Database;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//import androidx.room.migration.Migration;
//import androidx.sqlite.db.SupportSQLiteDatabase;

import com.wangqing.chilemecilent.model.dao.UserAuthDao;
import com.wangqing.chilemecilent.model.entity.UserAuth;

/**
 * 生成处理dao的Room单例
 * 并在本单例中进行一些相关配置
 */
//@Database(entities = {UserAuth.class}, version = 1, exportSchema = false)
public abstract class DataBaseHandle //extends RoomDatabase
{
//
//    private static DataBaseHandle INSTANCE;
//    public static synchronized DataBaseHandle getDataBase(Context context){
//        if (INSTANCE == null){
//            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
//                    DataBaseHandle.class, "chileme_database")
//                    //.addMigrations(MIGRATION_1_2)
//                    .build();
//        }
//        return INSTANCE;
//    }
//
//    public abstract UserAuthDao getUserAuthDao();
//
//    /**
//     * 数据库结构 迁移 策略
//     */
//    static final Migration MIGRATION_1_2 = new Migration(1,2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE word ADD COLUMN chinese_invisible INTEGER NOT NULL DEFAULT 0");
//        }
//    };
}
