package com.onechat.cat.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class RecommendEntity : Parcelable {

    var bannerList: List<BannerListBean>? = null
    var recommendAnchorList: List<RecommendAnchorListBean>? = null

    class BannerListBean {
        /**
         * id : 2
         * subject : 公告2
         * imageUrl : http://www.ichatcat.com:8088//banner/20190110162612.jpg
         * remark : null
         * sequence : null
         * createTime : null
         */

        var id: Int = 0
        var subject: String? = null
        var imageUrl: String? = null
        var remark: Any? = null
        var sequence: Any? = null
        var createTime: Any? = null
    }

    class RecommendAnchorListBean {
        /**
         * nickName : 测试
         * memberUUID : 9e89cbc110f652ccb68be7ece8e61253
         * account : 1235
         * gender : 保密
         * age : 6666
         * job : null
         * nation : null
         * province : 广州
         * city : 广州1
         * imPrice : 20181204102835
         * videoPrice : 200
         * personDesc : 你好，兄die
         * anchorFlag : 1
         * anchorLevel : 22
         * percent : 0.8
         * fans : 0
         * concern : false
         * indexPictureUrl : http://www.ichatcat.com:8088//background/20190110161002.jpg
         * uploadPictureList : null
         * shortVideoList : null
         * dynamicList : null
         */

        var nickName: String? = null
        var memberUUID: String? = null
        var account: String? = null
        var gender: String? = null
        var age: String? = null
        var job: Any? = null
        var nation: Any? = null
        var province: String? = null
        var city: String? = null
        var imPrice: Long = 0
        var videoPrice: Int = 0
        var personDesc: String? = null
        var anchorFlag: Int = 0
        var anchorLevel: Int = 0
        var percent: Double = 0.toDouble()
        var fans: Int = 0
        var isConcern: Boolean = false
        var indexPictureUrl: String? = null
        var uploadPictureList: Any? = null
        var shortVideoList: Any? = null
        var dynamicList: Any? = null
    }
}