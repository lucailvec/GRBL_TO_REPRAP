# GRBL_TO_REPRAP
A usefull translator that translates one file of gcode [grbl compatible] into another file of sanitized gcode [repetier firmware compatible].

## Getting Started

This software is maded after a lot of spent searching a good FREE cam. **Fusion 360**, i think, is the best on the internet.
Anyway if you want to use this transaltor for translate a random file of gcode **probably** it's only sanitize the gcode without removing any G-command.

## Prerequisities

I tested with: 
* java version "1.8.0_77"
* gradle version "2.9"

## Installing

To use the program you must:

```
git clone https://github.com/lucailvec/GRBL_TO_REPRAP.git
cd GRBL_TO_REPRAP
./gradlew build
```
The last command give you in the folder 
**
./build/libs/
**

the file **GCODESanitizer.jar** .

End with an example of getting some data out of the system or using it for a little demo

## Using 

To use the program you run in the main folder:

```
java -cp build/libs/GCODESanitizer.jar main.GCODESanitizer path/fileName.gcode
```

and if the file exist you will see the transale :

```
Change str: G0 Z5 Into : G1 Z5
Change str: X18.221 Y11.778 Into : G1 X18.221 Y11.778
```

## Running the tests

The gradle envoironment test and build automatically. You can customize the build.gradle if you want, if not when execute:

```
./gradlew build
```

if the test or program build fail you get a message such as:

```
* Try:
Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output.

BUILD FAILED

Total time: 4.971 secs
```

## Authors

* **Luca Vecchi** - *Initial work* - [lucailvec](https://github.com/lucailvec)

See also the list of [contributors](https://github.com/lucailvec/GRBL_TO_REPRAP/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
