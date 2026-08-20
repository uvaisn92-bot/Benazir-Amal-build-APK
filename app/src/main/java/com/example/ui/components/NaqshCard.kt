package com.example.ui.components

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.AmalDataProvider

@Composable
fun NaqshCard(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("mubarak_naqsh_card"),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header title
            Text(
                text = "मुबारक नक़्श व तावीज़ (बे नज़ीर अमल)",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "हिफ़ाज़त, बरकत व हर मक़सद में कामियाबी के लिए मुजर्रब",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            // The Decorative Frame for Naqsh
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
                    .border(
                        2.dp,
                        MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f),
                        RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Top Header Number (786 / 92)
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .padding(horizontal = 16.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = AmalDataProvider.NAQSH_HEADER,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            fontFamily = FontFamily.Serif
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // 4x4 Grid Matrix
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                1.5.dp,
                                MaterialTheme.colorScheme.primary,
                                RoundedCornerShape(8.dp)
                            )
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.surface)
                    ) {
                        AmalDataProvider.naqshCells.forEachIndexed { rowIndex, row ->
                            Row(modifier = Modifier.fillMaxWidth()) {
                                row.forEachIndexed { colIndex, cell ->
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .aspectRatio(1.2f)
                                            .border(
                                                0.5.dp,
                                                MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
                                            )
                                            .background(
                                                if (cell.isWord) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.25f)
                                                else Color.Transparent
                                            )
                                            .padding(2.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = cell.value,
                                            fontSize = if (cell.isWord) 14.sp else 13.sp,
                                            fontWeight = if (cell.isWord) FontWeight.Bold else FontWeight.Medium,
                                            textAlign = TextAlign.Center,
                                            color = if (cell.isWord) MaterialTheme.colorScheme.primary
                                            else MaterialTheme.colorScheme.onSurface,
                                            lineHeight = 16.sp
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Arabic Dua underneath the Naqsh
                    Text(
                        text = AmalDataProvider.NAQSH_FOOTER_ARABIC,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.Serif,
                        lineHeight = 28.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Hindi Transliteration underneath
                    Text(
                        text = AmalDataProvider.NAQSH_FOOTER_HINDI,
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Instructions on how to use this Naqsh
            Text(
                text = "★ इस्तेमाल का तरीक़ा:\nयह दुआ रोज़ाना सुबह व शाम पढ़ें। इस मुबारक नक़्श को लिखकर गले में डालें या सीधे बाज़ू पर बांधें। घर व दुकान में बरकत व हिफ़ाज़त के लिए लगायें।",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
            Spacer(modifier = Modifier.height(8.dp))

            // Bottom action buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = {
                        val textToCopy = buildString {
                            appendLine("मुबारक नक़्श (बे नज़ीर अमल) - ${AmalDataProvider.NAQSH_HEADER}")
                            appendLine(AmalDataProvider.NAQSH_FOOTER_ARABIC)
                            appendLine(AmalDataProvider.NAQSH_FOOTER_HINDI)
                            appendLine("- हुज़ूर मुफ़्ती-ए-आज़म हिन्द (बरेली शरीफ़)")
                        }
                        clipboardManager.setText(AnnotatedString(textToCopy))
                        Toast.makeText(context, "नक़्श की दुआ कॉपी हो गई", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.testTag("copy_naqsh_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = "कॉपी करें",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                IconButton(
                    onClick = {
                        val textToShare = buildString {
                            appendLine("मुबारक नक़्श (बे नज़ीर अमल)")
                            appendLine(AmalDataProvider.NAQSH_HEADER)
                            appendLine()
                            appendLine(AmalDataProvider.NAQSH_FOOTER_ARABIC)
                            appendLine()
                            appendLine(AmalDataProvider.NAQSH_FOOTER_HINDI)
                            appendLine()
                            appendLine("— हुज़ूर मुफ़्ती-ए-आज़म हिन्द (बरेली शरीफ़)")
                        }
                        val sendIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, textToShare)
                            type = "text/plain"
                        }
                        context.startActivity(Intent.createChooser(sendIntent, "शेयर करें"))
                    },
                    modifier = Modifier.testTag("share_naqsh_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "शेयर करें",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
