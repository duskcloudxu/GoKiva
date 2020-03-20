# GoKiva

## Install Instruction

The environment recommended:

- Intellij IDEA

> I very recommend this IDE as its used by litterally every java programmer in industry unless you are a programmer started Java in 20 years ago and know every detail in java lol

To run this project, simply pull it down from GitHub, run

```bash
./gradlew build
```

to build dependencies and ather that, run

``` java
./gradlew bootrun
```

to run the project, ensure your `localhost:8081` port is not being used.

Visit `localhost:8081/index` to see the current index page.

In real development, IDEA offers powerful support in gradle so I would like to use IDEA, and I think there is no reason not to try this powerful IDE.

## Configuration

The password and username to mysql is hardcoded at `src/main/java/com/timeWizard/GokivaBackEnd/ConnectionManager.java`. At first I was thinking to set up a online database for everyone but it might be not so necessary to us compared to the possible cost...So currently you could change the password and username in the ConnectionManager.java and **please do not update this modification to GitHub**.

![image-20200319224825186](https://tva1.sinaimg.cn/large/00831rSTgy1gd0ch08ystj310q0hojuw.jpg)

After you configured your database, run corresponding test of ConnectionManager in `src/test/java/com/timeWizard/GokivaBackEnd/ConnectionManagerTest.java`, if it passed, then it means the connection to the database is good.

## Test in backend

In Idea, you could generate corresponding test file by rightclick->generate->test.

![image-20200319223607538](https://tva1.sinaimg.cn/large/00831rSTgy1gd0c4abe5lj30ua0run0k.jpg)

Do remember use JUnit4 as testing library. I personnaly recommend generate a test for every method when you are developing. For example:

![image-20200319223853789](../../Library/Application Support/typora-user-images/image-20200319223853789.png)

To test `getBorrowersById` Method in BorrowersDao. By clicking the triangle icon in the left you could run or debug in test, and it would be very useful in development since you could set breakpoint and inspect your variables at any time to help you figure out the problem. Also, test is a good way to help others to understand the method you write.

![image-20200319224029752](https://tva1.sinaimg.cn/large/00831rSTgy1gd0c8rfddzj31hy0giwj7.jpg)

## Plugin recommendation

### FrontEnd

- FileWatcher

  To watch and compile your less file under resource file in real time. For anyone want to join frontend development, I recommend to install it so you could compile less file to CSS file in real time.