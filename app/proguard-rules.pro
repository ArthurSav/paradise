# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/arthur/Desktop/android/android sdk/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

#Butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}


#design lib
-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }

#appcompat
-keep public class android.support.v7.widget.** { *; }
-keep public class android.support.v7.internal.widget.** { *; }
-keep public class android.support.v7.internal.view.menu.** { *; }

-keep public class * extends android.support.v4.view.ActionProvider {
    public <init>(android.content.Context);
}

#crashlytics
-keepattributes SourceFile,LineNumberTable

######################################
############### dagger ################

#Keep the annotated things annotated
-keepattributes *Annotation*

#Keep the dagger annotation classes themselves
-keep @interface dagger.*,javax.inject.*

#Keep the Modules intact
-keep @dagger.Module class *

#-Keep the fields annotated with @Inject of any class that is not deleted.
-keepclassmembers class * {
  @javax.inject.* <fields>;
}

#-Keep the names of classes that have fields annotated with @Inject and the fields themselves.
-keepclasseswithmembernames class * {
  @javax.inject.* <fields>;
}

# Keep the generated classes by dagger-compile
-keep class **$$ModuleAdapter
-keep class **$$InjectAdapter
-keep class **$$StaticInjection


#retrofit
-keep class io.c0nnectorgithub.angelist.api.** {*;}
-keepattributes Signature

-dontwarn com.squareup.**
-keep class com.squareup.** { *; }

-dontwarn rx.**
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keep class okio.**
-dontwarn okio.**
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}

-keep class sun.misc.Unsafe { *; }

#otto
-keepclassmembers class ** {
    @com.squareup.otto.Subscribe public *;
    @com.squareup.otto.Produce public *;
}

#other
-keep class com.jakewharton.** {*;}
-keep class com.f2prateek.** {*;}


# Parcel library
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keep class org.parceler.Parceler$$Parcels


#mixpanel
-keep class com.mixpanel.** { *; }
-dontwarn com.mixpanel.**

-keep class jp.wasabeef.picasso.** { *; }
-dontwarn jp.wasabeef.picasso.**



-keepattributes InnerClasses

-keep class **.R
-keep class **.R$* {
    <fields>;
}
