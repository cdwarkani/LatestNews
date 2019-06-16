The following are the details about the task.

Github Link of the task :: https://github.com/cdwarkani/LatestNews.git

The following are the technical details implemented in the task.

I have used the following API from https://newsapi.org/  :: https://newsapi.org/v2/everything?q=business&sortBy=publishedAt&apiKey=76985723947c4d4f841bfe548a418640

1) The app is made to support all devices above android 19 as said by you.
2) The first screen has a recycler view which gets news title, news short description and news image and renders the information. Recycler view has pagination implemented (20 JSON object are first fetched and then remaining objects are fetched as and then the list is scrolled and hits the bottom).
3) The API sometimes returns objects with and without an image so the recycler is made with the support of two different layouts, one with image and other without it.
4) On clicking of any recycler view item, it takes you to the news detail page where the news is displayed with the following details - news image if any, title, description, author of news, news time and date. All these are fetched from the API which was sent from the main screen via bundle to the detail screen.
5) I have made two layouts at present - sw320dp and sw360dp.
6) The libraries used in the task were -
1. Picasso for efficient image rendering into an image view
2. fast networking library - the one with more than 4000 stars in GitHub and handles threading in an efficient way. 

Time taken for completing the project: 4 to 5 hours for coding the logic and one hour for fixing warnings and making the code warning and bug-free.
