# Abstract Factory aka. "Factory of Factories"

GoF Design Patterns -> Creational Design Patterns

- [Example_1](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/gof/creational/abstract_factory) - Example that demonstrates 

## What problems does it solve? Why to use it?
Abstract Factory also known as "Factory of Factories" provides an interface for creating families of related or dependent objects 
without specifying their concrete classes. In other words, this model allows us to create objects 
that follow a general pattern.

## When to use it?
- The client is independent of how we create and compose the objects in the system
- The system consists of multiple families of objects, and these families are designed to be used together
- We need a run-time value to construct a particular dependency

## Pros:
- Client does't know the concrete classes
- Easy to extend/ add support for new families

## Cons:
- To support new features (properties, methods and etc.), we have to add them to the generic classes
and implement them for each family

## How can be improved

## Examples from Java API
Recognizeable by creational methods returning the factory itself which in turn can be used to create another abstract/interface type
```
- javax.xml.parsers.DocumentBuilderFactory#newInstance()
- javax.xml.transform.TransformerFactory#newInstance()
- javax.xml.xpath.XPathFactory#newInstance()
```

## Examples

### Example 1 - How to implement it?
Let's say, we want to build some user interface, which consists of a text field and a button.
We may run our application in android mode or in swift mode, which will be determined at runtime.

To solve this problem, we need to:

1). Create our generic family classes
- InputText - generic input field
- Button - generic button
- UIFactory - generic ui components factory

2). Crete concrete classes for the "android" family classes
- AndroidInputText - android input field
- AndroidButton - android button
- AndroidUIFactory - android ui components factory

3). Crete concrete classes for the "swift" family classes
- SwiftInputText - android input field
- SwiftButton - android button
- SwiftUIFactory - android ui components factory

4). Create FactoryMaker class, which responsibility is to return the component factory, depending on the runtime type
```java
public class FactoryMaker {

    public static UIFactory getFactory(String choice) {
        UIFactory factory = null;
        if (choice.equals("Android")) {
            factory = new AndroidUIFactory();
        } else if (choice.equals("Swift")) {
            factory = new SwiftUIFactory();
        }
        return factory;
    }
}
```

OR We can use enumeration to return the factory, which is needed

```java
public enum Platform {
    ANDROID,
    SWIFT;

    public UIFactory getFactory() {
        UIFactory factory = null;
        switch (this) {
            case ANDROID:
                factory = new AndroidUIFactory();
                break;
            case SWIFT:
                factory = new SwiftUIFactory();
                break;
            default:
                break;
        }
        return factory;
    }
}
```

5). Example usage
```java
public class _Main {

    public static void main(String[] args) {
        runApp("Android");
        runApp("Swift");
    }

    private static void runApp(String type){
        UIFactory factory = FactoryMaker.getFactory(type);

        InputText input = factory.createInput();
        input.setValue("Hello, " + type);

        Button button = factory.createButton();
        button.click();

        input.submit();
    }
}
```

OR if we use ENUM
```java
public class _Main {

    public static void main(String[] args) {
        initApp(Platform.ANDROID);
        initApp(Platform.SWIFT);
    }

    private static void initApp(Platform platform) {
        UIFactory factory = platform.getFactory();

        InputText input = factory.createInput();
        input.setValue("Hello, " + platform.name());

        Button button = factory.createButton();
        button.click();

        input.submit();
    }
}
```


