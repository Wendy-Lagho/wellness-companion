pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        // Prefer settings repositories over project repositories
        google()
        mavenCentral()
        jcenter()
    }
}


rootProject.name = "WCAPP"
include(":app")
 