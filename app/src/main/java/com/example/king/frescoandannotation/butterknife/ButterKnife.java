package com.example.king.frescoandannotation.butterknife;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ButterKnife {
    public static void bind(Activity activity){
        ContentViews(activity);
        bindViews(activity);
    }

    private static void ContentViews(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        ContentView annotation = aClass.getAnnotation(ContentView.class);
        if (annotation != null){
            try {
                int layoutID = annotation.value();
                //获取方法
                Method method = aClass.getMethod("setContentView",int.class);
                method.invoke(activity,layoutID);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 绑定控件
     * @param activity
     */
    private static void bindViews(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        Field[] fields = aClass.getDeclaredFields();
        if (fields != null && fields.length > 0){
            for (Field field : fields) {
                //通过域获取注解的值
                BindView bindView = field.getAnnotation(BindView.class);
                if (bindView != null) {
                    //给方法设置值
                    View viewById = activity.findViewById(bindView.value());
                    //开启暴力破解
                    field.setAccessible(true);
                    //设置属性的值
                    try {
                        field.set(activity,viewById);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
