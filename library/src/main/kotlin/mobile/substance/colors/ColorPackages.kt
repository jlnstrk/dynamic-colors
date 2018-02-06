package mobile.substance.colors

/**
 * @author Julian Ostarek
 */

sealed class ColorPackage

class UIColorPackage(val primaryColor: Int, val primaryDarkColor: Int,
                     val primaryTextColor: Int, val primarySecondaryTextColor: Int, val primaryDisabledTextColor: Int,
                     val accentColor: Int, val accentDarkColor: Int,
                     val accentTextColor: Int, val accentSecondaryTextColor: Int, val accentDisabledTextColor: Int,
                     val primaryIconActiveColor: Int, val primaryIconInactiveColor: Int,
                     val accentIconActiveColor: Int, val accentIconInactiveColor: Int): ColorPackage() {

    constructor(primaryColor: Int, accentColor: Int) : this(primaryColor, DynamicColorsUtil.darken(primaryColor), accentColor, DynamicColorsUtil.darken(accentColor))

    constructor(primaryColor: Int, primaryDarkColor: Int, accentColor: Int, accentDarkColor: Int) : this(primaryColor, primaryDarkColor,
            if (DynamicColorsUtil.isColorLight(primaryColor)) ColorConstants.TEXT_COLOR_LIGHT_BG else ColorConstants.TEXT_COLOR_DARK_BG,
            if (DynamicColorsUtil.isColorLight(primaryColor)) ColorConstants.TEXT_COLOR_SECONDARY_LIGHT_BG else ColorConstants.TEXT_COLOR_SECONDARY_DARK_BG,
            if (DynamicColorsUtil.isColorLight(primaryColor)) ColorConstants.TEXT_COLOR_DISABLED_LIGHT_BG else ColorConstants.TEXT_COLOR_DISABLED_DARK_BG,
            accentColor, accentDarkColor,
            if (DynamicColorsUtil.isColorLight(accentColor)) ColorConstants.TEXT_COLOR_LIGHT_BG else ColorConstants.TEXT_COLOR_DARK_BG,
            if (DynamicColorsUtil.isColorLight(accentColor)) ColorConstants.TEXT_COLOR_SECONDARY_LIGHT_BG else ColorConstants.TEXT_COLOR_SECONDARY_DARK_BG,
            if (DynamicColorsUtil.isColorLight(accentColor)) ColorConstants.TEXT_COLOR_DISABLED_LIGHT_BG else ColorConstants.TEXT_COLOR_DISABLED_DARK_BG,
            if (DynamicColorsUtil.isColorLight(primaryColor)) ColorConstants.ICON_COLOR_ACTIVE_LIGHT_BG else ColorConstants.ICON_COLOR_ACTIVE_DARK_BG,
            if (DynamicColorsUtil.isColorLight(primaryColor)) ColorConstants.ICON_COLOR_INACTIVE_LIGHT_BG else ColorConstants.ICON_COLOR_INACTIVE_DARK_BG,
            if (DynamicColorsUtil.isColorLight(accentColor)) ColorConstants.ICON_COLOR_ACTIVE_LIGHT_BG else ColorConstants.ICON_COLOR_ACTIVE_DARK_BG,
            if (DynamicColorsUtil.isColorLight(accentColor)) ColorConstants.ICON_COLOR_INACTIVE_LIGHT_BG else ColorConstants.ICON_COLOR_INACTIVE_DARK_BG)
}

class DominantColorPackage(val dominantColor: Int, val dominantColorDark: Int,
                           val dominantTextColor: Int, val dominantSecondaryTextColor: Int, val dominantDisabledTextColors: Int,
                           val dominantIconActiveColor: Int, val dominantIconInactiveColor: Int): ColorPackage() {

    constructor(dominantColor: Int) : this(dominantColor, DynamicColorsUtil.darken(dominantColor))

    constructor(dominantColor: Int, dominantColorDark: Int) : this(dominantColor, dominantColorDark,
            if (DynamicColorsUtil.isColorLight(dominantColor)) ColorConstants.TEXT_COLOR_LIGHT_BG else ColorConstants.TEXT_COLOR_DARK_BG,
            if (DynamicColorsUtil.isColorLight(dominantColor)) ColorConstants.TEXT_COLOR_SECONDARY_LIGHT_BG else ColorConstants.TEXT_COLOR_SECONDARY_DARK_BG,
            if (DynamicColorsUtil.isColorLight(dominantColor)) ColorConstants.TEXT_COLOR_DISABLED_LIGHT_BG else ColorConstants.TEXT_COLOR_DISABLED_DARK_BG,
            if (DynamicColorsUtil.isColorLight(dominantColor)) ColorConstants.ICON_COLOR_ACTIVE_LIGHT_BG else ColorConstants.ICON_COLOR_ACTIVE_DARK_BG,
            if (DynamicColorsUtil.isColorLight(dominantColor)) ColorConstants.ICON_COLOR_INACTIVE_LIGHT_BG else ColorConstants.ICON_COLOR_INACTIVE_DARK_BG)

}