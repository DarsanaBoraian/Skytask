package com.sky.library.dao;

/*
 * Copyright © 2015 Sky plc All Rights reserved.
 * Please do not make your solution publicly available in any way e.g. post in forums or commit to GitHub.
 */

import com.sky.library.model.entity.BookEntity;

import java.util.Optional;

public interface BookRepository {
    Optional<BookEntity> retrieveBook(String reference);
}
