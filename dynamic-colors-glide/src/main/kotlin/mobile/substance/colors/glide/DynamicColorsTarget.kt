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

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.request.target.ImageViewTarget
import com.bumptech.glide.request.transition.Transition
import mobile.substance.colors.ColorPackage
import mobile.substance.colors.DominantColorPackage
import mobile.substance.colors.DynamicColorsOptions
import mobile.substance.colors.UIColorPackage

abstract class DynamicColorsTarget<CP : ColorPackage>(
        imageView: ImageView, private val onColorsExtractedListener: OnColorsExtractedListener<CP>)
    : ImageViewTarget<DynamicColorsWrapper<CP>>(imageView) {

    final override fun setResource(resource: DynamicColorsWrapper<CP>?) {
        view.setImageBitmap(resource?.bitmap)
    }

    final override fun onResourceReady(resource: DynamicColorsWrapper<CP>,
                                       transition: Transition<in DynamicColorsWrapper<CP>>?) {
        super.onResourceReady(resource, transition)
        onColorsExtractedListener.invoke(resource.bitmap, resource.colorPackage)
    }

    final override fun onLoadFailed(errorDrawable: Drawable?) {
        super.onLoadFailed(errorDrawable)
        onColorsExtractedListener.invoke(null, getDefaultColorPackage())
    }

    abstract fun getDefaultColorPackage(): CP

    class UIImpl(imageView: ImageView,
                 onColorsExtractedListener: OnColorsExtractedListener<UIColorPackage>)
        : DynamicColorsTarget<UIColorPackage>(imageView,
            onColorsExtractedListener) {

        override fun getDefaultColorPackage(): UIColorPackage {
            return DynamicColorsOptions.defaultUIColors.invoke()
        }

    }

    class DominantImpl(imageView: ImageView,
                       onColorsExtractedListener: OnColorsExtractedListener<DominantColorPackage>)
        : DynamicColorsTarget<DominantColorPackage>(imageView,
            onColorsExtractedListener) {

        override fun getDefaultColorPackage(): DominantColorPackage {
            return DynamicColorsOptions.defaultDominantColor.invoke()
        }

    }

}