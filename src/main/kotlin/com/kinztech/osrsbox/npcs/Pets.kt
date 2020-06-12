package com.kinztech.osrsbox.npcs

enum class Pets(val petName: String) {

    CHAOS("Pet chaos elemental"),
    DAGANNOTH_SUPREME("Pet dagannoth supreme"),
    DAGANNOTH_PRIME("Pet dagannoth prime"),
    DAGANNOTH_REX("Pet dagannoth rex"),
    PENANCE_QUEEN("Pet penance queen"),
    KREE_ARRA("Pet kree'arra"),
    GENERAL_GRAARDOR("Pet general graardor"),
    ZILYANA("Pet zilyana"),
    KRIL_TSUTSAROTH("Pet k'ril tsutsaroth"),
    MOLE("Baby mole"),
    PRINCE_BLACK_DRAGON("Prince black dragon"),
    KALPHITE_PRINCESS("Kalphite princess"),
    SMOKE_DEVIL("Pet smoke devil"),
    KRAKEN("Pet kraken"),
    DARK_CORE("Pet dark core"),
    SNAKELING("Pet snakeling"),
    CHOMPY_cHICK("Chompy chick"),
    VANENATIS_SPIDERLING("Venenatis spiderling"),
    CALLISTO_CUB("Callisto cub"),
    VETION_JR("Vet'ion jr."),
    SCORPIO_OFFSPRING("Scorpia's offspring"),
    TZREK_JAD("Tzrek-jad"),
    HELLPUPPY("Hellpuppy"),
    ABYSSAL_ORPHAN("Abyssal orphan"),
    HERON("Heron"),
    ROCK_GOLEM("Rock golem"),
    BEAVER("Beaver"),
    BABY_CHINCHOMPA("Baby chinchompa"),
    BLOODHOUND("Bloodhound"),
    GIAN_SQUIRREL("Giant squirrel"),
    TANGLEROOT("Tangleroot"),
    RIFT_GUARDIAN("Rift guardian"),
    ROCKY("Rocky"),
    PHOENIX("Phoenix"),
    OLMLET("Olmlet"),
    SKOTOS("Skotos"),
    JAL_NIB_REK("Jal-nib-rek"),
    HERBI("Herbi"),
    NOON("Noon"),
    VORKI("Vorki"),
    LIL_ZIK("Lil' zik"),
    IKKLE_HYDRA("Ikkle hydra"),
    SRARACHA("Sraracha"),
    YOUNGLEF("Youngllef"),
    SMOLCANO("Smolcano"),
    LITTLE_NIGHTMARE("Little nightmare"),
    ;

    companion object {

        /**
         * The cached array of enum definitions
         */
        val values = enumValues<Pets>()

        fun findForName(name: String): Pets? {
            return values.find { v -> v.name == name }
        }
    }



}