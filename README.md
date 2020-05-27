# lmdb-viewer
GUI to navigate over LMDB data

![alt text](https://raw.githubusercontent.com/cleve/lmdb-viewer/master/media/lmdb.png "Main window")

## Limitations

* Only single database files can be opened.
* Search by key

## Executing the JAR 

Get the file from: https://github.com/cleve/lmdb-viewer/blob/master/release/lmdb-viewer.zip

### Requirements

+ Java 11 (Javafx does not work with java < 11)

+ You will need to get javafx library:

    https://gluonhq.com/products/javafx/

After that, in the viewer.sh file, add your path:

```sh
java --module-path /path_to_lib/javafx-sdk-11.0.2/lib --add-modules=javafx.controls,javafx.fxml -jar lmdb-viewer.jar
```

### Executing it

Add execution permission to the file ***viewer.sh*** and execute:

```sh
./viewer.sh
```