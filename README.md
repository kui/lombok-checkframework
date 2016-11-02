Sample project with Lombok, Checker Framework and Gradle
============================================================

See [build.gradle](build.gradle).


Run with Check Framework
-------------------------

You can see Checker Framework working with gradle and lombok. In terminal:

~~~~~~~~~~~~~~~
$ ./gradlew clean compileJava
:clean
:compileJava
/Users/kui/IdeaProjects/lombok-checkframework/src/main/java/Main.java:7: エラー: [argument.type.incompatible] incompatible types in argument.
        greet(null);
              ^
  found   : null
  required: @Initialized @NonNull Person
エラー1個
:compileJava FAILED

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':compileJava'.
> Compilation failed; see the compiler error output for details.

* Try:
Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output.

BUILD FAILED

Total time: 1.429 secs
~~~~~~~~~~~~~~~


Check test codes
--------------------

Current gradle was configured that Checker Framework checks `src/main` codes, and doesn't check test codes.

Fix as belowing if you want to check test codes too:

~~~~~~~~~~~~~~~~~~~~~diff
diff --git a/build.gradle b/build.gradle
index 8718de3..ea4114c 100644
--- a/build.gradle
+++ b/build.gradle
@@ -30,12 +30,12 @@ dependencies {
     compileOnly lombok
 }

-compileJava {
+tasks.withType(JavaCompile).all { compile ->
     def processors = [
             'org.checkerframework.checker.nullness.NullnessChecker',
             'lombok.launch.AnnotationProcessorHider\$AnnotationProcessor',
     ]
-    options.compilerArgs = [
+    compile.options.compilerArgs = [
             '-processor', processors.join(','),
             '-processorpath', "${configurations.annotationProcessors.asPath}",
             "-Xbootclasspath/p:${configurations.checkerFrameworkAnnotatedJDK.asPath}"
~~~~~~~~~~~~~~~~~~~~~
