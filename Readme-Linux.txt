javac -d target/classes src/main/java/tetris/*.java
javac -d target/test-classes -cp lib/*:target/classes src/test/java/tetris/*.java
java -jar lib/junit-platform-console-standalone-1.9.1.jar -cp target/classes:target/test-classes --scan-class-path
