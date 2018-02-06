package mobile.substance.colors

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.File
import java.net.URL

sealed class DefaultSources {

    class FileSource(private val file: File) : DynamicColors.BitmapSource {
        override fun getBitmap(): Bitmap? = BitmapFactory.decodeFile(file.path)
    }

    class ResourceSource(private val res: Resources, private val resId: Int) : DynamicColors.BitmapSource {
        override fun getBitmap(): Bitmap? = BitmapFactory.decodeResource(res, resId)
    }

    class UriSource(private val context: Context, private val uri: Uri) : DynamicColors.BitmapSource {
        override fun getBitmap(): Bitmap? {
            return when (uri.scheme) {
                "file" -> BitmapFactory.decodeFile(uri.path)
                "http", "https" -> BitmapFactory.decodeStream(URL(uri.toString()).openStream())
                else -> BitmapFactory.decodeStream(context.contentResolver.openInputStream(uri))
            }
        }
    }

    class CheapSource(private val bitmap: Bitmap?) : DynamicColors.BitmapSource {
        override fun getBitmap(): Bitmap? = bitmap
    }

}

