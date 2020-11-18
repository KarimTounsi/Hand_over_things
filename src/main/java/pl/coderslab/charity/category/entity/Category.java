package pl.coderslab.charity.category.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@Transactional
@Getter
@Setter
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "name")
    private String name;

}
