package com.kinztech.cache.sql.transactions

/**
 * Transactions will execute in order listed below.
 */
enum class Transactions(val transaction: Transaction) {

    //ITEMS(transaction = ItemTransaction()),
    //ITEM_SPAWNS(transaction = ItemSpawnsTransaction()),
    //NPCS(transaction = NpcTransaction()),
    //NPCS_SPAWNS(transaction = NpcSpawnsTransaction()),
    //ENUMS(transaction = EnumTransaction()),
    //STRUCTS(transaction = StructTransaction()),
    //OBJECTS(transaction = ObjectTransaction()),
    //REGIONS(transaction = RegionTransaction()),
    //WIDGETS(transaction = WidgetTransaction()),
    SHOPS(transaction = ShopTransaction()),
    //TBL_REGIONS(transaction = TblRegionsTransaction()),
    ;
    companion object {
        val values = enumValues<Transactions>()
    }

}