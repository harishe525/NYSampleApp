The app has following packages:


1)data: It contains all the data accessing and manipulating components.
2)di: Dependency providing classes using Dagger2.
3)list: View classes along with their corresponding ViewModel.
4)util: Utility classes.


Libraries used in this project
   . LiveData
   . ViewModel
   . Data binding
   . Junit

Retrofit is an awesome type-safe HTTP client for Android and Java built by awesome folks at Square. Retrofit makes it easy to consume JSON or XML data which is parsed into Plain Old Java Objects (POJOs).

RxJava is a reactive implementation to bring this concept to the Android platform. Android applications are a perfect place to start your exploration of the reactive world. It’s even easier with RxAndroid, a library that wraps asynchronous UI events 

Here i used to Android MVVM design pattern to develop the app
MVVM stands for Model, View, ViewModel.

Model: This holds the data of the application. It cannot directly talk to the View. Generally, it’s recommended to expose the data to the ViewModel through Observables.

View: It represents the UI of the application devoid of any Application Logic. It observes the ViewModel.

ViewModel: It acts as a link between the Model and the View. It’s responsible for transforming the data from the Model. It provides data streams to the View. It also uses hooks or callbacks to update the View. It’ll ask for the data from the Model.
The following flow illustrates the core MVVM Pattern.



