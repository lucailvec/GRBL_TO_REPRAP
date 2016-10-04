# GRBL_TO_REPRAP
A usefull translator that translates one file of gcode [grbl compatible] into another file of sanitized gcode [repetier firmware compatible].

## Getting Started

This software is maded after a lot of spent searching a good FREE cam. **Fusion 360**, i think, is the best on the internet.
Anyway if you want to use this transaltor for translate a random file of gcode **probably** it's only sanitize the gcode without removing any G-command.

## Feauture

- [x] Remove M command.
- [x] Replace fast line with G1.
- [x] Restore row without the GXX field but obviusly work with it (grbl protocol).
- [x] Add in any negative Z the command ignore endstop S1.
- [x] Testing a little bit.
- [ ] **Remove home command (G28)**
- [ ] Possibility to remove G2 - G3 with G1 command.
- [ ] Testing a little more.


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


## Using 

To use the program you run in the main folder:

```
java -cp build/libs/GCODESanitizer.jar main.GCODESanitizer path/fileName.gcode
```

and if the file exist you will see the transale procedure in sysout:


```
Change str: G0 Z5 Into : G1 Z5
Change str: X18.221 Y11.778 Into : G1 X18.221 Y11.778
```

and the new file : 
```
fileName.gcode_sanitized_
```

Open **repetierHost** doesn't show the preview but i tested the modified gcode first on a delta and then on a prusa and seems to work right.

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

<<<<<<< HEAD
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
=======
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
>>>>>>> d0a8c46118e3ddb6d741642bc5c5e033f7e5e020
