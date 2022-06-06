## What

Encountered a bug after switching from Proguard to R8.

This is a sample [class](src/main/java/com/example/r8bugsample/MainActivity.kt):

```kotlin
class Repository {
    val prop by MyDelegate()
    val prop1 = "abc"
}
```

In proguard:
```
-keep class com.example.r8bugsample.Repository { *; }
```

When try to access `Repository::prop.returnType`, get an exception:
```
kotlin.reflect.jvm.internal.KotlinReflectionInternalError: Property 'prop' (JVM signature: getProp()Ljava/lang/String;) not resolved in class com.example.r8bugsample.Repository
        at kotlin.reflect.jvm.internal.KDeclarationContainerImpl.findPropertyDescriptor(:86)
        at kotlin.reflect.jvm.internal.KPropertyImpl$_descriptor$1.invoke(:126)
        at kotlin.reflect.jvm.internal.KPropertyImpl$_descriptor$1.invoke(:125)
        at kotlin.reflect.jvm.internal.ReflectProperties$LazySoftVal.invoke(:93)
        at kotlin.reflect.jvm.internal.KPropertyImpl.getDescriptor(:129)
        at kotlin.reflect.jvm.internal.KPropertyImpl.getDescriptor(:29)
        at kotlin.reflect.jvm.internal.KCallableImpl$_returnType$1.invoke(:76)
        at kotlin.reflect.jvm.internal.KCallableImpl$_returnType$1.invoke(:75)
        at kotlin.reflect.jvm.internal.ReflectProperties$LazySoftVal.invoke(:93)
        at kotlin.reflect.jvm.internal.KCallableImpl.getReturnType(:82)
        at kotlin.jvm.internal.CallableReference.getReturnType(:145)
```

But when try to access `Repository::prop1.returnType`, works ok.
