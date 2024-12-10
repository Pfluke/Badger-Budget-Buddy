// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id ("org.jetbrains.kotlin.jvm") version "2.0.21"
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false
}
