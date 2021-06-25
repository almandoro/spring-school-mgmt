package pl.marcinkowski.schoolmgmt.entities.lesson;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Lesson {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // TODO:
  @Enumerated(EnumType.STRING)
  private LessonSubject subject;

  @ManyToOne()
  @JoinColumn(name = "teacher_id", referencedColumnName = "id")
  private Teacher teacher;

}
