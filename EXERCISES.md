Exercises
=========

Exercise #1
-----------

1. UI

* Update the [WidgetsExample](/src/main/java/org/roger600/stunner/fundation/client/examples/widgets/WidgetsExample.java) (and View)
by adding some new [Patternfly](https://www.patternfly.org/) components:
    * Add a new text input control with label *name*. It must ensure text only contains characters and is not empty.
    * Add a radio button control with label *gender* and values *male* and *female*
    * Add a date input control with label *birth date*
    * Add a new button with text *greet*

2. Service

* Update the [WidgetsExampleService](/src/main/java/org/roger600/stunner/fundation/shared/WidgetsExampleService.java) with a new method as:

        String greet(String name, String gender, int birthMonth, int birthYear);

* Update the service ([WidgetsExampleServiceImpl](/src/main/java/org/roger600/stunner/fundation/backend/WidgetsExampleServiceImpl.java)) by implementing the *greet* method by resulting as:

        Hello <name>, you was born in <birthMonth>/<birthYear>. Ah, and you're a <gender>. Here is the server, nice to meet you!

3. Goal

* Once filling the *name*, *gender*, *birth date* and clicking on *greet*, it has to perform a call to *WidgetsExampleService::greet* with the value from the UI

* The service will respond with some greeting message, which has to be displayed in the widget by using an [alert](http://getbootstrap.com/components/#alerts) (*success* type)

4. Unit tests

* Update both [WidgetsExampleServiceImplTest](/src/test/java/org/roger600/stunner/fundation/backend/WidgetsExampleServiceImplTest.java) and [WidgetsExampleTest](/src/test/java/org/roger600/stunner/fundation/client/examples/widgets/WidgetsExampleTest.java) properly,
by ensuring new logic has been tested

Exercise #2
-----------

1. Lienzo / IoC / CDI

* Create a new "Lienzo" example named *Exercise2*

So a new *Exercise2* entry MUST appear in the selector for the Lienzo examples screen:

![Lienzo examples](/images/lienzo-examples-dropdown.png)

* It will render a canvas with the following shapes:
    * A rectangle as:
        * X: 10
        * Y: 10
        * WIDTH: 50
        * HEIGHT: 25
        * FILL COLOR: RED
    * A polygon as:
        * X: 200
        * X: 150
        * POINTS: 5
        * STROKE COLOR: BLUE
        * STROKE WIDTH: 1.5
    * A circle as:
        * X: 10
        * X: 150
        * FILL COLOR: YELLOW

2. Goal

* Once double clicking on the circle:
    * The rectangle will be scaled x2
    * The polygon will be scaled x2 by using some animation which takes 3seconds
    * Once the animation above completes, the circle's fill color will be changed to GREEN

3. Unit tests

* Add unit tests for new stuff (at least should be there some tests for the new presenter and view classes)

Exercise #3
-----------

* Create a new portable type called *LienzoExamplesContent* (eg: see [WidgetsExampleContent](/src/main/java/org/roger600/stunner/fundation/shared/WidgetsExampleContent.java)) with the following fields:
    * `x`: `double`
    * `y`: `double`
    * `color`: `String`

* Declare a new service called *LienzoExamplesService* (eg: see [WidgetsExampleService](/src/main/java/org/roger600/stunner/fundation/shared/WidgetsExampleService.java)) with the following signature:

        LienzoExamplesContent update(double x, double y);

* Create the service (implementation) for *LienzoExamplesService* (eg: see [WidgetsExampleServiceImpl](/src/main/java/org/roger600/stunner/fundation/backend/WidgetsExampleServiceImpl.java))
    * Considering the input arguments as `args.x`, `args.y`, returns a *LienzoExamplesContent* instance with values:
        * `x`: `args.x * 2`
        * `y`: `args.y * 2`
        * `color`: generate a randon RGB color string

* Update the recently new lienzo example *Exercise2* (created in the previous exercise) as:
    * Add a Caller to the *LienzoExamplesService* service (`Caller<T>`)
    * Once dragging the circle on top of the rectangle, it will perform a call to the *LienzoExamplesService* service
    * The service will receive the rectangle's location coordinates (`x` and `y`) and will result in some *LienzoExamplesContent*
    * The rectangle itself will be updated to the location and fill color given by the *LienzoExamplesContent* (the service result)

