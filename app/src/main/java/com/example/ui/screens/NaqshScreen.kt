package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.NaqshCard

@Composable
fun NaqshScreen(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("naqsh_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "मुबारक नक़्श व तावीज़ शरीफ़",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "हुज़ूर मुफ़्ती-ए-आज़म हिन्द का अता करदा मुजर्रब तावीज़ हर मक़सद में कामियाबी, दफ़ा आफ़त व परेशानी और हिफ़ाज़त-ए-दुश्मन के लिए।",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.9f),
                        lineHeight = 20.sp
                    )
                }
            }
        }

        // The Full Naqsh Card
        item {
            NaqshCard()
        }

        // Detailed Benefit & Instructions Card (Page 27-28 of book)
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    Text(
                        text = "★ किताब से तफ़सीलात व फ़ज़ीलत:",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "• यह दुआ रोज़ाना सुबह व शाम पढ़ें।\n• मुंदरजा ज़ैल तावीज़ (नक़्श) गले में डालें या बाज़ू पर बांधें।\n• हर काम मक़सद के लिए आज़मूदा और दफ़ा आफ़त व परेशानी व हिफ़ाज़त-ए-दुश्मन के वास्ते मुजर्रब है।\n• घर के हर फ़र्द को लिखकर पहनायें, बहुत ही नफ़ा-बख़्श व मुफ़ीद साबित होगा।\n• सुबह से मुराद: आधी रात ढलने से तुलूअ-ए-आफ़ताब तक।\n• शाम से मुराद: ज़वाल से ग़ुरूब-ए-आफ़ताब तक।",
                        style = MaterialTheme.typography.bodyMedium,
                        lineHeight = 24.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}
