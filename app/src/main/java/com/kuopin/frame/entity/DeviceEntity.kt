package com.kuopin.frame.entity

/**
 * @author  Ben
 * @date 2019/3/19
 */
data class DeviceEntity(
    val deviceId: Long,
    val userId: Long,
    val pushToken: String?,
    val outletId: Long,
    val outletExternalId: String,
    val retailerId: Long,
    val retailerCode: String,
    val role: String,
    val token: String,
    val refreshToken: String,
    val expiresIn: Int
)