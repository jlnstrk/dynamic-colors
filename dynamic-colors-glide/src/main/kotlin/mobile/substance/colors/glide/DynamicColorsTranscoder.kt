/*
 * Copyright 2017 Julian Ostarek
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

package mobile.substance.colors.glide

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Option
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder
import mobile.substance.colors.DominantColorPackage
import mobile.substance.colors.DynamicColors
import mobile.substance.colors.UIColorPackage

class DynamicColorsTranscoder(private val context: Context) : ResourceTranscoder<Bitmap,
        DynamicColorsWrapper<*>> {
    private val bitmapPool: BitmapPool
        get() = Glide.get(context).bitmapPool

    @Suppress("IMPLICIT_CAST_TO_ANY")
    override fun transcode(toTranscode: Resource<Bitmap>, options: Options): Resource<DynamicColorsWrapper<*>> {
        val bitmap = toTranscode.get()
        val typeClass = options[Option.memory<Class<*>>(TRANSCODE_TYPE_PARAM)]
        val colorPackage = when (typeClass) {
            UIColorPackage::class.java -> {
                val primaryMode = options.get(Option.memory(UI_COLORS_MODE_PRIMARY,
                        DynamicColors.MODE_RANGE_PRIMARY_NEUTRAL))
                val accentMode = options.get(Option.memory(UI_COLORS_MODE_ACCENT,
                        DynamicColors.MODE_RANGE_ACCENT_NEUTRAL))
                DynamicColors.from(bitmap)
                        .extractUiColors(primaryMode!! or accentMode!!)
            }
            DominantColorPackage::class.java -> DynamicColors.from(bitmap).extractDominantColor()
            else -> throw IllegalArgumentException("option $TRANSCODE_TYPE_PARAM must be either " +
                    "Class<UIColorPackage> or Class<DominantColorPackage>, it was ${typeClass!!.name}")
        }
        val wrapper = DynamicColorsWrapper(context.resources, bitmap, colorPackage)
        return DynamicColorsResource(wrapper, bitmapPool)
    }

    companion object {
        const val TRANSCODE_TYPE_PARAM = "transcode_type_param"
        const val UI_COLORS_MODE_PRIMARY = "primary_mode"
        const val UI_COLORS_MODE_ACCENT = "accent_mode"
    }

}