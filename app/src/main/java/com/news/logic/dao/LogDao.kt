@file:Suppress("unused")

package com.news.logic.dao

import androidx.room.*
import com.news.logic.model.NetworkLog

// 本地数据源,来自SQLite数据库
@Dao
interface LogDao {

    @Insert
    suspend fun insertAll(vararg log: NetworkLog)

    @Query(
        """
        SELECT * FROM network_log WHERE category=:category and timestamp>=datetime('now','+8 hour','start of day','+0 day') and timestamp<datetime('now','+8 hour','start of day','+1 day')
    """
    )
    suspend fun getTodayLogsByCategory(category: String): List<NetworkLog>
}