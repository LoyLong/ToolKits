package org.loy.dynaopproxy.jdkaopproxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class SimpleJDKMethodInterceptor implements MethodInterceptor {

    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("[SimpleJDKMethodInterceptor].[invoke].in");
        return null;
    }

}
