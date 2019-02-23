# JsonPlaceholder posts.

This application shows a list of post that are getting from the JsonPlaceholder services. The user can delete all post, mark a post like favorite and delete one post at time using the swipe to delete.

When the user tap on one post the application will show the description of the post, the user that made the post and the comments associeted with this post.

#How to run
This project is public, you can clone using the git command and open the project using Android Studio, after the sync you can run the project on your device or use an emulator.

#Libs 
This project use external libraries, next are the list of this libraries and why are using:

* Retrofit: this library help with the services calls, its helps to have a good performance on the service calls and automatically parse the json response to java objects.

* Realm: this library permit to use database interactions with java objects and methods and helps in the performance of the app.

* Dagger: this library helps to manage the dependecies of the application using dependencies injection, helps to decouple the classes and this decoupling mades the unit test more easy to do. This library has the avantage that didn't use reflection tecnique that consumes a lot of resourses.

* LiveData: Android library that manage the data changes using observators that will indicate when the data is change and that permit to update the ui without bloquing the user interactions while the app are getting the neccesary data. 

# Arquitecure

This project use the MVP pattern to have decouple the Android framework with the java code, that helps to the unit test because this test can be doing using java test tools, due to the presenters only contain java code. For manage the data the project is using interactors that permit to have different data providers and are made thinking on the single use.