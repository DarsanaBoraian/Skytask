package com.sky.library.service;

/*
 * Copyright © 2015 Sky plc All Rights reserved.
 * Please do not make your solution publicly available in any way e.g. post in forums or commit to GitHub.
 */

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(final String e) {
        super(e);
    }
}
