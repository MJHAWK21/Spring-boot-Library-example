package com.ust.LibraryExample.Repository;

import com.ust.LibraryExample.Entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<BookEntity,Long> {
}
