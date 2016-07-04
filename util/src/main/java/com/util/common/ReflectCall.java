package com.util.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by hooyee on 2016/7/4.
 * e-mail maohui_dream@outlook.com
 */
public class ReflectCall {
    public static Object callMethod(Object obj, String methodName) {
        try {
            Class clazz = obj.getClass();
            Method method = null;
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException ex) {
                Class ancestor = clazz.getSuperclass();
                while (ancestor != null) {
                    try {
                        method = ancestor.getDeclaredMethod(methodName);
                        if (!Modifier.isPublic(Modifier.methodModifiers()) &&
                                !Modifier.isProtected(Modifier.methodModifiers())) {
                            return obj;
                        }
                        break;
                    } catch (NoSuchMethodException ex1) {
                        ancestor = ancestor.getSuperclass();
                    }
                }
            }
            if(method == null) {
                return obj;
            }
            method.setAccessible(true);
            Object replacement = method.invoke(obj);
            return replacement;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
