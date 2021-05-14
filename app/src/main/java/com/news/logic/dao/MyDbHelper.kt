package com.news.logic.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import java.sql.SQLException


class MyDbHelper  //创建数据库
    (context: Context) :
    OrmLiteSqliteOpenHelper(context, "new", null, 1) {

    companion object {
        private const val NAME = "new.db"
    }

    override fun onCreate(database: SQLiteDatabase, connectionSource: ConnectionSource) {
        //创建表
        if (database == null){
            try {
                TableUtils.createTable(connectionSource, NewInfo::class.java)
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
    }

    override fun onUpgrade( //更新表
        database: SQLiteDatabase,
        connectionSource: ConnectionSource,
        oldVersion: Int,
        newVersion: Int
    ) {
    }


}


