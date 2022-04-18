# National ID Batch Processor
This processor is a console application that batch processes Lithuanian National IDs, returning lists of valid and invalid IDs from a data file input.
### Pre-requisite
The [National ID Validator](https://github.com/hibikutek/nat-id) needs to be installed (see instructions)
## Installation
Currently, the build needs to be packaged itself, before you can run the .jar as a console app. 
Run the assemply plugin via maven `mvn assembly:assembly`
## Usage
The 'jar-with-dependencies' output of the assembly contains the required dependencies to run the app.
Once assembled, run it as `java nat-id-batch-processor-1.0-jar-with-dependencies.jar`. 
Use the --help flag to get more usage information