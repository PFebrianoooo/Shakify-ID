package com.putrapebrianonurba.shakifyid.domain.repository

import kotlinx.coroutines.flow.Flow

interface NetworkMonitorRepository {
    val isConnected: Flow<Boolean>
}