package mobile.substance.colors.extensions.glide

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.LibraryGlideModule

/**
 * @author Julian Ostarek
 */
@GlideModule
class DynamicColorsGlideModule : LibraryGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry.register(Bitmap::class.java, DynamicColorsWrapper::class.java,
                DynamicColorsTranscoder(context))
    }

}