/*
 * Copyright 2025 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See See https://github.com/openMF/kmp-project-template/blob/main/LICENSE
 */
package org.mifos.core.data.model

/**
 * Result class to share the [loggedOutUserId] of a user
 * that was successfully logged out.
 */
data class LogoutEvent(
    val loggedOutUserId: Long,
)
