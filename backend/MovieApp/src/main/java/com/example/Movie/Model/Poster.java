package com.example.Movie.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Poster")
public class Poster
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long PosterId;

    private byte[] PosterData;


}
