package com.skillswapcomunity.skillswapcomunity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Requests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer status;

    @ManyToOne
    @JoinColumn(name="sender_id")
    @JsonBackReference
    private Person sender;

    @ManyToOne
    @JoinColumn(name="recipient_id")
    @JsonBackReference
    private Person recipient;
}
