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

| Convention           | Example      | Notes  |
|:--------------------------|:-------------|:-------------|
| Names representing packages should be in all lower case.	 | `org.mule.application`      | Package naming convention used by Sun for the Java core packages. The initial package name representing the domain name must be in lower case. |
| Package names should be singular rather than plural.	 | `org.mule.transformer`, `org.mule.transport`      |  |
| The 'org.mule.api' package tree should be used for all interfaces that make up the API/SPI.	 | `org.mule.api.lifecycle.Callable`      |  |


## Interfaces/Classes

| Convention           | Example      | Notes  |
|:--------------------------|:-------------|:-------------|
| Names representing packages should be in all lower case.	 | `org.mule.application`      | Package naming convention used by Sun for the Java core packages. The initial package name representing the domain name must be in lower case. |
| Package names should be singular rather than plural.	 | `org.mule.transformer`, `org.mule.transport`      |  |
| The 'org.mule.api' package tree should be used for all interfaces that make up the API/SPI.	 | `org.mule.api.lifecycle.Callable`      |  |


