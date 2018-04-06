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

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import mobile.substance.colors.ColorPackage

/**
 * @author Julian Ostarek
 */
open class DynamicColorsWrapper<out CP : ColorPackage>(resources: Resources, bitmap: Bitmap,
                                                       val colorPackage: CP) : BitmapDrawable(resources, bitmap)