package com.onechat.cat.entity

import java.io.Serializable

/**
 * @author  Ben
 * @date 2019/4/3
 */
data class MemberEntity(
    var nickName: String? = null,
    var memberUUID: String? = null,
    var phone: String? = null,
    var account: String? = null,
    var gender: String? = null,
    var age: String? = null,
    var vipStatus: Int = 0,
    var lastLoginTime: String? = null,
    var personDesc: String? = null,
    var personImgUrl: String? = null,
    var appId: String? = null,
    var virtualCoin: Int = 0,
    var incomeCoin: Int = 0,
    var totalCoin: Int = 0,
    var videoPrice: Int = 0,
    var imPrice: Int = 0,
    var fans: Int = 0,
    var concerns: Int = 0,
    var anchorFlag: Int = 0,
    var anchorLevel: Int = 0,
    var videoFlag: Int = 0,
    var nation: String? = null,
    var province: String? = null,
    var city: String? = null
) : Serializable {

    /**
     * nickName : 13246859103
     * memberUUID : 9f6007ab710a583c44a148f483a71890
     * phone : 1235
     * account : 1235
     * gender : 保密
     * age : null
     * vipStatus : 0
     * lastLoginTime : null
     * personDesc :
     * personImgUrl : http://www.ichatcat.com:8088/null
     * appId : ichatcat
     * virtualCoin : 0
     * incomeCoin : 0
     * totalCoin : 0
     * videoPrice : 100
     * imPrice : 0
     * fans : 0
     * concerns : 0
     * anchorFlag : 0
     * anchorLevel : 0
     * videoFlag : 1
     * nation : null
     * province : null
     * city : null
     */

}