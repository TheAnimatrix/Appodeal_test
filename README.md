# Appodeal_test
TESTING APPODEAL NATIVE ADS


So where's the error you ask ?

If you look at the code i've made it handle an intent in the onNativeClicked() method:

something like this startActivity(TestActivity); finish()

Alright so when you click an ad you goto testactivity , could've been a button instead but i preferred not to implement
additional buttons etc.. to demonstrate this error

when the TestActivity.class opens , there's a 1.5 second delay simulated by a Handler and then it starts the main activity
again.

Here you get the error , the ads don't load


CACHE creates another problem , try calling the cache method and not even one ad loads
