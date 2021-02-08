# Auto Bing

Small and simple app to launch multiple random searches on the Bing application, in order to get
daily reward points quickly. Choose how many searches you want to make (20 for the 60 possible
daily points), click the button and wait a few seconds for Bing launches.

A Microsoft account must be logged in the default browser of the device.

~~I make use of the __com.microsoft.bing.SEARCH__ action with the __query__ parameter.~~

Action __com.microsoft.bing.SEARCH__ is not available anymore in latest version.

~~[Microsoft Bing] application must be installed and connected to your Microsoft Live account.~~

## Known issue

Since Android 11, there is a limitation on how many activities a service can open so the number of
pages opened by the application in one command could be limited to 3 or 4.

[Microsoft Bing]: https://play.google.com/store/apps/details?id=com.microsoft.bing
