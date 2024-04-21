package cloud.jordaan.juan.books.application.books.query;

import cloud.jordaan.juan.books.domain.Book;
import cloud.jordaan.juan.books.infrastructure.repository.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
interface BookQueryMapper {
    BookQueryMapper INSTANCE = Mappers.getMapper(BookQueryMapper.class);

    Book map(BookEntity from);
}