package com.example.RoomMultipleEntities

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.RoomMultipleEntities.dao.SchoolDao
import com.example.RoomMultipleEntities.entities.Director
import com.example.RoomMultipleEntities.entities.School
import com.example.RoomMultipleEntities.entities.Student
import com.example.RoomMultipleEntities.entities.Subject
import com.example.RoomMultipleEntities.entities.relations.StudentSubjectCrossRef

@Database(
    entities = [
        School::class,
        Student::class,
        Director::class,
        Subject::class,
        StudentSubjectCrossRef::class
    ],
    version = 1,
)
abstract class SchoolDatabase : RoomDatabase() {
    abstract val schoolDao: SchoolDao

    //Singleton
    companion object {
        //Volatile means when we change variable INSTANCE in the database it will change immediately
        @Volatile
        private var INSTANCE: SchoolDatabase? = null

        fun getInstance(context: Context): SchoolDatabase {
            //synchronized means this method it's locked and only single thread processing.
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context,
                    SchoolDatabase::class.java,
                    "school_db"
                ).build().also {
                    INSTANCE = it  //when database have been created, also update the INSTANCE
                }
            }
        }
    }
}