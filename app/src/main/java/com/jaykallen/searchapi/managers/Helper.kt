package com.jaykallen.searchapi.managers

object Helper {

    fun checkToIndex(checkedId: Int, arraySize: Int): Int {
        var checked = checkedId % arraySize
        checked = if (checked == 0) arraySize-1 else checked - 1
        return checked
    }

    private fun NumOnly(input: String): String {
        return input.replace("[^\\d.]".toRegex(), "")
    }

}
