package com.onechat.cat.entity

/**
 * @author  Ben
 * @date 2019/5/28
 */
class AccountArticleEntity {


    /**
     * curPage : 1
     * datas : []
     * offset : 0
     * over : false
     * pageCount : 37
     * size : 20
     * total : 723
     */

    var curPage: Int = 0
    var offset: Int = 0
    var isOver: Boolean = false
    var pageCount: Int = 0
    var size: Int = 0
    var total: Int = 0
    var datas: List<ArticleIntroEntity>? = null
}