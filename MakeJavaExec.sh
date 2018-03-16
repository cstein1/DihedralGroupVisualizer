echo "Place libraries into same folder"
jar xf scala-library.jar
jar xf [[rest of libraries]].jar
echo "place all bin files into same folder as the extracted libraries"
jar cf "name-raw.jar" .
echo "go into the executable w/ 7z and edit the last line of the manifest to say Main-Class: Main"