/*
 * Copyright 2025 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See See https://github.com/openMF/kmp-project-template/blob/main/LICENSE
 */
package org.mifos.testing.data

import org.mifos.testing.model.TestUserData

val usersTestData: List<TestUserData> = listOf(
    TestUserData(
        id = 1001L,
        name = "User 1",
        age = 10,
    ),
    TestUserData(
        id = 1002L,
        name = "User 2",
        age = 15,
    ),
    TestUserData(
        id = 1003L,
        name = "User 3",
        age = 18,
    ),
)
