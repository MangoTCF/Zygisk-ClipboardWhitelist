rootProject.name = "Clipboard Whitelist"

include(":shared", ":hideapi", ":module", ":app")

pluginManagement {
    // Vendored: the upstream maven repository (maven.kr328.app) is offline.
    includeBuild("external/ZygoteLoader")

    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
    }
    versionCatalogs {
        create("libs") {
            val refine = "4.3.1"
            val magic = "1.9"

            library("refine-processor", "dev.rikka.tools.refine:annotation-processor:$refine")
            library("refine-annotation", "dev.rikka.tools.refine:annotation:$refine")
            library("refine-runtime", "dev.rikka.tools.refine:runtime:$refine")
            library("magic-library", "com.github.kr328.magic:library:$magic")
        }
    }
}

includeBuild("external/ZygoteLoader")
includeBuild("external/MagicLibrary")
