/*
 * Copyright 2025 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See See https://github.com/openMF/kmp-project-template/blob/main/LICENSE
 */
package org.mifos.core.datastore

import kotlinx.coroutines.flow.Flow
import org.mifos.core.datastore.model.SampleUser

class UserPreferencesRepositoryImpl(
    private val dataStore: UserPreferencesDataStore,
) : UserPreferencesRepository {
    override val currentUser: Flow<SampleUser> =
        dataStore.observeKeyFlow<SampleUser>(USER_KEY, SampleUser.DEFAULT, SampleUser.serializer())

    override suspend fun saveUser(user: SampleUser) {
        dataStore.putSerializableData(USER_KEY, user, SampleUser.serializer())
    }
}
