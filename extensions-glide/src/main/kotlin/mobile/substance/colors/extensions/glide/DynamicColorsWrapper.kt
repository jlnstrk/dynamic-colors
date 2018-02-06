package mobile.substance.colors.extensions.glide

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import mobile.substance.colors.ColorPackage

/**
 * @author Julian Ostarek
 */
open class DynamicColorsWrapper<out CP : ColorPackage>(resources: Resources, bitmap: Bitmap,
                                                       val colorPackage: CP) : BitmapDrawable(resources, bitmap)