package com.example.RoomMultipleEntities.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.RoomMultipleEntities.entities.Director
import com.example.RoomMultipleEntities.entities.School


data class SchoolAndDirector(
    @Embedded val school: School,
    @Relation(
        parentColumn = "schoolName",
        entityColumn = "schoolName"
    )
    val director: Director
)