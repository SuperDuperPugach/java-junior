package com.acme.edu.print;

/**
 * Created by pugach on 02/11/15.
 */
public class ConsolePrinter extends BufferPrinter {
    @Override
    public void print(String buffer, String format) {
        System.out.println(String.format(format, buffer));
    }
}
