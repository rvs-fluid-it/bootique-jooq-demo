package com.foo;

import io.bootique.Bootique;

public class Runner {
    public static void main(String[] args) {
        Bootique.app(new String[]{"--server", "--config=demo-test.yml"}).autoLoadModules().run();
    }
}
