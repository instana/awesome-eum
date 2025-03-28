plugins {
    id("com.android.library")
    id("com.instana.android-agent-plugin")
}
configurations {
    create("copyDependencies")
}
android {
    namespace = "com.example.newbinding"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation("com.instana:android-agent-runtime:6.1.2") //Instana agent version
    implementation("com.instana:android-agent-plugin:6.1.2") //Instana agent version
    "copyDependencies"("com.instana:android-agent-runtime:6.1.2")
    "copyDependencies"("com.instana:android-agent-plugin:6.1.2")

}
configurations.all {
    resolutionStrategy {
        eachDependency {
            if (requested.group == "com.instana" && requested.name == "instana") {
                useTarget("com.instana:android-agent-runtime:6.1.2")
                useTarget("com.instana:android-agent-plugin:6.1.2")
            }
        }
    }
}
project.afterEvaluate {
    tasks.register<Copy>("copyDeps") {
        from(configurations["copyDependencies"])
        into("${projectDir}/build/outputs/deps")
    }
    tasks.named("preBuild") { finalizedBy("copyDeps") }
}
