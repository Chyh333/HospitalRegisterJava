pluginManagement {
    repositories {
        // 阿里云镜像 顺序很重要，镜像放前面
        maven { setUrl("https://maven.aliyun.com/repository/google") }
        maven { setUrl("https://maven.aliyun.com/repository/gradle-plugin") }
        maven { setUrl("https://maven.aliyun.com/repository/public") }
        maven { setUrl("https://www.jitpack.io") }
        // 兜底保留官方，镜像失败才走官方
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        // 阿里云镜像
        maven { setUrl("https://maven.aliyun.com/repository/google") }
        maven { setUrl("https://maven.aliyun.com/repository/public") }
        maven { setUrl("https://maven.aliyun.com/repository/central") }
        maven { setUrl("https://maven.aliyun.com/repository/jcenter") }
        maven { setUrl("https://www.jitpack.io") }
        // 兜底官方
        google()
        mavenCentral()
    }
}

rootProject.name = "HospitalRegisterJava"
include(":app")
