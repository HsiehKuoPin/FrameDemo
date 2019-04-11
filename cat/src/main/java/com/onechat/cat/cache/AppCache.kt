package com.onechat.cat.cache

import com.benjamin.utils.SPUtil
import com.benjamin.utils.eighteen.UtilsInitializer
import com.onechat.cat.entity.MemberEntity

/**
 * @author  Ben
 * @date 2019/4/3
 */
object AppCache {
    private var member: MemberEntity? = null

    val memberUUID: String?
        get() = if (getMember() == null) null else member!!.memberUUID

    fun getMember(): MemberEntity? {
        if (member == null) {
            member = SPUtil.getObjectFromShare<MemberEntity>(UtilsInitializer.getContext(), "key_sp_member")
        }
        return member
    }

    fun setMember(member: MemberEntity?) {
        AppCache.member = member
        SPUtil.saveObjectToShare<MemberEntity>(UtilsInitializer.getContext(), "key_sp_member", member)
    }

    /**
     * 清除用户缓存
     */
    fun clearUserCache() {
        AppCache.setMember(null)
    }
}