package cloud.jordaan.juan.books.interfaces.rest;

import cloud.jordaan.juan.books.domain.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDto from(Book from);

    Book fromBookCreateRequest(BookCreateRequest from);

    Book fromBookUpdateRequest(BookUpdateRequest from);
}