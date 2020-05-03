package com.kinztech.osrsbox.shops

data class Shop(var title: String, var owner_name: String, var items: Array<ShopItem>, var currency: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Shop

        if (title != other.title) return false
        if (owner_name != other.owner_name) return false
        if (!items.contentEquals(other.items)) return false
        if (currency != other.currency) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + owner_name.hashCode()
        result = 31 * result + items.contentHashCode()
        result = 31 * result + currency.hashCode()
        return result
    }

}