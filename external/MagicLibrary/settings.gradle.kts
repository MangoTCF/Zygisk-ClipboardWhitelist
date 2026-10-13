rootProject.name = "MagicLibrary"

include(":library")
include(":hideapi")

pluginManagement {
    repositories {
        mavenCentral()
        google()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
    }
}
