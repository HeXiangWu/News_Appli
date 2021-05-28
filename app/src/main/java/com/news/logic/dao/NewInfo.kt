package com.news.logic.dao

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable


//定义数据库中的新闻实体类
//存储 新闻实体类 问题在于每次存储的id 重启完 验证对应值 会刷新
@DatabaseTable(tableName = "news_table")
class NewInfo {

    @DatabaseField(columnName = "id")
    var id: Int? = null

    @DatabaseField(columnName = "newsId")
    var newsId //在表中有这个newId，表示已被读过，否则未读
            : Int? = null



    constructor(newsId: Int) { //自己调用,newsId为NewListData的新闻id
        this.newsId = newsId
    }

    constructor() { //框架调用
    }

    override fun toString(): String {
        return "NewInfo{" +
                "id=" + id +
                ", newId=" + newsId +
                '}'
    }
}

