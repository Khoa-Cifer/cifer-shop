package com.cifer.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Images")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageData { //Save the information of user's avatar image
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;
    private String name;
    private Date createdTime;
    private String type;
    private String filePath;
    private int versionCopy = 0;
    private String imageType;
    @OneToOne(mappedBy = "data")
    @JsonIgnore
    private Product product;
    @OneToOne(mappedBy = "data")
    @JsonIgnore
    private User user;
}
