gradle-trang
============

Trang plugin for Gradle.

Description
-----------

This plugin adds a task called 'trang'. This task converts XML schema files using [Trang](http://www.thaiopensource.com/relaxng/trang.html).

By default, all schema files from the directory 'src/main/schemas' are translated to schema files in the directory 'generated/schemas'. These directories can be changed by setting the properties 'sourceDirectory' and 'targetDirectory' of the task.
A schema file is a file with one of the extensions supported by Trang, i.e., 'rng', 'rnc', 'dtd', 'xsd', and 'xml'.

By default, the schemas are translated into XSD schemas. This can be changed by setting the property 'targetExtension' to one of the target extensions supported by Trang, i.e., 'rng', 'rnc', 'dtd', and 'xsd'.

For information about the conversion performed by Trang, please check [Trang's documentation](http://www.thaiopensource.com/relaxng/trang-manual.html).

Usage
-----

First, install the plugin into the local maven repository by executing the following task of the plugin's build script:
```
gradle install
```

Then, use the plugin as follows:

```groovy
buildscript {
    repositories {
        mavenCentral()
        mavenLocal()
    }
    dependencies {
        classpath 'org.hsudbrock:gradle-trang-plugin:0.1-SNAPSHOT'
    }
}
apply plugin: 'trang'
trang.targetExtension = 'rng'
```
