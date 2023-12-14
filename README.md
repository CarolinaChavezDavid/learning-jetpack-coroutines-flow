#  <img width="50" alt="image" src="https://github.com/CarolinaChavezDavid/learning-jetpack-coroutines-flow/assets/77591347/4e7f8722-419a-481e-9da6-61da71ac628c"> learning-jetpack-coroutines-flow



# Jetpack Compose

<details> 

### Layouts

* **Scaffolds**  convenient layouts for combining Material Components into common screen pattern
  
</details>


# Coroutines

Coroutines are light-weighted threads. Coroutines can be suspended and resume, in contrast to blocked threads, other task can be performed while coroutines are suspended, while coroutines are suspended they don't block any thread


> a general rule in concurrent programming is to avoid shared mutable states whenever is possible


|                                                       **Structured concurrency**                                                       |                                **Unstructured concurrency**                               |
|:--------------------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------:|
| Every coroutine needs to be started in a logical scope with a limited lifetime                                                         | Threads are started globally, is a developer's responsability keep track. of the lifetime |
| Coroutines started in the same scope form a hierarchy                                                                                  | No hierarchy                                                                              |
| A parent Job won't complete until all of it's children have completed                                                                  | All threads runs completely independent from  each other                                  |
| Cancelling a parent will cancel all children,  cancelling a child won't cancel the parent or  siblings                                 | No automatic cancelation mechanism                                                        |
| If a child coroutine fails, the exception is propagated upwards and depending of the Job type either all siblings are cancelled or not | No automatic exception handling                                                           |


### Coroutine builders

* **async**
* **launch** when you don't need to access the value inside the coroutine. It returns a **Job** object
* **runBlocking**
* **withContext**

> **Job** is a reference or a representation of the code that is started with launch. Can be use to check the state in which the coroutine is and can cancel the coroutine (Job.Join() suspends the coroutine to wait till it's done to finish)

### Coroutines Scopes
   
Scopes control the lifetime of the coroutine, it defines when the coroutine is cancelled
* **Global Scope** *Not recomendable for your app*
* **View model Scope** Associated to the view model life-time
* **Life Cycle scope** is tied to the lifetime of a cycle objects as main Activities and Fragments
  
### Coroutines context 
It's the core of a coroutine, is define on the scope and consist of several context elements, the most impost are: dispatcher, job, error handler and name

+ **Dispatcher** defines on which thread or thread pool a coroutine will be launched
  + **Dispatcher.Main** Only available in applications with a user interface. This is a special thread (Android main thread) that can perform UI operations. Define as the dispatcher for the viewModelScope.
  + **Dispatcher.IO** Perform IO-related operations, blocking operations, uses shared thread pool internally, limited to 64 threads.
  + **Dispatcher.Default** Optimised CPU-intense work, uses shared thread pool internally, the maximum number of threads is equal to the number of CPU cores. A coroutine uses the Default dispatcher ir no other is defined.
+ **Job** Control the life cycle of a coroutine and also build a parent child hierarchies of coroutines

### Suspend functions

Suspend functions are special function that perform some long running operations in the main thread and can be suspended in every suspension point, can only be called from another suspend function or a coroutine. A suspend fucntion return a single value from a asynchronous operation.

* **Main-safety** The ability of calling suspend functions on the main thread in a non-blocking way


# <img width="50" alt="image" src="https://github.com/CarolinaChavezDavid/learning-jetpack-coroutines-flow/assets/77591347/3b6c5c00-7669-4ebf-8a3f-cca86ad2a187"> Kotlin-Flow

 ***<div align="center">A stream of values that are computed asynchronously</div>***
 > **stream:** Data is receive continously instead of only once in the end. </br> **Asynchronous:** The execution of the code is not blocking the main thread


A flow is a type in the Kotlin coroutine libriry, a flow is a type that can emit multiple values sequentially, as opposed to suspend functions that return only a single value. For example, you can use a flow to receive live updates from a database.

> **Reactive programming:** The foundation of reactive programming is the observe patter in which components can observer other components, receive state changes and react according to these changes. ***Live data*** Live data is a data holder that can be observed and whenever there is a new state we can react to it.
 
<details> 
  <div align="center">
  <img width="600" alt="image" src="https://github.com/CarolinaChavezDavid/learning-jetpack-coroutines-flow/assets/77591347/7c89d5c2-49f1-4161-ae97-040d3f5e5d52">
  </div>
</details>

### Flow builders

* `flowOf()` creates a flow from a fix set of values `flowOf(1, 2, 3)`
* `asFlow()` is an extension function on various types to convert them into flows `listOf(1, 2, 3).asFlow()`
* `flow{}` takes a lambda code block which means we can call regular and suspend functons.

### Flow terminal operators

Allow us to execute code in the flow

* `collect()` is a suspend function and will suspend the function until the code is completed 
* `first()`
* `last()`
* `single()` Returns only one value
* `toSet()` 
* `fold()` 
* `launchIn()` Needs scope. Is a regular function, not a suspend function, therefore it doesn't suspend the coroutine in which it is called until the flow has completed. *Executes concurrently*
* `asLivedata()` 


* 

  



