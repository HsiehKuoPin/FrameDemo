package com.onechat.cat.ui.register

import com.onechat.cat.entity.MemberEntity
import com.onechat.cat.entity.RequestEntity
import com.onechat.cat.net.NetProvider
import io.reactivex.Observable

/**
 * @describe
 * @author  Benjamin
 * @date 2019/4/9  16:47
 * 								 - generate by MvpAutoCodePlus plugin.
 */

class RegisterModel : IRegisterContract.Model {
    override fun register(requestBody: RequestEntity): Observable<MemberEntity> {
        return NetProvider.getInstance().register(requestBody)
    }

}

