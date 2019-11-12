# Stopwatch

The purpose of this project is to research the performance of a simple stopwatch Android app,
to be compared to a Flutter based stopwatch.

It is my assumption that a native Java Android application will have more performance than
a Flutter based app.

This project will help me test my assumption.

## Java 7 vs Java 8
The first version of the code was written in Java 7.
Just out of curiosity I wanted to know if changing the code to Java 8 and replacing anonymous 
classes with *lambdas* would have any impact on the performance.

It turns out that yes, for some reason there was a drop of 4MB of memory usage (from 88MB to 84MB) 
while profiling in *debug* mode. It's not very significant drop but I changed the code to Java 8 
to be able to have the cleaner code that is achieved with *lambdas*.

The huge amount of memory is due to the *debug* mode. I created a release version of the Java 7
version of the app and it used *only* 27MB. I still think this is a huge memory usage for just
a stopwatch but it would require further investigation.

CPU performance in both Java 7 and Java 8 oscillated between 7 and 10% of CPU usage in *debug* mode 
when updating the screen every 30ms.




