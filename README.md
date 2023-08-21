# devholic-library
useful libraries made by devholic  

## How to use?
### 1. Add it in your root build.gradle at the end of repositories:
```groovy
allprojects {
    repositories {
        ....
        maven {
            url 'https://jitpack.io'
        }
    }
}
```
### 2. Add the dependency
```groovy
dependencies {
    implementation 'com.github.devholic22:discordbot-library:tag' // tag means the latest tag
}
```
## library lists
- [x] discord web-hook function (If you have discord webhook URL and content what you want to send, You can send via this library.)
