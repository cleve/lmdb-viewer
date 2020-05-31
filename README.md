# lmdb-viewer
GUI to navigate over LMDB data

![alt text](https://raw.githubusercontent.com/cleve/lmdb-viewer/master/media/lmdb.png "Main window")

## Limitations

* Only single database files can be opened.
* Tested on Ubuntu 20.04

## Executing the App

### Linux

Get the file from: https://github.com/cleve/lmdb-viewer/releases

Extract and execute the app under the *bin* directory.

```sh
./lmdb-viewer
``` 

### All platform

Requirements:

+ Java 11+ (Javafx does not work with java < 11)
+ You will need to get javafx library (LTS) for your platform:

        https://gluonhq.com/products/javafx/

Execute the Jar including the libraries for your platform

Shell example:

```sh
#! /usr/bin/sh
java --module-path /path/javafx-sdk-11.0.2/lib --add-modules=javafx.controls,javafx.fxml -jar lmdb-viewer.jar
``` 


### Requirements for dev

+ Java 11+ (Javafx does not work with java < 11)
+ You will need to get javafx library:

    https://gluonhq.com/products/javafx/
