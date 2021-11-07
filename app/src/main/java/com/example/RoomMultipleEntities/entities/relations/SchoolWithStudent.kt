package com.example.RoomMultipleEntities.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.RoomMultipleEntities.entities.School
import com.example.RoomMultipleEntities.entities.Student


data class SchoolWithStudent(
    @Embedded val school: School,
    @Relation(
        parentColumn = "schoolName",
        entityColumn = "schoolName"
    )
    val student: List<Student>
)

