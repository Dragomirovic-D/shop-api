package com.example.shop_api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
        import lombok.*;

        import java.util.List;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true) // ignore extra JSON fields if any
public class Product {

    @Id
    private Long id;  // JSON provides id already

    private String name;
    private Double price;

    @Column(length = 500)
    private String shortDescription;

    @Column(length = 2000)
    private String fullDescription;

    @ElementCollection
    private List<String> images;

    @Convert(converter = JsonToMapConverter.class)
    @Column(columnDefinition = "TEXT")
    private Map<String, String> technicalSpecifications;
}
