package com.example.ui.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.AmalDataProvider
import com.example.data.ReadingDisplayMode
import com.example.ui.components.DuaCard
import kotlinx.coroutines.launch

@Composable
fun AmalReaderScreen(
    fontSizeMultiplier: Float,
    onFontSizeChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedSectionId by remember { mutableStateOf<String?>("all") }
    var displayMode by remember { mutableStateOf(ReadingDisplayMode.ARABIC_AND_TRANSLATION) }
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    val filteredVerses = remember(selectedSectionId) {
        if (selectedSectionId == null || selectedSectionId == "all") {
            AmalDataProvider.mainAmalSections
        } else {
            AmalDataProvider.mainAmalSections.filter { it.id == selectedSectionId }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("amal_reader_screen")
    ) {
        // Minimal Top Quick Bar: Display Mode & Font Size
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 6.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Display Mode Options
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FilterChip(
                        selected = displayMode == ReadingDisplayMode.ARABIC_AND_TRANSLATION,
                        onClick = { displayMode = ReadingDisplayMode.ARABIC_AND_TRANSLATION },
                        label = { Text("अरबी + तर्जुमा", fontSize = 12.sp) },
                        modifier = Modifier.testTag("mode_both_chip")
                    )
                    FilterChip(
                        selected = displayMode == ReadingDisplayMode.ARABIC_ONLY,
                        onClick = { displayMode = ReadingDisplayMode.ARABIC_ONLY },
                        label = { Text("सिर्फ़ अरबी", fontSize = 12.sp) },
                        modifier = Modifier.testTag("mode_arabic_chip")
                    )
                }

                // Font zoom controls
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = {
                            if (fontSizeMultiplier > 0.85f) onFontSizeChange(fontSizeMultiplier - 0.1f)
                        },
                        modifier = Modifier.size(36.dp).testTag("font_decrease_button")
                    ) {
                        Text("A-", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                    IconButton(
                        onClick = {
                            if (fontSizeMultiplier < 1.45f) onFontSizeChange(fontSizeMultiplier + 0.1f)
                        },
                        modifier = Modifier.size(36.dp).testTag("font_increase_button")
                    ) {
                        Text("A+", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                }
            }
        }

        // Section Navigation Pills
        val scrollState = rememberScrollState()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                selected = selectedSectionId == "all",
                onClick = {
                    selectedSectionId = "all"
                    scope.launch { listState.animateScrollToItem(0) }
                },
                label = { Text("मुकम्मल अमल") },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                modifier = Modifier.testTag("chip_all_sections")
            )

            AmalDataProvider.mainAmalSections.forEach { verse ->
                FilterChip(
                    selected = selectedSectionId == verse.id,
                    onClick = {
                        selectedSectionId = verse.id
                        scope.launch { listState.animateScrollToItem(0) }
                    },
                    label = { Text(verse.sectionTitle.substringBefore(" (").take(15)) },
                    modifier = Modifier.testTag("chip_section_${verse.id}")
                )
            }
        }

        // Main Dua Content List
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيمِ",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "बे नज़ीर अमल (मुतरजम)",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        Text(
                            text = "अज़ इफ़ादात: हुज़ूर मुफ़्ती-ए-आज़म हिन्द अल्लामा शाह मुस्तफ़ा रज़ा ख़ान",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.85f),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            // The verses / sections
            items(filteredVerses, key = { it.id }) { verse ->
                DuaCard(
                    verse = verse,
                    fontSizeMultiplier = fontSizeMultiplier,
                    displayMode = displayMode
                )
            }

            // Bottom reminder item
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)
                    )
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "✨ पाबंदी का तरीक़ा:",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary,
                            style = MaterialTheme.typography.titleSmall
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "इस अमल को सुबह (आधी रात ढलने से तुलूअ आफ़ताब तक) और शाम (ज़वाल से सूरज डूबने तक) बा-वुज़ू एक-एक बार पाबंदी से पढ़ें।",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}
