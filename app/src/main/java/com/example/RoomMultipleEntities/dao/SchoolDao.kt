package com.example.RoomMultipleEntities.dao

import androidx.room.*
import com.example.RoomMultipleEntities.entities.Director
import com.example.RoomMultipleEntities.entities.School
import com.example.RoomMultipleEntities.entities.Student
import com.example.RoomMultipleEntities.entities.Subject
import com.example.RoomMultipleEntities.entities.relations.*


@Dao
interface SchoolDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchool(school: School)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDirector(director: Director)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: Subject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudentSubjectCrossRef(crossRef: StudentSubjectCrossRef)


    //one to one relationship "base on one school only has one director"
    @Transaction
    @Query("SELECT * FROM school where schoolName = :schoolName")
    suspend fun getSchoolAndDirectorWIthSchoolName(schoolName: String):List<SchoolAndDirector>

    //ont to many relationship "base on one school has many students"
    @Transaction
    @Query("SELECT * FROM school where schoolName = :schoolName")
    suspend fun getSchoolAndStudentWithSchoolName(schoolName: String):List<SchoolWithStudent>

    // many to many relationship "each student has multiples subjects, and each subject has multiples students."
    // from subject get all the students
    @Transaction
    @Query("SELECT * FROM subject where subjectName= :subjectName")
    suspend fun getStudentsOfSubject(subjectName:String):List<SubjectWithStudents>

    // from student get all the subjects
    @Transaction
    @Query("SELECT * FROM student where studentName= :studentName")
    suspend fun getSubjectsOfStudent(studentName:String):List<StudentWithSubjects>


}