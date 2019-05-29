package com.onechat.cat.entity

/**
 * @author  Ben
 * @date 2019/5/28
 */
class AccountEntity {

    /**
     * children : []
     * courseId : 13
     * id : 408
     * name : 鸿洋
     * order : 190000
     * parentChapterId : 407
     * userControlSetTop : false
     * visible : 1
     */

    var courseId: Int = 0
    var id: Int = 0
    var name: String = ""
    var order: Int = 0
    var parentChapterId: Int = 0
    var isUserControlSetTop: Boolean = false
    var visible: Int = 0
    var children: List<*>? = null
}