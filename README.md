Stunner fundation
==================

This repository provides a web application which contains several examples and it built on top of most of the same
libraries as [Stunner](https://github.com/kiegroup/kie-wb-common/tree/master/kie-wb-common-stunner) does.

Fundation libraries
-------------------

The following libraries are the considered the pilars for Stunner:

  - [GWT](http://www.gwtproject.org/doc/latest/DevGuide.html)
  - [AppFormer](http://appformer.org/) ([docs](http://www.uberfireframework.org/docs/))
  - [Errai](http://docs.jboss.org/errai/latest/errai/reference/html_single/)
  - [Lienzo](https://github.com/ahome-it/lienzo-core/wiki)

Web Application
-------------------

This repository results in a web application that can be deployed in any servlet container.

![Home perspective](/images/home-perspective.png)

It provides two screens:

  - *Lienzo Examples* - contains examples for Lienzo
  - *Widgets Example* - contains an example with a patternfly component and also calls some backend service

Usage
-----

If you plan to run the application inside an IDE, please previously look at
the [IDE configuration](https://github.com/kiegroup/kie-wb-common/tree/master/kie-wb-common-stunner#ide-environment-setup) section.

Building:

        mvn clean install -DskipTests

Running:

        mvn clean gwt:run

Tests:

        mvn clean test

Requires Java 1.8+ and Maven 3.5.4+

Exercises
---------

See some [exercises](/EXERCISES.md) for getting started on the code

