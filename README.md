# blackjack helper android app #

* D/L available in the play store at https://play.google.com/store/apps/details?id=com.sullivankw.blackjackhelper
* HELP! If anyone is interested in creating an ios version of this app, the REST API can be found here: https://github.com/sullivankw/spring-boot-blackjack-api. Currently, this Android version is running off a jar with the services of that API. It's only a couple clicks away though to get it up in running in Azure for you to code against.

### Dev Stuff ###  
* This app was created using a tab layout with a viewpager to handle the flow of entering all the data needed to process the best statistical decision.
* Bottom navigation is implemented to allow the user to easily switch between pratice mode and get help mode
* I tried to create as much seperation of layers as possible without relying on outside libraries. Singleton patterns are followed for class creations and view models for passing data to and from the UI layers
* Retrofit is included in this app. However, it is not currently being used. We are using the "non http version". The service layer was included in the project. I have a rest api available here: https://github.com/sullivankw/spring-boot-blackjack-api. It works correctly when the api is deployed and the switch is turned on in the app itself to start making the http calls instead of the embedded service layer. I used this approach with the hope of creating (with help!?) an ios version that can plug into the same services rather than creating that same logic in multiple languages. But for the time being...it doesn't matter...it's all java :)
* Firebase realtime database is used to maintain and overall leaderboard of most consecutive correct guesses by users

### Screens ###
* Choose the dealer's card

<img src="https://github.com/sullivankw/blackjack-v2-android-app/blob/master/choose_dealer_card_updated.png" width="256">

* Get statistically backed advice on the decision
<img src="https://github.com/sullivankw/blackjack-v2-android-app/blob/master/advice_updated.png" width="256">

* Try practice mode and hone your skills
<img src="https://github.com/sullivankw/blackjack-v2-android-app/blob/master/practice_mode_updated.png" width="256">

