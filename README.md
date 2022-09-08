# My MessageApp 
Get Posts from Internet and view details of each one, comments, author information and make it as favorite!.

# Installation
Clone this repository and import into **Android Studio**
>git clone git@github.com:diegocs87/My_Message_App.git>

# Gradle config 
 this project was implemented with the following gradle config
 >compileSdkVersion 33
    buildToolsVersion "31.0.0"
	minSdkVersion 26
    targetSdkVersion 33
	JavaVersion.VERSION_1_8

# MVVM Architecture
For this Project  a MVVM (Model, View, View-Model) was used. Thinking about make it scalable, flexible, easy to mantain and debug. 

 * **Scalable:**
If the project expands, The MVVM structure supports it. Doesn't matter if more uses cases are added or data used is expanded, it would be easy  to implement on the current structure

* **Easy to mantain:**
MVVM provides a layer model that help us to handle observer and UI events automatically. It would make easier the integration of other collaborators to the project without making a big effort to understand how the code works.

* **Easy to debug:**
MVVM  layer model together with Kotlin languagge reduces the boilerplate code, so it is easy to find bugs as there is less code to check.

# Technologies/ Frameworks 
In this project the following Technologies/Frameworks were implemented:

* **Room DataBase**
* **Retrofit**
* **Fragments**
* **Tab Layout**
* **View Pager**
* **Live Data**
* **Dagger hilt**
