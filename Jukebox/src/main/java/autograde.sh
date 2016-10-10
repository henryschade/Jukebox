#!/bin/bash

rm *.class > /dev/null 2>&1

echo "-------------------"
echo "Testing Song.java"
echo "-------------------"

javac SongTest.java
if [ ! $? == 0 ] # Make sure the program built
then
  echo "FAIL reason: SongTest.java did not compile"
  exit 1
fi

java SongTest
if [ ! $? == 0 ] # Make sure the program exited with success
then
  echo "FAIL reason: Song tests failed"
  exit 1
fi

echo "----------------------"
echo "Testing PlayList.java"
echo "----------------------"

javac PlayListTest.java
if [ ! $? == 0 ];then
  echo "FAIL reason: PlayListTest.java did not compile"
  exit 1
fi

java PlayListTest
if [ ! $? == 0 ]
then
  echo "FAIL reason: PlayList tests failed"
  exit 1
fi

echo "-------------------"
echo "Testing Jukebox.java (output below)"
echo "-------------------"

javac Jukebox.java
if [ ! $? == 0 ];then
  echo "FAIL reason: Jukebox.java did not compile"
  exit 1
fi

java Jukebox <<END
f
a
An awesome song
Coolio Jo
00:05
sounds/westernBeat.wav
d
2
p
1
l
i
q
END
if [ ! $? == 0 ];then
  echo
  echo "FAIL reason: Jukebox test failed"
  exit 1
fi
echo "--------------------------"
echo "DONE running Jukebox test"
echo "Verify that the output is correct. Each song should have played and must have a valid file location."
echo "Your final playlist should be"
cat << EOF
------------------
Sample Playlist (3 songs)
------------------
(0) Classical            A Classical Artist   sounds/classical.wav             181
(1) Eighties Jam         Some 80's band       sounds/eightiesJam.wav           201
(2) An awesome song      Coolio Jo            sounds/westernBeat.wav             5
------------------
EOF

javadoc -author -d doc -quiet Song.java PlayList.java
if [ ! $? == 0 ];then
  echo "FAIL reason: javadoc generation failed"
  exit 1
fi

echo
echo "Generated javadocs. Run the following command to view your documentation!"
echo "google-chrome doc/index.html"
echo
echo "Make sure that each method has the correct documentation or you will lose points"
echo "When you are done, remove the entire doc directory using \"rm -rf doc\""
echo

if [ ! -f "README" ];then
  echo "FAIL reason: No README file (or it may be named something different)"
  exit 1
else
  echo "README found. Make sure it follows the correct format."
fi

echo
echo " Keep in mind that this doesn't test EVERYTHING."
echo " You should still make sure your indentation is correct and that you are submitting"
echo " the correct files, using good coding practices, javadocs, etc."

rm *.class
