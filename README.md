# dynamic-colors

## Overview

dynamic-colors is a small and lightweight wrapper around Google's Palette API from the Android Support Library. This library simplifies and abstracts dealing with image color extraction on Android - focusing on two major usecases.

## Installation

First, add the jitpack repository to your top-level build.gradle file:
```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
Next, add the following line(s) to your module-level build.gradle file's dependencies section:
```groovy
// Include the whole library
implementation 'com.github.SubstanceMobile:dynamic-colors:<VERSION>'

// Include only the core library (excluding the Glide extensions)
implementation 'com.github.SubstanceMobile.dynamic-colors:library:<VERSION>'

// Include only the glide extensions (why would you want to do this???)
implementation 'com.github.SubstanceMobile.dynamic-colors:extensions-glide:<VERSION>'
```

## Usage

### Approaches

The library provides two ways of dealing with color extraction. Independent on which approach you're going for, you will receive primary, secondary & disabled text colors, as well as active and inactive icon colors, to be used for content you might want to display on top of surfaces that you're applying the extraction result color on.

1. #### Dominant color extraction

    When choosing dominant color extraction, the library will extract the color with the highest population from within the passed image, regardless of its lightness or other factors. The resulting colors may range from ```#FF000000``` (pure black) to ```#FFFFFFFF``` (pure white).

2. #### UI color extraction

    When choosing UI color extraction, the library will extract two colors from within the passed image. They follow targets specified to result in near perfect matches for their respective field of use. Both colors aim for a lightness of ```0.675F```, biasing the results to be slightly lighter than originally - as images tend to be rather dark, while usually lighter colors are better suited for accompanying user interfaces.
    
    The primary color target sets a strong focus on color population, while the accent color target also weighs color saturation. The primary color usually tends to be darker and less vibrant in comparison to the accent color.
    
    In a normal use case, the primary color can be used to tint bigger areas and surfaces of your user interface, while the accent color should only be used to be, well, an accent color, applied only on small portions of the interface.

### Implementation

An instance of the main class, ```DynamicColors```, can be obtained by calling static ```.from()``` methods, passing in a data source pointing to an image. While you can supply your own, custom ```BitmapSource``` implementation, the options provided out of the box will probably fit your needs:

```kotlin
// A plain Bitmap - yes, it can even be null!
DynamicColors.from(Bitmap?)
// A File
DynamicColors.from(File)
// A file path
DynamicColors.from(String)
// A Uri - file, content and http/https schemes are accepted
DynamicColors.from(Context, Uri)
// A Drawable resource
DynamicColors.from(Resources, Int)
```
