package com.putrapebrianonurba.shakifyid.main_features.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material.icons.outlined.Dataset
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.putrapebrianonurba.shakifyid.core.common.redirectOpenUrl
import com.putrapebrianonurba.shakifyid.core.components.EarthquakeSettingsItem
import com.putrapebrianonurba.shakifyid.main_features.presentation.EarthquakeSettingsViewModel
import com.putrapebrianonurba.shakifyid.main_features.ui.components.settings.SettingsTopBar

@Composable
fun EarthquakeSettingsScreen(
    viewModel: EarthquakeSettingsViewModel,
    onNavigateBack: () -> Unit
) {
    // LOCAL CONTEXT
    val context = LocalContext.current

    // UI STATE
    val uiState by viewModel.uiState.collectAsState()

    // CONTAINER
    Scaffold(
        // TOP BAR
        topBar = { SettingsTopBar(onNavigateBack) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentPadding = innerPadding,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            // APPLICATION SETTINGS
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalAlignment = Alignment.Start
                ) {

                    // TITLE
                    Text(
                        text = "Pengaturan Aplikasi",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(start = 14.dp)
                    )

                    // THEME SETTINGS
                    EarthquakeSettingsItem(
                        icon = Icons.Outlined.LightMode,
                        name = "Tema Gelap",
                        description = "Tampilan dengan warna gelap, cocok untuk pada malam hari atau tempat gelap.",
                        trailing = {
                            Switch(
                                checked = uiState.isDarkTheme,
                                onCheckedChange = { viewModel.onThemeToggle(it) },
                                modifier = Modifier.scale(0.7f)
                            )
                        }
                    )

                    // NOTIFICATION SETTINGS
                    EarthquakeSettingsItem(
                        icon = Icons.Outlined.Notifications,
                        name = "Notifikasi",
                        description = "Aktifkan notifikasi untuk menerima peringatan gempa bumi terbaru.",
                        trailing = {
                            Switch(
                                checked = uiState.isNotificationEnabled,
                                onCheckedChange = { viewModel.onNotificationToggle(it) },
                                modifier = Modifier.scale(0.7f)
                            )
                        }
                    )
                }
            }

            // DATA SOURCE
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                ) {

                    // TITLE
                    Text(
                        text = "Sumber Informasi",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(start = 14.dp)
                    )

                    // BMKG WEBSITE
                    EarthquakeSettingsItem(
                        icon = Icons.Outlined.Public,
                        name = "BMKG",
                        description = "Kunjungi situs resmi BMKG untuk informasi tentang cuaca, iklim dan gempa bumi lebih lanjut.",
                        onClick = { redirectOpenUrl(context, "https://www.bmkg.go.id/") }
                    )

                    // SOURCE DATA
                    EarthquakeSettingsItem(
                        icon = Icons.Outlined.Dataset,
                        name = "Informasi Data",
                        description = "Akses langsung sumber informasi terbuka tentang cuaca, iklim, dan gempa bumi dari BMKG.",
                        onClick = { redirectOpenUrl(context, "https://data.bmkg.go.id/") }
                    )
                }
            }

            // DEVELOPER INFORMATION
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {

                    // TITLE
                    Text(
                        text = "Informasi Pengembang",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(start = 14.dp)
                    )

                    // LINKEDIN ACCOUNT DIRECT LINK
                    EarthquakeSettingsItem(
                        icon = Icons.Outlined.Person,
                        name = "Mengenal Pengembang",
                        description = "Yuk kepoin profil pengembang Aplikasi Shakify ID di LinkedIn.",
                        onClick = { redirectOpenUrl(context, "https://id.linkedin.com/in/putra-pebriano-nurba-754b9227b") }
                    )

                    // GITHUB ACCOUNT DIRECT LINK
                    EarthquakeSettingsItem(
                        icon = Icons.Outlined.Code,
                        name = "GitHub",
                        description = "Lihat aktivitas yang dilakukan pengembang dan aplikasi lain yang telah dibuat.",
                        onClick = { redirectOpenUrl(context, "https://github.com/PFebrianoooo") }
                    )

                    // CONTRIBUTIONS TO REPOSITORY
                    EarthquakeSettingsItem(
                        icon = Icons.Outlined.StarOutline,
                        name = "Berkontribusi",
                        description = "Ayo kita sama-sama mengembangkan Shakify ID agar menjadi lebih baik.",
                        onClick = { redirectOpenUrl(context, "https://github.com/PFebrianoooo/Shakify-ID") }
                    )
                }
            }
        }
    }
}