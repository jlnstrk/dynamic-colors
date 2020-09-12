/*
 * Copyright 2020 Substance Mobile
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mobile.substance.colors

/**
 * @author Julian Ostarek
 */

sealed class ColorPackage

class UIColorPackage(
    val primaryColor: Int,
    val primaryDarkColor: Int,
    val primaryPrimaryTextColor: Int,
    val primarySecondaryTextColor: Int,
    val primaryDisabledTextColor: Int,
    val accentColor: Int,
    val accentDarkColor: Int,
    val accentPrimaryTextColor: Int,
    val accentSecondaryTextColor: Int,
    val accentDisabledTextColor: Int,
    val primaryActiveIconColor: Int,
    val primaryInactiveIconColor: Int,
    val accentActiveIconColor: Int,
    val accentInactiveIconColor: Int
) : ColorPackage() {

    constructor(primaryColor: Int, accentColor: Int) : this(
        primaryColor,
        primaryColor.darkenedColor(),
        accentColor,
        accentColor.darkenedColor()
    )

    constructor(
        primaryColor: Int,
        primaryDarkColor: Int,
        accentColor: Int,
        accentDarkColor: Int,
        isPrimaryLight: Boolean = primaryColor.isLightColor(),
        isAccentLight: Boolean = accentColor.isLightColor()
    ) : this(
        primaryColor, primaryDarkColor,
        if (isPrimaryLight) ColorConstants.TEXT_COLOR_LIGHT_BG else ColorConstants.TEXT_COLOR_DARK_BG,
        if (isPrimaryLight) ColorConstants.TEXT_COLOR_SECONDARY_LIGHT_BG else ColorConstants.TEXT_COLOR_SECONDARY_DARK_BG,
        if (isPrimaryLight) ColorConstants.TEXT_COLOR_DISABLED_LIGHT_BG else ColorConstants.TEXT_COLOR_DISABLED_DARK_BG,
        accentColor, accentDarkColor,
        if (isAccentLight) ColorConstants.TEXT_COLOR_LIGHT_BG else ColorConstants.TEXT_COLOR_DARK_BG,
        if (isAccentLight) ColorConstants.TEXT_COLOR_SECONDARY_LIGHT_BG else ColorConstants.TEXT_COLOR_SECONDARY_DARK_BG,
        if (isAccentLight) ColorConstants.TEXT_COLOR_DISABLED_LIGHT_BG else ColorConstants.TEXT_COLOR_DISABLED_DARK_BG,
        if (isPrimaryLight) ColorConstants.ICON_COLOR_ACTIVE_LIGHT_BG else ColorConstants.ICON_COLOR_ACTIVE_DARK_BG,
        if (isPrimaryLight) ColorConstants.ICON_COLOR_INACTIVE_LIGHT_BG else ColorConstants.ICON_COLOR_INACTIVE_DARK_BG,
        if (isAccentLight) ColorConstants.ICON_COLOR_ACTIVE_LIGHT_BG else ColorConstants.ICON_COLOR_ACTIVE_DARK_BG,
        if (isAccentLight) ColorConstants.ICON_COLOR_INACTIVE_LIGHT_BG else ColorConstants.ICON_COLOR_INACTIVE_DARK_BG
    )
}

class DominantColorPackage(
    val dominantColor: Int,
    val dominantDarkColor: Int,
    val dominantPrimaryTextColor: Int,
    val dominantSecondaryTextColor: Int,
    val dominantDisabledTextColor: Int,
    val dominantActiveIconColor: Int,
    val dominantInactiveIconColor: Int
) : ColorPackage() {

    constructor(dominantColor: Int) : this(dominantColor, dominantColor.darkenedColor())

    constructor(
        dominantColor: Int,
        dominantColorDark: Int,
        isLight: Boolean = dominantColor.isLightColor()
    ) : this(
        dominantColor, dominantColorDark,
        if (isLight) ColorConstants.TEXT_COLOR_LIGHT_BG else ColorConstants.TEXT_COLOR_DARK_BG,
        if (isLight) ColorConstants.TEXT_COLOR_SECONDARY_LIGHT_BG else ColorConstants.TEXT_COLOR_SECONDARY_DARK_BG,
        if (isLight) ColorConstants.TEXT_COLOR_DISABLED_LIGHT_BG else ColorConstants.TEXT_COLOR_DISABLED_DARK_BG,
        if (isLight) ColorConstants.ICON_COLOR_ACTIVE_LIGHT_BG else ColorConstants.ICON_COLOR_ACTIVE_DARK_BG,
        if (isLight) ColorConstants.ICON_COLOR_INACTIVE_LIGHT_BG else ColorConstants.ICON_COLOR_INACTIVE_DARK_BG
    )

}