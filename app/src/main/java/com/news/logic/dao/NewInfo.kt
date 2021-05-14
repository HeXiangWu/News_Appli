package com.news.logic.dao

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable


//定义数据库中的新闻实体类
@DatabaseTable(tableName = "news_table")
class NewInfo {
    @DatabaseField(columnName = "id", generatedId = true)
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

