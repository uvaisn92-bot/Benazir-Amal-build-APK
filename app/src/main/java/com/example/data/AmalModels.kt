package com.example.data

data class AmalVerse(
    val id: String,
    val sectionTitle: String,
    val arabicText: String,
    val hindiTransliteration: String = "",
    val hindiTranslation: String,
    val countInstruction: String = "1 बार",
    val targetCount: Int = 1,
    val note: String = ""
)

data class WazifaItem(
    val id: Int,
    val title: String,
    val arabicText: String,
    val hindiTransliteration: String,
    val hindiTranslation: String,
    val count: Int,
    val timing: String,
    val benefit: String
)

data class NaqshCell(
    val row: Int,
    val col: Int,
    val value: String,
    val isWord: Boolean = false
)

enum class ReadingDisplayMode {
    ARABIC_AND_TRANSLATION,
    ARABIC_ONLY,
    TRANSLATION_ONLY
}
