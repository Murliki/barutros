package barulin.barutros.classes

val keyboardButtons = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9")


val lettersHoles = listOf("G", "H", "K", "M", "N", "S", "Js", "F")

val numbersHolesNot_H = listOf("7")
val numbersHoles_F = listOf("8")
val numbersHoles_H = listOf("7", "8", "9", "11", "12", "13", "14", "15", "16")

val lettersRollers =
    listOf("h", "k", "m", "n", "p", "r", "s", "js", "f", "d", "e", "u", "a", "b", "c")

val numbersRollers_h = listOf("6", "8", "9", "11", "12", "13", "14", "15", "16")
val numbersRollers_k_m_n_p_r_s = listOf("6")
val numbersRollers_js = listOf("6", "7")
val numbersRollers_f = listOf("7", "9")
val numbersRollers_d = listOf("8", "9", "11")
val numbersRollers_e_u = listOf("8")
val numbersRollers_a_c = listOf("11")
val numbersRollers_b = listOf("12")

val diameters = listOf(
    "2", "2.5", "3", "4", "5", "6", "8", "10", "12", "14", "16",
    "18", "20", "22", "24", "27", "30", "33", "36", "39", "42",
    "45", "48", "50", "52", "56", "60", "64", "68", "72", "76",
    "80", "85", "90"
)

val steps_2 = listOf("0.4")
val steps_2_5 = listOf("0.45")
val steps_3 = listOf("0.5", "0.35")
val steps_4 = listOf("0.7", "0.35")
val steps_5 = listOf("0.8", "0.5")
val steps_6 = listOf("1", "0.75", "0.5")
val steps_8 = listOf("1.25", "1")
val steps_10 = listOf("1.5", "1.25", "1", "0.75")
val steps_12 = listOf("1.75", "1.5", "1.25", "1", "0.75")
val steps_14 = listOf("2", "1.5", "1")
val steps_16 = listOf("2", "1.5", "1")
val steps_18 = listOf("2.5", "1.5", "0.75")
val steps_20 = listOf("2.5", "1", "1.5")
val steps_22 = listOf("2.5", "1.5", "1", "0.75")
val steps_24 = listOf("3", "2", "1.5")
val steps_27 = listOf("3", "2", "1.5")
val steps_30 = listOf("3.5", "2", "1.5")
val steps_33 = listOf("3.5", "2", "1.5", "1")
val steps_36 = listOf("4", "3", "2", "1.5")
val steps_39 = listOf("4", "3", "2", "1.5")
val steps_42 = listOf("4", "3", "2", "1.5")
val steps_45 = listOf("4.5", "3", "2", "1.5")
val steps_48 = listOf("5", "3", "2", "1.5")
val steps_50 = listOf("3")
val steps_52 = listOf("5", "3", "2", "1.5")
val steps_56 = listOf("5", "3", "2", "1.5")
val steps_60 = listOf("5.5", "3", "2")
val steps_64 = listOf("6", "4", "3", "2")
val steps_68 = listOf("3", "2")
val steps_72 = listOf("4", "3", "2")
val steps_76 = listOf("3", "2")
val steps_80 = listOf("3", "2")
val steps_85 = listOf("3", "2")
val steps_90 = listOf("3", "2")

val steps = mapOf(
    Pair(2.0, steps_2),
    Pair(2.5, steps_2_5),
    Pair(3.0, steps_3),
    Pair(4.0, steps_4),
    Pair(5.0, steps_5),
    Pair(6.0, steps_6),
    Pair(8.0, steps_8),
    Pair(10.0, steps_10),
    Pair(12.0, steps_12),
    Pair(14.0, steps_14),
    Pair(16.0, steps_16),
    Pair(18.0, steps_18),
    Pair(20.0, steps_20),
    Pair(22.0, steps_22),
    Pair(24.0, steps_24),
    Pair(27.0, steps_27),
    Pair(30.0, steps_30),
    Pair(33.0, steps_33),
    Pair(36.0, steps_36),
    Pair(39.0, steps_39),
    Pair(42.0, steps_42),
    Pair(45.0, steps_45),
    Pair(48.0, steps_48),
    Pair(50.0, steps_50),
    Pair(52.0, steps_52),
    Pair(56.0, steps_56),
    Pair(60.0, steps_60),
    Pair(64.0, steps_64),
    Pair(68.0, steps_68),
    Pair(72.0, steps_72),
    Pair(76.0, steps_76),
    Pair(80.0, steps_80),
    Pair(85.0, steps_85),
    Pair(90.0, steps_90),
)
