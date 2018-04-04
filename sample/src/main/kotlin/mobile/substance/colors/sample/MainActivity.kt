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

package mobile.substance.colors.sample

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import co.metalab.asyncawait.async
import mobile.substance.colors.DynamicColors
import mobile.substance.colors.DynamicColorsUtil

class MainActivity : AppCompatActivity() {
    private lateinit var radioGroupPrimary: RadioGroup
    private lateinit var radioGroupAccent: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroupPrimary = findViewById(R.id.radioGroupPrimary)
        radioGroupAccent = findViewById(R.id.radioGroupAccent)

        findViewById<Button>(R.id.generate).setOnClickListener {
            generate()
        }
    }

    private fun generate() = async {
        var primaryMode: Int? = null
        when (radioGroupPrimary.checkedRadioButtonId) {
            R.id.primary_neutral -> primaryMode = DynamicColors.MODE_RANGE_PRIMARY_NEUTRAL
            R.id.primary_light -> primaryMode = DynamicColors.MODE_RANGE_PRIMARY_LIGHT
            R.id.primary_dark -> primaryMode = DynamicColors.MODE_RANGE_PRIMARY_DARK
        }
        var accentMode: Int? = null
        when (radioGroupAccent.checkedRadioButtonId) {
            R.id.accent_neutral -> accentMode = DynamicColors.MODE_RANGE_ACCENT_NEUTRAL
            R.id.accent_light -> accentMode = DynamicColors.MODE_RANGE_ACCENT_LIGHT
            R.id.accent_dark -> accentMode = DynamicColors.MODE_RANGE_ACCENT_DARK
        }
        val colors = await { DynamicColors.from(this@MainActivity, Uri.parse(findViewById<EditText>(R.id.source).text.toString())).extractUIColors(primaryMode!! or accentMode!!) }
        findViewById<TextView>(R.id.primaryText).apply {
            setBackgroundColor(colors.primaryColor)
            setTextColor(colors.primaryTextColor)
            text = DynamicColorsUtil.hexStringForInt(colors.primaryColor)
        }
        findViewById<TextView>(R.id.primaryDarkText).apply {
            setBackgroundColor(colors.primaryDarkColor)
            setTextColor(colors.primaryTextColor)
            text = DynamicColorsUtil.hexStringForInt(colors.primaryDarkColor)
        }
        findViewById<TextView>(R.id.accentText).apply {
            setBackgroundColor(colors.accentColor)
            setTextColor(colors.accentTextColor)
            text = DynamicColorsUtil.hexStringForInt(colors.accentColor)
        }
    }


}
