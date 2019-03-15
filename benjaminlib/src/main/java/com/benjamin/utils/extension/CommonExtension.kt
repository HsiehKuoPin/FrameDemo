package cn.ejiamall.extension

import android.text.Html
import android.text.Spanned
import android.text.TextUtils
import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.DecimalFormat
import java.util.regex.Pattern

/**
 * String扩展函数
 */

/**
 * 格式化价格
 */
fun Double.formatMoney(): String {
    val df = DecimalFormat("#,###,##0.00")
    return df.format(this)
}

/**
 * 格式化价格
 */
fun Int.formatMoney(): String {
    val df = DecimalFormat("#,###,##0.00")
    return df.format(this)
}

/**
 * 检查字符串是否是手机号码
 */
fun String.isMobileNumber(): Boolean {
    var isMobileNo: Boolean = false
    try {
        val p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[^4,\\D])|(17[0-9])|(19[0-9])|(18[0-9]))\\d{8}$")
        val m = p.matcher(this)
        isMobileNo = m.matches()
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return isMobileNo
}

/**
 * 检查字符串是否是邮箱
 */
fun String.isEmail(): Boolean {
    var isEmail: Boolean = false
    val expr = "\\w+(\\.\\w+)*@\\w+(\\.\\w+)+"
    if (matches(expr.toRegex())) {
        isEmail = true
    }
    return isEmail
}

/**
 * 判断字符串是否是url
 * @return boolean
 */
fun String.isURL(): Boolean {
    val URL_REG_EXPRESSION = "^(https?://)?([a-zA-Z0-9_-]+\\.[a-zA-Z0-9_-]+)+(/*[A-Za-z0-9/\\-_&:?\\+=//.%]*)*"
    return Pattern.matches(URL_REG_EXPRESSION, this)
}

/**
 * 判断是否只是数字.
 * @return 是否只是数字:是为true，否则false
 */
fun String.isNumber(): Boolean {
    var isNumber: Boolean = false
    val expr = "^[0-9]+$"
    if (matches(expr.toRegex())) {
        isNumber = true
    }
    return isNumber
}

/**
 * 判断是否为中国邮政编码
 * return boolean
 */
fun String.isChinesePostCode(): Boolean {
    if (length != 6) return false
    if (get(0).equals("0")) return false
    return true
}

/**
 * 判断是否只是字母和数字.
 * @return 是否只是字母和数字:是为true，否则false
 */
fun String.isNumberLetter(): Boolean {
    var isNoLetter: Boolean = false
    val expr = "^[A-Za-z0-9]+$"
    if (matches(expr.toRegex())) {
        isNoLetter = true
    }
    return isNoLetter
}

/**
 * 获取MD5值
 */
fun String.getMD5(): String {
    try {
        val instance: MessageDigest = MessageDigest.getInstance("MD5")//获取md5加密对象
        val digest: ByteArray = instance.digest(toByteArray())//对字符串加密，返回字节数组
        var sb: StringBuffer = StringBuffer()
        for (b in digest) {
            var i: Int = b.toInt() and 0xff//获取低八位有效值
            var hexString = Integer.toHexString(i)//将整数转化为16进制
            if (hexString.length < 2) {
                hexString = "0" + hexString//如果是一位的话，补0
            }
            sb.append(hexString)
        }
        return sb.toString().toLowerCase()

    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    }
    return ""
}

/**
 * 填充成一个完整的html格式数据
 * return String
 */
fun String.fillHtmlContent(): String {
    var content = """
                        <html>
                            <style> *{ margin:0px;padding:0px }</style>
                            <body>
                            ${this}
                            </body>
                        </html>
                        """
    return content
}

/**
 * 转义成html
 * return Spanned
 */
fun String.toHtml(): Spanned {
    return Html.fromHtml(this)
}

/**
 * url编码
 */
fun String.urlEncode(): String {
    var data = ""
    try {
        for (i in 0..length - 1) {
            val c = get(i)
            if (c.toInt() + "".toByteArray().size > 1 && c != ':' && c != '/' && c != '?') {
                data += java.net.URLEncoder.encode(c + "", "utf-8")
            } else {
                data += c
            }
        }
    } catch (e: UnsupportedEncodingException) {
        e.printStackTrace()
    } finally {
        data = if (TextUtils.isEmpty(data)) this else data
    }
    return data
}