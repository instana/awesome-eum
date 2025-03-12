// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        //use vt once available for plugin
        classpath("com.instana:android-agent-plugin:6.1.2") //Instana agent version
    }
}
plugins {
    id("com.android.library") version "8.6.1" apply false
}
