package com.wcapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class Contact(val id: Long, val name: String, val phone: String)
data class MoodEntry(val userId: String, val mood: String, val diary: String, val timestamp: Long)

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 2
        private const val DATABASE_NAME = "wcapp.db"

        // Contacts table definition
        const val TABLE_CONTACTS = "contacts"
        const val COLUMN_CONTACT_ID = "contact_id"
        const val COLUMN_CONTACT_NAME = "contact_name"
        const val COLUMN_CONTACT_PHONE = "contact_phone"

        // Users table definition
        const val TABLE_USERS = "users"
        const val COLUMN_USER_ID = "user_id"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_PASSWORD = "password"

        // Mood entries table definition
        const val TABLE_MOOD_ENTRIES = "mood_entries"
        const val COLUMN_MOOD_ENTRY_ID = "mood_entry_id"
        const val COLUMN_MOOD_USER_ID = "user_id"
        const val COLUMN_MOOD = "mood"
        const val COLUMN_DIARY = "diary"
        const val COLUMN_TIMESTAMP = "timestamp"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Create Contacts table
        db?.execSQL(
            "CREATE TABLE $TABLE_CONTACTS (" +
                    "$COLUMN_CONTACT_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_CONTACT_NAME TEXT," +
                    "$COLUMN_CONTACT_PHONE TEXT)"
        )

        // Create Users table
        db?.execSQL(
            "CREATE TABLE $TABLE_USERS (" +
                    "$COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_USERNAME TEXT UNIQUE," +
                    "$COLUMN_PASSWORD TEXT)"
        )

        // Create Mood Entries table
        db?.execSQL(
            "CREATE TABLE $TABLE_MOOD_ENTRIES(" +
                    "$COLUMN_MOOD_ENTRY_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_MOOD_USER_ID TEXT," +
                    "$COLUMN_MOOD TEXT," +
                    "$COLUMN_DIARY TEXT," +
                    "$COLUMN_TIMESTAMP INTEGER)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Drop old tables if they exist
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_CONTACTS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_MOOD_ENTRIES")

        // Create new tables
        onCreate(db)
    }

    // Contact methods
    fun insertContact(name: String, phone: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_CONTACT_NAME, name)
            put(COLUMN_CONTACT_PHONE, phone)
        }
        val result = db.insert(TABLE_CONTACTS, null, values)
        db.close()
        return result
    }

    fun getAllContacts(): List<Contact> {
        val contacts = mutableListOf<Contact>()
        val db = readableDatabase
        val cursor = db.query(
            TABLE_CONTACTS,
            null,
            null,
            null,
            null,
            null,
            null
        )
        cursor.use {
            while (it.moveToNext()) {
                val id = it.getLong(it.getColumnIndexOrThrow(COLUMN_CONTACT_ID))
                val name = it.getString(it.getColumnIndexOrThrow(COLUMN_CONTACT_NAME))
                val phone = it.getString(it.getColumnIndexOrThrow(COLUMN_CONTACT_PHONE))
                val contact = Contact(id, name, phone)
                contacts.add(contact)
            }
        }
        cursor.close()
        db.close()
        return contacts
    }

    // User methods
    fun registerUser(username: String, password: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(COLUMN_PASSWORD, password)
        }
        val result = db.insert(TABLE_USERS, null, values)
        db.close()
        return result != -1L
    }

    fun checkLoginCredentials(username: String, password: String): Boolean {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_USERS,
            arrayOf(COLUMN_USER_ID),
            "$COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?",
            arrayOf(username, password),
            null,
            null,
            null
        )
        val isValid = cursor.count > 0
        cursor.close()
        db.close()
        return isValid
    }

    // MoodEntry methods
    fun insertMoodEntry(moodEntry: MoodEntry): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_MOOD_USER_ID, moodEntry.userId)
            put(COLUMN_MOOD, moodEntry.mood)
            put(COLUMN_DIARY, moodEntry.diary)
            put(COLUMN_TIMESTAMP, moodEntry.timestamp)
        }
        val result = db.insert(TABLE_MOOD_ENTRIES, null, values)
        db.close()
        return result
    }

    fun getAllMoodEntries(): List<MoodEntry> {
        val moodEntries = mutableListOf<MoodEntry>()
        val db = readableDatabase
        val cursor = db.query(
            TABLE_MOOD_ENTRIES,
            null,
            null,
            null,
            null,
            null,
            null
        )
        cursor.use {
            while (it.moveToNext()) {
                val userId = it.getString(it.getColumnIndexOrThrow(COLUMN_MOOD_USER_ID))
                val mood = it.getString(it.getColumnIndexOrThrow(COLUMN_MOOD))
                val diary = it.getString(it.getColumnIndexOrThrow(COLUMN_DIARY))
                val timestamp = it.getLong(it.getColumnIndexOrThrow(COLUMN_TIMESTAMP))
                val moodEntry = MoodEntry(userId, mood, diary, timestamp)
                moodEntries.add(moodEntry)
            }
        }
        cursor.close()
        db.close()
        return moodEntries
    }
}
