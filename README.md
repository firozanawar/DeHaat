### Code Documentation: -

• The App is designed on the basic MVVM design pattern where I have used ViewModel, LiveData and ROOM.

• ViewModel is used for to maintain the data in orientation, modularity of business logic from view.

• LiveData is used to observe the data from API/Room DB, it also observes the changes in data. For view validation also LiveData is used to notify to.

• ButterKnief is used to some part of the app to show case the view binding.

• ROOM persistent library is used to cache the API data to shown in offline mode.

• 2 activities having 2 different fragment are used to show the data in portrait mode and these 2 fragment shown in first activity side by side portrait mode.

• Recyclerview and Cardview is used to show the list.

• Basic Retrofit is used to fetch the API response with proper response code, error code and message.

• To check the login seesion a fake string is store locally using SharedPrefernce. Which is just a mock of login session. And on logout this data get cleared.

• ROOM is used to store the fetch data locally separate table for Author list and Book list and relation are makde using @relation @embeded using generic common class called AuthorWithBook.

• The API provided by team are hosted by https://api.npoint.io/d88e632f6ba5c0060d35 to used it in app to get the Author and Books details

• Separate API hosted for login as fake mock API to check the session as success. https://api.npoint.io/7c16277b18694b026821

• Common ViewModel created for both the fragments to exchange the data.

• Basic java’s executors are used to for background processing. This can be replaced with RxJava but due to lack of time could’t implement RxJava.
