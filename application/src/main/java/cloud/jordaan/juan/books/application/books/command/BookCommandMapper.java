package cloud.jordaan.juan.books.application.books.command;

import cloud.jordaan.juan.books.domain.Book;
import cloud.jordaan.juan.books.infrastructure.repository.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
interface BookCommandMapper {
    BookCommandMapper INSTANCE = Mappers.getMapper(BookCommandMapper.class);

    BookEntity map(Book from);
    Book map(BookEntity from);
}