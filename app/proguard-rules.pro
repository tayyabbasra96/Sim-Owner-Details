# Keep Jetpack Compose classes
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**

# Keep Kotlin metadata
-keep class kotlin.Metadata

# Optional: Keep any model classes you use with reflection (e.g. Gson, Moshi, Room)
# Example:
# -keep class com.check.simownerdetailspakistan.model.** { *; }

# Optional: Keep entry points if using DI or reflection
# -keepclasseswithmembers class * {
#     public <init>(android.content.Context);
# }
