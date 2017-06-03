# Little Finger

Little Finger is a set of simple server/client libraries which lets you add a backdoor to your Android/iOS app easily, with a single line of code. The project is based on [this anonymous Quora answer](https://www.quora.com/As-a-software-developer-how-often-do-you-leave-a-backdoor-in-your-code/answers/42071021?share=8fc91108&srid=J6SM). Say, you are a freelancer and often you must have had faced really bad clients who vanish without paying the money promised. Now, using Little Finger you can crash the app remotely if the client vanishes. And when you do receive money, you can make the app work as expected.

## How it works

The Little finger comes with a server component. The client library makes a call to this server and based on the response HTTP status code received it takes an action. If the money is received, it lets the work as expected and never makes another call to the server again. However, if money is not received then it crashes the app.

From the server, you can also send a local push notification, which app can display.

It takes only one line of code to start the Little Finger. Start it everytime in `AppDelegate(_:didFinishLaunchingWithOptions:)` if Swift or `Mainactivity.onCreate` if Android.

### HTTP Status codes

Following HTTP status codes are used:

- If the server sends `HTTP_PAYMENT_REQUIRED` (402) or `HTTP_OK` (200), then payment is expected to receive and Litte Finger lets the app work as usual. Little Finger will keep calling the server everytime it is started to know the status of the payment.
- If the server sends `HTTP_ACCEPTED` (202), then payment has been received. Little Finger updates the app's preferences so that no future network calls are made again.
- If the server sends `HTTP_CONFLICT` (409), then no payment has been received and there is a
conflict. When Little Finger receives 409, it crashes the app. The server also can notification data and Little Finger uses that to display a local notification before crashing. You can be creative and send a notification text saying "You have won $10 million dollars" or other such spammy messages to make the matter worse.

## Default behaviours

1. If there is no internet available and if app fails to make a HTTP call, then app crashes
2. If server sends any other responses, other than 202 or 409, app continues to work as expected
3. If your heroku app is down and app fails to reach then app will continue to work as expected, as heroku sends 404

Little Finger is fully open source, so you are free to fork, modify these behaviours as per your taste.

## Quick instructions

1. Install the server on Heroku (click on Deploy on Heroku button at the bottom). Give the response status code as `402` or `200`.
2. Include Little Finger calling code in your app. If [iOS](https://github.com/avinassh/little-finger-ios):
        
    ```swift
    LittleFinger.start("https://your-heroku-app.heroku.com/status/");
    ```

    if [Android](https://github.com/avinassh/little-finger-android):
    
    ```java
    LittleFinger.start(this, "https://your-heroku-app.heroku.com/status/");
    ```

3. If you have received the payment or if there is a conflict, then update the Heroku App's environment variable (usually located at - https://dashboard.heroku.com/apps/your-heroku-app/settings, `Config Variables`) accordingly.


## Detailed instructions

1. The [Little Finger server](https://github.com/avinassh/little-finger) is written in Clojure and comes with one click Heroku deploy (located at bottom). Click on it and it will do the server installation.
2. For Swift, check the [swift repo's README](https://github.com/avinassh/little-finger-ios) for detailed installation instructions.
3. For Android, check the [android repo's README](https://github.com/avinassh/little-finger-android) for detailed installation instructions.

## Demo

Very simple and crude demo apps: [iOS](https://github.com/avinassh/LFDemo-iOS) and [Android](https://github.com/avinassh/LFDemo-Android)

## Heroku 

[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy?template=https://github.com/avinassh/little-finger/tree/master)

## Name

The name Little Finger comes from GRRM's A Song of Ice and Fire character [Petyr Baelish](https://en.wikipedia.org/wiki/Petyr_Baelish).

## License

The libraries and server are released under the mighty [MIT license](https://v.mit-license.org/). Please check the `LICENSE` file of respective repository for more details.
