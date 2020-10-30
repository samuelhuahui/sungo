/*
 * ViewClickAspect      2016-03-04
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 *
 */

package com.hy.library;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * class description here
 *
 * @author simon
 * @version 1.0.0
 * @since 2016-03-04
 */
@Aspect
public class ViewClickAspect {
    private static final String TAG = "Sungo";

    @Pointcut("execution(* android.view.View.OnClickListener.onClick(..))")
    public void clickPointcut() {
    }

    @Around("clickPointcut()")
    public void onClick(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0 && args[0] instanceof View) {
            View view = (View) args[0];
            int id = view.getId();
            if (id > 0) {
                String typeName = view.getContext().getResources().getResourceTypeName(id);
                String entryName = view.getContext().getResources().getResourceEntryName(id);
                Log.d(TAG, joinPoint.getSignature().toString());
                Log.d(TAG, typeName + "/" + entryName);
            }
        }
        joinPoint.proceed();
    }
}