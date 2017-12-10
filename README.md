# openfass-template-java

Supports [fast fork](https://github.com/openfaas/faas/tree/fast_fork/watchdog#optimizing-for-performance)

## Prerequisite
* Deploy [OpenFaaS](https://github.com/openfaas/faas#get-started-with-openfaas)
* Install [OpenFaaS CLI](https://github.com/openfaas/faas-cli#get-started-install-the-cli)

## Usage
```
# Pull template and create a new function
faas-cli template pull https://github.com/ganesshkumar/openfaas-template-java
faa-cli new --lang java-gradle java-function

# Build and deploy to OpenFaaS
faas-cli build -f java-function.yml
faas-cli deploy -f java-function.yml

# Test your function
echo test | faas-cli invoke java-function
```

#### Current limitation
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
