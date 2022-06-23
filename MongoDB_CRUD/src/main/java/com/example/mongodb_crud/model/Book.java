package com.example.mongodb_crud.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;

import java.util.Date;

@Document(collection = "minhpv64")
@Getter
@Setter
@AllArgsConstructor
@Data

public class Book {
    private @Id String _id;
    private @TextIndexed String name;
    private @TextIndexed String author;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date publishDay;
    private String description;
}

