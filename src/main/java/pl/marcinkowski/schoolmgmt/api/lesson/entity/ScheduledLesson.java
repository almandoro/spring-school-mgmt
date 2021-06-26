package pl.marcinkowski.schoolmgmt.api.lesson.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.marcinkowski.schoolmgmt.api.schoolclass.entity.SchoolClass;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ScheduledLesson {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
//  @JoinColumn(name="schoolclass_id")
  private SchoolClass schoolClass;

  @OneToOne
  private Lesson lesson;

  @Basic
  @Temporal(TemporalType.TIMESTAMP)
  private java.util.Calendar startDate;

  @Basic
  @Temporal(TemporalType.TIMESTAMP)
  private java.util.Calendar endDate;

  public ScheduledLesson(SchoolClass schoolClass, Lesson lesson, Calendar startDate, Calendar endDate) {
    this.schoolClass = schoolClass;
    this.lesson = lesson;
    this.startDate = startDate;
    this.endDate = endDate;
  }
}
