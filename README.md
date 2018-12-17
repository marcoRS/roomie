Roomie (Android Room & Kotlin Sample)
============================

This is an API sample to showcase how to use [Room](https://developer.android.com/topic/libraries/architecture/room.html),
with Kotlin coroutines. 

Introduction
-------------

### Functionality
This sample app shows a list of words stored using Room database. The user can add new words or delete all in one operation. 

### Implementation

#### Data layer

The database is created using Room and has one entity: a `Word`. Room generates the corresponding SQLite table at
runtime.

Queries are executed in the `WordDao` class. The word retrieval is done via a `LiveData` object.
Every time the user data is updated, the LiveData object will emit automatically, allowing to update the UI
based on the latest data. 

#### Presentation layer

The app has a Word Activity that displays the data.
The Activity works with a ViewModel to do the following:
* subscribe to the emissions of the words and updates the UI every time there is a new word emitted
* notify the ViewModel when a new word has been added

#### Third Party Libraries
* [Koin](https://insert-koin.io/) Dependency injection framework
* [Calligraphy](https://github.com/InflationX/Calligraphy) Custom fonts


