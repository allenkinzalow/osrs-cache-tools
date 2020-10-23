package com.kinztech.cache.sql.transactions

import com.kinztech.cache.sql.schema.Items
import com.kinztech.cache.sql.schema.TblAreaRegions
import com.kinztech.osrsbox.OSRSBox
import io.guthix.cache.js5.Js5Cache
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.transactions.transaction

class TblRegionsTransaction: Transaction {

    private val TIRANNWN = listOf(8501, 8500, 8499, 8498, 8497, 8496, 8495, 8751, 8752, 8753, 8754, 8755, 8756, 8757, 9013, 9012, 9011, 9010, 9009, 9008, 9007, 9264, 9265, 9266, 9267, 9268, 9523, 12640, 12896, 13152, 13408, 12639, 12895, 13151, 13407, 12638, 12894, 13150, 13406, 12637, 12893, 13149, 13405)
    private val KANDARIN = listOf(9269, 9270, 9014, 9015, 9271, 9272, 9016, 9017, 9275, 9528, 9527, 9526, 9525, 9524, 9783, 9782, 9781, 9780, 10036, 10037, 10038, 10039, 10294, 10293, 10292, 10548, 10549, 10550, 10551, 10807, 10806, 10805, 11061, 11317, 9779, 9778, 9777, 9776, 9775, 9520, 9519, 9263, 9774, 9773, 9772, 10028, 10284, 10285, 10029, 10030, 10286, 10542, 10287, 10031, 10032, 10288, 10544, 10545, 10289, 10033, 10034, 10290, 10546, 10547, 10803, 10804, 10795, 11051, 10794, 11050, 11306, 11562, 11059)
    private val FREMENNIK = listOf(8253, 8509, 8252, 8508, 9023, 8763, 9275, 9531, 9532, 9276, 9790, 10044, 10300, 10042, 10558, 10559, 11325, 11581, 11324, 11580, 10812, 10811, 10810, 10890, 11065, 10808, 11064,  10554, 10553, 10552, 10297, 10296, 10040, 10041, 10042, 10043)
    private val KARAMJA = listOf(11053, 11309, 11565, 11821, 11822, 11566, 11310, 11054, 11055, 11311, 11567, 11823, 11568, 11312, 11056, 11569, 11313, 11057, 10801, 10802, 11058, 11314, 11315, 9552, 9808, 10064, 9807, 10063)
    private val ASGARNIA = listOf(10537, 10536, 11824, 11825, 12081, 12082, 11826, 11570, 11571, 11827, 12083, 12082, 11572, 11828, 12084, 12085, 12086, 11830, 11829, 11573, 11574, 11318, 11319, 11575, 11320, 11576, 11321, 11577, 11322, 11578, 11579, 11323, 11068, 11067, 11066, 14936)
    private val WILDERNESS = listOf(11837, 11836, 11835, 11834, 11833, 11832, 11831, 12087, 12088, 12089, 12090, 12091, 12092, 12093, 12349, 12348, 12347, 12346, 12345, 12344, 12343, 12599, 12600, 12601, 12602, 12603, 12604, 12605, 12861, 12860, 12859, 12858, 12857, 12856, 12855, 13111, 13112, 13113, 13114, 13115, 13116, 13117, 13373, 13372, 13371, 13370, 13369, 13368, 13367 )
    private val MISTHALIN = listOf(12342, 12341, 12340, 12339, 12338, 12337, 12593, 12849, 12594, 12850, 12595, 12851, 12596, 12852, 13108, 13364, 13365, 13366, 13109, 13110, 12853, 12854, 12597, 12598, 14393, 14649, 14905, 15418, 15162, 14906, 14650, 14394, 14395, 14651, 14907, 15163, 15164, 14908, 14652, 14653, 14909, 14910, 14142, 14398, 9285, 9541, 9797, 9540, 9796, 10307 )
    private val DESERT = listOf(13107, 13363, 13362, 13106, 13105, 13361, 13617, 12848, 13104, 13360, 13616, 13872, 12591, 12847, 13103, 13359, 13615, 12590, 12846, 13102, 13358, 13614, 12589, 12845, 13101, 13357, 13613, 12844, 13100, 13356, 12843, 13099, 11605)
    private val MORYTANIA = listOf(13624, 13623, 13879, 14135, 14391, 14647, 15159, 13622, 13879, 14134, 14390, 14646, 13621, 13877, 14133, 14389, 14645, 14901, 14900, 14644, 14388, 14132, 13876, 13620, 13619, 13875, 14131, 14387, 14643, 14899, 13618, 13874, 14130, 14386, 14642, 14898, 13873, 14129, 14385, 14641, 14897, 14639, 14895, 15151, 15407, 15406, 15150, 14894, 14638, 15148)

