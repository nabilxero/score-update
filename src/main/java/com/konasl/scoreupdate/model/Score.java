package com.konasl.scoreupdate.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "score_update")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "guid",unique = true,nullable = false)
    private String guid;

    @Column(name = "published_date")
    private Date publishedDate;

    @Column(name = "title")
    private String title;

    @Column(name = "link")
    private String link;

    @Column(name = "description")
    private String description;



}
