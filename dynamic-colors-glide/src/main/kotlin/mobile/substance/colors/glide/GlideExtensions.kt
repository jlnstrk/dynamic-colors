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

package mobile.substance.colors.glide

import android.widget.ImageView
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.Option
import com.bumptech.glide.request.RequestOptions
import mobile.substance.colors.ColorPackage
import mobile.substance.colors.DominantColorPackage
import mobile.substance.colors.DynamicColors
import mobile.substance.colors.UIColorPackage

/**
 * @author Julian Ostarek
 */

inline fun <reified CP : ColorPackage> RequestManager.loadWithColors(model: Any?): RequestBuilder<DynamicColorsWrapper<CP>> {
    return `as`(DynamicColorsWrapper::class.java)
            .load(model)
            .apply(RequestOptions()
                    .set(Option.memory(DynamicColorsTranscoder.TRANSCODE_TYPE_PARAM), CP::class.java)
                    .disallowHardwareConfig())
            as RequestBuilder<DynamicColorsWrapper<CP>>
}

inline fun RequestBuilder<DynamicColorsWrapper<UIColorPackage>>.mode(mode: Int)
        : RequestBuilder<DynamicColorsWrapper<UIColorPackage>> {
    return apply(RequestOptions()
            .set(Option.memory(DynamicColorsTranscoder.UI_COLORS_MODE_PRIMARY),
                    mode and DynamicColors.MASK_PRIMARY)
            .set(Option.memory(DynamicColorsTranscoder.UI_COLORS_MODE_ACCENT),
                    mode and DynamicColors.MASK_ACCENT))
}

inline fun RequestBuilder<DynamicColorsWrapper<UIColorPackage>>.primaryMode(primaryMode: Int)
        : RequestBuilder<DynamicColorsWrapper<UIColorPackage>> {
    return apply(RequestOptions()
            .set(Option.memory(DynamicColorsTranscoder.UI_COLORS_MODE_PRIMARY), primaryMode))
}

inline fun RequestBuilder<DynamicColorsWrapper<UIColorPackage>>.accentMode(accentMode: Int)
        : RequestBuilder<DynamicColorsWrapper<UIColorPackage>> {
    return apply(RequestOptions()
            .set(Option.memory(DynamicColorsTranscoder.UI_COLORS_MODE_ACCENT), accentMode))
}

inline fun <reified CP : ColorPackage> RequestBuilder<DynamicColorsWrapper<CP>>.into(imageView: ImageView, onColorsExtracted: OnColorsExtractedListener<CP>): DynamicColorsTarget<CP> {
    val target = when (CP::class.java) {
        UIColorPackage::class.java -> DynamicColorsTarget.UIImpl(imageView,
                onColorsExtracted as OnColorsExtractedListener<UIColorPackage>)
        DominantColorPackage::class.java -> DynamicColorsTarget.DominantImpl(imageView,
                onColorsExtracted as OnColorsExtractedListener<DominantColorPackage>)
        else -> throw IllegalArgumentException("Type parameter 'CP' must be either UIColorPackage " +
                "or DominantColorPackage")
    }
    return into(target as DynamicColorsTarget<CP>)
}