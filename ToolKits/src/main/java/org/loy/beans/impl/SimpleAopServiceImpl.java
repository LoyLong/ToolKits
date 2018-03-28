package org.loy.beans.impl;

import org.loy.beans.ISimpleAopService;

public class SimpleAopServiceImpl implements ISimpleAopService {

    @Override
    public void helloWorld() {
        System.out.println("[SimpleAopServiceImpl].helloWorld in ");
    }

}
