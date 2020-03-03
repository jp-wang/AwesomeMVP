package io.jp.awesomemvp.model.repo.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.jp.awesomemvp.model.data.Character

@Dao
interface CharactersDao {
    @Query("SELECT * FROM Characters")
    fun observe(): LiveData<List<Character>>

    /**
     * Delete all characters.
     */
    @Query("DELETE FROM Characters")
    suspend fun deleteAll()

    /**
     * Insert a character in the database. If the character already exists, replace it.
     *
     * @param character the character to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(character: Character)

    /**
     * Select a character by id.
     *
     * @param id the character id.
     * @return the character.
     */
    @Query("SELECT * FROM Characters WHERE url = :id")
    suspend fun getCharacterById(id: String): Character?
}