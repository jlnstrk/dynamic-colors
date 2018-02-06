package mobile.substance.colors.extensions.glide

import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.util.Util
import mobile.substance.colors.ColorPackage

/**
 * @author Julian Ostarek
 */
class DynamicColorsResource<out CP : ColorPackage>(private val bitmapWrapper: DynamicColorsWrapper<CP>,
                                    private val bitmapPool: BitmapPool) : Resource<DynamicColorsWrapper<*>> {

    override fun getSize(): Int = Util.getBitmapByteSize(bitmapWrapper.bitmap)

    override fun recycle() = bitmapPool.put(bitmapWrapper.bitmap)

    override fun getResourceClass(): Class<DynamicColorsWrapper<*>> = DynamicColorsWrapper::class.java

    override fun get(): DynamicColorsWrapper<CP> = bitmapWrapper

}