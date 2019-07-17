package com.onechat.cat.entity

/**
 * @author Ben
 * @date 2019/7/15
 */
data class ArticleEntity(

    /**
     * apkLink :
     * author : sadhu
     * chapterId : 100
     * chapterName : RecyclerView
     * collect : false
     * courseId : 13
     * desc :
     * envelopePic :
     * fresh : false
     * id : 8671
     * link : https://www.wecando.cc/article/9
     * niceDate : 2019-07-06
     * origin :
     * prefix :
     * projectLink :
     * publishTime : 1562419763000
     * superChapterId : 179
     * superChapterName : 5.+高新技术
     * tags : []
     * title : ItemDecoration解析(一) getItemOffsets
     * type : 0
     * userId : -1
     * visible : 1
     * zan : 0
     */
    var apkLink: String = "",
    var author: String = "",
    var chapterId: Int = 0,
    var chapterName: String = "",
    var collect: Boolean = false,
    var courseId: Int = 0,
    var desc: String = "",
    var envelopePic: String = "",
    var fresh: Boolean = false,
    var id: Int = 0,
    var link: String = "",
    var niceDate: String = "",
    var origin: String = "",
    var prefix: String = "",
    var projectLink: String = "",
    var publishTime: Long = 0,
    var superChapterId: Int = 0,
    var superChapterName: String = "",
    var tags: List<Any> = listOf(),
    var title: String = "",
    var type: Int = 0,
    var userId: Int = 0,
    var visible: Int = 0,
    var zan: Int = 0
)