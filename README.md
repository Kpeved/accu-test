# Accu weather app

A small weather app, which utilises the best and latest android best practices

### Architecture: 
Modularised MVVM architecture. It has 3 layers: core layer, feature, and app. All of these layers are separated according to the clean architecture principles. 
Core layer doesn't know anything about other layers, feature layer knows about core layer but doesn't know about App layer

![image](https://github.com/Kpeved/accu-test/blob/master/art/architecture.png)
### Navigation
Navigation was done by using Navigator interface. This interface is located in the core module and implemented in the App module. 
Feature module has no idea about implementation of this interface, but can call it and switch between any fragments.

### Reactive programming
For async requests a RxJava v2 was used. A state machine was updated using RxRelay 

### Dependency injection
Dependency injection was done by using Dagger2 

### Other libraries
Network: Retrofit, Gson
Logging: Timber
Tests: espresso