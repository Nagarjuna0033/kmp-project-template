/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See See https://github.com/openMF/kmp-project-template/blob/main/LICENSE
 */
package org.mifos.core.designsystem.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.mifos.core.designsystem.icon.AppIcons
import org.mifos.core.designsystem.theme.MifosTheme

/**
 * Now in Android navigation bar item with icon and label content slots.
 * Wraps Material 3 [NavigationBarItem].
 *
 * @param selected Whether this item is selected.
 * @param onClick The callback to be invoked when this item is selected.
 * @param icon The item icon content.
 * @param modifier Modifier to be applied to this item.
 * @param enabled controls the enabled state of this item. When `false`,
 *    this item will not be clickable and will appear disabled to
 *    accessibility services.
 * @param alwaysShowLabel Whether to always show the label for this item.
 *    If false, the label will only be shown when this item is selected.
 * @param selectedIcon The item icon content when selected.
 * @param label The item text label content.
 */
@Composable
fun RowScope.MifosNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
    selectedIcon: @Composable () -> Unit = icon,
    label: @Composable (() -> Unit)? = null,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
    )
}

/**
 * Now in Android navigation bar with content slot. Wraps Material 3
 * [NavigationBar].
 *
 * @param modifier Modifier to be applied to the navigation bar.
 * @param content Destinations inside the navigation bar. This should
 *    contain multiple [NavigationBarItem]s.
 */
@Composable
fun MifosNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 0.dp,
        content = content,
    )
}

/**
 * Now in Android navigation rail item with icon and label content slots.
 * Wraps Material 3 [NavigationRailItem].
 *
 * @param selected Whether this item is selected.
 * @param onClick The callback to be invoked when this item is selected.
 * @param icon The item icon content.
 * @param modifier Modifier to be applied to this item.
 * @param enabled controls the enabled state of this item. When `false`,
 *    this item will not be clickable and will appear disabled to
 *    accessibility services.
 * @param alwaysShowLabel Whether to always show the label for this item.
 *    If false, the label will only be shown when this item is selected.
 * @param selectedIcon The item icon content when selected.
 * @param label The item text label content.
 */
@Composable
fun MifosNavigationRailItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
    selectedIcon: @Composable () -> Unit = icon,
    label: @Composable (() -> Unit)? = null,
) {
    NavigationRailItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
    )
}

/**
 * Now in Android navigation rail with header and content slots. Wraps
 * Material 3 [NavigationRail].
 *
 * @param modifier Modifier to be applied to the navigation rail.
 * @param header Optional header that may hold a floating action button or
 *    a logo.
 * @param content Destinations inside the navigation rail. This should
 *    contain multiple [NavigationRailItem]s.
 */
@Composable
fun MifosNavigationRail(
    modifier: Modifier = Modifier,
    header: @Composable (ColumnScope.() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    NavigationRail(
        modifier = modifier,
        containerColor = Color.Transparent,
        contentColor = MifosNavigationDefaults.navigationContentColor(),
        header = header,
        content = content,
    )
}

@Preview
@Composable
fun MifosNavigationBarPreview() {
    val items = listOf("Home", "Payments", "Finance", "Profile")
    val icons = listOf(
        AppIcons.Home,
        AppIcons.Payment,
        AppIcons.Finance,
        AppIcons.Profile,
    )
    val selectedIcons =
        listOf(
            AppIcons.Home,
            AppIcons.Payment,
            AppIcons.Finance,
            AppIcons.Profile,
        )

    MifosTheme {
        MifosNavigationBar {
            items.forEachIndexed { index, item ->
                MifosNavigationBarItem(
                    selected = index == 0,
                    onClick = { },
                    icon = {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = item,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = selectedIcons[index],
                            contentDescription = item,
                        )
                    },
                    label = { Text(item) },
                )
            }
        }
    }
}

@Preview
@Composable
fun MifosNavigationRailPreview() {
    val items = listOf("Home", "Payments", "Finance", "Profile")
    val icons =
        listOf(
            AppIcons.Home,
            AppIcons.Payment,
            AppIcons.Finance,
            AppIcons.Profile,
        )
    val selectedIcons =
        listOf(
            AppIcons.HomeBoarder,
            AppIcons.Payment,
            AppIcons.Finance,
            AppIcons.ProfileBoarder,
        )

    MifosTheme {
        MifosNavigationRail {
            items.forEachIndexed { index, item ->
                MifosNavigationRailItem(
                    selected = index == 0,
                    onClick = { },
                    icon = {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = item,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = selectedIcons[index],
                            contentDescription = item,
                        )
                    },
                    label = { Text(item) },
                )
            }
        }
    }
}

/** Now in Android navigation default values. */
object MifosNavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.primaryContainer
}
