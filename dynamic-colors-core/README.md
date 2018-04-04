# Dynamic Colors
### Install
This library is used to generate colors from bitmaps. It wraps around `Palette` and simplifies its API. To install, follow the instructions in the main readme. Next add this to your `build.gradle`
```
compile('com.github.SubstanceMobile.SDK:dynamic-colors:-SNAPSHOT'@aar){
    transitive = true
}
```

### API
The basic use for this library is this:
```
Bitmap bitmap = //GET THE BITMAP FROM SOMEWHERE;
DynamicColors.from(bitmap).generate(new DynamicColorsCallback() {
    @Override
    public void onColorsReady(ColorPackage colors) {
        //Do whatever you need with the loaded colors
    }
}, true);
```

`DynamicColors.from();` supports `Bitmap`, `Uri`, `String` (Path to file), and `File`.

Once you get a `DynamicColors` instance, you can start generating images with the following methods:
`generate(DynamicColorsCallback, Boolean)` The default generator. It uses smart picking (more on that later) and it has the option to use smart text picking (again, more on that later)
`generate(Boolean)` Calls the above method with the default callback you set.
`generateOnExecutor(Executor, DynamicColorsCallback, Boolean)` Same as above. It just gives you the option to use your own executor.
`generateSimple(DynamicColorsCallback)` This generator uses simple picking. It grabs the dark vibrant color as primary and vibrant as accent.
`generateSimple()` Calls the above method with the default callback.
`generateSimpleOnExecutor(Executor, DynamicColorsCallback)` Same as above. It just gives you the option to use your own executor.

Your data will be returned in the form of a `ColorPackage`. This class is pretty self-explanatory. It has simple getters containing color values. The color values contained are:
Primary Color, Main Primary Text Color, Main Secondary Text Color, Main Disabled Text Color, Main Active Icon Color, Main Inactive Icon Color, Accent Color, Accent Primary Text Color, Accent Secondary Text Color, Accent Disabled Text Color, Accent Active Icon Color, and Accent Inactive Icon Color

---

For configuration it is pretty simple. To set default colors, call `DynamicColorsOptions.setDefaultColors(ColorPackage);`. You can also set a default callback using `DynamicColorsOptions.setDefaultCallback(DynamicColorsCallback);`

### Features
###### Smart Picking
This feature calculates the most used color palette from the albumTitle art, giving you the most accurate color possible using palette. This system has been used before in [GEM Player](https://github.com/SubstanceMobile/GEM) and is more accurate than standard color extraction methods.

###### Smart Text Picking
Palette tends to make the wrong choices about text color. This is why smart text picking was created. It uses the lightness of the primary color in order to choose primary, secondary, and disabled text colors. Same thing happens with accent. All of the colors used are from the material guidelines. 
