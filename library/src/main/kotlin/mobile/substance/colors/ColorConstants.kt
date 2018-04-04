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

/**
 * This class contains material values for text and icon colors.
 */
object ColorConstants {

    //Text colors
    const val TEXT_COLOR_LIGHT_BG = 0xDE000000.toInt()
    const val TEXT_COLOR_SECONDARY_LIGHT_BG = 0x8A000000.toInt()
    const val TEXT_COLOR_DISABLED_LIGHT_BG = 0x61000000.toInt()

    const val TEXT_COLOR_DARK_BG = 0xFFFFFFFF.toInt()
    const val TEXT_COLOR_SECONDARY_DARK_BG = 0xB3FFFFFF.toInt()
    const val TEXT_COLOR_DISABLED_DARK_BG = 0x80FFFFFF.toInt()
    
    //Icon colors
    const val ICON_COLOR_ACTIVE_LIGHT_BG = 0x8A000000.toInt()
    const val ICON_COLOR_INACTIVE_LIGHT_BG = 0x42000000.toInt()

    const val ICON_COLOR_ACTIVE_DARK_BG = 0xFFFFFFFF.toInt()
    const val ICON_COLOR_INACTIVE_DARK_BG = 0x4DFFFFFF.toInt()
}
