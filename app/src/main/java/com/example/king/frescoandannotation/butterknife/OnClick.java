package com.example.king.frescoandannotation.butterknife;

import android.support.annotation.IdRes;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.CLASS;

@Target(METHOD)
@Retention(CLASS)
//@ListenerClass(
//        targetType = "android.view.View",
//        setter = "setOnClickListener",
//        type = "butterknife.internal.DebouncingOnClickListener",
//        method = @ListenerMethod(
//                name = "doClick",
//                parameters = "android.view.View"
//        )
//)
public @interface OnClick {
    /** View IDs to which the method will be bound. */
    @IdRes int[] value() default { View.NO_ID };
}
