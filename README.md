# HBV202GLibrarySystem

## Description
This program is a command-line interface (CLI) program, simulating a library system. 
You can add books and users to the library and then borrow and return books. For every command in
the program you can type -help in the program or open the [HelpText](helpText.txt) text file.

## Supported Maven Goals

- `mvn compile`: Compiles the source code of the project.
- `mvn test`: Runs the unit tests using a suitable unit testing framework such as JUnit.
- `mvn package`: Packages the compiled code and formats it into a distributable format, like a JAR or WAR.
- `mvn exec:java`: Executes the specified main method of the implementation.

## Install and run

### Installing
Clone the [repository](https://github.com/ArnthorAtli/LibrarySystem)  to your local machine. 

### Building and running
You can either use the supported Maven goals to build and run the program, or you can use the
shell scripts provided in the [shellScripts](./shellScripts) folder to compile, run, create a jar and run the jar. Just make sure
you are working from the root directory when you execute the commands.
## UML
[UML](UML/library_system.jpg)

## License
[MIT License](LICENSE)
