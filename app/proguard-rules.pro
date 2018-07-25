# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# We only want obfuscation
-keepattributes InnerClasses
-keepattributes Signature
-keepattributes Exceptions
-keepattributes *Annotation*
-keepattributes EnclosingMethod

# Appcompat and support
-keep interface android.support.v4.** { *; }
-keep class android.support.v4.** { *; }
-keep interface android.support.v13.** { *; }
-keep class android.support.v7.** { *; }
-dontwarn android.app.Notification
-dontwarn android.support.v7.**


-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}


# Keep the annotations
-keepattributes *Annotation*

-keep class com.stt.stt.common.webService.**{ *; }

-keepattributes Signature
-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }

# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }

-verbose
#-keepnames class * # For crashlytics
-keep class javax.** { *; }
-keep class org.** { *; }
-ignorewarnings
-dontwarn


-repackageclasses ''
-allowaccessmodification
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
-optimizations !method/inlining/*
-optimizationpasses 5
