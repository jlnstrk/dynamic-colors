/*
 * Copyright 2020 Substance Mobile
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

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.File
import java.net.URL

class DefaultSources {

    class FileSource(override val context: Context, private val file: File) :
        DynamicColors.BitmapSource() {
        override fun getBitmap(): Bitmap? = BitmapFactory.decodeFile(file.path)
    }

    class ResourceSource(
        override val context: Context,
        private val res: Resources,
        private val resId: Int
    ) : DynamicColors.BitmapSource() {
        override fun getBitmap(): Bitmap? = BitmapFactory.decodeResource(res, resId)
    }

    class UriSource(override val context: Context, private val uri: Uri) :
        DynamicColors.BitmapSource() {
        override fun getBitmap(): Bitmap? {
            return when (uri.scheme) {
                "file" -> BitmapFactory.decodeFile(uri.path)
                "http", "https" -> BitmapFactory.decodeStream(URL(uri.toString()).openStream())
                else -> BitmapFactory.decodeStream(context.contentResolver.openInputStream(uri))
            }
        }
    }

    class CheapSource(override val context: Context, private val bitmap: Bitmap?) :
        DynamicColors.BitmapSource() {
        override fun getBitmap(): Bitmap? = bitmap
    }

}

