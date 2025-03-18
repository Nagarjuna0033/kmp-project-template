plugins {
    alias(libs.plugins.android.library.convention)
}

android {
    namespace = "org.mifos.testing"
}

dependencies {
    api(libs.kotlinx.coroutines.test)
    api(projects.core.common)
    api(libs.kotlin.test)

    implementation(libs.koin.core)
    implementation(libs.koin.test)
}