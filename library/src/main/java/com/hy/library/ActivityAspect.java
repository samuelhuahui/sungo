package com.hy.library;

import android.content.Context;
import android.util.Log;
import android.view.View;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ActivityAspect {
    private static final String TAG = "ActivityAspect";


    @Pointcut("execution( * androidx.appcompat.app.AppCompatActivity.setContentView(..))")
    public void setContentViewPointcut() {
    }

    @Before("setContentViewPointcut()")
    public void setContentView(JoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0 && args[0] instanceof Integer) {
            int layoutId = (int) args[0];
            Context context = (Context) joinPoint.getThis();
            String typeName = context.getResources().getResourceTypeName(layoutId);
            String entryName = context.getResources().getResourceEntryName(layoutId);
            Log.d(TAG, context.getClass().getName());
            Log.d(TAG, typeName + "/" + entryName);
        }
    }

}
