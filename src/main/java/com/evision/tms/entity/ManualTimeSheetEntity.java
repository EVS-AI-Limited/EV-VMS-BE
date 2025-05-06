package com.evision.tms.entity;

import java.time.LocalDate;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "timesheet")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class ManualTimeSheetEntity {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	   // @SequenceGenerator(initialValue = 1, name = "emp_seq", sequenceName = "employee_sequence")
	    @Column(name = "id")
	    private Integer id;
	    @Column(name = "project_id",nullable = false)
	    private Integer projectId;
	    @Column(name = "project_name")
	    private String projectName;
	    @UpdateTimestamp
		@Column(name = "created_at",updatable = false, nullable =false )
		private LocalDate date;
		@Column(name = "date" )
		private String date1;
		@Column(name = "total_time" )
		private String duration;
		@Column(name = "task_title")
		private String description;
}
