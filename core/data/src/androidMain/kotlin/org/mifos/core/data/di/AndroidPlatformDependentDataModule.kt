/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See See https://github.com/openMF/kmp-project-template/blob/main/LICENSE
 */
package org.mifos.core.data.di

import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import org.mifos.core.data.util.ConnectivityManagerNetworkMonitor
import org.mifos.core.data.util.TimeZoneBroadcastMonitor
import org.mifos.core.data.utils.NetworkMonitor
import org.mifos.core.data.utils.TimeZoneMonitor

class AndroidPlatformDependentDataModule(
    private val context: Context,
    private val dispatcher: CoroutineDispatcher,
    private val scope: CoroutineScope,
) : PlatformDependentDataModule {
    override val networkMonitor: NetworkMonitor by lazy {
        ConnectivityManagerNetworkMonitor(context, dispatcher)
    }

    override val timeZoneMonitor: TimeZoneMonitor by lazy {
        TimeZoneBroadcastMonitor(context, scope, dispatcher)
    }
}
