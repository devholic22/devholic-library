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
    implementation 'com.github.devholic22:devholic-library:Tag' // tag means the latest tag
}
```
## library lists
### Discord web-hook function
If you have discord webhook URL and content what you want to send, You can send via this library.
#### Example
```java
import java.io.IOException;
import devholic.library.discordbot.DiscordSender;
public static void main(String[] args) throws IOException {
    DiscordSender sender = new DiscordSender("DISCORD_WEBHOOK");
    System.out.println(sender.send("hello world")); // result: send success
}
```
<img width="673" alt="스크린샷 2023-08-22 오전 12 54 36" src="https://github.com/devholic22/devholic-library/assets/90085154/7fb318eb-b572-40df-9af2-e4bd90a72987">
