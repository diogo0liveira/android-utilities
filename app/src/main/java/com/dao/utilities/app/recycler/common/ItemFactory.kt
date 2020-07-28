package com.dao.utilities.app.recycler.common

import kotlin.random.Random

@ExperimentalStdlibApi
class ItemFactory : Generate {
    override fun buildItems(): List<String> {
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

        return buildList(50) {
            repeat(50) {
                add(generateItem(charPool))
            }
        }
    }

    private fun generateItem(charPool: List<Char>): String {
        return (3..(3..20).random())
            .map { Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }
}