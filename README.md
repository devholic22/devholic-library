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

### Get Google user information function (OAuth2)
If you have Access-Token about Google Server, you can get Google user information (HashMap) via this library.
#### Example
```java
import java.io.IOException;
import devholic.library.oauth2.google.GoogleTokenAgent;
public static void main(String[] args) throws IOException { // if access-token has problem, IOException occur.
    Map<String, String> userResource = GoogleTokenAgent.getUserResource("ACCESS_TOKEN");
    for (String key : userResource.keySet()) {
        System.out.println(key + ": " + userResource.get(key));
    }
}
```
![스크린샷 2023-09-01 오후 4 26 18](https://github.com/devholic22/devholic-library/assets/90085154/74ff2bdf-e9af-48fe-99d8-502d299a8ef2)

### Get Github user information function (OAuth2)
If you have Access-Token about Github Server, you can get Github user information (HashMap) via this library.  
when you want "bio" value, use `result.get(key).split(" ")` (because bio key has many values)
#### Example
```java
import java.util.Map;
import java.io.IOException;
import devholic.library.oauth2.github.GithubTokenAgent;
public static void main(String[] args) throws IOException { // if access-token has problem, IOException occur.
    Map<String, String> result = GithubTokenAgent.getUserResource("ACCESS_TOKEN");
    for (String key : result.keySet()) {
        System.out.println(key + ": " + result.get(key));
    }
}
```
<img width="1169" alt="스크린샷 2023-09-02 오전 1 35 22" src="https://github.com/devholic22/devholic-library/assets/90085154/5ad9e264-2ac5-40b1-b63e-7e9c90a5f471">
