/*
 * Copyright 2018 Substance Mobile
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

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.graphics.Palette
import android.support.v7.graphics.Target
import java.io.File

class DynamicColors private constructor(private val from: BitmapSource) {

    fun extractDominantColor() = extractDominantColor(from)

    fun extractLightUIColors() = extractUIColors(from, MODE_RANGE_PRIMARY_LIGHT or MODE_RANGE_ACCENT_LIGHT)

    fun extractDarktUIColors() = extractUIColors(from, MODE_RANGE_PRIMARY_DARK or MODE_RANGE_ACCENT_DARK)

    fun extractNeutralUIColors() = extractUIColors(from, MODE_RANGE_PRIMARY_NEUTRAL or MODE_RANGE_ACCENT_NEUTRAL)

    fun extractUIColors(mode: Int) = extractUIColors(from, mode)

    private fun extractDominantColor(source: BitmapSource): DominantColorPackage {
        val bitmap = source.getBitmap()
        if (bitmap != null) {
            val palette = Palette.from(bitmap)
                    .maximumColorCount(FAST_MAX_COLOR_COUNT)
                    .generate()
            try {
                val dominant = palette.dominantSwatch
                if (dominant != null) {
                    return DominantColorPackage(dominant.rgb)
                }
            } catch (ignored: Exception) {
                ignored.printStackTrace()
            }
        }
        return DynamicColorsOptions.defaultDominantColor.invoke()
    }

    private fun extractUIColors(source: BitmapSource, mode: Int): UIColorPackage {
        val bitmap = source.getBitmap()
        if (bitmap != null) {
            val primaryTarget = Target.Builder()
                    .setPopulationWeight(0.75F)
                    .setLightnessWeight(0.25F)
                    .setTargetLightness(0.675F)
                    .setMinimumLightness(if (mode and MASK_PRIMARY == MODE_RANGE_PRIMARY_LIGHT) 0.6F else 0F)
                    .setMaximumLightness(if (mode and MASK_PRIMARY == MODE_RANGE_PRIMARY_DARK) 0.75F else 1F)
                    .setExclusive(true)
                    .build()
            val accentTarget = Target.Builder()
                    .setPopulationWeight(0.5F)
                    .setSaturationWeight(0.2F)
                    .setLightnessWeight(0.3F)
                    .setTargetLightness(0.675F)
                    .setMinimumLightness(if (mode and MASK_ACCENT == MODE_RANGE_ACCENT_LIGHT) 0.6F else 0F)
                    .setMaximumLightness(if (mode and MASK_ACCENT == MODE_RANGE_ACCENT_DARK) 0.75F else 1F)
                    .setExclusive(true)
                    .build()
            val palette = Palette.from(bitmap)
                    .clearTargets()
                    .addTarget(primaryTarget)
                    .addTarget(accentTarget)
                    .generate()
            try {
                val primary = palette.getSwatchForTarget(primaryTarget)
                val accent = palette.getSwatchForTarget(accentTarget)
                if (primary != null && accent != null) {
                    return UIColorPackage(primary.rgb, accent.rgb)
                }
            } catch (ignored: Exception) {
                ignored.printStackTrace()
            }
        }
        return DynamicColorsOptions.defaultUIColors.invoke()
    }

    interface BitmapSource {
        fun getBitmap(): Bitmap?
    }

    companion object {
        const val MASK_PRIMARY = 0x0F
        const val MASK_ACCENT = 0xF0
        const val MODE_RANGE_PRIMARY_NEUTRAL = 0x01
        const val MODE_RANGE_PRIMARY_LIGHT = 0x02
        const val MODE_RANGE_PRIMARY_DARK = 0x03
        const val MODE_RANGE_ACCENT_NEUTRAL = 0x10
        const val MODE_RANGE_ACCENT_LIGHT = 0x20
        const val MODE_RANGE_ACCENT_DARK = 0x30
        const val FAST_MAX_COLOR_COUNT = 10

        @JvmStatic
        fun from(bitmap: Bitmap?): DynamicColors = DynamicColors(DefaultSources.CheapSource(bitmap))

        @JvmStatic
        fun from(context: Context, uri: Uri): DynamicColors = DynamicColors(DefaultSources.UriSource(context, uri))

        @JvmStatic
        fun from(path: String): DynamicColors = DynamicColors(DefaultSources.FileSource(File(path)))

        @JvmStatic
        fun from(file: File): DynamicColors = DynamicColors(DefaultSources.FileSource(file))

        @JvmStatic
        fun from(res: Resources, resId: Int): DynamicColors = DynamicColors(DefaultSources.ResourceSource(res, resId))

        @JvmStatic
        fun from(source: BitmapSource): DynamicColors = DynamicColors(source)

    }
}