    override fun execute(cache: Js5Cache, osrsbox: OSRSBox) {
        transaction {
            // print sql to std-out
            addLogger(StdOutSqlLogger)

            SchemaUtils.drop(TblAreaRegions)
            SchemaUtils.create (TblAreaRegions)

            TblAreaRegions.batchInsert(TIRANNWN) { regionId ->
                this[TblAreaRegions.tblAreaId] = 1
                this[TblAreaRegions.regionId]  = regionId
            }
            TblAreaRegions.batchInsert(TIRANNWN) { regionId ->
                this[TblAreaRegions.tblAreaId] = 1
                this[TblAreaRegions.regionId]  = regionId + 100
            }
            TblAreaRegions.batchInsert(KANDARIN) { regionId ->
                this[TblAreaRegions.tblAreaId] = 2
                this[TblAreaRegions.regionId]  = regionId
            }
            TblAreaRegions.batchInsert(KANDARIN) { regionId ->
                this[TblAreaRegions.tblAreaId] = 2
                this[TblAreaRegions.regionId]  = regionId + 100
            }
            TblAreaRegions.batchInsert(FREMENNIK) { regionId ->
                this[TblAreaRegions.tblAreaId] = 3
                this[TblAreaRegions.regionId]  = regionId
            }
            TblAreaRegions.batchInsert(FREMENNIK) { regionId ->
                this[TblAreaRegions.tblAreaId] = 3
                this[TblAreaRegions.regionId]  = regionId + 100
            }
            TblAreaRegions.batchInsert(KARAMJA) { regionId ->
                this[TblAreaRegions.tblAreaId] = 4
                this[TblAreaRegions.regionId]  = regionId
            }
            TblAreaRegions.batchInsert(KARAMJA) { regionId ->
                this[TblAreaRegions.tblAreaId] = 4
                this[TblAreaRegions.regionId]  = regionId + 100
            }
            TblAreaRegions.batchInsert(ASGARNIA) { regionId ->
                this[TblAreaRegions.tblAreaId] = 5
                this[TblAreaRegions.regionId]  = regionId
            }
            TblAreaRegions.batchInsert(ASGARNIA) { regionId ->
                this[TblAreaRegions.tblAreaId] = 5
                this[TblAreaRegions.regionId]  = regionId + 100
            }
            TblAreaRegions.batchInsert(WILDERNESS) { regionId ->
                this[TblAreaRegions.tblAreaId] = 6
                this[TblAreaRegions.regionId]  = regionId
            }
            TblAreaRegions.batchInsert(WILDERNESS) { regionId ->
                this[TblAreaRegions.tblAreaId] = 6
                this[TblAreaRegions.regionId]  = regionId + 100
            }
            TblAreaRegions.batchInsert(MISTHALIN) { regionId ->
                this[TblAreaRegions.tblAreaId] = 7
                this[TblAreaRegions.regionId]  = regionId
            }
            TblAreaRegions.batchInsert(MISTHALIN) { regionId ->
                this[TblAreaRegions.tblAreaId] = 7
                this[TblAreaRegions.regionId]  = regionId + 100
            }
            TblAreaRegions.batchInsert(DESERT) { regionId ->
                this[TblAreaRegions.tblAreaId] = 8
                this[TblAreaRegions.regionId]  = regionId
            }
            TblAreaRegions.batchInsert(DESERT) { regionId ->
                this[TblAreaRegions.tblAreaId] = 8
                this[TblAreaRegions.regionId]  = regionId + 100
            }
            TblAreaRegions.batchInsert(MORYTANIA) { regionId ->
                this[TblAreaRegions.tblAreaId] = 9
                this[TblAreaRegions.regionId]  = regionId
            }
            TblAreaRegions.batchInsert(MORYTANIA) { regionId ->
                this[TblAreaRegions.tblAreaId] = 9
                this[TblAreaRegions.regionId]  = regionId + 100
            }
        }
    }

}