package ru.otus.amezgin.restclient.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BookDto {

    private String title;

    @JsonProperty("author")
    private AuthorDto authorDto;

    @JsonProperty("genres")
    private List<GenreDto> genreDtos = new ArrayList<>();
}
