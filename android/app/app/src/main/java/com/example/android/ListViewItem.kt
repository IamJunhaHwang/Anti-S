package com.example.android

class ListViewItem {

    private var numStr: String? = null
    private var timeStr: String? = null
    private var contentStr: String? = null

    fun setNum(num: String?) {
        numStr = num
    }
    fun setTime(time: String?) {
        timeStr = time
    }
    fun setContent(content: String?) {
        contentStr = content
    }
    fun getNum(): String? {
        return numStr
    }
    fun getTime(): String? {
        return timeStr
    }
    fun getContent(): String? {
        return contentStr
    }
}