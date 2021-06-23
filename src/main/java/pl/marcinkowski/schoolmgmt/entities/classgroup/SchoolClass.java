package pl.marcinkowski.schoolmgmt.entities.classgroup;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class SchoolClass {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private ClassSubject subject;
    private Integer grade;
    private String letter;

    @ManyToOne()
    @JoinColumn(name = "classteacher_id", referencedColumnName = "id")
    private ClassTeacher teacher;
}
