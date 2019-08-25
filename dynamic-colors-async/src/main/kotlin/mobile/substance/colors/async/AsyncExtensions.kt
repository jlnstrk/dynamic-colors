/*
 * Copyright 2019 Substance Mobile
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

package mobile.substance.colors.async

import androidx.annotation.UiThread
import kotlinx.coroutines.*
import mobile.substance.colors.DominantColorPackage
import mobile.substance.colors.DynamicColors
import mobile.substance.colors.UIColorPackage

@Suppress("NON_PUBLIC_CALL_FROM_PUBLIC_INLINE")
@UiThread
inline fun DynamicColors.extractDominantColor(callback: DynamicColorsCallback<DominantColorPackage>) = extractDominantColorAsync(callback)

@Suppress("NON_PUBLIC_CALL_FROM_PUBLIC_INLINE")
@UiThread
inline fun DynamicColors.extractLightUiColors(callback: DynamicColorsCallback<UIColorPackage>) = extractUiColorsAsync(DynamicColors.MODE_RANGE_PRIMARY_LIGHT
        or DynamicColors.MODE_RANGE_ACCENT_LIGHT, callback)

@Suppress("NON_PUBLIC_CALL_FROM_PUBLIC_INLINE")
@UiThread
inline fun DynamicColors.extractDarkUiColors(callback: DynamicColorsCallback<UIColorPackage>) = extractUiColorsAsync(DynamicColors.MODE_RANGE_PRIMARY_DARK
        or DynamicColors.MODE_RANGE_ACCENT_DARK, callback)

@Suppress("NON_PUBLIC_CALL_FROM_PUBLIC_INLINE")
@UiThread
inline fun DynamicColors.extractNeutralUiColors(callback: DynamicColorsCallback<UIColorPackage>) = extractUiColorsAsync(DynamicColors.MODE_RANGE_PRIMARY_NEUTRAL
        or DynamicColors.MODE_RANGE_ACCENT_NEUTRAL, callback)

@Suppress("NON_PUBLIC_CALL_FROM_PUBLIC_INLINE")
@UiThread
inline fun DynamicColors.extractUiColors(mode: Int, callback: DynamicColorsCallback<UIColorPackage>) = extractUiColorsAsync(mode, callback)

@UiThread
internal inline fun DynamicColors.extractDominantColorAsync(callback: DynamicColorsCallback<DominantColorPackage>): Job {
    return GlobalScope.launch(Dispatchers.Main) {
        val dominantColorPackage = withContext(Dispatchers.Default) { extractDominantColor() }
        callback.onColorsReady(dominantColorPackage)
    }
}

@UiThread
internal inline fun DynamicColors.extractUiColorsAsync(mode: Int, callback: DynamicColorsCallback<UIColorPackage>): Job {
    return GlobalScope.launch(Dispatchers.Main) {
        val uiColorPackage = withContext(Dispatchers.Default) { extractUiColors(mode) }
        callback.onColorsReady(uiColorPackage)
    }
}
