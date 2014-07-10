# Mule Coding Conventions

As best practice for team development, Mule uses coding conventions to ensure that code is clean and consistent, and that developers are able to maintain efficient productivity.  

## Coding Conventions

In general, Mule follows [Checkstyle](http://checkstyle.sourceforge.net/) conventions. Refer to this sample [checkstyle.xml](http://www.mulesoft.org/docs/checkstyle.xml) config file for further detail.


### JavaDoc Comments

As per the example below, include the `JavadocType` and `JavadocMethod` accompained by `JavadocStyle`. We highly recommend including `PackageHtml`.

```xml
<!-- http://checkstyle.sf.net/config_javadoc.html -->
<module name="JavadocMethod"/>
<module name="JavadocType"/>
<module name="JavadocVariable"/>
```

### Naming Classes

As per the example below, include all standard naming conventions.  `AbstractClassName` (which enforces Abstract prefix or Factory suffix) and `ConstantName` (which enforces all CAPS) may be excluded.

```xml
<!-- http://checkstyle.sf.net/config_naming.html -->
<module name="LocalFinalVariableName"/>
<module name="LocalVariableName"/>
<module name="MethodName"/>
<module name="PackageName"/>
<module name="ParameterName"/>
<module name="StaticVariableName"/>
<module name="TypeName"/>
<module name="MemberName"/>
<!-- <module name="ConstantName"/> -->
```

### Importing Clases

As per the example below, include `AvoidStarImport`. Though it makes Java files long, it provides clear information about classes and their use. Further, include `IllegalImport`, `RedundantImport` and `UnusedImports`.

```xml
<!-- http://checkstyle.sf.net/config_import.html -->
<module name="AvoidStarImport"/>
<module name="IllegalImport"/>
<module name="RedundantImport"/>
<module name="UnusedImports"/>
```

### File Size

Include a methodLength check to ensure that files contain no more than 2000 lines and methods contain no more then 7 parameters.

```xml
<!-- http://checkstyle.sf.net/config_sizes.html -->
<module name="FileLength"/>
<module name="ParameterNumber"/>
```

### Whitespace

As per the example below, set the `tabWidth` property to 4.

```xml
<!-- http://checkstyle.sf.net/config_whitespace.html -->
<module name="EmptyForIteratorPad"/>
<module name="NoWhitespaceAfter"/>
<module name="NoWhitespaceBefore"/>
<module name="OperatorWrap"/>
<module name="TabCharacter"/>
<module name="WhitespaceAfter"/>
<module name="WhitespaceAround"/>
```

### Modifier order

To avoid redundant modifiers on interfaces and annotations, follow the order of the Java Language specification, as per the following example. 

```xml
<!-- http://checkstyle.sf.net/config_modifiers.html -->
<module name="ModifierOrder"/>
<module name="RedundantModifier"/>
```

### Block checks

As per the following example, ensure block checks are applied so that there are no empty blocks, and all blocks are contained within braces.

```xml
<\!-\- [http://checkstyle.sf.net/config_blocks.html] \-->
<module name="AvoidNestedBlocks"/>
<module name="EmptyBlock"/>
<module name="NeedBraces"/>
<module name="LeftCurly">
 <property name="option" value="nl"/>
</module>
<module name="RightCurly">
 <property name="option" value="alone"/>
</module>
```

### Coding

As per the following example, ensure your project meets these general coding conventions.

```xml
<\!-\- [http://checkstyle.sf.net/config_coding.html] \-->
<module name="AvoidInlineConditionals"/>
<module name="DoubleCheckedLocking"/>
<module name="EmptyStatement"/>
<module name="EqualsHashCode"/>
<module name="HiddenField"/>
<module name="IllegalInstantiation"/>
<module name="InnerAssignment"/>
<module name="MagicNumber"/>
<module name="MissingSwitchDefault"/>
<module name="RedundantThrows"/>
<module name="SimplifyBooleanExpression"/>
<module name="SimplifyBooleanReturn"/>
```

### Design

As per the the following example, ensure your project enforces encapsulation and adheres to standard coding design.

```xml
<\!-\- [http://checkstyle.sf.net/config_design.html] \-->
<module name="DesignForExtension"/>
<module name="FinalClass"/>
<module name="HideUtilityClassConstructor"/>
<module name="InterfaceIsType"/>
<module name="VisibilityModifier"/>
```

### Miscellaneous
As per the following example below, ensure your project meets these miscellaneous coding conventions.

```xml
<\!-\- [http://checkstyle.sf.net/config_misc.html] \-->
<module name="TodoComment"/>
<module name="UpperEll"/>
<module name="Translation"/> <\!-\- to the Checker \-->
```

## Source File Headers

Ensure that all source files contain the following header.

```java
/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
```

## Packages

| Property                  | Default      | Description  |
|:--------------------------|:-------------|:-------------|
| `skipIntegrationTests`	 | `false`      | Skip flag for integration tests |



| Sub-project       | Description  |  
|-------------------|--------------|  
| `buildtools`      | tools to build Mule, scripts, and checkstyle configuration |
| `core`            | Mule's core API and functionality that is required by all transports distributions       |  
| `builds`          | the various distributions (refer to the Distributions section below for further details)    |  
| `examples`        | examples that come with the full Mule distribution |
| `modules`         | non-transport extensions to Mule, such as XML processing, Spring extras, or scripting support
| `tests`           | tests that can be run as part of the Mule build |
| `tools`           | tools for Mule, such as the transport and project Maven archetypes |
| `transports`      | Mule transports such as the JMS, VM, and TCP transports |
 

### Building Mule

The following table lists common goals to execute for building Mule.

|Command | Description |
|:----------|:-------------|
| `mvn clean`	 | purges any built artifacts or intermediate files (such as .class) from the target directory. Default : `target` |
| `mvn install` | installs the artifact to your local repository. Default: `~/.m2/repository`|
| `mvn test`    | runs any unit tests for this sub-project |
| `mvn -DskipTests install` |	By default, Maven runs all unit tests for each project for each build which, depending on the project, can take a long time to complete. If you wish, you can skip the tests using this command.|
 
#### Build properties

In addition to the properties he following properies, can change the behaviour of the Mule build:

| Property                  | Default      | Description  |
|:--------------------------|:-------------|:-------------|
| `skipIntegrationTests`	 | `false`      | Skip flag for integration tests |
| `skipSystemTests`         | `true`       | Skip flag for container level tests |
| `DskipPerformanceTests`   | `true`       | Skip flag for performance tests |
| `skipArchetypeTests`      | `true`       | Skip flag for the archetype tests|
| `skipVerifications`       | `false`      | Skip flag for the license check, version enforce, style checks, etc.|
| `skipInstalls`            | `false`      | Disable installation of artifacts in the local maven repository|
| `skipGpg`                 | `true`       | Skip artifact signing, as it does require special setup|
| `skipDistributions`       | `true`      | Skip flag for the distribution files creation |

To set these properties, it's necessary to pass them in the maven command line as `-DPropertyName=value` where value is optional for `true`. Therefore, to disable the distribution creation skip we could use `-DskipDistributions=false` while to skip the integration tests we can `-DskipIntegrationTests`.

It is important to remember that the rest of the maven plugins flags are still applicable, for instance to disable tests in the surefire plugin we could do: `-DskipTests`.

#### Distributions

When you package Mule for distribution, all distributions and related resources are located in the distributions sub-project. 

For performance's sake, the distributions are *not* built from the project's top-level directory by default. You may either build a distribution from its own directory, or disable the `distributions` skip flag  by adding `-DskipDistributions=false` to your Maven command line.

The table below offers a brief description of each type distribution.

|Type                           | Sub-project	                   |Description       |Breadth            |
|:------------------------------|:-------------------------------|:-----------------|:------------------|
| Full Standalone Server        | `/distributions/server/full`   | Packages Mule as a stand-alone server application. Includes all transports, extras and all dependencies. Includes the [Java Service Wrapper](http://wrapper.tanukisoftware.org/) for starting/stopping/restarting Mule from the native OS. | heavyweight |
| Custom Standalone Server      | `/distributions/server/custom` | Packages Mule as a standalone server application without any dependencies. If the user's project is based on Maven, this distribution can easily provide the exact libraries it depends upon because of m2's intelligent resolution of transitive dependencies. | lightweight |
| JCA Resource Adapter          | `/distributions/jca`           | Packages Mule as a JCA-compatible Resource Adapter for deployment into a J2EE application server. Includes all transports, extras and all dependencies. | heavyweight |
| Embedded (Composite) JAR File | `/distribution/embedded` | Packages Mule as a single JAR file containing all Mule classes, including all transports and extras). This distribution is useful when embedding Mule into another application, or when using Mule with a non-Maven-based build. Note that when you use this approach, you are responsible for providing any needed Mule dependencies, as described in the next section. | lightweight |
 

### Troubleshooting Maven

This section describes some problems you might experience using Maven and how to resolve or work around them.

| Problem                             | Description  |  Solution    |
|:------------------------------------|:-------------|:------------| 
| Files could not be retrieved	       | You are behind a firewall and get an error stating that repository metadata for org.mule.tools could not be retrieved from the central repository.|Check the proxy settings in your Maven settings.xml file to confirm that they are configured correctly.|
|OutOfMemory Error                    | You encounter OutOfMemoryError exceptions when attempting a full build of Mule.| Increasing the max heap and the PermGen space sizes. To do so, either export a MAVEN_OPTS variable in your shell, or add the variable to the original mvn script. Use the following: `MAVEN_OPTS=-Xmx512m -XX:MaxPermSize=256m` |
|Slow build                           | 	-	 |If you know your downloads are up-to-date, you can use the offline option using the following command: `mvn -o` |
| Conflicting transitive dependencies | Transitive dependencies in m2 are both powerful and problematic at times. For example, you many have conflicting library versions or when unwanted libraries are in your classpath.|	Use the debug option to display the effective classpath in a tree format, making it easy to see where each library is coming from: `mvn -x` |
| Running a goal for a specific project| By default, Maven execute a goal for a project and all sub-projects in its hierarchy. |	If you want to run the goal for a specific project only (and not its children), you can use the non-recursive option: `mvn -N` |
| Debugging test failures | 	Surefire, the default Maven test runner, outputs all reports as a set of XML and text files. Any test failure details and stack traces are written to those files instead of the console, so it can be time consuming to open files to find problems. | You can redirect the output to the console temporarily by adding the following option: `mvn -Dsurefire.useFile=false`. This option skips creation ofthe text report, but still makes the XML report available for transformation by tools. | 
