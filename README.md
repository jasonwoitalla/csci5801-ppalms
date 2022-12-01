# CSCI 5801 PPALMS
This is group 24's implementation of Parson's Problem Appliance for Learning Management Systems (PPALMS).

## How to build
This program is written in Java so it must be built before it can be used. You can use any java compiler to build the program.

## How to run
The main function for this program is in the `Driver.java` class. So run that file to enter the command line interface.

## How to load code
Included in the files is a code directory your code can go into there. Then the command line will ask you to enter a file
for input. You can then enter `code/<my filename>` to load the file into the program. You should see the contents of the file
printed onto the terminal.

## How to annotate the file
The command line gives you a number of annotation options. The first is <b>commenting</b>, enter `c <line number>` to comment 
a line number. The file will then be reprinted to the terminal and the line is now green with a `#` next to it to indicate it has been
commented. You can uncomment the file by typing the same command in. Commented lines will be excluded from question generation.

Lines can also be <b>grouped</b> together in case the order of them does not matter. To create a line group you can enter `t <start line> <end line>`
The start line and end line are inclusive line numbers to mark where the group starts and end. The first number must be smaller than
the second number and the lines can't be apart of more than 1 group. Lines that are grouped up will be highlighted yellow and have a `|`
symbol in front to indicate a group.  

Groups can be removed by finding their group number. A lines group number can be found on the second line of the group. The line's number
will have been replaced by something that looks like `G00` where the `00` is the group number so `G01` is group 1 and `G09` is group 9.
Remove a group by entering `r <group number>`.

## Generate Questions
This version of PPALMS does not support question generation. However, the program is ready for generation at this point. Once generation is
supported you enter `g` into the terminal to generate questions.
