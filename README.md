# openfass-template-java

Supports [fast fork](https://github.com/openfaas/faas/tree/fast_fork/watchdog#optimizing-for-performance)

## Prerequisite
* Deploy [OpenFaaS](https://github.com/openfaas/faas#get-started-with-openfaas)
* Install [OpenFaaS CLI](https://github.com/openfaas/faas-cli#get-started-install-the-cli)

## Usage

**Pull template and create a new function**
```
$ faas-cli template pull https://github.com/ganesshkumar/openfaas-template-java

$ faa-cli new --lang java8-gradle java-function
```
**Build and deploy to OpenFaaS**
```
$ faas-cli build -f java-function.yml

$ faas-cli deploy -f java-function.yml
```
**Test your function**
```
$ echo test | faas-cli invoke java-function
You said: openfaas
```

## Updating the function
* Once you execute `faas-cli new` command, a new folder is created for your function.
* This folder is nothing but a gradle project with a rudimentary [`build.gradle`](https://github.com/ganesshkumar/openfaas-template-java/blob/master/template/java8-gradle/function/build.gradle) file.
* There **must be** a class `function.Hander` with a method `function(String requesBody, String httpMethod)` which is the starting point of your function.

## Current limitation
* The template looks for `function.Handler` class which has a method called `function`
```java
package function;

public class Handler {
    public String function(String input, String method) {
        return "You said: " + input;
    }
}
```

**Note:** These willbe addressed in a better way

